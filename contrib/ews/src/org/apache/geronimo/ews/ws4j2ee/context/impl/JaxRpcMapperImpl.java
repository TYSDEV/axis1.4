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

package org.apache.geronimo.ews.ws4j2ee.context.impl;

import java.io.InputStream;
import java.io.Writer;
import java.util.Iterator;
import java.util.Map;

import javax.wsdl.Operation;
import javax.wsdl.Part;
import javax.wsdl.Port;
import javax.xml.namespace.QName;

import org.apache.axis.wsdl.symbolTable.BindingEntry;
import org.apache.axis.wsdl.symbolTable.PortTypeEntry;
import org.apache.axis.wsdl.symbolTable.ServiceEntry;
import org.apache.geronimo.ews.jaxrpcmapping.J2eeEmitter;

import org.apache.geronimo.ews.jaxrpcmapping.JaxRpcMapper;
import org.apache.geronimo.ews.ws4j2ee.context.JaxRpcMapperContext;
import org.apache.geronimo.ews.ws4j2ee.toWs.GenerationFault;
import org.apache.geronimo.ews.ws4j2ee.toWs.UnrecoverableGenerationFault;
import org.apache.geronimo.ews.ws4j2ee.utils.Utils;

/**
 * This class wrap the JAXRPCMapper and only expose a interface to
 * the rest of the WS4j2ee.
 * @see org.apache.geronimo.ews.ws4j2ee.context.JaxRpcMapperContext
 * @author Srinath Perera(hemapani@opensource.lk)
 */
public class JaxRpcMapperImpl implements JaxRpcMapperContext {
    private JaxRpcMapper jaxrpcmapper;
    private J2eeEmitter j2ee;

    public JaxRpcMapperImpl(JaxRpcMapper jaxrpcmapper,J2eeEmitter j2ee) {
        this.jaxrpcmapper = jaxrpcmapper;
        this.j2ee = j2ee;
    }

    /**
     * @param messageQName 
     * @return 
     */
    public String getExceptionType(QName messageQName) {
        String exceptionName = jaxrpcmapper.getExceptionType(messageQName);
        if(exceptionName == null){
			exceptionName = j2ee.getJavaName(messageQName);
			if(exceptionName == null)
				throw new UnrecoverableGenerationFault("the exception name in a SEI OP can not be null" +
					"possibly be a bug check the WSDL2Java data extraction");
        }	
		return 	exceptionName;
        
    }

    /**
     * @param bEntry    
     * @param operation 
     * @return 
     */
    public String getJavaMethodName(BindingEntry bEntry, Operation operation) {
    	String opName = jaxrpcmapper.getJavaMethodName(bEntry, operation);
    	if(opName == null)
			opName = operation.getName();
		if(opName == null)
			throw new UnrecoverableGenerationFault("the method name in a SEI OP can not be null" +
				"possibly be a bug check the WSDL2Java data extraction");
		
		return 	Utils.firstCharacterToLowerCase(opName);
    }

    /**
     * @param bEntry    
     * @param operation 
     * @param position  
     * @return 
     */
    public String getJavaMethodParamType(BindingEntry bEntry,
                                         Operation operation,
                                         int position,QName parmType) {
        String type = jaxrpcmapper.getJavaMethodParamType(bEntry, operation, position);
        if(type == null){
			type = j2ee.getJavaName(parmType);
			if(type == null)
				throw new UnrecoverableGenerationFault("the parm type name in a SEI OP can not be null" +
					"possibly be a bug check the WSDL2Java data extraction");  
		}
        return type;
    }

    /**
     * @param bEntry    
     * @param operation 
     * @return 
     */
    public String getJavaMethodReturnType(BindingEntry bEntry,
                                          Operation operation) {
        String returnType = jaxrpcmapper.getJavaMethodReturnType(bEntry, operation);
        if(returnType == null){
		  Map parts = operation.getOutput().getMessage().getParts();
		  if (parts != null) {
			  Iterator returnlist = parts.values().iterator();
			  if (returnlist.hasNext()) {
				  Part part = (Part) returnlist.next();
				  returnType = jaxrpcmapper.getJavaType(part.getTypeName());
				  if(returnType == null)
				  	returnType = j2ee.getJavaName(part.getTypeName());
			  }
		  }
        }
        return returnType;
       // fixed inside 
       // return Utils.jni2javaName(returnType);
    }
	
			


    /**
     * @param typeQName 
     * @return 
     */
    public String getJavaType(QName typeQName) {
        String type = jaxrpcmapper.getJavaType(typeQName);
        if(type == null)
        	type = j2ee.getJavaName(typeQName);
        if(type == null)
			throw new UnrecoverableGenerationFault("the type name can" +
				" not be null possibly be a bug check the WSDL2Java data extraction");
		return type;			
    }


    /**
     * @param port 
     * @return 
     */
    public String getPortName(Port port) {
        String portName = jaxrpcmapper.getPortName(port);
        if(portName == null){
			portName = port.getName();
			if(portName == null)
				throw new UnrecoverableGenerationFault("the portName can" +
				" not be null, possibly be a bug check the WSDL2Java data extraction");
		}
		return portName;
    }

    /**
     * @param ptEntry 
     * @param bEntry  
     * @return 
     */
    public String getServiceEndpointInterfaceName(PortTypeEntry ptEntry,
                                                  BindingEntry bEntry) {
		String seiName = jaxrpcmapper.getServiceEndpointInterfaceName(ptEntry, bEntry);
        if(seiName == null){
			seiName = ptEntry.getName();
			if(seiName == null)
				throw new UnrecoverableGenerationFault("the seiName can" +
				" not be null, possibly be a bug check the WSDL2Java data extraction");
        }
        return seiName;
    }

    /**
     * @param entry 
     * @return 
     */
    public String getServiceInterfaceName(ServiceEntry entry) {
        String serviceInterface = jaxrpcmapper.getServiceInterfaceName(entry);
        if(serviceInterface == null){
			serviceInterface = entry.getName();
			if(serviceInterface == null)
				throw new UnrecoverableGenerationFault("the serviceInterface can" +
				" not be null, possibly be a bug check the WSDL2Java data extraction");
        }
        return serviceInterface;
    }

    /**
     * @param path 
     */
    public void loadMappingFromDir(String path)throws GenerationFault {
        jaxrpcmapper.loadMappingFromDir(path);
    }

    /**
     * @param is 
     */
    public void loadMappingFromInputStream(InputStream is)throws GenerationFault {
        jaxrpcmapper.loadMappingFromInputStream(is);
    }

    /* (non-Javadoc)
     * @see org.apache.geronimo.ews.ws4j2ee.context.JaxRpcMapperContext#serialize()
     */
    public void serialize(Writer out) {
        throw new UnsupportedOperationException("when the wsdl is avalibe serialization not reqired");
    }

    /* (non-Javadoc)
     * @see org.apache.geronimo.ews.ws4j2ee.context.JaxRpcMapperContext#getPackageMappingClassName(int)
     */
    public String getPackageMappingClassName(int index) {
        return jaxrpcmapper.getPackageMappingClassName(index);        
    }

    public int getPackageMappingCount() {
        return jaxrpcmapper.getPackageMappingCount();
    }

    public String getPackageMappingURI(int index) {
        return jaxrpcmapper.getPackageMappingURI(index);
    }

}
