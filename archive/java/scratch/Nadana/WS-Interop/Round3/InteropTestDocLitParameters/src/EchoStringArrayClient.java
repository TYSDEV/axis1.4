
import org.soapinterop.WSDLInteropTestDocLitPortTypeStub;
import org.soapinterop.xsd.EchoStringArrayDocument;
import org.soapinterop.xsd.ArrayOfstringLiteral;
import org.soapinterop.xsd.EchoStringArrayResponseDocument;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Jul 19, 2005
 * Time: 4:59:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class EchoStringArrayClient {
    public static void main(String[] args) {
        try{
        WSDLInteropTestDocLitPortTypeStub stub=new WSDLInteropTestDocLitPortTypeStub();
          EchoStringArrayDocument reqDoc=EchoStringArrayDocument.Factory.newInstance();
             EchoStringArrayDocument.EchoStringArray paramArr=EchoStringArrayDocument.EchoStringArray.Factory.newInstance();

            ArrayOfstringLiteral arr=ArrayOfstringLiteral.Factory.newInstance();
            arr.insertString(0,"hi");
            arr.insertString(1,"nadana");


            paramArr.setParam0(arr);
            reqDoc.setEchoStringArray1(paramArr);
            //stub.echoStringArray(reqDoc);

            EchoStringArrayResponseDocument resDoc = stub.echoStringArray(reqDoc);
            String[] resParams = resDoc.getEchoStringArrayResponse().getReturn().getStringArray();
            for (int i = 0; i < resParams.length; i++) {
                System.out.println(resParams[i]);
            }






            //reqDoc.setEchoStringArray1();


    } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}