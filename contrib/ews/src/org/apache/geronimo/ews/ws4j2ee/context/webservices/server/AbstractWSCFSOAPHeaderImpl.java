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

import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFSOAPHeader;

/**
 * This encapsulates the soap header of the handler element. The information about this element can be accessed uaing the interface published.
 * Basically this is  a Qname
 */
public abstract class AbstractWSCFSOAPHeaderImpl extends WSCFElement implements WSCFSOAPHeader {
	
	/**
	 * SOAP header namespace
	 */
	protected String namespaceURI;
	
	/**
	 * Soap Header local part
	 */
	protected String localpart;
	
	//////////////////////////////////jaxb interfacing block ///////////////////////////////
//	
//	protected XsdQNameType jaxbSoapHeader;
//	
//	public WSCFSOAPHeaderImpl(XsdQNameType jaxbSoapHeader){
//		this.jaxbSoapHeader = jaxbSoapHeader;
//		
//		if(null != jaxbSoapHeader.getValue()){	
//			this.localpart = jaxbSoapHeader.getValue().getLocalPart();
//			this.namespaceURI = jaxbSoapHeader.getValue().getNamespaceURI();
//		}
//			
//	}
//	
//	
//	////////////////////////////////////////////////////////////////////////////////////////
//	
//	/**
//	 * The constructor. This will get the namespace and the localpart of the SOAP header
//	 * @param e SOAP header element
//	 * @throws WSCFException
//	 */
//	public WSCFSOAPHeaderImpl(Element e)throws WSCFException{
//		super(e);
//		//		extract the namespace URI
//  		Element element = this.getChildElement(e, WSCFConstants.ELEM_WSCF_NAMESPACE_URI);
//  		if(null != element){this.namespaceURI = element.getChildNodes().item(0).toString();}
//
//  		//extract the local part
//  		element = this.getChildElement(e, WSCFConstants.ELEM_WSCF_LOCALPART);
//  		if(null != element){this.localpart = element.getChildNodes().item(0).toString();}
//	}
	
	/**
	 * gets the local part of the soap header
	 * @return local part
	 */
	public String getLocalpart() {
		return localpart;
	}

	/**
	 * Gets teh namespace of the SOAP header
	 * @return namespace
	 */
	public String getNamespaceURI() {
		return namespaceURI;
	}

//	/**
//	 * @return
//	 */
//	public XsdQNameType getJaxbSoapHeader() {
//		return jaxbSoapHeader;
//	}

}
