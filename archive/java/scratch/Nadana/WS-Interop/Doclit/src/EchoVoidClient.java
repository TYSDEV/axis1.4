
import org.soapinterop.WSDLInteropTestDocLitPortTypeStub;

/**
 * Created by IntelliJ IDEA.
 * User: Jaliya
 * Date: Jul 29, 2005
 * Time: 4:51:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class EchoVoidClient {

    public static void main(String[] args) {

          try {

           WSDLInteropTestDocLitPortTypeStub stub=new WSDLInteropTestDocLitPortTypeStub();

            stub.echoVoid();


             } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
