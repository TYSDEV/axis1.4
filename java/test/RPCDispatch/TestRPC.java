package test.RPCDispatch;

import junit.framework.TestCase;
import org.apache.axis.AxisFault;
import org.apache.axis.Constants;
import org.apache.axis.Handler;
import org.apache.axis.Message;
import org.apache.axis.MessageContext;
import org.apache.axis.handlers.soap.SOAPService;
import org.apache.axis.message.RPCElement;
import org.apache.axis.message.RPCParam;
import org.apache.axis.message.SOAPEnvelope;
import org.apache.axis.server.AxisServer;

import org.xml.sax.SAXException;

import org.w3c.dom.Element;
import org.w3c.dom.Text;

import java.util.Vector;

/**
 * Test org.apache.axis.handlers.RPCDispatcher
 *
 * @author Sam Ruby <rubys@us.ibm.com>
 */
public class TestRPC extends TestCase {

    private final String header =
        "<?xml version=\"1.0\"?>\n" +
        "<soap:Envelope " +
             "xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
             "xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\" " +
             "xmlns:xsi=\"" + Constants.URI_CURRENT_SCHEMA_XSI + "\" " +
             "xmlns:xsd=\"" + Constants.URI_CURRENT_SCHEMA_XSD + "\">\n" +
             "<soap:Body>\n";

    private final String footer =
             "</soap:Body>\n" +
        "</soap:Envelope>\n";

    private AxisServer engine = new AxisServer();
    private Handler RPCDispatcher;

    private String SOAPAction = "urn:reverse";

    public TestRPC(String name) {
        super(name);
        engine.init();
        try {
            RPCDispatcher = engine.getHandler("RPCDispatcher");
        } catch (AxisFault fault) {
            // ???
        }
        // Debug.setDebugLevel(5);
    }

    /**
     * Invoke a given RPC method, and return the result
     * @param soapAction action to be performed
     * @param request XML body of the request
     * @return Deserialized result
     */
    private final Object rpc(String method, Object[] parms)
        throws AxisFault, SAXException
    {

        // Create the message context
        MessageContext msgContext = new MessageContext(engine);

        // Set the dispatch either by SOAPAction or methodNS
        String methodNS = null;
        msgContext.setTargetService(SOAPAction);

        // Construct the soap request
        SOAPEnvelope envelope = new SOAPEnvelope();
        msgContext.setRequestMessage(new Message(envelope));
        RPCElement body = new RPCElement(methodNS, method, parms);
        envelope.addBodyElement(body);

        // Invoke the Axis engine
        try {
            engine.invoke(msgContext);
        } catch (AxisFault af) {
            return af;
        }

        // Extract the response Envelope
        Message message = msgContext.getResponseMessage();
        envelope = (SOAPEnvelope)message.getSOAPPart().getAsSOAPEnvelope();
        assertNotNull("SOAP envelope was null", envelope);

        // Extract the body from the envelope
        body = (RPCElement)envelope.getFirstBody();
        assertNotNull("SOAP body was null", body);

        // Extract the list of parameters from the body
        Vector arglist = body.getParams();
        assertNotNull("SOAP argument list was null", arglist);
        if (arglist.size()==0) return null;

        // Return the first parameter
        RPCParam param = (RPCParam) arglist.get(0);
        return param.getValue();
    }

    /**
     * Test a simple method that reverses a string
     */
    public void testReverseString() throws Exception {
        // Register the reverseString service
        SOAPService reverse = new SOAPService(RPCDispatcher);
        reverse.setOption("className", "test.RPCDispatch.Service");
        reverse.setOption("methodName", "reverseString");
        engine.deployService(SOAPAction, reverse);

        // invoke the service and verify the result
        assertEquals("Did not reverse the string correctly.", "cba", rpc("reverseString", new Object[] {"abc"}));
    }

    /**
     * Test a method that reverses a data structure
     */
    public void testReverseData() throws Exception {
        // Register the reverseData service
        SOAPService reverse = new SOAPService(RPCDispatcher);
        reverse.setOption("className", "test.RPCDispatch.Service");
        reverse.setOption("methodName", "reverseData");
        engine.deployService(SOAPAction, reverse);

        // invoke the service and verify the result
        Data input    = new Data(5, "abc", 3);
        Data expected = new Data(3, "cba", 5);
        assertEquals("Did not reverse the data as expected.", expected, rpc("reverseData", new Object[] {input}));
    }

    /**
     * Test a simple method that returns a field from the message context
     */
    public void testMessageContext() throws Exception {
        // Register the targetService service
        SOAPService tgtSvc = new SOAPService(RPCDispatcher);
        tgtSvc.setOption("className", "test.RPCDispatch.Service");
        tgtSvc.setOption("methodName", "targetService");
        engine.deployService(SOAPAction, tgtSvc);

        // invoke the service and verify the result
        assertEquals("SOAP Action did not equal the targetService.", SOAPAction, rpc("targetService", new Object[] {}));
    }

    /**
     * Test a simple method that accepts and returns a null
     */
    public void testNull() throws Exception {
        // Register the echoInt service
        SOAPService echoInt = new SOAPService(RPCDispatcher);
        echoInt.setOption("className", "test.RPCDispatch.Service");
        echoInt.setOption("methodName", "echoInt");
        engine.deployService(SOAPAction, echoInt);

        // invoke the service and verify the result
        assertNull("The result was not null as expected.", rpc("echoInt", new Object[] {null}));
    }
    
    /**
     * Test faults
     */
    public void testSimpleFault() throws Exception {
        // Register the reverseData service
        SOAPService simpleFault = new SOAPService(RPCDispatcher);
        simpleFault.setOption("className", "test.RPCDispatch.Service");
        simpleFault.setOption("methodName", "simpleFault");
        engine.deployService(SOAPAction, simpleFault);

        Object result = rpc("simpleFault", new Object[] {"foobar"});
        assertTrue("Did not get a fault as expected.", 
           result instanceof AxisFault);
        AxisFault fault = (AxisFault) result;
        assertEquals("faultString was not set correctly.", 
            "test.RPCDispatch.Service$TestFault: foobar", fault.getFaultString());
    }

    public static void main(String args[])
    {
      try {
        TestRPC tester = new TestRPC("RPC test");
        tester.testReverseString();
        tester.testReverseData();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
}
