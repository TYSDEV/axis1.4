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
package org.apache.axis.om.impl.llom.factory;

import org.apache.axis.om.*;
import org.apache.axis.om.impl.llom.OMElementImpl;
import org.apache.axis.om.impl.llom.OMNamespaceImpl;
import org.apache.axis.om.impl.llom.OMTextImpl;
import org.apache.axis.om.impl.llom.OMAttributeImpl;
import org.apache.axis.om.impl.llom.OMPIImpl;
import org.apache.axis.om.impl.llom.OMDTDImpl;
import org.apache.axis.om.impl.llom.OMCommentImpl;
import org.apache.axis.om.impl.llom.OMDocument;


import javax.xml.namespace.QName;

/**
 * Class OMLinkedListImplFactory
 */
public class OMLinkedListImplFactory implements OMFactory {
    /**
     * Field MAX_TO_POOL
     */
    public static final int MAX_TO_POOL = 100;

    /**
     * Method createOMElement
     *
     * @param localName
     * @param ns
     * @return
     */
    public OMElement createOMElement(String localName, OMNamespace ns) {
        OMElementImpl element = new OMElementImpl(localName, ns);
        return element;
    }

    /**
     * Method createOMElement
     *
     * @param localName
     * @param ns
     * @param parent
     * @param builder
     * @return
     */
    public OMElement createOMElement(String localName, OMNamespace ns,
                                     OMElement parent,
                                     OMXMLParserWrapper builder) {
        OMElementImpl element = new OMElementImpl(localName, ns, parent,
                builder);
        return element;
    }

    /**
     * Method createOMElement
     *
     * @param localName
     * @param namespaceURI
     * @param namespacePrefix
     * @return
     */
    public OMElement createOMElement(String localName, String namespaceURI,
                                     String namespacePrefix) {
        return this.createOMElement(localName,
                this.createOMNamespace(namespaceURI,
                        namespacePrefix));
    }

    /**
     * Method createOMElement
     *
     * @param qname
     * @param parent
     * @return
     * @throws OMException
     */
    public OMElement createOMElement(QName qname, OMElement parent)
            throws OMException {
        return new OMElementImpl(qname, parent);
    }

    /**
     * Method createOMNamespace
     *
     * @param uri
     * @param prefix
     * @return
     */
    public OMNamespace createOMNamespace(String uri, String prefix) {
        return new OMNamespaceImpl(uri, prefix);
    }

    /**
     * Method createText
     *
     * @param parent
     * @param text
     * @return
     */
    public OMText createText(OMElement parent, String text) {
        OMTextImpl textNode = new OMTextImpl(parent, text);
        return textNode;
    }

    /**
     * Method createText
     *
     * @param s
     * @return
     */
    public OMText createText(String s) {
        OMTextImpl textNode = new OMTextImpl(s);
    ;
        return textNode;
    }

    public OMAttribute createOMAttribute(String localName, OMNamespace ns, String value) {
          return new OMAttributeImpl(localName, ns, value);
      }
    
    /**
     * Method createOMDTD
     * 
     * @param parent
     * @param content
     * @return
     */
    /* */
     //Actually this implementation is just a makeshift implementation of DTD.
     //No validation checking, no processing of contents whatsoever. Just would
     //capture all of the contents as text and wrap it as a OMDTD node.
     public OMDTD createOMDTD(OMDocument parent, String content) {
     	OMDTDImpl omDTD = new OMDTDImpl((OMElement)parent, content);
     	return omDTD;
     }
    /**/

    /**
     * Method createOMPI
     *
     * @param parent
     * @param piTarget
     * @param piData
     * @return
     */
    public OMPI createOMPI(OMElement parent, String piTarget, String piData) {
    	OMPIImpl omPI = new OMPIImpl(parent, piTarget, piData);
    	return omPI;
    }
    
    /**
     * Method createOMComment
     *
     * @param parent
     * @param content
     * @return
     */
    public OMComment createOMComment(OMElement parent, String content) throws OMException{
    	OMCommentImpl commentNode = new OMCommentImpl(parent,content);
    	return commentNode;
    }


}
