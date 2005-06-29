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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.BindingProvider;
import javax.xml.rpc.JAXRPCContext;
import javax.xml.rpc.JAXRPCException;

public class JAXRPCContextImpl implements JAXRPCContext {

	protected static List<String> standardProperties;
	protected static Map properties;
	protected Object associatedObject;
	
	public JAXRPCContextImpl(BindingProvider provider){
		
		associatedObject = provider;
		
		properties = new HashMap();
		
		standardProperties = new ArrayList();
		standardProperties.add("javax.xml.rpc.binding.attachments");
		standardProperties.add("javax.xml.rpc.wsdl.description");
		standardProperties.add("javax.xml.rpc.wsdl.service");
		standardProperties.add("javax.xml.rpc.wsdl.port");
		standardProperties.add("javax.xml.rpc.wsdl.interface");
		standardProperties.add("javax.xml.rpc.wsdl.operation");
	}
	
	public void setProperty(String name, Object value)
			throws IllegalArgumentException {
		validateProperty(name, value);
		properties.put(name, value);
	}

	public void removeProperty(String name) throws IllegalArgumentException {
		validateProperty(name, null);
		properties.remove(name);
	}

	public Object getProperty(String name) throws IllegalArgumentException {
		validateProperty(name, null);
		return properties.get(name);
	}

	public Iterator getPropertyNames() {
		if(properties != null)
			return properties.keySet().iterator();
		else
			return null;
	}
	
	private void validateProperty(String name, Object value){
		if(name == null)
			throw new JAXRPCException("User-defined property name can not be null");
		if(standardProperties.indexOf(name) == -1 && name.startsWith("javax.xml.rpc"))
			throw new JAXRPCException("User-Defined property can not start with javax.ml.rpc");

	}

}
