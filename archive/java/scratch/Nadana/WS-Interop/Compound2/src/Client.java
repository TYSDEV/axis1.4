
import org.soapinterop.SoapInteropCompound2PortTypeStub;
import org.soapinterop.employee.Employee;
import org.soapinterop.employee.XEmployeeDocument;
import org.soapinterop.person.Person;

/**
 * Created by IntelliJ IDEA.
 * User: Jaliya
 * Date: Jul 29, 2005
 * Time: 4:20:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class Client {
    public static void main(String[] args) {
        try{
        SoapInteropCompound2PortTypeStub stub=new SoapInteropCompound2PortTypeStub();


            XEmployeeDocument empDoc=XEmployeeDocument.Factory.newInstance();

            Employee emp=Employee.Factory.newInstance();
            Person p=Person.Factory.newInstance();

            p.setName("nadana");
            p.setMale(false);


            emp.setID(001);
            emp.setPerson(p);
            emp.setSalary(20000.00);
            


            empDoc.setXEmployee(emp);
            stub.echoEmployee(empDoc);
    }catch (Exception e) {
            e.printStackTrace();


    }
    }
}
