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

import javax.xml.namespace.QName;

/**
 * AndCompositeAssertion represents either policy or a single policy 
 * alternative. It requires that all its terms are satisfied.
 * 
 * @author Nilupa Bandara     <nilupas@gmail.com>
 * @author Sanka Samaranayake <ssanka@gmail.com>
 */
public class AndCompositeAssertion extends CompositeAssertion implements Assertion {
	
	public AndCompositeAssertion() {
	}
	
    public Assertion intersect(Assertion assertion) {
    	    	
    	CompositeAssertion target = (CompositeAssertion) assertion;
    	
    	target = (CompositeAssertion) ((target.isNormalized()) ? target : target.normalize());
    	CompositeAssertion normalizedMe = (CompositeAssertion) ((isNormalized()) ? this : normalize());
    	
    //	if (!(normalizedMe instanceof AndCompositeAssertion)) {
	//		return normalizedMe.intersect(target);
	//	}
    //	if (!(target instanceof AndCompositeAssertion)) {
    //		return target.intersect(normalizedMe);
    //	}
    	
    	AndCompositeAssertion retANDterm = new AndCompositeAssertion();
    	
    	if (normalizedMe.getOwner() != null && target.getOwner() != null) {
    	
    		if (normalizedMe.isEmpty()) { // empty alternative
				return target;

    		} else if (target.isEmpty()) {
				return normalizedMe;
		
    		} else {
				List primTermsA = ((size() > target.size()) ? normalizedMe.getTerms() : target.getTerms());
				List primTermsB = ((size() > target.size()) ? target.getTerms() : normalizedMe.getTerms());
	
				boolean match = true;
				PrimitiveAssertion primTermA, primTermB;
				QName qnameA, qnameB;
				
				for (int i = 0; i < primTermsA.size(); i++) {
					primTermA = (PrimitiveAssertion) primTermsA.get(i);
					qnameA = primTermA.getName();
					boolean flag = false;
					
					for (int j = 0; j < primTermsB.size(); j++) {
						primTermB = (PrimitiveAssertion) primTermsB.get(j);
						qnameB = primTermB.getName();
						if (qnameA.equals(qnameB)) {
							flag = true;
							break;
						}   					
					}
					if (!flag) {
						match = false;
						break;
					}    				
				}
				
				if (match) { // vocabulary matches
					retANDterm.addTerms(primTermsA);
					retANDterm.addTerms(primTermsB);
				}
				return retANDterm;				
			}
			
    //	} else if (normalizedMe.getOwner() != null && target.getOwner() != null) {
    //		XORCompositeAssertion XORterm;
    		
    // 		if (normalizedMe.getOwner() != null) {

    //			XORterm = (XORCompositeAssertion) target.getTerms().get(0);
    // 			retANDterm.addTerm(XORterm.intersect(normalizedMe));
    			
    // 		} else {
    // 			XORterm = (XORCompositeAssertion) normalizedMe.getTerms().get(0);
    // 			retANDterm.addTerm(XORterm.intersect(target));
    // 		}
    // 		return retANDterm;
    		
    	} else {
    		XORCompositeAssertion argOne = (XORCompositeAssertion) normalizedMe.getTerms().get(0),
								  argTwo = (XORCompositeAssertion) target.getTerms().get(0);
    		retANDterm.addTerm(argOne.intersect(argTwo));
    		return retANDterm;
    	}
    }
    
    public Assertion merge(Assertion assertion) {
    	CompositeAssertion target = (CompositeAssertion) assertion;
    	
    	target = (CompositeAssertion) ((target.isNormalized()) ? target : target.normalize());
    	CompositeAssertion normalizedMe = (CompositeAssertion) ((isNormalized()) ? this : normalize());
    	
    //	if (!(normalizedMe instanceof AndCompositeAssertion)) {
	//		return normalizedMe.intersect(target);
	//	}
    //	if (!(target instanceof AndCompositeAssertion)) {
    //		return target.intersect(normalizedMe);
    //	}
    	
    	AndCompositeAssertion retANDterm = new AndCompositeAssertion();
    	
    	if (normalizedMe.getOwner() != null && target.getOwner() != null) {
    		retANDterm.addTerms(normalizedMe.getTerms());
    		retANDterm.addTerms(target.getTerms());
    		
    	} else {
    		XORCompositeAssertion argOne = (XORCompositeAssertion) normalizedMe.getTerms().get(0),
								  argTwo = (XORCompositeAssertion) target.getTerms().get(0);
    		retANDterm.addTerm(argOne.merge(argTwo));
    		
    	}
    	
    	return retANDterm;
    }
    
