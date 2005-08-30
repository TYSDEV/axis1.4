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

import org.apache.axis.utils.Messages;
import org.apache.axis.wsdl.gen.Generator;
import org.apache.axis.wsdl.symbolTable.BindingEntry;
import org.apache.axis.wsdl.symbolTable.PortTypeEntry;
import org.apache.axis.wsdl.symbolTable.SymbolTable;
import org.apache.axis.wsdl.toJava.Utils;

import javax.wsdl.Binding;
import java.io.IOException;

/**
 * This is Wsdl2java's Binding Writer.  It writes the following files, as appropriate:
 * <bindingName>Stub.java, <bindingName>Skeleton.java, <bindingName>Impl.java.
 *
 * @author Ias (iasandcb@tmax.co.kr)
 */
public class J2eeBindingWriter implements Generator {
    protected Generator stubWriter = null;
    protected Generator skelWriter = null;
    protected Generator implWriter = null;
    protected Generator interfaceWriter = null;
    protected J2eeEmitter emitter;
    protected Binding binding;
    protected SymbolTable symbolTable;

    // This is the dynamic var key for the SEI (Service Endpoint
    // Interface) name.  This name could either be derived from
    // the portType or the binding.  The generatorPass fills
    // this dynamic var in and it is used in the writers that
    // need this SEI name.
    public static String INTERFACE_NAME = "interface name";

    /**
     * Constructor.
     */
    public J2eeBindingWriter(J2eeEmitter emitter,
                             Binding binding,
                             SymbolTable symbolTable) {
        this.emitter = emitter;
        this.binding = binding;
        this.symbolTable = symbolTable;
    } // ctor

    /**
     * getJavaInterfaceWriter
     */
    protected Generator getJavaInterfaceWriter(J2eeEmitter emitter,
                                               PortTypeEntry ptEntry,
                                               BindingEntry bEntry,
                                               SymbolTable st) {
//TODO                                                   
//		if(emitter.isSeiNeeded()){
//			return new J2eeInterfaceWriter(emitter, ptEntry, bEntry, st);
//		}else{                                        
//			return null;        	
//		}	
        return new J2eeInterfaceWriter(emitter, ptEntry, bEntry, st);
    }

    /**
     * getJavaStubWriter
     */
    protected Generator getJavaStubWriter(J2eeEmitter emitter,
                                          BindingEntry bEntry,
                                          SymbolTable st) {
        return new J2eeStubWriter(emitter, bEntry, st);
    }

    /**
     * getJavaSkelWriter
     */
    protected Generator getJavaSkelWriter(J2eeEmitter emitter,
                                          BindingEntry bEntry,
                                          SymbolTable st) {
        return new J2eeSkelWriter(emitter, bEntry, st);
    }

    /**
     * getJavaImplWriter
     */
    protected Generator getJavaImplWriter(J2eeEmitter emitter,
                                          BindingEntry bEntry,
                                          SymbolTable st) {
//		if(emitter.isUsedbyws4j2ee()){
//			return null;		
//		}else{
//			return new J2eeImplWriter(emitter, bEntry, st);		
//		}
        return new J2eeImplWriter(emitter, bEntry, st);
    }

    /**
     * Write all the binding bindings:  stub, skeleton, and impl.
     */
    public void generate() throws IOException {
        setGenerators();
        if (interfaceWriter != null) {
            interfaceWriter.generate();
        }
        if (stubWriter != null) {
            stubWriter.generate();
        }
        if (skelWriter != null) {
            skelWriter.generate();
        }
        if (implWriter != null) {
            implWriter.generate();
        }
    } // generate

    /**
     * setGenerators
     * Logic to set the generators that are based on the Binding
     * This logic was moved from the constructor so extended interfaces
     * can more effectively use the hooks.
     */
    protected void setGenerators() {
        BindingEntry bEntry = symbolTable.getBindingEntry(binding.getQName());
        
        // Interface writer
        PortTypeEntry ptEntry =
                symbolTable.getPortTypeEntry(binding.getPortType().getQName());
        if (ptEntry.isReferenced()) {
            interfaceWriter = getJavaInterfaceWriter(emitter, ptEntry, bEntry, symbolTable);
        }
        if (bEntry.isReferenced()) {
            // Stub writer
            stubWriter = getJavaStubWriter(emitter, bEntry, symbolTable);

            // Skeleton and Impl writers
            if (emitter.isServerSide()) {
                if (emitter.isSkeletonWanted()) {
                    skelWriter = getJavaSkelWriter(emitter, bEntry, symbolTable);
                }
                String fileName = Utils.getJavaLocalName(bEntry.getName())
                        + "Impl.java";
                try {
                    if (Utils.fileExists(fileName,
                            binding.getQName().getNamespaceURI(),
                            emitter.getNamespaces())) {
                        System.out.println(Messages.getMessage("wontOverwrite", fileName));
                    } else {
                        implWriter = getJavaImplWriter(emitter, bEntry, symbolTable);
                    }
                } catch (IOException ioe) {
                    System.err.println(Messages.getMessage("fileExistError00", fileName));
                }
            }
        }
    }
} // class JavaBindingWriter
