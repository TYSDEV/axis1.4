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
package org.apache.geronimo.ews.ws4j2ee;

import java.io.File;

import org.apache.geronimo.ews.AbstractTestCase;
import org.apache.geronimo.ews.ws4j2ee.utils.AntExecuter;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Ant;

/**
 * @author hemapani
 */
public class AntExecuterTest extends AbstractTestCase{
	/**
	 * @param testName
	 */
	public AntExecuterTest(String testName) {
		super(testName);
	}

	public void testBuildFile() throws Exception{
		try{
			AntExecuter exec = new AntExecuter(null);
			exec.execute(getTestFile(testDir + "testData/testBuildfile.xml"));
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}
	public void testBuildFile1() throws Exception{
		try{
			Project project = new Project();
			project.init();
			Ant ant = new Ant();
			ant.setProject(project);
			File file = new File(getTestFile(testDir
				 + "testData/testBuildfile.xml"));
			ant.setAntfile(file.getAbsolutePath());
			ant.setDir(file.getParentFile());
			ant.init();
			//ant.setOutput("ant.log");
			ant.execute();
			System.out.print("Done");
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}
    
}
