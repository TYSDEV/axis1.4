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

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

/**
 * @author Srinath Perera(hemapani@opensource.lk)
 */
public class JarModule extends AbstractModule {
    private ClassLoader cl;
    private ArrayList list = new ArrayList(1);

    /**
     * @param jarFile
     * @throws GenerationFault
     */
    public JarModule(String jarFile, ClassLoader parentCL)
            throws GenerationFault {
        super(jarFile, parentCL);
        try {
            this.parentCL = parentCL;
            list.add(new File(jarFile));
            cl = new URLClassLoader(new URL[]{(new File(jarFile)).toURL()}, parentCL);
            wscfFile = getInputStreamForJarEntry(jarFile, "META-INF/webservices.xml");
            if (wscfFile == null) {
                wscfFile =
                        getInputStreamForJarEntry(jarFile, "META-INF/webservice.xml");
            }
            if (wscfFile == null) {
                wscfFile = getInputStreamForJarEntry(jarFile, "webservice.xml");
            }
            webddfile = getInputStreamForJarEntry(jarFile, "META-INF/web.xml");
            if (wscfFile == null) {
                webddfile = getInputStreamForJarEntry(jarFile, "web.xml");
            }
            ejbJarfile = getInputStreamForJarEntry(jarFile, "META-INF/ejb-jar.xml");
            if (wscfFile == null)
                throw new GenerationFault("wscf file must not be null");
        } catch (MalformedURLException e) {
            throw GenerationFault.createGenerationFault(e);
        }
    }

    /* (non-Javadoc)
     * @see org.apache.geronimo.ews.ws4j2ee.utils.packager.load.PackageModule#getClassLoaderWithPackageLoaded()
     */
    public ClassLoader getClassLoaderWithPackageLoaded() {
        return cl;
    }

    public ArrayList getClassPathElements() {
        return list;
    }
}
