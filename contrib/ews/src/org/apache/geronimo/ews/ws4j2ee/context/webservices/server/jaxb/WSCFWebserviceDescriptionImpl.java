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

import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.AbstractWSCFWebserviceDescription;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.WSCFException;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFPortComponent;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFWebserviceDescription;

/**
 * This represents a level 1 element in the Element tree :webservice-description. This is the concrete implementation of the
 * WSCFWebServiceDescription interface
 */
public class WSCFWebserviceDescriptionImpl extends AbstractWSCFWebserviceDescription implements WSCFWebserviceDescription {
    public WSCFWebserviceDescriptionImpl(WebserviceDescriptionType jaxbWebserviesDescription) throws WSCFException {
        ///////////////assigning the values //////////////
        if (null != jaxbWebserviesDescription.getDescription())
            this.description = jaxbWebserviesDescription.getDescription().getValue();
        if (null != jaxbWebserviesDescription.getDisplayName())
            this.displayName = jaxbWebserviesDescription.getDisplayName().getValue();
        if (null != jaxbWebserviesDescription.getIcon()) {
            if (null != (PathType) (jaxbWebserviesDescription.getIcon()).getSmallIcon())
                this.smallIcon = ((PathType) (jaxbWebserviesDescription.getIcon()).getSmallIcon()).getValue();
            if (null != (PathType) (jaxbWebserviesDescription.getIcon()).getLargeIcon())
                this.largeIcon = ((PathType) (jaxbWebserviesDescription.getIcon()).getLargeIcon()).getValue();
        }
        if (null != jaxbWebserviesDescription.getWebserviceDescriptionName())
            this.webserviceDescriptionName = jaxbWebserviesDescription.getWebserviceDescriptionName().getValue();
        if (null != jaxbWebserviesDescription.getWsdlFile())
            this.wsdlFile = ((PathType) jaxbWebserviesDescription.getWsdlFile()).getValue();
        if (null != jaxbWebserviesDescription.getJaxrpcMappingFile())
            this.jaxrpcMappingFile = ((PathType) jaxbWebserviesDescription.getJaxrpcMappingFile()).getValue();
        java.util.List list = jaxbWebserviesDescription.getPortComponent();
        if (0 == list.size()) {
            throw new WSCFException("At least one port-component element should exist in the " + this.description + " webservices element.");
        }
        for (int i = 0; i < list.size(); i++) {
            WSCFPortComponent portComponent = new WSCFPortComponentImpl(((PortComponentType) list.get(i)));
            this.portComponent.put(portComponent.getPortComponentName(), portComponent);
        }
    }
}
