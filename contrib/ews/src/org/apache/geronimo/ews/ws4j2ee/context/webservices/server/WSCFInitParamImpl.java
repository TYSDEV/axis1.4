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

import java.util.List;

import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFConstants;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFInitParam;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.jaxb.DescriptionType;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.jaxb.ParamValueType;
import org.w3c.dom.Element;

/**
 * This will encapsulate the init parameter element and the information will be able access 
 * using the interface published.
 *
 */
public class WSCFInitParamImpl extends WSCFElement implements WSCFInitParam {

	/**
	 * Init parameter name 
	 */
	private String paramName;
	
	/**
	 * Init parameter value
	 */
	private String paramValue;
	
	/**
	 * Init parameter description
	 */
	private String description;
	
	
	////////////////////////////////////////jaxb intefacing block////////////////////////////
	
	private ParamValueType jaxbInitParameter;
	
	public WSCFInitParamImpl(ParamValueType jaxbInitParameter){
		this.jaxbInitParameter = jaxbInitParameter;
		
		/////////////////asigning values///////////////////
		if(null != jaxbInitParameter.getParamName())
			this.paramName = jaxbInitParameter.getParamName().getValue();
		
		if(null != jaxbInitParameter.getParamValue())	
		this.paramValue = jaxbInitParameter.getParamValue().getValue();
		
		List temp = null;
		temp = jaxbInitParameter.getDescription();
		if(0 != temp.size())
			this.description = ((DescriptionType)temp.get(0)).getValue();
	
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////////	
	
	/**
	 * The constructor. this willget the child nodes that provide teh sufficient statistics about the init parameters.
	 * @param e init parameter Element
	 * @throws WSCFException
	 */
	public WSCFInitParamImpl(Element e) throws WSCFException{
		super(e);
		
		//extract param name
		Element element = this.getChildElement(e, WSCFConstants.ELEM_WSCF_PARAM_NAME);
		if(null != element){this.paramName = element.getChildNodes().item(0).toString();}
		
		//extract param value
		element = this.getChildElement(e, WSCFConstants.ELEM_WSCF_PARAM_VLAUE);
		if(null != element){this.paramValue = element.getChildNodes().item(0).toString();}
		
		// extract the description
		element = this.getChildElement(e, WSCFConstants.ELEM_WSCF_DESCRIPTION);
		if(null != element){this.description = element.getChildNodes().item(0).toString();}
		
	
	}
	
	
	/**
	 * Gets the description of the init parameter element
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Gets the name of the init parameter element
	 * @return name
	 */
	public String getParamName() {
		return paramName;
	}

	/**
	 * Gets the value of the init parameter element
	 * @return value
	 */
	public String getParamValue() {
		return paramValue;
	}

	/**
	 * @return
	 */
	public ParamValueType getJaxbInitParameter() {
		return jaxbInitParameter;
	}

}
