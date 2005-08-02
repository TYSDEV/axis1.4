/*
 * An XML document type.
 * Localname: echoStruct
 * Namespace: http://soapinterop.org/xsd
 * Java type: org.soapinterop.xsd.EchoStructDocument
 *
 * Automatically generated - do not modify.
 */
package org.soapinterop.xsd.impl;
/**
 * A document containing one echoStruct(@http://soapinterop.org/xsd) element.
 *
 * This is a complex type.
 */
public class EchoStructDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.soapinterop.xsd.EchoStructDocument
{
    
    public EchoStructDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ECHOSTRUCT$0 = 
        new javax.xml.namespace.QName("http://soapinterop.org/xsd", "echoStruct");
    
    
    /**
     * Gets the "echoStruct" element
     */
    public org.soapinterop.xsd.EchoStructDocument.EchoStruct getEchoStruct()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.EchoStructDocument.EchoStruct target = null;
            target = (org.soapinterop.xsd.EchoStructDocument.EchoStruct)get_store().find_element_user(ECHOSTRUCT$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "echoStruct" element
     */
    public void setEchoStruct(org.soapinterop.xsd.EchoStructDocument.EchoStruct echoStruct)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.EchoStructDocument.EchoStruct target = null;
            target = (org.soapinterop.xsd.EchoStructDocument.EchoStruct)get_store().find_element_user(ECHOSTRUCT$0, 0);
            if (target == null)
            {
                target = (org.soapinterop.xsd.EchoStructDocument.EchoStruct)get_store().add_element_user(ECHOSTRUCT$0);
            }
            target.set(echoStruct);
        }
    }
    
    /**
     * Appends and returns a new empty "echoStruct" element
     */
    public org.soapinterop.xsd.EchoStructDocument.EchoStruct addNewEchoStruct()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.EchoStructDocument.EchoStruct target = null;
            target = (org.soapinterop.xsd.EchoStructDocument.EchoStruct)get_store().add_element_user(ECHOSTRUCT$0);
            return target;
        }
    }
    /**
     * An XML echoStruct(@http://soapinterop.org/xsd).
     *
     * This is a complex type.
     */
    public static class EchoStructImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.soapinterop.xsd.EchoStructDocument.EchoStruct
    {
        
        public EchoStructImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName PARAM0$0 = 
            new javax.xml.namespace.QName("", "param0");
        
        
        /**
         * Gets the "param0" element
         */
        public org.soapinterop.xsd.SOAPStruct getParam0()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.soapinterop.xsd.SOAPStruct target = null;
                target = (org.soapinterop.xsd.SOAPStruct)get_store().find_element_user(PARAM0$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target;
            }
        }
        
        /**
         * Sets the "param0" element
         */
        public void setParam0(org.soapinterop.xsd.SOAPStruct param0)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.soapinterop.xsd.SOAPStruct target = null;
                target = (org.soapinterop.xsd.SOAPStruct)get_store().find_element_user(PARAM0$0, 0);
                if (target == null)
                {
                    target = (org.soapinterop.xsd.SOAPStruct)get_store().add_element_user(PARAM0$0);
                }
                target.set(param0);
            }
        }
        
        /**
         * Appends and returns a new empty "param0" element
         */
        public org.soapinterop.xsd.SOAPStruct addNewParam0()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.soapinterop.xsd.SOAPStruct target = null;
                target = (org.soapinterop.xsd.SOAPStruct)get_store().add_element_user(PARAM0$0);
                return target;
            }
        }
    }
}
