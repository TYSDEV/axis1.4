package org.apache.axis.impl.llom.builder;

import org.apache.axis.impl.llom.OMDocument;
import org.apache.axis.impl.llom.OMElementImpl;
import org.apache.axis.impl.llom.mtom.OMBlob;
import org.apache.axis.om.*;

import javax.xml.stream.XMLStreamConstants;
import java.io.InputStream;

/**
 * Copyright 2001-2004 The Apache Software Foundation.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <p/>
 */

/**
 * This will construct an OM without using SOAP specific classes like
 * SOAPEnvelope, SOAPHeader, SOAPHeaderBlock and SOAPBody. And this will habe
 * the Document concept also.
 */
public class StAXOMBuilder extends StAXBuilder implements OMXMLParserWrapper {
    protected OMDocument document;

    /*
     * Edited by Thilina Gunarathne
     */
    public StAXOMBuilder(OMFactory ombuilderFactory, InputStream stream) {
        super(ombuilderFactory, stream);
        document = new OMDocument(this);
    }

    public StAXOMBuilder(InputStream stream) {
        super(stream);
        document = new OMDocument(this);
    }

    protected OMNode createOMElement() throws OMException {

        String elementName = parser.getLocalName();
        String namespaceURI = parser.getNamespaceURI();

        if (elementName.equalsIgnoreCase("Include")
                & namespaceURI
                .equalsIgnoreCase("http://www.w3.org/2004/08/xop/include")) {
            OMBlob node;
            String CID = null;
            String CIDName = null;
            boolean found = false;
            OMAttribute Attr;

            CID = parser.getAttributeValue(0);
            CID = CID.trim();
            CIDName = parser.getAttributeLocalName(0);
            if (CIDName.equalsIgnoreCase("href")
                    & CID.substring(0, 3).equalsIgnoreCase("cid")) {
                CID = CID.substring(4);
                found = true;
            }

            if (found) {
                if (lastNode.isComplete()) {
                    node = new OMBlob(CID, lastNode.getParent(), MtomBuilder);
                    lastNode.setNextSibling(node);
                    node.setPreviousSibling(lastNode);
                } else {
                    OMElement e = (OMElement) lastNode;
                    node = new OMBlob(CID, (OMElement) lastNode, MtomBuilder);
                    e.setFirstChild(node);
                }
            } else {
                throw new OMException("CID not Found");
            }
            return node;
        } else {
            OMElement node;
            if (lastNode == null) {
                node = new OMElementImpl(elementName, null, null, this);
                document.setRootElement(node);
            } else if (lastNode.isComplete()) {
                node = new OMElementImpl(elementName, null, lastNode
                        .getParent(), this);
                lastNode.setNextSibling(node);
                node.setPreviousSibling(lastNode);
            } else {
                OMElement e = (OMElement) lastNode;
                node = new OMElementImpl(elementName, null,
                        (OMElement) lastNode, this);
                e.setFirstChild(node);
            }

            //create the namespaces
            processNamespaceData(node, false);

            //fill in the attributes
            processAttributes(node);

            return node;
        }
    }

    /*
     * Edit Over
     */

    public SOAPEnvelope getOMEnvelope() throws OMException {
        throw new UnsupportedOperationException(); //TODO implement this
    }

    public int next() throws OMException {
        try {

            if (done)
                throw new OMException();

            int token = parser.next();

            if (!cache) {
                return token;
            }

            switch (token) {
                case XMLStreamConstants.START_ELEMENT:
                    lastNode = createOMElement();
                    break;

                case XMLStreamConstants.START_DOCUMENT:
                    document = new OMDocument(this);
                    break;

                case XMLStreamConstants.CHARACTERS:
                    lastNode = createOMText();
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    if (lastNode.isComplete()) {
                        OMElement parent = lastNode.getParent();
                        parent.setComplete(true);
                        lastNode = parent;
                    } else {
                        OMNode e = (OMNode) lastNode;
                        e.setComplete(true);
                    }
                    break;

                case XMLStreamConstants.END_DOCUMENT:
                    done = true;

                    break;
                case XMLStreamConstants.SPACE:
                    next();
                    break;

                default:
                    throw new OMException();
            }
            return token;
        } catch (OMException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new OMException(e);
        }
    }

    public OMElement getDocumentElement() {
        return document.getRootElement();
    }

    protected void processNamespaceData(OMElement node, boolean isSOAPElement) {
        int namespaceCount = parser.getNamespaceCount();
        for (int i = 0; i < namespaceCount; i++) {
            node.declareNamespace(parser.getNamespaceURI(i), parser
                    .getNamespacePrefix(i));
        }

        //set the own namespace
        OMNamespace namespace = node.findInScopeNamespace(parser
                .getNamespaceURI(), parser.getPrefix());
        node.setNamespace(namespace);
    }

}