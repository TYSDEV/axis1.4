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

package org.apache.geronimo.ews.ws4j2ee.context.impl;

import org.apache.geronimo.ews.ws4j2ee.context.J2EEWebServiceContext;
import org.apache.geronimo.ews.ws4j2ee.context.JaxRpcMapperContext;
import org.apache.geronimo.ews.ws4j2ee.context.MiscInfo;
import org.apache.geronimo.ews.ws4j2ee.context.j2eeDD.EJBContext;
import org.apache.geronimo.ews.ws4j2ee.context.j2eeDD.WebContext;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.client.interfaces.ServiceReferanceContext;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFContext;
import org.apache.geronimo.ews.ws4j2ee.context.wsdl.WSDLContext;
import org.apache.geronimo.ews.ws4j2ee.toWs.UnrecoverableGenerationFault;
import org.apache.geronimo.ews.ws4j2ee.toWs.Ws4J2eeFactory;

import java.util.Vector;

/**
 * <p>Code should use parsers and create runtime representation
 * of the information taken fom the WSDL and configaration files.</p>
 * <p>depend on hasWSDL or the not the implementation should
 * <ol>
 * <li>parse the WSDL and populate informatio in <code>WSDLContext</code></li>
 * <li>parse the SEI or EJB and populate the information in <code>WSDLContext</code>
 * with the help of the jaxrpc mapping file information.
 * </li>
 * </ol>
 * </p>
 *
 * @author Srinath Perera(hemapani@opensorce.lk)
 * @see org.apache.geronimo.ews.ws4j2ee.context.J2EEWebServiceContext
 */
public class J2EEWebServiceContextImpl implements J2EEWebServiceContext {
    private boolean hasWSDL = true;
    private WSCFContext wscfcontext;
    private WSDLContext wsdlcontext;
    private JaxRpcMapperContext jaxrpcmappingcontext;
    private MiscInfo miscInfo;
    private Ws4J2eeFactory factory;
    private Vector srcontext;
    private EJBContext ejbcontext;
    private WebContext webcontext;

    public J2EEWebServiceContextImpl(boolean hasWSDL) {
        this.hasWSDL = hasWSDL;
        srcontext = new Vector();
    }

    /* (non-Javadoc)
     * @see org.apache.geronimo.ews.ws4j2ee.context.J2EEWebServiceContext#getWSDLContext()
     */
    public WSDLContext getWSDLContext() {
        return wsdlcontext;
    }

    /* (non-Javadoc)
     * @see org.apache.geronimo.ews.ws4j2ee.context.J2EEWebServiceContext#setWSDLContext(org.apache.geronimo.ews.ws4j2ee.context.wsdl.WSDLContext)
     */
    public void setWSDLContext(WSDLContext wsdlcontext) {
        this.wsdlcontext = wsdlcontext;
    }

    /* (non-Javadoc)
     * @see org.apache.geronimo.ews.ws4j2ee.context.J2EEWebServiceContext#getJAXRPCMappingContext()
     */
    public JaxRpcMapperContext getJAXRPCMappingContext() {
        return jaxrpcmappingcontext;
    }

    /* (non-Javadoc)
     * @see org.apache.geronimo.ews.ws4j2ee.context.J2EEWebServiceContext#setJAXRPCMappingContext(org.apache.geronimo.ews.ws4j2ee.context.JaxRpcMapperContext)
     */
    public void setJAXRPCMappingContext(JaxRpcMapperContext context) {
        this.jaxrpcmappingcontext = context;
    }

    public WSCFContext getWSCFContext() {
        return wscfcontext;
    }

    /* (non-Javadoc)
     * @see org.apache.geronimo.ews.ws4j2ee.context.J2EEWebServiceContext#setWSCFContext(org.apache.geronimo.ews.ws4j2ee.context.webservices.interfaces.WSCFContext)
     */
    public void setWSCFContext(WSCFContext wscfcontext) {
        this.wscfcontext = wscfcontext;
    }

    public MiscInfo getMiscInfo() {
        return miscInfo;
    }

    /**
     * @param info
     */
    public void setMiscInfo(MiscInfo info) {
        this.miscInfo = info;
    }

    public void validate() {
        if (wscfcontext == null || miscInfo == null ||
                (hasWSDL && wsdlcontext == null) || jaxrpcmappingcontext == null)
            throw new UnrecoverableGenerationFault("valdation of the j2ee context failed");
        miscInfo.validate();
    }

    public void setFactory(Ws4J2eeFactory factory) {
        this.factory = factory;
    }

    public Ws4J2eeFactory getFactory() {
        return this.factory;
    }

    public void addServiceReferanceContext(ServiceReferanceContext context) {
        srcontext.add(context);
    }

    public EJBContext getEJBDDContext() {
        return ejbcontext;
    }

    public ServiceReferanceContext getServiceReferanceContext(int index) {
        return (ServiceReferanceContext) srcontext.get(index);
    }

    public int getServiceReferanceContextCount() {
        return srcontext.size();
    }

    public WebContext getWebDDContext() {
        return webcontext;
    }

    public void setEJBDDContext(EJBContext context) {
        this.ejbcontext = context;
    }

    public void setWebDDContext(WebContext context) {
        webcontext = context;
    }

}
