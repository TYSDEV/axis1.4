/*
 * The Apache Software License, Version 1.1
 *
 *
 * Copyright (c) 2001 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "Axis" and "Apache Software Foundation" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache",
 *    nor may "Apache" appear in their name, without prior written
 *    permission of the Apache Software Foundation.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 */
package org.apache.axis.wsdl.toJava;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.wsdl.Binding;
import javax.wsdl.BindingOperation;
import javax.wsdl.Fault;
import javax.wsdl.Operation;
import javax.wsdl.OperationType;
import javax.wsdl.Part;
import javax.wsdl.PortType;
import javax.xml.namespace.QName;

import javax.wsdl.extensions.soap.SOAPOperation;

import org.apache.axis.utils.JavaUtils;

import org.apache.axis.wsdl.symbolTable.BindingEntry;
import org.apache.axis.wsdl.symbolTable.CollectionType;
import org.apache.axis.wsdl.symbolTable.Element;
import org.apache.axis.wsdl.symbolTable.Parameter;
import org.apache.axis.wsdl.symbolTable.Parameters;
import org.apache.axis.wsdl.symbolTable.SymbolTable;
import org.apache.axis.wsdl.symbolTable.TypeEntry;

/**
* This is Wsdl2java's stub writer.  It writes the <BindingName>Stub.java
* file which contains the <bindingName>Stub class.
*/
public class JavaStubWriter extends JavaClassWriter {
    private BindingEntry bEntry;
    private Binding binding;
    private SymbolTable symbolTable;

    /**
     * Constructor.
     */
    protected JavaStubWriter(
            Emitter emitter,
            BindingEntry bEntry,
            SymbolTable symbolTable) {
        super(emitter, bEntry.getName() + "Stub", "stub");
        this.bEntry = bEntry;
        this.binding = bEntry.getBinding();
        this.symbolTable = symbolTable;
    } // ctor

    /**
     * Returns "extends org.apache.axis.client.Stub ".
     */
    protected String getExtendsText() {
        return "extends org.apache.axis.client.Stub ";
    } // getExtendsText

    /**
     * Returns "implements <SEI> ".
     */
    protected String getImplementsText() {
        return "implements " + bEntry.getDynamicVar(JavaBindingWriter.INTERFACE_NAME) + " ";
    } // getImplementsText

