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

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;

/**
 * @author hemapani@opensource.lk
 */
public class ModulePackager {
    private JarOutputStream jarFile;
    private byte[] buf = new byte[1024];
    private HashMap entries = new HashMap();

    public ModulePackager(File outjarName) throws IOException {
        BufferedOutputStream bo =
                new BufferedOutputStream(new FileOutputStream(outjarName));
        jarFile = new JarOutputStream(bo);
    }

    public void addJarFile(File file) throws IOException {
        JarFile infile = new JarFile(file);
        Enumeration enu = infile.entries();
        while (enu.hasMoreElements()) {
            ZipEntry zipentry = (ZipEntry) enu.nextElement();
            InputStream in = infile.getInputStream(zipentry);
            addFileToJar(zipentry.getName(), in);
        }
    }

    public void addClassFiles(File dir) throws IOException {
        addClassFiles(dir, null);
    }

    private void addClassFiles(File file, String path) throws IOException {
        if (file.isDirectory()) {
            if (path == null) {
                path = "";
            } else if ("".equals(path)) {
                path = file.getName();
            } else {
                path = path + "/" + file.getName();
            }
            File[] files = file.listFiles();
            if (files != null) {
                for (int i = 0; i < files.length; i++) {
                    addClassFiles(files[i], path);
                }
            }
        } else {
            path = path + "/" + file.getName();
            addFileToJar(path, new FileInputStream(file));
        }
    }

    public void addFileToJar(String path, File input) throws IOException {
        addFileToJar(path, new FileInputStream(input));
    }

    private void addFileToJar(String entry, InputStream instream) throws IOException {
        if (entries.containsKey(entry)) {
            entries.remove(entry);
        }
        entries.put(entry, instream);
    }

    public void finalizeJar() throws IOException {
        Iterator jarentries = entries.keySet().iterator();
        while (jarentries.hasNext()) {
            String entryName = (String) jarentries.next();
            ZipEntry entry = new ZipEntry(entryName);
            InputStream instream = (InputStream) entries.get(entryName);
            jarFile.putNextEntry(entry);
            int anz;
            while ((anz = instream.read(buf)) != -1) {
                jarFile.write(buf, 0, anz);
            }
            jarFile.flush();
            jarFile.closeEntry();
            instream.close();
        }
        jarFile.close();
    }

}
