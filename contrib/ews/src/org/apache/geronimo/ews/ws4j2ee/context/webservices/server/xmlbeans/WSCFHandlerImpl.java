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

import com.sun.java.xml.ns.j2Ee.IconType;
import com.sun.java.xml.ns.j2Ee.ParamValueType;
import com.sun.java.xml.ns.j2Ee.PortComponentHandlerType;
import com.sun.java.xml.ns.j2Ee.XsdQNameType;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.AbstractWSCFHandler;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFHandler;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFInitParam;

/**
 * This encapsulates the layer 3 handler element of the webservices.xml. This
 * is also the concrete implementation of the WSCFHandler.
 */
public class WSCFHandlerImpl extends AbstractWSCFHandler implements WSCFHandler {
    public WSCFHandlerImpl(PortComponentHandlerType handler) {
        this.description = XMLBeansUtils.getStringValue(handler.getDescriptionArray());
        this.displayName = XMLBeansUtils.getStringValue(handler.getDisplayNameArray());
        IconType[] icons = handler.getIconArray();
        if (icons.length > 0) {
            this.smallIcon = XMLBeansUtils.getStringValue(icons[0].getSmallIcon());
            this.largeIcon = XMLBeansUtils.getStringValue(icons[0].getLargeIcon());
        }
        this.handlerName = XMLBeansUtils.getStringValue(handler.getHandlerName());
        this.handlerClass = XMLBeansUtils.getStringValue(handler.getHandlerName());
        ParamValueType[] list = handler.getInitParamArray();
        for (int i = 0; i < list.length; i++) {
            WSCFInitParam initParameters = new WSCFInitParamImpl(list[i]);
            this.initParam.put(initParameters.getParamName(), initParameters);
        }
        XsdQNameType[] list2 = handler.getSoapHeaderArray();
        for (int i = 0; i < list2.length; i++) {
            this.soapHeader.add(new WSCFSOAPHeaderImpl(list2[i]));
        }
        com.sun.java.xml.ns.j2Ee.String[] list3 = handler.getSoapRoleArray();
        for (int i = 0; i < list3.length; i++) {
            this.soapRole.add(XMLBeansUtils.getStringValue(list3[i]));
        }
    }
}
