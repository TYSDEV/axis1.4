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

import com.sun.java.xml.ns.j2Ee.ExceptionMappingType;
import com.sun.java.xml.ns.j2Ee.JavaWsdlMappingDocument;
import com.sun.java.xml.ns.j2Ee.JavaWsdlMappingType;
import com.sun.java.xml.ns.j2Ee.JavaXmlTypeMappingType;
import com.sun.java.xml.ns.j2Ee.MethodParamPartsMappingType;
import com.sun.java.xml.ns.j2Ee.PackageMappingType;
import com.sun.java.xml.ns.j2Ee.PortMappingType;
import com.sun.java.xml.ns.j2Ee.ServiceEndpointInterfaceMappingType;
import com.sun.java.xml.ns.j2Ee.ServiceEndpointMethodMappingType;
import com.sun.java.xml.ns.j2Ee.ServiceInterfaceMappingType;
import com.sun.java.xml.ns.j2Ee.WsdlReturnValueMappingType;
import com.sun.java.xml.ns.j2Ee.XsdQNameType;
import org.apache.axis.wsdl.symbolTable.BindingEntry;
import org.apache.axis.wsdl.symbolTable.PortTypeEntry;
import org.apache.axis.wsdl.symbolTable.ServiceEntry;
import org.apache.geronimo.ews.ws4j2ee.toWs.GenerationFault;
import org.apache.xmlbeans.XmlException;

import javax.wsdl.Binding;
import javax.wsdl.Operation;
import javax.wsdl.Port;
import javax.wsdl.PortType;
import javax.xml.namespace.QName;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Ias (iasandcb@tmax.co.kr)
 */
public class XMLBeansJaxRpcMapper implements JaxRpcMapper {

    private JavaWsdlMappingType mapping;

