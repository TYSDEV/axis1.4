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

import com.sun.java.xml.ns.j2Ee.WebservicesDocument;
import com.sun.java.xml.ns.j2Ee.WebservicesType;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.AsbtractWSCFDocument;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.WSCFException;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFConstants;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFDocument;
import org.apache.xmlbeans.XmlException;

import java.io.IOException;
import java.io.InputStream;

/**
 * This will encapsulate the webservices.xml document and this is the start of the
 * parsing tree.This will be used to support the the methods of the WSCFContext. The
 * class tree and the Interface tree will strat from here and there will be sufficient
 * functionality provided by each class to expose the information and to further
 * drill down the element tree.
 */
public class WSCFDocumentImpl extends AsbtractWSCFDocument implements WSCFConstants, WSCFDocument {
    public WSCFDocumentImpl(InputStream in) throws WSCFException {
        try {
            WebservicesDocument wsDoc = WebservicesDocument.Factory.parse(in);
            WebservicesType ws = wsDoc.getWebservices();
            this.webservices = new WSCFWebservicesImpl(ws);
        } catch (IOException je) {
            throw new WSCFException(je);
        } catch (XmlException je) {
            throw new WSCFException(je);
        }
    }
}
