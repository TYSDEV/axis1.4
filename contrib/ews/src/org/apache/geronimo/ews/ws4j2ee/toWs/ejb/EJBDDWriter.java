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

package org.apache.geronimo.ews.ws4j2ee.toWs.ejb;

/*
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException; */

import org.apache.geronimo.ews.ws4j2ee.context.J2EEWebServiceContext;
import org.apache.geronimo.ews.ws4j2ee.context.j2eeDD.EJBContext;
import org.apache.geronimo.ews.ws4j2ee.toWs.AbstractWriter;
import org.apache.geronimo.ews.ws4j2ee.toWs.GenerationConstants;
import org.apache.geronimo.ews.ws4j2ee.toWs.GenerationFault;

/**
 * We are plan to use the JAXB to genarate and parse the
 * ejb-jar.xml file. this code is just for the get very simple
 * DD created
 *
 * @author Srinath Perera(hemapani@opensource.lk)
 */
public class EJBDDWriter extends AbstractWriter {
    protected EJBContext ejbcontext;

    /**
     * @param j2eewscontext
     * @throws GenerationFault
     */
    public EJBDDWriter(J2EEWebServiceContext j2eewscontext, EJBContext ejbcontext)
            throws GenerationFault {
        super(j2eewscontext, j2eewscontext.getMiscInfo().getOutPutPath() + "/META-INF/ejb-jar.xml");
        this.ejbcontext = ejbcontext;
    }

    public void writeCode() throws GenerationFault {
        if (out != null)
            writeSessionDD();
    }

    public void writeSessionDD() throws GenerationFault {
        String ejbname = j2eewscontext.getWSDLContext().getTargetPortType().getName().toLowerCase();
        int index = ejbname.lastIndexOf(".");
        if (index > 0) {
            ejbname = ejbname.substring(index + 1);
        }
        String version = GenerationConstants.J2EE_VERSION_1_4;
        out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
//let us stack to the J2EE 1.4 and by the wy 1.3 has nothing to do with web services :) 
//		if(version == GenerationConstants.J2EE_VERSION_1_3){
//			out.write("<!DOCTYPE ejb-jar PUBLIC '-//Sun Microsystems, Inc.//DTD Enterprise JavaBeans 2.0//EN' 'http://java.sun.com/dtd/ejb-jar_2_0.dtd'>\n");
//			out.write("<ejb-jar>\n");
//		}else{
//		}
        out.write("<ejb-jar xmlns=\"http://java.sun.com/xml/ns/j2ee\"\n");
        out.write("		 xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n");
        out.write("		 xsi:schemaLocation=\"http://java.sun.com/xml/ns/j2ee\\n");
        out.write("		 http://java.sun.com/xml/ns/j2ee/ejb-jar_2_1.xsd\"\n");
        out.write("		 version=\"2.1\">\n");

        //out.write("<display-name>" + j2eewscontext.getWSCFContext().getWscfdWsDesxription().getDisplayName() + "</display-name>\n");
        out.write("\t<enterprise-beans>\n");
        out.write("\t\t<session>\n");
        out.write("\t\t\t<display-name>" + j2eewscontext.getWSCFContext().getWscfdWsDesxription().getDisplayName() + "</display-name>\n");
        out.write("\t\t\t<ejb-name>" + ejbname + "</ejb-name>\n");
        String implStyle = j2eewscontext.getMiscInfo().getImplStyle();
        if (GenerationConstants.USE_LOCAL_AND_REMOTE.equals(implStyle)
                || GenerationConstants.USE_REMOTE.equals(implStyle)
                || GenerationConstants.USE_INTERNALS.equals(implStyle)) {
            out.write("\t\t\t<home>" + ejbcontext.getEjbhomeInterface() + "</home>\n");
            out.write("\t\t\t<remote>" + ejbcontext.getEjbRemoteInterface() + "</remote>\n");
        }
        if (GenerationConstants.USE_LOCAL_AND_REMOTE.equals(implStyle)
                || GenerationConstants.USE_LOCAL.equals(implStyle)) {
            out.write("\t\t\t<local-home>" + ejbcontext.getEjbLocalHomeInterfce() + "</local-home>");
            out.write("\t\t\t<local>" + ejbcontext.getEjbLocalInterface() + "</local>");
        }
        out.write("\t\t\t<ejb-class>" + ejbcontext.getImplBean() + "</ejb-class>\n");
        out.write("\t\t\t<session-type>Stateless</session-type>\n");
        out.write("\t\t\t<transaction-type>Bean</transaction-type>\n");
        out.write("\t\t\t<security-identity>\n");
        out.write("\t\t\t\t<description></description>\n");
        out.write("\t\t\t\t<use-caller-identity></use-caller-identity>\n");
        out.write("\t\t\t</security-identity>\n");
        out.write("\t\t</session\n>");
        out.write("\t</enterprise-beans>\n");
        out.write("\t<assembly-descriptor>\n");
        out.write("\t    <method-permission>\n");
        out.write("\t        <unchecked/>\n");
        out.write("\t        <method>\n");
        out.write("\t		     <ejb-name>" + ejbname + "</ejb-name>\n");
        out.write("\t			 <method-name>*</method-name>\n");
        out.write("\t		</method>\n");
        out.write("\t     </method-permission>\n");
        out.write("\t</assembly-descriptor>\n");
        out.write("</ejb-jar>\n");
    }
}
