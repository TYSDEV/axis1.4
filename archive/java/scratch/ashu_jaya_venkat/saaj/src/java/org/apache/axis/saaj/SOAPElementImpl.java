/*
 * Created on Mar 17, 2005
 *
 */
package org.apache.axis.saaj;

import java.util.Iterator;

import javax.xml.soap.Name;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.NodeList;

import javax.xml.namespace.QName;
import java.util.ArrayList;
/**
 * Class SOAPElementImpl
 * 
 * @author Jayachandra
 * jayachandra@gmail.com
 */
public class SOAPElementImpl extends NodeImpl implements SOAPElement {
	/**
	 * Field omElement
	 * The corresponding OM object for SOAPElement is OMElement, so we would 
	 * have a datamember of type OMElement in this class
	 */
	protected org.apache.axis.om.OMElement omElement;
	
	/**
	 * Constructor SOAPElementImpl
	 * The standard constructor of being able to create soapelement given a omElement
	 * @param omElement
	 */
	public SOAPElementImpl(org.apache.axis.om.OMElement omElement)
	{
		this.omElement = omElement;
	}
	
	/**
	 * Constructor SOAPElementImpl
	 * The empty constructor
	 */
	public SOAPElementImpl() {
	}
	
	/**
	 * Method getOMElement
	 * getter method on the data member omElement
	 * @return
	 */
	public org.apache.axis.om.OMElement getOMElement() {
		return this.omElement;
	}

	/**
	 * Method addChildElement
	 * @param name
	 * @return
	 * @throws SOAPException
	 * @see javax.xml.soap.SOAPElement#addChildElement(javax.xml.soap.Name)
	 */
	public SOAPElement addChildElement(Name name) throws SOAPException {
		//We will create a new OMElement and add that as a child to the OMElement datamember that 
		//we are carrying along. And return back a wrapped SOAPElement corresponding to the 
		//created OMElement
		
		//Since a <code>Name</code> object is given as parameter we should try to create an OMElement
		//and register it with the contents of the <code>name</code> element 
		org.apache.axis.om.OMElement newOMElement = new org.apache.axis.om.impl.llom.OMElementImpl(new QName(name.getURI(), name.getLocalName(), name.getPrefix()), omElement);
		omElement.addChild(newOMElement); 
		return (SOAPElement)(new SOAPElementImpl(newOMElement));
	}

	/**
	 * Method addChildElement
	 * @param localName
	 * @return
	 * @throws SOAPException
	 * @see javax.xml.soap.SOAPElement#addChildElement(java.lang.String)
	 */
	public SOAPElement addChildElement(String localName) throws SOAPException {
		//We will create a new OMElement and add that as a child to the OMElement datamember that 
		//we are carrying along. And return back a wrapped SOAPElement corresponding to the 
		//created OMElement
		org.apache.axis.om.OMElement newOMElement = new org.apache.axis.om.impl.llom.OMElementImpl(new QName(localName), omElement);
		omElement.addChild(newOMElement); 
		return (SOAPElement)(new SOAPElementImpl(newOMElement));
	}

	/**
	 * Method addChildElement
	 * @param localName
	 * @param prefix
	 * @return
	 * @throws SOAPException
	 * @see javax.xml.soap.SOAPElement#addChildElement(java.lang.String, java.lang.String)
	 */
	public SOAPElement addChildElement(String localName, String prefix)
			throws SOAPException {		
		org.apache.axis.om.OMElement newOMElement = new org.apache.axis.om.impl.llom.OMElementImpl(new QName(null,localName,prefix), omElement);		
		omElement.addChild(newOMElement); 
		return (SOAPElement)(new SOAPElementImpl(newOMElement));
	}

	/**
	 * Method addChileElement
	 * @param localName
	 * @param prefix
	 * @return
	 * @throws SOAPException
	 * @see javax.xml.soap.SOAPElement#addChildElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	public SOAPElement addChildElement(String localName, String prefix,
			String uri) throws SOAPException {
		org.apache.axis.om.OMElement newOMElement = new org.apache.axis.om.impl.llom.OMElementImpl(new QName(uri,localName,prefix), omElement);
		omElement.addChild(newOMElement); 
		return (SOAPElement)(new SOAPElementImpl(newOMElement));
	}

	/**
	 * Method addChileElement
	 * @param element
	 * @return
	 * @throws SOAPException
	 * @see javax.xml.soap.SOAPElement#addChildElement(javax.xml.soap.SOAPElement)
	 */
	public SOAPElement addChildElement(SOAPElement element)
			throws SOAPException {
		//TODO:
		//The fragment rooted in element is either added as a whole or not at all, if there was an error.
		//The fragment rooted in element cannot contain elements named “Envelope”, “Header” or “Body”
		//and in the SOAP namespace. Any namespace prefixes present in the fragment should be fully
		//resolved using appropriate namespace declarations within the fragment itself.
		
		org.apache.axis.om.OMElement omElementToAdd = ((SOAPElementImpl)element).getOMElement();
		omElementToAdd.setParent(omElement);
		omElement.addChild(omElementToAdd);
		return (SOAPElement)(new SOAPElementImpl(omElementToAdd));		
	}

