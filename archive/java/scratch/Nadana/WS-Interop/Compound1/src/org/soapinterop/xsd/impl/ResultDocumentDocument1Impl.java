/*
 * An XML document type.
 * Localname: result_Document
 * Namespace: http://soapinterop.org/xsd
 * Java type: org.soapinterop.xsd.ResultDocumentDocument1
 *
 * Automatically generated - do not modify.
 */
package org.soapinterop.xsd.impl;
/**
 * A document containing one result_Document(@http://soapinterop.org/xsd) element.
 *
 * This is a complex type.
 */
public class ResultDocumentDocument1Impl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.soapinterop.xsd.ResultDocumentDocument1
{
    
    public ResultDocumentDocument1Impl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName RESULTDOCUMENT$0 = 
        new javax.xml.namespace.QName("http://soapinterop.org/xsd", "result_Document");
    
    
    /**
     * Gets the "result_Document" element
     */
    public org.soapinterop.xsd.Document getResultDocument()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.Document target = null;
            target = (org.soapinterop.xsd.Document)get_store().find_element_user(RESULTDOCUMENT$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "result_Document" element
     */
    public void setResultDocument(org.soapinterop.xsd.Document resultDocument)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.Document target = null;
            target = (org.soapinterop.xsd.Document)get_store().find_element_user(RESULTDOCUMENT$0, 0);
            if (target == null)
            {
                target = (org.soapinterop.xsd.Document)get_store().add_element_user(RESULTDOCUMENT$0);
            }
            target.set(resultDocument);
        }
    }
    
    /**
     * Appends and returns a new empty "result_Document" element
     */
    public org.soapinterop.xsd.Document addNewResultDocument()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.Document target = null;
            target = (org.soapinterop.xsd.Document)get_store().add_element_user(RESULTDOCUMENT$0);
            return target;
        }
    }
}
