/*
 * Copyright 2004,2005 The Apache Software Foundation.
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
package org.apache.axis.jaxrpc.client;

import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.wsdl.Operation;
import javax.xml.namespace.QName;
import javax.xml.rpc.Binding;
import javax.xml.rpc.JAXRPCContext;
import javax.xml.rpc.JAXRPCException;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.soap.SOAPFaultException;

/**
 * @author sunja07
 *
 */
public class CallImpl extends BindingProviderImpl implements javax.xml.rpc.Call {

	/**
	 * Field targetEndpointAddress
	 * The location where the webservice should be found
	 */
	private static String targetEndpointAddress;
	
	/**
	 * Field operationName 
	 * The name of the operation to invoke at the service endpoint
	 */
	private static QName operationName;
	
	/**
	 * Field portTypeName
	 * The name of the corresponding port type in the wsdl 
	 */
	private static QName portTypeName;
	
	/**
	 * Field returnType
	 * The xml return type to expect from the method invocation
	 */
	private static QName returnType;
	
	/**
	 * Field paramAndReturnSpecRequired
	 * A boolean flag that should be populated from the wsdl info, if
	 * the operation corresponding to this Call object has to have the
	 * parameters added explicitly and return type specified explicitly
	 */
	protected static boolean paramAndReturnSpecRequired;
	
	/**
	 * Field propertyBag
	 * A hashmap that contains the configured values of standard properties
	 * allowed in the setProperty method.
	 */
	private static HashMap propertyBag = new HashMap();
	
	/**
	 * Field outputParams
	 * A hashmap of {name, value} for the output parameters of the last 
	 * invoked operation. The parameter names in the returned Map are of type 
	 * java.lang.String.
	 */
	private static HashMap outputParams = new HashMap();
	
	/**
	 * 
	 */
	public CallImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Method isParameterAndReturnSpecRequired
	 * Indicates whether addParameter and setReturnType methods are to be 
	 * invoked to specify the parameter and return type specification for a 
	 * specific operation.
	 * @param operationName Qualified name of the operation
	 * @return Returns true if the Call implementation class requires 
	 * addParameter and setReturnType to be invoked in the client code for 
	 * the specified operation. This method returns false otherwise.
	 * @throws java.lang.IllegalArgumentException - If invalid operation name 
	 * is specified
	 */
	public boolean isParameterAndReturnSpecRequired(QName operationName)
			throws IllegalArgumentException {
		//Who is going to set this requirement. Will it be specified in the
		//wsdl? I need clarification on the use case of this flag.
		
		//Since operationName is specifically being provided to us
		//I don't think we should hold a single boolean datamember with this
		//call object. We should either check on the fly some datastructure 
		//and return the decision, I guess OR better would be to have a map
		//that can return the decision taking operationName as the key.
		return paramAndReturnSpecRequired;			
	}

	/**
	 * Method addParameter
	 * Adds a parameter type and mode for a specific operation. Note that the 
	 * client code may not call any addParameter and setReturnType methods 
	 * before calling the invoke method. In this case, the Call implementation 
	 * class determines the parameter types by using reflection on parameters, 
	 * using the WSDL description and configured type mapping registry.
	 * @param paramName Name of the parameter
	 * @param xmlType XML type of the parameter
	 * @param parameterMode Mode of the parameter-whether ParameterMode.IN, 
	 * ParameterMode.OUT, or ParameterMode.INOUT
	 * @throws javax.xml.rpc.JAXRPCException This exception may be thrown if 
	 * the method isParameterAndReturnSpecRequired returns false for this 
	 * operation.
	 * @throws java.lang.IllegalArgumentException If any illegal parameter 
	 * name or XML type is specified
	 */
	public void addParameter(String paramName, QName xmlType,
			ParameterMode parameterMode) throws JAXRPCException,
			IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	/**
	 * Method addParameter
	 * Adds a parameter type and mode for a specific operation. This method is 
	 * used to specify the Java type for either OUT or INOUT parameters. 
	 * @param paramName Name of the parameter
	 * @param xmlType XML type of the parameter
	 * @param javaType Java class of the parameter
	 * @param parameterMode Mode of the parameter-whether ParameterMode.IN, 
	 * OUT or INOUT 
	 * @throws java.lang.IllegalArgumentException If any illegal parameter 
	 * name or XML type is specified 
	 * @throws java.lang.UnsupportedOperationException If this method is not 
	 * supported
	 * @throws javax.xml.rpc.JAXRPCException 
	 * 1.This exception may be thrown if this method is invoked when the 
	 * method isParameterAndReturnSpecRequired returns false.
	 * 2.If specified XML type and Java type mapping is not valid. For 
	 * example, TypeMappingRegistry has no serializers for this mapping. 
	 */
	public void addParameter(String paramName, QName xmlType, Class javaType,
			ParameterMode parameterMode) throws IllegalArgumentException,
			UnsupportedOperationException, JAXRPCException {
		// TODO Auto-generated method stub

	}

