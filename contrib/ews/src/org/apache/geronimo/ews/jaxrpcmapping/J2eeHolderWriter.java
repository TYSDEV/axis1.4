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

import org.apache.axis.wsdl.symbolTable.TypeEntry;
import org.apache.axis.wsdl.toJava.JavaClassWriter;
import org.apache.axis.wsdl.toJava.Utils;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * This is Wsdl2java's Holder Writer.  It writes the <typeName>Holder.java file.
 *
 * @author Ias (iasandcb@tmax.co.kr)
 * @deprecated no more used by J2eeGeneratorFactory
 */
public class J2eeHolderWriter extends JavaClassWriter {
    private TypeEntry type;

    /**
     * Constructor.
     */
    protected J2eeHolderWriter(J2eeEmitter emitter, TypeEntry type) {
        super(emitter, Utils.holder(type, emitter), "holder");
        this.type = type;
    } // ctor

    /**
     * Return "public final ".
     */
    protected String getClassModifiers() {
        return super.getClassModifiers() + "final ";
    } // getClassModifiers

    /**
     * Return "implements javax.xml.rpc.holders.Holder ".
     */
    protected String getImplementsText() {
        return "implements javax.xml.rpc.holders.Holder ";
    } // getImplementsText

    /**
     * Generate the holder for the given complex type.
     */
    protected void writeFileBody(PrintWriter pw) throws IOException {
        String holderType = type.getName();
        pw.println("    public " + holderType + " value;");
        pw.println();
        pw.println("    public " + className + "() {");
        pw.println("    }");
        pw.println();
        pw.println("    public " + className + "(" + holderType + " value) {");
        pw.println("        this.value = value;");
        pw.println("    }");
        pw.println();
    } // writeOperation

}
