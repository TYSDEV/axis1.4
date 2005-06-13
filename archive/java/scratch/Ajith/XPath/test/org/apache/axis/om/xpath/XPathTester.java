package org.apache.axis.om.xpath;

import junit.framework.TestCase;
import org.apache.axis.om.OMAbstractFactory;
import org.apache.axis.om.OMFactory;
import org.apache.axis.om.OMElement;
import org.apache.axis.om.OMNamespace;
import org.jaxen.XPath;

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
 *
 * 
 */
public class XPathTester extends TestCase{
    private static final String CHILD1_TEXT = "I am child 1";
    private static final String CHILD2_TEXT = "I am child 2";

    public XPathTester(String s) {
        super(s);
    }

    private OMElement rootElement =null;

    protected void setUp() throws Exception {
        super.setUp();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public void testXPathWithoutNs() throws Exception{
        //create an OM tree
        OMFactory fac = OMAbstractFactory.getOMFactory();
        OMNamespace ns = fac.createOMNamespace("",null);
        OMElement rootElement = fac.createOMElement("root",ns);

        OMElement child1 = fac.createOMElement("child1",ns);
        child1.setText(CHILD1_TEXT);
        OMElement child2 = fac.createOMElement("child2",ns);
        child2.setText(CHILD2_TEXT);
        OMElement child11 = fac.createOMElement("child11",ns);
        child11.setText(CHILD1_TEXT);
        OMElement child21 = fac.createOMElement("child21",ns);
        child21.setText(CHILD2_TEXT);

        child1.addChild(child11);
        child2.addChild(child21);

        rootElement.addChild(child1);
        rootElement.addChild(child2);


        XPath xpath = new OMXPath("child1");
        Object selectedNode = xpath.selectSingleNode(rootElement);
        assertNotNull(selectedNode) ;
        assertEquals(((OMElement)selectedNode).getText(),CHILD1_TEXT);


        xpath = new OMXPath("child2/child21");
        selectedNode = xpath.selectSingleNode(rootElement);
        assertNotNull(selectedNode) ;
        assertEquals(((OMElement)selectedNode).getText(),CHILD2_TEXT);




    }

//    public void testXPathWithNs() throws Exception{
//        //create an OM tree
//        OMFactory fac = OMAbstractFactory.getOMFactory();
//        OMNamespace ns = fac.createOMNamespace("urn:myNs","ns1");
//        OMElement rootElement = fac.createOMElement("root",ns);
//
//        OMElement child1 = fac.createOMElement("child1",ns);
//        child1.setText(CHILD1_TEXT);
//        OMElement child2 = fac.createOMElement("child2",ns);
//        child2.setText(CHILD2_TEXT);
//
//        rootElement.addChild(child1);
//        rootElement.addChild(child2);
//
//        //create the Xpath
//        XPath xpath = new OMXPath("child1");
//        Object selectedNode = xpath.selectSingleNode(rootElement);
//        assertNotNull(selectedNode) ;
//        assertEquals(((OMElement)selectedNode).getText(),CHILD1_TEXT);
//
//    }

}
