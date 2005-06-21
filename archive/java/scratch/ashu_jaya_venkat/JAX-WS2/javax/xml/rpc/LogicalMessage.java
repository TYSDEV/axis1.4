/*
 * Created on Jun 19, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package javax.xml.rpc;

/**
 * @author sunja07
 * 
 * Interface LogicalMessage
 * The LogicalMessage interface represents a protocol agnostic XML message 
 * and contains methods that provide access to the payload of the message.
 */
public interface LogicalMessage {
	
	/**
	 * Method getPayload
	 * Gets the message payload as an XML source, may be called multiple times on the same LogicalMessage instance, always returns a new Source that may be used to retrieve the entire message payload.
	 * If the returned Source is an instance of DOMSource, then modifications to the encapsulated DOM tree change the message payload in-place, there is no need to susequently call setPayload. Other types of Source provide only read access to the message payload.
	 * @return The contained message payload; returns null if no payload is present in this message.
	 */
	javax.xml.transform.Source getPayload();

	/**
	 * Method getPayload
	 * Gets the message payload as a JAXB object. Note that there is no connection between the returned object and the message payload, changes to the payload require calling setPayload.
	 * @param context The JAXBContext that should be used to unmarshall the message payload
	 * @return The contained message payload; returns null if no payload is present in this message
	 * @throws JAXRPCException If an error occurs when using a supplied JAXBContext to unmarshall the payload. The cause of the JAXRPCException is the original JAXBException.
	 */
	java.lang.Object getPayload(javax.xml.bind.JAXBContext context) throws JAXRPCException;
	
	/**
	 * Method setPayload
	 * Sets the message payload
	 * @param payload message payload 
	 * @throws JAXRPCException If any error during the setting of the payload in this message java.lang.UnsupportedOperationException - If this operation is not supported.
	 */
	void setPayload(javax.xml.transform.Source payload) throws JAXRPCException;	
	
	/**
	 * Method setPayload
	 * Sets the message payload 
	 * @param payload message payload
	 * @param context The JAXBContext that should be used to marshall the payload 
	 * @throws java.lang.UnsupportedOperationException If this operation is not supported
	 * @throws JAXRPCException If an error occurs when using the supplied JAXBContext to marshall the payload. The cause of the JAXRPCException is the original JAXBException.
	 */
	void setPayload(java.lang.Object payload,
            javax.xml.bind.JAXBContext context) throws JAXRPCException;
}
