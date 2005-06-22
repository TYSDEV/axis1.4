/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package javax.xml.rpc;

/**
 * Interface AsyncHandler<T>
 * The javax.xml.rpc.AsyncHandler interface is implemented by clients that wish to receive callback notification of the completion of service endpoint operations invoked asynchronously.
 * 
 * @version 1.0
 * @author sunja07
 */
public interface AsyncHandler<T> {

	/**
	 * Method handleResponse
	 * Called when the response to an asynchronous operation is available.
	 * 
	 * @param res - The response to the operation invocation. 
	 */
	void handleResponse(Response<T> res) ;

}
