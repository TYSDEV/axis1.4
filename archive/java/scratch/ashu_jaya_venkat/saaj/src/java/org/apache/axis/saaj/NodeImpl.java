/*
 * Created on Mar 16, 2005
 *
 */
package org.apache.axis.saaj;

import java.util.Iterator;

import javax.xml.soap.Node;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

import org.apache.axis.om.OMAttribute;
import org.apache.axis.om.OMElement;
import org.apache.axis.om.OMNamespace;
import org.apache.axis.om.OMNode;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.CharacterData;

/**
 * Class NodeImpl
 * 
 * @author Ashutosh Shahi
 * ashutosh.shahi@gmail.com
 */
public class NodeImpl implements Node {
	
	/**
	 * Field omNode
	 */
	protected org.apache.axis.om.OMNode omNode;
	/**
	 * field document
	 */
	protected org.w3c.dom.Document document;
	
	//protected CharacterData textRep = null;
	
	/**
	 * Constructor NodeImpl
	 *
	 */
	public NodeImpl(){
	
	}
	
	/**
	 * Constructor NodeImpl
	 * 
	 * @param node
	 */
	public NodeImpl(OMNode node){
		this.omNode = node;
	}
	/**
	 * Constructor NodeImpl
	 * 
	 * @param attrib
	 */
	public NodeImpl(OMAttribute attrib){
		
		// To be implemented
		// Find out a way to construct OMNode from a OMAttribute
		// as OMAttributes are immutable
	}
	
	/**
	 * Constructor NodeImpl
	 * 
	 * @param ns
	 */
	public NodeImpl(OMNamespace ns){
		
		// To be implemented
		// Find out a way to construct OMNode from OMNamespace
		// OMNamespace is immutable
	}
	
     /**
     * constructor which adopts the name and NS of the char data, and its text
     * @param text
     */
/*    public NodeImpl(CharacterData text) {
    	
    }
*/
	/**
	 * Method getValue
	 * 
	 * @see javax.xml.soap.Node#getValue()
	 */
	public String getValue() {
		
		return omNode.getValue();
	}

	/**
	 * Method setParentElement
	 * @param parent
	 * 
	 * @see javax.xml.soap.Node#setParentElement(javax.xml.soap.SOAPElement)
	 */
	public void setParentElement(SOAPElement parent) throws SOAPException {
		
		OMElement omParent = ((SOAPElementImpl)parent).getOMElement();
		omNode.setParent(omParent);
	}

	/**
	 * Method getParentElement
	 * @see javax.xml.soap.Node#getParentElement()
	 */
	public SOAPElement getParentElement() {
		
		OMElement omParent = omNode.getParent();
		return new SOAPElementImpl(omParent);
	}

	/**
	 * Method detachNode
	 * @see javax.xml.soap.Node#detachNode()
	 */
	public void detachNode() {
	
		omNode.detach();
	}

	/**
	 * Method recycleNode
	 * @see javax.xml.soap.Node#recycleNode()
	 */
	public void recycleNode() {
		// No corresponding implementation in OM
		// There is no implementation in Axis 1.2 also

	}

	/**
	 * Method setValue
	 * @param value
	 * 
	 * @see javax.xml.soap.Node#setValue(java.lang.String)
	 */
	public void setValue(String value) {
		omNode.setValue(value);
	}

	/**
	 * Method getNodeType
	 * @see org.w3c.dom.Node#getNodeType()
	 */
	public short getNodeType() {
		
		return omNode.getType();
	}

	/**
	 * Method normalize
	 * @see org.w3c.dom.Node#normalize()
	 */
	public void normalize() {
		// No corresponding function in OM
		//Axis 1.2 also doesn't have any implementation for this

	}

	/**
	 * Method hasAttributes
	 * @see org.w3c.dom.Node#hasAttributes()
	 */
	public boolean hasAttributes() {
		if(omNode instanceof OMElement){
			Iterator iter = ((OMElement)omNode).getAttributes();
			return (iter.hasNext() ? true : false);
		}
		return false;
	}

	/**
	 * Method hasChildNodes
	 * @see org.w3c.dom.Node#hasChildNodes()
	 */
	public boolean hasChildNodes() {
		if(omNode instanceof OMElement){
			Iterator iter = ((OMElement)omNode).getChildren();
			return (iter.hasNext() ? true : false);
		}
		return false;
	}

	/**
	 * Method getLocalName
	 * @see org.w3c.dom.Node#getLocalName()
	 */
	public String getLocalName() {
		if(omNode.getType() == ELEMENT_NODE || omNode.getType()
				== ATTRIBUTE_NODE)
			return omNode.getValue();
		return null;
	}

	/**
	 * Method getNamespaceURI
	 * @see org.w3c.dom.Node#getNamespaceURI()
	 */
	public String getNamespaceURI() {
		// Could not find any method to access the namespace 
		// of the omNode.omElement allows to declare Namespace for 
		// in the context of current element, and stores them in a hashmap
		// but no way to find which one was used to create the current node
		return null;
	}

