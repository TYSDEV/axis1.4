package javax.xml.rpc.handler.soap;

import javax.xml.rpc.handler.AbstractHandler;

/**
 * public interface SOAPHandler<C extends SOAPMessageContext>
 * extends AbstractHandler<C>
 * <p>
 * The javax.xml.rpc.handler.SOAPHandler extends AbstractHandler to provide 
 * typesafety for the message context parameter and add a method to obtain 
 * access to the headers that may be processed by the handler.
 * 
 * @version 1.0
 * @author shaas02
 *
 * @param <C>
 */
public interface SOAPHandler<C extends SOAPMessageContext>
				extends AbstractHandler<C> {
	
	/**
	 * Gets the header blocks that can be processed by this Handler instance.
	 * @return Set of QNames of header blocks processed by this handler 
	 * instance. QName is the qualified name of the outermost element of the 
	 * Header block.
	 */
	public java.util.Set<javax.xml.namespace.QName> getHeaders();
}
