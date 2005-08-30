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
import org.apache.axis.wsdl.symbolTable.SchemaUtils;
import org.apache.axis.wsdl.symbolTable.SymTabEntry;
import org.apache.axis.wsdl.symbolTable.SymbolTable;
import org.apache.axis.wsdl.symbolTable.TypeEntry;
import org.apache.axis.wsdl.toJava.JavaGeneratorFactory;
import org.apache.axis.wsdl.toJava.JavaWriter;
import org.apache.axis.wsdl.toJava.Utils;
import org.w3c.dom.Node;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.util.Vector;

/**
 * This is Wsdl2java's Type Writer.  It writes the following files, as appropriate:
 * <typeName>.java, <typeName>Holder.java.
 *
 * @author Ias (iasandcb@tmax.co.kr)
 * @deprecated no more used by J2eeGeneratorFactory
 */
public class J2eeTypeWriter implements Generator {
    public static final String HOLDER_IS_NEEDED = "Holder is needed";

    private Generator typeWriter = null;
    private Generator holderWriter = null;

    /**
     * Constructor.
     */
    public J2eeTypeWriter(J2eeEmitter emitter,
                          TypeEntry type,
                          SymbolTable symbolTable) {
        if (type.isReferenced() && !type.isOnlyLiteralReferenced()) {
            // Determine what sort of type this is and instantiate 
            // the appropriate Writer.
            Node node = type.getNode();

            // If it's an array, don't emit a class
            if (!type.getName().endsWith("[]")) {
                // Generate the proper class for either "complex" or "enumeration" types
                Vector v = Utils.getEnumerationBaseAndValues(node, symbolTable);
                if (v != null) {
                    typeWriter = getEnumTypeWriter(emitter, type, v);
                } else {
                    TypeEntry base = SchemaUtils.getComplexElementExtensionBase(node, symbolTable);
                    if (base == null) {
                        base = SchemaUtils.getComplexElementRestrictionBase(node, symbolTable);
                    }
                    if (base == null) {
                        QName baseQName = SchemaUtils.getSimpleTypeBase(node);
                        if (baseQName != null) {
                            base = symbolTable.getType(baseQName);
                        }
                    }
                    typeWriter = getBeanWriter(emitter,
                            type,
                            SchemaUtils.getContainedElementDeclarations(node,
                                    symbolTable),
                            base,
                            SchemaUtils.getContainedAttributeTypes(node,
                                    symbolTable));
                }
            }

            // If the holder is needed (ie., something uses this type as an out or inout
            // parameter), instantiate the holder writer.
            if (holderIsNeeded(type)) {
                holderWriter = getHolderWriter(emitter, type);
            }
        }
    } // ctor

    /**
     * Write all the service bindnigs:  service and testcase.
     */
    public void generate() throws IOException {
        if (typeWriter != null) {
            typeWriter.generate();
        }
        if (holderWriter != null) {
            holderWriter.generate();
        }
    } // generate

    /**
     * Does anything use this type as an inout/out parameter?  Query the Type dynamicVar
     */
    private boolean holderIsNeeded(SymTabEntry entry) {
        Boolean holderIsNeeded =
                (Boolean) entry.getDynamicVar(HOLDER_IS_NEEDED);
        return (holderIsNeeded != null && holderIsNeeded.booleanValue());
    } // holderIsNeeded

    /**
     * getEnumWriter
     */
    protected JavaWriter getEnumTypeWriter(J2eeEmitter emitter, TypeEntry type, Vector v) {
        return new J2eeEnumTypeWriter(emitter, type, v);
    }

    /**
     * getBeanWriter
     */
    protected JavaWriter getBeanWriter(J2eeEmitter emitter, TypeEntry type,
                                       Vector elements, TypeEntry base,
                                       Vector attributes) {
        JavaWriter helperWriter = getBeanHelperWriter(emitter, type, elements, base,
                attributes);
        // If this complexType is referenced in a
        // fault context, emit a bean-like exception 
        // class
        Boolean isComplexFault = (Boolean)
                type.getDynamicVar(JavaGeneratorFactory.COMPLEX_TYPE_FAULT);
        if (isComplexFault != null &&
                isComplexFault.booleanValue()) {
            return new J2eeBeanFaultWriter(emitter, type,
                    elements, base, attributes,
                    helperWriter);
        }
        return new J2eeBeanWriter(emitter, type,
                elements, base, attributes,
                helperWriter);
    }

    /**
     * getHelperWriter
     */
    protected JavaWriter getBeanHelperWriter(J2eeEmitter emitter, TypeEntry type,
                                             Vector elements, TypeEntry base,
                                             Vector attributes) {
        return new J2eeBeanHelperWriter(emitter, type, elements, base, attributes);
    }

    /**
     * getHolderWriter
     */
    protected Generator getHolderWriter(J2eeEmitter emitter, TypeEntry type) {
        return new J2eeHolderWriter(emitter, type);
    }
}