    /**
     * Write the body of the binding's stub file.
     */
    protected void writeFileBody(PrintWriter pw) throws IOException {
        PortType portType = binding.getPortType();

        boolean isRPC = true;
        if (bEntry.getBindingStyle() == BindingEntry.STYLE_DOCUMENT) {
            isRPC = false;
        }
        HashSet types = getTypesInPortType(portType);
        boolean hasMIME = Utils.hasMIME(bEntry);
        if (types.size() > 0  || hasMIME) {
            pw.println("    private java.util.Vector cachedSerClasses = new java.util.Vector();");
            pw.println("    private java.util.Vector cachedSerQNames = new java.util.Vector();");
            pw.println("    private java.util.Vector cachedSerFactories = new java.util.Vector();");
            pw.println("    private java.util.Vector cachedDeserFactories = new java.util.Vector();");
        }
        pw.println();

        pw.println("    public " + className + "() throws org.apache.axis.AxisFault {");
        pw.println("         this(null);");
        pw.println("    }");
        pw.println();

        pw.println("    public " + className + "(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {");
        pw.println("         this(service);");
        pw.println("         super.cachedEndpoint = endpointURL;");
        pw.println("    }");
        pw.println();

        pw.println("    public " + className + "(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {");
        pw.println("        try {" );
        pw.println("            if (service == null) {");
        pw.println("                super.service = new org.apache.axis.client.Service();");
        pw.println("            } else {");
        pw.println("                super.service = service;");
        pw.println("            }");

        if (types.size() > 0 || hasMIME) {
            writeSerializationDecls(pw, hasMIME, binding.getQName().getNamespaceURI());
            Iterator it = types.iterator();
            while (it.hasNext()) {
                writeSerializationInit(pw, (TypeEntry) it.next());
            }
        }

        pw.println("        }");
        pw.println("        catch(java.lang.Exception t) {");
        pw.println("            throw org.apache.axis.AxisFault.makeFault(t);");
        pw.println("        }");

        pw.println("    }");
        pw.println();
        pw.println("    private org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {");
        pw.println("        try {");
        pw.println("            org.apache.axis.client.Call _call =");
        pw.println("                    (org.apache.axis.client.Call) super.service.createCall();");
        pw.println("            if (super.maintainSessionSet) {");
        pw.println("                _call.setMaintainSession(super.maintainSession);");
        pw.println("            }");
        pw.println("            if (super.cachedUsername != null) {");
        pw.println("                _call.setUsername(super.cachedUsername);");

        pw.println("            }");
        pw.println("            if (super.cachedPassword != null) {");
        pw.println("                _call.setPassword(super.cachedPassword);");
        pw.println("            }");
        pw.println("            if (super.cachedEndpoint != null) {");
        pw.println("                _call.setTargetEndpointAddress(super.cachedEndpoint);");
        pw.println("            }");
        pw.println("            if (super.cachedTimeout != null) {");
        pw.println("                _call.setTimeout(super.cachedTimeout);");
        pw.println("            }");
        pw.println("            if (super.cachedPortName != null) {");
        pw.println("                _call.setPortName(super.cachedPortName);");
        pw.println("            }");
        pw.println("            java.util.Enumeration keys = super.cachedProperties.keys();");
        pw.println("            while (keys.hasMoreElements()) {");
        pw.println("                String key = (String) keys.nextElement();");
        pw.println("                if(_call.isPropertySupported(key))");
        pw.println("                    _call.setProperty(key, super.cachedProperties.get(key));");
        pw.println("                else");
        pw.println("                    _call.setScopedProperty(key, super.cachedProperties.get(key));");
        pw.println("            }");
        if (types.size() > 0 || hasMIME) {
            pw.println("            // " + JavaUtils.getMessage("typeMap00"));
            pw.println("            // " + JavaUtils.getMessage("typeMap01"));
            pw.println("            // " + JavaUtils.getMessage("typeMap02"));
            pw.println("            // " + JavaUtils.getMessage("typeMap03"));
            pw.println("            // " + JavaUtils.getMessage("typeMap04"));
            pw.println("            synchronized (this) {");
            pw.println("                if (firstCall()) {");
            
            // Hack alert - we need to establish the encoding style before we register type mappings due
            // to the fact that TypeMappings key off of encoding style
            pw.println("                    // "
                    + JavaUtils.getMessage("mustSetStyle"));
            if (bEntry.hasLiteral()) {
                pw.println("                    _call.setEncodingStyle(null);");
            } else {
                pw.println("                    _call.setEncodingStyle(org.apache.axis.Constants.URI_SOAP11_ENC);");
            }
            
            pw.println("                    for (int i = 0; i < cachedSerFactories.size(); ++i) {");
            pw.println("                        Class cls = (Class) cachedSerClasses.get(i);");
            pw.println("                        javax.xml.namespace.QName qName =");
            pw.println("                                (javax.xml.namespace.QName) cachedSerQNames.get(i);");
            pw.println("                        Class sf = (Class)");
            pw.println("                                 cachedSerFactories.get(i);");
            pw.println("                        Class df = (Class)");
            pw.println("                                 cachedDeserFactories.get(i);");
            pw.println("                        _call.registerTypeMapping(cls, qName, sf, df, false);");
            pw.println("                    }");
            pw.println("                }");
            pw.println("            }");
        }
        pw.println("            return _call;");
        pw.println("        }");
        pw.println("        catch (Throwable t) {");
        pw.println("            throw new org.apache.axis.AxisFault(\""
                + JavaUtils.getMessage("badCall01") + "\", t);");
        pw.println("        }");
        pw.println("    }");
        pw.println();

        List operations = binding.getBindingOperations();
        for (int i = 0; i < operations.size(); ++i) {
            BindingOperation operation = (BindingOperation) operations.get(i);
            Parameters parameters =
                    bEntry.getParameters(operation.getOperation());

            // Get the soapAction from the <soap:operation>
            String soapAction = "";
            Iterator operationExtensibilityIterator = operation.getExtensibilityElements().iterator();
            for (; operationExtensibilityIterator.hasNext();) {
                Object obj = operationExtensibilityIterator.next();
                if (obj instanceof SOAPOperation) {
                    soapAction = ((SOAPOperation) obj).getSoapActionURI();
                    break;
                }
            }
            Operation ptOperation = operation.getOperation();
            OperationType type = ptOperation.getStyle();

            // These operation types are not supported.  The signature
            // will be a string stating that fact.
            if (type == OperationType.NOTIFICATION
                    || type == OperationType.SOLICIT_RESPONSE) {
                pw.println(parameters.signature);
                pw.println();
            }
            else {
                writeOperation(pw,
                        operation, parameters, soapAction, isRPC);
            }
        }
    } // writeFileBody

