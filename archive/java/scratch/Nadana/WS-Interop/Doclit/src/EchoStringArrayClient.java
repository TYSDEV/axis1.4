
import org.soapinterop.xsd.EchoStringArrayReturnDocument;
import org.soapinterop.xsd.EchoStringArrayParamDocument;
import org.soapinterop.xsd.ArrayOfstringLiteral;
import org.soapinterop.WSDLInteropTestDocLitPortTypeStub;

/**
 * Created by IntelliJ IDEA.
 * User: Jaliya
 * Date: Jul 29, 2005
 * Time: 5:08:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class EchoStringArrayClient {

    public static void main(String[] args) {
        try{
         WSDLInteropTestDocLitPortTypeStub stub=new WSDLInteropTestDocLitPortTypeStub(null,"http://soapinterop.java.sun.com:80/round3/groupd/doclit");


               //Create the request document to be sent.
               EchoStringArrayParamDocument reqDoc2 = EchoStringArrayParamDocument.Factory.newInstance();
               ArrayOfstringLiteral paramArray = ArrayOfstringLiteral.Factory.newInstance();
               paramArray.insertString(0,"Hi");
               paramArray.insertString(1,"nadana");

               reqDoc2.setEchoStringArrayParam(paramArray);

               EchoStringArrayReturnDocument resDoc2 = stub.echoStringArray(reqDoc2);
//Get the response params
               String[] resParams = resDoc2.getEchoStringArrayReturn().getStringArray();

               for (int i = 0; i < resParams.length; i++) {
                   System.out.println(resParams[i]);
               }








           } catch (Exception e) {
               e.printStackTrace();
           }
    }
}
