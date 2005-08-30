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
package org.apache.ws.axis.security;

import org.apache.axis.AxisFault;
import org.apache.axis.MessageContext;
import org.apache.axis.handlers.BasicHandler;
import org.apache.geronimo.ews.ws4j2ee.context.security.SecurityContext4J2EE;

/**
 * Still this is not in use. But may be in future...
 *
 * @author Rajith Priyanga (rpriyanga@yahoo.com)
 * @date Apr 6, 2004
 */
public abstract class CheckPoint4J2EEHandler extends BasicHandler {

    /**
     * @see org.apache.axis.Handler#invoke(org.apache.axis.MessageContext)
     */
    public void invoke(MessageContext cntxt) throws AxisFault {
        SecurityContext4J2EE sc4j2ee;
        //TODO
        //Populate the SecurityContext4J2EE object with available info.
        //Other info will be dynamically calculated rom MessageCOntext.		 
    }

    /**
     * Decides whether the message integrity is sufficiently varifiable.
     *
     * @param cntxt Message context.
     * @return true if the test is passed.
     */
    public abstract boolean integrityTest(MessageContext cntxt);

    /**
     * Decides whether the message privacy is sufficiently protected.
     *
     * @param cntxt Message context
     * @return true if the test is passed.
     */
    public abstract boolean privacyTest(MessageContext cntxt);

    /**
     * Returns the password of the given user. This should be retrieved
     * from a password store.
     *
     * @param username
     * @return The actual password.
     */
    public abstract char[] getPassword(String username);

}
