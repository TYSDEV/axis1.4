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

import org.apache.axis.i18n.Messages;
import org.apache.axis.utils.ClassUtils;
import org.apache.axis.wsdl.gen.Generator;
import org.apache.axis.wsdl.gen.NoopFactory;
import org.apache.axis.wsdl.symbolTable.BindingEntry;
import org.apache.axis.wsdl.symbolTable.CollectionElement;
import org.apache.axis.wsdl.symbolTable.MessageEntry;
import org.apache.axis.wsdl.symbolTable.PortTypeEntry;
import org.apache.axis.wsdl.symbolTable.ServiceEntry;
import org.apache.axis.wsdl.symbolTable.SymTabEntry;
import org.apache.axis.wsdl.symbolTable.SymbolTable;
import org.apache.axis.wsdl.symbolTable.Type;
import org.apache.axis.wsdl.symbolTable.TypeEntry;
import org.apache.axis.wsdl.toJava.Emitter;
import org.apache.axis.wsdl.toJava.Namespaces;
import org.apache.axis.wsdl.toJava.Utils;
import org.apache.geronimo.ews.ws4j2ee.context.J2EEWebServiceContext;
import org.apache.geronimo.ews.ws4j2ee.toWs.GenerationFault;

import javax.wsdl.Binding;
import javax.wsdl.Definition;
import javax.xml.namespace.QName;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

/**
 * @author Ias (iasandcb@tmax.co.kr)
 */

public class J2eeEmitter extends Emitter {

    private String mappingFilePath;
    private static final int timeoutms = 45000000;
    private InputStream mappingFileInputStream;
    private QName serviceQName;
    private JaxRpcMapper jaxRpcMapper;
    private SymbolTable symbolTable;
//    private boolean usedbyws4j2ee = false;
//    private boolean seiNeeded = true;
    protected J2EEWebServiceContext wscontext;

    public J2eeEmitter() {
        J2eeGeneratorFactory factory = new J2eeGeneratorFactory(this);
        setFactory(factory);
    } // ctor
//    public J2eeEmitter(boolean usedByws4j2ee,boolean needSei,J2EEWebServiceContext wscontext) {
//    	this.usedbyws4j2ee = usedByws4j2ee;
//    	this.seiNeeded = needSei;
//		this.wscontext = wscontext;
//        J2eeGeneratorFactory factory = new J2eeGeneratorFactory(this);
//        setFactory(factory);
//
//    } // ctor

    public J2eeEmitter(J2EEWebServiceContext wscontext, J2eeGeneratorFactory factory) {
        this.wscontext = wscontext;
        if (factory == null)
            factory = new J2eeGeneratorFactory(this);
        setFactory(factory);
        factory.setEmitter(this);
    } // ctor

    public void setMappingFilePath(String mappingFilePath) {
        this.mappingFilePath = mappingFilePath;
    }

    private void loadMapping() throws GenerationFault {
        //jaxRpcMapper = new JAXBJaxRpcMapper();
        jaxRpcMapper = new XMLBeansJaxRpcMapper();
        if (mappingFilePath == null) {
            jaxRpcMapper.loadMappingFromInputStream(mappingFileInputStream);
        } else {
            jaxRpcMapper.loadMappingFromDir(mappingFilePath);
        }
        int length = jaxRpcMapper.getPackageMappingCount();
        Map namespaceMap = getNamespaceMap();
        for (int i = 0; i < length; i++) {
            namespaceMap.put(jaxRpcMapper.getPackageMappingURI(i), jaxRpcMapper.getPackageMappingClassName(i));
        }
    }

    /**
     * Emit appropriate Java files for a WSDL at a given URL.
     * This method will time out after the number of milliseconds specified
     * by our timeoutms member.
     */
    public void run(String wsdlURL) throws Exception {
        setup();
        runTemp(wsdlURL);
    } // run

