/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package javax.xml.rpc;

import java.util.concurrent.Future;

//TODO: Needs a revisit, b'coz generics are involved.
/**
 * Interface Response<T>
 * 
 * @author sunja07
 */
public interface Response<T> extends Future<T> {
	
	/**
	 * Method getContext
	 * Gets the contained response context.
	 * 
	 * @return The contained response context. May be null if a response is not yet available.
	 */
	JAXRPCContext getContext();
}
