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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Packager {
    protected static Log log =
            LogFactory.getLog(Packager.class.getName());

    private String[] args;

    public Packager(String[] args) throws GenerationFault {
        this.args = args;
    }

    public void createJar() throws GenerationFault {
        try {
            JARFile jfile = new JARFile(new File(args[0]));
            log.info("creating the jar " + args[0]);
            for (int i = 1; i < args.length; i++) {
                if (args[i].endsWith(".jar")) {
                    jfile.addJarFile(args[i]);
                } else {
                    File file = new File(args[i]);
                    if (file.isDirectory()) {
                        ArrayList list = new ArrayList();
                        getSourceFiles(list, file);
                        for (int j = 0; j < list.size(); j++) {
                            File temp = new File((String) list.get(j));
                            String filename = temp.getAbsolutePath();
                            String checkedfor = "classes";
                            int index = filename.indexOf(checkedfor);
                            if (index > 0) {
                                filename = filename.substring(index + checkedfor.length()+1);
                            }
                            filename = filename.replace('\\','/');
                            JARFileEntry newEntry =
                                    new JARFileEntry(filename,
                                            new FileInputStream(temp));
                            jfile.addJarEntry(newEntry);
                        }
                    }
                }
            }
            log.info("jar file creation done ");
            jfile.createNewJarFile();
        } catch (IOException e) {
            log.error(e);
            throw GenerationFault.createGenerationFault(e);
        }
    }

    public static void main(String[] args) throws GenerationFault {
        Packager packger = new Packager(args);
        packger.createJar();
    }

    private void getSourceFiles(ArrayList list, File location) {
        String[] dirs = location.list();
        if (dirs == null)
            return;
        for (int i = 0; i < dirs.length; i++) {
            String filename = location.getAbsolutePath() + "/" + dirs[i];
            File file = new File(filename);
            if (file.isFile()) {
                list.add(filename);
            } else {
                getSourceFiles(list, new File(filename));
            }
        }
    }

}
