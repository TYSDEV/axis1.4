/*
 * Created on Apr 13, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.apache.axis.saaj;

import junit.framework.TestCase;

import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;


/**
 * @author shaas02
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TestSOAPEnvelope extends TestCase {
	
    public TestSOAPEnvelope(String name) {
        super(name);
    }
    
    public void testName() throws Exception {
        SOAPEnvelope env = new org.apache.axis.saaj.SOAPEnvelopeImpl();
        Name n = env.createName("local", "pref", "urn:blah");
        assertEquals("local part of name did not match", "local",
                     n.getLocalName());
        assertEquals("qname of name did not match", "pref:local",
                     n.getQualifiedName());
        assertEquals("prefix of name did not match", "pref",
                     n.getPrefix());
        assertEquals("uri of name did not match", "urn:blah",
                     n.getURI());
        Name n2 = env.createName("loc");
        assertEquals("local part of name2 did not match", "loc",
                     n2.getLocalName());
    }
    
    public void testHeader() throws Exception {
        SOAPEnvelope env = new org.apache.axis.saaj.SOAPEnvelopeImpl();
        SOAPHeader h1 = env.getHeader();
        assertTrue("null initial header", h1 != null);
        h1.detachNode();
        assertTrue("header not freed", env.getHeader() == null);
        SOAPHeader h2 = env.addHeader();
        assertTrue("null created header", h2 != null);
        assertEquals("wrong header retrieved", h2, env.getHeader());
        assertEquals("header parent incorrect", env, h2.getParentElement());
        
        try {
            env.addHeader();
            assertTrue("second header added", false);
        } catch (SOAPException e) {
        }
    }

    public void testBody() throws Exception {
        SOAPEnvelope env = new org.apache.axis.saaj.SOAPEnvelopeImpl();
        SOAPBody b1 = env.getBody();
        assertTrue("null initial body", b1 != null);
        b1.detachNode();
        assertTrue("body not freed", env.getBody() == null);
        SOAPBody b2 = env.addBody();
        assertTrue("null created body", b2 != null);
        assertEquals("wrong body retrieved", b2, env.getBody());
        assertEquals("body parent incorrect", env, b2.getParentElement());
        try {
            env.addBody();
            assertTrue("second body added", false);
        } catch (SOAPException e) {
        }
    }
    
	/*public void testNullpointer() throws Exception{
		org.apache.axis.saaj.SOAPEnvelopeImpl env=new org.apache.axis.saaj.SOAPEnvelopeImpl();
		SOAPBodyElementImpl bdy=new SOAPBodyElementImpl();
		bdy.setName("testResponse");
		env.addBodyElement(bdy);
		Message msg=new Message(env);
		SOAPBodyElement sbe = msg.getSOAPEnvelope().getBodyByName(null,"testResponse");
        assertTrue(sbe != null);
	}
	
    public void testNullpointerInHeader() throws Exception{
		org.apache.axis.saaj.SOAPEnvelopeImpl env=new org.apache.axis.saaj.SOAPEnvelopeImpl();
		SOAPHeaderElementImpl hdr=new SOAPHeaderElementImpl("", "testHeader");
		env.addHeader(hdr);
		Message msg=new Message(env);
		SOAPHeaderElement she = msg.getSOAPEnvelope().getHeaderByName(null,"testHeader");
        assertTrue(she != null);
	}*/
    
    public static void main(String args[]) throws Exception {
        TestSOAPEnvelope tester = new TestSOAPEnvelope("TestSOAPEnvelope");
        tester.testName();
        tester.testHeader();
        tester.testBody();
    }

}
