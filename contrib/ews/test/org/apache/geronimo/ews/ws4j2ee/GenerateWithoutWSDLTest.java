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

import org.apache.geronimo.ews.AbstractTestCase;
import org.apache.geronimo.ews.ws4j2ee.toWs.Ws4J2ee;

/**
 * this test case with wsdl does not exists .. but the information is get by the 
 * SEI. This should be the prefered way for the J2EE developer. User can give a 
 * packaged application as explained by the JSR109 specification to the tool.
 * the arguments are 
 * 	GenerateWithoutWSDL &lt;webservice.xml-file&gt; -o&lt;targetoutput&gt; &lt;additionl argument that are given toJava2WSDL&gt;   
 * @author Srinath Perera(hemapani@opensource.lk)
 */
public class GenerateWithoutWSDLTest extends AbstractTestCase {
	private String outDir = "target/generated/samples/";
    /**
     * @param testName
     */
    public GenerateWithoutWSDLTest(String testName) {
        super(testName);
    }

	public void testMathSample() throws Exception{
		try{
			String[] args2 = new String[]{
				getTestFile(testDir+"testData/math/webservice.xml"),"-o",
				outDir+"withoutWSDL/math/server" ,"-l" ,
				"http://127.0.0.1/aixs/math"};
			Ws4J2ee.main(args2);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void testServletBasedMathSample() throws Exception{
		String[] args2 = new String[]{
			getTestFile(testDir + "testData/math/webservice-ServletBased.xml"),"-o",
			outDir+"withoutWSDL/math/server-servlet" ,"-l" ,
			"http://127.0.0.1/aixs/math"};
		Ws4J2ee.main(args2);
	}
	public void testMathSampleWithHandlers() throws Exception{
		try{
			String[] args2 = new String[]{
				getTestFile(testDir + "testData/math/webservice-withHandler.xml"),"-o",
				outDir+"withoutWSDL/math-withHandlers/server" ,"-l" ,
				"http://127.0.0.1/aixs/math"};
			Ws4J2ee.main(args2);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void testBookSampleJar() throws Exception{
		String[] args2 = new String[]{
			getTestFile("target/generated/samples/bookquote.jar"),"-o"
			,outDir+"withoutWSDL/bookquote-jar/"};
		Ws4J2ee.main(args2);
	}
	public void testGoogleSampleJar() throws Exception{
		String[] args2 = new String[]{
			getTestFile("target/generated/samples/google.jar"),"-o",
			outDir+"withoutWSDL/google-jar/"};
		Ws4J2ee.main(args2);
	}
	public void testTimeSampleJar() throws Exception{
		String[] args2 = new String[]{getTestFile("target/generated/samples/time.jar"),"-o",
			outDir+"withoutWSDL/time-jar/"};
		Ws4J2ee.main(args2);
	}
	public void testZipampleJar() throws Exception{
		String[] args2 = new String[]{getTestFile("target/generated/samples/zip.jar"),"-o",
			outDir+"withoutWSDL/zip-jar/"};
		Ws4J2ee.main(args2);
	}
	
	public void testBookSampleWar() throws Exception{
		String[] args2 = new String[]{getTestFile("target/generated/samples/simple.war"),"-o",
			outDir+"withoutWSDL/bookquote-war/"};
		Ws4J2ee.main(args2);
	}
	public void testBookSampleEar() throws Exception{
		String[] args2 = new String[]{
				getTestFile("target/generated/samples/bookquote.ear"),"-o",
				outDir+"withoutWSDL/bookquote-ear/"};
		Ws4J2ee.main(args2);
	}
	
	public void testEchoJar() throws Exception{
		String[] args2 = new String[]{
				getTestFile("target/generated/samples/echo.jar"),"-o",
				outDir+"withoutWSDL/echo-jar/"};
		Ws4J2ee.main(args2);
	}
    public void testEchoWar() throws Exception{
//fix this once ohters fixed 
//        String[] args2 = new String[]{
//                getTestFile("target/generated/samples/echo.war"),"-o",
//                outDir+"withoutWSDL/echo-war/"};
//        Ws4J2ee.main(args2);
    }
}
