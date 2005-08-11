/**
 * 
 */
package org.apache.axis2.jaxws.soap;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;
import java.util.HashSet;

import javax.xml.rpc.JAXRPCException;
import javax.xml.rpc.soap.SOAPBinding;

import org.apache.axis2.jaxws.client.BindingImpl;
/**
 * @author sunja07
 *
 */
public class SOAPBindingImpl extends BindingImpl implements SOAPBinding {

	Set roles = new HashSet<URI>(2);
	
	private String binding = this.SOAP11HTTP_BINDING; //default soap1.1
	
	/**
	 * Empty Costructor
	 */
	public SOAPBindingImpl(String thisBinding) throws JAXRPCException{
		super();
		setBinding(thisBinding);
		try {
			if(thisBinding.equals(SOAP11HTTP_BINDING)) {
				//setting the 'next' role
				roles.add(new URI("http://schemas.xmlsoap.org/soap/actor/next"));
				
				//setting the 'ultimate receiver' role
				//There isn't any identified URI for this role in soap1.1
				//Its just identified by the absence of 'actor' attribute from
				//soap header.
			} else if(thisBinding.equals(SOAP12HTTP_BINDING)) {
				//setting the 'next' role
				roles.add(new URI("http://www.w3.org/2003/05/soap-envelope/role/next"));
				
				//setting the 'ultimate receiver' role
				roles.add(new URI("http://www.w3.org/2003/05/soap-envelope/role/ultimateReceiver"));
			} else
				throw new JAXRPCException("Unsupported Binding URI!");
			
		} catch (Exception e) {
			throw new JAXRPCException(e);
		}
	}

	/**
     * Method getRoles
     * Gets the roles played by the SOAP binding instance.
     *
     * @return Set the set of roles played by the binding instance.
     */
	public Set<URI> getRoles() {
		// TODO Auto-generated method stub
		return roles;
	}

	/**
     * Method setRoles
     * Sets the roles played by the SOAP binding instance.
     *
     * @param roles - The set of roles played by the binding instance.
     * @throws JAXRPCException - On an error in the configuration of the 
     * list of roles.
     */
	public void setRoles(Set<URI> inputRoles) throws JAXRPCException {
		//iterate to see if any of the listed roles in 'none' role.
		//if so, JAXRPC doesn't allow it.
		try {
			boolean isNone = inputRoles.contains(
					new URI("http://www.w3.org/2003/05/soap-envelope/role/none"));
			if(isNone) {
				throw new JAXRPCException("none role not allowed!");
			}
			roles.add(inputRoles);
		} catch (URISyntaxException e) {
			throw new JAXRPCException(e);
		}
	}
	
	public String getBinding() {
		return binding;
	}
	
	public void setBinding(String bindingURI) {
		binding = bindingURI;
	}

}