    /**
     * This method returns a set of all the TypeEntry in a given PortType.
     * The elements of the returned HashSet are Types.
     */
    private HashSet getTypesInPortType(PortType portType) {
        HashSet types = new HashSet();
        HashSet firstPassTypes = new HashSet();

        // Get all the types from all the operations
        List operations = portType.getOperations();

        for (int i = 0; i < operations.size(); ++i) {
            Operation op = (Operation) operations.get(i);
            firstPassTypes.addAll(getTypesInOperation(op));
        }

        // Add all the types nested and derived from the types
        // in the first pass.
        Iterator i = firstPassTypes.iterator();
        while (i.hasNext()) {
            TypeEntry type = (TypeEntry) i.next();
            if (!types.contains(type)) {
                types.add(type);
                types.addAll(
                   Utils.getNestedTypes(type, symbolTable, true));
            }
        }
        return types;
    } // getTypesInPortType

    /**
     * This method returns a set of all the TypeEntry in a given Operation.
     * The elements of the returned HashSet are TypeEntry.
     */
    private HashSet getTypesInOperation(Operation operation) {
        HashSet types = new HashSet();
        Vector v = new Vector();

        Parameters params = bEntry.getParameters(operation);
        
        // Loop over parameter types for this operation
        for (int i=0; i < params.list.size(); i++) {
            Parameter p = (Parameter) params.list.get(i);
            v.add(p.getType());
        }
        
        // Add the return type
        if (params.returnParam != null)
            v.add(params.returnParam.getType());
        
        // Collect all the types in faults
        Map faults = operation.getFaults();

        if (faults != null) {
            Iterator i = faults.values().iterator();

            while (i.hasNext()) {
                Fault f = (Fault) i.next();
                partTypes(v,
                        f.getMessage().getOrderedParts(null),
                        (bEntry.getFaultBodyType(operation, f.getName()) == BindingEntry.USE_LITERAL));
            }
        }
        // Put all these types into a set.  This operation eliminates all duplicates.
        for (int i = 0; i < v.size(); i++)
            types.add(v.get(i));

        return types;
    } // getTypesInOperation

    /**
     * This method returns a vector of TypeEntry for the parts.
     */
    private void partTypes(Vector v, Collection parts, boolean literal) {
        Iterator i = parts.iterator();

        while (i.hasNext()) {
            Part part = (Part) i.next();
            
            QName qType = part.getTypeName(); 
            if (qType != null) {
                v.add(symbolTable.getType(qType));
            } else {
                qType = part.getElementName();
                if (qType != null) {
                    v.add(symbolTable.getElement(qType));
                }
            }
        } // while
        
    } // partTypes

    /**
     * In the stub constructor, write the serializer code for the complex types.
     */

