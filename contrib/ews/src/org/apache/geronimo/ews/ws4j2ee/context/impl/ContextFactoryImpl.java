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

package org.apache.geronimo.ews.ws4j2ee.context.impl;

import org.apache.axis.wsdl.symbolTable.SymbolTable;
import org.apache.geronimo.ews.jaxrpcmapping.J2eeEmitter;
import org.apache.geronimo.ews.jaxrpcmapping.JaxRpcMapper;
import org.apache.geronimo.ews.ws4j2ee.context.ContextFactory;
import org.apache.geronimo.ews.ws4j2ee.context.J2EEWebServiceContext;
import org.apache.geronimo.ews.ws4j2ee.context.JaxRpcMapperContext;
import org.apache.geronimo.ews.ws4j2ee.context.MiscInfo;
import org.apache.geronimo.ews.ws4j2ee.context.wsdl.WSDLContext;
import org.apache.geronimo.ews.ws4j2ee.context.wsdl.impl.AxisWSDLContext;
import org.apache.geronimo.ews.ws4j2ee.toWs.UnrecoverableGenerationFault;

/**
 * <p>This class decouple the concreate implementations of the
 * class from the rest of the code</p>
 *
 * @author Srinath Perera(hemapani@opensource.lk)
 */
public class ContextFactoryImpl implements ContextFactory {
    private J2EEWebServiceContext currentContext;

    public WSDLContext createWSDLContext(Object info) {
        if (info instanceof SymbolTable)
            return new AxisWSDLContext((SymbolTable) info);
        throw new UnrecoverableGenerationFault("unknown context type");
    }

    public JaxRpcMapperContext createJaxRpcMapperContext(JaxRpcMapper mapper, J2eeEmitter emitter) {
        return new JaxRpcMapperImpl(mapper, emitter);
    }

//    public WSCFContext createWSCFContext(InputStream in)
//        throws GenerationFault {
//        try {
//            return new WSCFContextImpl(in);
//        } catch (WSCFException e) {
//            e.printStackTrace();
//            throw new GenerationFault(e.getMessage());
//        }
//    }

    public MiscInfo createMiscInfo() {
        return new MiscInfoImpl();
    }

//    public static J2EEWebServiceContext getCurrentJ2EEWsContext() {
//        return currentContext;
//    }
    public J2EEWebServiceContext getJ2EEWsContext(boolean hasWSDL) {
        currentContext = new J2EEWebServiceContextImpl(hasWSDL);
        return currentContext;
    }

}
