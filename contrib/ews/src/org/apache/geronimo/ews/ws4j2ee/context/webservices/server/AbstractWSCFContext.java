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

import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFContext;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFDocument;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFPortComponent;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFWebserviceDescription;
import org.apache.geronimo.ews.ws4j2ee.toWs.GenerationFault;

/**
 * @author hemapani@opensource.lk
 */
public abstract class AbstractWSCFContext implements WSCFContext {
    protected WSCFWebserviceDescription wscfdWsDesxription;
    protected WSCFPortComponent wscfport;
    /**
     * This reference will be the pointer to the element tree.
     */
    protected WSCFDocument document;

    /**
     * @return
     */
    public WSCFWebserviceDescription getWscfdWsDesxription() {
        return wscfdWsDesxription;
    }

    /**
     * @return
     */
    public WSCFPortComponent getWscfport() {
        return wscfport;
    }

    /**
     * @param description
     */
    public void setWscfdWsDescription(WSCFWebserviceDescription description) {
        wscfdWsDesxription = description;
    }

    /**
     * @param component
     */
    public void setWscfport(WSCFPortComponent component) {
        wscfport = component;
    }

    /**
     * Interface support method. This will get the description element of the webservices.xml
     */
    public String getDescription() {
        return this.document.getWebservices().getDescription();
    }

    /**
     * Interface support method. This will get the display name element of the webservices.xml
     */
    public String getDisplayName() {
        return this.document.getWebservices().getDisplayName();
    }

    /**
     * Interface support method. This will get the small icon element of the webservices.xml
     */
    public String getSmallIcon() {
        return this.document.getWebservices().getSmallIcon();
    }

    /**
     * Interface support method. This will get the large icon element of the webservices.xml
     */
    public String getLargeIcon() {
        return this.document.getWebservices().getLargeIcon();
    }

    /**
     * Interface support method. This will get the webservice description elements of the webservices.xml as an array.
     */
    public WSCFWebserviceDescription[] getWebServicesDescription() {
        return this.document.getWebservices().getWebServiceDescriptions();
    }

    public void serialize(java.io.Writer out) throws GenerationFault {
        throw new UnsupportedOperationException();
    }

}