	/**
	 * Method addTextNode
	 * @param text
	 * @return
	 * @throws SOAPException
	 * @see javax.xml.soap.SOAPElement#addTextNode(java.lang.String)
	 */
	public SOAPElement addTextNode(String text) throws SOAPException {
		//This doesn't seem to have support directly in OM
		//But work should always be possible.
		
		//My objective would be to create an OMText node and add that to 
		//the omElement delegate member that we have with us.		
		omElement.setValue(text);
		return this;
	}

	/**
	 * Method addAttribute
	 * @param name
	 * @param value
	 * @return
	 * @throws SOAPException
	 * @see javax.xml.soap.SOAPElement#addAttribute(javax.xml.soap.Name, java.lang.String)
	 */
	public SOAPElement addAttribute(Name name, String value)
			throws SOAPException {
		//insertAttribute() make use of it.
		return null;
	}

	/**
	 * Method addNamespaceDeclaration
	 * @param prefix
	 * @param uri
	 * @return
	 * @throws SOAPException
	 * @see javax.xml.soap.SOAPElement#addNamespaceDeclaration(java.lang.String, java.lang.String)
	 */
	public SOAPElement addNamespaceDeclaration(String prefix, String uri)
			throws SOAPException {
		return null;
	}

	/**
	 * Method getAttributeValue
	 * @param name
	 * @return
	 * @see javax.xml.soap.SOAPElement#getAttributeValue(javax.xml.soap.Name)
	 */
	public String getAttributeValue(Name name) {
		return omElement.getAttributeWithQName( new QName(name.getURI(),name.getLocalName())).getValue();
	}

	/**
	 * method getAllAttributes
	 * @return
	 * @see javax.xml.soap.SOAPElement#getAllAttributes()
	 */
	public Iterator getAllAttributes() {
		Iterator attrIter = omElement.getAttributes();
		ArrayList arrayList = new ArrayList();
		while (attrIter.hasNext()) {
			Object o = attrIter.next();
			if (o instanceof org.apache.axis.om.OMAttribute) {
				//we need to create a SOAPNode for this and add to the arrayList
				javax.xml.soap.Node soapNode = new NodeImpl((org.apache.axis.om.OMAttribute)o);
				arrayList.add(soapNode);
			}
		}
		return arrayList.iterator();
	}

	/**
	 * Method getNamespaceURI
	 * @param prefix
	 * @return
	 * @see javax.xml.soap.SOAPElement#getNamespaceURI(java.lang.String)
	 */
	public String getNamespaceURI(String prefix) {
		return null;
		//Taking too much of time, let me move on...
	}

	/**
	 * method getNamespacePrefixes
	 * @return
	 * @see javax.xml.soap.SOAPElement#getNamespacePrefixes()
	 */
	public Iterator getNamespacePrefixes() {
		return null;
		//deferring this too.
	}

	/**
	 * Method getElementName
	 * @return
	 * @see javax.xml.soap.SOAPElement#getElementName()
	 */
	public Name getElementName() {
		//TODO: Ashu! Look into PrefixeQName implementation, and undesired extra <code>prefix</code> field
		//is present inside it, which might create some problems up the execution flow.
		return (Name)(new PrefixedQName(((org.apache.axis.om.impl.llom.OMElementImpl)omElement).getQName()));
	}

	/**
	 * method removeAttribute
	 * @param name
	 * @return
	 * @see javax.xml.soap.SOAPElement#removeAttribute(javax.xml.soap.Name)
	 */
	public boolean removeAttribute(Name name) {
		return false;
	}

	/**
	 * method removeNamespaceDeclaration
	 * @param prefix
	 * @return
	 * @see javax.xml.soap.SOAPElement#removeNamespaceDeclaration(java.lang.String)
	 */
	public boolean removeNamespaceDeclaration(String prefix) {
		
		//Couldn't figure out corresponding functionality in OM
		return false;
	}

	/**
	 * method getChildElements
	 * @return
	 * @see javax.xml.soap.SOAPElement#getChildElements()
	 */
	public Iterator getChildElements() {
		//Actually all the children are being treated as OMNodes and are being
		//wrapped accordingly to a single type and being returned
		//Ideally speaking, node type should be observed and wrapped into 
		//corresponding type.
		Iterator childIter = omElement.getChildren();
		ArrayList arrayList = new ArrayList();
		while(childIter.hasNext()) {
			Object o = childIter.next();
			if (o instanceof org.apache.axis.om.OMNode) {
				SOAPElement childElement = new SOAPElementImpl((org.apache.axis.om.OMElement)o);
				arrayList.add(childElement);
			}
		}
		return arrayList.iterator();
	}

