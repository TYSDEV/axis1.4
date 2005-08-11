package org.apache.axis.jaxrpc.handler;

import org.apache.axis.jaxrpc.handler.soap.SOAPMessageContextImpl;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.AxisFault;
import org.apache.axis2.handlers.AbstractHandler;

public class Axis2Handler extends AbstractHandler {
	
	private javax.xml.rpc.handler.AbstractHandler jaxRpcHandler;
	
	public Axis2Handler(){
		//Default constructor, jaxRpcHandler will be set through setter method
	}
	
	public Axis2Handler(javax.xml.rpc.handler.AbstractHandler jaxRpcHandler){
		this.jaxRpcHandler = jaxRpcHandler;
	}
	
	public void setJaxRpcHandler(javax.xml.rpc.handler.AbstractHandler handler){
		jaxRpcHandler = handler;
	}
	
	public javax.xml.rpc.handler.AbstractHandler getJaxRpcHandler(){
		return jaxRpcHandler;
	}

	public void invoke(MessageContext mc) throws AxisFault {
		//Call the handlerMessage() method from 
		jaxRpcHandler.handleMessage(new SOAPMessageContextImpl(mc));
	}

}
