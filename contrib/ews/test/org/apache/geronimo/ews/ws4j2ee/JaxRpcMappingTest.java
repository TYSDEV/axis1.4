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

import java.util.List;

import javax.wsdl.Operation;
import javax.xml.namespace.QName;

import junit.framework.Assert;

import org.apache.axis.wsdl.symbolTable.BindingEntry;
import org.apache.axis.wsdl.symbolTable.PortEntry;
import org.apache.axis.wsdl.symbolTable.PortTypeEntry;
import org.apache.axis.wsdl.symbolTable.ServiceEntry;
import org.apache.geronimo.ews.AbstractTestCase;
import org.apache.geronimo.ews.jaxrpcmapping.J2eeEmitter;
import org.apache.geronimo.ews.jaxrpcmapping.JaxRpcMapper;

import org.apache.geronimo.ews.ws4j2ee.context.J2EEWebServiceContext;
import org.apache.geronimo.ews.ws4j2ee.context.JaxRpcMapperContext;
import org.apache.geronimo.ews.ws4j2ee.context.wsdl.WSDLContext;
import org.apache.geronimo.ews.ws4j2ee.toWs.Ws4J2eeFactory;
import org.apache.geronimo.ews.ws4j2ee.toWs.impl.Ws4J2eeFactoryImpl;

/**
 * @author hemapani
 */
public class JaxRpcMappingTest extends AbstractTestCase{
	private Ws4J2eeFactory factory;
    /**
     * @param testName
     */
    public JaxRpcMappingTest(String testName) {
        super(testName);
		factory = new Ws4J2eeFactoryImpl();
    }

	public void testGoogleTypeMapping() throws Exception{
	    try {
	    	    
	    	   J2EEWebServiceContext context = factory.getContextFactory().getJ2EEWsContext(true);
	    	   context.setMiscInfo(factory.getContextFactory().createMiscInfo());
	           String mappingfile = sampleDir +"mapper/google/GoogleSearch.xml";
	           String wsdlfile = sampleDir +"mapper/google/GoogleSearch.wsdl";
	           J2eeEmitter j2ee = new J2eeEmitter();
	           j2ee.setMappingFilePath(getTestFile(mappingfile));
	           j2ee.setOutputDir(outDir);
	           j2ee.setServerSide(true);
	           j2ee.setVerbose(false);
	           j2ee.setHelperWanted(true);
	           System.out.println();
	           j2ee.runServerSide(getTestFile(wsdlfile));
	           WSDLContext wscontext = factory.getContextFactory().createWSDLContext(j2ee.getSymbolTable());
			   context.setWSDLContext(wscontext);
	           PortEntry port = wscontext.getPort(new QName("GoogleSearchPort"));
	           BindingEntry be = wscontext.getBinding(new QName("urn:GoogleSearch","GoogleSearchBinding"));
	           PortTypeEntry pe = wscontext.getPortType(new QName("urn:GoogleSearch","GoogleSearchPort"));
               JaxRpcMapper mapper = j2ee.getJaxRpcMapper();
	           JaxRpcMapperContext mc =factory.getContextFactory().createJaxRpcMapperContext(mapper,j2ee);
	           context.setJAXRPCMappingContext(mc);
	           Assert.assertNotNull(port);
	           Assert.assertNotNull(be);
	           Assert.assertNotNull(pe);
	           Assert.assertEquals(mc.getJavaType(new QName("urn:GoogleSearch","GoogleSearchResult")),"org.objectweb.wssample.gen.google.MyGoogleSearchResult");
	           Assert.assertEquals(mc.getJavaType(new QName("urn:GoogleSearch","ResultElementArray")),"org.objectweb.wssample.gen.google.ResultElement[]");
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw e;
	    }
	}
	public void testBookTypeMapping() throws Exception{
	    try {
	    	
			   J2EEWebServiceContext context = factory.getContextFactory().getJ2EEWsContext(true);
			   context.setMiscInfo(factory.getContextFactory().createMiscInfo());
	           String mappingfile = sampleDir +"jaxrpc/book/BookQuote.xml";
	           String wsdlfile = sampleDir +"jaxrpc/book/BookQuote.wsdl";
	           J2eeEmitter j2ee = new J2eeEmitter();
	           j2ee.setMappingFilePath(getTestFile(mappingfile));
	           j2ee.setOutputDir(outDir);
	           j2ee.setServerSide(true);
	           j2ee.setVerbose(false);
	           j2ee.setHelperWanted(true);
	           System.out.println();
	           j2ee.runServerSide(getTestFile(wsdlfile));
	           WSDLContext wscontext = factory.getContextFactory().createWSDLContext(j2ee.getSymbolTable());
	           PortEntry port = wscontext.getPort(new QName("BookQuotePort"));
	           BindingEntry be = wscontext.getBinding(new QName("http://www.Monson-Haefel.com/jwsbook/BookQuote","BookQuoteBinding"));
	           PortTypeEntry pe = wscontext.getPortType(new QName("http://www.Monson-Haefel.com/jwsbook/BookQuote","BookQuote"));
	           ServiceEntry se = wscontext.getService(new QName("http://www.Monson-Haefel.com/jwsbook/BookQuote","BookQuoteService"));
               JaxRpcMapper mapper = j2ee.getJaxRpcMapper();
	           JaxRpcMapperContext mc =factory.getContextFactory().createJaxRpcMapperContext(mapper,j2ee);
	           Assert.assertNotNull(port);
	           Assert.assertNotNull(be);
	           Assert.assertNotNull(pe);
               System.out.println(mc.getExceptionType(new QName("http://www.Monson-Haefel.com/jwsbook/BookQuote","InvalidIsbnFault")));
	           Assert.assertEquals(mc.getExceptionType(new QName("http://www.Monson-Haefel.com/jwsbook/BookQuote","InvalidIsbnFault")),"com.jwsbook.jaxrpc.InvalidIsbnException");
	        
	           String sei = mc.getServiceEndpointInterfaceName(pe,be);
	           Assert.assertEquals(sei,"com.jwsbook.jaxrpc.BookQuote");
	           String si = mc.getServiceInterfaceName(se);
	           Assert.assertEquals(si,"com.jwsbook.jaxrpc.BookQuoteService");
	           List l = pe.getPortType().getOperations();
	           Operation op = (Operation)l.get(0);
	           Assert.assertEquals(mc.getJavaMethodName(be,op),"getBookPrice");
	           Assert.assertEquals(mc.getJavaMethodParamType(be,op,0,null),"java.lang.String");
	           Assert.assertEquals(mc.getJavaMethodReturnType(be,op),"float");  
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw e;
	    }
	}

}
