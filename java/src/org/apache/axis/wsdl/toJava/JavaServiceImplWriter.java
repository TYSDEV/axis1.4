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

import java.net.MalformedURLException;
import java.net.URL;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Vector;

import javax.wsdl.Binding;
import javax.wsdl.Port;
import javax.wsdl.Service;

import javax.wsdl.extensions.soap.SOAPAddress;

import org.apache.axis.utils.JavaUtils;
import org.apache.axis.utils.XMLUtils;

/**
* This is Wsdl2java's service implementation writer.
* It writes the <serviceName>Locator.java file.
*/
public class JavaServiceImplWriter extends JavaWriter {
    private ServiceEntry sEntry;
    private SymbolTable  symbolTable;

    /**
     * Constructor.
     */
    protected JavaServiceImplWriter(
            Emitter emitter,
            ServiceEntry sEntry,
            SymbolTable symbolTable) {
        super(emitter, sEntry, "Locator", "java",
                JavaUtils.getMessage("genService00"), "service");
        this.sEntry = sEntry;
        this.symbolTable = symbolTable;
    } // ctor

    /**
     * Write the body of the service file.
     */
    protected void writeFileBody() throws IOException {
        Service service = sEntry.getService();

        // declare class
        pw.println("public class " + className
                + " extends org.apache.axis.client.Service implements "
                + sEntry.getName() + " {");

        // output comments
        writeComment(pw, service.getDocumentationElement());

        // Used to construct the getPort(Class) method.
        Vector getPortIfaces = new Vector();
        Vector getPortStubClasses = new Vector();
        Vector getPortPortNames = new Vector();
        boolean printGetPortNotice = false;

        // get ports
        Map portMap = service.getPorts();
        Iterator portIterator = portMap.values().iterator();

        // write a get method for each of the ports with a SOAP binding
        while (portIterator.hasNext()) {
            Port p = (Port) portIterator.next();
            Binding binding = p.getBinding();
            if (binding == null) {
                throw new IOException(JavaUtils.getMessage("emitFailNoBinding01",
                        new String[] {p.getName()}));
            }
            
            BindingEntry bEntry =
                    symbolTable.getBindingEntry(binding.getQName());
            if (bEntry == null) {
                throw new IOException(JavaUtils.getMessage("emitFailNoBindingEntry01",
                        new String[] {binding.getQName().toString()}));
            }

            PortTypeEntry ptEntry = symbolTable.getPortTypeEntry(
                    binding.getPortType().getQName());
            if (ptEntry == null) {
                throw new IOException(JavaUtils.getMessage("emitFailNoPortType01",
                        new String[] {binding.getPortType().getQName().toString()}));
            }

            // If this isn't an SOAP binding, skip it
            if (bEntry.getBindingType() != BindingEntry.TYPE_SOAP) {
                continue;
            }

            String portName = Utils.xmlNameToJavaClass(p.getName());
            String stubClass = bEntry.getName() + "Stub";

            // If there is not literal use, the interface name is the portType name.
            // Otherwise it is the binding name.
            String bindingType = bEntry.hasLiteral() ?
                    bEntry.getName() : ptEntry.getName();

            // getPort(Class) must return a stub for an interface.  Collect all
            // the port interfaces so the getPort(Class) method can be constructed.
            if (getPortIfaces.contains(bindingType)) {
                printGetPortNotice = true;
            }
            else {
                getPortIfaces.add(bindingType);
                getPortStubClasses.add(stubClass);
                getPortPortNames.add(portName);
            }

            // Get endpoint address and validate it
            String address = getAddressFromPort(p);
            if (address == null) {
                // now what?
                throw new IOException(JavaUtils.getMessage("emitFail02",
                        portName, className));
            }
            try {
                URL ep = new URL(address);
            }
            catch (MalformedURLException e) {
                throw new IOException(JavaUtils.getMessage("emitFail03",
                        new String[] {portName, className, address}));
            }

            // Write out the get<PortName> methods
            pw.println();
            pw.println("    // " + JavaUtils.getMessage("getProxy00", portName));
            writeComment(pw, p.getDocumentationElement());
            pw.println("    private final java.lang.String " + portName + "_address = \"" + address + "\";");


            pw.println("" );
            pw.println("    public String get" + portName + "Address() {" );
            pw.println("        return " + portName + "_address;" );
            pw.println("    }" );
            pw.println("" );

            pw.println("    public " + bindingType + " get" + portName + "() throws javax.xml.rpc.ServiceException {");
            pw.println("       java.net.URL endpoint;");
            pw.println("        try {");
            pw.println("            endpoint = new java.net.URL(" + portName + "_address);");
            pw.println("        }");
            pw.println("        catch (java.net.MalformedURLException e) {");
            pw.println("            return null; // " +
                    JavaUtils.getMessage("unlikely00"));
            pw.println("        }");
            pw.println("        return get" + portName + "(endpoint);");
            pw.println("    }");
            pw.println();
            pw.println("    public " + bindingType + " get" + portName + "(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {");
            pw.println("        try {");
            pw.println("            return new " + stubClass + "(portAddress, this);");
            pw.println("        }");
            pw.println("        catch (org.apache.axis.AxisFault e) {");
            pw.println("            return null; // ???");
            pw.println("        }");
            pw.println("    }");
        }

        // Build the getPort method.
        pw.println();
        pw.println("    /**");
        pw.println("     * " + JavaUtils.getMessage("getPortDoc00"));
        pw.println("     * " + JavaUtils.getMessage("getPortDoc01"));
        pw.println("     * " + JavaUtils.getMessage("getPortDoc02"));
        if (printGetPortNotice) {
            pw.println("     * " + JavaUtils.getMessage("getPortDoc03"));
            pw.println("     * " + JavaUtils.getMessage("getPortDoc04"));
        }
        pw.println("     */");
        pw.println("    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {");
        if (getPortIfaces.size() == 0) {
            pw.println("        throw new javax.xml.rpc.ServiceException(\""
                    + JavaUtils.getMessage("noStub") + "  \" + (serviceEndpointInterface == null ? \"null\" : serviceEndpointInterface.getName()));");
        }
        else {
            pw.println("        try {");
            for (int i = 0; i < getPortIfaces.size(); ++i) {
                String iface = (String) getPortIfaces.get(i);
                String stubClass = (String) getPortStubClasses.get(i);
                String portName = (String) getPortPortNames.get(i);
                pw.println("            if (" + iface + ".class.isAssignableFrom(serviceEndpointInterface)) {");
                pw.println("                return new " + stubClass + "(new java.net.URL(" + portName + "_address), this);");
                pw.println("            }");
            }
            pw.println("        }");
            pw.println("        catch (Throwable t) {");
            pw.println("            throw new javax.xml.rpc.ServiceException(t);");
            pw.println("        }");
            pw.println("        throw new javax.xml.rpc.ServiceException(\""
                    + JavaUtils.getMessage("noStub") + "  \" + (serviceEndpointInterface == null ? \"null\" : serviceEndpointInterface.getName()));");
        }
        pw.println("    }");
        pw.println();

        // all done
        pw.println("}");
        pw.close();
    } // writeFileBody

    /**
     * Return the endpoint address from a <soap:address location="..."> tag
     */
    private String getAddressFromPort(Port p) {
        // Get the endpoint for a port
        List extensibilityList = p.getExtensibilityElements();
        for (ListIterator li = extensibilityList.listIterator(); li.hasNext();) {
            Object obj = li.next();
            if (obj instanceof SOAPAddress) {
                return XMLUtils.xmlEncodeString(
                        ((SOAPAddress) obj).getLocationURI());
            }
        }
        // didn't find it
        return null;
    } // getAddressFromPort

} // class JavaServiceImplWriter
