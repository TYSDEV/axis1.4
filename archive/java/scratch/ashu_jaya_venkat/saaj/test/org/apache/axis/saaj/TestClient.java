/*
 * Created on Mar 21, 2005
 *
 */
package org.apache.axis.saaj;

import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.axis.Constants;
import org.apache.axis.clientapi.Call;
import org.apache.axis.addressing.AddressingConstants;
import org.apache.axis.addressing.EndpointReference;
import org.apache.axis.engine.AxisFault;

/**
 * Class TestClient
 * Creates a SAAJ specific soap message using SAAJ api
 * Takes the omEnvelope from this SAAJ Envelope and sends it using Call.sendReceive()
 * recives the echoed response
 * 
 * @author Ashutosh Shahi
 * ashutosh.shahi@gmail.com
 */
public class TestClient {
	
	public static void main(String[] args){
		try{
		SOAPEnvelopeImpl env = new SOAPEnvelopeImpl();
		Name ns =  env.createName("EchoOM", "test1", "http://MyClient.org/MyClient");
		SOAPBody body = env.getBody();
		SOAPElement bodyElmnt =  body.addBodyElement(ns);
		Name ns2 = env.createName("Text","test2", "http://MyClient.org/ABC");
		SOAPElement text = bodyElmnt.addChildElement(ns2);
		text.addTextNode("Echo String");
		
		org.apache.axis.soap.SOAPEnvelope omEnv = env.getOMEnvelope();
		
		System.out.println("Request sent   ...");
		XMLStreamWriter writer = XMLOutputFactory.newInstance().createXMLStreamWriter(System.out);
		omEnv.serialize(writer);
		writer.flush();
		System.out.println();
		
		Call call = new Call();
		URL url = null;

		try {
				url = new URL("http://localhost:8080/axis2/services/myecho");
		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		call.setTransportInfo(Constants.TRANSPORT_HTTP,Constants.TRANSPORT_HTTP, true);
		call.setTo(new EndpointReference(AddressingConstants.WSA_TO, url.toString()));
		
		org.apache.axis.soap.SOAPEnvelope responseEnv = (org.apache.axis.soap.SOAPEnvelope)call.invokeBlocking("echo", omEnv);

		System.out.println("Responce received  ...");
		responseEnv.serialize(writer);
		writer.flush();
		
		} catch(AxisFault e1){
			e1.printStackTrace();
		} catch(javax.xml.soap.SOAPException e){
			e.printStackTrace();
		} catch (XMLStreamException e1) {
			e1.printStackTrace();
		}catch (FactoryConfigurationError e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