	/**
	 * Method getParameterTypeByName
	 * Gets the XML type of a parameter by name 
	 * @param paramName Name of the parameter
	 * @return Returns XML type for the specified parameter
	 */
	public QName getParameterTypeByName(String paramName) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Method setReturnType
	 * Sets the return type for a specific operation. Invoking 
	 * setReturnType(null) removes the return type for this Call object. 
	 * @param xmlType XML data type of the return value
	 * @throws javax.xml.rpc.JAXRPCException This exception may be thrown when 
	 * the method isParameterAndReturnSpecRequired returns false. 
	 * @throws java.lang.IllegalArgumentException If an illegal XML type is 
	 * specified 
	 */
	public void setReturnType(QName xmlType) throws JAXRPCException,
			IllegalArgumentException, UnsupportedOperationException {
		// TODO Auto-generated method stub

	}

	/**
	 * Method setReturnType
	 * Sets the return type for a specific operation.
	 * @param xmlType XML data type of the return value
	 * @param javaType Java Class of the return value
	 * @throws java.lang.UnsupportedOperationException If this method is not 
	 * supported 
	 * @throws java.lang.IllegalArgumentException If an illegal XML type is 
	 * specified
	 * @throws javax.xml.rpc.JAXRPCException
	 * 1. This exception may be thrown if this method is invoked when the 
	 * method isParameterAndReturnSpecRequired returns false.
	 * 2. If XML type and Java type cannot be mapped using the standard type 
	 * mapping or TypeMapping registry
	 */
	public void setReturnType(QName xmlType, Class javaType)
			throws UnsupportedOperationException, IllegalArgumentException,
			JAXRPCException {
		// TODO Auto-generated method stub

	}

	/**
	 * Method getReturnType
	 * Gets the return type for a specific operation
	 * @return Returns the XML type for the return value
	 */
	public QName getReturnType() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Method removeAllParameters
	 * Removes all specified parameters from this Call instance. Note that 
	 * this method removes only the parameters and not the return type. The 
	 * setReturnType(null) is used to remove the return type.
	 * @throws javax.xml.rpc.JAXRPCException This exception may be thrown If 
	 * this method is called when the method isParameterAndReturnSpecRequired 
	 * returns false for this Call's operation.
	 */
	public void removeAllParameters() throws JAXRPCException {
		// TODO Auto-generated method stub

	}

	/**
	 * Method getOperationName
	 * Gets the name of the operation to be invoked using this Call instance.
	 * @return Qualified name of the operation
	 */
	public QName getOperationName() {
		return operationName;
	}

	/**
	 * Method setOperationName
	 * Sets the name of the operation to be invoked using this Call instance. 
	 * @param opName QName of the operation to be invoked using the 
	 * Call instance
	 */
	public void setOperationName(QName opName) {
		operationName = opName;
	}

	/**
	 * Method getPortTypeName
	 * Gets the qualified name of the port type.
	 * @return Qualified name of the port type
	 */
	public QName getPortTypeName() {
		return portTypeName;
	}

	/**
	 * Method setPortTypeName
	 * Sets the qualified name of the port type. 
	 * @param portType Qualified name of the port type
	 */
	public void setPortTypeName(QName portType) {
		portTypeName = portType;
	}

	/**
	 * Method setTargetEndpointAddress
	 * Sets the address of the target service endpoint. This address must 
	 * correspond to the transport specified in the binding for this Call 
	 * instance.
	 * @param address Address of the target service endpoint; specified as an 
	 * URI
	 */
	public void setTargetEndpointAddress(String address) {
		targetEndpointAddress = address;
	}

	/**
	 * Method getTargetEndpointAddress
	 * Gets the address of a target service endpoint. 
	 * @return Address of the target service endpoint as an URI
	 */
	public String getTargetEndpointAddress() {
		return targetEndpointAddress;
	}

	/**
	 * Method setProperty
	 * Sets the value for a named property. JAX-RPC specification specifies a 
	 * standard set of properties that may be passed to the Call.setProperty 
	 * method.
	 * @param name Name of the property
	 * @param value Value of the property
	 * @throws javax.xml.rpc.JAXRPCException
	 * 1. If an optional standard property name is specified, however this 
	 * Call implementation class does not support the configuration of this 
	 * property.
	 * 2. If an invalid (or unsupported) property name is specified or if a 
	 * value of mismatched property type is passed.
	 * 3. If there is any error in the configuration of a valid property.
	 */
	public void setProperty(String name, Object value) throws JAXRPCException {
		// TODO Auto-generated method stub
		
		//Here a long if-elseif...-else list of construct will come, checking
		//the name of the key with the standard property names acceptable
		//and putting the value of the property wherever there is a match or
		//else throwing a JAXRPCException

	}

