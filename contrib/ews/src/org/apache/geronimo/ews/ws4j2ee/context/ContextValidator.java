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

import org.apache.axis.wsdl.fromJava.Emitter;
import org.apache.axis.wsdl.symbolTable.BindingEntry;
import org.apache.axis.wsdl.symbolTable.Parameter;
import org.apache.axis.wsdl.symbolTable.Parameters;
import org.apache.axis.wsdl.symbolTable.PortEntry;
import org.apache.axis.wsdl.symbolTable.PortTypeEntry;
import org.apache.axis.wsdl.symbolTable.ServiceEntry;
import org.apache.geronimo.ews.ws4j2ee.context.impl.EJBDDContextImpl;
import org.apache.geronimo.ews.ws4j2ee.context.impl.SEIOperationImpl;
import org.apache.geronimo.ews.ws4j2ee.context.j2eeDD.EJBContext;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFPortComponent;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFWebserviceDescription;
import org.apache.geronimo.ews.ws4j2ee.toWs.GenerationFault;
import org.apache.geronimo.ews.ws4j2ee.toWs.UnrecoverableGenerationFault;

import javax.wsdl.Fault;
import javax.wsdl.Operation;
import javax.wsdl.Port;
import javax.wsdl.Service;
import javax.xml.namespace.QName;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>Before the code genaration this class is used to analyis the parse inforemation
 * and make sure that the data populated in the different *Context* are
 * consistent. This class will populate the most of the infomation in the
 * MiscInfo.</p>
 *
 * @author Srinath Perera(hemapani@opensource.lk)
 */
public class ContextValidator {
    private J2EEWebServiceContext context;

    public ContextValidator(J2EEWebServiceContext context) throws GenerationFault {
        try {
            this.context = context;
        } catch (Exception e) {
            throw GenerationFault.createGenerationFault(e);
        }
    }

    /**
     * <p>Just as in the server side this code find the WSDL Service and WSDLport and
     * populate them in the MiscInfo</p>
     *
     * @param emitter
     * @throws GenerationFault
     */
    public void validateWithOutWSDL(Emitter emitter) throws GenerationFault {
        try {
            Collection servicesCollection = context.getWSDLContext().getServices();
            if (servicesCollection.size() > 1)
                throw new UnrecoverableGenerationFault("we are supporting one service yet");
            Iterator services = servicesCollection.iterator();
            Service service = null;
            if (services.hasNext()) {
                service = ((Service) services.next());
                context.getWSDLContext().settargetService(new ServiceEntry(service));
            }
            
            //get the service ports 
            Collection portCollecton = service.getPorts().values();
            //just as before if there are more than one servie just let
            //it failed
            if (servicesCollection.size() > 1)
                throw new UnrecoverableGenerationFault("we are supporting one port yet");
            Iterator portList = portCollecton.iterator();
            Port wsdlport = null;
            while (portList.hasNext()) {
                wsdlport = (Port) portList.next();
                context.getWSDLContext().setTargetPort(wsdlport);
            }
            if (wsdlport == null)
                throw new UnrecoverableGenerationFault("no port discription not match with the wsdl file");
            QName bindingName = wsdlport.getBinding().getQName();
            BindingEntry wsdlbinding = context.getWSDLContext().getBinding(bindingName);
            context.getWSDLContext().settargetBinding(wsdlbinding);
            QName portTypename = wsdlbinding.getBinding().getPortType().getQName();
            PortTypeEntry port = context.getWSDLContext().getPortType(portTypename);
            if (port == null)
                throw new UnrecoverableGenerationFault("port type must exits");
            context.getWSDLContext().setTargetPortType(port);
        } catch (Exception e) {
            throw GenerationFault.createGenerationFault(e);
        }
    }

