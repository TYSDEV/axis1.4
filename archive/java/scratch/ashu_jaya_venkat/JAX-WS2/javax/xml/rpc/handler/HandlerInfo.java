/*
 * Created on Jun 21, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package javax.xml.rpc.handler;

import java.io.Serializable;

/**
 * public class HandlerInfo
 * extends java.lang.Object
 * implements java.io.Serializable
 * <p>
 * The javax.xml.rpc.handler.HandlerInfo represents information about a handler in the HandlerChain. A HandlerInfo
 * instance is passed in the Handler.init method to initialize a Handler instance.
 * @version 1.0
 * @author shaas02
 * @see <code>HandlerChain</code>, <code>SerializedForm</code>
 */
public class HandlerInfo implements Serializable {
	
	/**
	 * Default constructor
	 */
	HandlerInfo(){
		
	}

	/**
	 * Constructor for HandlerInfo
	 * @param handlerClass - Java Class for the Handler
	 * @param config - Handler Configuration as a java.util.Map
	 * @param headers -  - QNames for the header blocks processed by this Handler. QName is the qualified name of the
	 * outermost element of a header block
	 */
	HandlerInfo(java.lang.Class handlerClass, java.util.Map config,
			javax.xml.namespace.QName[] headers){
		
	}
	
	/**
	 * Sets the Handler class
	 * @param handlerClass - Class for the Handler
	 */
	public void setHandlerClass(java.lang.Class handlerClass){
		
	}
	
	/**
	 * Gets the Handler class
	 * @return Returns null if no Handler class has been set; otherwise the set handler class
	 */
	public java.lang.Class getHandlerClass(){
		return null;
	}
	
	/**
	 * Sets the Handler configuration as java.util.Map
	 * @param config - Configuration map
	 */
	public void setHandlerConfig(java.util.Map config){
		
	}
	
	/**
	 * Gets the Handler configuration
	 * @return Returns empty Map if no configuration map has been set; otherwise returns the set configuration map
	 */
	public java.util.Map getHandlerConfig(){
		return null;
	}
	
	/**
	 * Sets the header blocks processed by this Handler.
	 * @param headers - QNames of the header blocks. QName is the qualified name of the outermost element of the SOAP
	 * header block
	 */
	public void setHeaders(javax.xml.namespace.QName[] headers){
		
	}
	
	/**
	 * Gets the header blocks processed by this Handler.
	 * @return Array of QNames for the header blocks. Returns null if no header blocks have been set using the setHeaders
	 * method.
	 */
	public javax.xml.namespace.QName[] getHeaders(){
		return null;
	}
}
