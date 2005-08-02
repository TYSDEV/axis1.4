/*
 * An XML document type.
 * Localname: result_Person
 * Namespace: http://soapinterop.org/xsd
 * Java type: org.soapinterop.xsd.ResultPersonDocument
 *
 * Automatically generated - do not modify.
 */
package org.soapinterop.xsd.impl;
/**
 * A document containing one result_Person(@http://soapinterop.org/xsd) element.
 *
 * This is a complex type.
 */
public class ResultPersonDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.soapinterop.xsd.ResultPersonDocument
{
    
    public ResultPersonDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName RESULTPERSON$0 = 
        new javax.xml.namespace.QName("http://soapinterop.org/xsd", "result_Person");
    
    
    /**
     * Gets the "result_Person" element
     */
    public org.soapinterop.xsd.Person getResultPerson()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.Person target = null;
            target = (org.soapinterop.xsd.Person)get_store().find_element_user(RESULTPERSON$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "result_Person" element
     */
    public void setResultPerson(org.soapinterop.xsd.Person resultPerson)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.Person target = null;
            target = (org.soapinterop.xsd.Person)get_store().find_element_user(RESULTPERSON$0, 0);
            if (target == null)
            {
                target = (org.soapinterop.xsd.Person)get_store().add_element_user(RESULTPERSON$0);
            }
            target.set(resultPerson);
        }
    }
    
    /**
     * Appends and returns a new empty "result_Person" element
     */
    public org.soapinterop.xsd.Person addNewResultPerson()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.Person target = null;
            target = (org.soapinterop.xsd.Person)get_store().add_element_user(RESULTPERSON$0);
            return target;
        }
    }
}
