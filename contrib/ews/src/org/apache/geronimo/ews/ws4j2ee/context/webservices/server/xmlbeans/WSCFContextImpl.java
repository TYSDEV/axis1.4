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
package org.apache.geronimo.ews.ws4j2ee.context.webservices.server.xmlbeans;

import java.io.InputStream;

import org.apache.geronimo.ews.ws4j2ee.context.J2EEWebServiceContext;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.AbstractWSCFContext;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.WSCFException;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFContext;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFDocument;

/**
 * This is the concrete implementation of the WSCFContext where the whole context of the
 * webservices.xml file will be published. The appropreate factory class will instanciate the class 
 * and in the factory a inputstream to the webservices.xml will be requires. This is not the 
 * starting point of the element tree, instead this s only giving an inteface
 * to the element tree which is rooted at WSCFDocument.
 *
 */
public class WSCFContextImpl extends AbstractWSCFContext implements WSCFContext {
	/**
	 * The constructor that will create the element tree starting from the root element as WSCFDocument.
	 * @param document The document object to the webservices.xml
	 * @throws WSCFException 
	 */
	public WSCFContextImpl(InputStream in,J2EEWebServiceContext context) throws WSCFException{
		this.document = (WSCFDocument) new WSCFDocumentImpl(in);
	}
}
