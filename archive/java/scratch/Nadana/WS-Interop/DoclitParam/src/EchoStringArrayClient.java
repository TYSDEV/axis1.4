
import org.soapinterop.WSDLInteropTestDocLitPortTypeStub;
import org.soapinterop.xsd.EchoStringArrayDocument;
import org.soapinterop.xsd.EchoStringDocument;
import org.soapinterop.xsd.ArrayOfstringLiteral;
import org.soapinterop.xsd.EchoStringArrayResponseDocument;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Jul 9, 2005
 * Time: 11:04:08 AM
 * To change this template use File | Settings | File Templates.
 */
public class EchoStringArrayClient {
    public static void main(String[] args) {
        try{
            WSDLInteropTestDocLitPortTypeStub stub=new WSDLInteropTestDocLitPortTypeStub(null,"http://soapinterop.java.sun.com:80/round3/groupd/doclitparams");
            EchoStringArrayDocument reqDoc=EchoStringArrayDocument.Factory.newInstance();
            EchoStringArrayDocument.EchoStringArray str=EchoStringArrayDocument.EchoStringArray.Factory.newInstance();

            ArrayOfstringLiteral paramArray = ArrayOfstringLiteral.Factory.newInstance();
            
            paramArray.setStringArray(0,"hi");
            paramArray.setStringArray(1,"nadana");
            reqDoc.setEchoStringArray1(str);

            stub.echoStringArray(reqDoc);
            EchoStringArrayResponseDocument resDoc = stub.echoStringArray(reqDoc);
            String[] resParams = resDoc.getEchoStringArrayResponse().getReturn().getStringArray();
            for (int i = 0; i < resParams.length; i++) {
                System.out.println(resParams[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

