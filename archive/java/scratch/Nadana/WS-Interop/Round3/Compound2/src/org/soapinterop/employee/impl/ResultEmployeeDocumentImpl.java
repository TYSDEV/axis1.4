/*
 * An XML document type.
 * Localname: result_Employee
 * Namespace: http://soapinterop.org/employee
 * Java type: org.soapinterop.employee.ResultEmployeeDocument
 *
 * Automatically generated - do not modify.
 */
package org.soapinterop.employee.impl;
/**
 * A document containing one result_Employee(@http://soapinterop.org/employee) element.
 *
 * This is a complex type.
 */
public class ResultEmployeeDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.soapinterop.employee.ResultEmployeeDocument
{
    
    public ResultEmployeeDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName RESULTEMPLOYEE$0 = 
        new javax.xml.namespace.QName("http://soapinterop.org/employee", "result_Employee");
    
    
    /**
     * Gets the "result_Employee" element
     */
    public org.soapinterop.employee.Employee getResultEmployee()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.employee.Employee target = null;
            target = (org.soapinterop.employee.Employee)get_store().find_element_user(RESULTEMPLOYEE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "result_Employee" element
     */
    public void setResultEmployee(org.soapinterop.employee.Employee resultEmployee)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.employee.Employee target = null;
            target = (org.soapinterop.employee.Employee)get_store().find_element_user(RESULTEMPLOYEE$0, 0);
            if (target == null)
            {
                target = (org.soapinterop.employee.Employee)get_store().add_element_user(RESULTEMPLOYEE$0);
            }
            target.set(resultEmployee);
        }
    }
    
    /**
     * Appends and returns a new empty "result_Employee" element
     */
    public org.soapinterop.employee.Employee addNewResultEmployee()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.employee.Employee target = null;
            target = (org.soapinterop.employee.Employee)get_store().add_element_user(RESULTEMPLOYEE$0);
            return target;
        }
    }
}
