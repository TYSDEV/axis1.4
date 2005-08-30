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

import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.AbstractWSCFPortComponent;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFHandler;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFPortComponent;

/**
 * This encapsulates the level 2 Elemenr PortComponent which is a child element of the webservice-description element.
 * It is also the concrete implmentation of the WSCFPortComponent.
 */
public class WSCFPortComponentImpl extends AbstractWSCFPortComponent implements WSCFPortComponent {
    public WSCFPortComponentImpl(PortComponentType jaxbPortComponent) {
//		/////////////assigning the values //////////////
        if (null != jaxbPortComponent.getDescription())
            this.description = jaxbPortComponent.getDescription().getValue();
        if (null != jaxbPortComponent.getDisplayName())
            this.displayName = jaxbPortComponent.getDisplayName().getValue();
        if (null != jaxbPortComponent.getIcon())
            this.smallIcon = ((PathType) (jaxbPortComponent.getIcon()).getSmallIcon()).getValue();
        if (null != jaxbPortComponent.getIcon())
            this.largeIcon = ((PathType) (jaxbPortComponent.getIcon()).getLargeIcon()).getValue();
        if (null != jaxbPortComponent.getPortComponentName())
            this.portComponentName = jaxbPortComponent.getPortComponentName().getValue();
        if (null != jaxbPortComponent.getServiceEndpointInterface())
            this.serviceEndpointInterface = (jaxbPortComponent.getServiceEndpointInterface()).getValue();
        this.wsdlPort = new WSCFWSDLPortImpl(jaxbPortComponent.getWsdlPort());
        this.serviceImplBean = new WSCFServiceImplBeanImpl(jaxbPortComponent.getServiceImplBean());
        java.util.List list = jaxbPortComponent.getHandler();
        for (int i = 0; i < list.size(); i++) {
            WSCFHandler handler = new WSCFHandlerImpl(((PortComponentHandlerType) list.get(i)));
            this.handlers.put(handler.getHandlerName(), handler);
        }
    }
}
