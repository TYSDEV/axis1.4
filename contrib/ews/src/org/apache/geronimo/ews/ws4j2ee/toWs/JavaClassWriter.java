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

package org.apache.geronimo.ews.ws4j2ee.toWs;

import org.apache.geronimo.ews.ws4j2ee.context.J2EEWebServiceContext;
import org.apache.geronimo.ews.ws4j2ee.utils.Utils;

/**
 * abstract class writer
 *
 * @author Srianth Perera(hemapani@opensource.lk)
 */
public abstract class JavaClassWriter extends AbstractWriter {
    protected String qulifiedName;
    protected String classname;
    protected String packageName;
    private String pacakgesatement;
    private String targetDirectory;

    public JavaClassWriter(J2EEWebServiceContext j2eewscontext, String qulifiedName) throws GenerationFault {
        super(j2eewscontext, Utils.getFileNamefromClass(j2eewscontext, qulifiedName));
        this.qulifiedName = qulifiedName;
        packageName = Utils.getPackageNameFromQuallifiedName(qulifiedName);
        classname = Utils.getClassNameFromQuallifiedName(qulifiedName);
    }

    public void writeCode() throws GenerationFault {
        if (out == null)
            return;
        out.write((packageName != null) ? ("package " + packageName + ";\n") : "");
        writeImportStatements();
        writeClassComment();
        out.write("public class "
                + classname
                + getExtendsPart()
                + getimplementsPart()
                + "{\n");
        writeAttributes();
        writeConstructors();
        writeMethods();
        out.write("}\n");
    }

    protected String getExtendsPart() {
        return " ";
    }

    protected String getimplementsPart() {
        return " ";
    }

    protected void writeClassComment() throws GenerationFault {
    }

    protected void writeImportStatements() throws GenerationFault {
    }

    protected abstract void writeAttributes() throws GenerationFault;

    protected abstract void writeConstructors() throws GenerationFault;

    protected abstract void writeMethods() throws GenerationFault;
}
