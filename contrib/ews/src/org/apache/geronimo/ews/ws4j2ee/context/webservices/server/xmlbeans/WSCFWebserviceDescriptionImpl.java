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
package org.apache.geronimo.ews.ws4j2ee.context.webservices.server.xmlbeans;

import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.AbstractWSCFWebserviceDescriptionImpl;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.WSCFException;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFPortComponent;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFWebserviceDescription;

import com.sun.java.xml.ns.j2Ee.IconType;
import com.sun.java.xml.ns.j2Ee.PortComponentType;

/**
 * This represents a level 1 element in the Element tree :webservice-description. This is the concrete implementation of the
 * WSCFWebServiceDescription interface
 *
 */
public class WSCFWebserviceDescriptionImpl extends AbstractWSCFWebserviceDescriptionImpl implements WSCFWebserviceDescription{
	public WSCFWebserviceDescriptionImpl(com.sun.java.xml.ns.j2Ee.WebserviceDescriptionType wsdes) throws WSCFException{

		this.description = XMLBeansUtils.getStringValue(wsdes.getDescription());
		
		this.displayName = XMLBeansUtils.getStringValue(wsdes.getDisplayName());

        IconType icon = wsdes.getIcon();
        if(icon != null){
            this.smallIcon = XMLBeansUtils.getStringValue(icon.getSmallIcon());
            this.largeIcon = XMLBeansUtils.getStringValue(icon.getLargeIcon());
        }
  		
  		this.webserviceDescriptionName = XMLBeansUtils.getStringValue(wsdes.getWebserviceDescriptionName());
		this.wsdlFile = XMLBeansUtils.getStringValue(wsdes.getWsdlFile());
		this.jaxrpcMappingFile = XMLBeansUtils.getStringValue(wsdes.getJaxrpcMappingFile());;
  		
        PortComponentType[] list = wsdes.getPortComponentArray();
  		
  		if (0 == list.length){
            throw new WSCFException("At least one port-component element should exist in the "+this.description+" webservices element.");}
  		for(int i=0; i < list.length; i++){
	  		WSCFPortComponent portComponent = new WSCFPortComponentImpl(list[i]);
	  		this.portComponent.put(portComponent.getPortComponentName(), portComponent);
		}
	}
}
