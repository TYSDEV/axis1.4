package test.saaj;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;

public class TestEnvelope extends junit.framework.TestCase {

    public TestEnvelope(String name) {
        super(name);
    }

    private SOAPEnvelope getSOAPEnvelope() throws Exception {
        SOAPConnectionFactory scFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection con = scFactory.createConnection();

        MessageFactory factory = MessageFactory.newInstance();
        SOAPMessage message = factory.createMessage();
        SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
        return envelope;
    }

    public void testAttributes() throws Exception {
        SOAPEnvelope envelope = getSOAPEnvelope();
        SOAPBody body = envelope.getBody();

        Name name1 = envelope.createName("MyAttr1");
        String value1 = "MyValue1";
        Name name2 = envelope.createName("MyAttr2");
        String value2 = "MyValue2";
        Name name3 = envelope.createName("MyAttr3");
        String value3 = "MyValue3";
        body.addAttribute(name1, value1);
        body.addAttribute(name2, value2);
        body.addAttribute(name3, value3);
        java.util.Iterator iterator = body.getAllAttributes();
        assertTrue(getIteratorCount(iterator) == 3);
        iterator = body.getAllAttributes();
        boolean foundName1 = false;
        boolean foundName2 = false;
        boolean foundName3 = false;
        while (iterator.hasNext()) {
            Name name = (Name) iterator.next();
            if (name.equals(name1))
                foundName1 = true;
            else if (name.equals(name2))
                foundName2 = true;
            else if (name.equals(name3))
                foundName3 = true;
        }
        assertTrue(foundName1 && foundName2 && foundName3);
    }

    public void testFaults() throws Exception {
        SOAPEnvelope envelope = getSOAPEnvelope();
        SOAPBody body = envelope.getBody();
        SOAPFault sf = body.addFault();
        sf.setFaultCode("myFault");
        String fc = sf.getFaultCode();
        assertTrue(fc.equals("myFault"));
    }

    public void testHeaderElements() throws Exception {
        SOAPEnvelope envelope = getSOAPEnvelope();
        SOAPBody body = envelope.getBody();
        SOAPHeader hdr = envelope.getHeader();

        SOAPHeaderElement she1 = hdr.addHeaderElement(envelope.createName("foo1", "f1", "foo1-URI"));
        she1.setActor("actor-URI");
        java.util.Iterator iterator = hdr.extractHeaderElements("actor-URI");
        int cnt = 0;
        while (iterator.hasNext()) {
            cnt++;
            SOAPHeaderElement she = (SOAPHeaderElement) iterator.next();
            assertTrue(she.equals(she1));
        }
        assertTrue(cnt == 1);
        iterator = hdr.extractHeaderElements("actor-URI");
        assertTrue(!iterator.hasNext());
    }

    private int getIteratorCount(java.util.Iterator i) {
        int count = 0;
        while (i.hasNext()) {
            count++;
            i.next();
        }
        return count;
    }

    public static void main(String[] args) throws Exception {
        test.saaj.TestEnvelope tester = new test.saaj.TestEnvelope("TestEnvelope");
        tester.testHeaderElements();
        tester.testFaults();
        tester.testAttributes();
    }
}
