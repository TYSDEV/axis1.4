/*
 * An XML document type.
 * Localname: Header1
 * Namespace: http://soapinterop.org/xsd
 * Java type: org.soapinterop.xsd.Header1Document
 *
 * Automatically generated - do not modify.
 */
package org.soapinterop.xsd.impl;
/**
 * A document containing one Header1(@http://soapinterop.org/xsd) element.
 *
 * This is a complex type.
 */
public class Header1DocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.soapinterop.xsd.Header1Document
{
    
    public Header1DocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName HEADER1$0 = 
        new javax.xml.namespace.QName("http://soapinterop.org/xsd", "Header1");
    
    
    /**
     * Gets the "Header1" element
     */
    public org.soapinterop.xsd.Header1 getHeader1()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.Header1 target = null;
            target = (org.soapinterop.xsd.Header1)get_store().find_element_user(HEADER1$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Header1" element
     */
    public void setHeader1(org.soapinterop.xsd.Header1 header1)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.Header1 target = null;
            target = (org.soapinterop.xsd.Header1)get_store().find_element_user(HEADER1$0, 0);
            if (target == null)
            {
                target = (org.soapinterop.xsd.Header1)get_store().add_element_user(HEADER1$0);
            }
            target.set(header1);
        }
    }
    
    /**
     * Appends and returns a new empty "Header1" element
     */
    public org.soapinterop.xsd.Header1 addNewHeader1()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.Header1 target = null;
            target = (org.soapinterop.xsd.Header1)get_store().add_element_user(HEADER1$0);
            return target;
        }
    }
}
