/*
 * The Apache Software License, Version 1.1
 *
 *
 * Copyright (c) 2001-2004 The Apache Software Foundation.  All rights
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

package org.apache.geronimo.ews.ws4j2ee.context;

import org.apache.axis.wsdl.symbolTable.BindingEntry;
import org.apache.axis.wsdl.symbolTable.PortTypeEntry;
import org.apache.axis.wsdl.symbolTable.ServiceEntry;
import org.apache.geronimo.ews.jaxrpcmapping.descriptor.JavaWsdlMapping;
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
     * @param messageQName 
     * @return 
     */
    public abstract String getExceptionType(QName messageQName);

    /**
     * @param bEntry    
     * @param operation 
     * @return 
     */
    public abstract String getJavaMethodName(BindingEntry bEntry,
                                             Operation operation);

    /**
     * @param bEntry    
     * @param operation 
     * @param position  
     * @return 
     */
    public abstract String getJavaMethodParamType(BindingEntry bEntry,
                                                  Operation operation,
                                                  int position);

    /**
     * @param bEntry    
     * @param operation 
     * @return 
     */
    public abstract String getJavaMethodReturnType(BindingEntry bEntry,
                                                   Operation operation);

    /**
     * @param typeQName 
     * @return 
     */
    public abstract String getJavaType(QName typeQName);

    /**
     * @return 
     */
    public abstract JavaWsdlMapping getMapping();

    /**
     * @param port 
     * @return 
     */
    public abstract String getPortName(Port port);

    /**
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
    public abstract void loadMappingFromDir(String path);

    /**
     * @param is 
     */
    public abstract void loadMappingFromInputStream(InputStream is);

    public void serialize(Writer out) throws GenerationFault;
}