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
import org.apache.geronimo.ews.ws4j2ee.utils.FileUtils;
import org.apache.geronimo.ews.ws4j2ee.utils.TemporaryRepository;
import org.apache.geronimo.ews.ws4j2ee.utils.UncompressingJarClassLoader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author hemapani@opensource.lk
 */
public class EARModule implements Module {
    private Module pkgModule;
    private UncompressingJarClassLoader cl;
    private Vector classPathElements;
    private ClassLoader parentCL;

    /**
     * @param jarFile
     * @throws GenerationFault
     */
    public EARModule(String jarFile)
            throws GenerationFault {
        try {
            ZipFile earFile = new ZipFile(jarFile);
            Enumeration enumeration = earFile.entries();
            while (enumeration.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) enumeration.nextElement();
                if (entry.getName().endsWith(".war") || entry.getName().endsWith(".jar")) {
                    File outWar = new File(TemporaryRepository.getEntry(), entry.getName());
                    FileUtils.uncompressFile(earFile.getInputStream(entry), outWar);
                    pkgModule = ModuleFactory.createPackageModule(outWar.getAbsolutePath());
                }
            }
        } catch (UnrecoverableGenerationFault e) {
            throw GenerationFault.createGenerationFault(e);
        } catch (IOException e) {
            throw GenerationFault.createGenerationFault(e);
        }
    }

    /**
     * @return
     * @throws GenerationFault
     */
    public ClassLoader getClassLoaderWithPackageLoaded() throws GenerationFault {
        return pkgModule.getClassLoaderWithPackageLoaded();
    }

    /**
     * @return
     * @throws GenerationFault
     */
    public ArrayList getClassPathElements() throws GenerationFault {
        return pkgModule.getClassPathElements();
    }

    public boolean equals(Object obj) {
        return pkgModule.equals(obj);
    }

    /**
     * @param path
     * @return
     * @throws GenerationFault
     */
    public InputStream findFileInModule(String path) throws GenerationFault {
        return pkgModule.findFileInModule(path);
    }

    /**
     * @return
     * @throws GenerationFault
     */
    public InputStream getEjbJarfile() throws GenerationFault {
        return pkgModule.getEjbJarfile();
    }

    /**
     * @return
     * @throws GenerationFault
     */
    public InputStream getWebddfile() throws GenerationFault {
        return pkgModule.getWebddfile();
    }

    /**
     * @return
     * @throws GenerationFault
     */
    public InputStream getWscfFile() throws GenerationFault {
        return pkgModule.getWscfFile();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return pkgModule.hashCode();
    }

    /**
     * @param stream
     * @throws GenerationFault
     */
    public void setEjbJarfile(InputStream stream) throws GenerationFault {
        pkgModule.setEjbJarfile(stream);
    }

    /**
     * @param stream
     * @throws GenerationFault
     */
    public void setWebddfile(InputStream stream) throws GenerationFault {
        pkgModule.setWebddfile(stream);
    }

    /**
     * @param stream
     * @throws GenerationFault
     */
    public void setWscfFile(InputStream stream) throws GenerationFault {
        pkgModule.setWscfFile(stream);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return pkgModule.toString();
    }
}
