/*
 * An XML document type.
 * Localname: echoStringParam
 * Namespace: http://soapinterop.org/xsd
 * Java type: org.soapinterop.xsd.EchoStringParamDocument
 *
 * Automatically generated - do not modify.
 */
package org.soapinterop.xsd.impl;
/**
 * A document containing one echoStringParam(@http://soapinterop.org/xsd) element.
 *
 * This is a complex type.
 */
public class EchoStringParamDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.soapinterop.xsd.EchoStringParamDocument
{
    
    public EchoStringParamDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ECHOSTRINGPARAM$0 = 
        new javax.xml.namespace.QName("http://soapinterop.org/xsd", "echoStringParam");
    
    
    /**
     * Gets the "echoStringParam" element
     */
    public java.lang.String getEchoStringParam()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ECHOSTRINGPARAM$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "echoStringParam" element
     */
    public org.apache.xmlbeans.XmlString xgetEchoStringParam()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(ECHOSTRINGPARAM$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "echoStringParam" element
     */
    public void setEchoStringParam(java.lang.String echoStringParam)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ECHOSTRINGPARAM$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(ECHOSTRINGPARAM$0);
            }
            target.setStringValue(echoStringParam);
        }
    }
    
    /**
     * Sets (as xml) the "echoStringParam" element
     */
    public void xsetEchoStringParam(org.apache.xmlbeans.XmlString echoStringParam)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(ECHOSTRINGPARAM$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(ECHOSTRINGPARAM$0);
            }
            target.set(echoStringParam);
        }
    }
}
