package org.apache.axis.om.builder;

import org.apache.axis.om.impl.llom.builder.StAXOMBuilder;
import org.apache.axis.om.impl.llom.OMDocument;
import org.apache.axis.om.OMFactory;
import org.apache.axis.om.OMException;
import org.apache.axis.om.OMNode;
import org.apache.axis.om.OMElement;

import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamConstants;
import java.util.List;

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
public class ExtendedBuilder extends StAXOMBuilder{

    public ExtendedBuilder(OMFactory omFactory, XMLStreamReader xmlStreamReader) {
        super(omFactory, xmlStreamReader);
    }

    public ExtendedBuilder(XMLStreamReader xmlStreamReader) {
        super(xmlStreamReader);
    }

    public int next() throws OMException {
        ExtensionRegistry registry = ExtensionRegistry.getInstance();
        OMNode node = null;

        try {
            if (done) {
                throw new OMException();
            }
            int token = parser.next();
            if (!cache) {
                return token;
            }

            if (!registry.isEmpty()){
                BuilderExtension[] extensions = registry.getExtensions();
                for (int i = 0; i < extensions.length; i++) {
                    node = extensions[i].buildExtensibleElement(this.parser,this.lastNode);
                    if (node!=null){
                        break;
                    }
                }
            }

            if (node==null){
                generateNextNode(token);
            }else{
                if (lastNode==null){
                    //first element
                    document.setRootElement((OMElement)node);
                }
                lastNode = node;
            }

            return token;

        } catch (OMException e) {
            throw e;
        } catch (Exception e) {
            throw new OMException(e);
        }


    }

    private void generateNextNode (int token){

        switch (token) {
            case XMLStreamConstants.START_ELEMENT:
                lastNode = createOMElement();
                break;
            case XMLStreamConstants.START_DOCUMENT:
                document = new OMDocument(this);
                break;
            case XMLStreamConstants.CHARACTERS:
                lastNode = createOMText();
                break;
            case XMLStreamConstants.END_ELEMENT:
                if (lastNode.isComplete()) {
                    OMElement parent = lastNode.getParent();
                    parent.setComplete(true);
                    lastNode = parent;
                } else {
                    OMElement e = (OMElement) lastNode;
                    e.setComplete(true);
                }
                break;
            case XMLStreamConstants.END_DOCUMENT:
                done = true;
                break;
            case XMLStreamConstants.SPACE:
                next();
                break;
            default :
                throw new OMException();
        }


    }
}
