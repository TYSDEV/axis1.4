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

import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.AbstractWSCFPortComponent;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFHandler;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFPortComponent;

import com.sun.java.xml.ns.j2Ee.IconType;
import com.sun.java.xml.ns.j2Ee.PortComponentHandlerType;
import com.sun.java.xml.ns.j2Ee.PortComponentType;

/**
 * This encapsulates the level 2 Elemenr PortComponent which is a child element of the webservice-description element.
 * It is also the concrete implmentation of the WSCFPortComponent. 
 *
 */
public class WSCFPortComponentImpl extends AbstractWSCFPortComponent implements WSCFPortComponent {
	
	public WSCFPortComponentImpl(PortComponentType pc){

//		/////////////assigning the values //////////////
		this.description = XMLBeansUtils.getStringValue(pc.getDescription());
		
			this.displayName = XMLBeansUtils.getStringValue(pc.getDisplayName());

		
            IconType icon = pc.getIcon();
            if(icon != null){
                this.smallIcon = XMLBeansUtils.getStringValue(icon.getSmallIcon());
                this.largeIcon = XMLBeansUtils.getStringValue(icon.getLargeIcon());
            }
		
			this.portComponentName = XMLBeansUtils.getStringValue(pc.getPortComponentName());
		
			this.serviceEndpointInterface = XMLBeansUtils.getStringValue(pc.getServiceEndpointInterface());
			
				
		this.wsdlPort = new WSCFWSDLPortImpl(pc.getWsdlPort());
        
		this.serviceImplBean = new WSCFServiceImplBeanImpl(pc.getServiceImplBean());
        
		PortComponentHandlerType[] list = pc.getHandlerArray();
		for(int i=0; i < list.length; i++){
			WSCFHandler handler = new WSCFHandlerImpl(list[i]);
			this.handlers.put(handler.getHandlerName(), handler);
		}
	}
	
}
