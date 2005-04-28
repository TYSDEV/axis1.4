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

import org.apache.axis.om.OMElement;
import org.apache.axis.om.OMException;
import org.apache.axis.om.OMFactory;
import org.apache.axis.om.OMNamespace;
import org.apache.axis.om.OMNode;
import org.apache.axis.om.OMComment;
import org.apache.axis.om.OMPI;
import org.apache.axis.om.OMDTD;
//import org.apache.axis.om.OMText;
import org.apache.axis.om.OMXMLParserWrapper;
import org.apache.axis.om.SOAPEnvelope;
import org.apache.axis.om.impl.llom.OMDocument;

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
     * Constructor StAXOMBuilder
     *
     * @param ombuilderFactory
     * @param parser
     */
    public StAXOMBuilder(OMFactory ombuilderFactory, XMLStreamReader parser) {
        super(ombuilderFactory, parser);
        document = new OMDocument(this);
        omfactory = OMFactory.newInstance();
    }

    /**
     * Constructor StAXOMBuilder
     *
     * @param parser
     */
    public StAXOMBuilder(XMLStreamReader parser) {
        super(parser);
        document = new OMDocument(this);
        omfactory = OMFactory.newInstance();
    }

    /**
     * Method createOMElement
     *
     * @return
     * @throws OMException
     */
    protected OMNode createOMElement() throws OMException {
        OMElement node=null;
        String elementName = parser.getLocalName();
        if (!foundRootElement) {
            node = omfactory.createOMElement(elementName, null, document, this);
            document.setRootElement(node);
            foundRootElement = true;
            if (lastNode == null) {
            	document.setFirstChild(node);
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
    
    public OMPI createOMPI() throws OMException{
    	String piTarget = parser.getPITarget();
    	String content = parser.getPIData();
    	OMPI omPI;
    	if(lastNode == null) {//meaning it is a document level comment    		    		    		

    		
    		//...No more waiting I've done that
    		omPI = omfactory.createOMPI(document, piTarget, content);
    		omPI.setComplete(true);
    		//also here we should add this comment as a child of document element.
    		//But that functionality is missing in OMDocument class. So lets wait for it.    		
    		document.setFirstChild(omPI);    		
    		//return null; //The reason behind returning null is that the return value of this
    		//will be used to update lastNode variable. Actually for all the stuff 
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
   
    public OMComment createOMComment() throws OMException {
    	OMComment comment;
    	String content = parser.getText();
    	if(lastNode == null) {//meaning it is a document level comment and is the first child of document too    		
    		comment = omfactory.createOMComment(document,content);
    		comment.setComplete(true);
    		document.setFirstChild(comment);    		
    		//return null; //For all nodes outside of document element, lastNode should remain to be null. Hence returning null
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
    
    /**/
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
     /**/
       
    /**
     * Method getOMEnvelope
     *
     * @return
     * @throws OMException
     */
    public SOAPEnvelope getOMEnvelope() throws OMException {
        throw new UnsupportedOperationException();    // TODO implement this
    }

    /**
     * Method next
     *
     * @return
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
                case XMLStreamConstants.DTD:
                	/*
                    System.out.println("DTD element encountered. Currently no support for DTD");
                	System.out.println("DTD text is: "+parser.getText());
                	next();
                	*/
                	lastNode = createOMDTD();
                	break;
                case XMLStreamConstants.PROCESSING_INSTRUCTION:
                	/*
                    System.out.println("Processing Instruction element encountered. Currently no support for Processing Instruction");
                	System.out.println("PI text is: "+parser.getText());
                	next();
                	*/
                	lastNode = createOMPI();
                	break;               
                case XMLStreamConstants.COMMENT:
                	/*
                	System.out.println("Comment element encountered. Currently no support for Comment");
            		System.out.println("Comment text is: "+parser.getText());
            		next();
            		*/
            		lastNode = createOMComment();
                	break;                	
                case XMLStreamConstants.END_DOCUMENT:
                    done = true;
                	document.setComplete(true);
                    break;
                case XMLStreamConstants.SPACE:
                    next();
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
     * @return
     */
    public OMElement getDocumentElement() {
        return document.getRootElement();
    }
    
    /**
     * Method getDocument
     *
     * @return
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
                node.findInScopeNamespace(parser.getNamespaceURI(),
                parser.getPrefix());
        node.setNamespace(namespace);
    }
}