    private void writeSerializationDecls(PrintWriter pw, boolean hasMIME,
            String namespace) {
        pw.println("            Class cls;" );
        pw.println("            javax.xml.namespace.QName qName;" );
        pw.println("            Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;");
        pw.println("            Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;");
        pw.println("            Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;");
        pw.println("            Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;");
        pw.println("            Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;");
        pw.println("            Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;");
        pw.println("            Class simplesf = org.apache.axis.encoding.ser.SimpleNonPrimitiveSerializerFactory.class;");
        pw.println("            Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;");

        if (hasMIME) {
            pw.println("            Class mimesf = org.apache.axis.encoding.ser.JAFDataHandlerSerializerFactory.class;");
            pw.println("            Class mimedf = org.apache.axis.encoding.ser.JAFDataHandlerDeserializerFactory.class;");
            pw.println();
            QName qname = new QName(namespace, "DataHandler");

            pw.println("            qName = new javax.xml.namespace.QName(\""
                       + qname.getNamespaceURI() + "\", \"" + qname.getLocalPart()
                       + "\");");
            pw.println("            cachedSerQNames.add(qName);");
            pw.println("            cls = javax.activation.DataHandler.class;");
            pw.println("            cachedSerClasses.add(cls);");
            pw.println("            cachedSerFactories.add(mimesf);");
            pw.println("            cachedDeserFactories.add(mimedf);");
            pw.println();
        }
    } // writeSerializationDecls

    private void writeSerializationInit(PrintWriter pw, TypeEntry type) throws IOException {

        // Note this same check is repeated in JavaDeployWriter.
        boolean process = true;

        // 1) Don't register types that are base (primitive) types.
        //    If the baseType != null && getRefType() != null this
        //    is a simpleType that must be registered.
        // 2) Don't register the special types for collections 
        //    (indexed properties) or element types
        // 3) Don't register types that are not referenced
        //    or only referenced in a literal context.
        if ((type.getBaseType() != null && type.getRefType() == null) ||
            type instanceof CollectionType ||
            type instanceof Element ||
            !type.isReferenced() ||
            type.isOnlyLiteralReferenced()) {
            process = false;
        }
        
        if (!process) {
            return;
        }        

        QName qname = type.getQName();

        pw.println("            qName = new javax.xml.namespace.QName(\""
                   + qname.getNamespaceURI() + "\", \"" + qname.getLocalPart()
                   + "\");");
        pw.println("            cachedSerQNames.add(qName);");
        pw.println("            cls = " + type.getName() + ".class;");
        pw.println("            cachedSerClasses.add(cls);");
        if (type.getName().endsWith("[]")) {
            pw.println("            cachedSerFactories.add(arraysf);");
            pw.println("            cachedDeserFactories.add(arraydf);");
        } else if (type.getNode() != null && 
                   Utils.getEnumerationBaseAndValues(
                     type.getNode(), symbolTable) != null) {
            pw.println("            cachedSerFactories.add(enumsf);");
            pw.println("            cachedDeserFactories.add(enumdf);");
        } else if (type.isSimpleType()) {
            pw.println("            cachedSerFactories.add(simplesf);");
            pw.println("            cachedDeserFactories.add(simpledf);");
        } else if (type.getBaseType() != null) {
            // serializers are not required for types derived from base types
            // java type to qname mapping is anyway established by default
            // note that we have to add null to the serfactories vector to
            // keep the order of other entries, this is not going to screw
            // up because if type mapping returns null for a serialization
            // factory, it is assumed to be not-defined and the delegate
            // will be checked, the end delegate is DefaultTypeMappingImpl
            // that'll get it right with the base type name
            pw.println("            cachedSerFactories.add(null);");
            pw.println("            cachedDeserFactories.add(simpledf);");
        } else {
            pw.println("            cachedSerFactories.add(beansf);");
            pw.println("            cachedDeserFactories.add(beandf);");
        }
        pw.println();
    } // writeSerializationInit

