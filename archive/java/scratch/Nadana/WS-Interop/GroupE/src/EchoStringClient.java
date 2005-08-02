
import org.soapinterop.WSDLInteropTestDocLitPortTypeStub;
import org.soapinterop.xsd.EchoStringParamDocument;
import org.soapinterop.xsd.EchoStringReturnDocument;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Jul 20, 2005
 * Time: 12:20:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class EchoStringClient {
    public static void main(String[] args) {
        try{
        WSDLInteropTestDocLitPortTypeStub stub=new WSDLInteropTestDocLitPortTypeStub();
            EchoStringParamDocument reqDoc=EchoStringParamDocument.Factory.newInstance();
            reqDoc.setEchoStringParam("hi");
            EchoStringReturnDocument resDoc=stub.echoString(reqDoc);
            resDoc.getEchoStringReturn();


    }   catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
