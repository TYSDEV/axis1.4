/*
 * Copyright 2004,2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package javax.xml.rpc.soap;

import javax.xml.rpc.ProtocolException;
import javax.xml.soap.Detail;

/**
 * The SOAPFaultException exception represents a SOAP fault. 
 * The message part in the SOAP fault maps to the contents of faultdetail 
 * element accessible through the getDetail method on the SOAPFaultException. 
 * The method createDetail on the javax.xml.soap.SOAPFactory creates an 
 * instance of the javax.xml.soap.Detail.
 * The faultstring provides a human-readable description of the SOAP fault. 
 * The faultcode element provides an algorithmic mapping of the SOAP fault.
 * Refer to SOAP 1.1 and WSDL 1.1 specifications for more details of the SOAP 
 * faults.
 * 
 * @version 1.0
 * @author sunja07
 */
public class SOAPFaultException extends ProtocolException {
	
	/**
	 * Constructor for the SOAPFaultException 
	 * @param faultcode QName for the SOAP faultcode
	 * @param faultstring faultstring element of SOAP fault
	 * @param faultactor faultactor element of SOAP fault
	 * @param faultdetail faultdetail element of SOAP fault
	 * @see javax.xml.soap.SOAPFactory#createDetail
	 */
	public SOAPFaultException(javax.xml.namespace.QName faultcode,
            java.lang.String faultstring,
            java.lang.String faultactor,
            javax.xml.soap.Detail faultdetail){}
	
	/**
	 * Method getFaultCode
	 * Gets the faultcode element. The faultcode element provides an 
	 * algorithmic mechanism for identifying the fault. SOAP defines a small 
	 * set of SOAP fault codes covering basic SOAP faults.
	 * 
	 * @return QName of the faultcode element
	 */
	public javax.xml.namespace.QName getFaultCode() {
		return null;
	}
	
	/**
	 * Method getFaultString
	 * Gets the faultstring element. The faultstring provides a human-readable
	 * description of the SOAP fault and is not intended for algorithmic 
	 * processing.
	 * 
	 * @return faultstring element of the SOAP fault
	 */
	public java.lang.String getFaultString() {
		return null;
	}
	
	/**
	 * Method getFaultActor
	 * Gets the faultactor element. The faultactor element provides 
	 * information about which SOAP node on the SOAP message path caused the 
	 * fault to happen. It indicates the source of the fault.
	 * 
	 * @return faultactor element of the SOAP fault
	 */
	public java.lang.String getFaultActor() {
		return null;
	}
	
	/**
	 * Method getDetail
	 * Gets the detail element. The detail element is intended for carrying 
	 * application specific error information related to the SOAP Body. 
	 * 
	 * @return detail element of the SOAP fault
	 */
	public Detail getDetail() {
		return null;
	}

}
