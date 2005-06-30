package org.apache.axis.jaxrpc.client;

import java.rmi.RemoteException;
import java.util.concurrent.Future;

import javax.xml.rpc.AsyncHandler;
import javax.xml.rpc.Dispatch;
import javax.xml.rpc.JAXRPCException;
import javax.xml.rpc.Response;

public class DispatchImpl extends BindingProviderImpl implements Dispatch {

	
	public DispatchImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Method invoke
	 * Invoke a service operation synchronously. The client is responsible 
	 * for ensuring that the msg object when marshalled is formed according 
	 * to the requirements of the protocol binding in use.
	 * 
	 * @param msg - An object that will form the message or payload of the 
	 * message used to invoke the operation.
	 * @return The response message or message payload to the operation 
	 * invocation.
	 * @throws java.rmi.RemoteException - If a fault occurs during 
	 * communication with the service
	 * @throws JAXRPCException - If there is any error in the configuration 
	 * of the Dispatch instance. 
	 */
	public Object invoke(Object msg) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Method invokeAsync
	 * Invoke a service operation asynchronously. The method returns without 
	 * waiting for the response to the operation invocation, the results of 
	 * the operation are obtained by polling the returned Response. The 
	 * client is responsible for ensuring that the msg object when marshalled 
	 * is formed according to the requirements of the protocol binding in use.
	 * 
	 * @param msg - An object that will form the message or payload of the 
	 * message used to invoke the operation.
	 * @return The response message or message payload to the operation 
	 * invocation.
	 * @throws JAXRPCException - If there is any error in the configuration 
	 * of the Dispatch instance.
	 */
	public Response invokeAsync(Object msg) throws JAXRPCException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Method invokeAsync
	 * Invoke a service operation asynchronously. The method returns without 
	 * waiting for the response to the operation invocation, the results of 
	 * the operation are communicated to the client via the passed in handler.
	 * The client is responsible for ensuring that the msg object when 
	 * marshalled is formed according to the requirements of the protocol 
	 * binding in use.
	 * 
	 * @param msg - An object that will form the message or payload of the 
	 * message used to invoke the operation.
	 * @param handler - The handler object that will receive the response to 
	 * the operation invocation.
	 * @return A Future object that may be used to check the status of the 
	 * operation invocation. This object must not be used to try to obtain 
	 * the results of the operation - the object returned from Future.get() 
	 * is implementation dependent and any use of it will result in 
	 * non-portable behaviour.
	 * @throws JAXRPCException - If there is any error in the configuration 
	 * of the Dispatch instance
	 */
	public Future invokeAsync(Object msg, AsyncHandler handler)
			throws JAXRPCException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Method invokeOneWay
	 * Invokes a service operation using the one-way interaction mode. The 
	 * operation invocation is logically non-blocking, subject to the 
	 * capabilities of the underlying protocol, no results are returned. 
	 * When the protocol in use is SOAP/HTTP, this method must block until an 
	 * HTTP response code has been received or an error occurs. The client is 
	 * responsible for ensuring that the msg object when marshalled is formed 
	 * according to the requirements of the protocol binding in use.
	 * 
	 * @param msg - An object that will form the message or payload of the 
	 * message used to invoke the operation.
	 * @throws JAXRPCException - If there is any error in the configuration 
	 * of the Dispatch instance or if an error occurs during the invocation.
	 */
	public void invokeOneWay(Object msg) throws JAXRPCException {
		// TODO Auto-generated method stub

	}

}
