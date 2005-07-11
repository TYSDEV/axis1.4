package org.apache.axis.jaxrpc.integrationTesting;

import junit.framework.TestCase;

import javax.xml.namespace.QName;
import java.net.URL;
import javax.xml.rpc.ServiceFactory;
import javax.xml.rpc.Service;
import javax.xml.rpc.Call;
import javax.xml.rpc.ParameterMode;

public class ClientAPITest1 extends TestCase {

	public static void main(String[] args) {
	}

	public ClientAPITest1(String name) {
		super(name);
	}
	
	public void testDII() {
		try {
			ServiceFactory sf = ServiceFactory.newInstance();
			Service s = sf.createService(new URL(getTestResourceDirectory()+ "/Echo.wsdl") , new QName("EchoService"));
			
			Call call = s.createCall();
			call.addParameter("param1", new QName("Here the URL for XSD should be given","string"), java.lang.String.class, ParameterMode.IN);
			call.setReturnType(new QName("URL of XSD","string"), String.class);
			Object[] inParams = new Object[]{"hello World!"};
			String response = (String) call.invoke(inParams);
			assertEquals("hello World!",response);
		}catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	private String getTestResourceDirectory() {
		return "C:\\workspace\\3.1Workspace\\JAXRPC2_Work\\test-resources";
	}

}
