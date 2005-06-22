/*
 * Created on Jun 21, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package javax.xml.rpc.soap;

import javax.xml.rpc.Binding;

/**
 * Interface SOAPBinding
 * The javax.xml.rpc.SOAPBinding interface is an abstraction for the JAX-RPC SOAP binding.
 * 
 * @version 1.0
 * @author sunja07
 */
public interface SOAPBinding extends Binding {
	
	/**
	 * A constant representing the identity of the SOAP 1.1 over HTTP binding. 
	 */
	static final java.lang.String SOAP11HTTP_BINDING=null;
	
	/**
	 * A constant representing the identity of the SOAP 1.2 over HTTP binding.
	 */
	static final java.lang.String SOAP12HTTP_BINDING=null;
	
	/**
	 * Method getRoles
	 * Gets the roles played by the SOAP binding instance.
	 * 
	 * @return Set the set of roles played by the binding instance.
	 */
	java.util.Set<java.net.URI> getRoles();
	
	/**
	 * Method setRoles
	 * Sets the roles played by the SOAP binding instance.
	 * 
	 * @param roles - The set of roles played by the binding instance.
	 * @throws JAXRPCException - On an error in the configuration of the list of roles.
	 */
	void setRoles(java.util.Set<java.net.URI> roles);

}
