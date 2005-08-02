/*
 * An XML document type.
 * Localname: echoStringArrayParam
 * Namespace: http://soapinterop.org/xsd
 * Java type: org.soapinterop.xsd.EchoStringArrayParamDocument
 *
 * Automatically generated - do not modify.
 */
package org.soapinterop.xsd.impl;
/**
 * A document containing one echoStringArrayParam(@http://soapinterop.org/xsd) element.
 *
 * This is a complex type.
 */
public class EchoStringArrayParamDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.soapinterop.xsd.EchoStringArrayParamDocument
{
    
    public EchoStringArrayParamDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ECHOSTRINGARRAYPARAM$0 = 
        new javax.xml.namespace.QName("http://soapinterop.org/xsd", "echoStringArrayParam");
    
    
    /**
     * Gets the "echoStringArrayParam" element
     */
    public org.soapinterop.xsd.ArrayOfstringLiteral getEchoStringArrayParam()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.ArrayOfstringLiteral target = null;
            target = (org.soapinterop.xsd.ArrayOfstringLiteral)get_store().find_element_user(ECHOSTRINGARRAYPARAM$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "echoStringArrayParam" element
     */
    public void setEchoStringArrayParam(org.soapinterop.xsd.ArrayOfstringLiteral echoStringArrayParam)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.ArrayOfstringLiteral target = null;
            target = (org.soapinterop.xsd.ArrayOfstringLiteral)get_store().find_element_user(ECHOSTRINGARRAYPARAM$0, 0);
            if (target == null)
            {
                target = (org.soapinterop.xsd.ArrayOfstringLiteral)get_store().add_element_user(ECHOSTRINGARRAYPARAM$0);
            }
            target.set(echoStringArrayParam);
        }
    }
    
    /**
     * Appends and returns a new empty "echoStringArrayParam" element
     */
    public org.soapinterop.xsd.ArrayOfstringLiteral addNewEchoStringArrayParam()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.ArrayOfstringLiteral target = null;
            target = (org.soapinterop.xsd.ArrayOfstringLiteral)get_store().add_element_user(ECHOSTRINGARRAYPARAM$0);
            return target;
        }
    }
}
