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
package org.apache.geronimo.ews.jaxrpcmapping;

import java.io.InputStream;

import javax.wsdl.Operation;
import javax.wsdl.Port;
import javax.xml.namespace.QName;

import org.apache.axis.wsdl.symbolTable.BindingEntry;
import org.apache.axis.wsdl.symbolTable.PortTypeEntry;
import org.apache.axis.wsdl.symbolTable.ServiceEntry;
import org.apache.geronimo.ews.ws4j2ee.toWs.GenerationFault;

/**
 * @author Srinath Perera(hemapani@opensource.lk)
 */
public interface JaxRpcMapper {
    public abstract void loadMappingFromInputStream(InputStream is)throws GenerationFault;
    public abstract void loadMappingFromDir(String path)throws GenerationFault;
    /**
     * @return Returns the mapping.
     */
    public int getPackageMappingCount();
    public String getPackageMappingClassName(int index);
    public String getPackageMappingURI(int index);
    public abstract String getJavaType(QName typeQName);
    public abstract String getExceptionType(QName messageQName);
    public abstract String getPortName(Port port);
    public abstract String getServiceInterfaceName(ServiceEntry entry);
    public abstract String getServiceEndpointInterfaceName(
        PortTypeEntry ptEntry,
        BindingEntry bEntry);
    /**
     * @param entry     
     * @param operation 
     * @param i         
     * @return 
     */
    public abstract String getJavaMethodParamType(
        BindingEntry bEntry,
        Operation operation,
        int position);
    /**
     * @param entry     
     * @param operation 
     * @return 
     */
    public abstract String getJavaMethodReturnType(
        BindingEntry bEntry,
        Operation operation);
    /**
     * @param entry     
     * @param operation 
     * @return 
     */
    public abstract String getJavaMethodName(
        BindingEntry bEntry,
        Operation operation);
}
