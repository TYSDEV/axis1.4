/*
 * Copyright 2001-2004 The Apache Software Foundation.
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
package org.apache.policy.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author Nilupa Bandara     <nilupas@gmail.com>
 * @author Sanka Samaranayake <ssanka@gmail.com>
 */
public abstract class CompositeAssertion implements Assertion {
	
	/** */
	protected boolean flag = false;
	
	/** */
    private List list = new ArrayList();
    
    /** */
    private Assertion owner = null;
        
    public void addTerm(Assertion assertion){
    	assertion.setOwner(this);
        list.add(assertion);
    }
    
    public void addTerms(List assertions) {
    	Iterator items = assertions.iterator();
    	
    	while (items.hasNext()) {
    		Object value = items.next();
    	
    		if (!(value instanceof Assertion)) {
    			throw new IllegalArgumentException("argument contains a " +
    					"non-assertion");
    		}
    		addTerm((Assertion) value);
    	}
    }
    
    public List getTerms() {
    	return list;
    }
    
    public boolean isEmpty() {
    	return list.size() == 0;
    }
    
    public boolean remove(Assertion assertion) {
        return list.remove(assertion);
    }
    
    public int size() {
        return list.size();
    }
    
    public boolean hasOwner() {
    	return owner != null;
    }
       
    public Assertion getOwner() {
    	return owner;
    }
    
    public void setOwner(Assertion parent) {
    	this.owner = parent;
    }
    
    protected boolean isNormalized() {
    	return flag;
    }
    
    protected void isNormalized(boolean value) {
    	Iterator children = getTerms().iterator();
    	
    	while (children.hasNext()) {
    		Object child = children.next();
    		if (child instanceof CompositeAssertion) {
    			((CompositeAssertion) child).isNormalized(true);
    		}
    	}
    	flag = value;
    }
}
