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

package org.apache.geronimo.ews.ws4j2ee.module;

import org.apache.geronimo.ews.ws4j2ee.toWs.GenerationFault;
import org.apache.geronimo.ews.ws4j2ee.toWs.UnrecoverableGenerationFault;

import java.io.File;

/**
 * @author Srinath Perera(hemapani@opensource.lk)
 */
public class ModuleFactory {

    /**
     * @param path        - path to the package
     * @param firstmodule - is it the first module or a module inside other module
     * @return
     * @throws GenerationFault
     */
    public static Module createPackageModule(String path) throws UnrecoverableGenerationFault {
        return createPackageModule(path, Thread.currentThread().getContextClassLoader());
    }

    public static Module createPackageModule(String path,
                                             ClassLoader parentCL) throws UnrecoverableGenerationFault {
        try {
            if (path != null) {
                File file = new File(path);
                if (!file.exists())
                    throw new UnrecoverableGenerationFault("file not found " + file.getAbsolutePath());
                if (file.isDirectory()) {
                    return new DirModule(path, parentCL);
                } else if (path.endsWith(".jar") || path.endsWith(".JAR"))
                    return new JarModule(path, parentCL);
                else if (path.endsWith(".war") || path.endsWith(".WAR"))
                    return new WARModule(path, parentCL);
                else if (path.endsWith(".ear") || path.endsWith(".EAR"))
                    return new EARModule(path);
                else if (path.endsWith(".xml"))
                    return new DirModule(new File(path));
                else
                    throw new UnrecoverableGenerationFault("unknown type of file");
            }
        } catch (GenerationFault e) {
            throw new UnrecoverableGenerationFault(path + " not found ", e);
        }
        throw new UnrecoverableGenerationFault("path is null");
    }
}
