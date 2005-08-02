/*
 * An XML document type.
 * Localname: echoString
 * Namespace: http://soapinterop.org/xsd
 * Java type: org.soapinterop.xsd.EchoStringDocument
 *
 * Automatically generated - do not modify.
 */
package org.soapinterop.xsd.impl;
/**
 * A document containing one echoString(@http://soapinterop.org/xsd) element.
 *
 * This is a complex type.
 */
public class EchoStringDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.soapinterop.xsd.EchoStringDocument
{
    
    public EchoStringDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ECHOSTRING$0 = 
        new javax.xml.namespace.QName("http://soapinterop.org/xsd", "echoString");
    
    
    /**
     * Gets the "echoString" element
     */
    public org.soapinterop.xsd.EchoStringDocument.EchoString getEchoString()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.EchoStringDocument.EchoString target = null;
            target = (org.soapinterop.xsd.EchoStringDocument.EchoString)get_store().find_element_user(ECHOSTRING$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "echoString" element
     */
    public void setEchoString(org.soapinterop.xsd.EchoStringDocument.EchoString echoString)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.EchoStringDocument.EchoString target = null;
            target = (org.soapinterop.xsd.EchoStringDocument.EchoString)get_store().find_element_user(ECHOSTRING$0, 0);
            if (target == null)
            {
                target = (org.soapinterop.xsd.EchoStringDocument.EchoString)get_store().add_element_user(ECHOSTRING$0);
            }
            target.set(echoString);
        }
    }
    
    /**
     * Appends and returns a new empty "echoString" element
     */
    public org.soapinterop.xsd.EchoStringDocument.EchoString addNewEchoString()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.EchoStringDocument.EchoString target = null;
            target = (org.soapinterop.xsd.EchoStringDocument.EchoString)get_store().add_element_user(ECHOSTRING$0);
            return target;
        }
    }
    /**
     * An XML echoString(@http://soapinterop.org/xsd).
     *
     * This is a complex type.
     */
    public static class EchoStringImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.soapinterop.xsd.EchoStringDocument.EchoString
    {
        
        public EchoStringImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName PARAM0$0 = 
            new javax.xml.namespace.QName("", "param0");
        
        
        /**
         * Gets the "param0" element
         */
        public java.lang.String getParam0()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PARAM0$0, 0);
                if (target == null)
                {
                    return null;
                }
                return target.getStringValue();
            }
        }
        
        /**
         * Gets (as xml) the "param0" element
         */
        public org.apache.xmlbeans.XmlString xgetParam0()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PARAM0$0, 0);
                return target;
            }
        }
        
        /**
         * Sets the "param0" element
         */
        public void setParam0(java.lang.String param0)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.SimpleValue target = null;
                target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PARAM0$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(PARAM0$0);
                }
                target.setStringValue(param0);
            }
        }
        
        /**
         * Sets (as xml) the "param0" element
         */
        public void xsetParam0(org.apache.xmlbeans.XmlString param0)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.apache.xmlbeans.XmlString target = null;
                target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(PARAM0$0, 0);
                if (target == null)
                {
                    target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(PARAM0$0);
                }
                target.set(param0);
            }
        }
    }
}
