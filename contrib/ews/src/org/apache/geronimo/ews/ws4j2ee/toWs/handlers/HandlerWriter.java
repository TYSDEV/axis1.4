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

package org.apache.geronimo.ews.ws4j2ee.toWs.handlers;

import org.apache.geronimo.ews.ws4j2ee.context.J2EEWebServiceContext;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFHandler;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFSOAPHeader;
import org.apache.geronimo.ews.ws4j2ee.toWs.AbstractWriter;
import org.apache.geronimo.ews.ws4j2ee.toWs.GenerationFault;
import org.apache.geronimo.ews.ws4j2ee.utils.Utils;

/**
 * <p>Simpley print the Handler without much mess.</p>
 *
 * @author Srinath perera(hemapani@opensource.lk)
 */
public class HandlerWriter extends AbstractWriter {
    private WSCFHandler handler;
    private String className = null;
    private String packageName = null;

    /**
     * @param j2eewscontext
     * @throws GenerationFault
     */
    public HandlerWriter(J2EEWebServiceContext j2eewscontext, WSCFHandler handler)
            throws GenerationFault {
        super(j2eewscontext, j2eewscontext.getMiscInfo().getOutPutPath()
                + "/" + handler.getHandlerClass().replace('.', '/') + ".java");
        this.handler = handler;
        className = Utils.getClassNameFromQuallifiedName(handler.getHandlerClass());
        packageName = Utils.getPackageNameFromQuallifiedName(handler.getHandlerClass());
    }

    public String getFileName() {
        throw new UnsupportedOperationException();
    }

    /**
     * just print it out
     */
    public void writeCode() throws GenerationFault {
        if (out == null)
            return;
        out.write("package " + packageName + ";\n");
        out.write("import org.apache.axis.AxisFault;\n");
        out.write("import org.apache.axis.MessageContext;\n");
        out.write("public class " + className + " extends org.apache.axis.handlers.BasicHandler{\n");
        out.write("\tpublic " + className + "(){\n");
        out.write("\t\tsetName(\"" + handler.getHandlerName() + "\");\n");
        out.write("\t}\n");
        out.write("\tpublic void init(){}\n");
        out.write("\tpublic void cleanup(){}\n");
        out.write("\tpublic void onFault(MessageContext msgContext){}\n");
        out.write("\tpublic void invoke(MessageContext msgContext) throws AxisFault{\n");
        out.write("\t\t//write your implementation here\n");
        out.write("\t}\n");
        out.write("\tpublic java.util.List getUnderstoodHeaders() {\n");
        out.write("\t\t	java.util.List list = new java.util.ArrayList();\n");
        WSCFSOAPHeader[] headers = handler.getSoapHeader();
        for (int i = 0; i < headers.length; i++) {
            out.write("\t\tjavax.xml.namespace.QName name" + i + " = new javax.xml.namespace.QName(\"" + headers[i].getNamespaceURI() + "\",\"" + headers[i].getNamespaceURI() + "\");\n");
            out.write("\t\tlist.add(name" + i + ");\n");
        }
        out.write("\t\treturn list;\n");
        out.write("\t}\n");
        out.write("}");
    }
}
