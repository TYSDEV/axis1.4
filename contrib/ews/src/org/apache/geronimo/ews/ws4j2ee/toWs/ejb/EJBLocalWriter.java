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
import org.apache.geronimo.ews.ws4j2ee.toWs.JavaInterfaceWriter;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class can be used to write the appropriate EJB Remote interface
 * class for the given port type.
 *
 * @author Rajith Priyanga
 * @author Srinath Perera
 * @date Nov 26, 2003
 */
public class EJBLocalWriter extends JavaInterfaceWriter {
    private String name;
    protected EJBContext ejbcontext;

    /**
     * Constructs a EJBRemoteWriter.
     *
     * @param portType The port type which contains the details.
     * @throws GenerationFault
     */
    public EJBLocalWriter(J2EEWebServiceContext context, EJBContext ejbcontext) throws GenerationFault {
        super(context, ejbcontext.getEjbLocalInterface());
        this.ejbcontext = ejbcontext;
    }

    protected String getExtendsPart() {
        return " extends javax.ejb.EJBLocalObject";
    }

    protected void writeAttributes() throws GenerationFault {
    }

    protected void writeMethods() throws GenerationFault {
        String parmlistStr = "";
        ArrayList operations = j2eewscontext.getMiscInfo().getSEIOperations();
        for (int i = 0; i < operations.size(); i++) {
            SEIOperation op = (SEIOperation) operations.get(i);
            String returnType = op.getReturnType();
            if (returnType == null)
                returnType = "void";
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
//ejb giving problems deploying check this            
//			  ArrayList faults = op.getFaults();
//			  for (int j = 0; j < faults.size(); j++) {
//				  out.write("," + (String) faults.get(i));
//			  }
            out.write(";\n");
        }
    }

}
