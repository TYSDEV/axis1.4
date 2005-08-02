
import org.soapinterop.xsd.SOAPStruct;
import org.soapinterop.xsd.EchoStructReturnDocument;
import org.soapinterop.xsd.EchoStructParamDocument;
import org.soapinterop.WSDLInteropTestDocLitPortTypeStub;

/**
 * Created by IntelliJ IDEA.
 * User: Jaliya
 * Date: Jul 29, 2005
 * Time: 5:13:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class EchoStructClient {
     public static void main(String[] args) {
        try {

           WSDLInteropTestDocLitPortTypeStub stub=new WSDLInteropTestDocLitPortTypeStub();

     //Create the request Document
    EchoStructParamDocument reqDoc = EchoStructParamDocument.Factory.newInstance();

    //Create the complex type
    SOAPStruct reqStruct = SOAPStruct.Factory.newInstance();

    reqStruct.setVarFloat(100.50F);
    reqStruct.setVarInt(10);
    reqStruct.setVarString("High");

    reqDoc.setEchoStructParam(reqStruct);

    //Service invocation
    EchoStructReturnDocument resDoc = stub.echoStruct(reqDoc);
    SOAPStruct resStruct = resDoc.getEchoStructReturn();

    System.out.println("float Value :" + resStruct.getVarFloat());
    System.out.println("int Value :" + resStruct.getVarInt());
    System.out.println("String Value :" + resStruct.getVarString());

} catch (Exception e) {
    e.printStackTrace();
}

}
}
