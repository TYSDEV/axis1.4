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
package samples.client;

import org.apache.axis.wsdl.gen.Parser;
import org.apache.axis.wsdl.symbolTable.BindingEntry;
import org.apache.axis.wsdl.symbolTable.Parameter;
import org.apache.axis.wsdl.symbolTable.Parameters;
import org.apache.axis.wsdl.symbolTable.PortTypeEntry;
import org.apache.axis.wsdl.symbolTable.ServiceEntry;
import org.apache.axis.wsdl.symbolTable.SymTabEntry;
import org.apache.axis.wsdl.symbolTable.SymbolTable;

import javax.wsdl.Binding;
import javax.wsdl.Input;
import javax.wsdl.Operation;
import javax.wsdl.Output;
import javax.wsdl.Port;
import javax.wsdl.PortType;
import javax.wsdl.Service;
import javax.wsdl.extensions.soap.SOAPAddress;
import javax.xml.namespace.QName;
import javax.xml.rpc.Call;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * This sample shows how to use Axis for completely dynamic invocations
 * as it is completely stubless execution. This sample does not support
 * complex types (it could if there was defined a to encode complex
 * values as command line arguments).
 *
 * @author Davanum Srinivas (dims@yahoo.com)
 */
public class DynamicInvoker {

    /** Field wsdlParser           */
    private Parser wsdlParser = null;

    /**
     * Constructor DynamicInvoker
     *
     * @param wsdlURL
     *
     * @throws Exception
     */
    public DynamicInvoker(String wsdlURL) throws Exception {
        // Start by reading in the WSDL using Parser
        wsdlParser = new Parser();
        System.out.println("Reading WSDL document from '" + wsdlURL + "'");
        wsdlParser.run(wsdlURL);
    }

    /**
     * Method usage
     */
    private static void usage() {
        System.err.println(
                "Usage: java " + DynamicInvoker.class.getName() + " wsdlLocation "
                + "operationName[(portName)]:[inputMessageName]:[outputMessageName] "
                + "[argument1 ...]");
        System.exit(1);
    }

