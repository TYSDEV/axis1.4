/*
 * Copyright 2001-2004 The Apache Software Foundation.
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
package org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces;


/**
 * This will interface the webservices.xml document itself. it represents teh he
 * webservices.xml document and will give an interface to the document element:the webservices element; where the element tree begins
 *
 */
public interface WSCFDocument {
	
	/**
	 * The is the getter for the document element thus the gateway to all teh elements in the webservices.xml
	 * @return webservices Element
	 */
	public WSCFWebservices getWebservices()  ;
}
