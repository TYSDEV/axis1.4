package org.apache.axis.impl.llom.serialize;

import org.apache.axis.encoding.Base64;
import org.apache.axis.impl.llom.OMAttributeImpl;
import org.apache.axis.impl.llom.OMNamespaceImpl;
import org.apache.axis.impl.llom.mtom.MTOMXMLStreamWriter;
import org.apache.axis.impl.llom.mtom.OMBlob;
import org.apache.axis.om.*;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.ByteArrayOutputStream;
import java.util.Iterator;
import java.util.Vector;

/**
 * Copyright 2001-2004 The Apache Software Foundation.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
public class SimpleOMSerializer {

    private Vector prefixList = new Vector();

    public void serialize(Object omNode, XMLStreamWriter writer)
            throws XMLStreamException {

        if (!(omNode instanceof OMNode)) {
            throw new UnsupportedOperationException("Unsupported input object. Must be of the the type OMNode");
        }

        OMNode node = (OMNode) omNode;
        serializeNode(node, writer);
    }

    protected void serializeNode(OMNode node, XMLStreamWriter writer)
            throws XMLStreamException {
        short nodeType = node.getType();
        if (nodeType == OMNode.ELEMENT_NODE) {
            serializeElement((OMElement) node, writer);
        } else if (nodeType == OMNode.ATTRIBUTE_NODE) {
            serializeAttribute((OMAttribute) node, writer);
        } else if (nodeType == OMNode.TEXT_NODE) {
            serializeText((OMText) node, writer);
        } else if (nodeType == OMNode.COMMENT_NODE) {
            serializeComment((OMText) node, writer);
        } else if (nodeType == OMNode.CDATA_SECTION_NODE) {
            serializeCData((OMText) node, writer);
            // added by Thilina
        } else if (nodeType == OMNode.BLOB_NODE) {
            serializeBlob((OMBlob) node, writer);
        }
        writer.flush();
    }

    /**
     * @param element
     */
    protected void serializeElement(OMElement element, XMLStreamWriter writer)
            throws XMLStreamException {

        OMNamespace ns = element.getNamespace();
        String prefix = null;
        String nameSpaceName = null;
        if (ns != null) {
            prefix = ns.getPrefix();
            nameSpaceName = ns.getName();
            if (prefix != null) {
                writer.writeStartElement(prefix, element.getLocalName(),
                        nameSpaceName);
                if (!prefixList.contains(prefix)) {
                    writer.writeNamespace(prefix, nameSpaceName);
                    prefixList.add(prefix);
                }
            } else {
                writer.writeStartElement(nameSpaceName, element.getLocalName());
                //add the own namespace
                writer.writeDefaultNamespace(nameSpaceName);

            }
        }

        //add the elements attributes
        Iterator attributes = element.getAttributes();
        while (attributes.hasNext()) {
            serializeAttribute((OMAttribute) attributes.next(), writer);
        }

        //add the namespaces
        Iterator namespaces = element.getAllDeclaredNamespaces();
        if (namespaces != null) {
            while (namespaces.hasNext()) {
                serializeNamespace((OMNamespace) namespaces.next(), writer);
            }
        }

        //add the children
        Iterator children = element.getChildren();

        while (children.hasNext()) {
            Object node = children.next();
            if (node != null) {
                serializeNode((OMNode) node, writer);
            }
        }

        writer.writeEndElement();

    }

    /*
     * Edited by Thilina Gunarathne
     */
    protected void serializeBlob(OMBlob blob, XMLStreamWriter xmlWriter)
            throws XMLStreamException {

        if (xmlWriter instanceof MTOMXMLStreamWriter & blob.isMTOMable()) {
            MTOMXMLStreamWriter writer = (MTOMXMLStreamWriter) xmlWriter;
            OMNamespace ns = new OMNamespaceImpl("http://www.w3.org/2004/08/xop/Include", "xop");
            String prefix = null;
            String nameSpaceName = null;
            if (ns != null) {
                prefix = ns.getPrefix();
                nameSpaceName = ns.getName();
                if (prefix != null) {
                    writer.writeStartElement(prefix, "Include", nameSpaceName);
                    //if (!prefixList.contains(prefix)) {
                    writer.writeNamespace(prefix, nameSpaceName);
                    //	prefixList.add(prefix);
                    //}
                }
            }

            //get the Cid from the MTOMwriter
            String cid;
            try {
                cid = writer.createPart(blob);
                OMAttribute href = new OMAttributeImpl("href",
                        new OMNamespaceImpl("", ""), "cid:" + cid.trim());
                serializeAttribute(href, writer);
            } catch (Exception e) {
                e.printStackTrace();
            }
            writer.writeEndElement();
        } else {
            ByteArrayOutputStream byteStream;
            try {
                byteStream = (ByteArrayOutputStream) blob.getStream();
                xmlWriter.writeCharacters(Base64.encode(byteStream
                        .toByteArray()));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    /*
     * Edit over
     */

    protected void serializeText(OMText text, XMLStreamWriter writer)
            throws XMLStreamException {
        writer.writeCharacters(text.getValue());
    }

    protected void serializeCData(OMText text, XMLStreamWriter writer)
            throws XMLStreamException {
        writer.writeCData(text.getValue());
    }

    protected void serializeComment(OMText text, XMLStreamWriter writer)
            throws XMLStreamException {
        writer.writeComment(text.getValue());
    }

    /**
     * @param attr
     * @param writer
     * @throws XMLStreamException
     */

    protected void serializeAttribute(OMAttribute attr, XMLStreamWriter writer)
            throws XMLStreamException {

        //first check whether the attribute is associated with a namespace
        OMNamespace ns = attr.getNamespace();
        String prefix = null;
        String namespaceName = null;
        if (ns != null) {
            //add the prefix if it's availble
            prefix = ns.getPrefix();
            namespaceName = ns.getName();

            if (prefix != null)
                writer.writeAttribute(prefix, namespaceName, attr
                        .getLocalName(), attr.getValue());
            else
                writer.writeAttribute(namespaceName, attr.getLocalName(), attr
                        .getValue());
        } else {
            writer.writeAttribute(attr.getLocalName(), attr.getValue());
        }

    }

    protected void serializeNamespace(OMNamespace namespace,
                                      XMLStreamWriter writer) throws XMLStreamException {
        if (namespace != null) {
            String prefix = namespace.getPrefix();
            if (!prefixList.contains(prefix))
                writer.writeNamespace(prefix, namespace.getName());

        }
    }

}