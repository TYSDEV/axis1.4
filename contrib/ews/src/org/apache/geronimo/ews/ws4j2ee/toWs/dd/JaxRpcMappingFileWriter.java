/*
 * Copyright 2003,2004 The Apache Software Foundation.
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
package org.apache.geronimo.ews.ws4j2ee.toWs.dd;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.wsdl.Binding;
import javax.wsdl.Operation;
import javax.wsdl.Part;
import javax.wsdl.Port;
import javax.wsdl.Service;
import javax.xml.namespace.QName;

import org.apache.axis.encoding.SerializationContext;
import org.apache.axis.wsdl.fromJava.Emitter;
import org.apache.geronimo.ews.ws4j2ee.context.J2EEWebServiceContext;
import org.apache.geronimo.ews.ws4j2ee.toWs.GenerationFault;
import org.apache.geronimo.ews.ws4j2ee.toWs.UnrecoverableGenerationFault;
import org.apache.geronimo.ews.ws4j2ee.toWs.Writer;
import org.apache.geronimo.ews.ws4j2ee.utils.Utils;

/**
 * @author Srinath Perera(hemapani@opensource.lk)
 */
public class JaxRpcMappingFileWriter implements Writer {
    private SerializationContext sc;
    private String ns = "http://java.sun.com/xml/ns/j2ee";
    private Emitter emitter;
    private J2EEWebServiceContext j2eewscontext;
    
    public JaxRpcMappingFileWriter(java.io.Writer out,
        Emitter emitter,
        J2EEWebServiceContext j2eewscontext){
        if(out == null){
            throw new UnrecoverableGenerationFault(out + " The writer is null");
        }
        sc = new SerializationContext(out);
       
        sc.setPretty(true);
        this.emitter = emitter;
        this.j2eewscontext = j2eewscontext;        
    } 
    
    private void writePackageMapping()throws IOException{

            
        String jaxrpcsei = j2eewscontext.getMiscInfo().getJaxrpcSEI();
        Map map = emitter.getNamespaceMap();
        String val = null;
        if (map != null) {
            Iterator packages = map.keySet().iterator();
            while (packages.hasNext()) {
                String pkg = (String) packages.next();
                if(pkg == null){
                    //TODO this is temporrary work around to make sure 
                    //the mapping is defined.
                    String pkgName = Utils.getPackageNameFromQuallifiedName(jaxrpcsei);
                    val = (String)map.get(pkgName);
                    if(val == null){
                        val = Utils.javapkgToURI(pkgName);
                    }else{
                        continue;
                    }
                }else if(pkg.equals(jaxrpcsei)){
                    continue;
                }else{
                    val = (String) map.get(pkg);
            
                }
                //TODO FIX this code to simplfy. 
                if(pkg != null){
                    sc.startElement(new QName(ns,"package-mapping"),null);//package-mapping
                    sc.startElement(new QName(ns,"package-type"),null);
                    sc.writeString(pkg);
                    sc.endElement();
                    sc.startElement(new QName(ns,"namespaceURI"),null);
                    sc.writeString(val);
                    sc.endElement();
                
                    sc.endElement();//package-mapping                            
                }
            }
        }

    }
    
    public void writeServiceMapping() throws IOException{
        Service service = j2eewscontext.getWSDLContext().gettargetService().getService();
                    
                    
        sc.startElement(new QName(ns,"service-interface-mapping"),null);//service-interface-mapping   
            
        String name = emitter.getCls().getName();
        int index = name.lastIndexOf('.');
        String packageName = "";
        if (index > 0)
            packageName = name.substring(0, index + 1);
            
        sc.startElement(new QName(ns,"service-interface"),null);
        sc.writeString(packageName + emitter.getServiceElementName());
        sc.endElement();
                    
        sc.registerPrefixForURI("ns1",service.getQName().getNamespaceURI());
        sc.startElement(new QName(ns,"wsdl-service-name"),null);
        sc.writeString("ns1:" + service.getQName().getLocalPart());
        sc.endElement();
            
        //port mapping
        Port wsdlport = j2eewscontext.getWSDLContext().getTargetPort();
                    
        sc.startElement(new QName(ns,"port-mapping"),null);//port-mapping
            
        sc.startElement(new QName(ns,"port-name"),null);
        sc.writeString(wsdlport.getName());
        sc.endElement();
        
        sc.startElement(new QName(ns,"java-port-name"),null);
        sc.writeString(emitter.getServicePortName());
        sc.endElement();
            
        sc.endElement();//port-mapping
                    
                      
        sc.endElement();//service-interface-mapping       
    
    }
    
