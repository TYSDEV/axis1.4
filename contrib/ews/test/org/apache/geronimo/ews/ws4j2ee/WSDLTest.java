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

import javax.xml.namespace.QName;

import junit.framework.Assert;

import org.apache.axis.wsdl.symbolTable.BindingEntry;
import org.apache.axis.wsdl.symbolTable.PortEntry;
import org.apache.geronimo.ews.AbstractTestCase;
import org.apache.geronimo.ews.jaxrpcmapping.J2eeEmitter;
import org.apache.geronimo.ews.ws4j2ee.context.J2EEWebServiceContext;
import org.apache.geronimo.ews.ws4j2ee.context.wsdl.WSDLContext;
import org.apache.geronimo.ews.ws4j2ee.toWs.Ws4J2eeFactory;
import org.apache.geronimo.ews.ws4j2ee.toWs.impl.Ws4J2eeFactoryImpl;

/**
 * @author hemapani
 */
public class WSDLTest extends AbstractTestCase {
	private Ws4J2eeFactory factory;
    /**
     * @param testName
     */
    public WSDLTest(String testName) {
        super(testName);
		factory = new Ws4J2eeFactoryImpl();
    }

    public void testGoogleWSDL() throws Exception {
        J2EEWebServiceContext context = factory.getContextFactory().getJ2EEWsContext(true);
        context.setMiscInfo(factory.getContextFactory().createMiscInfo());
        String mappingfile = getTestFile(sampleDir + "mapper/google/GoogleSearch.xml");
        String wsdlfile = getTestFile(sampleDir + "mapper/google/GoogleSearch.wsdl");
        J2eeEmitter j2ee = new J2eeEmitter();
        j2ee.setMappingFilePath(mappingfile);
        j2ee.setOutputDir(outDir);
        j2ee.setServerSide(true);
        j2ee.setVerbose(false);
        j2ee.setHelperWanted(true);
        System.out.println();
        j2ee.runServerSide(wsdlfile);
        WSDLContext wscontext =
			factory.getContextFactory().createWSDLContext(j2ee.getSymbolTable());
        PortEntry port = wscontext.getPort(new QName("GoogleSearchPort"));
        BindingEntry be =
            wscontext.getBinding(
                new QName("urn:GoogleSearch", "GoogleSearchBinding"));

        Assert.assertEquals(wscontext.getTargetNSURI(), "urn:GoogleSearch");
        Assert.assertNotNull(port);
        Assert.assertNotNull(be);
        Assert.assertNotNull(
            wscontext.getService(
                new QName("urn:GoogleSearch", "GoogleSearchService")));
    }
}
