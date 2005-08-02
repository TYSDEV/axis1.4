
import org.soapinterop.WSDLInteropTestDocLitPortTypeStub;
import org.soapinterop.xsd.EchoStructReturnDocument;
import org.soapinterop.xsd.EchoStructParamDocument;
import org.soapinterop.xsd.SOAPStruct;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Jul 19, 2005
 * Time: 12:36:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class EchoStructClient {
    public static void main(String[] args) {
        try{
        WSDLInteropTestDocLitPortTypeStub stub=new WSDLInteropTestDocLitPortTypeStub();
        EchoStructParamDocument reqDoc=EchoStructParamDocument.Factory.newInstance();
          SOAPStruct struct=SOAPStruct.Factory.newInstance();
            struct.setVarFloat(12);
            struct.setVarInt(10);
            struct.setVarString("hi");
         reqDoc.setEchoStructParam(struct);
            stub.echoStruct(reqDoc); 


         } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
