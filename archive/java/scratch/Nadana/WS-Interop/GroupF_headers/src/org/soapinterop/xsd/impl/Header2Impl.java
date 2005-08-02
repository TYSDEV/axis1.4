/*
 * XML Type:  Header2
 * Namespace: http://soapinterop.org/xsd
 * Java type: org.soapinterop.xsd.Header2
 *
 * Automatically generated - do not modify.
 */
package org.soapinterop.xsd.impl;
/**
 * An XML Header2(@http://soapinterop.org/xsd).
 *
 * This is a complex type.
 */
public class Header2Impl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.soapinterop.xsd.Header2
{
    
    public Header2Impl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName INT$0 = 
        new javax.xml.namespace.QName("http://soapinterop.org/xsd", "int");
    private static final javax.xml.namespace.QName STRING$2 = 
        new javax.xml.namespace.QName("http://soapinterop.org/xsd", "string");
    
    
    /**
     * Gets the "int" element
     */
    public int getInt()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(INT$0, 0);
            if (target == null)
            {
                return 0;
            }
            return target.getIntValue();
        }
    }
    
    /**
     * Gets (as xml) the "int" element
     */
    public org.apache.xmlbeans.XmlInt xgetInt()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlInt target = null;
            target = (org.apache.xmlbeans.XmlInt)get_store().find_element_user(INT$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "int" element
     */
    public void setInt(int xint)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(INT$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(INT$0);
            }
            target.setIntValue(xint);
        }
    }
    
    /**
     * Sets (as xml) the "int" element
     */
    public void xsetInt(org.apache.xmlbeans.XmlInt xint)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlInt target = null;
            target = (org.apache.xmlbeans.XmlInt)get_store().find_element_user(INT$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlInt)get_store().add_element_user(INT$0);
            }
            target.set(xint);
        }
    }
    
    /**
     * Gets the "string" element
     */
    public java.lang.String getString()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(STRING$2, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "string" element
     */
    public org.apache.xmlbeans.XmlString xgetString()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(STRING$2, 0);
            return target;
        }
    }
    
    /**
     * Sets the "string" element
     */
    public void setString(java.lang.String string)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(STRING$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(STRING$2);
            }
            target.setStringValue(string);
        }
    }
    
    /**
     * Sets (as xml) the "string" element
     */
    public void xsetString(org.apache.xmlbeans.XmlString string)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(STRING$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(STRING$2);
            }
            target.set(string);
        }
    }
}