    /**
     * Method main
     *
     * @param args
     *
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            usage();
        }
        String wsdlLocation = (args.length > 0)
                ? args[0]
                : null;
        String operationKey = (args.length > 1)
                ? args[1]
                : null;
        String portName = null;
        String operationName = null;
        String inputName = null;
        String outputName = null;
        StringTokenizer st = new StringTokenizer(operationKey, ":");
        int tokens = st.countTokens();
        int specType = 0;

        if (tokens == 2) {
            specType = operationKey.endsWith(":")
                    ? 1
                    : 2;
        } else if ((tokens != 1) && (tokens != 3)) {
            usage();
        }
        while (st.hasMoreTokens()) {
            if (operationName == null) {
                operationName = st.nextToken();
            } else if ((inputName == null) && (specType != 2)) {
                inputName = st.nextToken();
            } else if (outputName == null) {
                outputName = st.nextToken();
            } else {
                break;
            }
        }
        
        try {
            portName = operationName.substring(operationName.indexOf("(") + 1,
                                               operationName.indexOf(")"));
            operationName = operationName.substring(0, operationName.indexOf("("));
        } catch (Exception ignored) {
        }
        
        DynamicInvoker invoker = new DynamicInvoker(wsdlLocation);
        HashMap map = invoker.invokeMethod(operationName, inputName, outputName, portName, args);

        // print result
        System.out.println("Result:");
        for (Iterator it = map.keySet().iterator(); it.hasNext();) {
            String name = (String) it.next();

            System.out.println(name + "=" + map.get(name));
        }
        System.out.println("\nDone!");
    }

    /**
     * Method invokeMethod
     *
     * @param wsdlLocation
     * @param operationName
     * @param inputName
     * @param outputName
     * @param portName
     * @param args
     *
     * @return
     *
     * @throws Exception
     */
    public HashMap invokeMethod(
            String operationName, String inputName, String outputName, String portName, String[] args)
            throws Exception {
        String serviceNS = null;
        String serviceName = null;
        String portTypeNS = null;
        String portTypeName = null;
        String operationQName = null;

        System.out.println("Preparing Axis dynamic invocation");
        Service service = selectService(serviceNS, serviceName);
        PortType portType = selectPortType(portTypeNS, portTypeName);
        Operation operation = null;
        org.apache.axis.client.Service dpf = new org.apache.axis.client.Service(wsdlParser, service.getQName());

        if ((inputName == null) && (outputName == null)) {

            // retrieve list of operations
            List operationList = portType.getOperations();

            // try to find input and output names for the operation specified
            boolean found = false;

            for (Iterator i = operationList.iterator(); i.hasNext();) {
                Operation op = (Operation) i.next();
                String name = op.getName();

                if (!name.equals(operationName)) {
                    continue;
                }
                if (found) {
                    throw new RuntimeException(
                            "Operation '" + operationName + "' is overloaded. "
                            + "Please specify the operation in the form "
                            + "'operationName:inputMessageName:outputMesssageName' to distinguish it");
                }
                found = true;
                operation = op;
                Input opInput = op.getInput();

                inputName = (opInput.getName() == null)
                        ? null
                        : opInput.getName();
                Output opOutput = op.getOutput();

                outputName = (opOutput.getName() == null)
                        ? null
                        : opOutput.getName();
            }
        }
        Vector inputs = new Vector();
        Port port = selectPort(service.getPorts(), portName);

        if (portName == null) {
            portName = port.getName();
        }
        Binding binding = port.getBinding();
        Call call = dpf.createCall(QName.valueOf(portName),
                                   QName.valueOf(operationName));

        // Output types and names
        Vector outNames = new Vector();
        Vector outTypes = new Vector();

        // Input types and names
        Vector inNames = new Vector();
        Vector inTypes = new Vector();
        SymbolTable symbolTable = wsdlParser.getSymbolTable();
        BindingEntry bEntry =
                symbolTable.getBindingEntry(binding.getQName());
        Parameters parameters = null;
        Iterator i = bEntry.getParameters().keySet().iterator();

        while (i.hasNext()) {
            Operation o = (Operation) i.next();

            if (o.getName().equals(operationName)) {
                parameters = (Parameters) bEntry.getParameters().get(o);
            }
        }
        if ((operation == null) || (parameters == null)) {
            throw new RuntimeException("no operation " + operationName
                                       + " was found in port type "
                                       + portType.getQName());
        }

        // loop over paramters and set up in/out params
        for (int j = 0; j < parameters.list.size(); ++j) {
            Parameter p = (Parameter) parameters.list.get(j);

            // Get the QName representing the parameter type
            QName paramType = org.apache.axis.wsdl.toJava.Utils.getXSIType(p);

            if (p.getMode() == 1) {           // IN
                inNames.add(p.getQName().getLocalPart());
                addTypeClass(inTypes, paramType.getLocalPart());
            } else if (p.getMode() == 2) {    // OUT
                outNames.add(p.getQName().getLocalPart());
                addTypeClass(outTypes, paramType.getLocalPart());
            } else if (p.getMode() == 3) {    // INOUT
                inNames.add(p.getQName().getLocalPart());
                addTypeClass(inTypes, paramType.getLocalPart());
                outNames.add(p.getQName().getLocalPart());
                addTypeClass(outTypes, paramType.getLocalPart());
            }
        }

        // set output type
        if (parameters.returnParam != null) {

            // Get the QName for the return Type
            QName returnType = org.apache.axis.wsdl.toJava.Utils.getXSIType(
                    parameters.returnParam);
            QName returnQName = parameters.returnParam.getQName();

            outNames.add(returnQName.getLocalPart());
            addTypeClass(outTypes, returnType.getLocalPart());
        }
        for (int pos = 0; pos < inNames.size(); ++pos) {
            String arg = args[pos + 2];
            Object value = null;
            Class c = (Class) inTypes.get(pos);

            if (c.equals(String.class)) {
                value = arg;
            } else if (c.equals(Double.TYPE)) {
                value = new Double(arg);
            } else if (c.equals(Float.TYPE)) {
                value = new Float(arg);
            } else if (c.equals(Integer.TYPE)) {
                value = new Integer(arg);
            } else if (c.equals(Boolean.TYPE)) {
                value = new Boolean(arg);
            } else {
                throw new RuntimeException("not know how to convert '" + arg
                                           + "' into " + c);
            }
            inputs.add(value);
        }
        System.out.println("Executing operation " + operationName);
        Object ret = call.invoke(inputs.toArray());
        Map outputs = call.getOutputParams();
        HashMap map = new HashMap();

        for (int pos = 0; pos < outNames.size(); ++pos) {
            String name = (String) outNames.get(pos);
            Object value = outputs.get(name);

            if ((value == null) && (pos == 0)) {
                map.put(name, ret);
            } else {
                map.put(name, value);
            }
        }
        return map;
    }

