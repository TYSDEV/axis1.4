/*
 * Created on Mar 16, 2005
 *
 */
package org.apache.axis.saaj;

import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;

import org.apache.axis.om.OMFactory;
import org.apache.axis.saaj.SOAPBodyImpl;
import org.apache.axis.saaj.SOAPHeaderImpl;


/**
 * Class SOAPEnvelopeImpl
 * 
 * @author Jayachandra
 * jayachandra@gmail.com
 */
public class SOAPEnvelopeImpl extends SOAPElementImpl implements SOAPEnvelope {

	/**
	 * Field soSOAPEnvelope
	 * A data member of OM's SOAPEnvelopeImpl which would be used for delegation of any work to underlying OM.
	 */
	private org.apache.axis.om.SOAPEnvelope omSOAPEnvelope;
	
	/**
	 * Constructor SOAPEnvelopeImpl
	 */
	public SOAPEnvelopeImpl(){
		OMFactory fac = OMFactory.newInstance();
		omSOAPEnvelope = fac.getDefaultEnvelope();
	}
	
	/**
	 * method getOMEnvelope
	 * @return
	 */
	public org.apache.axis.om.SOAPEnvelope getOMEnvelope(){
		return omSOAPEnvelope;
	}
	

	/**
	 * method createName
	 * @param localName
	 * @param prefix
	 * @param uri
	 * @return
	 * @throws SOAPException
	 * @see javax.xml.soap.SOAPEnvelope#createName(java.lang.String, java.lang.String, java.lang.String)
	 */
	public Name createName(String localName, String prefix, String uri)
			throws SOAPException {
		try {
			return new PrefixedQName(uri,localName, prefix);
		}catch (Exception e)
		{
			throw new SOAPException(e);
		}
	}

	/**
	 * method createName
	 * 
	 * @param localName
	 * @return
	 * @throws SOAPException
	 * @see javax.xml.soap.SOAPEnvelope#createName(java.lang.String)
	 */
	public Name createName(String localName) throws SOAPException {
		try {
			return new PrefixedQName(null, localName, null);
		}catch (Exception e)
		{
			throw new SOAPException(e);
		}
	}

	/**
	 * method getHeader
	 * 
	 * @return
	 * @throws SOAPException
	 * @see javax.xml.soap.SOAPEnvelope#getHeader()
	 */
	public SOAPHeader getHeader() throws SOAPException {

		org.apache.axis.om.SOAPHeader omSOAPHeader;
		try
		{
			omSOAPHeader = (org.apache.axis.om.impl.llom.SOAPHeaderImpl) omSOAPEnvelope.getHeader();
		}catch (Exception e)
		{
			throw new SOAPException(e);
		}
		return (SOAPHeader) new SOAPHeaderImpl(omSOAPHeader);
	}

	/**
	 * method getBody
	 * 
	 * @return
	 * @throws SOAPException
	 * @see javax.xml.soap.SOAPEnvelope#getBody()
	 */
	public SOAPBody getBody() throws SOAPException {

		org.apache.axis.om.SOAPBody omSOAPBody;
		try
		{
			omSOAPBody = omSOAPEnvelope.getBody();
		} catch (Exception e)
		{
			throw new SOAPException(e);
		}
		return (SOAPBody)(new SOAPBodyImpl(omSOAPBody));
	}

	/**
	 * method addHeader
	 * 
	 * @return
	 * @throws SOAPException
	 * @see javax.xml.soap.SOAPEnvelope#addHeader()
	 */
	public SOAPHeader addHeader() throws SOAPException {
		/*
		 * Our objective is to set the omSOAPHeader of the omSOAPEnvelope if not already present
		 */
		try {
			org.apache.axis.om.SOAPHeader header = omSOAPEnvelope.getHeader();
			if (header == null) {
				header = new org.apache.axis.om.impl.llom.SOAPHeaderImpl(omSOAPEnvelope);
				omSOAPEnvelope.addChild(header);
				return (SOAPHeader)(new SOAPHeaderImpl(header));
			} else {
				throw new SOAPException("Header already present, can't set body again without deleting the existing header");
			}
		}catch (Exception e)
		{
			throw new SOAPException(e);
		}
	}

	/**
	 * method addBody
	 * 
	 * @return
	 * @throws SOAPException
	 * @see javax.xml.soap.SOAPEnvelope#addBody()
	 */
	public SOAPBody addBody() throws SOAPException {
		/*
		 * Our objective is to set the omSOAPBody of the omSOAPEnvelope if not already present
		 */
		try {
			org.apache.axis.om.SOAPBody body = omSOAPEnvelope.getBody();
			if (body == null) {
				body = new org.apache.axis.om.impl.llom.SOAPBodyImpl(omSOAPEnvelope);
				omSOAPEnvelope.addChild(body);
				return (SOAPBody)(new SOAPBodyImpl(body));
			} else {
				throw new SOAPException("Body already present, can't set body again without deleting the existing body");
			}
		}catch (Exception e)
		{
			throw new SOAPException(e);
		}
	}

}
