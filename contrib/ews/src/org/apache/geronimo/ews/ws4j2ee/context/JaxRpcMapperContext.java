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

package org.apache.geronimo.ews.ws4j2ee.context;

import org.apache.axis.wsdl.symbolTable.BindingEntry;
import org.apache.axis.wsdl.symbolTable.PortTypeEntry;
import org.apache.axis.wsdl.symbolTable.ServiceEntry;
import org.apache.geronimo.ews.ws4j2ee.toWs.GenerationFault;

import javax.wsdl.Operation;
import javax.wsdl.Port;
import javax.xml.namespace.QName;
import java.io.InputStream;
import java.io.Writer;

/**
 * <p>This is the interface which is shared by the application as the
 * jaxrpcmapper</p>
 *
 * @author Srinath Perera(hemapani@opensource.lk)
 */
public interface JaxRpcMapperContext {
    /**
     * how the excpetion type map to the java class
     *
     * @param messageQName
     * @return
     */
    public abstract String getExceptionType(QName messageQName);

    /**
     * how the wsdl operation name map to the java method name
     *
     * @param bEntry
     * @param operation
     * @return
     */
    public abstract String getJavaMethodName(BindingEntry bEntry,
                                             Operation operation);

    /**
     * how the parameters in the wsdl opration map to the java method parameters.
     *
     * @param bEntry
     * @param operation
     * @param position
     * @return
     */
    public abstract String getJavaMethodParamType(BindingEntry bEntry,
                                                  Operation operation,
                                                  int position, QName type);

    /**
     * how the return type in the wsdl opration map to the java method return type.
     *
     * @param bEntry
     * @param operation
     * @return
     */
    public abstract String getJavaMethodReturnType(BindingEntry bEntry,
                                                   Operation operation);

    /**
     * QName to java type mapping
     *
     * @param typeQName
     * @return
     */
    public abstract String getJavaType(QName typeQName);

    public int getPackageMappingCount();

    public String getPackageMappingClassName(int index);

    public String getPackageMappingURI(int index);

    /**
     * get the name of the port used in this webservice
     *
     * @param port
     * @return
     */
    public abstract String getPortName(Port port);

    /**
     * how port type maps to the java class
     *
     * @param ptEntry
     * @param bEntry
     * @return
     */
    public abstract String getServiceEndpointInterfaceName(PortTypeEntry ptEntry,
                                                           BindingEntry bEntry);

    /**
     * @param entry
     * @return
     */
    public abstract String getServiceInterfaceName(ServiceEntry entry);

    /**
     * @param path
     */
    public abstract void loadMappingFromDir(String path) throws GenerationFault;

    /**
     * @param is
     */
    public abstract void loadMappingFromInputStream(InputStream is) throws GenerationFault;

    public void serialize(Writer out) throws GenerationFault;
}
