
import org.soapinterop.WSDLInteropTestDocLitPortTypeStub;
import org.soapinterop.xsd.EchoStringDocument;
import org.soapinterop.xsd.EchoStringResponseDocument;
import org.apache.xmlbeans.XmlString;
import org.apache.xml.utils.XMLString;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Jul 19, 2005
 * Time: 2:17:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class EchoStringClient {
    public static void main(String[] args) {
        try{

            WSDLInteropTestDocLitPortTypeStub stub=new WSDLInteropTestDocLitPortTypeStub();
            EchoStringDocument reqDoc=EchoStringDocument.Factory.newInstance();
            EchoStringDocument.EchoString echo=EchoStringDocument.EchoString.Factory.newInstance();
            //XmlString str=XmlString.Factory.newInstance();
            
            echo.setParam0("hi");

            //echo.xsetParam0(str);
            reqDoc.addNewEchoString();

            //reqDoc.setEchoString(echo);

            EchoStringResponseDocument resDoc=stub.echoString(reqDoc);
            System.out.println(resDoc.getEchoStringResponse());

        }   catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
