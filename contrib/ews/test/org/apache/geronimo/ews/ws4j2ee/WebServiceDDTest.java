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

import java.io.FileInputStream;

import junit.framework.Assert;

import org.apache.geronimo.ews.AbstractTestCase;
import org.apache.geronimo.ews.ws4j2ee.context.J2EEWebServiceContext;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFContext;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFHandler;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFPortComponent;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFWebserviceDescription;
import org.apache.geronimo.ews.ws4j2ee.toWs.Ws4J2eeFactory;
import org.apache.geronimo.ews.ws4j2ee.toWs.impl.Ws4J2eeFactoryImpl;

import testData.MockWs4J2eeContext;

/**
 * @author hemapani
 */
public class WebServiceDDTest extends AbstractTestCase{
	private Ws4J2eeFactory factory;
	private J2EEWebServiceContext context;
    /**
     * @param testName
     */
    public WebServiceDDTest(String testName) {
        super(testName);
		factory = new Ws4J2eeFactoryImpl();
    }

	public void testGoogleDD() throws Exception{
		context = new MockWs4J2eeContext();
		WSCFContext wscfcontext 
			= factory.getParserFactory().parseWSCF(context,
				new FileInputStream(getTestFile(sampleDir 
				+ "mapper/google/webservices.xml")));
		Assert.assertNull(wscfcontext.getDescription());
		Assert.assertNull(wscfcontext.getLargeIcon());
		Assert.assertNull(wscfcontext.getSmallIcon());
		Assert.assertEquals(wscfcontext.getDisplayName(),"Google Serach");	
		WSCFWebserviceDescription[] wsarray = wscfcontext.getWebServicesDescription();
		Assert.assertNotNull(wsarray);	
		Assert.assertTrue(wsarray.length > 0);
		WSCFWebserviceDescription wsdes = wsarray[0];
		Assert.assertEquals(wsdes.getWebserviceDescriptionName(),"Google Serach WebService");
		Assert.assertEquals(wsdes.getWsdlFile(),"GoogleSearch.wsdl");
		Assert.assertNull(wsdes.getDisplayName());
		Assert.assertNull(wsdes.getLargeIcon());
		Assert.assertNull(wsdes.getSmallIcon());
		Assert.assertNull(wsdes.getDescription());
		Assert.assertEquals(wsdes.getJaxrpcMappingFile(),"GoogleSearch.xml");
		WSCFPortComponent[] pc = wsdes.getPortComponent();
		Assert.assertNotNull(pc);
		Assert.assertTrue(pc.length > 0);
		WSCFPortComponent tpc = pc[0];
		Assert.assertEquals(tpc.getDescription(),"port component description");
		Assert.assertEquals(tpc.getPortComponentName(),"GoogleSearchPort");
		Assert.assertNull(tpc.getDisplayName());
		Assert.assertNull(tpc.getLargeIcon());
		Assert.assertNull(tpc.getSmallIcon());
		Assert.assertNull(tpc.getDisplayName());
		Assert.assertEquals(tpc.getServiceEndpointInterface(),"org.objectweb.wssample.gen.google.GoogleSearchPort");
		Assert.assertEquals(tpc.getServiceImplBean().getEjblink(),"GoogleBean");
		Assert.assertEquals(tpc.getWsdlPort().getNamespaceURI(),"urn:GoogleSearch");
		Assert.assertEquals(tpc.getWsdlPort().getLocalpart(),"GoogleSearchPort");
		
	}
	
	public void testHandlerDD() throws Exception{
		WSCFContext wscfcontext 
			= factory.getParserFactory().parseWSCF(context,
				new FileInputStream(
					getTestFile(testDir + "testData/math/webservice-withHandler.xml")));
		WSCFWebserviceDescription[] wsarray = wscfcontext.getWebServicesDescription();
		Assert.assertNotNull(wsarray);	
		Assert.assertTrue(wsarray.length > 0);
		WSCFWebserviceDescription wsdes = wsarray[0];
		WSCFPortComponent[] pc = wsdes.getPortComponent();
		Assert.assertNotNull(pc);
		Assert.assertTrue(pc.length > 0);
		WSCFPortComponent tpc = pc[0];
		WSCFHandler[] handlers = tpc.getHandlers();
		Assert.assertNotNull(handlers);
		Assert.assertTrue(handlers.length == 2);
		WSCFHandler h1 = handlers[0];
		Assert.assertEquals("sample.ValidationHandler",h1.getHandlerName());
		Assert.assertEquals("sample.ValidationHandler",h1.getHandlerClass());
		
		WSCFHandler h2 = handlers[1];
		Assert.assertEquals("sample.LoggingHandler",h2.getHandlerName());
		Assert.assertEquals("sample.LoggingHandler",h2.getHandlerClass());
		
	}

}
