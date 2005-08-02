
import org.soapinterop.WSDLInteropTestDocLitPortTypeStub;
import org.soapinterop.xsd.EchoVoidDocument;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Jul 8, 2005
 * Time: 3:46:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class EchoVoidClient {
    public static void main(String[] args) {
        try {

            WSDLInteropTestDocLitPortTypeStub stub=new WSDLInteropTestDocLitPortTypeStub();
            EchoVoidDocument reqDoc=EchoVoidDocument.Factory.newInstance();
            EchoVoidDocument.EchoVoid echoVoid = EchoVoidDocument.EchoVoid.Factory.newInstance();
            reqDoc.setEchoVoid(echoVoid);
            stub.echoVoid(reqDoc);




        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
