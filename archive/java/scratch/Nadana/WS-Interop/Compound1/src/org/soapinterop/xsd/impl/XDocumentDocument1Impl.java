/*
 * An XML document type.
 * Localname: x_Document
 * Namespace: http://soapinterop.org/xsd
 * Java type: org.soapinterop.xsd.XDocumentDocument1
 *
 * Automatically generated - do not modify.
 */
package org.soapinterop.xsd.impl;
/**
 * A document containing one x_Document(@http://soapinterop.org/xsd) element.
 *
 * This is a complex type.
 */
public class XDocumentDocument1Impl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.soapinterop.xsd.XDocumentDocument1
{
    
    public XDocumentDocument1Impl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName XDOCUMENT$0 = 
        new javax.xml.namespace.QName("http://soapinterop.org/xsd", "x_Document");
    
    
    /**
     * Gets the "x_Document" element
     */
    public org.soapinterop.xsd.Document getXDocument()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.Document target = null;
            target = (org.soapinterop.xsd.Document)get_store().find_element_user(XDOCUMENT$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "x_Document" element
     */
    public void setXDocument(org.soapinterop.xsd.Document xDocument)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.Document target = null;
            target = (org.soapinterop.xsd.Document)get_store().find_element_user(XDOCUMENT$0, 0);
            if (target == null)
            {
                target = (org.soapinterop.xsd.Document)get_store().add_element_user(XDOCUMENT$0);
            }
            target.set(xDocument);
        }
    }
    
    /**
     * Appends and returns a new empty "x_Document" element
     */
    public org.soapinterop.xsd.Document addNewXDocument()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.Document target = null;
            target = (org.soapinterop.xsd.Document)get_store().add_element_user(XDOCUMENT$0);
            return target;
        }
    }
}
