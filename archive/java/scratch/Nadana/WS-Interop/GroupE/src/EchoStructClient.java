
import org.soapinterop.WSDLInteropTestDocLitPortTypeStub;
import org.soapinterop.xsd.EchoStructReturnDocument;
import org.soapinterop.xsd.EchoStructParamDocument;
import org.soapinterop.xsd.SOAPStruct;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Jul 20, 2005
 * Time: 2:08:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class EchoStructClient {
    public static void main(String[] args) {
        try{
        WSDLInteropTestDocLitPortTypeStub stub=new WSDLInteropTestDocLitPortTypeStub();
        EchoStructParamDocument reqDoc=EchoStructParamDocument.Factory.newInstance();
        SOAPStruct struct=SOAPStruct.Factory.newInstance();

            struct.setVarFloat(12);
            struct.setVarInt(5);
            struct.setVarString("nadee");

            reqDoc.setEchoStructParam(struct);
            EchoStructReturnDocument retDoc=stub.echoStruct(reqDoc);
            retDoc.getEchoStructReturn();






         }catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
}
}
