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

package org.apache.geronimo.ews.ws4j2ee.seviceRef;

import java.io.FileInputStream;

import junit.framework.Assert;

import org.apache.geronimo.ews.AbstractTestCase;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.client.interfaces.ServiceReferanceContext;
import org.apache.geronimo.ews.ws4j2ee.parsers.ServiceReferanceParser;

/**
 * @author hemapani
 */
public class ServiceRefTest extends AbstractTestCase{
	/**
	 * @param testName
	 */
	public ServiceRefTest(String testName) {
		super(testName);
	}

	public void testRefFile1() throws Exception{
		try {
			ServiceReferanceParser parser 
				= new ServiceReferanceParser(new FileInputStream(
					getTestFile(testDir+"org/apache/geronimo/ews/" +
						"ws4j2ee/seviceRef/service-ref1.xml")));
			ServiceReferanceContext ref = 	parser.getRef();
			Assert.assertEquals("service/Joe",ref.getServicerefName());
			Assert.assertEquals("javax.xml.rpc.Service",ref.getServiceInterface());
			Assert.assertEquals("WEB-INF/joe.xml",ref.getJaxrpcmappingFile());
			Assert.assertEquals("WEB-INF/joe.wsdl",ref.getWsdlFile());
		} catch (Exception e) {
			e.printStackTrace();
		   throw e;
		}
	}
	public void testRefFile2() throws Exception{
		try {
			ServiceReferanceParser parser 
				= new ServiceReferanceParser(new FileInputStream(
					getTestFile(testDir+"org/apache/geronimo/ews/ws4j2ee/" +
						"seviceRef/service-ref2.xml")));
			ServiceReferanceContext ref = 	parser.getRef();
			Assert.assertEquals("service/Joe",ref.getServicerefName());
			Assert.assertEquals("javax.xml.rpc.Service",ref.getServiceInterface());

			Assert.assertEquals("WEB-INF/joe.xml",ref.getJaxrpcmappingFile());
			Assert.assertEquals("WEB-INF/joe.wsdl",ref.getWsdlFile());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
