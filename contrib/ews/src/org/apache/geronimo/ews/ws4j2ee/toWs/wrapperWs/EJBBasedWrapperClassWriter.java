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
package org.apache.geronimo.ews.ws4j2ee.toWs.wrapperWs;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.geronimo.ews.ws4j2ee.context.J2EEWebServiceContext;
import org.apache.geronimo.ews.ws4j2ee.context.j2eeDD.EJBContext;
import org.apache.geronimo.ews.ws4j2ee.toWs.GenerationFault;
import org.apache.geronimo.ews.ws4j2ee.toWs.JavaClassWriter;
import org.apache.geronimo.ews.ws4j2ee.toWs.UnrecoverableGenerationFault;
import org.apache.geronimo.ews.ws4j2ee.utils.Utils;

/**
 * This class genarate the wrapper Webservice.
 *
 * @author Srinath Perera(hemapani@opensource.lk)
 */
public abstract class EJBBasedWrapperClassWriter extends JavaClassWriter {
    protected static Log log =
            LogFactory.getLog(WrapperWsGenerator.class.getName());
    protected String seiName = null;
    protected EJBContext context;

    /**
     * @param j2eewscontext
     * @param qulifiedName
     * @throws GenerationFault
     */
    public EJBBasedWrapperClassWriter(J2EEWebServiceContext j2eewscontext)
            throws GenerationFault {
        super(j2eewscontext, getName(j2eewscontext) + "Impl");
        context = j2eewscontext.getEJBDDContext();
        if (context == null) {
            throw new UnrecoverableGenerationFault("for ejbbased Impl" +
                    " the EJBDDContext must not be null");
        }
    }

    private static String getName(J2EEWebServiceContext j2eewscontext) {
        String name = j2eewscontext.getWSDLContext().gettargetBinding().getName();
        if (name == null) {
            name = Utils.qName2JavaName(j2eewscontext.getWSDLContext().gettargetBinding().getQName());
        }
        return name;
    }

    protected String getimplementsPart() {
        return " implements " + j2eewscontext.getMiscInfo().getJaxrpcSEI() + ",org.apache.geronimo.ews.ws4j2ee.wsutils.ContextAccssible";
    }

    protected void writeAttributes() throws GenerationFault {
        out.write("private " + seiName + " ejb = null;\n");
        out.write("private org.apache.axis.MessageContext msgcontext;\n");
    }

    protected void writeConstructors() throws GenerationFault {
        out.write("\tpublic " + classname + "(){}\n");
    }
}
