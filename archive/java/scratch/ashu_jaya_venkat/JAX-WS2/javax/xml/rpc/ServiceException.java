/*
 * Created on Jun 19, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package javax.xml.rpc;

import java.io.Serializable;

/**
 * Class ServiceException
 * The javax.xml.rpc.ServiceException is thrown from the methods in the javax.xml.rpc.Service interface and ServiceFactory class.
 * 
 * @version 1.0
 * @author sunja07
 */
public class ServiceException extends Exception implements Serializable {
	
	/**
	 * Empty Constructor
	 * Constructs a new exception with null as its detail message.
	 */
	public ServiceException() {}
	
	/**
	 * Constructor
	 * Constructs a new exception with the specified detail message.
	 * @param message
	 */
	public ServiceException(java.lang.String message) {}
	
	/**
	 * Constructor
	 * Constructs a new exception with the specified detail message and cause.
	 * @param message
	 * @param cause
	 */
	public ServiceException(java.lang.String message,
            java.lang.Throwable cause) {}
	
	/**
	 * Constructor
	 * Constructs a new exception with the specified cause and a detail message
	 * of (cause==null ? null : cause.toString()) (which typically contains 
	 * the class and detail message of cause).
	 * @param cause
	 */
	public ServiceException(java.lang.Throwable cause) {}
	
	/**
	 * Method getLinkedCause
	 * Gets the Linked cause
	 * @return
	 */
	public java.lang.Throwable getLinkedCause() {
		return null;
	}
}
