
import org.soapinterop.SoapInteropCompound1PortTypeStub;
import org.soapinterop.SoapInteropCompound1PortTypeStub;
import org.soapinterop.xsd.XPersonDocument;
import org.soapinterop.xsd.Person;
import org.soapinterop.xsd.XDocumentDocument1;
import org.soapinterop.xsd.Document;



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
        SoapInteropCompound1PortTypeStub stub=new SoapInteropCompound1PortTypeStub();


            XPersonDocument pDoc= XPersonDocument.Factory.newInstance();
            //XDocumentDocument1 doc=XDocumentDocument1.Factory.newInstance();
            //Person emp=Per.Factory.newInstance();
            Person p=Person.Factory.newInstance();
            Document doc=Document.Factory.newInstance();

            p.setName("Nadana");
            p.setMale(false);
            p.setID(001);
            p.setAge(25);

            doc.setID("Axis2");
            


            pDoc.setXPerson(p);
            stub.echoPerson(pDoc);
            //stub.echoDocument(doc);
    }catch (Exception e) {
            e.printStackTrace();


    }
    }
}
