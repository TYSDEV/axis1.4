/*
 * An XML document type.
 * Localname: echoStructReturn
 * Namespace: http://soapinterop.org/xsd
 * Java type: org.soapinterop.xsd.EchoStructReturnDocument
 *
 * Automatically generated - do not modify.
 */
package org.soapinterop.xsd.impl;
/**
 * A document containing one echoStructReturn(@http://soapinterop.org/xsd) element.
 *
 * This is a complex type.
 */
public class EchoStructReturnDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.soapinterop.xsd.EchoStructReturnDocument
{
    
    public EchoStructReturnDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ECHOSTRUCTRETURN$0 = 
        new javax.xml.namespace.QName("http://soapinterop.org/xsd", "echoStructReturn");
    
    
    /**
     * Gets the "echoStructReturn" element
     */
    public org.soapinterop.xsd.SOAPStruct getEchoStructReturn()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.SOAPStruct target = null;
            target = (org.soapinterop.xsd.SOAPStruct)get_store().find_element_user(ECHOSTRUCTRETURN$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "echoStructReturn" element
     */
    public void setEchoStructReturn(org.soapinterop.xsd.SOAPStruct echoStructReturn)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.SOAPStruct target = null;
            target = (org.soapinterop.xsd.SOAPStruct)get_store().find_element_user(ECHOSTRUCTRETURN$0, 0);
            if (target == null)
            {
                target = (org.soapinterop.xsd.SOAPStruct)get_store().add_element_user(ECHOSTRUCTRETURN$0);
            }
            target.set(echoStructReturn);
        }
    }
    
    /**
     * Appends and returns a new empty "echoStructReturn" element
     */
    public org.soapinterop.xsd.SOAPStruct addNewEchoStructReturn()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.SOAPStruct target = null;
            target = (org.soapinterop.xsd.SOAPStruct)get_store().add_element_user(ECHOSTRUCTRETURN$0);
            return target;
        }
    }
}
