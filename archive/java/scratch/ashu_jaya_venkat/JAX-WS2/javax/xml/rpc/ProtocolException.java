/*
 * Created on Jun 19, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package javax.xml.rpc;

import java.io.Serializable;

/**
 * Class ProtocolException
 * The javax.xml.rpc.ProtocolException interface is a marker base class for exceptions related to a specific protocol binding. Subclasses are used to communicate protocol level fault information to clients and may be used on the server to control the protocol specific fault representation.
 * 
 * @version 1.0
 * @author sunja07
 */
public class ProtocolException extends RuntimeException implements Serializable {

	/**
	 * Empty Constructor
	 * Constructs a new protocol exception with null as its detail message.
	 */
	public ProtocolException() {}
	
	/**
	 * Constructor
	 * Constructs a new protocol exception with the specified detail message.
	 * @param message - the detail message. The detail message is saved for later retrieval by the Throwable.getMessage() method.
	 */
	public ProtocolException(java.lang.String message) {}
	
	/**
	 * Constructor
	 * Constructs a new runtime exception with the specified detail message and
	 *  cause.
	 * @param message - the detail message (which is saved for later retrieval by the Throwable.getMessage() method).
	 * @param cause - the cause (which is saved for later retrieval by the Throwable.getCause() method). (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
	 */
	public ProtocolException(java.lang.String message,
            java.lang.Throwable cause) {}
	
	/**
	 * Constructor
	 * Constructs a new runtime exception with the specified cause and a detail
	 *  message of (cause==null ? null : cause.toString()) (which typically
	 *  contains the class and detail message of cause).
	 * @param cause - the cause (which is saved for later retrieval by the Throwable.getCause() method). (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
	 */
	public ProtocolException(java.lang.Throwable cause) {}
	
}
