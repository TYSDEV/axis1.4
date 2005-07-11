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
package org.apache.axis2.om;

import org.apache.axis2.om.impl.llom.OMNavigator;
import org.apache.axis2.soap.SOAPEnvelope;
import org.apache.axis2.soap.SOAPFactory;
import org.apache.axis2.soap.impl.llom.builder.StAXSOAPModelBuilder;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

public class OMNavigatorTest extends AbstractTestCase {
    private SOAPEnvelope envelope = null;
    private OMXMLParserWrapper builder;
    private File tempFile;
    private OMOutput omOutput;

    public OMNavigatorTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
        XMLStreamReader xmlStreamReader = XMLInputFactory.newInstance().
                createXMLStreamReader(
                        new FileReader(
                                getTestResourceFile("soap/soapmessage1.xml")));
        SOAPFactory factory = OMAbstractFactory.getSOAP11Factory();
        builder = new StAXSOAPModelBuilder(xmlStreamReader);
        envelope = (SOAPEnvelope) builder.getDocumentElement();
        tempFile = File.createTempFile("temp", "xml");
        omOutput =
                new OMOutput(
                        XMLOutputFactory.newInstance().createXMLStreamWriter(
                                new FileOutputStream(tempFile)));
        //        writer = XMLOutputFactory.newInstance().createXMLStreamWriter(System.out);


    }

    public void testnavigatorFullyBuilt() throws Exception {
        assertNotNull(envelope);
        //dump the out put to a  temporary file
        envelope.serializeWithCache(omOutput);

        //now the OM is fully created test the navigation
        OMNavigator navigator = new OMNavigator(envelope);
        OMNode node = null;
        while (navigator.isNavigable()) {
            node = navigator.next();
            assertNotNull(node);
        }
    }

    public void testnavigatorHalfBuilt() {
        assertNotNull(envelope);
        //now the OM is not fully created. Try to navigate it
        OMNavigator navigator = new OMNavigator(envelope);
        OMNode node = null;
        while (navigator.isNavigable()) {
            node = navigator.next();
            assertNotNull(node);
        }
    }

    public void testnavigatorHalfBuiltStep() {
        assertNotNull(envelope);

        //now the OM is not fully created
        OMNavigator navigator = new OMNavigator(envelope);
        OMNode node = null;
        while (!navigator.isCompleted()) {
            if (navigator.isNavigable()) {
                node = navigator.next();
            } else {
                builder.next();
                navigator.step();
                node = navigator.next();
            }
            assertNotNull(node);

        }

    }

    protected void tearDown() throws Exception {
        omOutput.flush();
        tempFile.delete();
    }

}
