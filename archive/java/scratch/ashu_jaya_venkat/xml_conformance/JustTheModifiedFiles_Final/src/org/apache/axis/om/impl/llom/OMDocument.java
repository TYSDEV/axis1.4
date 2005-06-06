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
package org.apache.axis.om.impl.llom;

import org.apache.axis.om.OMElement;
import org.apache.axis.om.OMNode;
import org.apache.axis.om.OMXMLParserWrapper;

import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.XMLStreamException;


/**
 * Class OMDocument
 */
public class OMDocument extends OMElementImpl{
    /**
     * Field rootElement
     */
    private OMElement rootElement;

    /**
     * Field parserWrapper
     */
    private OMXMLParserWrapper parserWrapper;

    /**
     * Constructor OMDocument
     *
     * @param rootElement
     * @param parserWrapper
     */
    public OMDocument(OMElement rootElement, OMXMLParserWrapper parserWrapper) {
    	super("DOCUMENT",null,null, parserWrapper);
        this.rootElement = rootElement;
        this.parserWrapper = parserWrapper;
        addChild(rootElement);
    }

    /**
     * Constructor OMDocument
     *
     * @param parserWrapper
     */
    public OMDocument(OMXMLParserWrapper parserWrapper) {
    	super("DOCUMENT",null,null, parserWrapper);
        this.parserWrapper = parserWrapper;
        this.firstChild = null;
    }

    /**
     * Method getRootElement
     *
     * @return OMElement
     */
    public OMElement getRootElement() {
        if (rootElement == null) {
            parserWrapper.next();
        }
        return rootElement;
    }

    /**
     * Method setRootElement
     *
     * @param rootElement
     */
    public void setRootElement(OMElement rootElement) {
        this.rootElement = rootElement;
    }

    //Though for historic reasons OMDocument has been made to extend OMElement
    //during serialization of OMDocument we wouldn't expect it to get serialized
    //like any OMElement. Hence am overriding this method.
    //Essentially, serializing a OMDocument means writing out all its children
    //and only the children i.e. OMDocument start and end parts are abstract and
    //should not figure in the written out piece of XML
    /**
     * Method serialize
     *
     * @param writer
     * @param cache
     */
    private void serialize(XMLStreamWriter writer, boolean cache)
    	throws XMLStreamException {
    	OMNode firstChild;
    	firstChild = this.getFirstChild();

		if(!cache)
			firstChild.serialize(writer);
		else
			firstChild.serializeWithCache(writer);
    }

    /**
     * Method serialize
     *
     * @param writer
     */
    public void serialize(XMLStreamWriter writer) throws XMLStreamException {
    	serialize(writer, false);
    }

    /**
     * Method serializeWithCache
     *
     * @param writer
     */
    public void serializeWithCache(XMLStreamWriter writer) throws XMLStreamException {
    	serialize(writer, true);
    }
}
