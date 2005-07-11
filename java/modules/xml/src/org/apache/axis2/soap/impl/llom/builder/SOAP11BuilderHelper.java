package org.apache.axis2.soap.impl.llom.builder;

import org.apache.axis2.om.OMAbstractFactory;
import org.apache.axis2.om.OMElement;
import org.apache.axis2.om.OMText;
import org.apache.axis2.om.impl.llom.exception.OMBuilderException;
import org.apache.axis2.soap.SOAPFactory;
import org.apache.axis2.soap.SOAPFault;
import org.apache.axis2.soap.SOAPFaultCode;
import org.apache.axis2.soap.SOAPFaultReason;
import org.apache.axis2.soap.SOAPFaultText;
import org.apache.axis2.soap.SOAPFaultValue;
import org.apache.axis2.soap.impl.llom.SOAPProcessingException;
import org.apache.axis2.soap.impl.llom.soap11.SOAP11Constants;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

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
 * author : Eran Chinthaka (chinthaka@apache.org)
 */

public class SOAP11BuilderHelper extends SOAPBuilderHelper implements SOAP11Constants {
    private SOAPFactory factory;
    private boolean faultcodePresent = false;
    private boolean faultstringPresent = false;

    private OMElement lastProcessedSOAPElement;

    public SOAP11BuilderHelper(StAXSOAPModelBuilder builder) {
        super(builder);
        factory = OMAbstractFactory.getSOAP11Factory();
    }

    public OMElement handleEvent(XMLStreamReader parser,
                                 OMElement parent,
                                 int elementLevel) throws SOAPProcessingException {
        this.parser = parser;

        OMElement element = null;
        String localName = parser.getLocalName();

        if (elementLevel == 4) {

            if (SOAP_FAULT_CODE_LOCAL_NAME.equals(localName)) {
                if (faultstringPresent) {
                    builder.setBooleanProcessingMandatoryFaultElements(false);
                }
                SOAPFaultCode code = factory.createSOAPFaultCode(
                        (SOAPFault) parent, builder);
                SOAPFaultValue value = factory.createSOAPFaultValue(code);
                processNamespaceData(code, true);
                processAttributes(code);

                processText(parser, value);
                code.setComplete(true);
                element = code;
                builder.elementLevel--;

                faultcodePresent = true;
            } else if (SOAP_FAULT_STRING_LOCAL_NAME.equals(localName)) {
                if (faultcodePresent) {
                    builder.setBooleanProcessingMandatoryFaultElements(false);
                }


                SOAPFaultReason reason = factory.createSOAPFaultReason(
                        (SOAPFault) parent, builder);
                SOAPFaultText faultText = factory.createSOAPFaultText(reason);
                processNamespaceData(reason, true);
                processAttributes(reason);

                processText(parser, faultText);
                reason.setComplete(true);
                element = reason;
                builder.elementLevel--;


                faultstringPresent = true;
            } else if (SOAP_FAULT_ACTOR_LOCAL_NAME.equals(localName)) {
                element =
                        factory.createSOAPFaultRole((SOAPFault) parent,
                                builder);
                processNamespaceData(element, true);
                processAttributes(element);
            } else if (SOAP_FAULT_DETAIL_LOCAL_NAME.equals(localName)) {
                element =
                        factory.createSOAPFaultDetail((SOAPFault) parent,
                                builder);
                processNamespaceData(element, true);
                processAttributes(element);
            } else {
                element =
                        OMAbstractFactory.getOMFactory().createOMElement(
                                localName, null, parent, builder);
                processNamespaceData(element, false);
                processAttributes(element);
            }

        } else if (elementLevel == 5) {

            if (parent.getLocalName().equals(SOAP_FAULT_CODE_LOCAL_NAME)) {
                throw new OMBuilderException(
                        "faultcode element should not have children");
            } else if (parent.getLocalName().equals(
                    SOAP_FAULT_STRING_LOCAL_NAME)) {
                throw new OMBuilderException(
                        "faultstring element should not have children");
            } else if (parent.getLocalName().equals(
                    SOAP_FAULT_ACTOR_LOCAL_NAME)) {
                throw new OMBuilderException(
                        "faultactor element should not have children");
            } else {
                element =
                        OMAbstractFactory.getOMFactory().createOMElement(
                                localName, null, parent, builder);
                processNamespaceData(element, false);
                processAttributes(element);
            }

        } else if (elementLevel > 5) {
            element =
                    OMAbstractFactory.getOMFactory().createOMElement(localName,
                            null,
                            parent,
                            builder);
            processNamespaceData(element, false);
            processAttributes(element);
        }

        return element;
    }

    private void processText(XMLStreamReader parser, OMElement value) {
        try {
            int token = parser.next();
            while (token != XMLStreamReader.END_ELEMENT) {
                if (token == XMLStreamReader.CHARACTERS) {
                    OMText text = factory.createText(value, parser.getText());
                    value.addChild(text);
                } else {
                    throw new SOAPProcessingException(
                            "Only Characters are allowed here");
                }
                token = parser.next();
            }


        } catch (XMLStreamException e) {
            throw new SOAPProcessingException(e);
        }
    }

}