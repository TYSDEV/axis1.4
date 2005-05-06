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

import javax.xml.namespace.QName;

import org.apache.axis.om.OMElement;

/**
 * 
 * @author Nilupa Bandara <nilupas@gmail.com>
 */
public class PrimitiveAssertion implements Assertion {
	private Assertion owner = null;
	private QName qname;
    private OMElement value;
    
        public PrimitiveAssertion(OMElement value) {
        this.value = value;
        this.qname = new QName(value.getNamespace().getName() , value.getLocalName());
    }
    
    public QName getName() {
        return qname;
    }
    
    public OMElement getValue() {
        return value;
    }
    
    public Assertion intersect(Assertion assertion) {
		throw new UnsupportedOperationException("intersect is not supported " +
				"in primitive assertions");
	}
	
	public Assertion merge(Assertion assertion) {
		throw new UnsupportedOperationException("merge is not supported in " +
				"primivite assertions");
	}
	
	public Assertion normalize() {
		throw new UnsupportedOperationException("normalize is not supported " +
				"in primitive assertions");
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
}
