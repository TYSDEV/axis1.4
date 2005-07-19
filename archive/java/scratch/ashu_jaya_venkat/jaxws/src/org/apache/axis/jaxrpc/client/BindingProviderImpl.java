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

package org.apache.axis.jaxrpc.client;

import java.net.URI;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.rpc.Binding;
import javax.xml.rpc.BindingProvider;
import javax.xml.rpc.JAXRPCContext;
import javax.xml.rpc.handler.HandlerInfo;

public class BindingProviderImpl implements BindingProvider {

	protected JAXRPCRequestContext requestContext;
	protected JAXRPCResponseContext responseContext;
	protected BindingImpl binding;
	
	protected List<HandlerInfo> serviceHandlerChain;
	protected Map<URI, List> bindingHandlerChain;
	protected Map<QName, List> portHandlerChain;
	
	public BindingProviderImpl(){
		//default constructor
	}
	
	public JAXRPCContext getRequestContext() {
		if(requestContext == null)
			requestContext = new JAXRPCRequestContext(this);
		return requestContext;
	}

	public JAXRPCContext getResponseContext() {
		if(responseContext == null)
			responseContext = new JAXRPCResponseContext(this);
		return null;
	}

	public Binding getBinding() {
		
		return binding;
	}
	
	public void setBinding(BindingImpl binding) {
		this.binding = binding;
	}
	
	public List getHandlerChain(){
		// For now just returning ServiceHandlerChain. Later may decide
		// on what basis should take handlers from bindingHandlerChain and portHandlerChain
		return serviceHandlerChain;
	}

}
