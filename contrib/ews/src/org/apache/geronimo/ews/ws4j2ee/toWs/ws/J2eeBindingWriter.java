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

import org.apache.axis.wsdl.gen.Generator;
import org.apache.axis.wsdl.symbolTable.BindingEntry;
import org.apache.axis.wsdl.symbolTable.PortTypeEntry;
import org.apache.axis.wsdl.symbolTable.SymbolTable;
import org.apache.axis.wsdl.toJava.Emitter;
import org.apache.axis.wsdl.toJava.JavaBindingWriter;
import org.apache.geronimo.ews.jaxrpcmapping.J2eeEmitter;

import javax.wsdl.Binding;

/**
 * @author hemapani@opensource.lk
 */
public class J2eeBindingWriter extends JavaBindingWriter {
    /**
     * @param arg0
     * @param arg1
     * @param arg2
     */
    public J2eeBindingWriter(Emitter arg0, Binding arg1, SymbolTable arg2) {
        super(arg0, arg1, arg2);
    }

    protected Generator getJavaInterfaceWriter(J2eeEmitter emitter,
                                               PortTypeEntry ptEntry,
                                               BindingEntry bEntry,
                                               SymbolTable st) {
        //TODO Do we need to generate the SEI sometimes ??
        return null;
    }

    protected Generator getJavaImplWriter(J2eeEmitter emitter,
                                          BindingEntry bEntry,
                                          SymbolTable st) {
        return null;
    }
}
