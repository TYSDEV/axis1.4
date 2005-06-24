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

import java.util.Collection;
import java.util.List;
import javax.xml.rpc.JAXRPCException;

/**
 * @deprecated JAX-RPC 1.1 didn't provide a way for a client to obtain an instance of HandlerChain. In JAX-RPC 2.0 the Binding interface and sub interfaces take over the role of HandlerChain
 * 
 * public interface HandlerChain extends java.util.List
 * 
 * The javax.xml.rpc.handler.HandlerChain represents a list of handlers. All elements in the HandlerChain are of the type
 * javax.xml.rpc.handler.Handler.
 * 
 * An implementation class for the HandlerChain  interface abstracts the policy and mechanism for the invocation of the registered
 * handlers.
 * 
 * @version 1.0
 * @author shaas02
 * @see javax.xml.rpc.Binding, javax.xml.rpc.soap.SOAPBinding, javax.xml.rpc.BindingProvider
 */
public interface HandlerChain extends Collection, Iterable, List {
	
	/**
	 * @deprecated 
	 * The handleRequest method initiates the request processing for this handler chain.
	 * @param context - MessageContext parameter provides access to the request SOAP message.
	 * @return boolean Returns true if all handlers in chain have been processed. Returns false  if a handler in the chain returned false from its handleRequest method.
	 * @throws JAXRPCException - if any processing error happens
	 * @see Handler.handleRequest(javax.xml.rpc.handler.MessageContext)
	 */
	boolean handleRequest(MessageContext context) throws JAXRPCException;
	
	/**
	 * @deprecated 
	 * The handleResponse method initiates the response processing for this handler chain.
	 * @param context - MessageContext parameter provides access to the response SOAP message.
	 * @return boolean Returns true if all handlers in chain have been processed. Returns false  if a handler in the chain returned false from its handleResponse method.
	 * @throws JAXRPCException - if any processing error happens
	 * @see Handler.handleResponse(javax.xml.rpc.handler.MessageContext)
	 */
	boolean handleResponse(MessageContext context) throws JAXRPCException;
	
	/**
	 * @deprecated 
	 * The handleFault method initiates the SOAP fault processing for this handler chain.
	 * @param context - MessageContext parameter provides access to the SOAP message.
	 * @return boolean Returns true if all handlers in chain have been processed. Returns false  if a handler in the chain returned false from its handleFault method.
	 * @throws JAXRPCException  - if any processing error happens
	 * @see Handler.handleFault(javax.xml.rpc.handler.MessageContext)
	 */
	boolean handleFault(MessageContext context) throws JAXRPCException;
	
	/**
	 * @deprecated 
	 * Initializes the configuration for a HandlerChain.
	 * @param config - Configuration for the initialization of this handler chain
	 * @throws JAXRPCException - If any error during initialization
	 */
	void init(java.util.Map config) throws JAXRPCException;
	
	/**
	 * @deprecated 
	 * Indicates the end of lifecycle for a HandlerChain.
	 * @throws JAXRPCException - If any error during destroy
	 */
	void destroy() throws JAXRPCException;
	
	/**
	 * @deprecated
	 * Sets SOAP Actor roles for this HandlerChain. This specifies the set of roles in which this HandlerChain is to act for the
	 * SOAP message processing at this SOAP node. These roles assumed by a HandlerChain must be invariant during the
	 * processing of an individual SOAP message through the HandlerChain.
	 * 
	 * A HandlerChain always acts in the role of the special SOAP actor next. Refer to the SOAP specification for the URI
	 * name for this special SOAP actor. There is no need to set this special role using this method. 
	 * @param soapActorNames  - URIs for SOAP actor name
	 * @see javax.xml.rpc.NamespaceConstants
	 */
	void setRoles(java.lang.String[] soapActorNames);
	
	/**
	 * @deprecated
	 * Gets SOAP actor roles registered for this HandlerChain at this SOAP node. The returned array includes the special
	 * SOAP actor next.
	 * @return String[] SOAP Actor roles as URIs
	 */
	java.lang.String[] getRoles();

}
