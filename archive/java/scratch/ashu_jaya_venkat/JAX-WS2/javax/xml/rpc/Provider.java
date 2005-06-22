/*
 * Created on Jun 19, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package javax.xml.rpc;

/**
 * Interface Provider<T>
 * Service endpoints may implement the Provider interface as a dynamic alternative to an SEI. Implementations are required to support Provider<Source> and Provider<SOAPMessage>. The ServiceMode annotation can be used to control whether the Provider instance will receive entire protocol messages or just message payloads.
 * 
 * @version 1.0
 * @author sunja07
 */
public interface Provider<T> {
	
	/**
	 * Method invoke
	 * Invokes an operation occording to the contents of the request message.
	 * 
	 * @param request - The request message or message payload.
	 * @param context - The context of the request message. This provides access to the properties of the underlying handler MessageContext that have a scope of APPLICATION. The content of the context may be modified and will be reused as the context for any response message.
	 * @return The response message or message payload. May be null if there is no response.
	 * @throws java.rmi.RemoteException - if there is an error processing request. The cause of the RemoteException may be set to a subclass of ProtocolException to control the protocol level representation of the exception.
	 */
	T invoke(T request, JAXRPCContext context)
	         throws java.rmi.RemoteException;

}
