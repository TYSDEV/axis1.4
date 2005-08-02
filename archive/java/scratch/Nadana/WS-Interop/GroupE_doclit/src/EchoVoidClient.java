
import org.soapinterop.WSDLInteropTestDocLitPortTypeStub;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Jul 9, 2005
 * Time: 12:34:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class EchoVoidClient {
    public static void main(String[] args) {
        try{
        WSDLInteropTestDocLitPortTypeStub stub=new WSDLInteropTestDocLitPortTypeStub();
        stub.echoVoid();
    }catch (Exception e) {
    e.printStackTrace();
}

    }
}
