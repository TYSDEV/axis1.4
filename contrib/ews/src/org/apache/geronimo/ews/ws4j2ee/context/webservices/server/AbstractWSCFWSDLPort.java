/*
 * Copyright 2001-2004 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.geronimo.ews.ws4j2ee.context.webservices.server;

import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFWSDLPort;

/**
 * This encapsulates the Qname wsdlport name and this is the concrete implementation of the WSCFWSDLPort
 *
 */
public abstract class AbstractWSCFWSDLPort extends WSCFElement implements WSCFWSDLPort {
	
	/**
	 * WSDL Port namespace URI
	 */
	protected String namespaceURI;
	
	/**
	 * WSDL port local part
	 */
	protected String localpart;
	
	
	////////////////////////////////jaxb interfacing block/////////////////////////////////////
//	
//	protected XsdQNameType jaxbWSDLPort;
//	
//	public WSCFWSDLPortImpl(XsdQNameType jaxbWSDLPort){
//		
//		if(null == jaxbWSDLPort){return;}
//		
//		this.jaxbWSDLPort = jaxbWSDLPort;		
//
//		if(null != jaxbWSDLPort.getValue()){
//			this.localpart = jaxbWSDLPort.getValue().getLocalPart();
//			this.namespaceURI = jaxbWSDLPort.getValue().getNamespaceURI();
//		}
//		
//		
//	}
//	
//	
//	///////////////////////////////////////////////////////////////////////////////////////////
//	
//	
//	/**
//	 * The constructor. this will get the naspace and the lacalpart extracted from the element.
//	 * @param e
//	 * @throws WSCFException
//	 */
//	public WSCFWSDLPortImpl(Element e) throws WSCFException{
//		super(e);
//		//extract the namespace URI
//		Element element = this.getChildElement(e, WSCFConstants.ELEM_WSCF_NAMESPACE_URI);
//		if(null != element){this.namespaceURI = element.getNodeValue();}
//		
//		//extract the local part
//		element = this.getChildElement(e, WSCFConstants.ELEM_WSCF_LOCALPART);
//		if(null != element){this.localpart = element.getNodeValue();}		
//		
//	}

	/**
	 * Gets the local part of the WSDL port element
	 * @return local part
	 */
	public String getLocalpart() {
		return localpart;
	}

	/**
	 * Gets the namespace URI of the WSDL port element
	 * @return
	 */
	public String getNamespaceURI() {
		return namespaceURI;
	}

	/**
	 * @return
	 */
//	public XsdQNameType getJaxbWSDLPort() {
//		return jaxbWSDLPort;
//	}
//
}
