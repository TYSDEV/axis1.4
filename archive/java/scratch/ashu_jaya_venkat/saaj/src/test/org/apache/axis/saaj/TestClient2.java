/*
 * Created on Apr 8, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.apache.axis.saaj;

import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.XMLStreamException;
/**
 * @author shaas02
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TestClient2 {
	
	public static void main(String[] args){
		try{
			javax.xml.soap.MessageFactory fact = javax.xml.soap.MessageFactory.newInstance(); 
			javax.xml.soap.SOAPMessage message = fact.createMessage();
			javax.xml.soap.SOAPPart soapPart = message.getSOAPPart();
			SOAPEnvelopeImpl env = (SOAPEnvelopeImpl)soapPart.getEnvelope(); 
			Name ns =  env.createName("EchoOM", "test1", "http://MyClient.org/MyClient");
			SOAPBody body = env.getBody();
			SOAPElement bodyElmnt =  body.addBodyElement(ns);
			Name ns2 = env.createName("Text","test2", "http://MyClient.org/ABC");
			SOAPElement text = bodyElmnt.addChildElement(ns2);
			text.addTextNode("Echo String");
			// TODO: Test with TextImpl, need some fix 
			//TextImpl data = new TextImpl((SOAPElementImpl)text, "Echo String");
			//System.out.println(data.getParentElement().getElementName());
			//data.setParentElement(text);
			
			
			SOAPConnectionFactory factory = SOAPConnectionFactory.newInstance();
			SOAPConnection connection = factory.createConnection();
			javax.xml.soap.SOAPMessage respMsg = ((SOAPConnectionImpl)connection).call(message, "http://localhost:8080/axis2/services/myecho");
			SOAPEnvelope response = respMsg.getSOAPPart().getEnvelope();
			
			org.apache.axis.om.SOAPEnvelope omEnv = env.getOMEnvelope();
			System.out.println("Request sent   ...");
			XMLStreamWriter writer = XMLOutputFactory.newInstance().createXMLStreamWriter(System.out);
			omEnv.serialize(writer,true);
			writer.flush();
			System.out.println();
			org.apache.axis.om.SOAPEnvelope omResp = ((SOAPEnvelopeImpl)response).getOMEnvelope();
			System.out.println("Response received  ...");
			omResp.serialize(writer,true);
			writer.flush();
			
			} catch(SOAPException e){
				e.printStackTrace();
			} catch(XMLStreamException e1){
				e1.printStackTrace();
			}
	}

}
