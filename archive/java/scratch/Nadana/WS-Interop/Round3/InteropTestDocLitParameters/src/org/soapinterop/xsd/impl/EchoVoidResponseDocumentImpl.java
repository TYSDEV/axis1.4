/*
 * An XML document type.
 * Localname: echoVoidResponse
 * Namespace: http://soapinterop.org/xsd
 * Java type: org.soapinterop.xsd.EchoVoidResponseDocument
 *
 * Automatically generated - do not modify.
 */
package org.soapinterop.xsd.impl;
/**
 * A document containing one echoVoidResponse(@http://soapinterop.org/xsd) element.
 *
 * This is a complex type.
 */
public class EchoVoidResponseDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.soapinterop.xsd.EchoVoidResponseDocument
{
    
    public EchoVoidResponseDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ECHOVOIDRESPONSE$0 = 
        new javax.xml.namespace.QName("http://soapinterop.org/xsd", "echoVoidResponse");
    
    
    /**
     * Gets the "echoVoidResponse" element
     */
    public org.soapinterop.xsd.EchoVoidResponseDocument.EchoVoidResponse getEchoVoidResponse()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.EchoVoidResponseDocument.EchoVoidResponse target = null;
            target = (org.soapinterop.xsd.EchoVoidResponseDocument.EchoVoidResponse)get_store().find_element_user(ECHOVOIDRESPONSE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "echoVoidResponse" element
     */
    public void setEchoVoidResponse(org.soapinterop.xsd.EchoVoidResponseDocument.EchoVoidResponse echoVoidResponse)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.EchoVoidResponseDocument.EchoVoidResponse target = null;
            target = (org.soapinterop.xsd.EchoVoidResponseDocument.EchoVoidResponse)get_store().find_element_user(ECHOVOIDRESPONSE$0, 0);
            if (target == null)
            {
                target = (org.soapinterop.xsd.EchoVoidResponseDocument.EchoVoidResponse)get_store().add_element_user(ECHOVOIDRESPONSE$0);
            }
            target.set(echoVoidResponse);
        }
    }
    
    /**
     * Appends and returns a new empty "echoVoidResponse" element
     */
    public org.soapinterop.xsd.EchoVoidResponseDocument.EchoVoidResponse addNewEchoVoidResponse()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.EchoVoidResponseDocument.EchoVoidResponse target = null;
            target = (org.soapinterop.xsd.EchoVoidResponseDocument.EchoVoidResponse)get_store().add_element_user(ECHOVOIDRESPONSE$0);
            return target;
        }
    }
    /**
     * An XML echoVoidResponse(@http://soapinterop.org/xsd).
     *
     * This is a complex type.
     */
    public static class EchoVoidResponseImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.soapinterop.xsd.EchoVoidResponseDocument.EchoVoidResponse
    {
        
        public EchoVoidResponseImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        
    }
}
