/*
 * XML Type:  Employee
 * Namespace: http://soapinterop.org/employee
 * Java type: org.soapinterop.employee.Employee
 *
 * Automatically generated - do not modify.
 */
package org.soapinterop.employee.impl;
/**
 * An XML Employee(@http://soapinterop.org/employee).
 *
 * This is a complex type.
 */
public class EmployeeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.soapinterop.employee.Employee
{
    
    public EmployeeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName PERSON$0 = 
        new javax.xml.namespace.QName("http://soapinterop.org/employee", "person");
    private static final javax.xml.namespace.QName SALARY$2 = 
        new javax.xml.namespace.QName("http://soapinterop.org/employee", "salary");
    private static final javax.xml.namespace.QName ID$4 = 
        new javax.xml.namespace.QName("http://soapinterop.org/employee", "ID");
    
    
    /**
     * Gets the "person" element
     */
    public org.soapinterop.person.Person getPerson()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.person.Person target = null;
            target = (org.soapinterop.person.Person)get_store().find_element_user(PERSON$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "person" element
     */
    public void setPerson(org.soapinterop.person.Person person)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.person.Person target = null;
            target = (org.soapinterop.person.Person)get_store().find_element_user(PERSON$0, 0);
            if (target == null)
            {
                target = (org.soapinterop.person.Person)get_store().add_element_user(PERSON$0);
            }
            target.set(person);
        }
    }
    
    /**
     * Appends and returns a new empty "person" element
     */
    public org.soapinterop.person.Person addNewPerson()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.soapinterop.person.Person target = null;
            target = (org.soapinterop.person.Person)get_store().add_element_user(PERSON$0);
            return target;
        }
    }
    
    /**
     * Gets the "salary" element
     */
    public double getSalary()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(SALARY$2, 0);
            if (target == null)
            {
                return 0.0;
            }
            return target.getDoubleValue();
        }
    }
    
    /**
     * Gets (as xml) the "salary" element
     */
    public org.apache.xmlbeans.XmlDouble xgetSalary()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDouble target = null;
            target = (org.apache.xmlbeans.XmlDouble)get_store().find_element_user(SALARY$2, 0);
            return target;
        }
    }
    
    /**
     * Sets the "salary" element
     */
    public void setSalary(double salary)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(SALARY$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(SALARY$2);
            }
            target.setDoubleValue(salary);
        }
    }
    
    /**
     * Sets (as xml) the "salary" element
     */
    public void xsetSalary(org.apache.xmlbeans.XmlDouble salary)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDouble target = null;
            target = (org.apache.xmlbeans.XmlDouble)get_store().find_element_user(SALARY$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlDouble)get_store().add_element_user(SALARY$2);
            }
            target.set(salary);
        }
    }
    
    /**
     * Gets the "ID" element
     */
    public int getID()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ID$4, 0);
            if (target == null)
            {
                return 0;
            }
            return target.getIntValue();
        }
    }
    
    /**
     * Gets (as xml) the "ID" element
     */
    public org.apache.xmlbeans.XmlInt xgetID()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlInt target = null;
            target = (org.apache.xmlbeans.XmlInt)get_store().find_element_user(ID$4, 0);
            return target;
        }
    }
    
    /**
     * Sets the "ID" element
     */
    public void setID(int id)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ID$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(ID$4);
            }
            target.setIntValue(id);
        }
    }
    
    /**
     * Sets (as xml) the "ID" element
     */
    public void xsetID(org.apache.xmlbeans.XmlInt id)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlInt target = null;
            target = (org.apache.xmlbeans.XmlInt)get_store().find_element_user(ID$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlInt)get_store().add_element_user(ID$4);
            }
            target.set(id);
        }
    }
}
