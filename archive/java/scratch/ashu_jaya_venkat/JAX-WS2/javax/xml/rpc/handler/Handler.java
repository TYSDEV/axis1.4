/*
 * Created on Jun 20, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package javax.xml.rpc.handler;

import javax.xml.rpc.JAXRPCException;
import javax.xml.rpc.soap.SOAPFaultException;

/**
 * public interface Handler extends HandlerLifecycle
 * <p>
 * The javax.xml.rpc.handler.Handler interface is required to be implemented by a SOAP message handler. The
 * handleRequest, handleResponse and handleFault methods for a SOAP message handler get access to the SOAPMessage
 * from the SOAPMessageContext. The implementation of these methods can modify the SOAPMessage including the headers and
 * body elements.
 * @version 1.0
 * @author shaas02
 *
 */
public interface Handler extends HandlerLifecycle {
	
	/**
	 * The handleRequest method processes the request message.
	 * @param context - MessageContext parameter provides access to the request message.
	 * @return boolean Indicates the processing mode
	 * 	-Return true to indicate continued processing of the request handler chain. The HandlerChain  takes the
	 *  responsibility of invoking the next entity. The next entity may be the next handler in the HandlerChain or if
	 * this handler is the last handler in the chain, the next entity is the service endpoint object.
	 * -Return false to indicate blocking of the request handler chain. In this case, further processing of the request
	 * handler chain is blocked and the target service endpoint is not dispatched. The JAX-RPC runtime system
	 * takes the responsibility of invoking the response handler chain next with the SOAPMessageContext. The
	 * Handler implementation class has the the responsibility of setting the appropriate response SOAP message in
	 *  either handleRequest and/or handleResponse method. In the default processing model, the response handler
	 * chain starts processing from the same Handler instance (that returned false) and goes backward in the
	 * execution sequence.
	 * @throws <code>JAXRPCException</code> - This exception indicates handler specific runtime error. If JAXRPCException is thrown by a
	 * handleRequest method, the HandlerChain terminates the further processing of this handler chain. On the server side,
	 *  the HandlerChain generates a SOAP fault that indicates that the message could not be processed for reasons not
	 * directly attributable to the contents of the message itself but rather to a runtime error during the processing of the
	 * message. On the client side, the exception is propagated to the client code
	 * @throws <code>SOAPFaultException</code> - This indicates a SOAP fault. The Handler implementation class has the the responsibility
	 * of setting the SOAP fault in the SOAP message in either handleRequest and/or handleFault method. If
	 * SOAPFaultException is thrown by a server-side request handler's handleRequest method, the HandlerChain
	 * terminates the further processing of the request handlers in this handler chain and invokes the handleFault method
	 * on the HandlerChain with the SOAP message context. Next, the HandlerChain invokes the handleFault method on
	 * handlers registered in the handler chain, beginning with the Handler instance that threw the exception and going
	 * backward in execution. The client-side request handler's handleRequest method should not throw the
	 * SOAPFaultException.
	 */
	boolean handleRequest(MessageContext context) throws JAXRPCException, SOAPFaultException;
	
	/**
	 * The handleResponse method processes the response SOAP message.
	 * @param context - MessageContext parameter provides access to the response SOAP message
	 * @return boolean Indicates the processing mode
	 * - Return true to indicate continued processing ofthe response handler chain. The HandlerChain invokes the
	 * handleResponse  method on the next Handler in the handler chain.
	 * - Return false to indicate blocking of the response handler chain. In this case, no other response handlers in
	 * the handler chain are invoked.
	 * @throws <code>JAXRPCException</code> - Indicates handler specific runtime error. If JAXRPCException is thrown by a
	 * handleResponse method, the HandlerChain terminates the further processing of this handler chain. On the server
	 * side, the HandlerChain generates a SOAP fault that indicates that the message could not be processed for reasons
	 * not directly attributable to the contents of the message itself but rather to a runtime error during the processing of
	 * the message. On the client side, the runtime exception is propagated to the client code.
	 */
	boolean handleResponse(MessageContext context) throws JAXRPCException;
	
	/**
	 * The handleFault method processes the SOAP faults based on the SOAP message processing model.
	 * @param context - MessageContext parameter provides access to the SOAP message
	 * @return boolean Indicates the processing mode
	 * - Return true to indicate continued processing of SOAP Fault. The HandlerChain invokes the handleFault
	 * method on the next Handler in the handler chain.
	 * - Return false to indicate end of the SOAP fault processing. In this case, no other handlers in the handler
	 * chain are invoked.
	 * @throws <code>JAXRPCException</code> - Indicates handler specific runtime error. If JAXRPCException is thrown by a handleFault
	 * method, the HandlerChain terminates the further processing of this handler chain. On the server side, the
	 * HandlerChain generates a SOAP fault that indicates that the message could not be processed for reasons not
	 * directly attributable to the contents of the message itself but rather to a runtime error during the processing of the
	 * message. On the client side, the JAXRPCException is propagated to the client code.
	 */
	boolean handleFault(MessageContext context) throws JAXRPCException;
	
	/**
	 * Gets the header blocks that can be processed by this Handler instance.
	 * @return Array of QNames of header blocks processed by this handler instance. QName is the qualified name of the
	 * outermost element of the Header block.
	 */
	javax.xml.namespace.QName[] getHeaders();

}
