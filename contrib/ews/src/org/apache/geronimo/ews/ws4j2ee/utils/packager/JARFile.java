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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.geronimo.ews.ws4j2ee.toWs.GenerationFault;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;

/**
 * create a jar file with given set of jar entries.
 * It works like
 * <ol>
 * <li>if no extension given the .class is added to the extenstion</li>
 * <li>then file with the given name is tried</li>
 * <li>if that failed the name is tired to load as a stream form the
 * class path</li>
 * </ol>
 *
 * @author Srinath Perera(hemapani@opensource.lk)
 */
public class JARFile {
    protected static Log log =
            LogFactory.getLog(JARFile.class.getName());

    private HashMap jarEntries;
    private File path;

    public JARFile(String path, HashMap entries) {
        jarEntries = entries;
        this.path = new File(path);
    }

    public JARFile(File path) {
        this.path = path;
        jarEntries = new HashMap();
    }

    public void addJarFile(String jarFile) throws GenerationFault {
        try {
            log.info(jarFile + " added ....");
            JarFile file = new JarFile(jarFile);
            Enumeration e = file.entries();
            while (e.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) e.nextElement();
                JARFileEntry newEntry =
                        new JARFileEntry(entry.getName(),
                                file.getInputStream(entry));
                if (!jarEntries.containsKey(entry.getName())) {
                    this.jarEntries.put(entry.getName(), newEntry);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw GenerationFault.createGenerationFault(e);
        }
    }

    public void addJarEntry(JARFileEntry entry) {
        this.jarEntries.put(entry.getJarEntry().getName(), entry);
    }

    public void createNewJarFile() throws IOException {
        if (!path.exists())
            path.createNewFile();
        BufferedOutputStream bo =
                new BufferedOutputStream(new FileOutputStream(path));
        JarOutputStream jo = new JarOutputStream(bo);
        Iterator it = jarEntries.values().iterator();
        for (; it.hasNext();) {
            JARFileEntry jarentry = (JARFileEntry) it.next();
            System.out.println(jarentry.getJarEntry().getName() + " adding");
            InputStream instream = null;
            instream = jarentry.getSource();
            BufferedInputStream source = new BufferedInputStream(instream);
            JarEntry je = jarentry.getJarEntry();
            jo.putNextEntry(je);
            byte[] buf = new byte[1024];
            int anz;
            while ((anz = source.read(buf)) != -1) {
                jo.write(buf, 0, anz);
            }
            jo.flush();
            jo.closeEntry();
            instream.close();
            source.close();
        }
        jo.close();
        bo.close();
    }

}
