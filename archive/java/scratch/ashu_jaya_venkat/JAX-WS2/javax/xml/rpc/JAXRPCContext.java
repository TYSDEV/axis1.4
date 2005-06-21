/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package javax.xml.rpc;

/**
 * @author sunja07
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface JAXRPCContext {
	/**
	 * Standard property: Map of attachments to a message, key is the MIME Content-ID, value is a DataHandler.
	 * Type: java.util.Map
	 */
	static final java.lang.String MESSAGE_ATTACHMENTS = null;
	
	/**
	 * Standard property: input source for WSDL document.
	 * Type: org.xml.sax.InputSource
	 */
	static final java.lang.String WSDL_DESCRIPTION = null;
	
	/**
	 * Standard property: name of WSDL service.
	 * Type: javax.xml.namespace.QName
	 */
	static final java.lang.String WSDL_SERVICE = null;
	
	/**
	 * Standard property: name of WSDL port.
	 * Type: javax.xml.namespace.QName
	 */
	static final java.lang.String WSDL_PORT = null;
	
	/**
	 * Standard property: name of wsdl interface (2.0) or port type (1.1).
	 * Type: javax.xml.namespace.QName
	 */
	static final java.lang.String WSDL_INTERFACE = null;
	
	/**
	 * Standard property: name of WSDL operation.
	 * Type: javax.xml.namespace.QName
	 */
	static final java.lang.String WSDL_OPERATION = null;
	
	/**
	 * Method setProperty
	 * Sets the name and value of a property associated with the context. If the context contains a value of the same property, the old value is replaced.
	 * @param name Name of the property associated with the context
	 * @param value Value of the property 
	 * @throws java.lang.IllegalArgumentException If some aspect of the property is prevents it from being stored in the context
	 */
	void setProperty(java.lang.String name,
            java.lang.Object value) throws java.lang.IllegalArgumentException;
	
	/**
	 * Method removeProperty
	 * Removes a property (name-value pair) from the context 
	 * @param name Name of the property to be removed
	 * @throws java.lang.IllegalArgumentException if an illegal property name is specified
	 */
	void removeProperty(java.lang.String name) throws java.lang.IllegalArgumentException;
	
	/**
	 * Method getProperty
	 * Gets the value of a specific property from the MessageContext
	 * @param name Name of the property whose value is to be retrieved
	 * @return Value of the property 
	 * @throws java.lang.IllegalArgumentException if an illegal property name is specified
	 */
	java.lang.Object getProperty(java.lang.String name) throws java.lang.IllegalArgumentException;
	
	/**
	 * Method getPropertyNames
	 * Returns an Iterator view of the names of the properties in this context 
	 * @return Iterator for the property names
	 */
	java.util.Iterator getPropertyNames();
}
