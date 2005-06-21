/*
 * Created on Jun 21, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package javax.xml.rpc.handler;

/**
 * public interface LogicalMessageContext
 * extends <code>MessageContext</code>
 * <p>
 * The LogicalMessageContext interface extends MessageContext to provide access to a the contained message as a
 * protocol neutral LogicalMessage
 * @version 1.0
 * @author shaas02
 *
 */
public interface LogicalMessageContext extends MessageContext {
	
	/**
	 * Gets the message from this message context
	 * @return The contained message; returns null if no message is present in this message context
	 */
	LogicalMessage getMessage();
	
}
