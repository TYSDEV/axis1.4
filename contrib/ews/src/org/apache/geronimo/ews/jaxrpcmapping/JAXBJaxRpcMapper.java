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

import org.apache.axis.wsdl.symbolTable.BindingEntry;
import org.apache.axis.wsdl.symbolTable.PortTypeEntry;
import org.apache.axis.wsdl.symbolTable.ServiceEntry;
import org.apache.geronimo.ews.jaxrpcmapping.descriptor.ExceptionMappingType;
import org.apache.geronimo.ews.jaxrpcmapping.descriptor.JavaWsdlMapping;
import org.apache.geronimo.ews.jaxrpcmapping.descriptor.JavaXmlTypeMappingType;
import org.apache.geronimo.ews.jaxrpcmapping.descriptor.MethodParamPartsMappingType;
import org.apache.geronimo.ews.jaxrpcmapping.descriptor.PortMappingType;
import org.apache.geronimo.ews.jaxrpcmapping.descriptor.ServiceEndpointInterfaceMappingType;
import org.apache.geronimo.ews.jaxrpcmapping.descriptor.ServiceEndpointMethodMappingType;
import org.apache.geronimo.ews.jaxrpcmapping.descriptor.ServiceInterfaceMappingType;
import org.apache.geronimo.ews.jaxrpcmapping.descriptor.WsdlReturnValueMappingType;
import org.apache.geronimo.ews.jaxrpcmapping.descriptor.XsdQNameType;

import com.sun.java.xml.ns.j2Ee.FullyQualifiedClassType;
import com.sun.java.xml.ns.j2Ee.PackageMappingType;
import com.sun.java.xml.ns.j2Ee.XsdAnyURIType;

import javax.wsdl.Binding;
import javax.wsdl.Operation;
import javax.wsdl.Port;
import javax.wsdl.PortType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

/**
 * @author Ias (iasandcb@tmax.co.kr)
 */
public class JAXBJaxRpcMapper implements JaxRpcMapper {

    private JavaWsdlMapping mapping;

    public void loadMappingFromInputStream(InputStream is) {
        JAXBContext jc = null;
        try {
            jc = JAXBContext.newInstance("org.apache.geronimo.ews.jaxrpcmapping.descriptor");
        } catch (JAXBException e1) {
            e1.printStackTrace();
        }
        Unmarshaller u = null;
        try {
            u = jc.createUnmarshaller();
            mapping = (JavaWsdlMapping) u.unmarshal(is);
        } catch (JAXBException e2) {
            //e2.printStackTrace();
            throw new RuntimeException(e2);
        }
    }

