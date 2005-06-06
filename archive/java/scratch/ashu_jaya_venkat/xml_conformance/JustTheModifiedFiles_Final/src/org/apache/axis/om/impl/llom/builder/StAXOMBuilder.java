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
package org.apache.axis.om.impl.llom.builder;

import org.apache.axis.om.*;
import org.apache.axis.om.impl.llom.OMDocument;
import org.apache.axis.soap.SOAPEnvelope;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

/**
 * This will construct an OM without using SOAP specific classes like SOAPEnvelope, SOAPHeader, SOAPHeaderBlock and SOAPBody.
 * And this will habe the Document concept also.
 */
public class StAXOMBuilder extends StAXBuilder implements OMXMLParserWrapper {
    /**
     * Field document
     */
    protected OMDocument document;

    /**
     * Field omFactory
     */
    protected OMFactory omFactory;

    /**
     * Field foundRootElement
     * keeps state of occurence of document element
     */
    protected boolean foundRootElement = false;


    /**
     * Constructor StAXOMBuilder
     *
     * @param ombuilderFactory
     * @param parser
     */
    public StAXOMBuilder(OMFactory ombuilderFactory, XMLStreamReader parser) {
        super(ombuilderFactory, parser);
        document = new OMDocument(this);
        omfactory = OMAbstractFactory.getOMFactory();
    }

    /**
     * Constructor StAXOMBuilder
     *
     * @param parser
     */
    public StAXOMBuilder(XMLStreamReader parser) {
        super(parser);
        document = new OMDocument(this);
        omfactory = OMAbstractFactory.getOMFactory();
    }

    /**
     * Method createOMElement
     *
     * @return OMNode
     * @throws OMException
     */
    protected OMNode createOMElement() throws OMException {
        OMElement node=null;
        String elementName = parser.getLocalName();
        if (!foundRootElement) {
            node = omfactory.createOMElement(elementName, null, (OMElement)document, this);
            document.setRootElement(node);
            foundRootElement = true;
            if (lastNode == null) {
            	((org.apache.axis.om.impl.llom.OMElementImpl)document).setFirstChild(node);
            }else if (lastNode.isComplete()) {
            lastNode.setNextSibling(node);
            node.setPreviousSibling(lastNode);
        	} else {
            OMElement e = (OMElement) lastNode;
            node = omfactory.createOMElement(elementName, null,
                    (OMElement) lastNode, this);
            e.setFirstChild(node);
        	}
        }else {
        	if (lastNode.isComplete()) {
            node = omfactory.createOMElement(elementName, null,
                    lastNode.getParent(), this);
            lastNode.setNextSibling(node);
            node.setPreviousSibling(lastNode);
        	} else {
            OMElement e = (OMElement) lastNode;
            node = omfactory.createOMElement(elementName, null,
                    (OMElement) lastNode, this);
            e.setFirstChild(node);
        	}
        }

        // create the namespaces
        processNamespaceData(node, false);

        // fill in the attributes
        processAttributes(node);
        return node;
    }

    /** Method createOMPI
     *
     * @return OMPI
     * @throws OMException
     */
    public OMPI createOMPI() throws OMException{
    	String piTarget = parser.getPITarget();
    	String content = parser.getPIData();
    	OMPI omPI;
    	if(lastNode == null) {//meaning it is a document level element
    		omPI = omfactory.createOMPI(document, piTarget, content);
    		omPI.setComplete(true);
    		//also here we should add this comment as a child of document element.
    		//But that functionality is missing in OMDocument class. So lets wait for it.
    		document.setFirstChild(omPI);
    	}
    	else {
    		//this is a PI inside some element
    		if (lastNode.isComplete()) {
    			omPI = omfactory.createOMPI(lastNode.getParent(), piTarget, content);
    			omPI.setComplete(true);
    			lastNode.setNextSibling((OMNode)omPI);
    			omPI.setPreviousSibling(lastNode);
    		} else {
            OMElement e = (OMElement) lastNode;
            omPI = omfactory.createOMPI(e, piTarget, content);
            omPI.setComplete(true);
            e.setFirstChild(omPI);
            }
        }
    	return omPI;
    }

