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

import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.AbstractWSCFSOAPHeader;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFSOAPHeader;

import com.sun.java.xml.ns.j2Ee.XsdQNameType;

/**
 * This encapsulates the soap header of the handler element. The information about this element can be accessed uaing the interface published.
 * Basically this is  a Qname
 */
public class WSCFSOAPHeaderImpl extends AbstractWSCFSOAPHeader implements WSCFSOAPHeader {
	public WSCFSOAPHeaderImpl(XsdQNameType header){
		if(null != header.getQNameValue()){	
			this.localpart = header.getQNameValue().getLocalPart();
			this.namespaceURI = header.getQNameValue().getNamespaceURI();
		}
	}
}
