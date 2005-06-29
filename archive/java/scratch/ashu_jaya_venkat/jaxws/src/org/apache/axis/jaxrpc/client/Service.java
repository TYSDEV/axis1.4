package org.apache.axis.jaxrpc.client;

import java.net.URI;
import java.net.URL;
import java.rmi.Remote;
import java.util.Iterator;

import javax.xml.bind.JAXBContext;
import javax.xml.namespace.QName;
import javax.xml.rpc.Call;
import javax.xml.rpc.Dispatch;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.Service.Mode;
import javax.xml.rpc.encoding.TypeMappingRegistry;
import javax.xml.rpc.handler.HandlerRegistry;
import javax.xml.rpc.security.SecurityConfiguration;

/**
 * @author sunja07
 *
 */
public class Service implements javax.xml.rpc.Service {

	/**
	 * Method createCall
	 * Creates a Call object not associated with specific operation or target 
	 * service endpoint. This Call object needs to be configured using the 
	 * setter methods on the Call interface.
	 * @return Call object
	 * @throws ServiceException If any error in the creation of the Call object
	 */
	public Call createCall() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Method createCall
	 * Creates a Call instance. 
	 * @param portName Qualified name for the target service endpoint
	 * @param operationName Qualified Name of the operation for which this 
	 * Call object is to be created.
	 * @return Call instance 
	 * @throws ServiceException If any error in the creation of the Call object
	 */
	public Call createCall(QName portName, QName operationName) throws 
	ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Method createCall
	 * Creates a Call instance. 
	 * @param portName Qualified name for the target service endpoint
	 * @param operationName Name of the operation for which this Call object 
	 * is to be created.
	 * @return Call instance
	 * @throws ServiceException If any error in the creation of the Call 
	 * object
	 */
	public Call createCall(QName portName, String operationName) throws 
	ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Method createCall
	 * Creates a Call instance.
	 * @param portName Qualified name for the target service endpoint 
	 * @return Call instance 
	 * @throws ServiceException If any error in the creation of the Call 
	 * object
	 */
	public Call createCall(QName portName) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	// This involves generics, needs a revisit
	/**
	 * Method createDispatch
	 * Creates a Dispatch instance for use with objects of the users choosing.
	 * 
	 * @param - Qualified name for the target service endpoint
	 * @param - The class of object used to messages or message payloads. 
	 * Implementations are required to support javax.xml.transform.Source and 
	 * javax.xml.soap.SOAPMessage.
	 * @param - Controls whether the created dispatch instance is message or 
	 *  payload oriented, i.e. whether the user will work with complete protocol
	 *  messages or message payloads. E.g. when using the SOAP protocol, this 
	 *  parameter controls whether the user will work with SOAP messages or the
	 *  contents of a SOAP body. Mode must be MESSAGE when type is SOAPMessage.
	 * @return Dispatch instance 
	 * @throws ServiceException - If any error in the creation of the Dispatch
	 *  object
	 * @see javax.xml.transform.Source, javax.xml.soap.SOAPMessage
	 */
	public <T> Dispatch<T> createDispatch(QName portName, Class<T> type, 
			Mode mode) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	// This involves generics, needs a revisit
	/**
	 * Method createDispatch
	 * Creates a Dispatch instance for use with JAXB generated objects.
	 * 
	 * @param portName - Qualified name for the target service endpoint
	 * @param context - The JAXB context used to marshall and unmarshall 
	 * messages or message payloads.
	 * @param mode - Controls whether the created dispatch instance is message
	 *  or payload oriented, i.e. whether the user will work with complete 
	 *  protocol messages or message payloads. E.g. when using the SOAP 
	 *  protocol, this parameter controls whether the user will work with 
	 *  SOAP messages or the contents of a SOAP body.
	 * @return Dispatch instance 
	 * @throws ServiceException - If any error in the creation of the Dispatch
	 *  object
	 * @see JAXBContext
	 */
	public Dispatch<Object> createDispatch(QName portName, JAXBContext context,
			Mode mode) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Method createPort
	 * Creates a new port for the service. Ports created in this way contain 
	 * no WSDL port type information and can only be used for creating Dispatch
	 *  and Call instances.
	 * @param portName Qualified name for the target service endpoint
	 * @param bindingId A URI identifier of a binding.
	 * @param endpointAddress Address of the target service endpoint as a URI 
	 * @throws ServiceException If any error in the creation of the port
	 * @see javax.xml.rpc.soap.SOAPBinding.SOAP11HTTP_BINDING
	 */
	public void createPort(QName portName, URI bindingId, 
			String endpointAddress) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Method getCalls
	 * Gets an array of preconfigured Call objects for invoking operations on 
	 * the specified port. There is one Call object per operation that can be 
	 * invoked on the specified port. Each Call object is pre-configured and 
	 * does not need to be configured using the setter methods on Call 
	 * interface.
	 * Each invocation of the getCalls method returns a new array of 
	 * preconfigured Call objects 
	 * This method requires the Service implementation class to have access to
	 * the WSDL related metadata.
	 * @param portName Qualified name for the target service endpoint 
	 * @return Call[] Array of pre-configured Call objects 
	 * @throws ServiceException If this Service class does not have access to 
	 * the required WSDL metadata or if an illegal portName is specified.
	 */
	public Call[] getCalls(QName portName) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Method getHandlerRegistry
	 * Returns the configured HandlerRegistry instance for this Service 
	 * instance.
	 * @return HandlerRegistry
	 * @throws java.lang.UnsupportedOperationException if the Service class 
	 * does not support the configuration of a HandlerRegistry
	 */
	public HandlerRegistry getHandlerRegistry() throws 
	UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Method getPort
	 * The getPort method returns either an instance of a generated stub 
	 * implementation class or a dynamic proxy. The parameter 
	 * serviceEndpointInterface specifies the service endpoint interface that 
	 * is supported by the returned stub or proxy. In the implementation of 
	 * this method, the JAX-RPC runtime system takes the responsibility of 
	 * selecting a protocol binding (and a port) and configuring the stub 
	 * accordingly. The returned Stub instance should not be reconfigured by 
	 * the client.
	 * @param serviceEndpointInterface Service endpoint interface 
	 * @return Stub instance or dynamic proxy that supports the specified 
	 * service endpoint interface 
	 * @throws ServiceException This exception is thrown in the following 
	 * cases:
	 * 1. If there is an error during creation of stub instance or dynamic 
	 * proxy 
	 * 2. If there is any missing WSDL metadata as required by this method
	 * 3. Optionally, if an illegal serviceEndpointInterface is specified 
	 */
	public Remote getPort(Class serviceEndpointInterface) throws 
	ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Method getPort
	 * The getPort method returns either an instance of a generated stub 
	 * implementation class or a dynamic proxy. A service client uses this 
	 * dynamic proxy to invoke operations on the target service endpoint. The 
	 * serviceEndpointInterface specifies the service endpoint interface that 
	 * is supported by the created dynamic proxy or stub instance.
	 * @param portName Qualified name of the service endpoint in the WSDL 
	 * service description
	 * @param serviceEndpointInterface Service endpoint interface supported by 
	 * the dynamic proxy or stub instance 
	 * @return java.rmi.Remote Stub instance or dynamic proxy that supports the 
	 * specified service endpoint interface 
	 * @throws ServiceException This exception is thrown in the following 
	 * cases:
	 * 1. If there is an error in creation of the dynamic proxy or stub 
	 * instance
	 * 2. If there is any missing WSDL metadata as required by this method 
	 * 3. Optionally, if an illegal serviceEndpointInterface or portName is 
	 * specified
	 * @see java.lang.reflect.Proxy, java.lang.reflect.InvocationHandler 
	 */
	public Remote getPort(QName portName, Class serviceEndpointInterface) 
	throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Method getPorts
	 * Returns an Iterator for the list of QNames of service endpoints grouped 
	 * by this service
	 * @return Returns java.util.Iterator with elements of type 
	 * javax.xml.namespace.QName 
	 * @throws ServiceException If this Service class does not have access to 
	 * the required WSDL metadata
	 */
	public Iterator getPorts() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Method getSecurityConfiguration
	 * Gets the SecurityConfiguration for this Service object. The returned 
	 * SecurityConfiguration instance is used to initialize the security 
	 * configuration of BindingProvider instance created using this Service 
	 * object.
	 * @return The SecurityConfiguration for this Service object.
	 * @throws java.lang.UnsupportedOperationException if the Service class 
	 * does not support the configuration of SecurityConfiguration.
	 */
	public SecurityConfiguration getSecurityConfiguration() throws 
	UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Method getServiceName
	 * Gets the name of this service.
	 * @return Qualified name of this service
	 */
	public QName getServiceName() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Method getTypeMappingRegistry
	 * Gets the TypeMappingRegistry for this Service object. The returned 
	 * TypeMappingRegistry instance is pre-configured to support the standard 
	 * type mapping between XML and Java types types as required by the 
	 * JAX-RPC specification.
	 * @return The TypeMappingRegistry for this Service object.
	 * @throws java.lang.UnsupportedOperationException if the Service class 
	 * does not support the configuration of TypeMappingRegistry.
	 */
	public TypeMappingRegistry getTypeMappingRegistry() throws 
	UnsupportedOperationException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Method getWSDLDocumentLocation
	 * Gets the location of the WSDL document for this Service. 
	 * @return URL for the location of the WSDL document for this service
	 */
	public URL getWSDLDocumentLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	public Service() {
		super();
		// TODO Auto-generated constructor stub
	}

}
