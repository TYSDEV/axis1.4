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

import java.util.List;

import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.AbstractWSCFWebservices;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.WSCFException;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFWebserviceDescription;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFWebservices;

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
	public WSCFWebservicesImpl(Webservices jaxbWebservices)throws WSCFException{
		if(null == jaxbWebservices) return;
		////////////assigning the values //////////////
		List temp = null; 
		
		temp = jaxbWebservices.getDescription();
		if(0 != temp.size())
			this.description =((DescriptionType)temp.get(0)).getValue();		
		
		temp = jaxbWebservices.getDisplayName();
		if(0 != temp.size())
			this.displayName =((DisplayNameType)temp.get(0)).getValue();
		
		temp = jaxbWebservices.getIcon();
		
		if(0 != temp.size()){
			if(null != (PathType)((IconType)temp.get(0)).getSmallIcon())
				this.smallIcon =((PathType)((IconType)temp.get(0)).getSmallIcon()).getValue();
			
			if(null != (PathType)((IconType)temp.get(0)).getLargeIcon())
				this.largeIcon =((PathType)((IconType)temp.get(0)).getLargeIcon()).getValue();
		}	
		
		java.util.List list = jaxbWebservices.getWebserviceDescription();
		if(0 == list.size()){throw new WSCFException("The webservices description element does not exist");}
		for(int i=0; i < list.size(); i++){
			WSCFWebserviceDescription webservicesDescription = new WSCFWebserviceDescriptionImpl(((WebserviceDescriptionType)list.get(i)));
			this.webServiceDescriptions.put(webservicesDescription.getDisplayName(), webservicesDescription);
		}
		
	}
}
