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

package org.apache.geronimo.ews.ws4j2ee.toWs.wsdl;

import org.apache.axis.utils.ClassUtils;
import org.apache.axis.wsdl.Java2WSDL;
import org.apache.axis.wsdl.fromJava.Emitter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.geronimo.ews.ws4j2ee.context.ContextValidator;
import org.apache.geronimo.ews.ws4j2ee.context.J2EEWebServiceContext;
import org.apache.geronimo.ews.ws4j2ee.context.impl.AxisEmitterBasedJaxRpcMapperContext;
import org.apache.geronimo.ews.ws4j2ee.context.wsdl.impl.AxisEmitterBasedWSDLContext;
import org.apache.geronimo.ews.ws4j2ee.toWs.GenerationFault;
import org.apache.geronimo.ews.ws4j2ee.toWs.Generator;
import org.apache.geronimo.ews.ws4j2ee.toWs.Ws4J2eeDeployContext;
import org.apache.geronimo.ews.ws4j2ee.utils.Utils;

/**
 * <p>This genarated theWrapper WS required in the
 * Axis.</p>
 *
 * @author Srinath Perera(hemapani@opensource.lk)
 */
public class WSDLGenerator extends Java2WSDL implements Generator {
    private J2EEWebServiceContext wscontext;
    private Ws4J2eeDeployContext clparser;
    private Emitter emitter;
    private String wsdlFile;
    private boolean verbose;

    protected static Log log =
            LogFactory.getLog(WSDLGenerator.class.getName());

    public WSDLGenerator(J2EEWebServiceContext wscontext,
                         Emitter emitter,
                         Ws4J2eeDeployContext clparser) throws GenerationFault {
        this.wscontext = wscontext;
        this.emitter = emitter;
        this.clparser = clparser;
        this.wsdlFile = wscontext.getMiscInfo().getWsdlFile().fileName();
    }

    public void generate() throws GenerationFault {
        try {
            if (verbose)
                log.info("calling Java2WSDL to genarated wsdl ...........");
            //generate the wsdl file
            ClassUtils.setDefaultClassLoader(wscontext.getMiscInfo().getClassloader());
            emitter.setLocationUrl("http://127.0.0.1");
            emitter.setServicePortName(wscontext.getWSCFContext().getWscfport().getWsdlPort().getLocalpart());
            int mode = Emitter.MODE_ALL;
            mode = clparser.getMode();
			
            // Find the class using the name
            String seiName = wscontext.getMiscInfo().getJaxrpcSEI();
            emitter.setCls(seiName);
            // Generate a full wsdl, or interface & implementation wsdls
            Utils.prepareTheDir(wsdlFile);
            if (wsdlImplFilename == null) {
                emitter.emit(wsdlFile, mode);
            } else {
                emitter.emit(wsdlFile, wsdlImplFilename);
            }
//			//initiate the wsdlContext
            this.wscontext.setWSDLContext(new AxisEmitterBasedWSDLContext(emitter.getWSDL()));
            //parse the ejb-jar.xml here
            ContextValidator validator = new ContextValidator(wscontext);
            //initiate the jaxrpcmapping context 
            this.wscontext.setJAXRPCMappingContext(new AxisEmitterBasedJaxRpcMapperContext(emitter, wscontext));
//			//initiate the wscf context 
//			this.wscontext.setWSCFContext( new AxisEmitterBasedWSCFContext(emitter, wscontext));
            //validate the j2ee context
            validator.validateWithOutWSDL(emitter);
        } catch (Exception e) {
            throw GenerationFault.createGenerationFault(e);
        }
    }
}
