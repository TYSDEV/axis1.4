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

import org.apache.geronimo.ews.ws4j2ee.toWs.GenerationFault;

/**
 * This will inteface the wholw webservices.xml file and will give sufficient access to
 * all the elements and attrebutes either directly or through other interfaces.
 *
 */
public interface WSCFContext {
	/**
	 * Gets the Description element of the webservices.xml
	 * @return description
	 */
	public String getDescription();
	
	/**
	 * Gets the display name element of the webservices.xml
	 * @return display name
	 */
	public String getDisplayName();
	
	/**
	 * Gets the small icon of the webservices,xml
	 * @return small icon
	 */
	public String getSmallIcon();
	
	/**
	 * Gets the large icon of the webservices.xml
	 * @return large icon
	 */
	public String getLargeIcon();
	
	/**
	 * Gets the webservice description elements as an array
	 * @return web service description(s)
	 */
	public WSCFWebserviceDescription[] getWebServicesDescription();
	
	public void serialize(java.io.Writer out) throws GenerationFault;
	
	/**
	 * WSCF artifacts correponds to the current WSCF file.
	 * If one element is in the wsdl theu are used. How to select them 
	 * if there is more than one is still to do.   
	 * @return
	 */
	public WSCFWebserviceDescription getWscfdWsDesxription();
	public void setWscfdWsDescription(WSCFWebserviceDescription description);

	public WSCFPortComponent getWscfport();
	public void setWscfport(WSCFPortComponent component);


	

}
