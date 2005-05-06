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

package org.apache.policy.parser;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.axis.om.OMElement;
import org.apache.axis.om.OMNode;
import org.apache.axis.om.OMXMLParserWrapper;
import org.apache.axis.om.OMAbstractFactory;
import org.apache.axis.om.impl.llom.factory.OMXMLBuilderFactory;
import org.apache.policy.model.AndCompositeAssertion;
import org.apache.policy.model.Assertion;
import org.apache.policy.model.CompositeAssertion;
import org.apache.policy.model.PrimitiveAssertion;
import org.apache.policy.model.XORCompositeAssertion;

/**
 * WSPolicyParser provides methods to build a Policy Model form an InputStream
 * and to write a Policy Model to an OutputStream.
 *  
 * @author Nilupa Bandara    <nilupas@gmail.com>
 */
public class WSPolicyParser {
	public static WSPolicyParser self = null;
	
	private WSPolicyParser() {
	}
	
	public static WSPolicyParser getInstance() {
		if (self == null) {
			self = new WSPolicyParser();
		}
		return self;
	}
	
	public Assertion getModel(InputStream in) {
		Assertion model = null;

		try {
			XMLStreamReader xmlr = 
					XMLInputFactory.newInstance().createXMLStreamReader(in);
			OMXMLParserWrapper builder = 
					OMXMLBuilderFactory.createStAXOMBuilder(OMAbstractFactory.getOMFactory(),xmlr);
			OMElement root = builder.getDocumentElement();
			model = getCompositeAssertion(root);
			
		} catch (XMLStreamException ex) {
			System.err.println(ex.getMessage()); // should I wrap it and throw a new PolicyException ??
		}
		return model;
	}
	
	public Assertion getCompositeAssertion(OMElement value) {
		CompositeAssertion assertion = null;
		
		if (value.getLocalName().equals(WSPConstants.WS_POLICY)) {
			assertion = new AndCompositeAssertion();
		} else if  (value.getLocalName().equals(WSPConstants.AND_COMPOSITE_ASSERTION)) {
			assertion = new AndCompositeAssertion();
		} else  if (value.getLocalName().equals(WSPConstants.XOR_COMPOSITE_ASSERTION)) {
			assertion = new XORCompositeAssertion();
		} else {
			throw new IllegalArgumentException("cannot resolve the argument to" +
					"a composite assertion");
		}
		
		Iterator children = value.getChildren();
		
		while (children.hasNext()){
			OMNode node = (OMNode) children.next();
			
			if (node instanceof OMElement){
				OMElement ome = (OMElement) node;
				
				assertion.addTerm(
						(isCompositeAssertion(ome))
						? getCompositeAssertion(ome)
						: getPrimitiveAssertion(ome)
						);
			}
		}
		return assertion;
	}
	
	public Assertion getPrimitiveAssertion(OMElement value) {
		return new PrimitiveAssertion(value);		
	}
	
	public static boolean isCompositeAssertion(OMElement value) {
		
		return (value.getNamespace().getName().equals(WSPConstants.WS_POLICY_NAMESPACE_URI))
				&&  (value.getLocalName().equals(WSPConstants.WS_POLICY)
				||  value.getLocalName().equals(WSPConstants.AND_COMPOSITE_ASSERTION)
				||  value.getLocalName().equals(WSPConstants.XOR_COMPOSITE_ASSERTION));
	}
	
	
	/*
	 * 
	 */
	public void printModel(CompositeAssertion model, OutputStream out) {
	}	
}
