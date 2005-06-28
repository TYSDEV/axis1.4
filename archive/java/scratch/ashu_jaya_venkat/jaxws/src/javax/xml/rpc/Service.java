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

package javax.xml.rpc;

import javax.xml.rpc.encoding.TypeMappingRegistry;
import javax.xml.rpc.security.SecurityConfiguration;
import javax.xml.rpc.handler.HandlerRegistry;

/**
 * Interface Service
 * Service acts as a factory of the following:
 * 1. Instance of javax.xml.rpc.Dispatch for dynamic message-oriented invocation of a remote operation.
 * 2. Instance of javax.xml.rpc.Call for the dynamic invocation of a remote operation on the target service endpoint.
 * 3. Instance of a generated stub class.
 * 4. Dynamic proxy for the target service endpoint.
 *
 * @version 2.0
 * @author sunja07
 */
public interface Service {
	
	/** 
	 * I've tried to create a nested class in here. Absolutely
	 * needs a revisit. Don't rely on this as it exists now.
	 */
	public static enum Mode { //extends java.lang.Enum<Service.Mode> {
		
		MESSAGE,
		
		PAYLOAD;
		
		/**
		 * Method values
		 * Returns an array containing the constants of this enum type, in the order they're declared. This method may be used to iterate over the constants as follows:
		 * <code>
		 * 		for(Service.Mode c : Service.Mode.values())
		 *			System.out.println(c);
		 * </code>
		 * @return
		 */
/*		public static final Mode[] values() {
			return null;
		}
*/		
		/**
		 * Method valueOf
		 * Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.) 
		 * @param name the name of the enum constant to be returned.
		 * @return the enum constant with the specified name
		 * @throws java.lang.IllegalArgumentException if this enum type has no constant with the specified name
		 */
/*		public static Service.Mode valueOf(java.lang.String name) throws java.lang.IllegalArgumentException {
			return null;
		}
*/		
	}
	
	/**
	 * Method getPort
	 * The getPort method returns either an instance of a generated stub implementation class or a dynamic proxy. A service client uses this dynamic proxy to invoke operations on the target service endpoint. The serviceEndpointInterface specifies the service endpoint interface that is supported by the created dynamic proxy or stub instance.
	 * @param portName Qualified name of the service endpoint in the WSDL service description
	 * @param serviceEndpointInterface Service endpoint interface supported by the dynamic proxy or stub instance 
	 * @return java.rmi.Remote Stub instance or dynamic proxy that supports the specified service endpoint interface 
	 * @throws ServiceException This exception is thrown in the following cases:
	 * 1. If there is an error in creation of the dynamic proxy or stub instance
	 * 2. If there is any missing WSDL metadata as required by this method 
	 * 3. Optionally, if an illegal serviceEndpointInterface or portName is specified
	 * @see java.lang.reflect.Proxy, java.lang.reflect.InvocationHandler 
	 */
	java.rmi.Remote getPort(javax.xml.namespace.QName portName,
            java.lang.Class serviceEndpointInterface)
            throws ServiceException;
	
	/**
	 * Method getPort
	 * The getPort method returns either an instance of a generated stub implementation class or a dynamic proxy. The parameter serviceEndpointInterface specifies the service endpoint interface that is supported by the returned stub or proxy. In the implementation of this method, the JAX-RPC runtime system takes the responsibility of selecting a protocol binding (and a port) and configuring the stub accordingly. The returned Stub instance should not be reconfigured by the client.
	 * @param serviceEndpointInterface Service endpoint interface 
	 * @return Stub instance or dynamic proxy that supports the specified service endpoint interface 
	 * @throws ServiceException This exception is thrown in the following cases:
	 * 1. If there is an error during creation of stub instance or dynamic proxy 
	 * 2. If there is any missing WSDL metadata as required by this method
	 * 3. Optionally, if an illegal serviceEndpointInterface is specified 
	 */
	java.rmi.Remote getPort(java.lang.Class serviceEndpointInterface)
    throws ServiceException;
	
	/**
	 * Method createPort
	 * Creates a new port for the service. Ports created in this way contain no WSDL port type information and can only be used for creating Dispatch and Call instances.
	 * @param portName Qualified name for the target service endpoint
	 * @param bindingId A URI identifier of a binding.
	 * @param endpointAddress Address of the target service endpoint as a URI 
	 * @throws ServiceException If any error in the creation of the port
	 * @see javax.xml.rpc.soap.SOAPBinding.SOAP11HTTP_BINDING
	 */
	void createPort(javax.xml.namespace.QName portName,
            java.net.URI bindingId,
            java.lang.String endpointAddress)
            throws ServiceException;
	
	// This involves generics, needs a revisit
	/**
	 * Method createDispatch
	 * Creates a Dispatch instance for use with objects of the users choosing.
	 * 
	 * @param - Qualified name for the target service endpoint
	 * @param - The class of object used to messages or message payloads. Implementations are required to support javax.xml.transform.Source and javax.xml.soap.SOAPMessage.
	 * @param - Controls whether the created dispatch instance is message or payload oriented, i.e. whether the user will work with complete protocol messages or message payloads. E.g. when using the SOAP protocol, this parameter controls whether the user will work with SOAP messages or the contents of a SOAP body. Mode must be MESSAGE when type is SOAPMessage.
	 * @return Dispatch instance 
	 * @throws ServiceException - If any error in the creation of the Dispatch object
	 * @see javax.xml.transform.Source, javax.xml.soap.SOAPMessage
	 */
	<T> Dispatch<T> createDispatch(javax.xml.namespace.QName portName,
            java.lang.Class<T> type,
            Service.Mode mode)
        throws ServiceException;
      
