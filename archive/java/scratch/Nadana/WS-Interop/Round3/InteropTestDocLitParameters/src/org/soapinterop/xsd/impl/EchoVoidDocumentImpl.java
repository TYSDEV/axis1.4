/*
 * An XML document type.
 * Localname: echoVoid
 * Namespace: http://soapinterop.org/xsd
 * Java type: org.soapinterop.xsd.EchoVoidDocument
 *
 * Automatically generated - do not modify.
 */
package org.soapinterop.xsd.impl;
/**
 * A document containing one echoVoid(@http://soapinterop.org/xsd) element.
 *
 * This is a complex type.
 */
public class EchoVoidDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.soapinterop.xsd.EchoVoidDocument
{
    
    public EchoVoidDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ECHOVOID$0 = 
        new javax.xml.namespace.QName("http://soapinterop.org/xsd", "echoVoid");
    
    
    /**
     * Gets the "echoVoid" element
     */
    public org.soapinterop.xsd.EchoVoidDocument.EchoVoid getEchoVoid()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.EchoVoidDocument.EchoVoid target = null;
            target = (org.soapinterop.xsd.EchoVoidDocument.EchoVoid)get_store().find_element_user(ECHOVOID$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "echoVoid" element
     */
    public void setEchoVoid(org.soapinterop.xsd.EchoVoidDocument.EchoVoid echoVoid)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.EchoVoidDocument.EchoVoid target = null;
            target = (org.soapinterop.xsd.EchoVoidDocument.EchoVoid)get_store().find_element_user(ECHOVOID$0, 0);
            if (target == null)
            {
                target = (org.soapinterop.xsd.EchoVoidDocument.EchoVoid)get_store().add_element_user(ECHOVOID$0);
            }
            target.set(echoVoid);
        }
    }
    
    /**
     * Appends and returns a new empty "echoVoid" element
     */
    public org.soapinterop.xsd.EchoVoidDocument.EchoVoid addNewEchoVoid()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.EchoVoidDocument.EchoVoid target = null;
            target = (org.soapinterop.xsd.EchoVoidDocument.EchoVoid)get_store().add_element_user(ECHOVOID$0);
            return target;
        }
    }
    /**
     * An XML echoVoid(@http://soapinterop.org/xsd).
     *
     * This is a complex type.
     */
    public static class EchoVoidImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.soapinterop.xsd.EchoVoidDocument.EchoVoid
    {
        
        public EchoVoidImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType);
        }
        
        
    }
}