	/**
	 * method getChildElements
	 * @param name
	 * @return
	 * @see javax.xml.soap.SOAPElement#getChildElements(javax.xml.soap.Name)
	 */
	public Iterator getChildElements(Name name) {
		QName qName = new QName(name.getURI(),name.getLocalName());
		Iterator childIter = omElement.getChildrenWithName(qName);
		ArrayList arrayList = new ArrayList();
		while(childIter.hasNext()) {
			Object o = childIter.next();
			if (o instanceof org.apache.axis.om.OMNode) {
				SOAPElement childElement = new SOAPElementImpl((org.apache.axis.om.OMElement)o);
				arrayList.add(childElement);
			}
		}
		return arrayList.iterator();
	}

	/**
	 * method setEncodingStyle
	 * @param encodingStyle
	 * @see javax.xml.soap.SOAPElement#setEncodingStyle(java.lang.String)
	 */
	public void setEncodingStyle(String encodingStyle) throws SOAPException {
		
		//Donno how to tackle this right now.
		//Couldn't figure out corresponding functionality in OM

	}

	/**
	 * method getEncodingStyle
	 * @return
	 * @see javax.xml.soap.SOAPElement#getEncodingStyle()
	 */
	public String getEncodingStyle() {
		return null;
	}

	/*&
	 * method removeContents
	 * @see javax.xml.soap.SOAPElement#removeContents()
	 */
	public void removeContents() {
		
		//Couldn't figure out corresponding functionality in OM
		//Actually there is a detach() method in OMElementImpl, but
		//that discards the current information item also. Here we only have to
		//remove contents and return back a void. Will have to consult
		//Venkat in making a work around for this.

	}

	/**
	 * method getVisibleNamespacePrefixes
	 * @return
	 * @see javax.xml.soap.SOAPElement#getVisibleNamespacePrefixes()
	 */
	public Iterator getVisibleNamespacePrefixes() {
		// See I'm returnign the iterator of namespaces returned by
		// getAllDeclaredNamespaces() of the OMElementImpl as of now.
		// But I doubt if that is going to cover the namespaces that are
		// in the scope of the document at the higher levels of hierarchy
		
		Iterator namespacesIter = omElement.getAllDeclaredNamespaces();
		ArrayList returnList = new ArrayList();
		while(namespacesIter.hasNext()) {
			Object o = namespacesIter.next();
			if (o instanceof org.apache.axis.om.OMNamespace) {
				javax.xml.soap.Node soapNode = new NodeImpl((org.apache.axis.om.OMNamespace)o);
				returnList.add(soapNode);
			}			
		}
		return returnList.iterator();
	}

	/**
	 * method getTagName
	 * @return
	 * @see org.w3c.dom.Element#getTagName()
	 */
	public String getTagName() {
		return null;
	}

	/**
	 * method removeAttribute
	 * @param arg0
	 * @see org.w3c.dom.Element#removeAttribute(java.lang.String)
	 */
	public void removeAttribute(String arg0) throws DOMException {


	}

	/**
	 * method hasAttribute
	 * @param arg0
	 * @return
	 * @see org.w3c.dom.Element#hasAttribute(java.lang.String)
	 */
	public boolean hasAttribute(String arg0) {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Element#getAttribute(java.lang.String)
	 */
	public String getAttribute(String arg0) {

		return null;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Element#removeAttributeNS(java.lang.String, java.lang.String)
	 */
	public void removeAttributeNS(String arg0, String arg1) throws DOMException {

//		Couldn't figure out corresponding functionality in OM
		//but looking into OMAttribute code might help you with some work around.

	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Element#setAttribute(java.lang.String, java.lang.String)
	 */
	public void setAttribute(String arg0, String arg1) throws DOMException {


	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Element#hasAttributeNS(java.lang.String, java.lang.String)
	 */
	public boolean hasAttributeNS(String arg0, String arg1) {
	
		return false;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Element#getAttributeNode(java.lang.String)
	 */
	public Attr getAttributeNode(String arg0) {
	
		return null;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Element#removeAttributeNode(org.w3c.dom.Attr)
	 */
	public Attr removeAttributeNode(Attr arg0) throws DOMException {
	
		return null;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Element#setAttributeNode(org.w3c.dom.Attr)
	 */
	public Attr setAttributeNode(Attr arg0) throws DOMException {
	
		return null;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Element#setAttributeNodeNS(org.w3c.dom.Attr)
	 */
	public Attr setAttributeNodeNS(Attr arg0) throws DOMException {
	
		return null;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Element#getElementsByTagName(java.lang.String)
	 */
	public NodeList getElementsByTagName(String arg0) {
	
		return null;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Element#getAttributeNS(java.lang.String, java.lang.String)
	 */
	public String getAttributeNS(String arg0, String arg1) {
	
		return null;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Element#setAttributeNS(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void setAttributeNS(String arg0, String arg1, String arg2)
			throws DOMException {


	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Element#getAttributeNodeNS(java.lang.String, java.lang.String)
	 */
	public Attr getAttributeNodeNS(String arg0, String arg1) {

		return null;
	}

	/* (non-Javadoc)
	 * @see org.w3c.dom.Element#getElementsByTagNameNS(java.lang.String, java.lang.String)
	 */
	public NodeList getElementsByTagNameNS(String arg0, String arg1) {
	
		return null;
	} 

}
