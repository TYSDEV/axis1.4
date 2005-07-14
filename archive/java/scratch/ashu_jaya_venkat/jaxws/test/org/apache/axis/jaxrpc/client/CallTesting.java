package org.apache.axis.jaxrpc.client;

//import java.net.URL;


import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;

import javax.xml.namespace.QName;
import javax.xml.rpc.Call;
import javax.xml.rpc.Service;
import javax.xml.rpc.ParameterMode;
//import javax.xml.rpc.ServiceFactory;

import org.apache.axis.jaxrpc.client.ServiceImpl;

import org.apache.axis2.om.OMElement;
import org.apache.axis2.om.OMOutput;

import junit.framework.TestCase;

public class CallTesting extends TestCase {

	public static void main(String[] args) {
	}

	public CallTesting(String name) {
		super(name);
	}

	public void testInvoke1() {
		try {

			Service s = new ServiceImpl();
			Call call = s.createCall();
			call.setOperationName(new QName("http://testingURL.org/","EchoString"));
			call.setTargetEndpointAddress("http://localhost:9090/axis/services/Echo");
			call.addParameter("param1", new QName("http://www.w3.org/2001/XMLSchema","any"), java.lang.Object.class, ParameterMode.IN);
			call.setReturnType(new QName("http://www.w3.org/2001/XMLSchema","any"), Object.class);
			Object[] inParams = new Object[]{"hello World!"};
			OMElement response = (OMElement)call.invoke(inParams);

			try {
				OutputStream fos = new BufferedOutputStream(System.out);
				OMOutput otpt = new OMOutput(fos, false);
				response.serialize(otpt);
				fos.flush();
				otpt.flush();
				} catch (Exception e){}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			fail(e.getMessage());
		}
	}
}