    private void writeReturnTypes(HashMap methods,Operation op)throws IOException{
        //set return type
        Method mtd = (Method) methods.get(op.getName());
        Class ret = mtd.getReturnType();
        if(ret!= null && !("void".equals(ret.toString()))){

            //set return type info
            Map parts = op.getOutput().getMessage().getParts();
            if (parts != null) {
                Iterator partIt = parts.values().iterator();
                while (partIt.hasNext()) {
                    //set wsdl message type
                    QName messagename = op.getOutput().getMessage().getQName();
                        
                    //set wsdl message part type
                    sc.startElement(new QName(ns,"wsdl-return-value-mapping"),null);

                    sc.startElement(new QName(ns,"method-return-value"),null);
                    sc.writeString(ret.getName());
                    sc.endElement();
                
                    sc.registerPrefixForURI("ns1",messagename.getNamespaceURI());
                    sc.startElement(new QName(ns,"wsdl-message"),null);
                    sc.writeString("ns1:" + messagename.getLocalPart());
                    sc.endElement();


                    sc.startElement(new QName(ns,"wsdl-message-part-name"),null);
                    sc.writeString(((Part) partIt.next()).getName());
                    sc.endElement();
                
                    sc.endElement();//wsdl-return-value-mapping

                }
            }
        }
    
    }
    
    private void writeParametParts(HashMap methods,Operation op)throws IOException{
        int position = 0;
        Class[] params = ((Method) methods.get(op.getName())).getParameterTypes();

        Iterator parmIt = null;
        Map parameters = op.getInput().getMessage().getParts();
        if (parameters != null) {
            parmIt = parameters.values().iterator();
        }

        while (parmIt != null && parmIt.hasNext()) {
            Part part = (Part) parmIt.next();
                    
            sc.startElement(new QName(ns,"method-param-parts-mapping"),null);

            //set parameter position
            sc.startElement(new QName(ns,"param-position"),null);
            sc.writeString(Integer.toString(position));
            sc.endElement();
            //set parameter java typr
            sc.startElement(new QName(ns,"param-type"),null);
            sc.writeString(params[position].getName());
            sc.endElement();
                
            //set message mapping
            sc.startElement(new QName(ns,"wsdl-message-mapping"),null);
                    
            QName messagename = op.getInput().getMessage().getQName();
            sc.registerPrefixForURI("ns1",messagename.getNamespaceURI());
            sc.startElement(new QName(ns,"wsdl-message"),null);
            sc.writeString(messagename.getLocalPart());
            sc.endElement();
                    
            sc.startElement(new QName(ns,"wsdl-message-part-name"),null);
            sc.writeString(part.getName());
            sc.endElement();
                    
            sc.startElement(new QName(ns,"parameter-mode"),null);
            sc.writeString("IN");
            sc.endElement();
                    
            sc.endElement();//wsdl-message-mapping

            sc.endElement();//method-param-parts-mapping
        }
   
    }
    
    
    private void writeMethodMapping(Binding binding)throws IOException{
        //add the operation mappings
            Iterator ops = binding.getPortType().getOperations().iterator();

            while (ops.hasNext()) {
                Operation op = (Operation) ops.next();
                sc.startElement(new QName(ns,"service-endpoint-method-mapping"),null);//service-endpoint-method-mapping

                //set the java method name
                sc.startElement(new QName(ns,"java-method-name"),null);
                sc.writeString(op.getName());
                sc.endElement();

                sc.startElement(new QName(ns,"wsdl-operation"),null);
                sc.writeString(op.getName());
                sc.endElement();

                HashMap methods = new HashMap();
                Method[] javamethods = emitter.getCls().getMethods();
                for (int i = 0; i < javamethods.length; i++) {
                    methods.put(javamethods[i].getName(), javamethods[i]);
                }
                writeReturnTypes(methods,op);
                //create method param parts mappings    
                sc.endElement();//service-endpoint-method-mapping
            }
            
    }
    
    public void write() throws GenerationFault {
        try {
            sc.startElement(new QName(ns,"java-wsdl-mapping"),null);
            writePackageMapping();
            writeServiceMapping();
            //port mapping
            Port wsdlport = j2eewscontext.getWSDLContext().getTargetPort();
            Binding binding = wsdlport.getBinding();
            if (binding == null)
                throw new UnrecoverableGenerationFault("no port discription not match with the wsdl file");
                
            sc.startElement(new QName(ns,"service-endpoint-interface-mapping"),null);
            
            sc.startElement(new QName(ns,"service-endpoint-interface"),null);
            sc.writeString(emitter.getCls().getName());
            sc.endElement();
            
            sc.registerPrefixForURI("ns2",binding.getQName().getNamespaceURI());
            sc.startElement(new QName(ns,"wsdl-binding"),null);
            sc.writeString("ns2:"+binding.getQName().getLocalPart());
            sc.endElement();

            QName ptName = binding.getPortType().getQName();
            sc.registerPrefixForURI("ns3",ptName.getNamespaceURI());
            sc.startElement(new QName(ns,"wsdl-port-type"),null);
            sc.writeString("ns3:"+ptName.getLocalPart());
            sc.endElement();
            writeMethodMapping(binding);
            sc.endElement(); //service-endpoint-interface-mapping   
            sc.endElement();//java-wsdl-mapping
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
