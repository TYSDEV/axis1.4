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
 
package org.apache.geronimo.ews.ws4j2ee.toWs.misc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.geronimo.ews.ws4j2ee.context.InputOutputFile;
import org.apache.geronimo.ews.ws4j2ee.context.J2EEWebServiceContext;
import org.apache.geronimo.ews.ws4j2ee.toWs.GenerationConstants;
import org.apache.geronimo.ews.ws4j2ee.toWs.GenerationFault;
import org.apache.geronimo.ews.ws4j2ee.toWs.Generator;
import org.apache.geronimo.ews.ws4j2ee.toWs.dd.JaxrpcMapperGenerator;

/**
 * @author Srinath Perera(hemapani@opensource.lk)
 */
public class BuildFileGenerator implements Generator {
	private J2EEWebServiceContext j2eewscontext;

	protected static Log log =
			LogFactory.getLog(JaxrpcMapperGenerator.class.getName());

	public BuildFileGenerator(J2EEWebServiceContext j2eewscontext) throws GenerationFault {
		this.j2eewscontext = j2eewscontext;
	}

	public void generate() throws GenerationFault {
		try {
			String buildfile = j2eewscontext.getMiscInfo().getOutPutPath() + "/build.xml";
			if(j2eewscontext.getMiscInfo().isVerbose())
				log.info("genarating " + buildfile + ".................");
            
			PrintWriter out = new PrintWriter(new FileWriter(buildfile));

			out.write("<?xml version=\"1.0\"?>\n");

			out.write("<project basedir=\".\" default=\"dist\">\n");
			out.write("	<property name=\"build.sysclasspath\" value=\"last\"/>\n");
			out.write("	<property name=\"src\" location=\".\"/>\n");
			out.write("	<property name=\"build\" location=\"build\"/>\n");
			out.write("	<property name=\"build.classes\" location=\"${build}/classes\"/>\n");
			out.write("	<property name=\"build.lib\" location=\"${build}/lib\"/>\n");
			out.write("	<property name=\"lib\" location=\"lib\"/>\n");
			
			Properties pro = new Properties();
			InputStream prpertyIn = null;
			File file = new File(GenerationConstants.WS4J2EE_PROPERTY_FILE);
			if(file.exists()){
				prpertyIn = new FileInputStream(file);
			}else{
				file = new File("modules/axis/target/"+GenerationConstants.WS4J2EE_PROPERTY_FILE);
				if(file.exists()){
					prpertyIn = new FileInputStream(file);
				}else{
					file = new File("target/"+GenerationConstants.WS4J2EE_PROPERTY_FILE);
					if(file.exists()){
						prpertyIn = new FileInputStream(file);
					}else{
						prpertyIn = getClass().getClassLoader().getResourceAsStream(GenerationConstants.WS4J2EE_PROPERTY_FILE);
					}				
				}
			}
			if(prpertyIn!= null){
				String location = null;
				try{
					pro.load(prpertyIn);
					location = pro.getProperty(GenerationConstants.MAVEN_LOCAL_REPOSITARY);
				}finally{
					prpertyIn.close();
				}
				if(location != null){
					out.write("	<property name=\"maven.repo.local\" location=\"" +location +"\"/>\n");
                    if(!(new File(location)).exists()){
                        j2eewscontext.getMiscInfo().setCompile(false);
                    }
                    
				}else{
					prpertyIn = null;
				}

			}else{
				System.out.println("property file not found");
			}
			//out.write("	<property file=\"ws4j2ee.properties\"/>\n");

			out.write("	<path id=\"classpath\" >\n");

            File tempfile = null;
            if(file != null) {
                tempfile = new File(file.getParent(), "classes");
            }
            if(tempfile == null) {
                tempfile = new File("./target/classes");
            }

			out.write("		<pathelement location=\"/"+tempfile.getAbsolutePath()+"\"/>");
			tempfile = new File("target/test-classes");
			out.write("		<pathelement location=\"/"+tempfile.getAbsolutePath()+"\"/>");
			
			ArrayList classpathelements = j2eewscontext.getMiscInfo().getClasspathElements();
			if(classpathelements != null){
				for(int i = 0;i<classpathelements.size();i++){
					out.write("		<pathelement location=\"" 
						+ ((File)classpathelements.get(i)).getAbsolutePath() + "\"/>\n");				
				}
			}
			if(prpertyIn!= null){
				out.write("		<fileset dir=\"${maven.repo.local}\">\n");
				out.write("		    <include name=\"axis/**/*.jar\"/>\n");
				out.write("			<include name=\"geronimo-spec/**/*.jar\"/>\n");
				out.write("			<include name=\"geronimo/**/*.jar\"/>\n");
				out.write("			<include name=\"sec/**/*.jar\"/>\n");
				out.write("			<include name=\"dom4j/**/*.jar\"/>\n");
				out.write("			<include name=\"jaxb-ri/**/*.jar\"/>\n");
				out.write("			<include name=\"xerces/**/*.jar\"/>\n");
                out.write("         <include name=\"ews/**/*.jar\"/>\n");
                out.write("         <include name=\"openejb/**/*.jar\"/>\n");
				out.write("		</fileset>\n");
			}
			StringTokenizer tok = getClasspathComponets();
			while (tok.hasMoreTokens()) {
				out.write("		<pathelement location=\"" + tok.nextToken() + "\"/>\n");
			}
			out.write("	</path>\n");


			out.write("	<target name=\"compile\">\n");
			out.write("	   <mkdir dir=\"${build.classes}\"/>\n");
            out.write("    <delete>\n");
            out.write("         <fileset dir=\"${build.classes}\">\n");
            out.write("             <include name=\"**\"/>\n");
            out.write("         </fileset>\n");
            out.write("    </delete>\n");

			out.write("	   <mkdir dir=\"${build.lib}\"/>\n");
			out.write("		<javac destdir=\"${build.classes}\" source=\"1.3\" debug=\"on\">\n");
			out.write("			<classpath refid=\"classpath\" />\n");
			out.write("			<src path=\"${src}\"/>\n");
			out.write("		</javac>\n");
			out.write("	</target>\n");

			out.write("	<target name=\"jar\" depends=\"compile\">\n");
			out.write("		<mkdir dir=\"${build.classes}/META-INF/\"/>\n");
	
			writeFileCopyStatement(j2eewscontext.getMiscInfo().getJaxrpcfile(),out);
			writeFileCopyStatement(j2eewscontext.getMiscInfo().getWsdlFile(),out);
			writeFileCopyStatement(j2eewscontext.getMiscInfo().getWsconffile(),out);
			
//			if(j2eewscontext.getMiscInfo().isImplwithEJB()){
//				File ejbDD = 	new File(j2eewscontext.getMiscInfo().getOutPutPath()+"/META-INF/ejb-jar.xml");
//				if(ejbDD.exists())
//					out.write("		<copy file =\""+ejbDD.getAbsolutePath()+"\" todir=\"${build.classes}/META-INF\"/>\n");
//				
//				File contianerDD = null;	
//				if(GenerationConstants.JBOSS_CONTAINER.equals(j2eewscontext.getMiscInfo().getTargetJ2EEContainer())){
//					contianerDD = new File(j2eewscontext.getMiscInfo().getOutPutPath()+"/META-INF/"+GenerationConstants.JBOSS_DD);
//				}else if(GenerationConstants.JONAS_CONTAINER.equals(j2eewscontext.getMiscInfo().getTargetJ2EEContainer())){
//					contianerDD = new File(j2eewscontext.getMiscInfo().getOutPutPath()+"/META-INF/"+GenerationConstants.JONAS_DD);
//				}else if(GenerationConstants.GERONIMO_CONTAINER.equals(j2eewscontext.getMiscInfo().getTargetJ2EEContainer())){
//					contianerDD = new File(j2eewscontext.getMiscInfo().getOutPutPath()+"/META-INF/"+GenerationConstants.GERONIMO_DD);
//				}	
//				if(contianerDD.exists()){
//						out.write("		<copy file =\""+contianerDD.getAbsolutePath()+"\" todir=\"${build.classes}/META-INF\"/>\n ");
//				}
//				
//									
//			}else{
//				File file = new File(j2eewscontext.getMiscInfo().getOutPutPath()+"/WEB-INF/web.xml");
//				if(file.exists()){
//					out.write("		<copy file =\"${src}/WEB-INF/web.xml\" todir=\"${build.classes}/META-INF\"/>\n ");				
//				}
//			}
			out.write("		<copy todir=\"${build.classes}\">\n");
			out.write("			<fileset dir=\"${src}\">\n");
            out.write("             <include name=\"*.properties\"/>\n");
			out.write("				<include name=\"META-INF/*.xml\"/>\n");
			out.write("				<include name=\"WEB-INF/*.xml\"/>\n");
			out.write("				<include name=\"META-INF/*.wsdl\"/>\n");
			out.write("				<include name=\"META-INF/*.wsdd\"/>\n");
            out.write("             <include name=\"WEB-INF/*.wsdl\"/>\n");
            out.write("             <include name=\"WEB-INF/*.wsdd\"/>\n");
            out.write("             <include name=\"*.wsdl\"/>\n");
            out.write("             <include name=\"*.wsdd\"/>\n");
            out.write("             <exclude name=\"build**\"/>\n");
			out.write("			</fileset>\n");
			out.write("		</copy>\n");



			String jarName = j2eewscontext.getWSDLContext().getTargetPortType().getName().toLowerCase();
			int index = jarName.lastIndexOf(".");
			if(index>0){
				jarName = jarName.substring(index+1);
			} 
			
			String finalJarFile = j2eewscontext.getMiscInfo().getOutPutPath() + "/"+ jarName + "-ewsimpl.jar";
			File jarFile = new File(finalJarFile);
			String tempFile = "${build}/lib/"+jarName + "-temp.jar";
            
			out.write("		<jar jarfile=\""+ tempFile + "\" basedir=\"${build.classes}\" >\n");
			out.write("		<include name=\"**\" />\n");
			out.write("		<manifest>\n");
			out.write("			<section name=\"org/apache/ws4j2ee\">\n");
			out.write("			<attribute name=\"Implementation-Title\" value=\"Apache jsr109 impl\"/>\n");
			out.write("			<attribute name=\"Implementation-Vendor\" value=\"Apache Web Services\"/>\n");
			out.write("			</section>\n");
			out.write("		</manifest>\n");
			out.write("		</jar>\n");
			
			out.write("     <java classname=\"org.apache.geronimo.ews.ws4j2ee.utils.packager.Packager\" fork=\"no\" >\n");
			out.write("     	<arg value=\""+jarFile.getAbsolutePath()+"\"/>\n");
			out.write("     	<arg value=\""	+ tempFile + "\"/>\n");
			out.write("     	<classpath refid=\"classpath\" />\n");
			for(int i = 0;i<classpathelements.size();i++){
				out.write("     	<arg value=\""
					+ ((File)classpathelements.get(i)).getAbsolutePath() + "\"/>\n");				
			}
			out.write("     </java>\n");
//			out.write("		<delete dir=\"${build}\"/>\n");
			out.write("	</target>\n");


			out.write("	<target name=\"dist\" depends=\"jar\"/>\n  ");

			out.write("	<target name=\"clean\">\n");
			out.write("		<delete dir=\"${build}\"/>\n");
			out.write("	</target>\n");
			
			
//			out.write("	<target name=\"deploy\" depends=\"jar\">\n");
//			out.write("		<copy file=\""+ jarName + 
//				".jar\" todir=\"${axis-webapps-lib}\"/>\n");
//				out.write("		<copy file=\""+ jarName + 
//					".jar\" todir=\"${ejb-deploy}\"/>\n");
//				
//			out.write("		<java classname=\"org.apache.axis.client.AdminClient\" fork=\"no\" >\n");
//			out.write("			<classpath refid=\"classpath\" />\n");
//
//			out.write("			<arg value=\"-h\"/>\n");
//			out.write("			<arg value=\"axis-host\"/>\n");
//			out.write("			<arg value=\"-p\"/>\n");
//			out.write("			<arg value=\"axis-port\"/>\n"); 
//			out.write("			<arg value=\"deploy.wsdd\"/>\n");
//			out.write("		</java>\n");
//			out.write("	</target>\n");
			
			out.write("</project>\n");
			out.close();
		} catch (IOException e) {
			throw GenerationFault.createGenerationFault(e);
		}

	}

	private StringTokenizer getClasspathComponets() {
		String classpath = System.getProperty("java.class.path");
		String spearator = System.getProperties().getProperty("path.separator");
		return new StringTokenizer(classpath, spearator);
	}
	
	private void writeFileCopyStatement(InputOutputFile file,PrintWriter out) throws GenerationFault{
		try{
			if(file != null){
				String fileName = file.fileName();
				if(fileName != null){
					File absFile = new File(fileName);
					if(absFile.exists())
						out.write("		<copy file =\""+absFile.getAbsolutePath()+"\" todir=\"${build.classes}/META-INF\"/>\n");
				} 
			}
		}catch(Exception e){}
	}
}
