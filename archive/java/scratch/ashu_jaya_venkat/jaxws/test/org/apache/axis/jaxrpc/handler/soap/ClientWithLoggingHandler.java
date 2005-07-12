package org.apache.axis.jaxrpc.handler.soap;

import junit.framework.TestCase;

import javax.xml.namespace.QName;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.rpc.ServiceFactory;
import javax.xml.rpc.Service;
import javax.xml.rpc.Call;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.handler.HandlerInfo;
import javax.xml.rpc.handler.HandlerRegistry;

public class ClientWithLoggingHandler extends TestCase {

	public static void main(String[] args) {
	}

	public ClientWithLoggingHandler(String name) {
		super(name);
	}
	
	public void testDII() {
		try {
			ServiceFactory sf = ServiceFactory.newInstance();
			Service s = sf.createService(new URL(getTestResourceDirectory()+ "/Echo.wsdl") , new QName("EchoService"));
			
			HandlerRegistry registry = s.getHandlerRegistry();
			List<HandlerInfo> handlerList = new ArrayList<HandlerInfo>();
			HandlerInfo hInfo = new HandlerInfo();
			hInfo.setHandlerClass(LoggingHandler.class);
			handlerList.add(hInfo);
			registry.setHandlerChain(handlerList);
			
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
