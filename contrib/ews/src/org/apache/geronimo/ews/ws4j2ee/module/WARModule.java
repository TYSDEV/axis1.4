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
import org.apache.geronimo.ews.ws4j2ee.utils.FileUtils;
import org.apache.geronimo.ews.ws4j2ee.utils.TemporaryRepository;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

/**
 * @author Srinath Perera(hemapani@opensource.lk)
 */
public class WARModule extends AbstractModule {
    private ArrayList urls;
    private ClassLoader cl;

    /**
     * @param jarFile
     * @throws GenerationFault
     */
    public WARModule(String jarFile, ClassLoader parentCL)
            throws GenerationFault {
        super(jarFile, parentCL);
        try {
            urls = FileUtils.uncompressWar(TemporaryRepository.getEntry(), new File(zip.getName()));
            URL[] aurls = new URL[urls.size()];
            for (int i = 0; i < aurls.length; i++) {
                aurls[i] = ((File) urls.get(i)).toURL();
                System.out.println(aurls[i]);
            }
            cl = new URLClassLoader(aurls, Thread.currentThread().getContextClassLoader());
            wscfFile = getInputStreamForJarEntry(jarFile, "WEB-INF/webservices.xml");
            if (wscfFile == null) {
                wscfFile = getInputStreamForJarEntry(jarFile, "webservices.xml");
            }
            webddfile = getInputStreamForJarEntry(jarFile, "WEB-INF/web.xml");
            if (webddfile == null)
                webddfile = getInputStreamForJarEntry(jarFile, "web.xml");
            if (wscfFile == null)
                throw new GenerationFault("wscf file must not be null");
        } catch (MalformedURLException e) {
            throw GenerationFault.createGenerationFault(e);
        } catch (IOException e) {
            throw GenerationFault.createGenerationFault(e);
        } catch (GenerationFault e) {
            throw GenerationFault.createGenerationFault(e);
        }
    }

    /* (non-Javadoc)
     * @see org.apache.geronimo.ews.ws4j2ee.utils.packager.load.PackageModule#getClassLoaderWithPackageLoaded()
     */
    public ClassLoader getClassLoaderWithPackageLoaded() throws GenerationFault {
        return cl;
    }

    public ArrayList getClassPathElements() {
        return urls;
    }

}