	// This involves generics, needs a revisit
	/**
	 * Method createDispatch
	 * Creates a Dispatch instance for use with JAXB generated objects.
	 * 
	 * @param portName - Qualified name for the target service endpoint
	 * @param context - The JAXB context used to marshall and unmarshall messages or message payloads.
	 * @param mode - Controls whether the created dispatch instance is message or payload oriented, i.e. whether the user will work with complete protocol messages or message payloads. E.g. when using the SOAP protocol, this parameter controls whether the user will work with SOAP messages or the contents of a SOAP body.
	 * @return Dispatch instance 
	 * @throws ServiceException - If any error in the creation of the Dispatch object
	 * @see JAXBContext
	 */
	Dispatch<java.lang.Object> createDispatch(javax.xml.namespace.QName portName,
            javax.xml.bind.JAXBContext context,
            Service.Mode mode)
            throws ServiceException;
     
	/**
	 * Method getCalls
	 * Gets an array of preconfigured Call objects for invoking operations on the specified port. There is one Call object per operation that can be invoked on the specified port. Each Call object is pre-configured and does not need to be configured using the setter methods on Call interface.
	 * Each invocation of the getCalls method returns a new array of preconfigured Call objects 
	 * This method requires the Service implementation class to have access to the WSDL related metadata.
	 * @param portName Qualified name for the target service endpoint 
	 * @return Call[] Array of pre-configured Call objects 
	 * @throws ServiceException If this Service class does not have access to the required WSDL metadata or if an illegal portName is specified.
	 */
	Call[] getCalls(javax.xml.namespace.QName portName)
    throws ServiceException;
	
	/**
	 * Method createCall
	 * Creates a Call instance.
	 * @param portName Qualified name for the target service endpoint 
	 * @return Call instance 
	 * @throws ServiceException If any error in the creation of the Call object
	 */
	Call createCall(javax.xml.namespace.QName portName)
    throws ServiceException;
	
	/**
	 * Method createCall
	 * Creates a Call instance. 
	 * @param portName Qualified name for the target service endpoint
	 * @param operationName Qualified Name of the operation for which this Call object is to be created.
	 * @return Call instance 
	 * @throws ServiceException If any error in the creation of the Call object
	 */
	Call createCall(javax.xml.namespace.QName portName,
            javax.xml.namespace.QName operationName)
            throws ServiceException;
	
	/**
	 * Method createCall
	 * Creates a Call instance. 
	 * @param portName Qualified name for the target service endpoint
	 * @param operationName Name of the operation for which this Call object is to be created.
	 * @return Call instance
	 * @throws ServiceException If any error in the creation of the Call object
	 */
	Call createCall(javax.xml.namespace.QName portName,
            java.lang.String operationName)
            throws ServiceException;
	
	/**
	 * Method createCall
	 * Creates a Call object not associated with specific operation or target service endpoint. This Call object needs to be configured using the setter methods on the Call interface.
	 * @return Call object
	 * @throws ServiceException If any error in the creation of the Call object
	 */
	Call createCall() throws ServiceException;
	
	/**
	 * Method getServiceName
	 * Gets the name of this service.
	 * @return Qualified name of this service
	 */
	javax.xml.namespace.QName getServiceName();
	
	/**
	 * Method getPorts
	 * Returns an Iterator for the list of QNames of service endpoints grouped by this service
	 * @return Returns java.util.Iterator with elements of type javax.xml.namespace.QName 
	 * @throws ServiceException If this Service class does not have access to the required WSDL metadata
	 */
	java.util.Iterator getPorts() throws ServiceException;
	
	/**
	 * Method getWSDLDocumentLocation
	 * Gets the location of the WSDL document for this Service. 
	 * @return URL for the location of the WSDL document for this service
	 */
	java.net.URL getWSDLDocumentLocation();
	
	/**
	 * Method getTypeMappingRegistry
	 * Gets the TypeMappingRegistry for this Service object. The returned TypeMappingRegistry instance is pre-configured to support the standard type mapping between XML and Java types types as required by the JAX-RPC specification.
	 * @return The TypeMappingRegistry for this Service object.
	 * @throws java.lang.UnsupportedOperationException if the Service class does not support the configuration of TypeMappingRegistry.
	 */
	TypeMappingRegistry getTypeMappingRegistry() throws java.lang.UnsupportedOperationException;
	
	/**
	 * Method getSecurityConfiguration
	 * Gets the SecurityConfiguration for this Service object. The returned SecurityConfiguration instance is used to initialize the security configuration of BindingProvider instance created using this Service object.
	 * @return The SecurityConfiguration for this Service object.
	 * @throws java.lang.UnsupportedOperationException if the Service class does not support the configuration of SecurityConfiguration.
	 */
	SecurityConfiguration getSecurityConfiguration() throws java.lang.UnsupportedOperationException;
	
	/**
	 * Method getHandlerRegistry
	 * Returns the configured HandlerRegistry instance for this Service instance.
	 * @return HandlerRegistry
	 * @throws java.lang.UnsupportedOperationException if the Service class does not support the configuration of a HandlerRegistry
	 */
	HandlerRegistry getHandlerRegistry() throws java.lang.UnsupportedOperationException;

}
