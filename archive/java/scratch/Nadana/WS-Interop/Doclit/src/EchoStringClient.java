
import org.soapinterop.WSDLInteropTestDocLitPortTypeStub;
import org.soapinterop.xsd.EchoStringReturnDocument;
import org.soapinterop.xsd.EchoStringParamDocument;

/**
 * Created by IntelliJ IDEA.
 * User: Jaliya
 * Date: Jul 29, 2005
 * Time: 5:04:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class EchoStringClient {

    public static void main(String[] args) {
        try{
        WSDLInteropTestDocLitPortTypeStub stub=new WSDLInteropTestDocLitPortTypeStub();
        EchoStringParamDocument reqDoc1=EchoStringParamDocument.Factory.newInstance();
            reqDoc1.setEchoStringParam("hi");

            //invokes the web service.
            EchoStringReturnDocument resDoc1=stub.echoString(reqDoc1);
            //System.out.println("res");
            System.out.println(resDoc1.getEchoStringReturn());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
