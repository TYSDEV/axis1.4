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
import org.apache.axis.utils.ClassUtils;

import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * register the MessageContext in the jax-rpc runtime of the JSR109
 *
 * @author Srinath Perera(hemapani@opensource.lk)
 */
public class EWSProvider extends RPCProvider {
    public static final String OPTION_EJB_NAME = "beanName";
    public static final String OPTION_JNDI_LOOKUP_NAME = "beanJndiName";
    public static final String OPTION_HOMEINTERFACE_NAME = "homeInterfaceName";
    public static final String OPTION_REMOTEINTERFACE_NAME = "remoteInterfaceName";
    public static final String OPTION_LOCALHOMEINTERFACE_NAME = "localHomeInterfaceName";
    public static final String OPTION_LOCALINTERFACE_NAME = "localInterfaceName";

    private String ejblookupName;
    private String localhome;
    private String home;
    private String remote;
    private String local;

    private boolean ejbbased = true;

    protected Object makeNewServiceObject(MessageContext msgContext,
                                          String clsName)
            throws Exception {
        if (ejbbased) {
            java.util.Properties env = new java.util.Properties();
            InputStream jndiIn = ClassUtils.getResourceAsStream(getClass(), "jndi.properties");
            if (jndiIn != null) {
                env.load(jndiIn);
            } else {
                throw new AxisFault("Do not find the JNDI properties file in the class path");
            }
            javax.naming.Context initial = new javax.naming.InitialContext(env);
            ejblookupName = (String) getOption(OPTION_JNDI_LOOKUP_NAME);
            remote = (String) getOption(OPTION_REMOTEINTERFACE_NAME);
            home = (String) getOption(OPTION_HOMEINTERFACE_NAME);
            local = (String) getOption(OPTION_LOCALINTERFACE_NAME);
            localhome = (String) getOption(OPTION_LOCALHOMEINTERFACE_NAME);
            if (remote != null && home != null && ejblookupName != null) {
                Object objref = initial.lookup(ejblookupName);
                Class homeClass = ClassUtils.forName(home);
                Object homeObj = javax.rmi.PortableRemoteObject.narrow(objref, homeClass);
                Method method = homeClass.getMethod("create", new Class[]{});
                return method.invoke(homeObj, new Object[]{});
            } else if (local != null && localhome != null && ejblookupName != null) {
                Object homeObj = initial.lookup("java:comp/" + ejblookupName);
                Class homeClass = ClassUtils.forName(localhome);
                Method method = homeClass.getMethod("create", new Class[]{});
                return method.invoke(homeObj, new Object[]{});
            }
            throw new AxisFault("Wrong configuration");
        } else {
            return makeNewServiceObject(msgContext, clsName);
        }
    }

    /* (non-Javadoc)
     * @see org.apache.axis.providers.java.RPCProvider#invokeMethod(org.apache.axis.MessageContext, java.lang.reflect.Method, java.lang.Object, java.lang.Object[])
     */
    protected Object invokeMethod(MessageContext msgContext,
                                  Method method,
                                  Object obj,
                                  Object[] argValues)
            throws Exception {
        Method ejbMethod = obj.getClass().getMethod(method.getName(), method.getParameterTypes());
        return ejbMethod.invoke(obj, argValues);
    }
}
