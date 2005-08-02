/*
 * An XML document type.
 * Localname: echoStringReturn
 * Namespace: http://soapinterop.org/xsd
 * Java type: org.soapinterop.xsd.EchoStringReturnDocument
 *
 * Automatically generated - do not modify.
 */
package org.soapinterop.xsd.impl;
/**
 * A document containing one echoStringReturn(@http://soapinterop.org/xsd) element.
 *
 * This is a complex type.
 */
public class EchoStringReturnDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.soapinterop.xsd.EchoStringReturnDocument
{
    
    public EchoStringReturnDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ECHOSTRINGRETURN$0 = 
        new javax.xml.namespace.QName("http://soapinterop.org/xsd", "echoStringReturn");
    
    
    /**
     * Gets the "echoStringReturn" element
     */
    public java.lang.String getEchoStringReturn()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ECHOSTRINGRETURN$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "echoStringReturn" element
     */
    public org.apache.xmlbeans.XmlString xgetEchoStringReturn()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(ECHOSTRINGRETURN$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "echoStringReturn" element
     */
    public void setEchoStringReturn(java.lang.String echoStringReturn)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ECHOSTRINGRETURN$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(ECHOSTRINGRETURN$0);
            }
            target.setStringValue(echoStringReturn);
        }
    }
    
    /**
     * Sets (as xml) the "echoStringReturn" element
     */
    public void xsetEchoStringReturn(org.apache.xmlbeans.XmlString echoStringReturn)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(ECHOSTRINGRETURN$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(ECHOSTRINGRETURN$0);
            }
            target.set(echoStringReturn);
        }
    }
}
