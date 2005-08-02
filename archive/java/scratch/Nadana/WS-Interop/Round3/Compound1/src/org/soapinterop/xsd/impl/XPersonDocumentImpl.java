/*
 * An XML document type.
 * Localname: x_Person
 * Namespace: http://soapinterop.org/xsd
 * Java type: org.soapinterop.xsd.XPersonDocument
 *
 * Automatically generated - do not modify.
 */
package org.soapinterop.xsd.impl;
/**
 * A document containing one x_Person(@http://soapinterop.org/xsd) element.
 *
 * This is a complex type.
 */
public class XPersonDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.soapinterop.xsd.XPersonDocument
{
    
    public XPersonDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName XPERSON$0 = 
        new javax.xml.namespace.QName("http://soapinterop.org/xsd", "x_Person");
    
    
    /**
     * Gets the "x_Person" element
     */
    public org.soapinterop.xsd.Person getXPerson()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.Person target = null;
            target = (org.soapinterop.xsd.Person)get_store().find_element_user(XPERSON$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "x_Person" element
     */
    public void setXPerson(org.soapinterop.xsd.Person xPerson)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.Person target = null;
            target = (org.soapinterop.xsd.Person)get_store().find_element_user(XPERSON$0, 0);
            if (target == null)
            {
                target = (org.soapinterop.xsd.Person)get_store().add_element_user(XPERSON$0);
            }
            target.set(xPerson);
        }
    }
    
    /**
     * Appends and returns a new empty "x_Person" element
     */
    public org.soapinterop.xsd.Person addNewXPerson()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.Person target = null;
            target = (org.soapinterop.xsd.Person)get_store().add_element_user(XPERSON$0);
            return target;
        }
    }
}
