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
package org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces;

/**
 * @author Chathura Herath
 */
public interface WSCFHandler {

    /**
     * Gets the description of the handler element
     *
     * @return description
     */
    public String getDescription();

    /**
     * Gets the display name of the handler element
     *
     * @return display-name
     */
    public String getDisplayName();

    /**
     * Gets the class of the handler element
     *
     * @return handler-class
     */
    public String getHandlerClass();

    /**
     * Gets the name of the handler element
     *
     * @return handler-name
     */
    public String getHandlerName();

    /**
     * Gets the large icon of the handler element
     *
     * @return large-icon
     */
    public String getLargeIcon();

    /**
     * Gets the small icon of the handler element
     *
     * @return small-icon
     */
    public String getSmallIcon();

    /**
     * Gets the soap headers of the handler element
     *
     * @return soap-headers
     */
    public WSCFSOAPHeader[] getSoapHeader();

    /**
     * Gets the soap roles of the handler element
     *
     * @return soap-roles
     */
    public String[] getSoapRole();

    /**
     * Gets the init paramaeters of the handler element as a array
     *
     * @return init-parameters
     */
    public WSCFInitParam[] getInitParam();
}
