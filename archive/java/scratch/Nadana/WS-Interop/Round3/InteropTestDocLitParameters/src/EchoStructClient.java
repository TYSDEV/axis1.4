
import org.soapinterop.WSDLInteropTestDocLitPortTypeStub;
import org.soapinterop.xsd.EchoStructDocument;
import org.soapinterop.xsd.SOAPStruct;
import org.soapinterop.xsd.EchoStructResponseDocument;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Jul 20, 2005
 * Time: 9:45:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class EchoStructClient {
    public static void main(String[] args) {

        try{
            WSDLInteropTestDocLitPortTypeStub stub=new WSDLInteropTestDocLitPortTypeStub();
            EchoStructDocument reqDoc=EchoStructDocument.Factory.newInstance();
            EchoStructDocument.EchoStruct struct=EchoStructDocument.EchoStruct.Factory.newInstance();
            SOAPStruct reqStr=SOAPStruct.Factory.newInstance();

            reqStr.setVarFloat(12);
            reqStr.setVarInt(20);
            reqStr.setVarString("nadana");


            //struct.setParam0(reqStr);


            reqDoc.setEchoStruct(struct);

            EchoStructResponseDocument resDoc= stub.echoStruct(reqDoc);


            resDoc.getEchoStructResponse();


        }  catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
