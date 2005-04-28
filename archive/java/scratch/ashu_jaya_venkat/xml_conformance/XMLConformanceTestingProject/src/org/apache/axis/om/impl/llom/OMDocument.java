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
import org.apache.axis.om.OMXMLParserWrapper;

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
     * @return
     */
    public OMElement getRootElement() {
        while (rootElement == null) {
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
}
