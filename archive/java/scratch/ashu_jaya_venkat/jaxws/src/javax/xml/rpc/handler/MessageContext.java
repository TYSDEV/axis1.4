/*
 * Copyright 2004,2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package javax.xml.rpc.handler;

/**
 * The interface MessageContext abstracts the message context that is processed by a handler in the handle  method.
 * <p>
 * The MessageContext interface provides methods to manage a property set. MessageContext properties enable handlers in a
 * handler chain to share processing related state.
 * @version 1.0
 * @author shaas02
 * @see <code>Handler</code>
 */
public interface MessageContext {
	
	static class Scope {
		
	}
	
	/**
	 * Standard property: message direction, true for outbound messages, false for inbound.
	 * <p>
	 * Type: boolean
	 */
	static final java.lang.String MESSAGE_OUTBOUND_PROPERTY = null;
	
	/**
	 * Standard property: security configuration.
	 * <p>
	 * Type: javax.xml.rpc.security.SecurityConfiguration
	 */
	static final java.lang.String MESSAGE_SECURITY_CONFIGURATION = null;
	
	/**
	 * Sets the scope of a property.
	 * @param name - Name of the property associated with the MessageContext
	 * @param scope - Desired scope of the property
	 * @throws java.lang.IllegalArgumentException - if an illegal property name is specified
	 */
	void setPropertyScope(java.lang.String name,
			 MessageContext.Scope scope) throws java.lang.IllegalArgumentException;
	
	/**
	 * Gets the scope of a property.
	 * @param name - Name of the property
	 * @return Scope of the property
	 * @throws java.lang.IllegalArgumentException - if a non-existant property name is specified
	 */
	MessageContext.Scope getPropertyScope(java.lang.String name)throws java.lang.IllegalArgumentException;
	
	/**
	 * Sets the name and value of a property associated with the MessageContext. If the MessageContext  contains a value of
	 * the same property, the old value is replaced but the properties scope is unchanged. The scope of the property defaults to
	 * HANDLER.
	 * @param name - Name of the property associated with the MessageContext
	 * @param value - Value of the property
	 * @throws java.lang.IllegalArgumentException - If some aspect of the property prevents it from being stored in the context
	 * @throws java.lang.UnsupportedOperationException - If this method is not supported.
	 */
	void setProperty(java.lang.String name,
			java.lang.Object value) throws java.lang.IllegalArgumentException,
			java.lang.UnsupportedOperationException;
	
	/**
	 * Sets the name, value and scope of a property associated with the MessageContext. If the MessageContext  contains a
	 * value of the same property, the old value is replaced.
	 * @param name - Name of the property associated with the MessageContext
	 * @param value - Value of the property
	 * @param scope - Desired scope of the property
	 * @throws java.lang.IllegalArgumentException - If some aspect of the property prevents it from being stored in the context
	 * @throws java.lang.UnsupportedOperationException - If this method is not supported.
	 */
	void setProperty(java.lang.String name,
			java.lang.Object value,
			MessageContext.Scope scope) throws java.lang.IllegalArgumentException,
			java.lang.UnsupportedOperationException;
	
	/**
	 * Gets the value of a specific property from the MessageContext
	 * @param name - Name of the property whose value is to be retrieved
	 * @return Value of the property.
	 * @throws java.lang.IllegalArgumentException - if an illegal property name is specified
	 */
	java.lang.Object getProperty(java.lang.String name) throws java.lang.IllegalArgumentException;
	
	/**
	 * Removes a property (name-value pair) from the MessageContext
	 * @param name - Name of the property to be removed
	 * @throws java.lang.IllegalArgumentException - if an illegal property name is specified
	 */
	void removeProperty(java.lang.String name) throws java.lang.IllegalArgumentException;
	
	/**
	 * Returns true if the MessageContext contains a property with the specified name.
	 * @param name  - Name of the property whose presense is to be tested
	 * @return Returns true if the MessageContext contains the property; otherwise false
	 */
	boolean containsProperty(java.lang.String name);
	
	/**
	 * Returns an Iterator view of the names of the properties in this MessageContext
	 * @return Iterator for the property names
	 */
	java.util.Iterator getPropertyNames();

}
