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

package org.apache.geronimo.ews.ws4j2ee.toWs.wrapperWs.geronimo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.geronimo.ews.ws4j2ee.context.J2EEWebServiceContext;
import org.apache.geronimo.ews.ws4j2ee.context.SEIOperation;
import org.apache.geronimo.ews.ws4j2ee.toWs.GenerationFault;
import org.apache.geronimo.ews.ws4j2ee.toWs.JavaClassWriter;
import org.apache.geronimo.ews.ws4j2ee.utils.Utils;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Srinath Perera(hemapani@opensource.lk)
 */
public class InternalBasedWrapperClassWriter extends JavaClassWriter {
    protected static Log log =
            LogFactory.getLog(InternalBasedWrapperClassWriter.class.getName());
    protected final String seiName;
    protected final String ejbName;

    /**
     * @param j2eewscontext
     * @param qulifiedName
     * @throws GenerationFault
     */
    public InternalBasedWrapperClassWriter(J2EEWebServiceContext j2eewscontext)
            throws GenerationFault {
        super(j2eewscontext, getName(j2eewscontext) + "Impl");
        seiName = j2eewscontext.getMiscInfo().getJaxrpcSEI();
        ejbName = j2eewscontext.getEJBDDContext().getEjbName();
    }

    private static String getName(J2EEWebServiceContext j2eewscontext) {
        String name =
                j2eewscontext.getWSDLContext().gettargetBinding().getName();
        if (name == null) {
            name = j2eewscontext.getMiscInfo().getJaxrpcSEI();
        }
        return name;
    }

    protected String getimplementsPart() {
        return " implements " + j2eewscontext.getMiscInfo().getJaxrpcSEI() + "," + "org.apache.geronimo.ews.ws4j2ee.wsutils.ContextAccssible";
    }

    protected void writeAttributes() throws GenerationFault {
        out.write("private org.apache.axis.MessageContext msgcontext;\n");
        out.write("\tprivate " + seiName + " bean = null;\n");
        out.write("\tprivate org.openejb.EJBContainer container;\n");
    }

    protected void writeConstructors() throws GenerationFault {
        out.write("\tpublic " + classname + "(){\n");
        out.write("\t}\n");
    }

    protected void writeMethods() throws GenerationFault {
        out.write("\tpublic void setMessageContext(org.apache.axis.MessageContext msgcontext){;\n");
        out.write("\t\tthis.msgcontext = msgcontext;\n");
        out.write("\t}\n");
        String parmlistStr = null;
        ArrayList operations = j2eewscontext.getMiscInfo().getSEIOperations();
        for (int i = 0; i < operations.size(); i++) {
            parmlistStr = "";
            SEIOperation op = (SEIOperation) operations.get(i);
            String returnType = op.getReturnType();
            if (returnType == null)
                returnType = "void";
            out.write("\tpublic " + returnType + " " + op.getMethodName() + "(");
            Iterator pas = op.getParameterNames().iterator();
            boolean first = true;
            while (pas.hasNext()) {
                String name = (String) pas.next();
                String type = op.getParameterType(name);
                if (first) {
                    first = false;
                    out.write(type + " " + name);
                    parmlistStr = parmlistStr + name;
                } else {
                    out.write("," + type + " " + name);
                    parmlistStr = parmlistStr + "," + name;
                }
            }
            out.write(") throws java.rmi.RemoteException");
            ArrayList faults = op.getFaults();
            for (int j = 0; j < faults.size(); j++) {
                out.write("," + (String) faults.get(i));
            }
            out.write("{\n");
            out.write("\t\tString methodName = \"" + op.getMethodName() + "\";\n");
            out.write("\t\tjava.lang.reflect.Method callMethod = Utils.getJavaMethod(\""
                    + seiName
                    + "\",methodName);\n");
            out.write("\t\tClass[] classes = callMethod.getParameterTypes();\n");
            out.write("\t\tObject[] arguments = new Object[]{");
            pas = op.getParameterNames().iterator();
            first = true;
            while (pas.hasNext()) {
                String name = (String) pas.next();
                String type = op.getParameterType(name);
                if (first) {
                    first = false;
                    out.write(Utils.getParameter(type, name));
                } else {
                    out.write("," + Utils.getParameter(type, name));
                }
            }
            out.write("};\n");
            if (!"void".equals(returnType)) {
                out.write("\t\t\treturn "
                        + Utils.getReturnCode(returnType, "AxisGeronimoUtils.invokeEJB(\""
                        + ejbName
                        + "\",methodName,classes,arguments)")
                        + ";\n");
            } else {
                out.write("\t\t\tAxisGeronimoUtils.invokeEJB(\""
                        + ejbName
                        + "\",methodName,classes,arguments);\n");
            }
            out.write("\t}\n");
        }
    }

    /* (non-Javadoc)
     * @see org.apache.geronimo.ews.ws4j2ee.toWs.JavaClassWriter#writeImportStatements()
     */
    protected void writeImportStatements() throws GenerationFault {
        out.write("import org.apache.geronimo.axis.AxisGeronimoUtils;\n");
        out.write("import org.apache.geronimo.ews.ws4j2ee.utils.Utils;\n");
    }

}

