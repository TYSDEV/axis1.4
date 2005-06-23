package javax.xml.rpc.handler;

import javax.xml.rpc.ProtocolException;

/**
 * public interface AbstractHandler<C extends <code>MessageContext</code>>
 * extends <code>HandlerLifecycle</code>
 * <p>
 * The javax.xml.rpc.handler.AbstractHandler interface is the base interface for JAXRPC 2.0 handlers.
 * @version 1.0
 * @author shaas02
 *
 * @param <C>
 */
public interface AbstractHandler<C extends MessageContext> extends HandlerLifecycle {

	/**
	 * The handleMessage method is invoked for normal processing of inbound and outbound messages. Refer to the
	 * description of the handler framework in the JAX-RPC 2.0 specification for full details.
	 * 
	 * @param context - the message context.
	 * @return An indication of whether handler processing should continue for the current message
	 *  - Return true to continue processing.
	 *  - Return false to block processing.
	 * @throws java.lang.RuntimeException - Causes the JAX-RPC runtime to cease handler processing and generate a
	 * fault.
	 * @throws ProtocolException - Causes the JAX-RPC runtime to switch to fault message processing.
	 */
	boolean handleMessage(C context) throws java.lang.RuntimeException, ProtocolException;
	
	/**
	 * The handleFault method is invoked for fault message processing. Refer to the description of the handler framework in
	 * the JAX-RPC 2.0 specification for full details.
	 * 
	 * @param context - the message context
	 * @return An indication of whether handler fault processing should continue for the current message
	 *  - Return true to continue processing.
	 *  - Return false to block processing.
	 * @throws java.lang.RuntimeException - Causes the JAX-RPC runtime to cease handler fault processing and dispatch
	 * the fault.
	 * @throws ProtocolException - Causes the JAX-RPC runtime to cease handler fault processing and dispatch the fault.
	 */
	boolean handleFault(C context) throws java.lang.RuntimeException, ProtocolException;
	
	/**
	 * Called at the conclusion of a message exchange pattern just prior to the JAX-RPC runtime disptaching a message, fault or
	 * exception. Refer to the description of the handler framework in the JAX-RPC 2.0 specification for full details.
	 *  
	 * @param context - the message context
	 */
	void close(MessageContext context);
}
