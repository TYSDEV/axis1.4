/*
 * An XML document type.
 * Localname: echoStringArrayReturn
 * Namespace: http://soapinterop.org/xsd
 * Java type: org.soapinterop.xsd.EchoStringArrayReturnDocument
 *
 * Automatically generated - do not modify.
 */
package org.soapinterop.xsd.impl;
/**
 * A document containing one echoStringArrayReturn(@http://soapinterop.org/xsd) element.
 *
 * This is a complex type.
 */
public class EchoStringArrayReturnDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.soapinterop.xsd.EchoStringArrayReturnDocument
{
    
    public EchoStringArrayReturnDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ECHOSTRINGARRAYRETURN$0 = 
        new javax.xml.namespace.QName("http://soapinterop.org/xsd", "echoStringArrayReturn");
    
    
    /**
     * Gets the "echoStringArrayReturn" element
     */
    public org.soapinterop.xsd.ArrayOfstringLiteral getEchoStringArrayReturn()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.ArrayOfstringLiteral target = null;
            target = (org.soapinterop.xsd.ArrayOfstringLiteral)get_store().find_element_user(ECHOSTRINGARRAYRETURN$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "echoStringArrayReturn" element
     */
    public void setEchoStringArrayReturn(org.soapinterop.xsd.ArrayOfstringLiteral echoStringArrayReturn)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.ArrayOfstringLiteral target = null;
            target = (org.soapinterop.xsd.ArrayOfstringLiteral)get_store().find_element_user(ECHOSTRINGARRAYRETURN$0, 0);
            if (target == null)
            {
                target = (org.soapinterop.xsd.ArrayOfstringLiteral)get_store().add_element_user(ECHOSTRINGARRAYRETURN$0);
            }
            target.set(echoStringArrayReturn);
        }
    }
    
    /**
     * Appends and returns a new empty "echoStringArrayReturn" element
     */
    public org.soapinterop.xsd.ArrayOfstringLiteral addNewEchoStringArrayReturn()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.xsd.ArrayOfstringLiteral target = null;
            target = (org.soapinterop.xsd.ArrayOfstringLiteral)get_store().add_element_user(ECHOSTRINGARRAYRETURN$0);
            return target;
        }
    }
}
