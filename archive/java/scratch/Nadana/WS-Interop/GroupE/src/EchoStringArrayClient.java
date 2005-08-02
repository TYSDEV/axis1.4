
import org.soapinterop.WSDLInteropTestDocLitPortTypeStub;
import org.soapinterop.xsd.EchoStringArrayParamDocument;
import org.soapinterop.xsd.ArrayOfstringLiteral;
import org.soapinterop.xsd.EchoStringArrayReturnDocument;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Jul 20, 2005
 * Time: 12:36:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class EchoStringArrayClient {

    public static void main(String[] args) {
        try{
            WSDLInteropTestDocLitPortTypeStub stub=new WSDLInteropTestDocLitPortTypeStub();
            EchoStringArrayParamDocument reqDoc=EchoStringArrayParamDocument.Factory.newInstance();
            ArrayOfstringLiteral str=ArrayOfstringLiteral.Factory.newInstance();

            str.insertString(0,"hi");
            str.insertString(1,"nadee");

            reqDoc.setEchoStringArrayParam(str);

            EchoStringArrayReturnDocument resDoc=stub.echoStringArray(reqDoc);
            String[] resParams=resDoc.getEchoStringArrayReturn().getStringArray();

            for (int i = 0; i< resParams.length ; i++) {
                System.out.println(resParams[i]);
            }





        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
