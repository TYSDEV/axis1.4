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

import java.util.HashMap;
import java.util.Vector;

import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFHandler;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFInitParam;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFSOAPHeader;

/**
 * This encapsulates the layer 3 handler element of the webservices.xml. This
 * is also the concrete implementation of the WSCFHandler.
 *
 */
public abstract class AbstractWSCFHandler extends WSCFElement implements WSCFHandler {

	/**
	 * handler derscription 
	 */
	protected String description;
	
	/**
	 * handler display name
	 */
	protected String displayName;
	
	/**
	 * handler small icon
	 */
	protected String smallIcon;
	
	/**
	 * handler  large icon
	 */
	protected String largeIcon;
	
	/**
	 * handler name
	 */
	protected String handlerName;
	
	/**
	 * handler class
	 */
	protected String handlerClass;
	
	/**
	 * handler init parameters as a collection
	 */
	protected HashMap initParam = new HashMap();
	
	/**
	 * handler soap headers as a collection
	 */
	protected Vector soapHeader = new Vector();
	
	/**
	 * handler soap roles as a collection
	 */
	protected Vector soapRole = new Vector();
	
	
	
	
	
//	/////////////////////////////////////////////////////////////////////////////////////
//	
//	/**
//	 * The constructor. This will parse the chaild elementsin a depth first manner.
//	 * @param e handler Element
//	 * @throws WSCFException
//	 */
//	public AbstractWSCFHandlerImpl(Element e)throws WSCFException{
//		super(e);
//		
//		//extract description
//		Element element = this.getChildElement(e, WSCFConstants.ELEM_WSCF_DESCRIPTION);
//		if(null != element){this.description = element.getChildNodes().item(0).toString();}
//		
//		//extracting the display name
//		element = this.getChildElement(e, WSCFConstants.ELEM_WSCF_DISPLAY_NAME);
//		if(null != element){this.displayName = element.getChildNodes().item(0).toString();}
//		
//		//extract small icon
//		element = this.getChildElement(e, WSCFConstants.ELEM_WSCF_SMALL_ICON);
//		if(null != element){this.smallIcon = element.getChildNodes().item(0).toString();}
//		
//		//extract handler name
//		element = this.getChildElement(e, WSCFConstants.ELEM_WSCF_HANDLER_NAME);
//		if(null != element){this.handlerName = element.getChildNodes().item(0).toString();}
//		
//		// extract handler class
//		element = this.getChildElement(e, WSCFConstants.ELEM_WSCF_HANDLER_CLASS);
//		if(null != element){this.handlerClass = element.getChildNodes().item(0).toString();}
//		
//		//extracting the params
//		Element[] elements = this.getChildElements(e, WSCFConstants.ELEM_WSCF_INIT_PARAM);
//		for(int i=0; i < elements.length; i++){
//			WSCFInitParam initparam = new WSCFInitParamImpl(elements[i]);
//			this.initParam.put(initparam.getParamName(), initparam);
//		}
//		
//		//extracting the soap headers
//		elements = this.getChildElements(e, WSCFConstants.ELEM_WSCF_SOAP_HEADER);
//		for(int i=0; i < elements.length; i++){
//			this.soapHeader.add(new WSCFSOAPHeaderImpl(elements[i]));
//		}
//		
//		//extract the SOAP roles
//		elements = this.getChildElements(e, WSCFConstants.ELEM_WSCF_SOAP_ROLE);
//		for(int i=0; i < elements.length; i++){
//			this.soapRole.add(elements[i].getChildNodes().item(0).toString());			
//		}
//		
//	}
		
	

	/**
	 * Gets the description of the handler element
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Gets the display name of the handler element
	 * @return display-name
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * Gets the class of the handler element
	 * @return handler-class
	 */
	public String getHandlerClass() {
		return handlerClass;
	}

	/**
	 * Gets the name of the handler element
	 * @return handler-name
	 */
	public String getHandlerName() {
		return handlerName;
	}

	/**
	 * Gets the init paramaeters of the handler element as a array
	 * @return init-parameters
	 */
	public WSCFInitParam[] getInitParam() {
		WSCFInitParam[] initparam = new WSCFInitParam[this.initParam.size()];
		this.initParam.values().toArray(initparam);
		return initparam;
	}

	/**
	 * Gets the large icon of the handler element
	 * @return large-icon
	 */
	public String getLargeIcon() {
		return largeIcon;
	}

	/**
	 * Gets the small icon of the handler element
	 * @return small-icon
	 */
	public String getSmallIcon() {
		return smallIcon;
	}

	/**
	 * Gets the soap headers of the handler element
	 * @return soap-headers
	 */
	public WSCFSOAPHeader[] getSoapHeader() {
		WSCFSOAPHeader[] soapheader = new WSCFSOAPHeader[this.soapHeader.size()];
		int size = soapHeader.size();
		for(int i = 0;i<size;i++){
			soapheader[i] = ((WSCFSOAPHeader)soapHeader.get(i));
		}
		return soapheader;
	}

	/**
	 * Gets the soap roles of the handler element
	 * @return soap-roles
	 */
	public String[] getSoapRole() {
		String[] soaprole = new String[this.soapRole.size()];
		int size = soapRole.size();
		for(int i = 0;i<size;i++){
			soaprole[i] = (String)soapRole.get(i);
		}
		return soaprole;
	}

//	/**
//	 * @return
//	 */
//	public PortComponentHandlerType getJaxbHandler() {
//		return jaxbHandler;
//	}

}
