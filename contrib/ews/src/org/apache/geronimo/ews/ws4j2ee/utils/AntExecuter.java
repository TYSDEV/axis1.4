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

package org.apache.geronimo.ews.ws4j2ee.utils;

import org.apache.geronimo.ews.ws4j2ee.context.J2EEWebServiceContext;
import org.apache.geronimo.ews.ws4j2ee.toWs.GenerationConstants;
import org.apache.geronimo.ews.ws4j2ee.toWs.GenerationFault;
import org.apache.geronimo.ews.ws4j2ee.utils.packager.ModulePackager;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Ant;
import org.apache.tools.ant.taskdefs.Javac;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Path;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.jar.JarOutputStream;

/**
 * <p>To call this Class and execute a ant task the $JAVA_HOME/lib/tool.jar need
 * to be in the class path. And so far I use the commnad line to call the Ant.
 * It should be replaced by call to the Project class.</p>
 *
 * @author hemapani
 */
public class AntExecuter {
    private J2EEWebServiceContext wscontext;
    private JarOutputStream jarFile;

    public AntExecuter(J2EEWebServiceContext wscontext) {
        this.wscontext = wscontext;
    }

    public void execute(String buildFile) throws GenerationFault {
        //wait till the ant jar added
        try {
            Class.forName("com.sun.tools.javac.Main");
            Project project = new Project();
            project.setCoreLoader(Thread.currentThread().getContextClassLoader());
            project.init();
            Ant ant = new Ant();
            ant.setProject(project);
            ant.init();
            ant.setInheritAll(true);
            ant.setInheritRefs(true);
            File file = new File(buildFile);
            ant.setAntfile(file.getAbsolutePath());
            ant.setDir(file.getParentFile());
            ant.execute();
        } catch (ClassNotFoundException e) {
            System.out.println("Ant file will not be run programatcally as the " +
                    "$JAVA_HOME/lib/tool.jar is not in the class path. To run the ant " +
                    "prgramatically add that jar to classpath");
//NOW as the code is used by Geronimo we can not afford to let the build
//failure tests beside if you use maven it works fine. it will find your maven 
//repository itself :) 
//        }catch(BuildException e){
//			System.out.println(e.getMessage() +
//			"if it is a compile error you may not have set the maven reposiroty " +
//			"directory in the conf/ws4j2ee.propertites Build fill ignore the faliure");
        }
    }

    public void execute() throws GenerationFault {
        try {
            Class.forName("com.sun.tools.javac.Main");
            File outDir = new File(wscontext.getMiscInfo().getOutPutPath());
            File dest = new File(outDir, "build/classes");
            compile(outDir, dest);
            createModule(outDir, dest);
        } catch (IOException e) {
            throw GenerationFault.createGenerationFault(e);
        } catch (ClassNotFoundException e) {
            System.out.println("Ant file will not be run programatcally as the " +
                    "$JAVA_HOME/lib/tool.jar is not in the class path. To run the ant " +
                    "prgramatically add that jar to classpath");
//NOW as the code is used by Geronimo we can not afford to let the build
//failure tests beside if you use maven it works fine. it will find your maven 
//repository itself :) 
//        }catch(BuildException e){
//          System.out.println(e.getMessage() +
//          "if it is a compile error you may not have set the maven reposiroty " +
//          "directory in the conf/ws4j2ee.propertites Build fill ignore the faliure");
        }
    }

    private void createModule(File outDir, File dest) throws IOException {
        String jarName = wscontext.getWSDLContext().getTargetPortType().getName().toLowerCase();
        int index = jarName.lastIndexOf(".");
        if (index > 0) {
            jarName = jarName.substring(index + 1);
        }
        ModulePackager module = new ModulePackager(new File(outDir, jarName + "-ewsImpl.jar"));
        module.addClassFiles(dest);
        ArrayList classpathelements = wscontext.getMiscInfo().getClasspathElements();
        if (classpathelements != null) {
            for (int i = 0; i < classpathelements.size(); i++) {
                module.addJarFile((File) classpathelements.get(i));
            }
        }
        File dir = outDir;
        File[] files = dir.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String file = files[i].getName();
                if (files[i].isFile() &&
                        !(file.endsWith(".jar") ||
                        file.endsWith(".zip") ||
                        file.endsWith(".war") ||
                        file.endsWith(".ear") ||
                        file.endsWith(".java"))
                ) {
                    module.addFileToJar(file, files[i]);
                }
            }
        }
        dir = new File(outDir, "META-INF");
        files = dir.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String file = files[i].getName();
                if (files[i].isFile() &&
                        !(file.endsWith(".jar") ||
                        file.endsWith(".zip") ||
                        file.endsWith(".war") ||
                        file.endsWith(".ear") ||
                        file.endsWith(".java"))
                ) {
                    module.addFileToJar("META-INF/" + file, files[i]);
                }
            }
        }
        module.finalizeJar();
    }

    private void compile(File outDir, File destDir) throws IOException {
        Project project = new Project();
        project.init();
        project.setCoreLoader(Thread.currentThread().getContextClassLoader());
        Javac comp = new Javac();
        comp.setProject(project);
        destDir.mkdirs();
        comp.setDestdir(destDir);
        Path path = new Path(project);
        path.setLocation(outDir);
        comp.setSrcdir(path);
        File repository = findMavenRepository();
        if (repository != null) {
            Path compileClasspath = new Path(project);
            FileSet fileset = new FileSet();
            fileset.setDir(repository);
            fileset.setIncludes("axis/**/*.jar");
            fileset.setIncludes("geronimo-spec/**/*.jar");
            fileset.setIncludes("geronimo/**/*.jar");
            fileset.setIncludes("sec/**/*.jar");
            fileset.setIncludes("dom4j/**/*.jar");
            fileset.setIncludes("jaxb-ri/**/*.jar");
            fileset.setIncludes("xerces/**/*.jar");
            fileset.setIncludes("ews/**/*.jar");
            fileset.setIncludes("openejb/**/*.jar");
            compileClasspath.addFileset(fileset);
            comp.setClasspath(compileClasspath);
        } else {
        }
        comp.init();
        comp.execute();
    }

    private File findMavenRepository() throws IOException {
        Properties pro = new Properties();
        InputStream prpertyIn = null;
        File file = new File(GenerationConstants.WS4J2EE_PROPERTY_FILE);
        if (file.exists()) {
            prpertyIn = new FileInputStream(file);
        } else {
            file = new File("modules/axis/target/" + GenerationConstants.WS4J2EE_PROPERTY_FILE);
            if (file.exists()) {
                prpertyIn = new FileInputStream(file);
            } else {
                file = new File("target/" + GenerationConstants.WS4J2EE_PROPERTY_FILE);
                if (file.exists()) {
                    prpertyIn = new FileInputStream(file);
                } else {
                    prpertyIn = getClass().getClassLoader().getResourceAsStream(GenerationConstants.WS4J2EE_PROPERTY_FILE);
                }
            }
        }
        if (prpertyIn != null) {
            String location = null;
            try {
                pro.load(prpertyIn);
                location = pro.getProperty(GenerationConstants.MAVEN_LOCAL_REPOSITARY);
            } finally {
                prpertyIn.close();
            }
            if (location != null) {
                File locationFile = new File(location);
                if (locationFile.exists()) {
                    return locationFile;
                }
            }
        }
        return null;
    }

}
