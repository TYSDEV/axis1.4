/*
 * Created on Jun 19, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package javax.xml.rpc;

/**
 * Interface BindingProvider
 * The javax.xml.rpc.BindingProvider interface provides access to the protocol binding and associated JAXRPCContext objects for request and response message processing.
 * 
 * @version 1.0
 * @author sunja07
 */
public interface BindingProvider {
	
	/**
	 * Standard property: Target service endpoint address. The URI scheme for 
	 * the endpoint address specification must correspond to the 
	 * protocol/transport binding for the binding in use. 
	 */
	static final java.lang.String ENDPOINT_ADDRESS_PROPERTY = null;
	
	/**
	 * Standard property: This boolean property is used by a service client to 
	 * indicate whether or not it wants to participate in a session with a 
	 * service endpoint. If this property is set to true, the service client 
	 * indicates that it wants the session to be maintained. If set to false, 
	 * the session is not maintained. The default value for this property is 
	 * false.
	 */
	static final java.lang.String SESSION_MAINTAIN_PROPERTY = null;
	
	/**
	 * Standard property for SOAPAction. This boolean property indicates 
	 * whether or not SOAPAction is to be used. The default value of this 
	 * property is false indicating that the SOAPAction is not used.
	 */
	static final java.lang.String SOAPACTION_USE_PROPERTY = null;
	
	/**
	 * Standard property for SOAPAction. Indicates the SOAPAction URI if the 
	 * javax.xml.rpc.soap.http.soapaction.use property is set to true.
	 */
	static final java.lang.String SOAPACTION_URI_PROPERTY = null;
	
	/**
	 * Standard property: Password for authentication.
	 */
	static final java.lang.String PASSWORD_PROPERTY = null;
	
	/**
	 * Standard property: User name for authentication.
	 */
	static final java.lang.String USERNAME_PROPERTY = null;
	
	/**
	 * Standard property: JAXB context to use for marshalling arguments in 
	 * dynamic APIs.
	 */
	static final java.lang.String JAXB_CONTEXT_PROPERTY = null;
	
	/**
	 * Method getRequestContext
	 * Get the JAXRPCContext that is used to initialize the message context 
	 * for request messages. Modifications to the request context do not 
	 * affect the message context of either synchronous or asynchronous 
	 * operations that have already been started.
	 * @return The JAXRPCContext that is used in processing request messages.
	 */
	JAXRPCContext getRequestContext();
	
	/**
	 * Method getResponseContext
	 * Get the JAXRPCContext that resulted from processing a response message. 
	 * The returned context is for the most recently completed synchronous 
	 * operation. Subsequent synchronous operation invocations overwrite the 
	 * response context. Asynchronous operations return their response context 
	 * via the Response interface.
	 * @return The JAXRPCContext that resulted from processing the latest 
	 * response messages.
	 */
	JAXRPCContext getResponseContext();
	
	/**
	 * Method getBinding
	 * Get the Binding for this binding provider.
	 * @return The Binding for this binding provider.
	 */
	Binding getBinding();
}
