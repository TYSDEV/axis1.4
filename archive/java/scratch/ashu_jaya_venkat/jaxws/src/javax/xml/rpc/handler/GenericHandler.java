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

package javax.xml.rpc.handler;

import javax.xml.namespace.QName;
import javax.xml.rpc.JAXRPCException;
import javax.xml.rpc.soap.SOAPFaultException;


/**
 * public abstract class GenericHandler
 * extends java.lang.Object
 * implements <code>Handler</code>
 * <p>
 * The javax.xml.rpc.handler.GenericHandler class implements the Handler interface. SOAP Message Handler
 * developers should typically subclass GenericHandler class unless the Handler class needs another class as a superclass.
 * <p>
 * The GenericHandler class is a convenience abstract class that makes writing Handlers easy. This class provides default
 * implementations of the lifecycle methods init  and destroy and also different handle methods. A Handler developer should
 * only override methods that it needs to specialize as part of the derived Handler implementation class.
 * 
 * @version 1.0
 * @author shaas02
 *
 */
public abstract class GenericHandler implements Handler, HandlerLifecycle {

	protected GenericHandler(){
		
	}
	
	/**
	 * The handleRequest method processes the request SOAP message. The default implementation of this method returns
	 * true. This indicates that the handler chain should continue processing of the request SOAP message. This method should
	 * be overridden if the derived Handler class needs to specialize implementation of this method.
	 * @param context - MessageContext parameter provides access to the request message.
	 * @return
	 * 	boolean Indicates the processing mode
	 * -Return true to indicate continued processing of the request handler chain. The HandlerChain  takes the
	 * responsibility of invoking the next entity. The next entity may be the next handler in the HandlerChain or if
	 * this handler is the last handler in the chain, the next entity is the service endpoint object.
	 * -Return false to indicate blocking of the request handler chain. In this case, further processing of the request
	 * handler chain is blocked and the target service endpoint is not dispatched. The JAX-RPC runtime system
	 * takes the responsibility of invoking the response handler chain next with the SOAPMessageContext. The
	 * Handler implementation class has the the responsibility of setting the appropriate response SOAP message in
	 * either handleRequest and/or handleResponse method. In the default processing model, the response handler
	 * chain starts processing from the same Handler instance (that returned false) and goes backward in the
	 * execution sequence.
	 * @see javax.xml.rpc.handler.Handler#handleRequest(javax.xml.rpc.handler.MessageContext)
	 */
	public boolean handleRequest(MessageContext context)
			throws JAXRPCException, SOAPFaultException {
		return true;
	}

	/**
	 * The handleResponse method processes the response message. The default implementation of this method returns true.
	 * This indicates that the handler chain should continue processing of the response SOAP message. This method should be
	 * overridden if the derived Handler class needs to specialize implementation of this method.
	 * @param context - MessageContext parameter provides access to the response SOAP message
	 * @return
	 *  boolean Indicates the processing mode
	 * - Return true to indicate continued processing ofthe response handler chain. The HandlerChain invokes the
	 * handleResponse  method on the next Handler in the handler chain.
	 * - Return false to indicate blocking of the response handler chain. In this case, no other response handlers in
	 * the handler chain are invoked.
	 * @see javax.xml.rpc.handler.Handler#handleResponse(javax.xml.rpc.handler.MessageContext)
	 */
	public boolean handleResponse(MessageContext context)
			throws JAXRPCException {
		return true;
	}

	/**
	 * The handleFault method processes the SOAP faults based on the SOAP message processing model. The default
	 * implementation of this method returns true. This indicates that the handler chain should continue processing of the SOAP
	 * fault. This method should be overridden if the derived Handler class needs to specialize implementation of this method.
	 * @param context - MessageContext parameter provides access to the SOAP message
	 * @return boolean Indicates the processing mode
	 * - Return true to indicate continued processing of SOAP Fault. The HandlerChain invokes the handleFault
	 * method on the next Handler in the handler chain.
	 * - Return false to indicate end of the SOAP fault processing. In this case, no other handlers in the handler
	 * chain are invoked.
	 * @see javax.xml.rpc.handler.Handler#handleFault(javax.xml.rpc.handler.MessageContext)
	 */
	public boolean handleFault(MessageContext context) throws JAXRPCException {
		return true;
	}

	/**
	 * Gets the header blocks processed by this Handler instance.
	 * @return Array of QNames of header blocks processed by this handler instance. QName is the qualified name of the
	 * outermost element of the Header block.
	 * @see javax.xml.rpc.handler.Handler#getHeaders()
	 */
	public abstract QName[] getHeaders();

	/**
	 * The init method to enable the Handler instance to initialize itself. This method should be overridden if the derived
	 * Handler class needs to specialize implementation of this method.
	 * @param config - Configuration for the initialization of this handler
	 * @see javax.xml.rpc.handler.HandlerLifecycle#init(null)
	 */
	public abstract void init(HandlerInfo config) throws JAXRPCException;

	/**
	 * The destroy method indicates the end of lifecycle for a Handler instance. This method should be overridden if the
	 * derived Handler class needs to specialize implementation of this method.
	 * @see javax.xml.rpc.handler.HandlerLifecycle#destroy()
	 */
	public abstract void destroy() throws JAXRPCException ;

}
