
import org.soapinterop.SoapInteropCompound1PortTypeStub;
import org.soapinterop.xsd.*;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Jul 18, 2005
 * Time: 4:54:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class Client {
    public static void main(String[] args) {

        SoapInteropCompound1PortTypeStub stub;
        try {
            stub = new SoapInteropCompound1PortTypeStub();
            XDocumentDocument1 reqDoc=XDocumentDocument1.Factory.newInstance();
            XPersonDocument d=XPersonDocument.Factory.newInstance();
            Document doc=Document.Factory.newInstance();
            Person p=Person.Factory.newInstance();

            doc.setID("A001");
            doc.setStringValue("Nadana");

            p.setName("Nadana");
            p.setID(001);
            p.setAge(25);
            p.setMale(false);

            d.setXPerson(p);



            reqDoc.setXDocument(doc);
            

            stub.echoDocument(reqDoc);
            stub.echoPerson(d);


               /*SoapInteropCompound1PortTypeStub stub = new SoapInteropCompound1PortTypeStub();
            XDocumentDocument1 doc = XDocumentDocument1.Factory.newInstance();
            XPersonDocument d = XPersonDocument.Factory.newInstance();
            Document doc1 = Document.Factory.newInstance();
            Person p=Person.Factory.newInstance();
            doc1.setID("Nadana");
            doc1.setStringValue("Gunarathna");


            p.setID(001);
            p.setName("samangie");
            p.setAge(25);
            p.setMale(false);



            doc.setXDocument(doc1);
            d.setXPerson(p);

            stub.echoDocument(doc);
            stub.echoPerson(d); */








        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
}