    public void loadMappingFromDir(String path) {
        JAXBContext jc = null;
        try {
            jc = JAXBContext.newInstance("org.apache.geronimo.ews.jaxrpcmapping.descriptor");
        } catch (JAXBException e1) {
            e1.printStackTrace();
        }
        Unmarshaller u = null;
        try {
            u = jc.createUnmarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        try {
            java.io.InputStream fis = new FileInputStream(path);
            mapping = (JavaWsdlMapping) u.unmarshal(fis);
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        } catch (JAXBException e2) {
            e2.printStackTrace();
        }
    }

    /**
     * @return Returns the mapping.
     */
    public JavaWsdlMapping getMapping() {
        return mapping;
    }

    public String getJavaType(QName typeQName) {
        if (mapping == null) {
            return null;
        }
        List typeList = mapping.getJavaXmlTypeMapping();
        for (Iterator i = typeList.iterator(); i.hasNext();) {
            JavaXmlTypeMappingType typeMapping = (JavaXmlTypeMappingType) i.next();
            XsdQNameType rootType = typeMapping.getRootTypeQname();
            if (rootType == null) {
                String mappedAnonymousTypeName = typeMapping.getAnonymousTypeQname().getValue();
                String localPart = typeQName.getLocalPart();
                String revisitedTypeQName = typeQName.getNamespaceURI() + ":" + localPart;
                if (mappedAnonymousTypeName.equals(revisitedTypeQName)) {
                	//TODO this is a quick fix there should be a better way to do this 
                    return J2eeUtils.jni2javaName(typeMapping.getJavaType().getValue());
                }
            } else {
                QName typeName = rootType.getValue();
                if (typeQName.equals(typeName)) {
                    String className = typeMapping.getJavaType().getValue();
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
        List exceptionMappingList = mapping.getExceptionMapping();
        for (Iterator j = exceptionMappingList.iterator(); j.hasNext();) {
            ExceptionMappingType exceptionMapping = (ExceptionMappingType) j.next();
            if (messageQName.equals(exceptionMapping.getWsdlMessage().getValue())) {
                return exceptionMapping.getExceptionType().getValue();
            }
        }
        return null;
    }

    public String getPortName(Port port) {
        if (mapping == null) {
            return null;
        }
        List serviceList = mapping.getServiceInterfaceMappingAndServiceEndpointInterfaceMapping();
        String portName = port.getName();
        for (Iterator i = serviceList.iterator(); i.hasNext();) {
            Object object = i.next();
            if (object instanceof ServiceInterfaceMappingType) {
                ServiceInterfaceMappingType interfaceMapping = (ServiceInterfaceMappingType) object;
                List portList = interfaceMapping.getPortMapping();
                for (Iterator j = portList.iterator(); j.hasNext();) {
                    PortMappingType portMapping = (PortMappingType) j.next();
                    String mappedPortName = portMapping.getPortName().getValue();
                    if (portName.equals(mappedPortName)) {
                        String javaPortName = portMapping.getJavaPortName().getValue();
                        return javaPortName;
                    }
                }
            }
        }
        return null;
    }

    public String getServiceInterfaceName(ServiceEntry entry) {
        if (mapping == null) {
            return null;
        }
        List serviceList = mapping.getServiceInterfaceMappingAndServiceEndpointInterfaceMapping();
        QName entryQName = entry.getQName();
        for (Iterator i = serviceList.iterator(); i.hasNext();) {
            ServiceInterfaceMappingType interfaceMapping = (ServiceInterfaceMappingType) i.next();
            QName wsdlServiceName = interfaceMapping.getWsdlServiceName().getValue();
            if (entryQName.equals(wsdlServiceName)) {
                String serviceInterfaceName = interfaceMapping.getServiceInterface().getValue();
                return serviceInterfaceName;
            }
        }
        return null;
    }

    public String getServiceEndpointInterfaceName(PortTypeEntry ptEntry, BindingEntry bEntry) {
        if (mapping == null) {
            return null;
        }
        List serviceList = mapping.getServiceInterfaceMappingAndServiceEndpointInterfaceMapping();
        Binding binding = bEntry.getBinding();
        PortType portType = ptEntry.getPortType();
        QName bindingQName = binding.getQName();
        QName portTypeQName = portType.getQName();
        for (Iterator i = serviceList.iterator(); i.hasNext();) {
            Object object = i.next();
            if (!(object instanceof ServiceEndpointInterfaceMappingType)) {
                continue;
            }
            ServiceEndpointInterfaceMappingType endpointInterfaceMapping =
                    (ServiceEndpointInterfaceMappingType) object;
            QName wsdlBinging = endpointInterfaceMapping.getWsdlBinding().getValue();
            QName wsdlPortType = endpointInterfaceMapping.getWsdlPortType().getValue();
            if ((bindingQName.equals(wsdlBinging)) && (portTypeQName.equals(wsdlPortType))) {
                String endpointServiceName =
                        endpointInterfaceMapping.getServiceEndpointInterface().getValue();
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
        List serviceList = mapping.getServiceInterfaceMappingAndServiceEndpointInterfaceMapping();
        Binding binding = bEntry.getBinding();
        QName bindingQName = binding.getQName();
        PortType portType = binding.getPortType();
        QName portTypeQName = portType.getQName();
        for (Iterator j = serviceList.iterator(); j.hasNext();) {
            Object object = j.next();
            if (!(object instanceof ServiceEndpointInterfaceMappingType)) {
                continue;
            }
            ServiceEndpointInterfaceMappingType endpointInterfaceMapping =
                    (ServiceEndpointInterfaceMappingType) object;
            QName wsdlBinging = endpointInterfaceMapping.getWsdlBinding().getValue();
            QName wsdlPortType = endpointInterfaceMapping.getWsdlPortType().getValue();
            if ((bindingQName.equals(wsdlBinging)) && (portTypeQName.equals(wsdlPortType))) {
                List methodList = endpointInterfaceMapping.getServiceEndpointMethodMapping();
                String operationName = operation.getName();
                for (Iterator k = methodList.iterator(); k.hasNext();) {
                    ServiceEndpointMethodMappingType methodMapping =
                            (ServiceEndpointMethodMappingType) k.next();
                    String mappedOperationName = methodMapping.getWsdlOperation().getValue();
                    if (operationName.equals(mappedOperationName)) {
                        List paramList = methodMapping.getMethodParamPartsMapping();
                        for (Iterator l = paramList.iterator(); l.hasNext();) {
                            MethodParamPartsMappingType paramPart =
                                    (MethodParamPartsMappingType) l.next();
                            if (paramPart.getParamPosition().getValue().intValue() == position) {
								//TODO this is a quick fix there should be a better way to do this
                                return J2eeUtils.jni2javaName(paramPart.getParamType().getValue());
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
        List serviceList = mapping.getServiceInterfaceMappingAndServiceEndpointInterfaceMapping();
        Binding binding = bEntry.getBinding();
        QName bindingQName = binding.getQName();
        PortType portType = binding.getPortType();
        QName portTypeQName = portType.getQName();
        for (Iterator j = serviceList.iterator(); j.hasNext();) {
            Object object = j.next();
            if (!(object instanceof ServiceEndpointInterfaceMappingType)) {
                continue;
            }
            ServiceEndpointInterfaceMappingType endpointInterfaceMapping =
                    (ServiceEndpointInterfaceMappingType) object;
            QName wsdlBinging = endpointInterfaceMapping.getWsdlBinding().getValue();
            QName wsdlPortType = endpointInterfaceMapping.getWsdlPortType().getValue();
            if ((bindingQName.equals(wsdlBinging)) && (portTypeQName.equals(wsdlPortType))) {
                List methodList = endpointInterfaceMapping.getServiceEndpointMethodMapping();
                String operationName = operation.getName();
                for (Iterator k = methodList.iterator(); k.hasNext();) {
                    ServiceEndpointMethodMappingType methodMapping =
                            (ServiceEndpointMethodMappingType) k.next();
                    String mappedOperationName = methodMapping.getWsdlOperation().getValue();
                    if (operationName.equals(mappedOperationName)) {
                        WsdlReturnValueMappingType returnValueMapping =
                                methodMapping.getWsdlReturnValueMapping();
                        if (returnValueMapping != null) {
							//TODO this is a quick fix there should be a better way to do this
                            return J2eeUtils.jni2javaName(returnValueMapping.getMethodReturnValue().getValue());
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
        List serviceList = mapping.getServiceInterfaceMappingAndServiceEndpointInterfaceMapping();
        Binding binding = bEntry.getBinding();
        QName bindingQName = binding.getQName();
        PortType portType = binding.getPortType();
        QName portTypeQName = portType.getQName();
        for (Iterator j = serviceList.iterator(); j.hasNext();) {
            Object object = j.next();
            if (!(object instanceof ServiceEndpointInterfaceMappingType)) {
                continue;
            }
            ServiceEndpointInterfaceMappingType endpointInterfaceMapping =
                    (ServiceEndpointInterfaceMappingType) object;
            QName wsdlBinging = endpointInterfaceMapping.getWsdlBinding().getValue();
            QName wsdlPortType = endpointInterfaceMapping.getWsdlPortType().getValue();
            if ((bindingQName.equals(wsdlBinging)) && (portTypeQName.equals(wsdlPortType))) {
                List methodList = endpointInterfaceMapping.getServiceEndpointMethodMapping();
                String operationName = operation.getName();
                for (Iterator k = methodList.iterator(); k.hasNext();) {
                    ServiceEndpointMethodMappingType methodMapping =
                            (ServiceEndpointMethodMappingType) k.next();
                    String mappedOperationName = methodMapping.getWsdlOperation().getValue();
                    if (operationName.equals(mappedOperationName)) {
                        return methodMapping.getJavaMethodName().getValue();
                    }
                }
            }
        }
        return null;
    }
    public int getPackageMappingCount(){
        List list = mapping.getPackageMapping();
        return list.size();
    }


    public String getPackageMappingClassName(int index){
        PackageMappingType pack = (PackageMappingType) mapping.getPackageMapping().get(index);
       FullyQualifiedClassType qPack = pack.getPackageType();
        return qPack.getStringValue();
    }

    public String getPackageMappingURI(int index){
        PackageMappingType pack = (PackageMappingType) mapping.getPackageMapping().get(index);
        XsdAnyURIType namespace = pack.getNamespaceURI();
        return namespace.getStringValue();
    }
}
