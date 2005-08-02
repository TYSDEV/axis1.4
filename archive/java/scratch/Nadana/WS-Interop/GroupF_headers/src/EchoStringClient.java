
/**
 * Created by IntelliJ IDEA.
 * User: Jaliya
 * Date: Jul 30, 2005
 * Time: 2:07:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class EchoStringClient {
    public static void main(String[] args) {
        try{
     org.soapinterop.RetHeaderPortTypeStub stub=new org.soapinterop.RetHeaderPortTypeStub();
            org.soapinterop.xsd.EchoStringParamDocument reqDoc=org.soapinterop.xsd.EchoStringParamDocument.Factory.newInstance();
            reqDoc.setEchoStringParam("hi sama");

             org.soapinterop.xsd.EchoStringReturnDocument resDoc=stub.echoString(reqDoc);
            System.out.println(resDoc.getEchoStringReturn());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
