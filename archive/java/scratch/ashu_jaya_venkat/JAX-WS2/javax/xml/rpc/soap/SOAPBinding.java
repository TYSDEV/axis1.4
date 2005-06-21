/*
 * Created on Jun 21, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package javax.xml.rpc.soap;

import javax.xml.rpc.Binding;

/**
 * @author sunja07
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface SOAPBinding extends Binding {
	
	static final java.lang.String SOAP11HTTP_BINDING=null;
	
	static final java.lang.String SOAP12HTTP_BINDING=null;
	
	java.util.Set<java.net.URI> getRoles();
	
	void setRoles(java.util.Set<java.net.URI> roles);

}