    public Assertion normalize() {
    	AndCompositeAssertion retANDterm = new AndCompositeAssertion();
    	XORCompositeAssertion retXORterm = new XORCompositeAssertion();
    	
    	ArrayList ANDtermList = new ArrayList();
    	ArrayList XORtermList = new ArrayList();
    	
    	Iterator myTerms = getTerms().iterator();
    	
    	while (myTerms.hasNext()) {
    		Object term = myTerms.next();
    	
    		if (term instanceof PrimitiveAssertion) {
    			retANDterm.addTerm((Assertion) term);
    		
    		} else if (term instanceof CompositeAssertion) {
    			CompositeAssertion cterm = (CompositeAssertion) term;
    			
    			cterm =((cterm.isNormalized()) ? cterm  :(CompositeAssertion) cterm.normalize());
    			
    			if (cterm instanceof AndCompositeAssertion) {
    				ANDtermList.add(cterm);
    			} else {
    				XORtermList.add(cterm);
    			}
    		}
    	}
    	
    	// processing AND logic
    	
    	if (! ANDtermList.isEmpty()) {
    		Iterator ANDterms = ANDtermList.iterator();
        	
        	while (ANDterms.hasNext()) {
        		CompositeAssertion ANDterm = (CompositeAssertion) ANDterms.next();
        		
                /*
                 * since these CompositeAssertions are normalized, 
                 * child-assertions should be primitives which adds to a single
                 * and logic
                 */ 
        		Iterator primTerms = ANDterm.getTerms().iterator();
        		       		
        		while (primTerms.hasNext()) {
        			retANDterm.addTerm((Assertion) primTerms.next());   			
        		}    		
        	}    		
    	}    	
    	
    	if (XORtermList.size() > 1) {
    		
    		outer : for (int i = 0; i < XORtermList.size(); i++) {
    			inner : for (int j = i; j < XORtermList.size(); j++) {
    				if (i != j) {
    					XORCompositeAssertion XORtermA = (XORCompositeAssertion) XORtermList.get(i);
    					XORCompositeAssertion XORtermB = (XORCompositeAssertion) XORtermList.get(j);
    					
    					// what if xor1 or xor2 is empty
    					if (XORtermA.isEmpty() || XORtermB.isEmpty()) {
    						retXORterm = new XORCompositeAssertion();
    						break outer;
    					}
    					Iterator iterA = XORtermA.getTerms().iterator();
    					
    					while (iterA.hasNext()) {
    						// must be a AND
    						CompositeAssertion ANDtermA = (CompositeAssertion) iterA.next();
    						Iterator iterB = XORtermB.getTerms().iterator();
    						while (iterB.hasNext()) {
    							// must be a AND
    							CompositeAssertion ANDtermB = (CompositeAssertion) iterB.next();
    							
    							AndCompositeAssertion ANDterm = new AndCompositeAssertion();
    							ANDterm.addTerms(ANDtermA.getTerms());
    							ANDterm.addTerms(ANDtermB.getTerms());
    							retXORterm.addTerm(ANDterm);
    						}
    					}
    					
    				}
    			}
    		}
    	
    	} else if (XORtermList.size() == 1) {
    		
    		CompositeAssertion XORterm = (CompositeAssertion) XORtermList.get(0);
    		retXORterm.addTerms(XORterm.getTerms());
    	}
    	    	
    	if (!XORtermList.isEmpty()) {
    		
    		if (!retANDterm.isEmpty()) {
    			
    			// get list of primitive assertions form result (AndCompositeAssertion)
    			List primTerms = retANDterm.getTerms();
    			
    			// these terms should be AndCompositeAssertions
    			Iterator ANDterms = retXORterm.getTerms().iterator();
    			
    			while (ANDterms.hasNext()) {
    				CompositeAssertion ANDterm = (CompositeAssertion) ANDterms.next();
    				ANDterm.addTerms(primTerms);
    			}
    		}
    		
    		if (getOwner() == null) {
    	
    			// self is a root .. hence we wrap the XORterm in an ANDterm
    			AndCompositeAssertion ANDterm = new AndCompositeAssertion();
    			ANDterm.addTerm(retXORterm);
         		ANDterm.isNormalized(true);
         		return ANDterm;
    		} 

    	//	if (xorLogic.isEmpty()) {
    			// xor logic is empty and self is not the root
    			// hence it should proceed as an empty Alternative
        //		AndCompositeAssertion andlogic = new AndCompositeAssertion();
        //		andLogic.isNormalized(true);
        //		return andLogic;        		
    	//	}

    		retXORterm.isNormalized(true);
    		return retXORterm;
    		
    	} else {
    		
    		if (getOwner() == null) {
				
    			// self is a root .. hence we should wrap the ANDterm in a XORterm and then
    			// in an ANDterm
    			AndCompositeAssertion ANDterm = new AndCompositeAssertion();
				XORCompositeAssertion XORterm = new XORCompositeAssertion();
				XORterm.addTerm(retANDterm);
				ANDterm.addTerm(XORterm);
				ANDterm.isNormalized(true);
				return ANDterm;		
    		}  	   		
    		retANDterm.isNormalized(true);
    		return retANDterm;
    	}
    }
}
