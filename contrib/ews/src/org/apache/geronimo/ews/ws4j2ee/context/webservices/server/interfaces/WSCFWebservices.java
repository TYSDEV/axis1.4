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
 * This will interface the WebServices element in the webService.xml file
 *
 */
public interface WSCFWebservices{
	
	/**
	 * Gets websevice description Elements from the webservices element as a n array
	 * @return webservice-descriptions
	 */
	public WSCFWebserviceDescription[] getWebServiceDescriptions() ;
	
	/**
	 * Gets the description element of the webservices Element
	 * @return Description
	 */
	public String getDescription() ;
	
	/**
	 * Gets the display name element of the webservices Element
	 * @return display-name
	 */
	public String getDisplayName();

	/**
	 * Gets the large icon element of the webservices Element
	 * @return large-icon
	 */
	public String getLargeIcon();
	
	/**
	 * Gets the small icon element of the webservices Element
	 * @return small-icon
	 */
	public String getSmallIcon();
	
//	public Webservices getJaxbWebservices(); 
	
//	public WSCFWebserviceDescription getWSDDService(QName qname);
//	public void setWebServiceDescriptions(Vector vector) ;
//	public void setDescription(String description) ;
//	public void setDisplayName(String displayName) ;
//	public void setLargeIcon(String largeIcon);
//	public void setSmallIcon(String smallIcon) ;
}
