/*
 * Created on Jun 19, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package javax.xml.rpc;

/**
 * @author sunja07
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class ServiceFactory {

	/**
	 * A constant representing the property used to lookup the name of a ServiceFactory implementation class. 
	 */
	public static final java.lang.String SERVICEFACTORY_PROPERTY = null;
	
	/**
	 * Empty constructor
	 */
	protected ServiceFactory() {}
	
	/**
	 * Gets an instance of the ServiceFactory 
	 * Only one copy of a factory exists and is returned to the application each time this method is called.
	 * The implementation class to be used can be overridden by setting the javax.xml.rpc.ServiceFactory system property.
	 * @return static instance of ServiceFactory
	 * @throws ServiceException
	 */
	public static ServiceFactory newInstance() throws ServiceException {
		return null;
	}
	
	/**
	 * Method createService
	 * Create a Service instance.
	 * @param wsdlDocumentLocation URL for the WSDL document location for the service
	 * @param serviceName QName for the service.
	 * @return a <code>Service</code> instance
	 * @throws ServiceException
	 */
	public abstract Service createService(java.net.URL wsdlDocumentLocation,
            javax.xml.namespace.QName serviceName) throws ServiceException;
	
	/**
	 * Method loadService
	 * Create an instance of the generated service implementation class for a given service interface, if available.
	 * @param serviceInterface Service interface 
	 * @return ??? read the spec once again
	 * @throws ServiceException If there is any error while creating the specified service, including the case where a generated service implementation class cannot be located
	 */
	public abstract Service loadService(java.lang.Class serviceInterface)
    throws ServiceException;
	
	/**
	 * Method loadService
	 * Create an instance of the generated service implementation class for a given service interface, if available. An implementation may use the provided wsdlDocumentLocation and properties to help locate the generated implementation class. If no such class is present, a ServiceException will be thrown.
	 * @param wsdlDocumentLocation URL for the WSDL document location for the service or null
	 * @param serviceInterface Service interface
	 * @param properties A set of implementation-specific properties to help locate the generated service implementation class 
	 * @return ??? read the spec once again
	 * @throws ServiceException If there is any error while creating the specified service, including the case where a generated service implementation class cannot be located
	 */
	public abstract Service loadService(java.net.URL wsdlDocumentLocation,
            java.lang.Class serviceInterface,
            java.util.Properties properties) throws ServiceException;
	
	/**
	 * Method loadService
	 * Create an instance of the generated service implementation class for a given service, if available. The service is uniquely identified by the wsdlDocumentLocation and serviceName arguments. An implementation may use the provided properties to help locate the generated implementation class. If no such class is present, a ServiceException will be thrown.
	 * @param wsdlDocumentLocation URL for the WSDL document location for the service or null
	 * @param serviceName Qualified name for the service
	 * @param properties A set of implementation-specific properties to help locate the generated service implementation class 
	 * @return ??? read the spec once again
	 * @throws ServiceException If there is any error while creating the specified service, including the case where a generated service implementation class cannot be located
	 */
	public abstract Service loadService(java.net.URL wsdlDocumentLocation,
            javax.xml.namespace.QName serviceName,
            java.util.Properties properties) throws ServiceException;
}