    /**
     * Write the stub code for the given operation.
     */
    private void writeOperation(
            PrintWriter pw,
            BindingOperation operation,
            Parameters parms,
            String soapAction,
            boolean isRPC) throws IOException {

        writeComment(pw, operation.getDocumentationElement());

        pw.println(parms.signature + " {");
        pw.println("        if (super.cachedEndpoint == null) {");
        pw.println("            throw new org.apache.axis.NoEndPointException();");
        pw.println("        }");
        pw.println("        org.apache.axis.client.Call _call = createCall();");

        // loop over paramters and set up in/out params
        for (int i = 0; i < parms.list.size(); ++i) {
            Parameter p = (Parameter) parms.list.get(i);

            String mimeType = p.getMIMEType();

            // Get the QName representing the parameter type
            QName paramType = Utils.getXSIType(p);

            // Set the javaType to the name of the type
            String javaType = null;
            if (mimeType != null) {
                javaType = "javax.activation.DataHandler.class, ";
            }
            else {
                javaType = p.getType().getName();
                if (javaType != null) {
                    javaType += ".class, ";
                } else {
                    javaType = "";
                }
            }

            // Get the text representing newing a QName for the name and type
            String paramNameText = Utils.getNewQName(p.getQName());
            String paramTypeText = Utils.getNewQName(paramType);

            // Generate the addParameter call with the
            // name qname, typeQName, optional javaType, and mode
            if (p.getMode() == Parameter.IN) {
                pw.println("        _call.addParameter(" + paramNameText + ", "
                           + paramTypeText + ", " 
                           + javaType + "javax.xml.rpc.ParameterMode.IN);");
            }
            else if (p.getMode() == Parameter.INOUT) {
                pw.println("        _call.addParameter(" + paramNameText + ", "
                           + paramTypeText + ", " 
                           + javaType + "javax.xml.rpc.ParameterMode.INOUT);");
            }
            else { // p.getMode() == Parameter.OUT
                pw.println("        _call.addParameter(" + paramNameText + ", "
                           + paramTypeText + ", " 
                           + javaType + "javax.xml.rpc.ParameterMode.OUT);");
            }
        }
        // set output type
        if (parms.returnParam != null) {

            // Get the QName for the return Type
            QName returnName = Utils.getXSIType(parms.returnParam);
            
            // Get the javaType
            String javaType = null;
            if (parms.returnParam.getMIMEType() != null) {
                javaType = "javax.activation.DataHandler";
            }
            else {
                javaType = parms.returnParam.getType().getName();
            }
            if (javaType == null) {
                pw.println("        _call.setReturnType(" + 
                           Utils.getNewQName(returnName) + ");");
            } else {
                pw.println("        _call.setReturnType(" + 
                           Utils.getNewQName(returnName) + 
                           ", " + javaType + ".class);");
            }
        }
        else {
            pw.println("        _call.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);");
        }

        // SoapAction
        if (soapAction != null) {
            pw.println("        _call.setUseSOAPAction(true);");
            pw.println("        _call.setSOAPActionURI(\"" + soapAction + "\");");
        }

        // Encoding: literal or encoded use.
        int use = bEntry.getInputBodyType(operation.getOperation());
        if (use == BindingEntry.USE_LITERAL) {
            // Turn off encoding
            pw.println("        _call.setEncodingStyle(null);");
            // turn off multirefs
            pw.println("        _call.setScopedProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);");
            // turn off XSI types
            pw.println("        _call.setScopedProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);");
        }
        
        // Style: document, RPC, or wrapped
        int style = bEntry.getBindingStyle();
        String styleStr = "rpc";
        if (style == BindingEntry.STYLE_DOCUMENT) {
            if (symbolTable.isWrapped()) {
                styleStr = "wrapped";
            } else {
                styleStr = "document";
            }
        }

        if (!Utils.hasMIME(bEntry, operation)) {
            pw.println("        _call.setOperationStyle(\"" + styleStr + "\");");
        }

        // Operation name
        if (styleStr.equals("wrapped")) {
            // We need to make sure the operation name, which is what we
            // wrap the elements in, matches the Qname of the parameter
            // element.
            Map partsMap = operation.getOperation().getInput().getMessage().getParts();
            Part p = (Part)partsMap.values().iterator().next();
            QName q = p.getElementName();
            pw.println("        _call.setOperationName(new javax.xml.namespace.QName(\"" + q.getNamespaceURI() + "\", \"" + q.getLocalPart() + "\"));" );
        } else {
            QName elementQName = Utils.getOperationQName(operation);
            if (elementQName != null) {
                pw.println("        _call.setOperationName(" +
                        Utils.getNewQName(elementQName) + ");" );
            }
        }

        // Initialize any MIME parameters
        int numberOfInMIMES = 0;
        for (int i = 0; i < parms.list.size(); ++i) {
            Parameter p = (Parameter) parms.list.get(i);
            String mimeType = p.getMIMEType();
            byte mode = p.getMode();
            if (mode != Parameter.OUT && mimeType != null) {
                String javifiedName = Utils.xmlNameToJava(p.getName());
                if (mode == Parameter.INOUT) {
                    javifiedName += ".value";
                }

                if (mimeType.equals("text/plain")) {
                    pw.println("        javax.activation.DataHandler _dh" +
                            numberOfInMIMES++ +
                            " = new javax.activation.DataHandler(new org.apache.axis.attachments.PlainTextDataSource(\"" +
                            javifiedName + "\", " + javifiedName + "));");
                }
                else if (mimeType.startsWith("multipart/")) {
                    pw.println("        javax.activation.DataHandler _dh" +
                            numberOfInMIMES++ +
                            " = new javax.activation.DataHandler(new org.apache.axis.attachments.MimeMultipartDataSource(\"" +
                            javifiedName + "\", " + javifiedName + "));");
                }
            }
        }
        numberOfInMIMES = 0;
        
        // Invoke the operation
        pw.println();
        pw.print("        Object _resp = _call.invoke(");
        pw.print("new Object[] {");

        // Write the input and inout parameter list
        boolean needComma = false;
        for (int i = 0; i < parms.list.size(); ++i) {
            Parameter p = (Parameter) parms.list.get(i);

            if (p.getMode() != Parameter.OUT) {
                if (needComma) {
                    pw.print(", ");
                }
                else {
                    needComma = true;
                }

                String mimeType = p.getMIMEType();
                if (mimeType != null) {
                    if (mimeType.equals("text/plain")) {
                        pw.print("_dh" + numberOfInMIMES++);
                        continue;
                    }
                    else if (mimeType.startsWith("multipart/")) {
                        pw.print("_dh" + numberOfInMIMES++);
                        continue;
                    }
                }

                // If we get here, then we didn't find a MIME type
                String javifiedName = Utils.xmlNameToJava(p.getName());
                if (p.getMode() == Parameter.IN) {
                    pw.print(Utils.wrapPrimitiveType(p.getType(),
                            javifiedName));
                }
                else { 
                    pw.print(
                            Utils.wrapPrimitiveType(p.getType(),
                            javifiedName + ".value"));
                }
            }
        }
        pw.println("});");
        pw.println();
        pw.println("        if (_resp instanceof java.rmi.RemoteException) {");
        pw.println("            throw (java.rmi.RemoteException)_resp;");
        pw.println("        }");

        int allOuts = parms.outputs + parms.inouts;
        if (allOuts > 0) {
            pw.println("        else {");
            if (allOuts == 1) {
                if (parms.returnParam != null) {
                    writeOutputAssign(pw, "return ", parms.returnParam.getType(),
                            parms.returnParam.getMIMEType(), "_resp");
                }
                else {
                    // The resp object must go into a holder
                    int i = 0;
                    Parameter p = (Parameter) parms.list.get(i);

                    while (p.getMode() == Parameter.IN) {
                        p = (Parameter) parms.list.get(++i);
                    }
                    String javifiedName = Utils.xmlNameToJava(p.getName());
                    String qnameName = Utils.getNewQName(p.getQName());
                               
                    pw.println("            java.util.Map _output;");
                    pw.println("            _output = _call.getOutputParams();");
                    writeOutputAssign(pw, javifiedName + ".value = ",
                                      p.getType(), p.getMIMEType(),
                                      "_output.get(" + qnameName + ")");
                }
            }
            else {
                // There is more than 1 output.  Get the outputs from getOutputParams.    
                pw.println("            java.util.Map _output;");
                pw.println("            _output = _call.getOutputParams();");
                for (int i = 0; i < parms.list.size (); ++i) {
                    Parameter p = (Parameter) parms.list.get (i);
                    String javifiedName = Utils.xmlNameToJava(p.getName());
                    String qnameName = Utils.getNewQName(p.getQName());
                    if (p.getMode() != Parameter.IN) {
                        writeOutputAssign(pw, javifiedName + ".value = ",
                                          p.getType(), p.getMIMEType(),
                                          "_output.get(" + qnameName + ")");
                    }
                }
                if (parms.returnParam != null) {
                    writeOutputAssign(pw, "return ", parms.returnParam.getType(),
                            parms.returnParam.getMIMEType(), "_resp");
                }

            }
            pw.println("        }");
        }
        pw.println("    }");
        pw.println();
    } // writeOperation

