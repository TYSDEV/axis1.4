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
 * This interfaces the port component element of the webservices.xml. 
 *
 */
public interface WSCFPortComponent {

	/**
	 * Gets the description of the port component Element
	 * @return description
	 */	
	public String getDescription();
	
	/**
	 * Gets the display name of the port component Element
	 * @return display name
	 */	
	public String getDisplayName() ;
	
	/**
	 * Gets the large icon of the port component Element
	 * @return large icon
	 */
	public String getLargeIcon() ;
	
	/**
	 * Gets the port componenet name of the port component Element
	 * @return name
	 */
	public String getPortComponentName();
	
	/**
	 * Gets the SEI of the port component Element
	 * @return SEI
	 */
	public String getServiceEndpointInterface() ;
	
	/**
	 * Gets the service implimentation bean of the port component Element
	 * @return service inplinmentation bean
	 */
	public WSCFServiceImplBean getServiceImplBean() ;
	
	/**
	 * Gets the small icon of the port component Element
	 * @return small icon
	 */
	public String getSmallIcon() ;
	
	/**
	 * Gets the wsdl port of the port component Element
	 * @return wsd; port
	 */
	public WSCFWSDLPort getWsdlPort() ;
	
	/**
	 * Gets the handlers of the port component Element as an array
	 * @return handlers
	 */
	public WSCFHandler[] getHandlers();
}
