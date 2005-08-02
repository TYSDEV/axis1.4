
import org.soapinterop.WSDLInteropTestDocLitPortTypeStub;
import org.soapinterop.xsd.EchoStringParamDocument;
import org.soapinterop.xsd.EchoStringReturnDocument;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Jul 9, 2005
 * Time: 12:41:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class EchoVoidStringClient {
    public static void main(String[] args) {
        try {

            WSDLInteropTestDocLitPortTypeStub stub=new WSDLInteropTestDocLitPortTypeStub();
            //echoString
            //Create the request document to be sent.
            EchoStringParamDocument reqDoc1=EchoStringParamDocument.Factory.newInstance();
            reqDoc1.setEchoStringParam("hi Nadana");

            //invokes the web service.
            EchoStringReturnDocument resDoc1=stub.echoString(reqDoc1);
            System.out.println(resDoc1.getEchoStringReturn());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
