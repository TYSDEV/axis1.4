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

package org.apache.geronimo.ews.ws4j2ee.context;

import org.apache.geronimo.ews.jaxrpcmapping.J2eeEmitter;
import org.apache.geronimo.ews.jaxrpcmapping.JaxRpcMapper;
import org.apache.geronimo.ews.ws4j2ee.context.wsdl.WSDLContext;

/**
 * <p>This class decouple the concreate implementations of the
 * class from the rest of the code</p>
 *
 * @author Srinath Perera(hemapani@opensource.lk)
 */
public interface ContextFactory {

    public WSDLContext createWSDLContext(Object info);

    public JaxRpcMapperContext createJaxRpcMapperContext(JaxRpcMapper mapper, J2eeEmitter emitter);

//    public WSCFContext createWSCFContext(InputStream in)throws GenerationFault;

    public MiscInfo createMiscInfo();

    public J2EEWebServiceContext getJ2EEWsContext(boolean hasWSDL);

//    public InputOutputFile getInputFile(
//        String fileName,
//        InputStream instream) ;
//    
//	public  InputOutputFile getInputFile(
//		String fileName) throws GenerationFault ;
//	public  InputOutputFile getInputFile(
//		InputStream instream);
}
