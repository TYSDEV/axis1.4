/*
 * Created on Jun 21, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package javax.xml.rpc.soap;

import javax.xml.rpc.ProtocolException;
import javax.xml.soap.Detail;

/**
 * @author sunja07
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SOAPFaultException extends ProtocolException {
	
	public SOAPFaultException(javax.xml.namespace.QName faultcode,
            java.lang.String faultstring,
            java.lang.String faultactor,
            javax.xml.soap.Detail faultdetail){}
	
	public javax.xml.namespace.QName getFaultCode() {
		return null;
	}
	
	public java.lang.String getFaultString() {
		return null;
	}
	
	public java.lang.String getFaultActor() {
		return null;
	}
	
	public Detail getDetail() {
		return null;
	}

}
