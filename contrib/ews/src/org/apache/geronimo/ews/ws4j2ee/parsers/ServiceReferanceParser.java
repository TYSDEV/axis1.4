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

package org.apache.geronimo.ews.ws4j2ee.parsers;

import org.apache.geronimo.ews.ws4j2ee.context.webservices.client.ServiceReferanceImpl;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.client.interfaces.ServiceReferanceContext;
import org.apache.geronimo.ews.ws4j2ee.toWs.GenerationFault;
import org.apache.geronimo.ews.ws4j2ee.utils.EWSUtils;
import org.apache.geronimo.ews.ws4j2ee.utils.Utils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;

/**
 * <service-ref>
 * <service-ref-name>service/Joe</service-ref-name>
 * <service-interface>javax.xml.rpc.Service</service-interface>
 * <wsdl-file>WEB-INF/joe.wsdl</wsdl-file>
 * <jaxrpc-mapping-file>WEB-INF/joe.xml</jaxrpc-mapping-file>
 * <service-qname></service-qname>
 * <port-component-ref>
 * <service-endpoint-interface>sample.Joe</service-endpoint-interface>
 * <port-component-link>JoePort</port-component-link>
 * </port-component-ref>
 * <handler>
 * <handler-name></handler-name>
 * <handler-class></handler-class>
 * </handler>
 * </service-ref>
 *
 * @author Srinath Perera(hemapani@opensource.lk)
 */
public class ServiceReferanceParser {
    private ServiceReferanceContext ref;

    public ServiceReferanceParser(InputStream inputStream) throws GenerationFault {
        try {
            Document doc = EWSUtils.createDocument(inputStream);
            Element root = doc.getDocumentElement();
            Element serviceref = findServiceReferance(root);
            if (serviceref != null)
                parse(serviceref);
            else
                throw new GenerationFault("No service Referance in the file");
        } catch (Exception e) {
            throw GenerationFault.createGenerationFault(e);
        }
    }

    /**
     * find the service-ref element from the xml file.
     *
     * @param ele
     * @return
     */
    public Element findServiceReferance(Element ele) {
        if ("service-ref".equals((ele).getLocalName())) {
            return ele;
        } else {
            NodeList nodes = ele.getChildNodes();
            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                if (node instanceof Element) {
                    return findServiceReferance((Element) node);
                }
            }
            return null;
        }
    }

    public ServiceReferanceParser(Element refEle) {
        parse(refEle);
    }

    public void parse(Element refEle) {
        ref = new ServiceReferanceImpl();
        Element root = refEle;
        ref.setServicerefName(Utils.getElementValue(root.getElementsByTagName("service-ref-name")));
        ref.setServiceInterface(Utils.getElementValue(root.getElementsByTagName("service-interface")));
        ref.setWsdlFile(Utils.getElementValue(root.getElementsByTagName("wsdl-file")));
        ref.setJaxrpcmappingFile(Utils.getElementValue(root.getElementsByTagName("jaxrpc-mapping-file")));
    }

    /**
     * @return
     */
    public ServiceReferanceContext getRef() {
        return ref;
    }

}
