
import org.soapinterop.SoapInteropCompound2PortTypeStub;
import org.soapinterop.person.Person;
import org.soapinterop.employee.XEmployeeDocument;
import org.soapinterop.employee.Employee;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Jul 19, 2005
 * Time: 9:46:31 AM
 * To change this template use File | Settings | File Templates.
 */
public class Client {
    public static void main(String[] args) {

        SoapInteropCompound2PortTypeStub stub;
        try {
            stub = new SoapInteropCompound2PortTypeStub();
            XEmployeeDocument reqDoc=XEmployeeDocument.Factory.newInstance();
            Employee emp=Employee.Factory.newInstance();
            Person p=Person.Factory.newInstance();

            p.setName("Nadana");
            p.setMale(false);

            emp.setID(001);
            emp.setPerson(p);
            emp.setSalary(20000.00);

            reqDoc.setXEmployee(emp);
            stub.echoEmployee(reqDoc);



        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
}
