package org.apache.axis.om.builder;

import junit.framework.TestCase;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.ByteArrayInputStream;

import org.apache.axis.om.OMXMLParserWrapper;
import org.apache.axis.om.OMFactory;
import org.apache.axis.om.OMElement;
import org.apache.axis.om.SOAPEnvelope;
import org.apache.axis.om.builder.impl.EnvelopeBuilderExtension;
import org.apache.axis.om.impl.llom.factory.OMXMLBuilderFactory;

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
public class ExtendedBuilderTest extends TestCase{
     private String XMLText = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"\n" +
             "                  xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"\n" +
             "                  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
             "                  xmlns:wsa=\"http://schemas.xmlsoap.org/ws/2004/03/addressing\">\n" +
             "    <soapenv:Header>\n" +
             "        <wsa:MessageID soapenv:mustUnderstand=\"0\">\n" +
             "            uuid:920C5190-0B8F-11D9-8CED-F22EDEEBF7E5</wsa:MessageID>\n" +
             "        <wsa:To soapenv:mustUnderstand=\"0\">\n" +
             "            http://localhost:8081/axis/services/BankPort</wsa:To>\n" +
             "        <wsa:From soapenv:mustUnderstand=\"0\">\n" +
             "            <Address xmlns=\"http://schemas.xmlsoap.org/ws/2004/03/addressing\">\n" +
             "                http://schemas.xmlsoap.org/ws/2004/03/addressing/role/anonymous\n" +
             "            </Address>\n" +
             "        </wsa:From>\n" +
             "    </soapenv:Header>\n" +
             "    <soapenv:Body>\n" +
             "        <axis2:echoVoid xmlns:axis2=\"http://ws.apache.org/axis2\">\n" +
             "        </axis2:echoVoid>\n" +
             "    </soapenv:Body>\n" +
             "</soapenv:Envelope>";

    private XMLStreamReader parser1;
    private ExtendedBuilder builder;

    protected void setUp() throws Exception {
        parser1 = XMLInputFactory.newInstance().createXMLStreamReader(new ByteArrayInputStream(XMLText.getBytes()));
        builder = new ExtendedBuilder(parser1);
        ExtensionRegistry.getInstance().clearExtensions();

    }

     public void testExtendedBuilderForEnvelop(){
         //register the SOAP extension
        ExtensionRegistry.getInstance().registerExtension(new EnvelopeBuilderExtension());

        OMElement elt =  builder.getDocumentElement();
        assertTrue(elt instanceof SOAPEnvelope);


     }
      public void testExtendedBuilderForNoEnvelope(){

        OMElement elt =  builder.getDocumentElement();
        assertFalse(elt instanceof SOAPEnvelope);


     }
}
