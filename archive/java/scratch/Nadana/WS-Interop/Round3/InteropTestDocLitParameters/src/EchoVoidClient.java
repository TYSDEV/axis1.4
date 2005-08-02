
import org.soapinterop.WSDLInteropTestDocLitPortTypeStub;
import org.soapinterop.xsd.EchoVoidDocument;
import org.soapinterop.xsd.EchoVoidResponseDocument;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Jul 19, 2005
 * Time: 1:42:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class EchoVoidClient {
    public static void main(String[] args) {
        try{


        WSDLInteropTestDocLitPortTypeStub stub=new WSDLInteropTestDocLitPortTypeStub();
            EchoVoidDocument reqDoc=EchoVoidDocument.Factory.newInstance();
            EchoVoidDocument.EchoVoid echo=EchoVoidDocument.EchoVoid.Factory.newInstance();
            reqDoc.setEchoVoid(echo);
            EchoVoidResponseDocument resDoc=stub.echoVoid(reqDoc);

            EchoVoidResponseDocument.EchoVoidResponse res=EchoVoidResponseDocument.EchoVoidResponse.Factory.newInstance();
            resDoc.setEchoVoidResponse(res);
            
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        }
}
