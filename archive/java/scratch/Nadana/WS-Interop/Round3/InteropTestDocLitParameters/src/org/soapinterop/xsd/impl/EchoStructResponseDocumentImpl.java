/*
 * An XML document type.
 * Localname: echoStructResponse
 * Namespace: http://soapinterop.org/xsd
 * Java type: org.soapinterop.xsd.EchoStructResponseDocument
 *
 * Automatically generated - do not modify.
 */
package org.soapinterop.xsd.impl;
/**
 * A document containing one echoStructResponse(@http://soapinterop.org/xsd) element.
 *
 * This is a complex type.
 */
public class EchoStructResponseDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.soapinterop.xsd.EchoStructResponseDocument
{
    
    public EchoStructResponseDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ECHOSTRUCTRESPONSE$0 = 
        new javax.xml.namespace.QName("http://soapinterop.org/xsd", "echoStructResponse");
    
    
    /**
     * Gets the "echoStructResponse" element
     */
    public org.soapinterop.xsd.EchoStructResponseDocument.EchoStructResponse getEchoStructResponse()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.EchoStructResponseDocument.EchoStructResponse target = null;
            target = (org.soapinterop.xsd.EchoStructResponseDocument.EchoStructResponse)get_store().find_element_user(ECHOSTRUCTRESPONSE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "echoStructResponse" element
     */
    public void setEchoStructResponse(org.soapinterop.xsd.EchoStructResponseDocument.EchoStructResponse echoStructResponse)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.EchoStructResponseDocument.EchoStructResponse target = null;
            target = (org.soapinterop.xsd.EchoStructResponseDocument.EchoStructResponse)get_store().find_element_user(ECHOSTRUCTRESPONSE$0, 0);
            if (target == null)
            {
                target = (org.soapinterop.xsd.EchoStructResponseDocument.EchoStructResponse)get_store().add_element_user(ECHOSTRUCTRESPONSE$0);
            }
            target.set(echoStructResponse);
        }
    }
    
    /**
     * Appends and returns a new empty "echoStructResponse" element
     */
    public org.soapinterop.xsd.EchoStructResponseDocument.EchoStructResponse addNewEchoStructResponse()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.EchoStructResponseDocument.EchoStructResponse target = null;
            target = (org.soapinterop.xsd.EchoStructResponseDocument.EchoStructResponse)get_store().add_element_user(ECHOSTRUCTRESPONSE$0);
            return target;
        }
    }
    /**
     * An XML echoStructResponse(@http://soapinterop.org/xsd).
     *
     * This is a complex type.
     */
    public static class EchoStructResponseImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.soapinterop.xsd.EchoStructResponseDocument.EchoStructResponse
    {
        
        public EchoStructResponseImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName RETURN$0 = 
            new javax.xml.namespace.QName("", "return");
        
        
        /**
         * Gets the "return" element
         */
        public org.soapinterop.xsd.SOAPStruct getReturn()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.soapinterop.xsd.SOAPStruct target = null;
                target = (org.soapinterop.xsd.SOAPStruct)get_store().find_element_user(RETURN$0, 0);
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
        public void setReturn(org.soapinterop.xsd.SOAPStruct xreturn)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.soapinterop.xsd.SOAPStruct target = null;
                target = (org.soapinterop.xsd.SOAPStruct)get_store().find_element_user(RETURN$0, 0);
                if (target == null)
                {
                    target = (org.soapinterop.xsd.SOAPStruct)get_store().add_element_user(RETURN$0);
                }
                target.set(xreturn);
            }
        }
        
        /**
         * Appends and returns a new empty "return" element
         */
        public org.soapinterop.xsd.SOAPStruct addNewReturn()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.soapinterop.xsd.SOAPStruct target = null;
                target = (org.soapinterop.xsd.SOAPStruct)get_store().add_element_user(RETURN$0);
                return target;
            }
        }
    }
}