    /**
     * Method createOMComment
     *
     * @return OMComment
     * @throws OMException
     */
    public OMComment createOMComment() throws OMException {
    	OMComment comment;
    	String content = parser.getText();
    	if(lastNode == null) {//meaning it is a document level comment and is the first child of document too
    		comment = omfactory.createOMComment(document,content);
    		comment.setComplete(true);
    		document.setFirstChild(comment);
    	}
    	else {
    		//this is a comment inside some element
    		if (lastNode.isComplete()) {
    			comment = omfactory.createOMComment(lastNode.getParent(), content);
    			comment.setComplete(true);
    			lastNode.setNextSibling(comment);
    			comment.setPreviousSibling(lastNode);
    		} else {
            OMElement e = (OMElement) lastNode;
            comment = omfactory.createOMComment(e, content);
            comment.setComplete(true);
            e.setFirstChild(comment);
            }
        }
        return comment;
    }

    /**
     * Method createOMDTD
     *
     * @return OMDTD
     * @throws OMException
     */
    public OMDTD createOMDTD() throws OMException {
    	OMDTD dtd;
    	String content = parser.getText();
    	//By default only one DOCTYPE declaration can be present and it will be
    	//well outside of document element scope. so lastNode is null for this always
    	dtd = omfactory.createOMDTD(document, content);
    	dtd.setComplete(true);
    	if (lastNode == null)
    		document.setFirstChild(dtd);
    	else if (lastNode.isComplete()) {
    		lastNode.setNextSibling(dtd);
    		dtd.setPreviousSibling(lastNode);
    	}
    	else { //meaning dtd is being tried to be put inside as a child of some element
    		throw new OMException("DTD can't be a child of any element");
    	}
    	return dtd;
    }

    /**
     * Method getOMEnvelope
     *
     * @return SOAPEnvelope
     * @throws OMException
     */
    public SOAPEnvelope getOMEnvelope() throws OMException {
        throw new UnsupportedOperationException();    // TODO implement this
    }

    /**
     * Method next
     *
     * @return int
     * @throws OMException
     */
    public int next() throws OMException {
        try {
            if (done) {
                throw new OMException();
            }
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
                        OMElement e = (OMElement) lastNode;
                        e.setComplete(true);
                    }
                    break;
                case XMLStreamConstants.END_DOCUMENT:
                    done = true;
                	document.setComplete(true);
                    break;
                case XMLStreamConstants.SPACE:
                    next();
                    break;
                case XMLStreamConstants.DTD:
                	lastNode = createOMDTD();
                	break;
                case XMLStreamConstants.PROCESSING_INSTRUCTION:
                	lastNode = createOMPI();
                	break;
                case XMLStreamConstants.COMMENT:
                	lastNode = createOMComment();
                	break;
                default :
                    throw new OMException();
            }
            return token;
        } catch (OMException e) {
            throw e;
        } catch (Exception e) {
            throw new OMException(e);
        }
    }

    /**
     * Method getDocumentElement
     *
     * @return OMElement
     */
    public OMElement getDocumentElement() {
        return document.getRootElement();
    }

    /**
     * Method getDocument
     *
     * @return OMElement
     */
    public OMElement getDocument() {
        return document;
    }

    /**
     * Method processNamespaceData
     *
     * @param node
     * @param isSOAPElement
     */
    protected void processNamespaceData(OMElement node, boolean isSOAPElement) {
        int namespaceCount = parser.getNamespaceCount();
        for (int i = 0; i < namespaceCount; i++) {
            node.declareNamespace(parser.getNamespaceURI(i),
                    parser.getNamespacePrefix(i));
        }

        // set the own namespace
        OMNamespace namespace =
                node.findNamespace(parser.getNamespaceURI(),
                parser.getPrefix());
        node.setNamespace(namespace);
    }
}
