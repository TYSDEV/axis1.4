package test.encoding;

import junit.framework.TestCase;
import org.apache.axis.Constants;
import org.apache.axis.Message;
import org.apache.axis.MessageContext;
import org.apache.axis.message.RPCElement;
import org.apache.axis.message.RPCParam;
import org.apache.axis.message.SOAPEnvelope;
import org.apache.axis.server.AxisServer;

import java.util.Vector;

/** 
 * Test deserialization of SOAP messages with references, by putting the
 * actual value in various places in the message.
 * 
 * @author Glen Daniels (gdaniels@macromedia.com)
 */
public class TestHrefs extends TestCase {

    private String header;
    private String [] messageParts;
    
    public TestHrefs(String name) {
        this(name, Constants.URI_CURRENT_SCHEMA_XSI, 
                   Constants.URI_CURRENT_SCHEMA_XSD);
    }

    public static void main(String [] args)
    {
        TestHrefs tester = new TestHrefs("me");
        try {
            tester.testStringReference2();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public TestHrefs(String name, String NS_XSI, String NS_XSD) {
        super(name);

        header = 
            "<?xml version=\"1.0\"?>\n" +
            "<soap:Envelope " +
              "xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
              "xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\" " +
              "xmlns:xsi=\"" + NS_XSI + "\" " +
              "xmlns:xsd=\"" + NS_XSD + "\">\n" +
              "<soap:Body>\n" +
                "<methodResult xmlns=\"http://tempuri.org/\">\n" +
                 "<reference href=\"#1\"/>\n" +
                "</methodResult>\n";
        
        messageParts = new String [] {
              "</soap:Body>\n",
        
             "</soap:Envelope>\n" };
    }

    private void deserialize(String data, Object expected, int pos) 
       throws Exception
    {
       String msgString = header;
       
       for (int i = 0; i < messageParts.length; i++) {
           if (pos == i)
               msgString += data;
           msgString += messageParts[i];
       }
       
       Message message = new Message(msgString);
       message.setMessageContext(new MessageContext(new AxisServer()));

       SOAPEnvelope envelope = (SOAPEnvelope)message.getAsSOAPEnvelope();
       assertNotNull("SOAP envelope should not be null", envelope);

       RPCElement body = (RPCElement)envelope.getFirstBody();
       assertNotNull("SOAP body should not be null", body);

       Vector arglist = body.getParams();
       assertNotNull("arglist", arglist);
       assertTrue("SOAP param.size()<=0 {Should be > 0}", arglist.size()>0);

       RPCParam param = (RPCParam) arglist.get(0);
       assertNotNull("SOAP param should not be null", param);

       Object result = param.getValue();
       assertEquals("Expected result not received for case " + pos, expected, result);
    }

    public void testStringReference1() throws Exception {
        String result = 
            "<result root=\"0\" id=\"1\" xsi:type=\"xsd:string\">abc</result>";
        deserialize(result, "abc", 0);
    }

    public void testStringReference2() throws Exception {
        String result = 
            "<result root=\"0\" id=\"1\" xsi:type=\"xsd:string\">abc</result>";
        deserialize(result, "abc", 1);
    }

}
