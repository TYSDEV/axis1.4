package org.apache.axis.jaxrpc.handler.soap;

import java.util.Iterator;

import javax.xml.bind.JAXBContext;
import javax.xml.namespace.QName;
import javax.xml.rpc.JAXRPCException;
import javax.xml.rpc.handler.soap.SOAPMessageContext;
import javax.xml.soap.SOAPMessage;

import org.apache.axis.jaxrpc.handler.MessageContextImpl;

/*
 * Need to do something more in this class for faults and error messages?
 */
public class SOAPMessageContextImpl extends MessageContextImpl implements SOAPMessageContext {

	private SOAPMessage message;
	private String[] roles;
	
	
	public SOAPMessage getMessage() {
		
		return message;
	}

	public void setMessage(SOAPMessage message) throws JAXRPCException,
			UnsupportedOperationException {
		this.message = message;
	}

	public Object[] getHeaders(QName header, JAXBContext context,
			boolean allRoles) throws JAXRPCException {
		// TODO 
		return null;
	}

	public String[] getRoles() {
		
		return roles;
	}
	
	public void setRoles(String[] roles){
		
		this.roles = roles;
	}

}
