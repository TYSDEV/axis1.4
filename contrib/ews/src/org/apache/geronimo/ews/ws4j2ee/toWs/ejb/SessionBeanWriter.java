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
import org.apache.geronimo.ews.ws4j2ee.context.SEIOperation;
import org.apache.geronimo.ews.ws4j2ee.context.j2eeDD.EJBContext;
import org.apache.geronimo.ews.ws4j2ee.toWs.GenerationFault;
import org.apache.geronimo.ews.ws4j2ee.toWs.JavaClassWriter;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * <p>This class can be used to write the appropriate SessionBean
 * class for the given port type.
 * A Stateless Session Bean, as defined by the Enterprise JavaBeans specification,
 * can be used to implement a Web service to be deployed in the EJB container.
 * A Stateless Session Bean does not have to worry about multi-threaded access.
 * The EJB container is required to serialize request flow through any particular
 * instance of a Service Implementation Bean. The requirements for creating a Service
 * Implementation Bean as a Stateless Session EJB are repeated in part here.</p>
 * <ol>
 * <li>The Service Implementation Bean must have a default public constructor.</li>
 * <li>The Service Implementation Bean may implement the Service Endpoint Interface,
 * but it is not required to do so. The bean must implement all the method
 * signatures of the SEI.<li>
 * <li>The Service Implementation Bean methods are not required to throw
 * javax.rmi.RemoteException. The business methods of the bean must be public
 * and must not be final or static. It may implement other methods in addition
 * to those defined by the SEI.</li>
 * <li>A Service Implementation Bean must be a stateless object.
 * A Service Implementation Bean must not save client specific state
 * across method calls either within the bean instance’s data members or
 * external to the instance.</li>
 * <li>The class must be public, must not be final and must not be abstract.</li>
 * <li>The class must not define the finalize() method.</li>
 * <li>Currently, it must implement the ejbCreate() and ejbRemove() methods which
 * take no arguments. This is a requirement of the EJB container, but generally
 * can be stubbed out with an empty implementation.</li>
 * <li>Currently, a Stateless Session Bean must implement the javax.ejb.SessionBean
 * interface either directly or indirectly. This interface allows the container to notify the Service Implementation Bean of impending changes in its state. The full requirements of this interface are defined in the Enterprise JavaBeans specification section 7.5.1.</li>
 * <li>The Enterprise JavaBeans specification section 7.8.2 defines the allowed
 * container service access requirements.</li>
 * </ol>
 * <h5>Exposing an existing EJB</h5>
 * <p>An existing Enterprise JavaBean may be used as a Service Implementation Bean if it meets the following requirements:</p>
 * <ol>
 * <li>The business methods of the EJB bean class that are exposed on the SEI must meet the Service</li>
 * <li>Implementation Bean requirements defined in section 5.3.1.</li>
 * <li>The SEI must meet the requirements described in the JAX-RPC specification for Service Endpoint Interfaces.</li>
 * <li>The transaction attributes of the SEI methods must not include Mandatory.</li>
 * <li>The developer must package the Web service as described in section 5.4 and must specify an ejb-link from the port in the Web services deployment descriptor to the existing EJB.</li>
 * <ol>
 *
 * @author Rajith Priyanga
 * @author Srinath Perera
 * @date Nov 26, 2003
 */
public class SessionBeanWriter extends JavaClassWriter {
    private String name;
    protected EJBContext ejbcontext;

    /**
     * Constructs a SessionBeanWriter.
     *
     * @param portType The port type which contains the details.
     * @throws GenerationFault
     */
    public SessionBeanWriter(J2EEWebServiceContext context, EJBContext ejbcontext) throws GenerationFault {
        super(context, ejbcontext.getImplBean());
        this.ejbcontext = ejbcontext;
    }

    protected void writeAttributes() throws GenerationFault {
    }

    protected void writeConstructors() throws GenerationFault {
    }

    protected void writeMethods() throws GenerationFault {
        String parmlistStr = "";
        ArrayList operations = j2eewscontext.getMiscInfo().getSEIOperations();
        for (int i = 0; i < operations.size(); i++) {
            SEIOperation op = (SEIOperation) operations.get(i);
            String returnType = op.getReturnType();
            returnType = (returnType == null) ? "void" : returnType;
            out.write("\tpublic " + returnType + " " + op.getMethodName() + "(");
            Iterator pas = op.getParameterNames().iterator();
            boolean first = true;
            while (pas.hasNext()) {
                String name = (String) pas.next();
                String type = (String) op.getParameterType(name);
                if (first) {
                    first = false;
                    out.write(type + " " + name);
                    parmlistStr = parmlistStr + name;
                } else {
                    out.write("," + type + " " + name);
                    parmlistStr = "," + name;
                }
            }
            out.write(")");
//			out.write(") throws java.rmi.RemoteException");
//			ejb giving problems deploying check this            
//			  ArrayList faults = op.getFaults();
//			  for (int j = 0; j < faults.size(); j++) {
//				  out.write("," + (String) faults.get(i));
//			  }
            out.write("{\n");
            if ("int".equals(returnType)) {
                out.write("\t\t\treturn 12;\n");
            } else if ("float".equals(returnType)) {
                out.write("\t\t\treturn 0.0f;\n");
            } else if ("double".equals(returnType)) {
                out.write("\t\t\treturn 0.0d;\n");
            } else if ("short".equals(returnType)) {
                out.write("\t\t\treturn (short)0.0;\n");
            } else if ("boolean".equals(returnType)) {
                out.write("\t\t\treturn false;\n");
            } else if ("byte".equals(returnType)) {
                out.write("\t\t\treturn (byte)24;\n");
            } else if ("long".equals(returnType)) {
                out.write("\t\t\treturn (long)0.0l;\n");
            } else if ("char".equals(returnType)) {
                out.write("\t\t\treturn 'w';\n");
            } else if ("void".equals(returnType)) {
            } else {
                out.write("\t\t\treturn null;\n");
            }
            out.write("\t}\n");
        }
        out.write("\tpublic javax.naming.Context getInitialContext()throws javax.naming.NamingException{\n");
        out.write("\t	java.util.Properties env = new java.util.Properties();\n");
        out.write("\t	env.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY,\"org.jnp.interfaces.NamingContextFactory\");\n");
        out.write("\t	env.put(javax.naming.Context.PROVIDER_URL, \"127.0.0.1:1099\");\n");
        out.write("\t	return new javax.naming.InitialContext(env);\n");
        out.write("\t}\n");
        out.write("\tpublic void ejbCreate() {}\n");
        out.write("\tpublic void ejbActivate() throws javax.ejb.EJBException, java.rmi.RemoteException {}\n");
        out.write("\tpublic void ejbPassivate() throws javax.ejb.EJBException, java.rmi.RemoteException {}\n");
        out.write("\tpublic void ejbRemove() throws javax.ejb.EJBException, java.rmi.RemoteException {}\n");
        out.write("\tpublic void setSessionContext(javax.ejb.SessionContext arg0)throws javax.ejb.EJBException, java.rmi.RemoteException {}\n");
    }

    /* (non-Javadoc)
     * @see org.apache.geronimo.ews.ws4j2ee.toWs.JavaClassWriter#getimplementsPart()
     */
    protected String getimplementsPart() {
        return " implements javax.ejb.SessionBean";
    }

    /* (non-Javadoc)
     * @see org.apache.geronimo.ews.ws4j2ee.toWs.AbstractWriter#isOverWrite()
     */
    protected boolean isOverWrite() {
        return false;
    }

}
