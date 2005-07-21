package org.apache.axis.jaxrpc.handler.soap;

import junit.framework.TestCase;

import javax.xml.namespace.QName;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.rpc.ServiceFactory;
import javax.xml.rpc.Service;
import javax.xml.rpc.Call;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.handler.HandlerInfo;
import javax.xml.rpc.handler.HandlerRegistry;

import org.apache.axis.jaxrpc.client.ServiceImpl;
import org.apache.axis2.om.OMElement;
import org.apache.axis2.om.impl.OMOutputImpl;

public class ClientWithLoggingHandler extends TestCase {

	public static void main(String[] args) {
	}

	public ClientWithLoggingHandler(String name) {
		super(name);
	}
	
	public void testDII() {
		try {
			Service s = new ServiceImpl();
			
			HandlerRegistry registry = s.getHandlerRegistry();
			List<HandlerInfo> handlerList = new ArrayList<HandlerInfo>();
			HandlerInfo hInfo = new HandlerInfo();
			hInfo.setHandlerClass(LoggingHandler.class);
			handlerList.add(hInfo);
			registry.setHandlerChain(handlerList);
			
			Call call = s.createCall();
			call.setOperationName(new QName("http://testingURL.org/","EchoString"));
			call.setTargetEndpointAddress("http://localhost:8080/axis/services/Echo");
			call.addParameter("param1", new QName("http://www.w3.org/2001/XMLSchema","any"), java.lang.Object.class, ParameterMode.IN);
			call.setReturnType(new QName("http://www.w3.org/2001/XMLSchema","any"), Object.class);
			Object[] inParams = new Object[]{"hello World!"};
			OMElement response = (OMElement)call.invoke(inParams);
			try {
				OutputStream fos = new BufferedOutputStream(System.out);
				OMOutputImpl otpt = new OMOutputImpl(fos, false);
				response.serialize(otpt);
				fos.flush();
				otpt.flush();
				} catch (Exception e){}

		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	

}
