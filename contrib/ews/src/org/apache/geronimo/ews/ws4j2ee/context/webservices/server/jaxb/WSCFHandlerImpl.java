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

import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.AbstractWSCFHandlerImpl;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFHandler;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFInitParam;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.jaxb.impl.ParamValueTypeImpl;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.jaxb.impl.StringImpl;

/**
 * This encapsulates the layer 3 handler element of the webservices.xml. This
 * is also the concrete implementation of the WSCFHandler.
 *
 */
public class WSCFHandlerImpl extends AbstractWSCFHandlerImpl implements WSCFHandler {
	public WSCFHandlerImpl(PortComponentHandlerType jaxbHandler){
		//////////asigning the values/////////////////
	
		List temp = null;
		
		
		temp = jaxbHandler.getDescription();
		if(0 != temp.size())
			this.description = ((DescriptionType)temp.get(0)).getValue();
			
		temp = jaxbHandler.getDisplayName();
		if(0 != temp.size())
			this.displayName =((DisplayNameType)temp.get(0)).getValue();
			
		temp = jaxbHandler.getIcon();
		if(0 != temp.size()){		
			if(null != (PathType)((IconType)temp.get(0)).getSmallIcon())
				this.smallIcon =((PathType)((IconType)temp.get(0)).getSmallIcon()).getValue();
			
			if(null != (PathType)((IconType)temp.get(0)).getLargeIcon())
				this.largeIcon =((PathType)((IconType)temp.get(0)).getLargeIcon()).getValue();
		}
		
		if(null != jaxbHandler.getHandlerName())
			this.handlerName = jaxbHandler.getHandlerName().getValue();
		
		if(null != jaxbHandler.getHandlerClass())
			this.handlerClass = jaxbHandler.getHandlerClass().getValue();
		
		java.util.List list = jaxbHandler.getInitParam();
		for(int i=0; i < list.size(); i++){			
			WSCFInitParam initParameters = new WSCFInitParamImpl(((ParamValueTypeImpl)list.get(i)));
			this.initParam.put(initParameters.getParamName(), initParameters);
		}
		
		list = jaxbHandler.getSoapHeader();
		for(int i=0; i < list.size(); i++){
			this.soapHeader.add(new WSCFSOAPHeaderImpl((XsdQNameType)list.get(i))); 
		}
		
		list = jaxbHandler.getSoapRole();
		for(int i=0; i < list.size(); i++){
			this.soapRole.add(((StringImpl)list.get(i)).getValue()); 
		}		
	
	}
}
