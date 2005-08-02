/*
 * An XML document type.
 * Localname: echoStructParam
 * Namespace: http://soapinterop.org/xsd
 * Java type: org.soapinterop.xsd.EchoStructParamDocument
 *
 * Automatically generated - do not modify.
 */
package org.soapinterop.xsd.impl;
/**
 * A document containing one echoStructParam(@http://soapinterop.org/xsd) element.
 *
 * This is a complex type.
 */
public class EchoStructParamDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.soapinterop.xsd.EchoStructParamDocument
{
    
    public EchoStructParamDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ECHOSTRUCTPARAM$0 = 
        new javax.xml.namespace.QName("http://soapinterop.org/xsd", "echoStructParam");
    
    
    /**
     * Gets the "echoStructParam" element
     */
    public org.soapinterop.xsd.SOAPStruct getEchoStructParam()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.SOAPStruct target = null;
            target = (org.soapinterop.xsd.SOAPStruct)get_store().find_element_user(ECHOSTRUCTPARAM$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "echoStructParam" element
     */
    public void setEchoStructParam(org.soapinterop.xsd.SOAPStruct echoStructParam)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.SOAPStruct target = null;
            target = (org.soapinterop.xsd.SOAPStruct)get_store().find_element_user(ECHOSTRUCTPARAM$0, 0);
            if (target == null)
            {
                target = (org.soapinterop.xsd.SOAPStruct)get_store().add_element_user(ECHOSTRUCTPARAM$0);
            }
            target.set(echoStructParam);
        }
    }
    
    /**
     * Appends and returns a new empty "echoStructParam" element
     */
    public org.soapinterop.xsd.SOAPStruct addNewEchoStructParam()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.SOAPStruct target = null;
            target = (org.soapinterop.xsd.SOAPStruct)get_store().add_element_user(ECHOSTRUCTPARAM$0);
            return target;
        }
    }
}
