package test.encoding;

import junit.framework.TestCase;
import org.apache.axis.Constants;
import org.apache.axis.Message;
import org.apache.axis.MessageContext;
import org.apache.axis.configuration.BasicServerConfig;
import org.apache.axis.encoding.SerializationContext;
import org.apache.axis.encoding.SerializationContextImpl;
import org.apache.axis.encoding.TypeMapping;
import org.apache.axis.encoding.TypeMappingRegistry;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;
import org.apache.axis.encoding.ser.SimpleDeserializerFactory;
import org.apache.axis.encoding.ser.SimpleNonPrimitiveSerializerFactory;
import org.apache.axis.message.RPCElement;
import org.apache.axis.message.RPCParam;
import org.apache.axis.message.SOAPEnvelope;
import org.apache.axis.server.AxisServer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.rpc.namespace.QName;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Vector;


/**
 *  Test the serialization of a bean with attributes
 *
 * @author Tom Jordahl (tomj@macromedia.com)
 * @author Glen Daniels (gdaniels@apache.org)
 */
public class TestAttributes extends TestCase {
    static Log log =
            LogFactory.getLog(TestAttributes.class.getName());

    public static final String myNS = "urn:myNS";

    public static void main(String [] args) throws Exception
    {
        TestAttributes tester = new TestAttributes("TestAttributes");
        tester.testBean();
        tester.testSimpleType();
    }

    public TestAttributes(String name) {
        super(name);
    }

    public void testBean () throws Exception {
        MessageContext msgContext = new MessageContext(new AxisServer(new BasicServerConfig()));
        SOAPEnvelope msg = new SOAPEnvelope();

        // Create bean with data
        AttributeBean bean = new AttributeBean();
        bean.setAge(35);
        bean.setID(1.15F);
        bean.setMale(true);
        bean.company = "Majesty's Secret Service";
        bean.setName("James Bond");

        RPCParam arg = new RPCParam("", "struct", bean);
        RPCElement body = new RPCElement("urn:myNamespace", "method1", new Object[]{ arg });
        msg.addBodyElement(body);
        body.setEncodingStyle(null);

        Writer stringWriter = new StringWriter();
        SerializationContext context = new SerializationContextImpl(stringWriter, msgContext);
        context.setDoMultiRefs(false);  // no multirefs
        context.setPretty(false);

        // Create a TypeMapping and register the Bean serializer/deserializer
        TypeMappingRegistry reg = context.getTypeMappingRegistry();
        TypeMapping tm = (TypeMapping) reg.createTypeMapping();
        // The "" namespace is literal (no encoding).
        tm.setSupportedNamespaces(new String[] {Constants.URI_CURRENT_SOAP_ENC});
        reg.register(Constants.URI_CURRENT_SOAP_ENC, tm);

        QName beanQName = new QName("typeNS", "TheBean");
        tm.register(AttributeBean.class,
                    beanQName,
                    new BeanSerializerFactory(AttributeBean.class, beanQName),
                    new BeanDeserializerFactory(AttributeBean.class, beanQName));

        // Serialize the bean in to XML
        msg.output(context);
        // Get the XML as a string
        String msgString = stringWriter.toString();

        log.debug("---");
        log.debug(msgString);
        log.debug("---");

        Message message = new Message(msgString);
        message.setMessageContext(msgContext);
        SOAPEnvelope env = message.getSOAPPart().getAsSOAPEnvelope();
        RPCElement rpcEl = (RPCElement)env.getFirstBody();
        Vector params = rpcEl.getParams();
        assertEquals("Wrong # of params in deserialized message!",
                     1,
                     params.size());

        Object obj = ((RPCParam)params.get(0)).getValue();
        assertTrue("Deserialized param not an AttributeBean!",
                   (obj instanceof AttributeBean));
        AttributeBean deserBean = (AttributeBean)obj;
        assertTrue("Deserialized bean not equal to expected values!",
                   (bean.equals(deserBean)));
    }

    public void testSimpleType() throws Exception {
        SimpleBean bean = new SimpleBean("test value");
        bean.temp = 85.0F;

        MessageContext msgContext = new MessageContext(new AxisServer(new BasicServerConfig()));
        SOAPEnvelope msg = new SOAPEnvelope();

        RPCParam arg = new RPCParam("", "simple", bean);
        RPCElement body = new RPCElement("urn:myNamespace", "method1", new Object[]{ arg });
        msg.addBodyElement(body);
        body.setEncodingStyle(null);

        StringWriter writer = new StringWriter();
        SerializationContext context = new SerializationContextImpl(writer,
                                                                    msgContext);
        context.setDoMultiRefs(false);

        // Create a TypeMapping and register the Bean serializer/deserializer
        TypeMappingRegistry reg = context.getTypeMappingRegistry();
        TypeMapping tm = (TypeMapping) reg.createTypeMapping();
        // The "" namespace is literal (no encoding).
        tm.setSupportedNamespaces(new String[] {Constants.URI_CURRENT_SOAP_ENC});
        reg.register(Constants.URI_CURRENT_SOAP_ENC, tm);

        QName beanQName = new QName("typeNS", "Bean");
        tm.register(SimpleBean.class,
                    beanQName,
                    new SimpleNonPrimitiveSerializerFactory(SimpleBean.class, beanQName),
                    new SimpleDeserializerFactory(SimpleBean.class, beanQName));

        // Serialize the bean in to XML
        msg.output(context);
        // Get the XML as a string
        String msgString = writer.toString();

        Message message = new Message(msgString);
        message.setMessageContext(msgContext);
        SOAPEnvelope env = message.getSOAPPart().getAsSOAPEnvelope();
        RPCElement rpcEl = (RPCElement)env.getFirstBody();
        Vector params = rpcEl.getParams();
        assertEquals("Wrong # of params in deserialized message!",
                     1,
                     params.size());

        Object obj = ((RPCParam)params.get(0)).getValue();
        assertTrue("Deserialized param not a SimpleBean!",
                   (obj instanceof SimpleBean));

        SimpleBean deserBean = (SimpleBean)obj;
        assertTrue("Deserialized bean not equal to expected values!",
                   (bean.equals(deserBean)));
    }
}