    public void loadMappingFromInputStream(InputStream is) throws GenerationFault {
        try {
            JavaWsdlMappingDocument mappingdoc = JavaWsdlMappingDocument.Factory.parse(is);
            mapping = mappingdoc.getJavaWsdlMapping();
        } catch (XmlException e) {
            e.printStackTrace();
            throw GenerationFault.createGenerationFault(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw GenerationFault.createGenerationFault(e);
        }
    }

    public void loadMappingFromDir(String path) throws GenerationFault {
        try {
            java.io.InputStream fis = new FileInputStream(path);
            JavaWsdlMappingDocument mappingdoc = JavaWsdlMappingDocument.Factory.parse(fis);
            mapping = mappingdoc.getJavaWsdlMapping();
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
            throw GenerationFault.createGenerationFault(e2);
        } catch (IOException e) {
            e.printStackTrace();
            throw GenerationFault.createGenerationFault(e);
        } catch (XmlException e2) {
            e2.printStackTrace();
            throw GenerationFault.createGenerationFault(e2);
        }
    }

    public int getPackageMappingCount() {
        PackageMappingType[] packagemapping = mapping.getPackageMappingArray();
        return packagemapping.length;
    }

    public String getPackageMappingClassName(int index) {
        PackageMappingType[] packagemapping = mapping.getPackageMappingArray();
        return packagemapping[index].getPackageType().getStringValue();
    }

    public String getPackageMappingURI(int index) {
        PackageMappingType[] packagemapping = mapping.getPackageMappingArray();
        return packagemapping[index].getNamespaceURI().getStringValue();
    }

    /**
     * @return Returns the mapping.
     */
    public JavaWsdlMappingType getMapping() {
        return mapping;
    }

    public String getJavaType(QName typeQName) {
        if (mapping == null) {
            return null;
        }
        JavaXmlTypeMappingType[] typeList = mapping.getJavaXmlTypeMappingArray();
        for (int i = 0; i < typeList.length; i++) {
            JavaXmlTypeMappingType typeMapping = typeList[i];
            XsdQNameType rootType = typeMapping.getRootTypeQname();
            if (rootType == null) {
                String mappedAnonymousTypeName = typeMapping.getAnonymousTypeQname().getStringValue();
                String localPart = typeQName.getLocalPart();
                String revisitedTypeQName = typeQName.getNamespaceURI() + ":" + localPart;
                if (mappedAnonymousTypeName.equals(revisitedTypeQName)) {
                    //TODO this is a quick fix there should be a better way to do this 
                    return J2eeUtils.jni2javaName(typeMapping.getJavaType().getStringValue());
                }
            } else {
                QName typeName = rootType.getQNameValue();
                if (typeQName.equals(typeName)) {
                    String className = typeMapping.getJavaType().getStringValue();
                    //TODO this is a quick fix there should be a better way to do this
                    return J2eeUtils.jni2javaName(className);
                }
            }
        }
        return null;
    }

    public String getExceptionType(QName messageQName) {
        if (mapping == null) {
            return null;
        }
        ExceptionMappingType[] exceptionMappingList = mapping.getExceptionMappingArray();
        for (int j = 0; j < exceptionMappingList.length; j++) {
            ExceptionMappingType exceptionMapping = exceptionMappingList[j];
            QName name = exceptionMapping.getWsdlMessage().getQNameValue();
            if (messageQName.equals(name)) {
                return exceptionMapping.getExceptionType().getStringValue();
            }
        }
        return null;
    }

    public String getPortName(Port port) {
        if (mapping == null) {
            return null;
        }
        ServiceInterfaceMappingType[] serviceList = mapping.getServiceInterfaceMappingArray();
        String portName = port.getName();
        for (int i = 0; i < serviceList.length; i++) {
            PortMappingType[] portList = serviceList[i].getPortMappingArray();
            for (int j = 0; j < portList.length; j++) {
                PortMappingType portMapping = portList[j];
                String mappedPortName = portMapping.getPortName().getStringValue();
                if (portName.equals(mappedPortName)) {
                    String javaPortName = portMapping.getJavaPortName().getStringValue();
                    return javaPortName;
                }
            }
        }
        return null;
    }

    public String getServiceInterfaceName(ServiceEntry entry) {
        if (mapping == null) {
            return null;
        }
        ServiceInterfaceMappingType[] serviceList = mapping.getServiceInterfaceMappingArray();
        QName entryQName = entry.getQName();
        for (int i = 0; i < serviceList.length; i++) {
            QName wsdlServiceName = serviceList[i].getWsdlServiceName().getQNameValue();
            if (entryQName.equals(wsdlServiceName)) {
                String serviceInterfaceName = serviceList[i].getServiceInterface().getStringValue();
                return serviceInterfaceName;
            }
        }
        return null;
    }

    public String getServiceEndpointInterfaceName(PortTypeEntry ptEntry, BindingEntry bEntry) {
        if (mapping == null) {
            return null;
        }
        ServiceEndpointInterfaceMappingType[] serviceList = mapping.getServiceEndpointInterfaceMappingArray();
        Binding binding = bEntry.getBinding();
        PortType portType = ptEntry.getPortType();
        QName bindingQName = binding.getQName();
        QName portTypeQName = portType.getQName();
        for (int i = 0; i < serviceList.length; i++) {
            QName wsdlBinging = serviceList[i].getWsdlBinding().getQNameValue();
            QName wsdlPortType = serviceList[i].getWsdlPortType().getQNameValue();
            if ((bindingQName.equals(wsdlBinging)) && (portTypeQName.equals(wsdlPortType))) {
                String endpointServiceName =
                        serviceList[i].getServiceEndpointInterface().getStringValue();
                return endpointServiceName;
            }
        }
        return null;
    }

    /**
     * @param entry
     * @param operation
     * @param i
     * @return
     */
    public String getJavaMethodParamType(BindingEntry bEntry, Operation operation, int position) {
        if (mapping == null) {
            return null;
        }
        ServiceEndpointInterfaceMappingType[] serviceList = mapping.getServiceEndpointInterfaceMappingArray();
        Binding binding = bEntry.getBinding();
        QName bindingQName = binding.getQName();
        PortType portType = binding.getPortType();
        QName portTypeQName = portType.getQName();
        for (int i = 0; i < serviceList.length; i++) {
            QName wsdlBinging = serviceList[i].getWsdlBinding().getQNameValue();
            QName wsdlPortType = serviceList[i].getWsdlPortType().getQNameValue();
            if ((bindingQName.equals(wsdlBinging)) && (portTypeQName.equals(wsdlPortType))) {
                ServiceEndpointMethodMappingType[] methodList = serviceList[i].getServiceEndpointMethodMappingArray();
                String operationName = operation.getName();
                for (int k = 0; k < methodList.length; k++) {
                    ServiceEndpointMethodMappingType methodMapping = methodList[k];
                    String mappedOperationName = methodMapping.getWsdlOperation().getStringValue();
                    if (operationName.equals(mappedOperationName)) {
                        MethodParamPartsMappingType[] paramList = methodMapping.getMethodParamPartsMappingArray();
                        for (int m = 0; m < paramList.length; m++) {
                            MethodParamPartsMappingType paramPart = paramList[m];
                            if (paramPart.getParamPosition().getBigIntegerValue().intValue() == position) {
                                //TODO this is a quick fix there should be a better way to do this
                                return J2eeUtils.jni2javaName(paramPart.getParamType().getStringValue());
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * @param entry
     * @param operation
     * @return
     */
    public String getJavaMethodReturnType(BindingEntry bEntry, Operation operation) {
        if (mapping == null) {
            return null;
        }
        ServiceEndpointInterfaceMappingType[] serviceList = mapping.getServiceEndpointInterfaceMappingArray();
        Binding binding = bEntry.getBinding();
        QName bindingQName = binding.getQName();
        PortType portType = binding.getPortType();
        QName portTypeQName = portType.getQName();
        for (int i = 0; i < serviceList.length; i++) {
            QName wsdlBinging = serviceList[i].getWsdlBinding().getQNameValue();
            QName wsdlPortType = serviceList[i].getWsdlPortType().getQNameValue();
            if ((bindingQName.equals(wsdlBinging)) && (portTypeQName.equals(wsdlPortType))) {
                ServiceEndpointMethodMappingType[] methodList = serviceList[i].getServiceEndpointMethodMappingArray();
                String operationName = operation.getName();
                for (int k = 0; k < methodList.length; k++) {
                    ServiceEndpointMethodMappingType methodMapping = methodList[k];
                    String mappedOperationName = methodMapping.getWsdlOperation().getStringValue();
                    if (operationName.equals(mappedOperationName)) {
                        WsdlReturnValueMappingType returnValueMapping =
                                methodMapping.getWsdlReturnValueMapping();
                        if (returnValueMapping != null) {
                            //TODO this is a quick fix there should be a better way to do this
                            return J2eeUtils.jni2javaName(returnValueMapping.getMethodReturnValue().getStringValue());
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * @param entry
     * @param operation
     * @return
     */
    public String getJavaMethodName(BindingEntry bEntry, Operation operation) {
        if (mapping == null) {
            return null;
        }
        ServiceEndpointInterfaceMappingType[] serviceList = mapping.getServiceEndpointInterfaceMappingArray();
        Binding binding = bEntry.getBinding();
        QName bindingQName = binding.getQName();
        PortType portType = binding.getPortType();
        QName portTypeQName = portType.getQName();
        for (int i = 0; i < serviceList.length; i++) {
            QName wsdlBinging = serviceList[i].getWsdlBinding().getQNameValue();
            QName wsdlPortType = serviceList[i].getWsdlPortType().getQNameValue();
            if ((bindingQName.equals(wsdlBinging)) && (portTypeQName.equals(wsdlPortType))) {
                ServiceEndpointMethodMappingType[] methodList = serviceList[i].getServiceEndpointMethodMappingArray();
                String operationName = operation.getName();
                for (int k = 0; k < methodList.length; k++) {
                    ServiceEndpointMethodMappingType methodMapping = methodList[k];
                    String mappedOperationName = methodMapping.getWsdlOperation().getStringValue();
                    if (operationName.equals(mappedOperationName)) {
                        return methodMapping.getJavaMethodName().getStringValue();
                    }
                }
            }
        }
        return null;
    }
}
