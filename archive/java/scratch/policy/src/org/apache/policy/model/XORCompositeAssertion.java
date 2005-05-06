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

import java.util.Iterator;

/**
 * XORCompositeAssertion represents a bunch of policy alternatives. It requires
 * that exactly one of its terms (policy alternative) is statisfied.
 * 
 * @author Nilupa Bandara     <nilupas@gmail.com>
 * @author Sanka Samaranayake <ssanka@gmail.com>
 */
public class XORCompositeAssertion extends CompositeAssertion implements Assertion  {
	
	public XORCompositeAssertion() {
	}
	
	public Assertion normalize() {
		XORCompositeAssertion xorLogic = new XORCompositeAssertion();
		Iterator terms = getTerms().iterator();
		
		while (terms.hasNext()) {
			Assertion term = (Assertion) terms.next();
			
			if (term instanceof PrimitiveAssertion) { // just wrap it in an AND
													  // logic and add 
				AndCompositeAssertion wrapper = new AndCompositeAssertion();
				wrapper.addTerm(term);
				xorLogic.addTerm(wrapper);
			} else {
				// must be a composite assertion
				CompositeAssertion cterm = (CompositeAssertion) term;
				cterm =((cterm.isNormalized()) ? cterm  :(CompositeAssertion) cterm.normalize());
				
				if (cterm instanceof XORCompositeAssertion) {
					// just adds the child-terms to super
					xorLogic.addTerms(cterm.getTerms());
				} else {
					// must be an AndCompositeAssertion with primitives
					xorLogic.addTerm(cterm);
				}
			}
		}
		xorLogic.isNormalized(true);
		return xorLogic;
	}

	public Assertion intersect(Assertion assertion) {
		if (!(assertion instanceof CompositeAssertion)) {
			throw new IllegalArgumentException("argument can't be a primitive" +
					" assertion");
		}
		
		XORCompositeAssertion retXORterm = new XORCompositeAssertion();
		
		CompositeAssertion target = (CompositeAssertion) assertion;
		CompositeAssertion normalizedMe = (CompositeAssertion) ((isNormalized()) ? this : normalize());
		target = (CompositeAssertion) ((target.isNormalized()) ? target : target.normalize());
		
		Iterator children = normalizedMe.getTerms().iterator();
		
		while (children.hasNext()) {
			CompositeAssertion term = (CompositeAssertion) children.next();
			CompositeAssertion iterm;
			
			if (target instanceof AndCompositeAssertion) {
				iterm = (CompositeAssertion) term.intersect(target);
				
				if (!iterm.isEmpty()) {
					retXORterm.addTerm(iterm);
				}				
				
			} else if (target instanceof XORCompositeAssertion) {
				Iterator ANDterms = target.getTerms().iterator();
				Assertion ANDterm;
				
				while (ANDterms.hasNext()) {
					ANDterm = (Assertion) ANDterms.next();
					iterm = (CompositeAssertion) term.intersect(ANDterm);
					
					if (!iterm.isEmpty()) {
						retXORterm.addTerm(iterm);
					}
				}
			}
		}
		
		return retXORterm;
	}

	public Assertion merge(Assertion assertion) {
		
		if (!(assertion instanceof CompositeAssertion)) {
			throw new IllegalArgumentException("argument can't be a primitive" +
					" assertion");
		}
		
		XORCompositeAssertion retXORterm = new XORCompositeAssertion();
		CompositeAssertion target = (CompositeAssertion) assertion;
		
		target = (CompositeAssertion) ((target.isNormalized()) ? target : target.normalize());
		CompositeAssertion normalizedMe = (CompositeAssertion) ((isNormalized()) ? this : normalize());
				
		Iterator myTerms = normalizedMe.getTerms().iterator();
		while (myTerms.hasNext()) {
			CompositeAssertion term = (CompositeAssertion) myTerms.next();
			
			if (target instanceof AndCompositeAssertion) {
				retXORterm.addTerm(term.merge(target));
								
			} else if (target instanceof XORCompositeAssertion) {
				Iterator ANDterms = target.getTerms().iterator();
				Assertion ANDterm;
				
				while (ANDterms.hasNext()) {
					ANDterm = (Assertion) ANDterms.next();
					retXORterm.addTerm(term.merge(ANDterm));
				}
			}
		}
		
		return retXORterm;
	}
}
