/*
 * The Apache Software License, Version 1.1
 *
 *
 * Copyright (c) 2001-2004 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "Axis" and "Apache Software Foundation" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache",
 *    nor may "Apache" appear in their name, without prior written
 *    permission of the Apache Software Foundation.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 */

package org.apache.geronimo.ews.ws4j2ee.wsutils;

import java.io.InputStream;
import java.lang.reflect.Method;

import org.apache.axis.AxisFault;
import org.apache.axis.MessageContext;
import org.apache.axis.providers.java.RPCProvider;
import org.apache.axis.utils.ClassUtils;

/**
 * register the MessageContext in the jax-rpc runtime of the JSR109 
 * @author Srinath Perera(hemapani@opensource.lk)
 */
public class EWSProvider extends RPCProvider{
    public static final String OPTION_BEANNAME = "beanJndiName";
    public static final String OPTION_HOMEINTERFACENAME = "homeInterfaceName";
    public static final String OPTION_REMOTEINTERFACENAME = "remoteInterfaceName";
    public static final String OPTION_LOCALHOMEINTERFACENAME = "localHomeInterfaceName";
    public static final String OPTION_LOCALINTERFACENAME = "localInterfaceName";
    
    public static final String OPTION_INITIAL_FACOTORY = "jndiContextClass";
    public static final String OPTION_JNDI_FACTORY = "jndiContextClass";
    public static final String OPTION_JNDI_URL = "jndiURL";
    public static final String OPTION_JNDI_USERNAME = "jndiUser";
    public static final String OPTION_JNDI_PASSWORD = "jndiPassword";

    private String ejblookupName;
    private String localhome;
    private String home;
    private String remote;
    private String local;
    
    private String jndiUrl;
    private String jndiFactory;
    private String jndiUser;
    private String jndiPassword; 
    private boolean ejbbased = true;


    protected Object makeNewServiceObject(
        MessageContext msgContext,
        String clsName)
        throws Exception {
            if(ejbbased){
                java.util.Properties env = new java.util.Properties();
                InputStream jndiIn = getClass().getClassLoader().getResourceAsStream("jndi.properties");
                if(jndiIn != null){
                    env.load(jndiIn);
                }else{
                    env.setProperty("java.naming.factory.initial",(String)getOption(OPTION_INITIAL_FACOTORY));
                    env.setProperty("java.naming.factory.url.pkgs",(String)getOption(OPTION_JNDI_FACTORY));
                    env.setProperty("java.naming.provider.url",(String)getOption(OPTION_JNDI_URL));
                }
                javax.naming.Context initial = new javax.naming.InitialContext(env);
                if(remote != null && home != null && ejblookupName != null){
                    Object objref = initial.lookup(ejblookupName);
                    Class homeClass = ClassUtils.forName(home);
                    Object homeObj = javax.rmi.PortableRemoteObject.narrow(objref,homeClass);
                    Method method = homeClass.getMethod("create",new Class[]{}); 
                    return method.invoke(homeObj,new Object[]{});
                }else if(local != null && localhome != null && ejblookupName != null){
                    Object homeObj = initial.lookup("java:comp/"+ejblookupName);
                    Class homeClass = ClassUtils.forName(localhome);
                    Method method = homeClass.getMethod("create",new Class[]{}); 
                    return method.invoke(homeObj,new Object[]{});
                }
                throw new AxisFault("Wrong configuration");
            }else{
                return makeNewServiceObject(msgContext, clsName);
            
            }

    }

    /* (non-Javadoc)
     * @see org.apache.axis.providers.java.RPCProvider#invokeMethod(org.apache.axis.MessageContext, java.lang.reflect.Method, java.lang.Object, java.lang.Object[])
     */
    protected Object invokeMethod(
        MessageContext msgContext,
        Method method,
        Object obj,
        Object[] argValues)
        throws Exception {
            Method ejbMethod = obj.getClass().getMethod(method.getName(),method.getParameterTypes());
            return ejbMethod.invoke(obj,argValues);
    }

}
