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

package org.apache.geronimo.ews.ws4j2ee.utils;

import org.apache.geronimo.ews.ws4j2ee.toWs.GenerationFault;
import org.w3c.dom.Document;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author hemapani@opensource.lk
 */
public class EWSUtils {
    public static Document createDocument(InputStream in) throws GenerationFault {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            dbf.setValidating(false);
            dbf.setExpandEntityReferences(false);
            DocumentBuilder db = dbf.newDocumentBuilder();
            EntityResolver er = new EntityResolver() {
                public InputSource resolveEntity(String publicId,
                                                 String systemId)
                        throws SAXException, IOException {
                    InputStream is = null;
                    if ("http://java.sun.com/dtd/ejb-jar_2_0.dtd".equalsIgnoreCase(systemId)) {
                        return getInputSource(EWSUtils.class.getClassLoader().getResourceAsStream("ejb-jar_2_0.dtd"));
                    } else if ("http://java.sun.com/dtd/web-app_2_3.dtd".equalsIgnoreCase(systemId))
                        return getInputSource(EWSUtils.class.getClassLoader().getResourceAsStream("web-app_2_3.dtd"));
                    return null;
                }

                private InputSource getInputSource(InputStream is) throws IOException {
                    if (is == null)
                        throw new IOException("error at the project set up can not find entity");
                    return new InputSource(is);
                }
            };
            db.setEntityResolver(er);
            return db.parse(in);
        } catch (Exception e) {
            throw GenerationFault.createGenerationFault(e);
        }
    }
}
