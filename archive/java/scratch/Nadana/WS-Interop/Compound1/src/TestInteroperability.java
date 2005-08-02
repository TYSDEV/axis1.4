
import org.soapinterop.xsd.*;
import org.soapinterop.SoapInteropCompound1PortTypeStub;
import junit.framework.TestCase;

/**
 * Created by IntelliJ IDEA.
 * User: Jaliya
 * Date: Aug 1, 2005
 * Time: 10:37:03 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestInteroperability extends TestCase{
    private String name = "Nadana";
       private float ID = (float) 10.55;
       private double age = 25;
       private boolean male = false;
       private Person retPerson = null;
       private SoapInteropCompound1PortTypeStub stub =null;
       private String docID="Axis2";
       private String docVal="Next Generation Web Services Engine";
       private Document retDocument=null;
       private XPersonDocument doc=null;
       private ResultPersonDocument resultEchoPerson=null;
       private ResultDocumentDocument1 resultEchoDocument=null;
       private Document document=null;

       public TestInteroperability(String name) {
           super(name);
       }

       public void setUp() throws Exception {
           stub = new SoapInteropCompound1PortTypeStub();
           Person person = Person.Factory.newInstance();
           person.setID(ID);
           person.setName(name);
           person.setAge(age);
           person.setMale(male);
           doc = XPersonDocument.Factory.newInstance();
           doc.setXPerson(person);
           resultEchoPerson = stub.echoPerson(doc);
           retPerson = resultEchoPerson.getResultPerson();
           document = Document.Factory.newInstance();
           document.setID(docID);
           document.setStringValue(docVal);
           //document.setNil();
           XDocumentDocument1 xDoc=XDocumentDocument1.Factory.newInstance();
           xDoc.setXDocument(document);
           resultEchoDocument = stub.echoDocument(xDoc);
           retDocument = resultEchoDocument.getResultDocument();
       }

       public void testEchoPerson() throws Exception {
           assertEquals(ID,retPerson.getID(),0);
           assertEquals(name,retPerson.getName());
           assertEquals(age,retPerson.getAge(),0);
           assertEquals(male,retPerson.getMale());
       }

       public void testEchoDocument(){
           assertEquals(docVal,retDocument.getStringValue());
           assertEquals(docID,retDocument.getID());
       }

}
