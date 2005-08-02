
import org.soapinterop.xsd.EchoStringArrayParamDocument;
import org.soapinterop.xsd.ArrayOfstringLiteral;
import org.soapinterop.xsd.EchoStringArrayReturnDocument;
import org.soapinterop.WSDLInteropTestDocLitPortTypeStub;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Jul 19, 2005
 * Time: 11:38:54 AM
 * To change this template use File | Settings | File Templates.
 */
public class EchoStringArrayClient {
    public static void main(String[] args) {
        try{
            WSDLInteropTestDocLitPortTypeStub stub=new WSDLInteropTestDocLitPortTypeStub();
            EchoStringArrayParamDocument reqDoc=EchoStringArrayParamDocument.Factory.newInstance();

            ArrayOfstringLiteral paramArr=ArrayOfstringLiteral.Factory.newInstance();
            paramArr.insertString(0,"hi");
            paramArr.insertString(1,"nadana");
            reqDoc.setEchoStringArrayParam(paramArr);
            stub.echoStringArray(reqDoc);

            EchoStringArrayReturnDocument retDoc=stub.echoStringArray(reqDoc);


            String[] resParams=retDoc.getEchoStringArrayReturn().getStringArray();
            for (int i = 0; i< resParams.length - 1; i++) {
                System.out.println(resParams[i]);
            }



        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
}
