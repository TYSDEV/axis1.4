/*
 * An XML document type.
 * Localname: echoStringResponse
 * Namespace: http://soapinterop.org/xsd
 * Java type: org.soapinterop.xsd.EchoStringResponseDocument
 *
 * Automatically generated - do not modify.
 */
package org.soapinterop.xsd.impl;
/**
 * A document containing one echoStringResponse(@http://soapinterop.org/xsd) element.
 *
 * This is a complex type.
 */
public class EchoStringResponseDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.soapinterop.xsd.EchoStringResponseDocument
{
    
    public EchoStringResponseDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ECHOSTRINGRESPONSE$0 = 
        new javax.xml.namespace.QName("http://soapinterop.org/xsd", "echoStringResponse");
    
    
    /**
     * Gets the "echoStringResponse" element
     */
    public org.soapinterop.xsd.EchoStringResponseDocument.EchoStringResponse getEchoStringResponse()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.EchoStringResponseDocument.EchoStringResponse target = null;
            target = (org.soapinterop.xsd.EchoStringResponseDocument.EchoStringResponse)get_store().find_element_user(ECHOSTRINGRESPONSE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "echoStringResponse" element
     */
    public void setEchoStringResponse(org.soapinterop.xsd.EchoStringResponseDocument.EchoStringResponse echoStringResponse)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.EchoStringResponseDocument.EchoStringResponse target = null;
            target = (org.soapinterop.xsd.EchoStringResponseDocument.EchoStringResponse)get_store().find_element_user(ECHOSTRINGRESPONSE$0, 0);
            if (target == null)
            {
                target = (org.soapinterop.xsd.EchoStringResponseDocument.EchoStringResponse)get_store().add_element_user(ECHOSTRINGRESPONSE$0);
            }
            target.set(echoStringResponse);
        }
    }
    
    /**
     * Appends and returns a new empty "echoStringResponse" element
     */
    public org.soapinterop.xsd.EchoStringResponseDocument.EchoStringResponse addNewEchoStringResponse()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.EchoStringResponseDocument.EchoStringResponse target = null;
            target = (org.soapinterop.xsd.EchoStringResponseDocument.EchoStringResponse)get_store().add_element_user(ECHOSTRINGRESPONSE$0);
            return target;
        }
    }
    /**
     * An XML echoStringResponse(@http://soapinterop.org/xsd).
     *
     * This is a complex type.
     */
    public static class EchoStringResponseImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.soapinterop.xsd.EchoStringResponseDocument.EchoStringResponse
    {
        
        public EchoStringResponseImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName RETURN$0 = 
            new javax.xml.namespace.QName("", "return");
        
        
        /**
         * Gets the "return" element
         */
        public java.lang.String getReturn()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(RETURN$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "return" element
         */
        public org.apache.xmlbeans.XmlString xgetReturn()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(RETURN$0, 0);
                return target;
            }
        }
        
        /**
         * Sets the "return" element
         */
        public void setReturn(java.lang.String xreturn)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(RETURN$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(RETURN$0);
                }
                target.setStringValue(xreturn);
            }
        }
        
        /**
         * Sets (as xml) the "return" element
         */
        public void xsetReturn(org.apache.xmlbeans.XmlString xreturn)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(RETURN$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(RETURN$0);
                }
                target.set(xreturn);
            }
        }
    }
}
