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

import javax.xml.rpc.JAXRPCException;

/**
 * public interface HandlerLifecycle
 * 
 * The javax.xml.rpc.handler.HandlerLifecycle interface is is the base interface for all JAX-RPC handlers.
 * @version 1.0
 * @author shaas02
 *
 */
public interface HandlerLifecycle {
	
	/**
	 * The init method enables the handler instance to initialize itself. The init method passes the handler configuration as a
	 * HandlerInfo instance. The HandlerInfo is used to configure the handler (for example: setup access to an external
	 * resource or service) during the initialization.
	 * 
	 * In the init method, the handler class may get access to any resources (for example; access to a logging service or
	 * database) and maintain these as part of its instance variables. Note that these instance variables must not have any state
	 * specific to the message processing performed in the various handle methods.
	 * 
	 * @param config - Configuration for the initialization of this handler
	 * 
	 * @throws JAXRPCException - If initialization of the handler fails
	 */
	void init(HandlerInfo config) throws JAXRPCException;
	
	/**
	 * The destroy method indicates the end of lifecycle for a Handler instance. The handler implementation class should
	 * release its resources and perform cleanup in the implementation of the destroy method.
	 * @throws JAXRPCException - If any error during destroy
	 */
	void destroy() throws JAXRPCException;
}
