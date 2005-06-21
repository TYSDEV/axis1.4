/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package javax.xml.rpc;

import java.util.concurrent.Future<T>;

/**
 * @author sunja07
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
//TODO: Needs a revisit, b'coz generics are involved.
public interface Response extends java.util.concurrent.Future<T> {
	
	JAXRPCContext getContext();
}
