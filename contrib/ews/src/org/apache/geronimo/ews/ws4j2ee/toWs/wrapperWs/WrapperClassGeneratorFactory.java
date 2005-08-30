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

import org.apache.geronimo.ews.ws4j2ee.context.J2EEWebServiceContext;
import org.apache.geronimo.ews.ws4j2ee.toWs.GenerationConstants;
import org.apache.geronimo.ews.ws4j2ee.toWs.GenerationFault;
import org.apache.geronimo.ews.ws4j2ee.toWs.UnrecoverableGenerationFault;
import org.apache.geronimo.ews.ws4j2ee.toWs.Writer;
import org.apache.geronimo.ews.ws4j2ee.toWs.wrapperWs.geronimo.InternalBasedWrapperClassWriter;
import org.apache.geronimo.ews.ws4j2ee.toWs.wrapperWs.geronimo.RemoteInterfacedBasedWrapperClassWriter4Geronimo;
import org.apache.geronimo.ews.ws4j2ee.toWs.wrapperWs.jboss.RemoteInterfacedBasedWrapperClassWriter4JBoss;
import org.apache.geronimo.ews.ws4j2ee.toWs.wrapperWs.jonas.RemoteInterfacedBasedWrapperClassWriter4JOnAS;

/**
 * @author Srinath Perera(hemapani@opensource.lk)
 */
public class WrapperClassGeneratorFactory {
    public static Writer createInstance(J2EEWebServiceContext context) throws GenerationFault {
        String implStyle = context.getMiscInfo().getImplStyle();
        String container = context.getMiscInfo().getTargetJ2EEContainer();
        if (!context.getMiscInfo().isImplwithEJB()) {
            return new WebEndpointWrapperClassWriter(context);
        } else if (GenerationConstants.USE_REMOTE.equals(implStyle)
                || GenerationConstants.USE_LOCAL_AND_REMOTE.equals(implStyle)) {
            if (GenerationConstants.JBOSS_CONTAINER.equals(container))
                return new RemoteInterfacedBasedWrapperClassWriter4JBoss(context);
            else if (GenerationConstants.GERONIMO_CONTAINER.equals(container))
                return new RemoteInterfacedBasedWrapperClassWriter4Geronimo(context);
            else if (GenerationConstants.JONAS_CONTAINER.equals(container))
                return new RemoteInterfacedBasedWrapperClassWriter4JOnAS(context);
            else
                throw new UnrecoverableGenerationFault("No proper Wrapper Class generator found");
        } else if (GenerationConstants.USE_LOCAL.equals(implStyle)) {
            return new SimpleLocalInterfaceBasedWrapperClassWriter(context);
        } else if (GenerationConstants.USE_INTERNALS.equals(implStyle)) {
            if (GenerationConstants.JBOSS_CONTAINER.equals(container)) {
                //jboss internals
                throw new UnrecoverableGenerationFault("This combination not supported" + implStyle + "|" + container);
            } else if (GenerationConstants.GERONIMO_CONTAINER.equals(container)) {
                return new InternalBasedWrapperClassWriter(context);
            } else if (GenerationConstants.JONAS_CONTAINER.equals(container))
                throw new UnrecoverableGenerationFault("This combination not supported" + implStyle + "|" + container);
            else
                throw new UnrecoverableGenerationFault("No proper Wrapper Class generator found");
        }
        throw new UnrecoverableGenerationFault("No proper Wrapper Class generator found for " + implStyle + "|" + container);
    }
}
