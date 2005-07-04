package org.apache.axis.jaxrpc.client;

import java.net.URI;
import java.net.URL;
import java.rmi.Remote;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.namespace.QName;
import javax.xml.rpc.Call;
import javax.xml.rpc.Dispatch;
import javax.xml.rpc.JAXRPCException;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.Service.Mode;
import javax.xml.rpc.encoding.TypeMappingRegistry;
import javax.xml.rpc.handler.HandlerInfo;
import javax.xml.rpc.handler.HandlerRegistry;
import javax.xml.rpc.security.SecurityConfiguration;

/**
 * @author sunja07
 *
 */
public class ServiceImpl implements javax.xml.rpc.Service {
	
	private HandlerRegistryImpl handlerRegistry = new HandlerRegistryImpl();
	
	private TypeMappingRegistry typeMappingRegistry;

	public static boolean JAXB_USAGE = true;
	
	public String wsdlLoc = null;
	/**
	 * Method createCall
	 * Creates a Call object not associated with specific operation or target 
	 * service endpoint. This Call object needs to be configured using the 
	 * setter methods on the Call interface.
	 * @return Call object
	 * @throws ServiceException If any error in the creation of the Call object
	 */
	public Call createCall() throws ServiceException {
		Call call = new CallImpl();
		return call;
	}
	
	protected class HandlerRegistryImpl implements HandlerRegistry{
		
		//Adding as an inner class to ServiceImpl.
		// Might need to revisit later, if it needs to be an independent one.
		
		private List<HandlerInfo> serviceHandlerChain;
		private Map<URI, List> bindingHandlerChain;
		private Map<QName, List> portHandlerChain;
		
		private void confirmPort(javax.xml.namespace.QName portName){
			Iterator<QName> ports = null;
			try{
				ports = getPorts();
				while(ports.hasNext()){
					if(ports.next()== portName)
						return;
				}
			}catch(ServiceException se){
				
			}
			throw new java.lang.IllegalArgumentException("Invalid portName");
		}
		
		private void confirmBindingId(java.net.URI bindingId){
			
			if(bindingId.toString().equalsIgnoreCase("http://schemas.xmlsoap.org/wsdl/soap/http"))
				return;
			
			throw new java.lang.IllegalArgumentException("Invalid bindingId");
		}
		
		public java.util.List getHandlerChain(javax.xml.namespace.QName portName) throws java.lang.IllegalArgumentException{
			
			confirmPort(portName);
			if(portHandlerChain == null)
				portHandlerChain = new HashMap<QName, List>();
			if(portHandlerChain.get(portName) == null){
				setHandlerChain(portName, new ArrayList());
			}
			return portHandlerChain.get(portName);
		}
		
		public void setHandlerChain(javax.xml.namespace.QName portName,
				java.util.List chain) throws JAXRPCException, java.lang.UnsupportedOperationException,
				java.lang.IllegalArgumentException{
			
			confirmPort(portName);
			if(portHandlerChain == null)
				portHandlerChain = new HashMap<QName, List>();
			portHandlerChain.put(portName, chain);
		}
		
		public java.util.List<HandlerInfo> getHandlerChain(){
			if(serviceHandlerChain == null)
				serviceHandlerChain = new ArrayList<HandlerInfo>();
			return serviceHandlerChain;
		}
		
		public void setHandlerChain(java.util.List<HandlerInfo> chain) throws java.lang.UnsupportedOperationException, JAXRPCException{
			serviceHandlerChain = chain;
		}
		
		public java.util.List<HandlerInfo> getHandlerChain(java.net.URI bindingId) throws java.lang.IllegalArgumentException{
			confirmBindingId(bindingId);
			if(bindingHandlerChain == null)
				bindingHandlerChain = new HashMap<URI, List>();
			if(bindingHandlerChain.get(bindingId) == null){
				setHandlerChain(bindingId, new ArrayList());
			}
			return bindingHandlerChain.get(bindingId);
		}
		
		public void setHandlerChain(java.net.URI bindingId,
				java.util.List<HandlerInfo> chain) throws JAXRPCException,
				java.lang.UnsupportedOperationException, java.lang.IllegalArgumentException{
			
			confirmBindingId(bindingId);
			if(bindingHandlerChain == null)
				bindingHandlerChain = new HashMap<URI, List>();
			bindingHandlerChain.put(bindingId, chain);
		}
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
		Call call = createCall(portName);
		call.setOperationName(operationName);
		
		return call;
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
		QName dummyQNameForOperationName = new QName(operationName);
		return createCall(portName, dummyQNameForOperationName);
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
		
		Call call = new CallImpl();

		//portName and portType name needn't be matching. So...
		QName portTypeName = getPortTypeNameForPort(portName); 
		call.setPortTypeName(portTypeName);
		
		
		prefillPortFromWSDL(portName);
		return null;
	}
	
	public QName getPortTypeNameForPort(QName portName) {
		//TODO Corresponding to a portName we can avail a binding linkage and 
		//from the binding we can get the unique portType it binds
		
		//but for now. Lets assume our portName is exactly our portTypeName
		//Will revisit once WSDL handling mechanism is in place.
		return portName;
	}
	
	public void prefillPortFromWSDL(QName portName) { 
		//TODO Actually there is lot amount of info that can be prefetched
		//from wsdl for a given port. But WSDL handling is yet to be thought
		//over and finalized. Once that done, we will prefill as much info
		//from wsdl as we can in this method.
		//From port, we can get targetEndPointAddress, binding and from 
		//binding the portType information. Using which may be the call
		//object can be better configured with parameters and returnType
		//info.
	}

	// This involves generics, needs a revisit
	/**
	 * Method createDispatch
	 * Creates a Dispatch instance for use with objects of the users choosing.
	 * 
	 * @param portName - Qualified name for the target service endpoint
	 * @param type - The class of object used to messages or message payloads. 
	 * Implementations are required to support javax.xml.transform.Source and 
	 * javax.xml.soap.SOAPMessage.
	 * @param mode - Controls whether the created dispatch instance is message or 
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
		
		//Logic here is that corresponding to this portName
		//identify the portType and within it identify all the
		//operations in it.
		//For each operation, create a call object, configure it and
		//add it to an array of call objects. Configuring of call
		//objects would be typically setting the TargetEndPointAddress, 
		//setting the operationName, portName, portType, adding 
		//parameters and returnType.

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
		//Actually at the deployment configuration time positively the handler
		//registry must be populated with handler chain info.
		return handlerRegistry;
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
		//If the implementation is using JAXB, we should throw exception
		//Usage of JAXB might be using a flag. This flag, I feel, is most
		//appropriate if placed in the way the ServiceFactory itself is 
		//instantiated. ServiceFactory.newInstance(boolean JAXB_USAGE)
		//The flag can be copied to a boolean value in the Service object
		//that gets created with sf.createService(...) method.
		
		if (JAXB_USAGE)
			throw new UnsupportedOperationException();
		else
			return typeMappingRegistry;
			//But positively at some point of execution flow
			//typeMappingRegistry must be populated.
	}

	/**
	 * Method getWSDLDocumentLocation
	 * Gets the location of the WSDL document for this Service. 
	 * @return URL for the location of the WSDL document for this service
	 */
	public URL getWSDLDocumentLocation() {
		return null;
	}

	public ServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

}
