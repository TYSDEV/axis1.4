/*
 * Created on Jun 19, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package javax.xml.rpc;

/**
 * class JAXRPCException
 * The javax.xml.rpc.JAXRPCException is thrown from the core JAX-RPC APIs to indicate an exception related to the JAX-RPC runtime mechanisms.
 * 
 * @version 1.1
 * @author sunja07
 */
public class JAXRPCException extends RuntimeException implements java.io.Serializable {

	/**
	 * Empty Constructor
	 * Constructs a new exception with null as its detail message.
	 */
	public JAXRPCException() {}
	
	/**
	 * Constructor
	 * Constructs a new exception with the specified detail message.
	 * @param message
	 */
	public JAXRPCException(java.lang.String message) {}
	
	/**
	 * Constructor
	 * Constructs a new exception with the specified detail message and cause.
	 * @param message
	 * @param cause
	 */
	public JAXRPCException(java.lang.String message,
            java.lang.Throwable cause) {}
	
	/**
	 * Constructor
	 * Constructs a new JAXRPCException with the specified cause and a detail 
	 * message of (cause==null ? null : cause.toString()) (which typically 
	 * contains the class and detail message of cause).
	 * @param cause
	 */
	public JAXRPCException(java.lang.Throwable cause){}
	
	/**
	 * Method getLinkedCause
	 * Gets the Linked cause
	 * @deprecated Retained for backwards compatility, new applications should
	 * use the standard cause mechanism.
	 * @return
	 */
	public java.lang.Throwable getLinkedCause() {
		return null;
	}
}
