/*
 * An XML document type.
 * Localname: echoStringArray
 * Namespace: http://soapinterop.org/xsd
 * Java type: org.soapinterop.xsd.EchoStringArrayDocument
 *
 * Automatically generated - do not modify.
 */
package org.soapinterop.xsd.impl;
/**
 * A document containing one echoStringArray(@http://soapinterop.org/xsd) element.
 *
 * This is a complex type.
 */
public class EchoStringArrayDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.soapinterop.xsd.EchoStringArrayDocument
{
    
    public EchoStringArrayDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ECHOSTRINGARRAY1$0 = 
        new javax.xml.namespace.QName("http://soapinterop.org/xsd", "echoStringArray");
    
    
    /**
     * Gets the "echoStringArray" element
     */
    public org.soapinterop.xsd.EchoStringArrayDocument.EchoStringArray getEchoStringArray1()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.EchoStringArrayDocument.EchoStringArray target = null;
            target = (org.soapinterop.xsd.EchoStringArrayDocument.EchoStringArray)get_store().find_element_user(ECHOSTRINGARRAY1$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "echoStringArray" element
     */
    public void setEchoStringArray1(org.soapinterop.xsd.EchoStringArrayDocument.EchoStringArray echoStringArray1)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.EchoStringArrayDocument.EchoStringArray target = null;
            target = (org.soapinterop.xsd.EchoStringArrayDocument.EchoStringArray)get_store().find_element_user(ECHOSTRINGARRAY1$0, 0);
            if (target == null)
            {
                target = (org.soapinterop.xsd.EchoStringArrayDocument.EchoStringArray)get_store().add_element_user(ECHOSTRINGARRAY1$0);
            }
            target.set(echoStringArray1);
        }
    }
    
    /**
     * Appends and returns a new empty "echoStringArray" element
     */
    public org.soapinterop.xsd.EchoStringArrayDocument.EchoStringArray addNewEchoStringArray1()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.EchoStringArrayDocument.EchoStringArray target = null;
            target = (org.soapinterop.xsd.EchoStringArrayDocument.EchoStringArray)get_store().add_element_user(ECHOSTRINGARRAY1$0);
            return target;
        }
    }
    /**
     * An XML echoStringArray(@http://soapinterop.org/xsd).
     *
     * This is a complex type.
     */
    public static class EchoStringArrayImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.soapinterop.xsd.EchoStringArrayDocument.EchoStringArray
    {
        
        public EchoStringArrayImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        private static final javax.xml.namespace.QName PARAM0$0 = 
            new javax.xml.namespace.QName("", "param0");
        
        
        /**
         * Gets the "param0" element
         */
        public org.soapinterop.xsd.ArrayOfstringLiteral getParam0()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.soapinterop.xsd.ArrayOfstringLiteral target = null;
                target = (org.soapinterop.xsd.ArrayOfstringLiteral)get_store().find_element_user(PARAM0$0, 0);
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
        public void setParam0(org.soapinterop.xsd.ArrayOfstringLiteral param0)
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.soapinterop.xsd.ArrayOfstringLiteral target = null;
                target = (org.soapinterop.xsd.ArrayOfstringLiteral)get_store().find_element_user(PARAM0$0, 0);
                if (target == null)
                {
                    target = (org.soapinterop.xsd.ArrayOfstringLiteral)get_store().add_element_user(PARAM0$0);
                }
                target.set(param0);
            }
        }
        
        /**
         * Appends and returns a new empty "param0" element
         */
        public org.soapinterop.xsd.ArrayOfstringLiteral addNewParam0()
        {
            synchronized (monitor())
            {
                check_orphaned();
                org.soapinterop.xsd.ArrayOfstringLiteral target = null;
                target = (org.soapinterop.xsd.ArrayOfstringLiteral)get_store().add_element_user(PARAM0$0);
                return target;
            }
        }
    }
}
