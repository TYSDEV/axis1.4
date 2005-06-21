/*
 * Created on Jun 21, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package javax.xml.rpc.holders;

/**
 * @author sunja07
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
//This involves 1.5 new feature 'generics', needs a revisit
public final class GenericHolder<T> implements Holder {
	
	public T value;
	
	public GenericHolder() {}
	
	public GenericHolder(T v){}

}
