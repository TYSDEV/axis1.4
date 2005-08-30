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
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Vector;

/**
 * This will represent an Element in the WebService.xml configuration file.
 * EVERY concrete Element class in the configuration file will directly or indirectlt
 * will extend this class and will show the polymorphic behavior defined here.
 * The class has been taken from the axis WSDDElement class
 */
public abstract class WSCFElement implements WSCFConstants {

    public WSCFElement() {
    }

    public WSCFElement(Element e) throws WSCFException {
        //TODO validate for the naspaces and the URIs
    }

    /**
     * Gets the child element of name <code>name<\code> from the element passed <code>e</code>
     *
     * @param e    Element
     * @param name name of the child element to be searched
     * @return child Element
     */
    public Element getChildElement(Element e, String name) {
        Element[] elements = getChildElements(e, name);
        if (elements.length == 0)
            return null;
        return elements[0];
    }

    /**
     * Gets the child elements of name <code>name<\code> from the element passed <code>e</code>
     *
     * @param e    Element
     * @param name name of the child element to be searched
     * @return child Elements
     */
    public Element[] getChildElements(Element e, String name) {
        NodeList nl = e.getChildNodes();
        Vector els = new Vector();
        for (int i = 0; i < nl.getLength(); i++) {
            Node thisNode = nl.item(i);
            if (!(thisNode instanceof Element))
                continue;
            Element el = (Element) thisNode;
            if (el.getLocalName().equals(name)) {
                els.add(el);
            }
        }
        Element[] elements = new Element[els.size()];
        els.toArray(elements);
        return elements;
    }
}