    /** 
     * writeOutputAssign
     * @param target (either "return" or "something ="
     * @param type (source TypeEntry)
     * @param source (source String)   
     *
     */
    private void writeOutputAssign(PrintWriter pw, String target,
                                   TypeEntry type, String mimeType,
                                   String source) {
        String realTarget = null;
        if (mimeType != null) {
            realTarget = target;
            if(mimeType != null) {
                target = "javax.activation.DataHandler _returnDH = ";
            }
        }
        if (type != null && type.getName() != null) {
            // Try casting the output to the expected output.
            // If that fails, use JavaUtils.convert()
            pw.println("            try {");
            pw.println("                " + target +
                       Utils.getResponseString(type, mimeType, source));

            if (mimeType != null) {
                writeMIMETypeReturn(pw, realTarget, source, type, mimeType);
                target = realTarget;
            }

            pw.println("            } catch (java.lang.Exception _exception) {");
            if (mimeType != null) {
                pw.println("                // Is there anything we can do for MIME types?");
                pw.println("                " + target + "null;");
            }
            else {
                pw.println("                " + target +
                        Utils.getResponseString(type, null, 
                        "org.apache.axis.utils.JavaUtils.convert(" +
                        source + ", " + type.getName() + ".class)"));
            }
            pw.println("            }"); 
        } else {
            pw.println("              " + target +
                       Utils.getResponseString(type, mimeType, source));
        }
    }

