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

package org.apache.geronimo.ews.ws4j2ee.toWs;

import java.io.FileInputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.geronimo.ews.ws4j2ee.context.ContextValidator;
import org.apache.geronimo.ews.ws4j2ee.context.J2EEWebServiceContext;
import org.apache.geronimo.ews.ws4j2ee.context.MiscInfo;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.client.interfaces.ServiceReferanceContext;
import org.apache.geronimo.ews.ws4j2ee.toWs.impl.Ws4J2eeFactoryImpl;
import org.apache.geronimo.ews.ws4j2ee.utils.MiscFactory;
import org.apache.geronimo.ews.ws4j2ee.utils.Utils;

/**
 * <p>this class genarate the code when the WSDL presents.</p>
 */
public class Ws4J2EEClientwithWSDL implements Generator {
	private String wsConfFileLocation;
    private boolean verbose = false;
    private Ws4J2eeCLOptionParser clparser;
	private Ws4J2eeFactory factory;
    protected static Log log =
            LogFactory.getLog(Ws4J2EEClientwithWSDL.class.getName());
     


    private MiscInfo misc;

    public Ws4J2EEClientwithWSDL(String[] args) throws Exception {
        clparser = new Ws4J2eeCLOptionParser(args);
        
    }

    /**
     * genarate. what is genarated is depend on genarators included.
     * 
     * @see org.apache.geronimo.ews.ws4j2ee.toWs.Generator#genarate()
     */
    public void generate() throws GenerationFault {
        try {
			factory = new Ws4J2eeFactoryImpl(); 
			J2EEWebServiceContext wscontext = factory.getContextFactory().getJ2EEWsContext(true);
			wscontext.setFactory(factory);			
			misc = factory.getContextFactory().createMiscInfo();

			wscontext.setMiscInfo(misc);

            String wscfClientfile = clparser.getWscffile();
			misc.setWsconffile(MiscFactory.getInputFile(wscfClientfile));
            misc.setOutputPath(clparser.getOutputDirectory());
			wsConfFileLocation = Utils.getRootDirOfFile(wscfClientfile);
			wscontext.getMiscInfo().setImplStyle(clparser.getImplStyle());
			wscontext.getMiscInfo().setTargetJ2EEContainer(clparser.getContanier());
            
            
            //parsing of the webservices.xml happen here 
            ServiceReferanceContext serviceContext = 
            	factory.getParserFactory().parseServiceReferance(wscontext,
            		new FileInputStream(wscfClientfile));
            if (verbose){
				log.info(wscfClientfile + " parsed ..");
				log.info(serviceContext.getJaxrpcmappingFile());       
				log.info(serviceContext.getWsdlFile());
           }

            wscontext.getMiscInfo().setJaxrpcfile(
				MiscFactory.getInputFile(
					Utils.getAbsolutePath(serviceContext.getJaxrpcmappingFile(),
					wsConfFileLocation)));
            wscontext.getMiscInfo().setWsdlFile(
				MiscFactory.getInputFile(
            		Utils.getAbsolutePath(serviceContext.getWsdlFile(), 
					wsConfFileLocation)));
			wscontext.getMiscInfo().setVerbose(verbose);
			wscontext.getMiscInfo().setHandlers(serviceContext.getHandlers()); 
			
            //JAX-RPC mapper calling
            if (verbose)
                log.info("starting client side code genaration .. ");
			Generator clientStubGen = factory.getGenerationFactory().createClientSideWsGenerator(wscontext);
			clientStubGen.generate();
			ContextValidator cvalidator = new ContextValidator(wscontext);
			//cvalidator.validateWithWSDL();
			Generator handlerGen = factory.getGenerationFactory().createHandlerGenerator(wscontext);
			handlerGen.generate();
        } catch (Exception e) {
            e.printStackTrace();
            throw GenerationFault.createGenerationFault(e);
        }
    }


    public static void main(String[] args) throws Exception {
        Ws4J2EEClientwithWSDL gen = new Ws4J2EEClientwithWSDL(args);
        gen.generate();
    }
}
