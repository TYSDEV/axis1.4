
package org.apache.axis.om.impl.llom.builder;

import java.io.IOException;
import java.io.InputStream;

import javax.mail.MessagingException;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;

import org.apache.axis.om.OMAttribute;
import org.apache.axis.om.OMElement;
import org.apache.axis.om.OMException;
import org.apache.axis.om.OMFactory;
import org.apache.axis.om.OMNode;
import org.apache.axis.om.impl.llom.OMBlobImpl;
import org.apache.axis.om.impl.llom.mtom.MTOMHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Thilina Gunarathne thilina@opensource.lk
 */
public class MTOMStAXSOAPModelBuilder extends StAXSOAPModelBuilder {
    private Log log = LogFactory.getLog(getClass());

    MTOMHelper mtomHelper;

    public MTOMStAXSOAPModelBuilder(OMFactory ombuilderFactory,
            InputStream inStream) throws XMLStreamException,
            FactoryConfigurationError, OMException {
        super(ombuilderFactory);
        mtomHelper = new MTOMHelper(inStream);
        this.parser = mtomHelper.getParser();
    }

    public MTOMStAXSOAPModelBuilder(InputStream inStream)
            throws XMLStreamException, FactoryConfigurationError, IOException,
            MessagingException {
        super();
        mtomHelper = new MTOMHelper(inStream);
        this.parser = mtomHelper.getParser();
    }

    protected OMNode createOMElement() throws OMException {

        String elementName = parser.getLocalName();

        String namespaceURI = parser.getNamespaceURI();

        // create an OMBlob if the element is an <xop:Include>
        if (elementName.equalsIgnoreCase("Include")
                & namespaceURI
                        .equalsIgnoreCase("http://www.w3.org/2004/08/xop/include")) {

            OMBlobImpl node;
            String contentID = null;
            String contentIDName = null;
            OMAttribute Attr;
            if (lastNode == null) {
                // Decide whether to ckeck the level >3 or not
                throw new OMException(
                        "XOP:Include element is not supported here");
            }
            if (parser.getAttributeCount() > 0) {
                contentID = parser.getAttributeValue(0);
                contentID = contentID.trim();
                contentIDName = parser.getAttributeLocalName(0);
                if (contentIDName.equalsIgnoreCase("href")
                        & contentID.substring(0, 3).equalsIgnoreCase("cid")) {
                    contentID = contentID.substring(4);
                } else {
                    throw new OMException(
                            "contentID not Found in XOP:Include element");
                }
            } else {
                throw new OMException(
                        "Href attribute not found in XOP:Include element");
            }

            if (lastNode.isComplete()) {
                node = new OMBlobImpl(contentID, lastNode.getParent(),
                        mtomHelper, this);
                lastNode.setNextSibling(node);
                node.setPreviousSibling(lastNode);
            } else {
                OMElement e = (OMElement) lastNode;
                node = new OMBlobImpl(contentID, (OMElement) lastNode,
                        mtomHelper, this);
                e.setFirstChild(node);
            }
            return node;

        } else {
            OMElement node;
            if (lastNode == null) {
                node = constructNode(null, elementName, true);
            } else if (lastNode.isComplete()) {
                node = constructNode(lastNode.getParent(), elementName, false);
                lastNode.setNextSibling(node);
                node.setPreviousSibling(lastNode);
            } else {
                OMElement e = (OMElement) lastNode;
                node = constructNode((OMElement) lastNode, elementName, false);
                e.setFirstChild(node);
            }

            // fill in the attributes
            processAttributes(node);
            log.info("Build the OMElelment {" + node.getNamespaceName() + '}'
                    + node.getLocalName() + "By the StaxSOAPModelBuilder");
            return node;
        }
    }
}