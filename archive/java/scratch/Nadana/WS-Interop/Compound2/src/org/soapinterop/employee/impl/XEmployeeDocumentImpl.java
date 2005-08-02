/*
 * An XML document type.
 * Localname: x_Employee
 * Namespace: http://soapinterop.org/employee
 * Java type: org.soapinterop.employee.XEmployeeDocument
 *
 * Automatically generated - do not modify.
 */
package org.soapinterop.employee.impl;
/**
 * A document containing one x_Employee(@http://soapinterop.org/employee) element.
 *
 * This is a complex type.
 */
public class XEmployeeDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.soapinterop.employee.XEmployeeDocument
{
    
    public XEmployeeDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName XEMPLOYEE$0 = 
        new javax.xml.namespace.QName("http://soapinterop.org/employee", "x_Employee");
    
    
    /**
     * Gets the "x_Employee" element
     */
    public org.soapinterop.employee.Employee getXEmployee()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.employee.Employee target = null;
            target = (org.soapinterop.employee.Employee)get_store().find_element_user(XEMPLOYEE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "x_Employee" element
     */
    public void setXEmployee(org.soapinterop.employee.Employee xEmployee)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.employee.Employee target = null;
            target = (org.soapinterop.employee.Employee)get_store().find_element_user(XEMPLOYEE$0, 0);
            if (target == null)
            {
                target = (org.soapinterop.employee.Employee)get_store().add_element_user(XEMPLOYEE$0);
            }
            target.set(xEmployee);
        }
    }
    
    /**
     * Appends and returns a new empty "x_Employee" element
     */
    public org.soapinterop.employee.Employee addNewXEmployee()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.employee.Employee target = null;
            target = (org.soapinterop.employee.Employee)get_store().add_element_user(XEMPLOYEE$0);
            return target;
        }
    }
}
