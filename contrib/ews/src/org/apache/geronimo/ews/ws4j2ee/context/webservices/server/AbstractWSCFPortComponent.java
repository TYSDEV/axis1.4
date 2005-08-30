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

import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFHandler;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFPortComponent;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFServiceImplBean;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFWSDLPort;

import java.util.HashMap;

/**
 * This encapsulates the level 2 Elemenr PortComponent which is a child element of the webservice-description element.
 * It is also the concrete implmentation of the WSCFPortComponent.
 */
public abstract class AbstractWSCFPortComponent extends WSCFElement implements WSCFPortComponent {

    /**
     * port-component description
     */
    protected String description;

    /**
     * port-component display name
     */
    protected String displayName;

    /**
     * port-component small icon
     */
    protected String smallIcon;

    /**
     * port-component large icon
     */
    protected String largeIcon;

    /**
     * port-component name
     */
    protected String portComponentName;

    /**
     * port-component wsdl port
     */
    protected WSCFWSDLPort wsdlPort;

    /**
     * port-component service endpoint interface
     */
    protected String serviceEndpointInterface;

    /**
     * port-component service implementation bean
     */
    protected WSCFServiceImplBean serviceImplBean;

    /**
     * port-component handlers
     */
    protected HashMap handlers = new HashMap();
	
	
    //////////////////////////////jaxb interfacing block///////////////////////////////////
	
//	public WSCFPortComponentImpl(PortComponentType jaxbPortComponent){
//		this.jaxbPortComponent = jaxbPortComponent;		
//		
////		/////////////assigning the values //////////////
//		if(null != jaxbPortComponent.getDescription())
//			this.description =jaxbPortComponent.getDescription().getValue();
//		
//		if(null != jaxbPortComponent.getDisplayName())			
//			this.displayName =jaxbPortComponent.getDisplayName().getValue();
//		
//		if(null != jaxbPortComponent.getIcon())	
//			this.smallIcon =((PathType)(jaxbPortComponent.getIcon()).getSmallIcon()).getValue();
//		
//		if(null != jaxbPortComponent.getIcon())
//			this.largeIcon =((PathType)(jaxbPortComponent.getIcon()).getLargeIcon()).getValue();
//		
//		if(null != jaxbPortComponent.getPortComponentName())
//			this.portComponentName = jaxbPortComponent.getPortComponentName().getValue();
//		
//		if(null != jaxbPortComponent.getServiceEndpointInterface())
//			this.serviceEndpointInterface = (jaxbPortComponent.getServiceEndpointInterface()).getValue();
//			
//				
//		this.wsdlPort = new WSCFWSDLPortImpl(jaxbPortComponent.getWsdlPort());
//		this.serviceImplBean = new WSCFServiceImplBeanImpl(jaxbPortComponent.getServiceImplBean());
//		java.util.List list = this.jaxbPortComponent.getHandler();
//		for(int i=0; i < list.size(); i++){
//			WSCFHandler handler = new AbstractWSCFHandlerImpl(((PortComponentHandlerType)list.get(i)));
//			this.handlers.put(handler.getHandlerName(), handler);
//		}
//	}
	
	
    ///////////////////////////////////////////////////////////////////////////////////////
	
	
//	/**
//	 * The constructor. This will recursively parse the child elements in depth first manner.
//	 * @param e PortComponent Element
//	 * @throws WSCFException
//	 */
//	public WSCFPortComponentImpl(Element e)throws WSCFException{
//		super(e);
//		
//		//extract the description.
//		Element element = this.getChildElement(e, WSCFConstants.ELEM_WSCF_DESCRIPTION);
//		if(null != element){this.description = element.getChildNodes().item(0).toString();}
//		
//		//extract the display name
//		element = this.getChildElement(e, WSCFConstants.ELEM_WSCF_DISPLAY_NAME);
//		if(null != element){this.displayName = element.getChildNodes().item(0).toString();}
//		
//		//extract the small icon
//		element = this.getChildElement(e, WSCFConstants.ELEM_WSCF_SMALL_ICON);
//		if(null != element){this.smallIcon = element.getChildNodes().item(0).toString();}
//		
//		//extract the large icon
//		element = this.getChildElement(e, WSCFConstants.ELEM_WSCF_LARGE_ICON);
//		if(null != element){this.largeIcon = element.getChildNodes().item(0).toString();}
//		
//		//extract the port component name
//		element = this.getChildElement(e, WSCFConstants.ELEM_WSCF_PORT_COMPONENT_NAME);
//		if(null != element){this.portComponentName = element.getChildNodes().item(0).toString();}
//		
//		//extract the  wsdl port
//		element = this.getChildElement(e, WSCFConstants.ELEM_WSCF_WSDL_PORT);
//		if(null != element){this.wsdlPort = new WSCFWSDLPortImpl(element);}
//		
//		//extracting the SEI
//		element = this.getChildElement(e, WSCFConstants.ELEM_WSCF_SERVICE_ENDPOINT_INTERFACE);
//		if(null != element){this.serviceEndpointInterface = element.getChildNodes().item(0).toString();}
//		
//		//extracting the service implementation bean
//		element = this.getChildElement(e, WSCFConstants.ELEM_WSCF_SERVICE_IMPLEMENTATION_BEAN);
//		if(null != element){this.serviceImplBean = new WSCFServiceImplBeanImpl(element);}
//		
//		Element[] elements = this.getChildElements(e, WSCFConstants.ELEM_WSCF_HANDLER);
//		for(int i=0; i < elements.length; i++){
//			WSCFHandler handler = new AbstractWSCFHandlerImpl(elements[i]);
//			this.handlers.put(handler.getHandlerName(), handler);					
//		}
//	
//	}	
	
	


    /**
     * Gets the description of the port component Element
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the display name of the port component Element
     *
     * @return display name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Gets the handlers of the port component Element as an array
     *
     * @return handlers
     */
    public WSCFHandler[] getHandlers() {
        WSCFHandler[] handler = new WSCFHandler[this.handlers.size()];
        this.handlers.values().toArray(handler);
        return handler;
    }

    /**
     * Gets the large icon of the port component Element
     *
     * @return large icon
     */
    public String getLargeIcon() {
        return largeIcon;
    }

    /**
     * Gets the port componenet name of the port component Element
     *
     * @return name
     */
    public String getPortComponentName() {
        return portComponentName;
    }

    /**
     * Gets the SEI of the port component Element
     *
     * @return SEI
     */
    public String getServiceEndpointInterface() {
        return serviceEndpointInterface;
    }

    /**
     * Gets the service implimentation bean of the port component Element
     *
     * @return service inplinmentation bean
     */
    public WSCFServiceImplBean getServiceImplBean() {
        return serviceImplBean;
    }

    /**
     * Gets the small icon of the port component Element
     *
     * @return small icon
     */
    public String getSmallIcon() {
        return smallIcon;
    }

    /**
     * Gets the wsdl port of the port component Element
     *
     * @return wsd; port
     */
    public WSCFWSDLPort getWsdlPort() {
        return wsdlPort;
    }

//	/**
//	 * @return
//	 */
//	public PortComponentType getJaxbPortComponent() {
//		return jaxbPortComponent;
//	}

}
