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
package org.apache.geronimo.ews.ws4j2ee.context.webservices.server.jaxb;

import java.util.List;

import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.AbstractWSCFInitParam;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFInitParam;

/**
 * This will encapsulate the init parameter element and the information will be able access 
 * using the interface published.
 *
 */
public class WSCFInitParamImpl extends AbstractWSCFInitParam implements WSCFInitParam {

	public WSCFInitParamImpl(ParamValueType jaxbInitParameter){
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
}