	/**
	 * Method getProperty
	 * Gets the value of a named property. 
	 * @param name Name of the property
	 * @return Value of the named property
	 * @throws javax.xml.rpc.JAXRPCException if an invalid or unsupported 
	 * property name is passed.
	 */
	public Object getProperty(String name) {
		return propertyBag.get(name);
	}

	/**
	 * Method removeProperty
	 * Removes a named property.
	 * @param name Name of the property
	 * @throws javax.xml.rpc.JAXRPCException if an invalid or unsupported 
	 * property name is passed.
	 */
	public void removeProperty(String name) {
		//do the error check of invalid or unsupported property name
		
		//if error check passed delete the property from the bag
		propertyBag.remove(name);
	}

	/**
	 * Method getPropertyNames
	 * Gets the names of configurable properties supported by this Call object.
	 * @return Iterator for the property names
	 */
	public Iterator getPropertyNames() {
		return propertyBag.values().iterator();
	}

	/**
	 * Method invoke
	 * Invokes a specific operation using a synchronous request-response 
	 * interaction mode.
	 * @param inputParams Object[]--Parameters for this invocation. This 
	 * includes only the input params
	 * @return Returns the return value or null
	 * @throws java.rmi.RemoteException if there is any error in the remote 
	 * method invocation
	 * @throws javax.xml.rpc.soap.SOAPFaultException Indicates a SOAP fault
	 * @throws javax.xml.rpc.JAXRPCException 
	 * 1. If there is an error in the configuration of the Call object
	 * 2. If inputParams do not match the required parameter set (as specified
	 *    through the addParameter invocations or in the corresponding WSDL)
	 * 3. If parameters and return type are incorrectly specified 
	 */
	public Object invoke(Object[] inputParams) throws RemoteException,
			SOAPFaultException, JAXRPCException {

		// ---
		// some solid code will have to go here
		// ---
		
		//I can't comment about the implementation of this method at this 
		//point, but from what the outputParams hashMap should contain I
		//think after the successful(?) invocation we should clear the 
		//outputParams hashMap and fill it with latest operation output
		//parameters.
		
		outputParams.clear();
		//populateOutputParams(operationName); // a private method to be coded
		
		// TODO Auto-generated method stub
		return null;
		
		
	}

	/**
	 * Method invoke
	 * Invokes a specific operation using a synchronous request-response 
	 * interaction mode.
	 * @param operationName QName of the operation
	 * @param inputParams Object[]--Parameters for this invocation. This 
	 * includes only the input params.
	 * @return Returns the return value or null
	 * @throws java.rmi.RemoteException if there is any error in the remote 
	 * method invocation
	 * @throws javax.xml.rpc.soap.SOAPFaultException Indicates a SOAP fault
	 * @throws javax.xml.rpc.JAXRPCException 
	 * 1. If there is an error in the configuration of the Call object
	 * 2. If inputParams do not match the required parameter set (as specified 
	 *    through the addParameter invocations or in the corresponding WSDL)
	 * 3. If parameters and return type are incorrectly specified
	 */
	public Object invoke(QName operationName, Object[] inputParams)
			throws RemoteException {
		
		//---
		// some solid code will have to go here
		// ---
		
		//I can't comment about the implementation of this method at this 
		//point, but from what the outputParams hashMap should contain I
		//think after the successful(?) invocation we should clear the 
		//outputParams hashMap and fill it with latest operation output
		//parameters.
		
		outputParams.clear();
		//populateOutputParams(operationName); // a private method to be coded
		
		// TODO Auto-generated method stub
		return null;
		
	}

	/**
	 * Method invokeOneWay
	 * @param inputParams Object[]--Parameters for this invocation. This 
	 * includes only the input params.
	 * @throws javax.xml.rpc.JAXRPCException if there is an error in the 
	 * configuration of the Call object (example: a non-void return type has 
	 * been incorrectly specified for the one-way call) or if there is any 
	 * error during the invocation of the one-way remote call
	 */
	public void invokeOneWay(Object[] inputParams) throws JAXRPCException {
		// TODO Auto-generated method stub

	}

	/**
	 * Method getOutputParams
	 * Returns a Map of {name, value} for the output parameters of the last 
	 * invoked operation. The parameter names in the returned Map are of type 
	 * java.lang.String.
	 * @return Map Output parameters for the last Call.invoke(). Empty Map is 
	 * returned if there are no output parameters.
	 * @throws JAXRPCException If this method is invoked for a one-way 
	 * operation or is invoked before any invoke method has been called.
	 */
	public Map getOutputParams() throws JAXRPCException {
		return (Map)outputParams;
	}

	/**
	 * Method getOutputValues
	 * Returns a List values for the output parameters of the last invoked 
	 * operation.
	 * @return java.util.List Values for the output parameters. An empty List 
	 * is returned if there are no output values.
	 * @throws JAXRPCException If this method is invoked for a one-way 
	 * operation or is invoked before any invoke method has been called.
	 */
	public List getOutputValues() throws JAXRPCException {
		return (List)outputParams.values();
	}

}
