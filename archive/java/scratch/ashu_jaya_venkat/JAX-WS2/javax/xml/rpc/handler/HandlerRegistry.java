/*
 * Created on Jun 20, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package javax.xml.rpc.handler;

import java.io.Serializable;
import javax.xml.rpc.JAXRPCException;

/**
 * public interface HandlerRegistry extends java.io.Serializable
 * The javax.xml.rpc.handler.HandlerRegistry provides support for the programmatic configuration of handlers in a
 * HandlerRegistry.
 * 
 * Handler chains may be registered on a per-service, per-port and per -protcol binding basis. The complete handler chain for a
 * stub, dynamic proxy, Dispatch or Call instance is a concatenation of the per-service handler chain, the handler chain for the
 * relevent port and the handler chain for the protocol binding in use.
 * @version 1.0
 * @author shaas02
 * @see javax.xml.rpc.Service
 *
 */
public interface HandlerRegistry extends Serializable {
	
	/**
	 * Gets the handler chain for the specified port. The returned List is used to configure this specific handler chain in this
	 * HandlerRegistry. Each element in this list is required to be of the Java type
	 * javax.xml.rpc.handler.HandlerInfo.
	 * 
	 * @param portName - Qualified name of the target service endpoint
	 * @return java.util.List Handler chain
	 * @throws java.lang.IllegalArgumentException - If an invalid portName is specified
	 */
	java.util.List getHandlerChain(javax.xml.namespace.QName portName) throws java.lang.IllegalArgumentException;
	
	/**
	 * Sets the handler chain for the specified port as a java.util.List. Each element in this list is required to be of the Java
	 * type javax.xml.rpc.handler.HandlerInfo.
	 * 
	 * @param portName - Qualified name of the target service endpoint
	 * @param chain - A List representing configuration for the handler chain
	 * @throws JAXRPCException - If any error in the configuration of the handler chain
	 * @throws java.lang.UnsupportedOperationException - If this set operation is not supported. This is done to avoid
	 * any overriding of a pre-configured handler chain.
	 * @throws java.lang.IllegalArgumentException  - If an invalid portName is specified
	 */
	void setHandlerChain(javax.xml.namespace.QName portName,
			java.util.List chain) throws JAXRPCException, java.lang.UnsupportedOperationException,
			java.lang.IllegalArgumentException;
	
	/**
	 * Gets the handler chain for all ports in the service instance. The returned List is used to configure the handler chain.
	 * @return java.util.List Handler chain
	 */
	java.util.List<HandlerInfo> getHandlerChain();
	
	/**
	 * Sets the handler chain for all ports in the service instance.
	 * @param chain - A List of handler configuration entries
	 * @throws java.lang.UnsupportedOperationException - If this set operation is not supported. This is done to avoid
	 * any overriding of a pre-configured handler chain.
	 * @throws JAXRPCException - On an error in the configuration of the handler chain
	 */
	void setHandlerChain(java.util.List<HandlerInfo> chain) throws java.lang.UnsupportedOperationException, JAXRPCException;
	
	/**
	 * Gets the handler chain for the specified protocol binding. The returned List is used to configure this specific handler chain
	 * in this HandlerRegistry.
	 * @param bindingId - A URI identifier of a binding.
	 * @return java.util.List Handler chain
	 * @throws java.lang.IllegalArgumentException - If an unknown bindingId is specified
	 * @see javax.xml.rpc.soap.SOAPBinding#SOAP11HTTP_BINDING
	 */
	java.util.List<HandlerInfo> getHandlerChain(java.net.URI bindingId) throws java.lang.IllegalArgumentException;
	
	/**
	 * Sets the handler chain for the specified protocol binding as a java.util.List.
	 * @param bindingId  - A URI identifier of a binding.
	 * @param chain - A List of handler configuration entries
	 * @throws JAXRPCException  - On an error in the configuration of the handler chain
	 * @throws java.lang.UnsupportedOperationException - If this set operation is not supported. This is done to avoid
	 * any overriding of a pre-configured handler chain.
	 * @throws java.lang.IllegalArgumentException - If an unknown bindingId is specified
	 * @see javax.xml.rpc.soap.SOAPBinding#SOAP11HTTP_BINDING
	 */
	void setHandlerChain(java.net.URI bindingId,
			java.util.List<HandlerInfo> chain) throws JAXRPCException,
			java.lang.UnsupportedOperationException, java.lang.IllegalArgumentException;

}
