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

import org.apache.axis.AxisFault;
import org.apache.axis.MessageContext;
import org.apache.axis.providers.java.RPCProvider;

/**
 * register the MessageContext in the jax-rpc runtime of the JSR109
 *
 * @author Srinath Perera(hemapani@opensource.lk)
 */
public class J2EEProvider extends RPCProvider {
    protected Object makeNewServiceObject(MessageContext msgContext,
                                          String clsName)
            throws Exception {
        ContextAccssible webservice =
                (ContextAccssible) super.makeNewServiceObject(msgContext, clsName);
        webservice.setMessageContext(msgContext);
        System.out.println("message Context set");
        return webservice;
    }

    /* (non-Javadoc)
     * @see org.apache.axis.Handler#invoke(org.apache.axis.MessageContext)
     */
    public void invoke(MessageContext msgContext) throws AxisFault {
        super.invoke(msgContext);
    }

}
