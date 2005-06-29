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

import javax.xml.rpc.Binding;
import javax.xml.rpc.BindingProvider;
import javax.xml.rpc.JAXRPCContext;

public class BindingProviderImpl implements BindingProvider {

	protected JAXRPCRequestContext requestContext;
	protected JAXRPCResponseContext responseContext;
	
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
		
		return null;
	}

}