	/**
	 * Method getNodeName
	 * @see org.w3c.dom.Node#getNodeName()
	 */
	public String getNodeName() {
		// Returning the local name of the node if it is element 
		// or attribute, null otherwise
		// Don't know if it is correct, may need to change
		if(omNode.getType() == ELEMENT_NODE || omNode.getType()
				== ATTRIBUTE_NODE)
			return omNode.getValue();
		return null;
	}

	/**
	 * Method getNodeValue
	 * @see org.w3c.dom.Node#getNodeValue()
	 */
	public String getNodeValue() throws DOMException {
		// Doing a get value on omNode
		// Will be text for TEXT_NODE, and localname for
		// ELEMENT_NODE and ATTRIBUTE_NODE
		return omNode.getValue();
	}

	/**
	 * Method getPrefix
	 * @see org.w3c.dom.Node#getPrefix()
	 */
	public String getPrefix() {
		// See the comments of getNamespaceURI, same applies here
		return null;
	}

	/**
	 * Method setNodeValue
	 * @see org.w3c.dom.Node#setNodeValue(java.lang.String)
	 */
	public void setNodeValue(String arg0) throws DOMException {
		// call omNode's setValue
		omNode.setValue(arg0);
	}

	/**
	 * Method setPrefix
	 * @see org.w3c.dom.Node#setPrefix(java.lang.String)
	 */
	public void setPrefix(String arg0) throws DOMException {
		// No way of setting prefix in omNode or omElement without uri
		// also, prefix and uri can be set only in constructor, 
		// later on we have declareNamespace method which allows namespace 
		//to be created in current element scope, but can't change the namespace
		// for the current element

	}

	/**
	 * Method setOwnerDocument
	 * @param doc
	 */
	public void setOwnerDocument(Document doc){
		// method not part of org.w3c.dom.Node, created to set the document
		this.document = doc;
	}
	
	/**
	 * Method getOwnerDocument
	 * @see org.w3c.dom.Node#getOwnerDocument()
	 */
	public Document getOwnerDocument() {
		// return the set document
		return document;
	}

	/**
	 * Method getAttributes
	 * @see org.w3c.dom.Node#getAttributes()
	 */
	public NamedNodeMap getAttributes() {
		// Will have to provide an implementation of NamedNodeMap
		// Dropping for now
		// TODO
		Iterator iter = ((OMElement)omNode).getAttributes();
		
		return null;
	}

	/**
	 * Method getFirstChild
	 * @see org.w3c.dom.Node#getFirstChild()
	 */
	public org.w3c.dom.Node getFirstChild() {
		//
		OMNode child = ((OMElement)omNode).getFirstChild();
		return new NodeImpl(child);
	}

	/**
	 * Method getLastChild
	 * @see org.w3c.dom.Node#getLastChild()
	 */
	public org.w3c.dom.Node getLastChild() {
		// No corresponding implementation in OMElement or OMNode
		// TODO: Discuss with Venkat what needs to be done here
		return null;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#getNextSibling()
	 */
	public org.w3c.dom.Node getNextSibling() {
		
		return null;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#getParentNode()
	 */
	public org.w3c.dom.Node getParentNode() {
		
		return null;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#getPreviousSibling()
	 */
	public org.w3c.dom.Node getPreviousSibling() {
		
		return null;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#cloneNode(boolean)
	 */
	public org.w3c.dom.Node cloneNode(boolean arg0) {
		
		return null;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#getChildNodes()
	 */
	public NodeList getChildNodes() {
		
		return null;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#isSupported(java.lang.String, java.lang.String)
	 */
	public boolean isSupported(String arg0, String arg1) {
		
		return false;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#appendChild(org.w3c.dom.Node)
	 */
	public org.w3c.dom.Node appendChild(org.w3c.dom.Node arg0)
			throws DOMException {
		
		return null;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#removeChild(org.w3c.dom.Node)
	 */
	public org.w3c.dom.Node removeChild(org.w3c.dom.Node arg0)
			throws DOMException {
		
		return null;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#insertBefore(org.w3c.dom.Node, org.w3c.dom.Node)
	 */
	public org.w3c.dom.Node insertBefore(org.w3c.dom.Node arg0,
			org.w3c.dom.Node arg1) throws DOMException {
		
		return null;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Node#replaceChild(org.w3c.dom.Node, org.w3c.dom.Node)
	 */
	public org.w3c.dom.Node replaceChild(org.w3c.dom.Node arg0,
			org.w3c.dom.Node arg1) throws DOMException {
		
		return null;
	}
	
	public boolean equals(Object o){
		if(o instanceof NodeImpl){
			if(this.omNode.equals(((NodeImpl)o).omNode))
					return true;
		}
		return false;
	}

}
