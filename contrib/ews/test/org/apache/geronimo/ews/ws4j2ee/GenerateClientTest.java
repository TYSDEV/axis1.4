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

import junit.framework.Test;
import junit.framework.TestSuite;

import org.apache.geronimo.ews.AbstractTestCase;
import org.apache.geronimo.ews.ws4j2ee.toWs.Ws4J2EEClientwithWSDL;

/**
 * Unit test for simple App.
 *
 * @author <a href="mailto:jason@zenplex.com">Jason van Zyl</a>
 */
public class GenerateClientTest 
	extends AbstractTestCase
{
	private String outDir = "target/generated/samples/";
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public GenerateClientTest( String testName )
	{
		super( testName );
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite()
	{
		return new TestSuite( GenerateClientTest.class );
	}
	public void testDummy() {}
	
	public void testBookSample() throws Exception
	{
		//client side
		String args[] = new String[]{getTestFile(sampleDir + "jaxrpc/book/webserviceClient.xml"),
									 "-o" + getTestFile(outDir+"withWSDL/client/book/"),"-Euse-remote"};
		Ws4J2EEClientwithWSDL.main(args);
	}

	public void testTimeSample() throws Exception{
		String[] args;
		args = new String[]{getTestFile(sampleDir + "jaxrpc/time/webserviceClient.xml"),
									 "-o" + getTestFile(outDir+"withWSDL/client/time")};
		Ws4J2EEClientwithWSDL.main(args);
	}
	public void testZipSample() throws Exception{
			String args[] = new String[]{getTestFile(sampleDir + "mapper/frenchzip/webserviceClient.xml"),
										 "-o" + getTestFile(outDir+"withWSDL/client/zip")};
			Ws4J2EEClientwithWSDL.main(args);
	}
	public void testGoogleSample() throws Exception{
			String[] args; 
			args = new String[]{getTestFile(sampleDir + "mapper/google/webserviceClient.xml"),
										 "-o" + getTestFile(outDir+"withWSDL/client/google")};
			Ws4J2EEClientwithWSDL.main(args);
	}	
}
