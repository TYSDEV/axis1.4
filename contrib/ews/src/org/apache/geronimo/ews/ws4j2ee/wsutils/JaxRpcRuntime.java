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

package org.apache.geronimo.ews.ws4j2ee.wsutils;

import org.apache.axis.MessageContext;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

/**
 * <p>This class provide acsess to the JAX-RPC runtime dynamic information
 * to the WS4J2ee runtime.</p>
 *
 * @author Srinath Perera(hemapani@opensource.lk)
 */
public class JaxRpcRuntime {
    private static MessageContext MSG_CONTEXT = null;

    public static void setMessageContext(MessageContext msgcontext) {
        MSG_CONTEXT = msgcontext;
    }

    public static MessageContext getMessageContext(MessageContext msgcontext) {
        return MSG_CONTEXT;
    }

    public static Object getRemoteHome(String jndiname, Class homeclass) throws J2EEFault {
        try {
            InitialContext context = new InitialContext();
            Object objref = context.lookup(jndiname);
            return PortableRemoteObject.narrow(objref, homeclass);
        } catch (ClassCastException e) {
            e.printStackTrace();
            throw new J2EEFault(e);
        } catch (NamingException e) {
            e.printStackTrace();
            throw new J2EEFault(e);
        }
    }
}
