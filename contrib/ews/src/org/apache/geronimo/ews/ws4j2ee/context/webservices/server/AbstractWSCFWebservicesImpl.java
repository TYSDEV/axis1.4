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

import java.util.HashMap;

import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFWebserviceDescription;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFWebservices;

/**
 * This is the class that will represent the webservices element in the runtime. 
 * This is the root element of the webservices.xml file. The parsing of elements will be initiated from here and will be 
 * parsed in a depth first manner
 */
public class AbstractWSCFWebservicesImpl extends WSCFElement implements WSCFWebservices{

	//TODO figure out how the id should be encapsulated
	
	/**
	 * This will refer to the multiple webservice descriptions elements that the
	 * webservice element can support
	 */
	protected HashMap webServiceDescriptions = new HashMap();
	
	/**
	 * Webservice description.
	 */
	protected String description;
	
	/**
	 * Webservice display name
	 */
	protected String displayName;
	
	/**
	 * Webservice small Icon.
	 */
	protected String smallIcon;
	
	/**
	 * Webservice large icon.
	 */
	protected String largeIcon;

////////////////////////////////////////////jaxb interfacing block/////////////////////////////////////////
//
////This is kept basically to meet the future demands of the serialising.
//	protected Webservices jaxbWebservices;
//
//		/**
//		 * Constructor
//		 * This is the constructor that was written to make the code jaxb complient. THis class
//		 * basically wrap the jaxb webservides element.
//		 */
//	public WSCFWebservicesImpl(Webservices jaxbWebservices)throws WSCFException{
//		if(null == jaxbWebservices) return;
//		this.jaxbWebservices = jaxbWebservices;
//
//		////////////assigning the values //////////////
//		List temp = null; 
//		
//		temp = jaxbWebservices.getDescription();
//		if(0 != temp.size())
//			this.description =((DescriptionType)temp.get(0)).getValue();		
//		
//		temp = jaxbWebservices.getDisplayName();
//		if(0 != temp.size())
//			this.displayName =((DisplayNameType)temp.get(0)).getValue();
//		
//		temp = jaxbWebservices.getIcon();
//		
//		if(0 != temp.size()){
//			if(null != (PathType)((IconType)temp.get(0)).getSmallIcon())
//				this.smallIcon =((PathType)((IconType)temp.get(0)).getSmallIcon()).getValue();
//			
//			if(null != (PathType)((IconType)temp.get(0)).getLargeIcon())
//				this.largeIcon =((PathType)((IconType)temp.get(0)).getLargeIcon()).getValue();
//		}	
//		
//		java.util.List list = this.jaxbWebservices.getWebserviceDescription();
//		if(0 == list.size()){throw new WSCFException("The webservices description element does not exist");}
//		for(int i=0; i < list.size(); i++){
//			WSCFWebserviceDescription webservicesDescription = new AbstractWSCFWebserviceDescriptionImpl(((WebserviceDescriptionType)list.get(i)));
//			this.webServiceDescriptions.put(webservicesDescription.getDisplayName(), webservicesDescription);
//		}
//		
//	}
//
//
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//
//
//
//
//	/**
//	 * Constructor
//	 * @param e  Webservices element
//	 * @throws WSCFException
//	 * 
//	 * !!!!!!!!!!!!!!!!!!!!!!!!Deprecated!!!!!!!!!!!!!!!!!!!!!!!!!!
//	 */
//	public WSCFWebservicesImpl(Element e) throws WSCFException{
//		super(e);
//		//extracting the description
//		Element element = this.getChildElement(e,WSCFConstants.ELEM_WSCF_DESCRIPTION);
//		if(null != element){this.description = (element.getChildNodes()).item(0).toString();}
//		
//		//extraction the dispaly-name
//		element = this.getChildElement(e,WSCFConstants.ELEM_WSCF_DISPLAY_NAME);
//		if(null != element){this.displayName = element.getChildNodes().item(0).toString();}
//		
//		//extraction the small icon
//		 element = this.getChildElement(e,WSCFConstants.ELEM_WSCF_SMALL_ICON);
//		if(null != element){this.smallIcon = element.getChildNodes().item(0).toString();}
//				
//		//extracting the large icon
//		element = this.getChildElement(e, WSCFConstants.ELEM_WSCF_LARGE_ICON);
//		if(null != element){this.largeIcon = element.getChildNodes().item(0).toString();}
//		//TODO ********whether this is the most appropreate way to do this
//		// getting the webservice description.
//		Element[] elements = this.getChildElements(e, WSCFConstants.ELEM_WSCF_WEBSERVICES_DESCRIPTION);
//		for (int i=0; i< elements.length; i++){
//			WSCFWebserviceDescription webservice = new AbstractWSCFWebserviceDescriptionImpl(elements[i]);
//			this.webServiceDescriptions.put(webservice.getWebserviceDescriptionName(), webservice);
//		}
//		
//	}


	/**
	 * Gets all the webservice descriptions as a array of such elements
	 * @return Webservice description array.
	 */
	public WSCFWebserviceDescription[] getWebServiceDescriptions() {
		WSCFWebserviceDescription[] wsdescArray = new WSCFWebserviceDescription[this.webServiceDescriptions.size()];
		this.webServiceDescriptions.values().toArray(wsdescArray);
		return wsdescArray;
	}	


//TODO *throw an exception appropreately if the value is null

	/**
	 * Gets the description of the webservices Element
	 * @return The description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Gets the displayname of the webservices element
	 * @return The Display name
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * Gets the description of the large icon Element
	 * @return The large icon
	 */
	public String getLargeIcon() {
		return largeIcon;
	}

	/**
	 * Gets the description of the small icon Element
	 * @return The small icon
	 */
	public String getSmallIcon() {
		return smallIcon;
	}

//	/**
//	 * @param description - Description of the webservice
//	 */
//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//	/**
//	 * @param displayName
//	 */
//	public void setDisplayName(String displayName) {
//		this.displayName = displayName;
//	}
//
//	/**
//	 * @param largeIcon
//	 */
//	public void setLargeIcon(String largeIcon) {
//		this.largeIcon = largeIcon;
//	}
//
//	/**
//	 * @param smallIcon
//	 */
//	public void setSmallIcon(String smallIcon) {
//		this.smallIcon = smallIcon;
//	}

///**
// * @return
// */
//public Webservices getJaxbWebservices() {
//	return jaxbWebservices;
//}
//
}
