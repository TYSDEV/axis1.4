/*   Copyright 2004 The Apache Software Foundation
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *  limitations under the License.
 * <p/>
 * User: Eran Chinthaka - Lanka Software Foundation
 * Date: Oct 28, 2004
 * Time: 11:52:18 AM
 * <p/>
 *
 */


package org.apache.axis.om;


public interface SOAPEnvelope extends OMElement {

    /**
     * Creates a new <CODE>Name</CODE> object initialized with the given local
     * name, namespace prefix, and namespace URI.
     * <p/>
     * <P>This factory method creates <CODE>Name</CODE> objects for use in the
     * SOAP/XML envelope.
     *
     * @param localName a <CODE>String</CODE> giving the local name
     * @param prefix    a <CODE>String</CODE> giving the prefix of the
     *                  namespace
     * @param uri       a <CODE>String</CODE> giving the URI of the namespace
     * @return a <CODE>OMNamespace</CODE> object initialized with the given
     *         local name, namespace prefix, and namespace URI
     * @throws OMException if there is a SOAP error
     */
    public abstract OMNamespace createNamespace(String localName, String prefix, String uri)
            throws OMException;


    /**
     * Returns the <CODE>SOAPHeader</CODE> object for this <CODE>
     * SOAPEnvelope</CODE> object.
     * <p/>
     * <P> This SOAPHeader will just be a container for all the headers in the
     * <CODE>OMMessage</CODE> </P>
     *
     * @return the <CODE>SOAPHeader</CODE> object or <CODE> null</CODE> if there
     *         is none
     * @throws OMException if there is a problem obtaining the <CODE>SOAPHeader</CODE>
     *                     object
     */
    public abstract SOAPHeader getHeader() throws OMException;

    /**
     * Returns the <CODE>SOAPBody</CODE> object associated with this
     * <CODE>SOAPEnvelope</CODE> object.
     * <p/>
     * <P> This SOAPBody will just be a container for all the BodyElements in
     * the <CODE>OMMessage</CODE> </P>
     *
     * @return the <CODE>SOAPBody</CODE> object for this <CODE>
     *         SOAPEnvelope</CODE> object or <CODE>null</CODE> if there is none
     * @throws OMException if there is a problem obtaining the <CODE>SOAPBody</CODE>
     *                     object
     */
    public abstract SOAPBody getBody() throws OMException;


}
