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
 * or atleast provide some more setters and getters to hook on to underlying
 * Axis2 MC
 */
public class SOAPMessageContextImpl extends MessageContextImpl implements SOAPMessageContext {

	//private SOAPMessage message;
	
	private String[] roles;
	
	/**
	 * The JAX-RPC MessageContext is prepared from Axis2 MessageContext, we
	 * delegate all the setters and getters methods to Axis2 MC whenever
	 * possible
	 */
	public SOAPMessageContextImpl(org.apache.axis2.context.MessageContext amc){
		super(amc);
	}
	
	public SOAPMessage getMessage() {
		javax.xml.soap.SOAPEnvelope env = new org.apache.axis2.saaj.SOAPEnvelopeImpl(axisMC.getEnvelope());
		return new org.apache.axis2.saaj.SOAPMessageImpl(env);
	}

	public void setMessage(SOAPMessage message) throws JAXRPCException,
			UnsupportedOperationException {
		try{
			axisMC.setEnvelope(((org.apache.axis2.saaj.SOAPEnvelopeImpl)(message.getSOAPPart().getEnvelope())).getOMEnvelope());
		} catch(javax.xml.soap.SOAPException e){
			throw new JAXRPCException(e);
		}
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
