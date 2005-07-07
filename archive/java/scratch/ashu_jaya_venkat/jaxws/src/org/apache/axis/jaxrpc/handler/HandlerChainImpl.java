package org.apache.axis.jaxrpc.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

import javax.xml.rpc.JAXRPCException;
import javax.xml.rpc.handler.Handler;
import javax.xml.rpc.handler.HandlerChain;
import javax.xml.rpc.handler.HandlerInfo;
import javax.xml.rpc.handler.MessageContext;
import javax.xml.rpc.handler.soap.SOAPMessageContext;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;

import org.apache.axis.jaxrpc.handler.soap.SOAPMessageContextImpl;
import org.apache.axis.jaxrpc.handler.MessageContextImpl;

public class HandlerChainImpl extends ArrayList implements HandlerChain {

	private String[] roles;
	
	private int falseIndex = -1;
	
	protected List handlerInfos = new ArrayList();
	
	public static final String JAXRPC_METHOD_INFO = "jaxrpc.method.info";
	
	private HandlerInfo getHandlerInfo(int index){
		return (HandlerInfo)handlerInfos.get(index);
	}
	
	private Handler getHandlerInstance(int index) {
		return (Handler)get(index);
	}
	
	private Handler newHandler(HandlerInfo handlerInfo){
		try{
			Handler handler = (Handler)handlerInfo.getHandlerClass().newInstance();
			handler.init(handlerInfo);
			return handler;
		} catch (Exception ex){
			throw new JAXRPCException("No Jax-RPC handler" + handlerInfo.getHandlerClass().toString(), ex);
		}
	}
	
	private void postInvoke(SOAPMessageContext msgContext) {
		SOAPMessage message = msgContext.getMessage();
		ArrayList oldList =  (ArrayList)msgContext.getProperty(JAXRPC_METHOD_INFO);
		if (oldList != null) {
			if (!Arrays.equals(oldList.toArray(), getMessageInfo(message).toArray())) {
				throw new RuntimeException("invocationArgumentsModified");
			}
		}
	}
	
	public HandlerChainImpl(){
		
	}
	
	public HandlerChainImpl(List handlerInfos){
		this.handlerInfos = handlerInfos;
		for(int i = 0; i < handlerInfos.size(); i++){
			add(newHandler(getHandlerInfo(i)));
		}
	}
	
	public void addNewHandler(String className, Map config){
		try {
			HandlerInfo handlerInfo = new HandlerInfo(ClassUtils.forName(className), config, null);
			handlerInfos.add(handlerInfo);
			add(newHandler(handlerInfo));
		} catch(Exception ex){
			throw new JAXRPCException("No Jax-RPC handler"+className, ex);
		}
	}
	
	public ArrayList getMessageInfo(SOAPMessage message) {
		ArrayList list = new ArrayList();
		try {
			if(message == null || message.getSOAPPart() == null)
				return list;
			SOAPEnvelope env = message.getSOAPPart().getEnvelope();
			SOAPBody body = env.getBody();
			java.util.Iterator it = body.getChildElements();
			SOAPElement operation = (SOAPElement)it.next();
			list.add(operation.getElementName().toString());
			for (java.util.Iterator i = operation.getChildElements(); i.hasNext();) {
				SOAPElement elt = (SOAPElement)i.next();
				list.add(elt.getElementName().toString());
			}
		} catch(Exception e){
			
		}
		return list;
	}
	
	public boolean handleRequest(MessageContext context) throws JAXRPCException {
		SOAPMessageContextImpl actx = (SOAPMessageContextImpl)context;
		actx.setRoles(getRoles());
		try {
			for (int i = 0; i < size(); i++) {
				Handler currentHandler = getHandlerInstance(i);
				try {
					if (currentHandler.handleRequest(context) == false) {
						falseIndex = i;
						return false;
					}
				}catch (javax.xml.rpc.soap.SOAPFaultException sfe) {
					falseIndex = i;
					throw sfe;
				}
			}
			return true;
		}finally {
			postInvoke(actx);
		}
	}

	public boolean handleResponse(MessageContext context)
			throws JAXRPCException {
		SOAPMessageContextImpl scontext = (SOAPMessageContextImpl)context;
		try {
			int endIdx = size() - 1;
			if (falseIndex != -1) {
				endIdx = falseIndex;
			}
			for (int i = endIdx; i >= 0; i--) {
				if (getHandlerInstance(i).handleResponse(context) == false) {
					return false;
				}
			}
			return true;
		} finally {
			postInvoke(scontext);
		}
	}

	public boolean handleFault(MessageContext _context) throws JAXRPCException {
		SOAPMessageContextImpl context = (SOAPMessageContextImpl)_context;
		try {
			int endIdx = size() - 1;
			if (falseIndex != -1) {
				endIdx = falseIndex;
			}
			for (int i = endIdx; i >= 0; i--) {
				if (getHandlerInstance(i).handleFault(context) == false) {
					return false;
				}
			}
			return true;
		} finally{
			postInvoke(context);
		}
	}

	public void init(Map config) throws JAXRPCException {
		// DO SOMETHING WITH THIS
	}

	public void destroy() throws JAXRPCException {
		int endIdx = size() - 1;
		if (falseIndex != -1) {
			endIdx = falseIndex;
		}
		for (int i = endIdx; i >= 0; i--) {
			getHandlerInstance(i).destroy();
		}
		falseIndex = -1;
		clear();
	}

	public void setRoles(String[] soapActorNames) {
		if(soapActorNames != null){
			// use clone for cheap array copy
			roles = (String[])soapActorNames.clone();
		}
	}

	public String[] getRoles() {
		return roles;
	}

}
