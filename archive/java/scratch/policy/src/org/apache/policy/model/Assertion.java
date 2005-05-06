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

/**
 * 
 * @author Nilupa Bandara <nilupas@gmail.com>
 * 
 */
public interface Assertion {
	/** Defines the short value for Primitive Assertions */
	public static final short PRIMITIVE_TYPE = 0x1;
	
	/** Defines the short value for ANDCompositeAssertion */                           
    public static final short COMPOSITE_AND_TYPE = 0x2;
    
    /** Defines the short value for XORCompositeAssertion*/
    public static final short COMPOSITE_XOR_TYPE = 0x3;
    
    /**
     * 
     * @return
     */
    public Assertion normalize() throws UnsupportedOperationException;
    
    /**
     * 
     * @param assertion
     * @return
     */
    public Assertion intersect(Assertion assertion) throws UnsupportedOperationException;
    
    /**
     * 
     * @param assertion
     * @return
     */
    public Assertion merge(Assertion assertion) throws UnsupportedOperationException;
    
    /**
     * 
     * @return
     */
    public boolean hasOwner();
    
    /**
     * 
     * @return
     */
    public Assertion getOwner();
    
    /**
     * 
     * @param parent
     */
    public void setOwner(Assertion parent);
}
