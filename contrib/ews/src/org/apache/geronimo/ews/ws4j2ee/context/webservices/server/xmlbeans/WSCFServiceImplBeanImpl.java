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

import com.sun.java.xml.ns.j2Ee.ServiceImplBeanType;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.AbstractWSCFServiceImplBean;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFServiceImplBean;

/**
 * This encapsulates the Service Implementation bean element whih is a level 3 elemet. This is the concrete implementation of teh
 * WSCFServiceImplBean
 */
public class WSCFServiceImplBeanImpl extends AbstractWSCFServiceImplBean implements WSCFServiceImplBean {
    public WSCFServiceImplBeanImpl(ServiceImplBeanType implbean) {
        if (null == implbean) {
            return;
        }
        this.ejblink = XMLBeansUtils.getStringValue(implbean.getEjbLink());
        this.servletlink = XMLBeansUtils.getStringValue(implbean.getServletLink());
    }

    /**
     * Gets the ejblink elemet of the service implementation bean element
     *
     * @return ejb link
     */
    public String getEjblink() {
        return ejblink;
    }

    /**
     * @return
     */
    public String getServletlink() {
        return servletlink;
    }

}