    public void runServerSide(String wsdlURL) throws Exception {
        setup();
        if (getFactory() == null) {
            setFactory(new NoopFactory());
        }
        symbolTable = new SymbolTable(getFactory().getBaseTypeMapping(),
                imports,
                verbose,
                nowrap);
        symbolTable.populate(wsdlURL, username, password);
        generate(symbolTable);
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    private void setup() throws GenerationFault {
        try {
            if (baseTypeMapping == null) {
                setTypeMappingVersion(typeMappingVersion);
            }
            getFactory().setBaseTypeMapping(baseTypeMapping);
            namespaces = new Namespaces(getOutputDir());
            if (getPackageName() != null) {
                namespaces.setDefaultPackage(getPackageName());
            } else {
                // First, read the namespace mapping file - configurable, by default
                // NStoPkg.properties - if it exists, and load the namespaceMap HashMap
                // with its data.
                getNStoPkgFromPropsFile(namespaces);
                loadMapping();
                if (getNamespaceMap() != null) {
                    namespaces.putAll(getNamespaceMap());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw GenerationFault.createGenerationFault(e);
        }
    } // setup

    /**
     * Tries to load the namespace-to-package mapping file.
     * <ol>
     * <li>if a file name is explicitly set using <code>setNStoPkg()</code>, tries
     * to load the mapping from this file. If this fails, the built-in default
     * mapping is used.
     * <li>if no file name is set, tries to load the file <code>DEFAULT_NSTOPKG_FILE</code>
     * as a java resource. If this fails, the built-in dfault mapping is used.
     * </ol>
     *
     * @param namespaces a hashmap which is filled with the namespace-to-package mapping
     *                   in this method
     * @see #setNStoPkg(String)
     * @see #DEFAULT_NSTOPKG_FILE
     * @see org.apache.axis.utils.ClassUtils#getResourceAsStream(java.lang.Class,String)
     */
    private void getNStoPkgFromPropsFile(HashMap namespaces) throws IOException {
        Properties mappings = new Properties();
        if (NStoPkgFilename != null) {
            try {
                mappings.load(new FileInputStream(NStoPkgFilename));
                if (verbose) {
                    System.out.println(Messages.getMessage("nsToPkgFileLoaded00", NStoPkgFilename));
                }
            } catch (Throwable t) {
                // loading the custom mapping file failed. We do not try
                // to load the mapping from a default mapping file.
                throw new IOException(Messages.getMessage("nsToPkgFileNotFound00", NStoPkgFilename));
            }
        } else {
            try {
                mappings.load(new FileInputStream(DEFAULT_NSTOPKG_FILE));
                if (verbose) {
                    System.out.println(Messages.getMessage("nsToPkgFileLoaded00", DEFAULT_NSTOPKG_FILE));
                }
            } catch (Throwable t) {
                try {
                    mappings.load(ClassUtils.getResourceAsStream(Emitter.class, DEFAULT_NSTOPKG_FILE));
                    if (verbose) {
                        System.out.println(Messages.getMessage("nsToPkgDefaultFileLoaded00", DEFAULT_NSTOPKG_FILE));
                    }
                } catch (Throwable t1) {
                    // loading the default mapping file failed.
                    // The built-in default mapping is used
                    // No message is given, since this is generally what happens
                }
            }
        }
        Enumeration keys = mappings.propertyNames();
        while (keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            namespaces.put(key, mappings.getProperty(key));
        }
    } // getNStoPkgFromPropsFile

    /**
     * Convert the specified QName into a full Java Name.
     */
    public String getJavaName(QName qName) {
        // If this is one of our special 'collection' qnames.

        // get the element type and append []

        if (qName.getLocalPart().indexOf("[") > 0) {
            String localPart = qName.getLocalPart().substring(0, qName.getLocalPart().indexOf("["));
            QName eQName = new QName(qName.getNamespaceURI(), localPart);
            return getJavaName(eQName) + "[]";
        }



        // Handle the special "java" namespace for types

        if (qName.getNamespaceURI().equalsIgnoreCase("java")) {
            return qName.getLocalPart();
        }



        // The QName may represent a base java name, so check this first

        String fullJavaName = getFactory().getBaseTypeMapping().getBaseName(qName);
        if (fullJavaName != null)
            return fullJavaName;



        // Use the namespace uri to get the appropriate package
      
        String pkg = getPackage(qName.getNamespaceURI());
        if (pkg != null) {
            fullJavaName = pkg + "." + Utils.xmlNameToJavaClass(qName.getLocalPart());
        } else {
            fullJavaName = Utils.xmlNameToJavaClass(qName.getLocalPart());
        }
        return fullJavaName;
    } // getJavaName

    /**
     * Parse a WSDL at a given URL.
     * This method will time out after the number of milliseconds specified
     * by our timeoutms member.
     */
    public void runTemp(String wsdlURI) throws Exception {
        if (getFactory() == null) {
            setFactory(new NoopFactory());
        }
        symbolTable = new SymbolTable(getFactory().getBaseTypeMapping(),
                imports,
                verbose,
                nowrap);
// We run the actual Emitter in a thread that we can kill
        WSDLRunnable runnable = new WSDLRunnable(symbolTable, wsdlURI);
        Thread wsdlThread = new Thread(runnable);
        wsdlThread.start();
        try {
            if (timeoutms > 0)
                wsdlThread.join(timeoutms);
            else
                wsdlThread.join();
        } catch (InterruptedException e) {
        }
        if (wsdlThread.isAlive()) {
            wsdlThread.interrupt();
            throw new IOException(Messages.getMessage("timedOut"));
        }
        if (runnable.getFailure() != null) {
            throw runnable.getFailure();
        }
    } // run

    private class WSDLRunnable implements Runnable {
        private SymbolTable symbolTable;
        private String wsdlURI;
        private Exception failure = null;

        public WSDLRunnable(SymbolTable symbolTable, String wsdlURI) {
            this.symbolTable = symbolTable;
            this.wsdlURI = wsdlURI;
        } // ctor

        public void run() {
            try {
                symbolTable.populate(wsdlURI, username, password);
                generate(symbolTable);
            } catch (Exception e) {
                failure = e;
            }
        } // run

        public Exception getFailure() {
            return failure;
        } // getFailure
    } // WSDLRunnable

    protected void sanityCheck(SymbolTable symbolTable) {
        Iterator it = symbolTable.getHashMap().values().iterator();
        while (it.hasNext()) {
            Vector v = (Vector) it.next();
            for (int i = 0; i < v.size(); ++i) {
                SymTabEntry entry = (SymTabEntry) v.elementAt(i);
                String namespace = entry.getQName().getNamespaceURI();
                String packageName =
                        org.apache.axis.wsdl.toJava.Utils.makePackageName(namespace);
                String localName = entry.getQName().getLocalPart();
                if (localName.equals(packageName) &&
                        packageName.equals(namespaces.getCreate(namespace))) {
                    packageName += "_pkg";
                    namespaces.put(namespace, packageName);
                }
            }
        }
    }

    private void generate(SymbolTable symbolTable) throws IOException {
        sanityCheck(symbolTable);
        Definition def = symbolTable.getDefinition();
        getFactory().generatorPass(def, symbolTable);
        if (isDebug()) {
            symbolTable.dump(System.out);
        }
        if (getOutputDir() == null) {
            return;
        }

        // Generate bindings for types
        generateTypes(symbolTable);
        Iterator it = symbolTable.getHashMap().values().iterator();
        while (it.hasNext()) {
            Vector v = (Vector) it.next();
            for (int i = 0; i < v.size(); ++i) {
                SymTabEntry entry = (SymTabEntry) v.elementAt(i);
                Generator gen = null;
                if (entry instanceof MessageEntry) {
                    gen = getFactory().getGenerator(((MessageEntry) entry).getMessage(), symbolTable);
                } else if (entry instanceof PortTypeEntry) {
                    PortTypeEntry pEntry = (PortTypeEntry) entry;
                    // If the portType is undefined, then we're parsing a Definition
                    // that didn't contain a portType, merely a binding that referred
                    // to a non-existent port type.  Don't bother writing it.
                    if (pEntry.getPortType().isUndefined()) {
                        continue;
                    }
                    gen = getFactory().getGenerator(pEntry.getPortType(), symbolTable);
                } else if (entry instanceof BindingEntry) {
                    BindingEntry bEntry = (BindingEntry) entry;
                    Binding binding = bEntry.getBinding();

                    // If the binding is undefined, then we're parsing a Definition
                    // that didn't contain a binding, merely a service that referred
                    // to a non-existent binding.  Don't bother writing it.
                    if (binding.isUndefined() || !bEntry.isReferenced()) {
                        continue;
                    }
                    gen = getFactory().getGenerator(binding, symbolTable);
                } else if (entry instanceof ServiceEntry) {
                    ServiceEntry sEntry = (ServiceEntry) entry;
                    serviceQName = sEntry.getService().getQName();
                    gen = getFactory().getGenerator(sEntry.getService(), symbolTable);
                }
                if (gen != null) {
                    gen.generate();
                }
            }
        }

        // Output extra stuff (deployment files and faults)
        // outside of the recursive emit method.
        Generator gen = getFactory().getGenerator(def, symbolTable);
        gen.generate();
    } // generate

    /**
     * Generate bindings (classes and class holders) for the complex types.
     * If generating serverside (skeleton) spit out beanmappings
     */
    private void generateTypes(SymbolTable symbolTable) throws IOException {
        Map elements = symbolTable.getElementIndex();
        Collection elementCollection = elements.values();
        for (Iterator i = elementCollection.iterator(); i.hasNext();) {
            TypeEntry type = (TypeEntry) i.next();
            type.setOnlyLiteralReference(false);

            // Write out the type if and only if:
            //  - we found its definition (getNode())
            //  - it is referenced
            //  - it is not a base type
            //  - it is a Type (not an Element) or a CollectionElement
            // (Note that types that are arrays are passed to getGenerator
            //  because they may require a Holder)

            // A CollectionElement is an array that might need a holder

//			boolean isType = (type instanceof Type ||
//					type instanceof CollectionElement);
//			if (type.getNode() != null &&
//					type.isReferenced() &&
//					isType &&
//					type.getBaseType() == null) {
            Generator gen = getFactory().getGenerator(type, symbolTable);
            gen.generate();
//			}
        }
        Map types = symbolTable.getTypeIndex();
        Collection typeCollection = types.values();
        for (Iterator i = typeCollection.iterator(); i.hasNext();) {
            TypeEntry type = (TypeEntry) i.next();

            // Write out the type if and only if:
            //  - we found its definition (getNode())
            //  - it is referenced
            //  - it is not a base type
            //  - it is a Type (not an Element) or a CollectionElement
            // (Note that types that are arrays are passed to getGenerator
            //  because they may require a Holder)

            // A CollectionElement is an array that might need a holder
            boolean isType = (type instanceof Type ||
                    type instanceof CollectionElement);
            if (type.getNode() != null &&
                    type.isReferenced() &&
                    isType &&
                    type.getBaseType() == null) {
                Generator gen = getFactory().getGenerator(type, symbolTable);
                gen.generate();
            }
        }
    } // generateTypes

    /**
     * @param mappingFileInputStream The mappingFileInputStream to set.
     */
    public void setMappingFileInputStream(InputStream mappingFileInputStream) {
        this.mappingFileInputStream = mappingFileInputStream;
    }

    /**
     * @return Returns the serviceQName.
     */
    public QName getServiceQName() {
        return serviceQName;
    }

    /**
     * @return
     */
    public boolean isGeneratingInterface() {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * @return
     */
    public boolean isGeneratingTypes() {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * @return Returns the jaxRpcMapper.
     */
    public JaxRpcMapper getJaxRpcMapper() {
        return jaxRpcMapper;
    }

    /**
     * @param jaxRpcMapper The jaxRpcMapper to set.
     */
    public void setJaxRpcMapper(JaxRpcMapper jaxRpcMapper) {
        this.jaxRpcMapper = jaxRpcMapper;
    }

//    /**
//     * @return
//     */
//    public boolean isSeiNeeded() {
//        return seiNeeded;
//    }
//
//    /**
//     * @return
//     */
//    public boolean isUsedbyws4j2ee() {
//        return usedbyws4j2ee;
//    }
//
//    /**
//     * @param b
//     */
//    public void setSeiNeeded(boolean b) {
//        seiNeeded = b;
//    }
//
//    /**
//     * @param b
//     */
//    public void setUsedbyws4j2ee(boolean b) {
//        usedbyws4j2ee = b;
//    }

    /**
     * @return
     */
    public J2EEWebServiceContext getWscontext() {
        return wscontext;
    }

    /**
     * @param context
     */
    public void setWscontext(J2EEWebServiceContext context) {
        wscontext = context;
    }

} // class MyEmitter
