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

package javax.xml.rpc;

import java.util.List;
import javax.xml.rpc.handler.HandlerInfo;
import javax.xml.rpc.security.SecurityConfiguration;

/**
 * @author sunja07
 */
public interface Binding {
	
	/**
	 * Method getHandlerChain
	 * Gets the handler chain for protocol binding instance. The returned 
	 * List is used to configure the handler chain.
	 * 
	 * @return java.util.List Handler chain
	 */
	List<HandlerInfo> getHandlerChain();
	
	/**
	 * Method setHandlerChain
	 * Sets the handler chain for the protocol binding instance.
	 * 
	 * @param chain - A List of handler configuration entries
	 * @throws JAXRPCException - On an error in the configuration of the 
	 * handler chain 
	 java.lang.UnsupportedOperationException - If this operation is not 
	 supported. This may be done to avoid any overriding of a pre-configured 
	 handler chain.
	 */
	void setHandlerChain(java.util.List<HandlerInfo> chain) throws 
	JAXRPCException;
	
	/**
	 * Method getSecurityConfiguration
	 * Gets the SecurityConfiguration for this Binding object.
	 * 
	 * @return The SecurityConfiguration for this Binding object.
	 * @throws java.lang.UnsupportedOperationException - if the Binding class 
	 * does not support the configuration of SecurityConfiguration.
	 */
	SecurityConfiguration getSecurityConfiguration() throws 
	java.lang.UnsupportedOperationException;
	
}
