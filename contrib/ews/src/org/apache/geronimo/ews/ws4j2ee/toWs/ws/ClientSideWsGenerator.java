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

package org.apache.geronimo.ews.ws4j2ee.toWs.ws;

import org.apache.axis.wsdl.symbolTable.SymbolTable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.geronimo.ews.jaxrpcmapping.J2eeEmitter;
import org.apache.geronimo.ews.jaxrpcmapping.JaxRpcMapper;

import org.apache.geronimo.ews.ws4j2ee.context.J2EEWebServiceContext;
import org.apache.geronimo.ews.ws4j2ee.toWs.GenerationFault;
import org.apache.geronimo.ews.ws4j2ee.toWs.Generator;
import org.apache.geronimo.ews.ws4j2ee.toWs.Ws4J2eeFactory;

/**
 * <p>This genarated the Client side SEI and other classes required in the
 * Axis.</p>
 * 
 * * <h3>Service Endpoint Interface</h3>
 *	<p>The JAX-RPC specification requires that a JAX-RPC service endpoint interface must
 *	follow the following rules:</p>
 *   <ol>
 *	    <li>Service endpoint interface must extend java.rmi.Remote either directly or indirectly</li>
 *	    <li>All methods in the interface must throw java.rmi.RemoteException. Methods may
 *			throw service specific exceptions in addition to the RemoteException.</li>
 *		<li>Method parameters and return types must be the JAX-RPC supported Java types
 *			(refer to the section 5.1, �JAX-RPC Supported Java Types�). At runtime, values of a
 *			supported Java type must be serializable to and from the corresponding XML
 *	        representation.
 *	    </li>
 *	    <li>Holder classes may be used as method parameters. These Holder classes are either
 *		generated or those packaged in the standard javax.xml.rpc.holders package.</li>
 *		<li>Service endpoint interface must not include constant (as public final static)
 *		declarations. WSDL 1.1 specification does not define any standard representation for
 *		constants in a wsdl:portType definition.</li>
 *	</ol>
 * 
 * @author Srinath Perera(hemapani@opensource.lk)
 */
public class ClientSideWsGenerator implements Generator {
    private J2EEWebServiceContext j2eewscontext;
    private Ws4J2eeFactory factory;
    protected static Log log =
        LogFactory.getLog(ServerSideWsGenerator.class.getName());

    public ClientSideWsGenerator(J2EEWebServiceContext j2eewscontext) {
        this.j2eewscontext = j2eewscontext;
		factory = j2eewscontext.getFactory();
    }

    public void generate() throws GenerationFault {
        try {
            String mappingfile =
                j2eewscontext.getMiscInfo().getJaxrpcfile().fileName();
            String wsdlfile =
                j2eewscontext.getMiscInfo().getWsdlFile().fileName();

//			J2eeEmitter j2ee = new J2eeEmitter(true,
//				!j2eewscontext.getMiscInfo().isSEIExists(),
//				j2eewscontext);
            J2eeEmitter j2ee = new J2eeEmitter(j2eewscontext,new J2eeGeneratorFactory());


            if (j2eewscontext.getMiscInfo().isVerbose()) {
                log.info("wsdl file = " + wsdlfile);
                log.info("jaxrpc mapping file = " + mappingfile);
                log.info("calling the jaxrpcmapper >> ");
            }

            j2ee.setMappingFilePath(mappingfile);
            j2ee.setOutputDir(j2eewscontext.getMiscInfo().getOutPutPath());
            j2ee.setServerSide(false);
            j2ee.setVerbose(j2eewscontext.getMiscInfo().isVerbose());
            j2ee.setHelperWanted(true);
            j2ee.setTestCaseWanted(true);

            j2ee.run(wsdlfile);
            SymbolTable axisSymboltable = j2ee.getSymbolTable();
            j2eewscontext.setWSDLContext(
				factory.getContextFactory().createWSDLContext(axisSymboltable));
            JaxRpcMapper mapper = j2ee.getJaxRpcMapper();
            j2eewscontext.setJAXRPCMappingContext(
				factory.getContextFactory().createJaxRpcMapperContext(mapper, j2ee));
        } catch (Exception e) {
            e.printStackTrace();
            throw GenerationFault.createGenerationFault(e);
        }

    }

}
