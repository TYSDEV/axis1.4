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
 * This will interface the webservice-description element which is a level1 element of teh webservices.xml
 *
 */
public interface WSCFWebserviceDescription {
	
	/**
	 * Gets the port compoments of the webservices-description Element as an array
	 * @return port components
	 */
	public WSCFPortComponent[] getPortComponent();
	
	/**
	 * Gets the description of the webservices-description Element
	 * @return description
	 */
	public String getDescription();
	
	/**
	 * Gets the name of the webservices-description Element
	 * @return webservice-description-name
	 */
	public String getWebserviceDescriptionName();
	
	/**
	 * Gets the display name of the webservices-description Element
	 * @return display-name
	 */
	public String getDisplayName() ;
	
	/**
	 * Gets the JAXRPC mapping file of the webservices-description Element
	 * @return JAXRPC-mapping-file
	 */
	public String getJaxrpcMappingFile();
	
	/**
	 * Gets the large icon of the webservices-description Element
	 * @return large-icon
	 */
	public String getLargeIcon();
	
	/**
	 * Gets the small icon of the webservices-description Element
	 * @return small-icon
	 */
	public String getSmallIcon()  ;
	
	/**
	 * Gets the wsdl file of the webservices-description Element
	 * @return wsdl-file
	 */
	public String getWsdlFile() ;
}
