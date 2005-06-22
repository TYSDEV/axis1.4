/*
 * Created on Jun 19, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package javax.xml.rpc;

import java.util.List;
import javax.xml.rpc.handler.HandlerInfo;
import javax.xml.rpc.security.SecurityConfiguration;

/**
 * @author sunja07
 */
public interface Binding {
	
	/**
	 * Method getHandlerChain
	 * Gets the handler chain for protocol binding instance. The returned List is used to configure the handler chain.
	 * 
	 * @return java.util.List Handler chain
	 */
	List<HandlerInfo> getHandlerChain();
	
	/**
	 * Method setHandlerChain
	 * Sets the handler chain for the protocol binding instance.
	 * 
	 * @param chain - A List of handler configuration entries
	 * @throws JAXRPCException - On an error in the configuration of the handler chain 
	 java.lang.UnsupportedOperationException - If this operation is not supported. This may be done to avoid any overriding of a pre-configured handler chain.
	 */
	void setHandlerChain(java.util.List<HandlerInfo> chain) throws JAXRPCException;
	
	/**
	 * Method getSecurityConfiguration
	 * Gets the SecurityConfiguration for this Binding object.
	 * 
	 * @return The SecurityConfiguration for this Binding object.
	 * @throws java.lang.UnsupportedOperationException - if the Binding class does not support the configuration of SecurityConfiguration.
	 */
	SecurityConfiguration getSecurityConfiguration() throws java.lang.UnsupportedOperationException;
	
}