    public void validateWithWSDL() {
        WSCFWebserviceDescription wscfwsdis = context.getWSCFContext().getWscfdWsDesxription();
        WSCFPortComponent[] ports = wscfwsdis.getPortComponent();
        if (ports == null || ports.length == 0)
            throw new UnrecoverableGenerationFault("no port discription found in the" +
                    "webservices.xml file");
        WSCFPortComponent port = ports[0];
        context.getWSCFContext().setWscfport(port);
        String ejbLink = port.getServiceImplBean().getEjblink();
        // context.getMiscInfo().setJaxrpcSEI(port.getServiceEndpointInterface());
        if (ejbLink != null) {
            String bean = port.getServiceEndpointInterface() + "Bean";
            String home = port.getServiceEndpointInterface() + "Home";
            String remote = port.getServiceEndpointInterface() + "EJB";
            String local = port.getServiceEndpointInterface() + "LocalEJB";
            String localHome = port.getServiceEndpointInterface() + "LocalHome";
            EJBContext ejbcontext = context.getEJBDDContext();
            if (ejbcontext == null) {
                ejbcontext = new EJBDDContextImpl(ejbLink, bean, home, remote, localHome, local);
            }
//TODO remove this if not needed
            if (ejbcontext.getImplBean() == null)
                ejbcontext.setImplBean(bean);
            if (ejbcontext.getEjbhomeInterface() == null)
                ejbcontext.setEjbhomeInterface(home);
            if (ejbcontext.getEjbRemoteInterface() == null)
                ejbcontext.setEjbRemoteInterface(remote);
            if (ejbcontext.getEjbLocalHomeInterfce() == null)
                ejbcontext.setEjbLocalHomeInterfce(localHome);
            if (ejbcontext.getEjbLocalInterface() == null)
                ejbcontext.setEjbLocalHomeInterfce(local);
            context.getMiscInfo().setJ2eeComponetLink(port.getServiceImplBean().getEjblink());
        } else {
            context.getMiscInfo().setImplwithEJB(false);
        }
        QName portName = new QName(port.getWsdlPort().getNamespaceURI(), port.getWsdlPort().getLocalpart());
        PortEntry wsdlport = context.getWSDLContext().getPort(portName);
        if (wsdlport == null)
            throw new UnrecoverableGenerationFault("wsdl port can not be null, you may have the " +
                    "wsdlport define wrongly on the webservices.xml");
        Iterator services = context.getWSDLContext().getServices().iterator();
        while (services.hasNext()) {
            ServiceEntry service = (ServiceEntry) services.next();
            Iterator portList = service.getService().getPorts().values().iterator();
            while (portList.hasNext()) {
                if (((Port) portList.next()) == wsdlport.getPort()) {
                    context.getWSDLContext().settargetService(service);
                    break;
                }
            }
        }
        if (wsdlport == null)
            throw new UnrecoverableGenerationFault("no port discription not match with the wsdl file");
        QName bindingName = wsdlport.getPort().getBinding().getQName();
        BindingEntry wsdlbinding = context.getWSDLContext().getBinding(bindingName);
        context.getWSDLContext().settargetBinding(wsdlbinding);
        QName portTypename = wsdlbinding.getBinding().getPortType().getQName();
        context.getWSDLContext().setTargetPortType(context.getWSDLContext().getPortType(portTypename));
        context.validate();
			
        //find and populate the information about the SEI in the 
        //MiscInfo
        String seiName = context.getJAXRPCMappingContext()
                .getServiceEndpointInterfaceName(context.getWSDLContext().getTargetPortType(), context.getWSDLContext().gettargetBinding());
        context.getMiscInfo().setJaxrpcSEI(seiName);
        List operations = context.getWSDLContext().getTargetPortType().getPortType().getOperations();
        for (int i = 0; i < operations.size(); i++) {
            SEIOperation seiOp = new SEIOperationImpl();
            Operation op = (Operation) operations.get(i);
            BindingEntry binding = context.getWSDLContext().gettargetBinding();
            
            //got to get the same parameter order as the JAXRPC mapper does
            //So I am using the same methods. Can somebody find something better?? 
            Parameters parms = binding.getParameters(op);
            //set return type	
            String returnType = context.getJAXRPCMappingContext().getJavaMethodReturnType(binding, op);
            seiOp.setReturnType(returnType);


            //set method name 
            String methodName = context.getJAXRPCMappingContext().getJavaMethodName(binding, op);
            seiOp.setMethodName(methodName);

            //Iterator paramlist = parms.list.iterator();
            String parameterName = null;
            String parameterType = null;	  
			
            //find the parameters and add each parameter to parameter list 
            for (int paramCount = 0; paramCount < parms.list.size(); paramCount++) {
                Parameter p = (Parameter) parms.list.get(paramCount);
                parameterName = p.getName();
                parameterType = context.getJAXRPCMappingContext().getJavaMethodParamType(binding, op, paramCount, p.getType().getQName());
                seiOp.addParameter(parameterType, parameterName);
            }
            //let us find the faults
            Map faults = parms.faults;
            if (faults != null) {
                Iterator it = faults.values().iterator();
                while (it.hasNext()) {
                    Fault fault = (Fault) it.next();
                    String faulltName = context.getJAXRPCMappingContext()
                            .getExceptionType(fault.getMessage().getQName());
                    seiOp.addFault(faulltName);
                }
            }
            context.getMiscInfo().setSEIOperations(seiOp);
        }
//        
//        String serviceName = context.getJAXRPCMappingContext()
//                .getServiceInterfaceName(context.getMiscInfo().gettargetService());
    }
}
