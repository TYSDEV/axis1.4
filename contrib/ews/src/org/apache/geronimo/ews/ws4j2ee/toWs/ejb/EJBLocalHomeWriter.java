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
import org.apache.geronimo.ews.ws4j2ee.context.j2eeDD.EJBContext;
import org.apache.geronimo.ews.ws4j2ee.toWs.GenerationFault;
import org.apache.geronimo.ews.ws4j2ee.toWs.JavaInterfaceWriter;

/**
 * This class can be used to write the appropriate EJB LocalHome interface
 * class for the given port type.
 *
 * @author Rajith Priyanga
 * @author Srinath Perera
 * @date Nov 26, 2003
 */
public class EJBLocalHomeWriter extends JavaInterfaceWriter {
    private String name;
    protected EJBContext ejbcontext;

    /**
     * Constructs a EJBLocalHomeWriter.
     *
     * @param portType The port type which contains the details.
     * @throws GenerationFault
     */
    public EJBLocalHomeWriter(J2EEWebServiceContext context, EJBContext ejbcontext) throws GenerationFault {
        super(context, ejbcontext.getEjbLocalHomeInterfce());
        this.ejbcontext = ejbcontext;
    }

    protected void writeAttributes() throws GenerationFault {
    }

    protected void writeMethods() throws GenerationFault {
        out.write("\tpublic " + ejbcontext.getEjbLocalInterface() + " create()throws javax.ejb.CreateException;\n");
    }

    protected String getExtendsPart() {
        return " extends javax.ejb.EJBLocalHome ";
    }

}