    /**
     * Write the statements that convert the returned DataHandler to the appropriate
     * MIME mapping type.
     */
    private void writeMIMETypeReturn(PrintWriter pw, String target,
            String source, TypeEntry type, String mimeType) {
        if (mimeType.equals("text/plain")) {
            pw.println("                " + target + "(java.lang.String) _returnDH.getContent();");
        }
        else if (mimeType.startsWith("multipart/")) {
            pw.println("                javax.mail.internet.MimeMultipart _mmp = (javax.mail.internet.MimeMultipart) _returnDH.getContent();");
            pw.println("                if (_mmp.getCount() == 0) {");
            pw.println("                    " + target + "null;");
            pw.println("                }");
            pw.println("                else {");
            pw.println("                    " + target + "(javax.mail.internet.MimeMultipart) _returnDH.getContent();");
            pw.println("                }");
        }
        else if (mimeType.equals("image/jpeg") ||
                mimeType.equals("image/gif")) {
            pw.println("                " + target +
                    "(java.awt.Image) _returnDH.getContent();");
        }
        else if (mimeType.equals("text/xml") ||
                 mimeType.equals("applications/xml")) {
            pw.println("                " + target +
                    "(javax.xml.transform.Source) _returnDH.getContent();");
        }
        else {
            pw.println("                " + target +
                       Utils.getResponseString(type, mimeType, source));
        }
    } // writeMIMETypeReturn
} // class JavaStubWriter
