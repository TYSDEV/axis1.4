package PACKAGE_NAME;

import junit.framework.TestCase;
import org.soapinterop.SoapInteropCompound2PortTypeStub;
import org.soapinterop.employee.XEmployeeDocument;
import org.soapinterop.employee.ResultEmployeeDocument;
import org.soapinterop.employee.Employee;
import org.soapinterop.person.Person;

/**
 * Created by IntelliJ IDEA.
 * User: Jaliya
 * Date: Aug 1, 2005
 * Time: 10:41:56 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestInteroperability extends TestCase {

    private String name = "Nadana";
           private int ID = (int) 10;
           private double age = 25;
           private boolean male = false;
            private double salary=20000;
           private Person retPerson = null;
            private Employee retEmployee=null;
           private SoapInteropCompound2PortTypeStub stub =null;
           private String docID="Axis2";
           private String docVal="Next Generation Web Services Engine";

           private XEmployeeDocument doc=null;
           private ResultEmployeeDocument resultEmployee=null;
           //private ResultDocumentDocument1 resultEchoDocument=null;


           public TestInteroperability(String name) {
               super(name);
           }

           public void setUp() throws Exception {
               stub = new SoapInteropCompound2PortTypeStub();
               Person person = Person.Factory.newInstance();
               Employee employee=Employee.Factory.newInstance();


               person.setName(name);
               person.setMale(male);

               employee.setID(ID);
               employee.setSalary(salary);
               employee.setPerson(person);

               doc = XEmployeeDocument.Factory.newInstance();
               doc.setXEmployee(employee);
               resultEmployee = stub.echoEmployee(doc);
//               retPerson = resultEmployee.getResultEmployee();
//               //document = Document.Factory.newInstance();
//               //document.setID(docID);
//               //document.setStringValue(docVal);
//               //document.setNil();
//               XDocumentDocument1 xDoc=XDocumentDocument1.Factory.newInstance();
//               xDoc.setXDocument(document);
//               resultEchoDocument = stub.echoDocument(xDoc);
//               retDocument = resultEchoDocument.getResultDocument();
//           }
//
//           public void testEchoPerson() throws Exception {
//               assertEquals(ID,retPerson.getID(),0);
//               assertEquals(name,retPerson.getName());
//               assertEquals(age,retPerson.getAge(),0);
//               assertEquals(male,retPerson.getMale());
//           }
//
//           public void testEchoDocument(){
//               assertEquals(docVal,retDocument.getStringValue());
//               assertEquals(docID,retDocument.getID());
//           }
//
//


}
}
