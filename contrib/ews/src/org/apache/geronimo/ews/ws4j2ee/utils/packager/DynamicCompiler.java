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

package org.apache.geronimo.ews.ws4j2ee.utils.packager;

import org.apache.axis.components.compiler.CompilerError;
import org.apache.axis.components.compiler.CompilerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Srinath Perera(hemapani@opensource.lk)
 */
public class DynamicCompiler {
    private ArrayList srcFiles = new ArrayList();
    private String outDir = ".";

    public DynamicCompiler(String srcDir) {
        FileSerachAgent searcher = new FileSerachAgent(srcDir);
        searcher.addAcceptedFile(".java");
        srcFiles = searcher.serach();
    }

    public void compile() {
        org.apache.axis.components.compiler.Compiler comp = CompilerFactory.getCompiler();
        comp.setClasspath(System.getProperty("java.class.path"));
        comp.setSource("genCode/withoutWSDL/server/ejb/");

        comp.setDestination(outDir);

        for (int i = 0; i < srcFiles.size(); i++) {
            String src = (String) srcFiles.get(i);
            comp.addFile(src);
        }
        try {
            if (!comp.compile()) {
                List errors = comp.getErrors();
                String errorStr = "";
                for (int i = 0; i < errors.size(); i++) {
                    errorStr = errorStr + ((CompilerError) errors.get(i)).getMessage() + "\n";
                }
                throw new RuntimeException(errorStr);
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage() +
                    "\nif you getting class not found Exception your jar $JAVA_HOME/lib/tools.jar" +
                    "	may be not in the classpath please add it and try agaien");
        }
    }
}
