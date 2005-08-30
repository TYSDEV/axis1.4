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

package org.apache.geronimo.ews.ws4j2ee.context.wsdl;

import org.apache.axis.wsdl.symbolTable.BindingEntry;
import org.apache.axis.wsdl.symbolTable.Element;
import org.apache.axis.wsdl.symbolTable.PortEntry;
import org.apache.axis.wsdl.symbolTable.PortTypeEntry;
import org.apache.axis.wsdl.symbolTable.ServiceEntry;
import org.apache.axis.wsdl.symbolTable.TypeEntry;
import org.apache.geronimo.ews.ws4j2ee.context.wsdl.type.SchemaType;

import javax.wsdl.Port;
import javax.wsdl.Service;
import javax.xml.namespace.QName;
import java.util.Collection;
import java.util.Map;

/**
 * <p>This interface expose the information contains in the WSDL.
 * This interface exposed the information. It does not expose how the
 * the information is parsed.</p>
 * <p>This interface and related interfaces has both getter and setter methods
 * but who ever implements this interface might not need the both.
 * e.g. there can be two concreate implementations for this class
 * for the cases</p>
 * <ul>
 * <li>have WSDL</li>
 * <li>do not have WSDL</li>
 * </ul>
 * <p>if some method is not requried please throw java.lang.UnsupportedOperationException</p>
 *
 * @author Srinath Perera(hemapani@opensource.lk)
 */
public interface WSDLContext {
    /**
     * @return all the ports define in the WSDL
     */
    public Collection getPortTypes();

    /**
     * @param portname
     * @return PortType whose name is equal to portname.
     *         return null if no PortType with this name.
     */
    public PortTypeEntry getPortType(QName portname);

    /**
     * @return all the bindings define in the WSDL
     */
    public Collection getBindings();

    /**
     * @param bindingname
     * @return Binding whose name is equal to bindingname.
     *         return null if no Binding with this name.
     */
    public BindingEntry getBinding(QName bindingname);

    /**
     * @return all the ports define in the WSDL
     */
    public Collection getServices();

    /**
     * @param servicename
     * @return Service whose name is equal to servicename.
     *         return null if no Service with this name.
     */
    public ServiceEntry getService(QName servicename);

    /**
     * add Service information
     *
     * @param service
     */
    public void addService(Service service);

    /**
     * @return all the Types define in the WSDL
     */
    public Map getTypes();

    /**
     * @param typename
     * @return Type whose name is equal to typename.
     *         return null if no Type with this name.
     */
    public TypeEntry getType(QName typename);

    /**
     * add a Type
     *
     * @param type
     */
    public void addType(SchemaType type);

    public Element getElement(QName name);

    public PortEntry getPort(QName name);

    public String getTargetNSURI();

    /**
     * WSDL artifacts correponds to the current WSCF port.
     * If one element is in the wsdl theu are used. How to select them
     * if there is more than one is still to do.
     *
     * @return
     */
    public ServiceEntry gettargetService();

    public void settargetService(ServiceEntry service);

    public BindingEntry gettargetBinding();

    public void settargetBinding(BindingEntry binding);

    public PortTypeEntry getTargetPortType();

    public void setTargetPortType(PortTypeEntry port);

    public void setTargetPort(Port port);

    public Port getTargetPort();

    public void validate();

}
