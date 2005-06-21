/*
 * Created on Jun 21, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package javax.xml.rpc.security;

/**
 * Interface SecurityConfiguration
 * The interface SecurityConfiguration abstracts the message security configuration.
 * 
 * @version 1.0
 * @author sunja07
 */
public interface SecurityConfiguration {
	
	//TODO: This involves generics, needs a thorough revisit.
	/**
	 * Abstract security features. 
	 * 	Integrity 
	 * 		Provide assurance that the data received by a recipient is the same as the data sent by the originator 
	 * 	Confidentiality
	 * 		Protect data from being read by anyone except the intended recipient 
	 * 	Authentication 
	 * 		Establish or constrain the identity of the source and/or recipient of a message 
	 */
	public static enum SecurityFeature extends java.lang.Enum <SecurityConfiguration.SecurityFeature> {
		
		public static final SecurityConfiguration.SecurityFeature CONFIDENTIALITY=null;
		
		public static final SecurityConfiguration.SecurityFeature INTEGRITY = null;
		
		public static final SecurityConfiguration.SecurityFeature AUTHENTICATION = null;
		
		/**
		 * Method values
		 * Returns an array containing the constants of this enum type, in the order they're declared. This method may be used to iterate over the constants as follows:
		 * <code>
		 * 		for(SecurityConfiguration.SecurityFeature c : SecurityConfiguration.SecurityFeature.values())
		 * 			System.out.println(c);
		 * </code>
		 * @return an array containing the constants of this enum type, in the order they're declared
		 */
		public static final SecurityConfiguration.SecurityFeature[] values() {
			
		}
		
		/**
		 * Method valueOf
		 * Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.)
		 * @param name the name of the enum constant to be returned.
		 * @return the enum constant with the specified name 
		 * @throws java.lang.IllegalArgumentException if this enum type has no constant with the specified name
		 */
		public static SecurityConfiguration.SecurityFeature valueOf(java.lang.String name) throws java.lang.IllegalArgumentException {
			
		}
	}
	
	/**
	 * Method setOutboundConfigId
	 * @param configId
	 */
	void setOutboundConfigId(java.lang.String configId);
	
	/**
	 * Method getOutboundConfigId
	 * @return
	 */
	java.lang.String getOutboundConfigId();
	
	/**
	 * Method setInboundConfigId
	 * @param configId
	 */
	void setInboundConfigId(java.lang.String configId);
	
	/**
	 * Method getInboundConfigId
	 * @return
	 */
	java.lang.String getInboundConfigId();
	
	/**
	 * Method setInboundFeatures
	 * 
	 */
	void setInboundFeatures(SecurityConfiguration.SecurityFeature... features);
	
	/**
	 * Method getInbound
	 * @return
	 */
	SecurityConfiguration.SecurityFeature[] getInbound();
	
	/**
	 * Method setOutboundFeatures
	 * 
	 */
	void setOutboundFeatures(SecurityConfiguration.SecurityFeature... features);
	
	/**
	 * Method getOutbound
	 * @return
	 */
	SecurityConfiguration.SecurityFeature[] getOutbound();
	
	/**
	 * Method setCallbackHandler
	 * @param callbackHandler
	 */
	void setCallbackHandler(javax.security.auth.callback.CallbackHandler callbackHandler);
	
	/**
	 * Method getCallbackHandler 
	 * @return
	 */
	javax.security.auth.callback.CallbackHandler getCallbackHandler();

}
