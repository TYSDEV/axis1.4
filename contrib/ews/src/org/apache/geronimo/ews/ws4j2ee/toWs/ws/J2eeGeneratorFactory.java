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
import org.apache.axis.wsdl.symbolTable.SymbolTable;
import org.apache.axis.wsdl.toJava.JavaDefinitionWriter;
import org.apache.axis.wsdl.toJava.JavaUndeployWriter;
import org.apache.geronimo.ews.jaxrpcmapping.J2eeEmitter;

import javax.wsdl.Binding;
import javax.wsdl.Definition;

/**
 * @author hemapani@opensource.lk
 */
public class J2eeGeneratorFactory extends org.apache.geronimo.ews.jaxrpcmapping.J2eeGeneratorFactory {
    /**
     * 
     */
    public J2eeGeneratorFactory() {
        super();
    }

    /**
     * @param emitter
     */
    public J2eeGeneratorFactory(J2eeEmitter emitter) {
        super(emitter);
    }

    protected void addDefinitionGenerators() {
        addGenerator(Definition.class, JavaDefinitionWriter.class); // for faults
        addGenerator(Definition.class, J2eeDeployWriter.class); // for deploy.wsdd
        addGenerator(Definition.class, JavaUndeployWriter.class); // for undeploy.wsdd
    }

    public Generator getGenerator(Binding binding, SymbolTable symbolTable) {
        Generator writer = new J2eeBindingWriter(emitter, binding, symbolTable);
        BindingEntry bEntry = symbolTable.getBindingEntry(binding.getQName());
        bindingWriters.addStuff(writer, bEntry, symbolTable);
        return bindingWriters;
    } // getGenerator

}
