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

import org.apache.axis.wsdl.fromJava.Emitter;
import org.apache.geronimo.ews.ws4j2ee.context.J2EEWebServiceContext;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFContext;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFWebserviceDescription;
import org.apache.geronimo.ews.ws4j2ee.toWs.GenerationFault;

/**
 * @author Srinath Perera(hemapani@opensource.lk)
 * @see org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFContext
 */
public class AxisEmitterBasedWSCFContext extends AbstractWSCFContext implements WSCFContext {
    private Emitter emitter;
    private J2EEWebServiceContext j2eeweserviceContext;

    public AxisEmitterBasedWSCFContext(Emitter emitter, J2EEWebServiceContext j2eeweserviceContext) {
        this.emitter = emitter;
        this.j2eeweserviceContext = j2eeweserviceContext;
    }

    /* (non-Javadoc)
     * @see org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFContext#getDescription()
     */
    public String getDescription() {
        throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFContext#getDisplayName()
     */
    public String getDisplayName() {
        throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFContext#getLargeIcon()
     */
    public String getLargeIcon() {
        throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFContext#getSmallIcon()
     */
    public String getSmallIcon() {
        throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFContext#getWebServicesDescription()
     */
    public WSCFWebserviceDescription[] getWebServicesDescription() {
        throw new UnsupportedOperationException();
    }

    public void serialize(java.io.Writer out) throws GenerationFault {
        try {
            out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            out.write("<webservices xmlns=\"http://java.sun.com/xml/ns/j2ee\"\n");
            out.write("xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\n");
            out.write("xmlns:ns1=\"http://www.Monson-Haefel.com/jwsbook/BookQuote\n");
            out.write("xsi:schemaLocation=\"http://java.sun.com/xml/ns/j2ee\n");
            out.write("http://www.ibm.com/standards/xml/webservices/j2ee/j2ee_web_services_1_1.xsd\" version=\"1.1\">\n");
            out.write("<webservice-description>\n");
            out.write("<webservice-description-name>" + emitter.getServiceElementName() + "</webservice-description-name>\n");
            out.write("<wsdl-file>" + j2eeweserviceContext.getMiscInfo().getWsdlFile() + "</wsdl-file>\n");
            out.write("<jaxrpc-mapping-file>" + j2eeweserviceContext.getMiscInfo().getJaxrpcfile() + "</jaxrpc-mapping-file>\n");
            out.write("<port-component>\n");
            out.write("<port-component-name>" + emitter.getPortTypeName() + "</port-component-name>\n");
            out.write("<wsdl-port xmlns:ns1=\"" + j2eeweserviceContext.getWSDLContext().getTargetNSURI() + "\">\n");
            out.write("ns1:" + j2eeweserviceContext.getWSDLContext().getTargetPort().getName());
            out.write("</wsdl-port>\n");
            out.write("<service-endpoint-interface>" + emitter.getCls().getName() + "</service-endpoint-interface>\n");
            out.write("<service-impl-bean>\n");
			
            //TODO let usprint the port type name for now here
            //we got to print the ejb name here parsing the ejb-jar.xml
            String ejbName = j2eeweserviceContext.getMiscInfo().getJ2eeComponetLink();
            if (ejbName == null)
                ejbName = emitter.getPortTypeName();
            out.write("<ejb-link >" + ejbName + "</ejb-link>\n");
            out.write("</service-impl-bean>\n");
            out.write("</port-component>\n");
            out.write("</webservice-description>\n");
            out.write("</webservices>\n");
        } catch (Exception e) {
        }
    }

}