    /**
     * Method addTypeClass
     *
     * @param v
     * @param type
     */
    private void addTypeClass(Vector v, String type) {

        if ("string".equals(type)) {
            v.add(String.class);
        } else if ("double".equals(type)) {
            v.add(Integer.TYPE);
        } else if ("float".equals(type)) {
            v.add(Float.TYPE);
        } else if ("int".equals(type)) {
            v.add(Integer.TYPE);
        } else if ("boolean".equals(type)) {
            v.add(Boolean.TYPE);
        } else {
            throw new RuntimeException("Type " + type + " is not supported");
        }
    }

    /**
     * Method selectService
     *
     * @param def
     * @param serviceNS
     * @param serviceName
     *
     * @return
     *
     * @throws Exception
     */
    public Service selectService(String serviceNS, String serviceName)
            throws Exception {

        QName serviceQName = (((serviceNS != null)
                && (serviceName != null))
                ? new QName(serviceNS, serviceName)
                : null);
        ServiceEntry serviceEntry = (ServiceEntry) getSymTabEntry(serviceQName,
                                                                  ServiceEntry.class);

        return serviceEntry.getService();
    }

    /**
     * Method getSymTabEntry
     *
     * @param qname
     * @param cls
     *
     * @return
     */
    public SymTabEntry getSymTabEntry(QName qname, Class cls) {

        HashMap map = wsdlParser.getSymbolTable().getHashMap();
        Iterator iterator = map.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            QName key = (QName) entry.getKey();
            Vector v = (Vector) entry.getValue();

            if ((qname == null) || qname.equals(qname)) {
                for (int i = 0; i < v.size(); ++i) {
                    SymTabEntry symTabEntry = (SymTabEntry) v.elementAt(i);

                    if (cls.isInstance(symTabEntry)) {
                        return symTabEntry;
                    }
                }
            }
        }
        return null;
    }    // get

    /**
     * Method selectPortType
     *
     * @param def
     * @param portTypeNS
     * @param portTypeName
     *
     * @return
     *
     * @throws Exception
     */
    public PortType selectPortType(String portTypeNS, String portTypeName)
            throws Exception {

        QName portTypeQName = (((portTypeNS != null)
                && (portTypeName != null))
                ? new QName(portTypeNS, portTypeName)
                : null);
        PortTypeEntry portTypeEntry =
                (PortTypeEntry) getSymTabEntry(portTypeQName, PortTypeEntry.class);

        return portTypeEntry.getPortType();
    }

    /**
     * Method selectPort
     *
     * @param ports
     * @param portName
     *
     * @return
     *
     * @throws Exception
     */
    public Port selectPort(Map ports, String portName) throws Exception {

        Iterator valueIterator = ports.keySet().iterator();

        while (valueIterator.hasNext()) {
            String name = (String) valueIterator.next();

            if ((portName == null) || (portName.length() == 0)) {
                Port port = (Port) ports.get(name);
                List list = port.getExtensibilityElements();

                for (int i = 0; (list != null) && (i < list.size()); i++) {
                    Object obj = list.get(i);

                    if (obj instanceof SOAPAddress) {
                        return port;
                    }
                }
            }
            if ((name != null) && name.equals(portName)) {
                return (Port) ports.get(name);
            }
        }
        return null;
    }
}

