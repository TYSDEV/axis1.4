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
package org.apache.geronimo.ews.ws4j2ee.context.webservices.server;

import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFConstants;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFDocument;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFWebservices;

/**
 * This will encapsulate the webservices.xml document and this is the start of the 
 * parsing tree.This will be used to support the the methods of the WSCFContext. The 
 * class tree and the Interface tree will strat from here and there will be sufficient
 * functionality provided by each class to expose the information and to further
 * drill down the element tree. 
 * 
 */
public abstract class AsbtractWSCFDocument implements WSCFConstants, WSCFDocument{

	/**
	 * Will keep a reference to the webservices element which is the document element in the webservices.xml
	 * @see org.apache.x.ws4j2ee.context.webservices.interfaces.WSCFWebservices
	 */
	protected WSCFWebservices webservices;

//
///////////////////////////////////The jaxb delegation block////////////////////////////////////////////////
//
////	//This is kept basically to meet the future demands of the serialising.
////	protected Webservices jaxbWebservices;
//
//	public AsbtractWSCFDocumentImpl(InputStream in)throws WSCFException{
//		try{
//			JAXBContext jc    
//                = JAXBContext.newInstance(
//                        "org.apache.geronimo.ews.ws4j2ee.context.webservices.server.jaxb");
//			// create an Unmarshaller
//			Unmarshaller unmarshaller = jc.createUnmarshaller();
//	
//			// unmarshal a FooBar instance document into a tree of Java content
//			// objects composed of classes from the example package.
//			this.webservices = new AbstractWSCFWebservicesImpl((Webservices)unmarshaller.unmarshal(in));
//			
//			
//	//		  org.apache.x.ws4j2ee.context.webservices.jaxb.impl.DisplayNameTypeImpl dis = (org.apache.x.ws4j2ee.context.webservices.jaxb.impl.DisplayNameTypeImpl)ws.getDisplayName().get(0);
//		} catch( JAXBException je ) {
//			throw new WSCFException(je);
//		} 
//		
//	}
//
//
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////


//	/**
//	 * The constructor
//	 * @param doc org.w3c.dom.Document object of the webservices.xml
//	 * @throws WSCFException 
//	 */
//	public WSCFDocumentImpl(Document doc) throws WSCFException{
//		Element documentElement = doc.getDocumentElement();		
//		this.webservices = new WSCFWebservicesImpl(documentElement);
//		
//		
//	}
	/**
	 * Getter fo the Webservices element which is the document element of the 
	 * webservices.xml
	 * @return The Webservices object.
	 * @see org.apache.x.ws4j2ee.context.webservices.interfaces.WSCFWebservices
	 */
	public WSCFWebservices getWebservices() {
		return webservices;
	}

///**
// * @return
// */
//public Webservices getJaxbWebservices() {
//	return jaxbWebservices;
//}

}
