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

package org.apache.geronimo.ews.ws4j2ee.context;

import org.apache.geronimo.ews.ws4j2ee.context.j2eeDD.EJBContext;
import org.apache.geronimo.ews.ws4j2ee.context.j2eeDD.WebContext;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.client.interfaces.ServiceReferanceContext;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFContext;
import org.apache.geronimo.ews.ws4j2ee.context.wsdl.WSDLContext;
import org.apache.geronimo.ews.ws4j2ee.toWs.Ws4J2eeFactory;

/**
 * <p>This interface has all the information about the J2EE webservice that is
 * going to be genarated. from this interface onward all the codes are
 * ws4j2ee. If we using any class from the geronimo deployment we should
 * wrap them. This is a Code whith runing once. It is worth keeping the
 * code independent.<p>
 * <p/>
 * <p>This interface and related interfaces has both getter and setter methods
 * but who ever implements this interface might not need the both.
 * e.g. there can be two concreate implementations for this class
 * for the cases
 * <ol>
 * <li>have WSDL</li>
 * <li>do not have WSDL</li>
 * </ol>
 * if some method is not requried please throw java.lang.UnsupportedOperationException</p>
 * @author Srinath Perera(hemapani@opensource.lk)
 */

public interface J2EEWebServiceContext {
	/**
	 * Information about the WSDL file
	 * @return
	 */
    public WSDLContext getWSDLContext();
    public void setWSDLContext(WSDLContext wsdlcontext);

	/**
	 * Information about the webservices.xml file
	 * @return
	 */
    public WSCFContext getWSCFContext();
    public void setWSCFContext(WSCFContext wscfcontext);

	/**
	 * Information about the jaxrpcmapping.xml file  
	 * @return
	 */
    public JaxRpcMapperContext getJAXRPCMappingContext();
    public void setJAXRPCMappingContext(JaxRpcMapperContext context);

	/**
	 * Have the mislaneous infomation about the web service.
	 * @return
	 */
    public MiscInfo getMiscInfo();
    public void setMiscInfo(MiscInfo info);

	/**
	 * validate the context
	 */
    public void validate();
    
    /**
     * Information about which implementation should used for by the tool
     * Changing this one can change how the tool behave.
     * @param factory
     */
    public void setFactory(Ws4J2eeFactory factory);
	public Ws4J2eeFactory getFactory();
	
	public EJBContext getEJBDDContext();
	public void setEJBDDContext(EJBContext context);
	
	public WebContext getWebDDContext();
	public void setWebDDContext(WebContext context);

	public ServiceReferanceContext getServiceReferanceContext(int index);
	public void addServiceReferanceContext(ServiceReferanceContext context);
	public int getServiceReferanceContextCount();
}
