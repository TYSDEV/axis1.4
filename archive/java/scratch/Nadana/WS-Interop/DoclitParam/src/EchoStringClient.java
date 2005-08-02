
import org.soapinterop.WSDLInteropTestDocLitPortTypeStub;
import org.soapinterop.xsd.EchoStringDocument;
import org.soapinterop.xsd.EchoStringResponseDocument;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: Jul 8, 2005
 * Time: 4:54:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class EchoStringClient {
   public static void main(String[] args) {


    try {
                 WSDLInteropTestDocLitPortTypeStub stub=new WSDLInteropTestDocLitPortTypeStub();//null,"http://soapinterop.java.sun.com:80/round3/groupd/doclitparams");
               //echoString
               //Create the request document to be sent.
               EchoStringDocument reqDoc1=EchoStringDocument.Factory.newInstance();
               EchoStringDocument.EchoString str=EchoStringDocument.EchoString.Factory.newInstance();
        
               str.setParam0("hi");
               reqDoc1.setEchoString(str);


//               //invokes the web service.
             EchoStringResponseDocument resDoc1=stub.echoString(reqDoc1);
//               System.out.println("res");
               System.out.println(resDoc1.getEchoStringResponse());

           } catch (Exception e) {
               e.printStackTrace();
           }
       }



}
