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
package org.apache.geronimo.ews.jaxrpcmapping;

import org.apache.axis.wsdl.gen.Generator;
import org.apache.axis.wsdl.symbolTable.ServiceEntry;
import org.apache.axis.wsdl.symbolTable.SymbolTable;

import javax.wsdl.Service;
import java.io.IOException;

/**
 * This is Wsdl2java's Service Writer.  It writes the following files, as appropriate:
 * <serviceName>.java, <serviceName>TestCase.java.
 *
 * @author Ias (iasandcb@tmax.co.kr)
 * @deprecated no more used by J2eeGeneratorFactory
 */
public class J2eeServiceWriter implements Generator {
    protected Generator serviceIfaceWriter = null;
    protected Generator serviceImplWriter = null;
    protected Generator testCaseWriter = null;

    /**
     * Constructor.
     */
    public J2eeServiceWriter(J2eeEmitter emitter,
                             Service service,
                             SymbolTable symbolTable) {
        ServiceEntry sEntry = symbolTable.getServiceEntry(service.getQName());
        if (sEntry.isReferenced()) {
            serviceIfaceWriter =
                    new J2eeServiceIfaceWriter(emitter, sEntry, symbolTable);
            serviceImplWriter =
                    new J2eeServiceImplWriter(emitter, sEntry, symbolTable);
            if (emitter.isTestCaseWanted()) {
                testCaseWriter =
                        new J2eeTestCaseWriter(emitter, sEntry, symbolTable);
            }
        }
    } // ctor

    /**
     * Write all the service bindnigs:  service and testcase.
     */
    public void generate() throws IOException {
        if (serviceIfaceWriter != null) {
            serviceIfaceWriter.generate();
        }
        if (serviceImplWriter != null) {
            serviceImplWriter.generate();
        }
        if (testCaseWriter != null) {
            testCaseWriter.generate();
        }
    } // generate

}
