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

import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFServiceImplBean;

/**
 * This encapsulates the Service Implementation bean element whih is a level 3 elemet. This is the concrete implementation of teh
 * WSCFServiceImplBean
 */
public abstract class AbstractWSCFServiceImplBean extends WSCFElement implements WSCFServiceImplBean {

    /**
     * Service Implementation bean ejblink
     */
    protected String ejblink;

    protected String servletlink;
	
		
		
		
    //////////////////////////////jaxb interfacing block////////////////////////////////
	
//	protected ServiceImplBeanType jaxbServiceImplBean;
//	
//	
//	public WSCFServiceImplBeanImpl(ServiceImplBeanType jaxbServiceImplBean){
//		if(null == jaxbServiceImplBean){return;}
//		
//		this.jaxbServiceImplBean = jaxbServiceImplBean;
//		
////		///////////assigning the values //////////////
//		
//		if(null != this.jaxbServiceImplBean.getEjbLink())
//			this.ejblink = jaxbServiceImplBean.getEjbLink().getValue();
//		
//		if(null != jaxbServiceImplBean.getServletLink())
//			this.servletlink = jaxbServiceImplBean.getServletLink().getValue();	
//		
//	}
//	
//	
//	/////////////////////////////////////////////////////////////////////////////////////
//		
//	/**
//	 * The constructor. Parse the only child element it has: the ejblink element.
//	 * @param e Service implimentation bean Element 
//	 * @throws WSCFException
//	 */
//	public WSCFServiceImplBeanImpl(Element e) throws WSCFException{
//		super(e);
//		
//		//extracting the ejb-link
//		Element element = this.getChildElement(e, WSCFConstants.ELEM_WSCF_EJB_LINK);
//		if(null != element){this.ejblink = element.getChildNodes().item(0).toString();}
//	}

    /**
     * Gets the ejblink elemet of the service implementation bean element
     *
     * @return ejb link
     */
    public String getEjblink() {
        return ejblink;
    }

//	/**
//	 * @return
//	 */
//	public ServiceImplBeanType getJaxbServiceImplBean() {
//		return jaxbServiceImplBean;
//	}

    /**
     * @return
     */
    public String getServletlink() {
        return servletlink;
    }

}
