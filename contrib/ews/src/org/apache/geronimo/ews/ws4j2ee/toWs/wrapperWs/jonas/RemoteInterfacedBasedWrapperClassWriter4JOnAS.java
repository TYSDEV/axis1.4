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
package org.apache.geronimo.ews.ws4j2ee.toWs.wrapperWs.jonas;

import org.apache.geronimo.ews.ws4j2ee.context.J2EEWebServiceContext;
import org.apache.geronimo.ews.ws4j2ee.toWs.GenerationFault;
import org.apache.geronimo.ews.ws4j2ee.toWs.wrapperWs.SimpleRemoteInterfaceBasedWrapperClassWriter;

/**
 * @author Srinath Perera(hemapani@opensource.lk)
 */
public class RemoteInterfacedBasedWrapperClassWriter4JOnAS extends SimpleRemoteInterfaceBasedWrapperClassWriter {

    /**
     * @param j2eewscontext
     * @throws GenerationFault
     */
    public RemoteInterfacedBasedWrapperClassWriter4JOnAS(J2EEWebServiceContext j2eewscontext)
            throws GenerationFault {
        super(j2eewscontext);
    }

    protected String getJNDIHostAndPort() {
        // TODO using carol MultiOrb, port number can change from protocol to protocol (jrmp, iiop, jeremie) 
        return "127.0.0.1:1099";
    }

    protected String getJNDIInitialContextFactory() {
        return "org.objectweb.carol.jndi.spi.MultiOrbInitialContextFactory";
    }

}
