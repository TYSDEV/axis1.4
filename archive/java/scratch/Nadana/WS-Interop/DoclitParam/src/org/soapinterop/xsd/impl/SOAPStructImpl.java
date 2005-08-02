/*
 * XML Type:  SOAPStruct
 * Namespace: http://soapinterop.org/xsd
 * Java type: org.soapinterop.xsd.SOAPStruct
 *
 * Automatically generated - do not modify.
 */
package org.soapinterop.xsd.impl;
/**
 * An XML SOAPStruct(@http://soapinterop.org/xsd).
 *
 * This is a complex type.
 */
public class SOAPStructImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.soapinterop.xsd.SOAPStruct
{
    
    public SOAPStructImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName VARFLOAT$0 = 
        new javax.xml.namespace.QName("", "varFloat");
    private static final javax.xml.namespace.QName VARINT$2 = 
        new javax.xml.namespace.QName("", "varInt");
    private static final javax.xml.namespace.QName VARSTRING$4 = 
        new javax.xml.namespace.QName("", "varString");
    
    
    /**
     * Gets the "varFloat" element
     */
    public float getVarFloat()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(VARFLOAT$0, 0);
            if (target == null)
            {
                return 0.0f;
            }
            return target.getFloatValue();
        }
    }
    
    /**
     * Gets (as xml) the "varFloat" element
     */
    public org.apache.xmlbeans.XmlFloat xgetVarFloat()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlFloat target = null;
            target = (org.apache.xmlbeans.XmlFloat)get_store().find_element_user(VARFLOAT$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "varFloat" element
     */
    public void setVarFloat(float varFloat)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(VARFLOAT$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(VARFLOAT$0);
            }
            target.setFloatValue(varFloat);
        }
    }
    
    /**
     * Sets (as xml) the "varFloat" element
     */
    public void xsetVarFloat(org.apache.xmlbeans.XmlFloat varFloat)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlFloat target = null;
            target = (org.apache.xmlbeans.XmlFloat)get_store().find_element_user(VARFLOAT$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlFloat)get_store().add_element_user(VARFLOAT$0);
            }
            target.set(varFloat);
        }
    }
    
    /**
     * Gets the "varInt" element
     */
    public int getVarInt()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(VARINT$2, 0);
            if (target == null)
            {
                return 0;
            }
            return target.getIntValue();
        }
    }
    
    /**
     * Gets (as xml) the "varInt" element
     */
    public org.apache.xmlbeans.XmlInt xgetVarInt()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlInt target = null;
            target = (org.apache.xmlbeans.XmlInt)get_store().find_element_user(VARINT$2, 0);
            return target;
        }
    }
    
    /**
     * Sets the "varInt" element
     */
    public void setVarInt(int varInt)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(VARINT$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(VARINT$2);
            }
            target.setIntValue(varInt);
        }
    }
    
    /**
     * Sets (as xml) the "varInt" element
     */
    public void xsetVarInt(org.apache.xmlbeans.XmlInt varInt)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlInt target = null;
            target = (org.apache.xmlbeans.XmlInt)get_store().find_element_user(VARINT$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlInt)get_store().add_element_user(VARINT$2);
            }
            target.set(varInt);
        }
    }
    
    /**
     * Gets the "varString" element
     */
    public java.lang.String getVarString()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(VARSTRING$4, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "varString" element
     */
    public org.apache.xmlbeans.XmlString xgetVarString()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(VARSTRING$4, 0);
            return target;
        }
    }
    
    /**
     * Sets the "varString" element
     */
    public void setVarString(java.lang.String varString)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(VARSTRING$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(VARSTRING$4);
            }
            target.setStringValue(varString);
        }
    }
    
    /**
     * Sets (as xml) the "varString" element
     */
    public void xsetVarString(org.apache.xmlbeans.XmlString varString)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(VARSTRING$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(VARSTRING$4);
            }
            target.set(varString);
        }
    }
}
