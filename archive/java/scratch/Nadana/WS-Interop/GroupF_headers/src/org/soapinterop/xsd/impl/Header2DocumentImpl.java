/*
 * An XML document type.
 * Localname: Header2
 * Namespace: http://soapinterop.org/xsd
 * Java type: org.soapinterop.xsd.Header2Document
 *
 * Automatically generated - do not modify.
 */
package org.soapinterop.xsd.impl;
/**
 * A document containing one Header2(@http://soapinterop.org/xsd) element.
 *
 * This is a complex type.
 */
public class Header2DocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.soapinterop.xsd.Header2Document
{
    
    public Header2DocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName HEADER2$0 = 
        new javax.xml.namespace.QName("http://soapinterop.org/xsd", "Header2");
    
    
    /**
     * Gets the "Header2" element
     */
    public org.soapinterop.xsd.Header2 getHeader2()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.Header2 target = null;
            target = (org.soapinterop.xsd.Header2)get_store().find_element_user(HEADER2$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Header2" element
     */
    public void setHeader2(org.soapinterop.xsd.Header2 header2)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.Header2 target = null;
            target = (org.soapinterop.xsd.Header2)get_store().find_element_user(HEADER2$0, 0);
            if (target == null)
            {
                target = (org.soapinterop.xsd.Header2)get_store().add_element_user(HEADER2$0);
            }
            target.set(header2);
        }
    }
    
    /**
     * Appends and returns a new empty "Header2" element
     */
    public org.soapinterop.xsd.Header2 addNewHeader2()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.Header2 target = null;
            target = (org.soapinterop.xsd.Header2)get_store().add_element_user(HEADER2$0);
            return target;
        }
    }
}
