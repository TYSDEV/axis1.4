package org.apache.axis.jaxrpc.handler;

import java.util.HashMap;
import java.util.Iterator;

import javax.xml.rpc.handler.MessageContext;

public class MessageContextImpl implements MessageContext {
	
	/**
	 * HashMap to hold peroperties, key is the propertyName, and value is a
	 * class with keyValue and scopeValue
	 */
	protected HashMap<String, PropVals> properties;
	
	protected class PropVals{
		Object value;
		Scope propScope;
		
		PropVals(Object value, Scope scope){
			this.value = value;
			this.propScope = scope;
		}
	}
	
	public MessageContextImpl(){
		properties = new HashMap<String, PropVals>();
	}

	public void setPropertyScope(String name, Scope scope)
			throws IllegalArgumentException {
		
		PropVals property = properties.get(name);
		if(property != null)
			property.propScope = scope;

	}

	public Scope getPropertyScope(String name) throws IllegalArgumentException {
		
		PropVals property = properties.get(name);
		if(property != null)
			return property.propScope;
		return null;
	}

	public void setProperty(String name, Object value)
			throws IllegalArgumentException, UnsupportedOperationException {
		
		PropVals property = properties.get(name);
		if(property != null)
			property.value = value;
		else
			properties.put(name, new PropVals(value, Scope.HANDLER)); //Using HANDLER as the default scope
										// not sure which one should be default or if scope can be null

	}

	public void setProperty(String name, Object value, Scope scope)
			throws IllegalArgumentException, UnsupportedOperationException {
		
		PropVals property = properties.get(name);
		if(property != null){
			property.value = value;
			property.propScope = scope;
		} else
			properties.put(name, new PropVals(value, scope));

	}

	public Object getProperty(String name) throws IllegalArgumentException {
		
		PropVals property = properties.get(name);
		if(property != null)
			return property.value;
		return null;
	}

	public void removeProperty(String name) throws IllegalArgumentException {
		
		properties.remove(name);
	}

	public boolean containsProperty(String name) {
		
		PropVals property = properties.get(name);
		if(property != null)
			return true;
		else
			return false;
	}

	public Iterator getPropertyNames() {
		
		return properties.keySet().iterator();
	}

}
