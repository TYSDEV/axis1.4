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

import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFPortComponent;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFWebserviceDescription;

import java.util.HashMap;

/**
 * This represents a level 1 element in the Element tree :webservice-description. This is the concrete implementation of the
 * WSCFWebServiceDescription interface
 */
public class AbstractWSCFWebserviceDescription extends WSCFElement implements WSCFWebserviceDescription {

    /**
     * This will refer to the multiple port components that can be there in the webservice-description Element.
     */
    protected HashMap portComponent = new HashMap();

    /**
     * Webservice-description - description
     */
    protected String description;

    /**
     * Webservice-description - display name
     */
    protected String displayName;

    /**
     * Webservice-description - small icon
     */
    protected String smallIcon;

    /**
     * Webservice-description - large icon
     */
    protected String largeIcon;

    /**
     * Webservice-description - name
     */
    protected String webserviceDescriptionName;

    /**
     * Webservice-description - wsdl file
     */
    protected String wsdlFile;

    /**
     * Webservice-description - jaxrpc mapping file
     */
    protected String jaxrpcMappingFile;
	
	
//	////////////////////////////////////jaxb interfacing block /////////////////////////////////////////////
//	
//	protected WebserviceDescriptionType jaxbWebserviesDescription;
//	
//	
//	public WSCFWebserviceDescriptionImpl(WebserviceDescriptionType jaxbWebserviesDescription) throws WSCFException{
//		this.jaxbWebserviesDescription = jaxbWebserviesDescription;
//		
//		///////////////assigning the values //////////////
//		if(null != jaxbWebserviesDescription.getDescription())
//  			this.description =jaxbWebserviesDescription.getDescription().getValue();
//		
//		if(null != jaxbWebserviesDescription.getDisplayName())			
//  			this.displayName =jaxbWebserviesDescription.getDisplayName().getValue();
//  		
//  		if(null != jaxbWebserviesDescription.getIcon()){
//  			if(null != (PathType)(jaxbWebserviesDescription.getIcon()).getSmallIcon())
//  				this.smallIcon =((PathType)(jaxbWebserviesDescription.getIcon()).getSmallIcon()).getValue();
//  				
//  			if(null != (PathType)(jaxbWebserviesDescription.getIcon()).getLargeIcon())
//  				this.largeIcon =((PathType)(jaxbWebserviesDescription.getIcon()).getLargeIcon()).getValue();
//  		}
//  		
//  		if(null != jaxbWebserviesDescription.getWebserviceDescriptionName())
//  			this.webserviceDescriptionName = jaxbWebserviesDescription.getWebserviceDescriptionName().getValue();
//  		
//  		if(null != jaxbWebserviesDescription.getWsdlFile())
//  			this.wsdlFile = ((PathType)jaxbWebserviesDescription.getWsdlFile()).getValue();
//  		
//  		if(null != jaxbWebserviesDescription.getJaxrpcMappingFile())
//  			this.jaxrpcMappingFile = ((PathType)jaxbWebserviesDescription.getJaxrpcMappingFile()).getValue();
//  		
//  		java.util.List list = this.jaxbWebserviesDescription.getPortComponent();
//  		if (0 == list.size()){throw new WSCFException("At least one port-component element should exist in the "+this.description+" webservices element.");}
//  		for(int i=0; i < list.size(); i++){
//	  		WSCFPortComponent portComponent = new AbstractWSCFPortComponentImpl(((PortComponentType)list.get(i)));
//	  		this.portComponent.put(portComponent.getPortComponentName(), portComponent);
//		}
//	}
//	
//	
//	/////////////////////////////////////////////////////////////////////////////////////////////////
//	
//	
//	/**
//	 * The constructor. Here the child elements will be created recursively in a depth first manner.
//	 * This is the concrete implementation of the WSCFWebserviceDescrption.
//	 * @param e Webservice-description Element
//	 * @throws WSCFException
//	 */
//	public WSCFWebserviceDescriptionImpl(Element e)throws WSCFException{
//		super(e);
//		//extract the description
//		Element element = this.getChildElement(e,WSCFConstants.ELEM_WSCF_DESCRIPTION);
//		if(null != element){this.description = element.getChildNodes().item(0).toString();}
//		
//		//extract the display name
//		element = this.getChildElement(e, WSCFConstants.ELEM_WSCF_DISPLAY_NAME);
//		if(null != element){this.displayName = element.getChildNodes().item(0).toString();}
//		
//		//extract the small icon.
//		element = this.getChildElement(e, WSCFConstants.ELEM_WSCF_SMALL_ICON);
//		if(null != element){this.smallIcon = element.getChildNodes().item(0).toString();}
//		
//		//extract the large icon
//		element = this.getChildElement(e, WSCFConstants.ELEM_WSCF_LARGE_ICON);
//		if (null != element){this.largeIcon = element.getChildNodes().item(0).toString();}
//		
//		//extract the webservice description name.
//		element = this.getChildElement(e, WSCFConstants.ELEM_WSCF_WEBSERVICES_DESCRIPTION_NAME);
//		if(null != element){this.webserviceDescriptionName = element.getChildNodes().item(0).toString();}
//		
//		//extract the wsdl file
//		element = this.getChildElement(e, WSCFConstants.ELEM_WSCF_WSDLFILE);
//		if(null != element){this.wsdlFile = element.getChildNodes().item(0).toString();}
//		
//		//extract the jax rpc mapping file
//		element = this.getChildElement(e, WSCFConstants.ELEM_WSCF_JAXRPC_MAPPING_FILE);
//		if(null != element){this.jaxrpcMappingFile = element.getChildNodes().item(0).toString();}
//		
//		//extract the port component
//		Element[] elements = this.getChildElements(e, WSCFConstants.ELEM_WSCF_PORT_COMPONENT);
//		for(int i=0; i < elements.length; i++){
//			WSCFPortComponent portComponent = new AbstractWSCFPortComponentImpl(elements[i]);
//			this.portComponent.put(portComponent.getPortComponentName(), portComponent);
//		}
//		
//	}
		
		
    /**
     * Gets the description of the webservices-description Element
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the display name of the webservices-description Element
     *
     * @return display-name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Gets the JAXRPC mapping file of the webservices-description Element
     *
     * @return JAXRPC-mapping-file
     */
    public String getJaxrpcMappingFile() {
        return jaxrpcMappingFile;
    }

    /**
     * Gets the large icon of the webservices-description Element
     *
     * @return large-icon
     */
    public String getLargeIcon() {
        return largeIcon;
    }

    /**
     * Gets the port compoments of the webservices-description Element as an array
     *
     * @return port components
     */
    public WSCFPortComponent[] getPortComponent() {
        WSCFPortComponent[] portComponents = new WSCFPortComponent[this.portComponent.size()];
        this.portComponent.values().toArray(portComponents);
        return portComponents;
    }

    /**
     * Gets the small icon of the webservices-description Element
     *
     * @return small-icon
     */
    public String getSmallIcon() {
        return smallIcon;
    }

    /**
     * Gets the name of the webservices-description Element
     *
     * @return webservice-description-name
     */
    public String getWebserviceDescriptionName() {
        return webserviceDescriptionName;
    }

    /**
     * Gets the wsdl file of the webservices-description Element
     *
     * @return wsdl-file
     */
    public String getWsdlFile() {
        return wsdlFile;
    }

//	/**
//	 * @return
//	 */
//	public WebserviceDescriptionType getJaxbWebserviesDescription() {
//		return jaxbWebserviesDescription;
//	}

}
