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

import org.apache.geronimo.ews.ws4j2ee.context.J2EEWebServiceContext;
import org.apache.geronimo.ews.ws4j2ee.context.j2eeDD.EJBContext;
import org.apache.geronimo.ews.ws4j2ee.toWs.GenerationConstants;
import org.apache.geronimo.ews.ws4j2ee.toWs.GenerationFault;
import org.apache.geronimo.ews.ws4j2ee.toWs.Generator;
import org.apache.geronimo.ews.ws4j2ee.toWs.UnrecoverableGenerationFault;
import org.apache.geronimo.ews.ws4j2ee.toWs.Writer;
import org.apache.geronimo.ews.ws4j2ee.toWs.Ws4J2eeFactory;

/**
 * <p>This class crete the nessacsaary EJB artifacts</p>
 *
 * @author Srinath Perera(hemapani@opensource.lk)
 */
public class EJBGenerator implements Generator {
    private J2EEWebServiceContext context;
    private Writer homewriter;
    private Writer remotewriter;
    private Writer beanwriter;
    private Writer ddwriter;
    private Writer localwriter;
    private Writer localhomewriter;
    protected EJBContext ejbcontext;
    private Ws4J2eeFactory factory;

    public EJBGenerator(J2EEWebServiceContext context) throws GenerationFault {
        this.context = context;
        ejbcontext = context.getEJBDDContext();
        if (ejbcontext == null) {
            throw new UnrecoverableGenerationFault("for ejbbased Impl" +
                    " the EJBDDContext must not be null");
        }
        factory = context.getFactory();
        String implStyle = context.getMiscInfo().getImplStyle();
        if (GenerationConstants.USE_LOCAL_AND_REMOTE.equals(implStyle)
                || GenerationConstants.USE_REMOTE.equals(implStyle)) {
            homewriter = factory.getGenerationFactory().createEJBWriter(context, ejbcontext, GenerationConstants.EJB_HOME_INTERFACE_WRITER);
            remotewriter = factory.getGenerationFactory().createEJBWriter(context, ejbcontext, GenerationConstants.EJB_REMOTE_INTERFACE_WRITER);
        } else if (GenerationConstants.USE_LOCAL_AND_REMOTE.equals(implStyle)
                || GenerationConstants.USE_LOCAL.equals(implStyle)) {
            localhomewriter = factory.getGenerationFactory().createEJBWriter(context, ejbcontext, GenerationConstants.EJB_LOCAL_HOME_INTERFACE_WRITER);
            localwriter = factory.getGenerationFactory().createEJBWriter(context, ejbcontext, GenerationConstants.EJB_LOCAL_INTERFACE_WRITER);
        } else if (GenerationConstants.USE_INTERNALS.equals(implStyle)) {
            homewriter = factory.getGenerationFactory().createEJBWriter(context, ejbcontext, GenerationConstants.EJB_HOME_INTERFACE_WRITER);
            remotewriter = factory.getGenerationFactory().createEJBWriter(context, ejbcontext, GenerationConstants.EJB_REMOTE_INTERFACE_WRITER);
        }
        if (!context.getMiscInfo().isImplAvalible()) {
            beanwriter = factory.getGenerationFactory().createEJBWriter(context, ejbcontext, GenerationConstants.EJB_IMPLEMENTATION_BEAN_WRITER);
        }
        ddwriter = factory.getGenerationFactory().createEJBWriter(context, ejbcontext, GenerationConstants.EJB_DD_WRITER);
    }

    public void generate() throws GenerationFault {
        if (homewriter != null)
            homewriter.write();
        if (remotewriter != null)
            remotewriter.write();
        if (beanwriter != null)
            beanwriter.write();
        if (ddwriter != null)
            ddwriter.write();
        if (localwriter != null) {
            localwriter.write();
        }
        if (localhomewriter != null) {
            localhomewriter.write();
        }
    }

}
