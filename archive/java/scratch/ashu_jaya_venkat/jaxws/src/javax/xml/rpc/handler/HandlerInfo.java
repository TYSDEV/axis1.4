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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

/**
 * public class HandlerInfo
 * extends java.lang.Object
 * implements java.io.Serializable
 * <p>
 * The javax.xml.rpc.handler.HandlerInfo represents information about a handler in the HandlerChain. A HandlerInfo
 * instance is passed in the Handler.init method to initialize a Handler instance.
 * @version 1.0
 * @author shaas02
 * @see <code>HandlerChain</code>, <code>SerializedForm</code>
 */
public class HandlerInfo implements Serializable {
	
	private Class handlerClass;
	private Map config;
	private List<QName> headers;
	
	
	/**
	 * Default constructor
	 */
	HandlerInfo(){
		handlerClass = null;
		config = new HashMap();
		headers = new ArrayList<QName>();
	}

	/**
	 * Constructor for HandlerInfo
	 * @param handlerClass - Java Class for the Handler
	 * @param config - Handler Configuration as a java.util.Map
	 * @param headers -  - QNames for the header blocks processed by this Handler. QName is the qualified name of the
	 * outermost element of a header block
	 */
	HandlerInfo(java.lang.Class handlerClass, java.util.Map config,
			javax.xml.namespace.QName[] headers){
		this.handlerClass = handlerClass;
		this.config = config;
		if(headers != null){
			for(int i = 0; i < headers.length; i++){
				if(headers[i] != null){
					this.headers.add(headers[i]);
				}
			}
		}
	}
	
	/**
	 * Sets the Handler class
	 * @param handlerClass - Class for the Handler
	 */
	public void setHandlerClass(java.lang.Class handlerClass){
		this.handlerClass = handlerClass;
	}
	
	/**
	 * Gets the Handler class
	 * @return Returns null if no Handler class has been set; otherwise the set handler class
	 */
	public java.lang.Class getHandlerClass(){
		return handlerClass;
	}
	
	/**
	 * Sets the Handler configuration as java.util.Map
	 * @param config - Configuration map
	 */
	public void setHandlerConfig(java.util.Map config){
		this.config = config;
	}
	
	/**
	 * Gets the Handler configuration
	 * @return Returns empty Map if no configuration map has been set; otherwise returns the set configuration map
	 */
	public java.util.Map getHandlerConfig(){
		return config;
	}
	
	/**
	 * Sets the header blocks processed by this Handler.
	 * @param headers - QNames of the header blocks. QName is the qualified name of the outermost element of the SOAP
	 * header block
	 */
	public void setHeaders(javax.xml.namespace.QName[] headers){
		if(headers != null){
			for(int i = 0; i < headers.length; i++){
				if(headers[i] != null){
					this.headers.add(headers[i]);
				}
			}
		}
	}
	
	/**
	 * Gets the header blocks processed by this Handler.
	 * @return Array of QNames for the header blocks. Returns null if no header blocks have been set using the setHeaders
	 * method.
	 */
	public javax.xml.namespace.QName[] getHeaders(){
		if(headers == null || headers.size() == 0){
			return null;
		}else{
			QName[] elements = new QName[headers.size()];
			for(int i = 0; i < headers.size(); i++){
				elements[i] = headers.get(i);
			}
			return elements;
		}
	}
}
