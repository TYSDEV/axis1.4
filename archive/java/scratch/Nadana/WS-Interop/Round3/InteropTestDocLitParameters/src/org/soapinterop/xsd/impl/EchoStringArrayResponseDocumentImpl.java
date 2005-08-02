/*
 * An XML document type.
 * Localname: echoStringArrayResponse
 * Namespace: http://soapinterop.org/xsd
 * Java type: org.soapinterop.xsd.EchoStringArrayResponseDocument
 *
 * Automatically generated - do not modify.
 */
package org.soapinterop.xsd.impl;
/**
 * A document containing one echoStringArrayResponse(@http://soapinterop.org/xsd) element.
 *
 * This is a complex type.
 */
public class EchoStringArrayResponseDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.soapinterop.xsd.EchoStringArrayResponseDocument
{
    
    public EchoStringArrayResponseDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ECHOSTRINGARRAYRESPONSE$0 = 
        new javax.xml.namespace.QName("http://soapinterop.org/xsd", "echoStringArrayResponse");
    
    
    /**
     * Gets the "echoStringArrayResponse" element
     */
    public org.soapinterop.xsd.EchoStringArrayResponseDocument.EchoStringArrayResponse getEchoStringArrayResponse()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.EchoStringArrayResponseDocument.EchoStringArrayResponse target = null;
            target = (org.soapinterop.xsd.EchoStringArrayResponseDocument.EchoStringArrayResponse)get_store().find_element_user(ECHOSTRINGARRAYRESPONSE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "echoStringArrayResponse" element
     */
    public void setEchoStringArrayResponse(org.soapinterop.xsd.EchoStringArrayResponseDocument.EchoStringArrayResponse echoStringArrayResponse)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.EchoStringArrayResponseDocument.EchoStringArrayResponse target = null;
            target = (org.soapinterop.xsd.EchoStringArrayResponseDocument.EchoStringArrayResponse)get_store().find_element_user(ECHOSTRINGARRAYRESPONSE$0, 0);
            if (target == null)
            {
                target = (org.soapinterop.xsd.EchoStringArrayResponseDocument.EchoStringArrayResponse)get_store().add_element_user(ECHOSTRINGARRAYRESPONSE$0);
            }
            target.set(echoStringArrayResponse);
        }
    }
    
    /**
     * Appends and returns a new empty "echoStringArrayResponse" element
     */
    public org.soapinterop.xsd.EchoStringArrayResponseDocument.EchoStringArrayResponse addNewEchoStringArrayResponse()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.EchoStringArrayResponseDocument.EchoStringArrayResponse target = null;
            target = (org.soapinterop.xsd.EchoStringArrayResponseDocument.EchoStringArrayResponse)get_store().add_element_user(ECHOSTRINGARRAYRESPONSE$0);
            return target;
        }
    }
    /**
     * An XML echoStringArrayResponse(@http://soapinterop.org/xsd).
     *
     * This is a complex type.
     */
    public static class EchoStringArrayResponseImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.soapinterop.xsd.EchoStringArrayResponseDocument.EchoStringArrayResponse
    {
        
        public EchoStringArrayResponseImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName RETURN$0 = 
            new javax.xml.namespace.QName("", "return");
        
        
        /**
         * Gets the "return" element
         */
        public org.soapinterop.xsd.ArrayOfstringLiteral getReturn()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.soapinterop.xsd.ArrayOfstringLiteral target = null;
                target = (org.soapinterop.xsd.ArrayOfstringLiteral)get_store().find_element_user(RETURN$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * Sets the "return" element
         */
        public void setReturn(org.soapinterop.xsd.ArrayOfstringLiteral xreturn)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.soapinterop.xsd.ArrayOfstringLiteral target = null;
                target = (org.soapinterop.xsd.ArrayOfstringLiteral)get_store().find_element_user(RETURN$0, 0);
                if (target == null)
                {
                    target = (org.soapinterop.xsd.ArrayOfstringLiteral)get_store().add_element_user(RETURN$0);
                }
                target.set(xreturn);
            }
        }
        
        /**
         * Appends and returns a new empty "return" element
         */
        public org.soapinterop.xsd.ArrayOfstringLiteral addNewReturn()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.soapinterop.xsd.ArrayOfstringLiteral target = null;
                target = (org.soapinterop.xsd.ArrayOfstringLiteral)get_store().add_element_user(RETURN$0);
                return target;
            }
        }
    }
}
