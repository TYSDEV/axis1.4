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

import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.AbstractWSCFWebservices;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.WSCFException;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFWebserviceDescription;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFWebservices;

import com.sun.java.xml.ns.j2Ee.IconType;
import com.sun.java.xml.ns.j2Ee.WebserviceDescriptionType;
import com.sun.java.xml.ns.j2Ee.WebservicesType;

/**
 * This is the class that will represent the webservices element in the runtime. 
 * This is the root element of the webservices.xml file. The parsing of elements will be initiated from here and will be 
 * parsed in a depth first manner
 */
public class WSCFWebservicesImpl extends AbstractWSCFWebservices implements WSCFWebservices{
	/**
	 * Constructor
	 * This is the constructor that was written to make the code jaxb complient. THis class
	 * basically wrap the jaxb webservides element.
	 */
	public WSCFWebservicesImpl(WebservicesType webservices)throws WSCFException{
			this.description =XMLBeansUtils.getStringValue(webservices.getDescriptionArray());		
			this.displayName =XMLBeansUtils.getStringValue(webservices.getDisplayNameArray());
	        
            IconType[] icons = webservices.getIconArray();
            if(icons.length > 0){
                this.smallIcon = XMLBeansUtils.getStringValue(icons[0].getSmallIcon());
                this.largeIcon = XMLBeansUtils.getStringValue(icons[0].getLargeIcon());
            
            }
            
            WebserviceDescriptionType[] wsdes = webservices.getWebserviceDescriptionArray();
        	if(0 == wsdes.length){
                throw new WSCFException("The webservices description element does not exist");}
		for(int i=0; i < wsdes.length; i++){
			WSCFWebserviceDescription webservicesDescription = new WSCFWebserviceDescriptionImpl(wsdes[i]);
			this.webServiceDescriptions.put(webservicesDescription.getDisplayName(), webservicesDescription);
		}
		
	}
}
