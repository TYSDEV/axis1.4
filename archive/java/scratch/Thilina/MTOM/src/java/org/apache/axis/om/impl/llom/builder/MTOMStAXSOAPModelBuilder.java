/*
 * Created on Mar 11, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.apache.axis.om.impl.llom.builder;

import org.apache.axis.om.*;
import org.apache.axis.om.impl.llom.mtom.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.InputStream;

import javax.mail.MessagingException;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;

/**
 * @author Thilina Gunarathne
 *  thilina@opensource.lk
 */
public class MTOMStAXSOAPModelBuilder extends StAXSOAPModelBuilder {
	private Log log = LogFactory.getLog(getClass());
	
	MTOMBuilder MtomBuilder;
	
	public MTOMStAXSOAPModelBuilder(OMFactory ombuilderFactory,
			InputStream inStream) throws Exception, XMLStreamException,
			FactoryConfigurationError, IOException, MessagingException {
		super(ombuilderFactory);
		MtomBuilder = new MTOMBuilder(inStream);
		this.parser = MtomBuilder.getParser();
	}
	
	public MTOMStAXSOAPModelBuilder(InputStream inStream)
	throws XMLStreamException, FactoryConfigurationError, IOException,
	MessagingException {
		super();
		MtomBuilder = new MTOMBuilder(inStream);
		this.parser = MtomBuilder.getParser();
	}
	
	protected OMNode createOMElement() throws OMException {
		
		String elementName = parser.getLocalName();
		
		String namespaceURI = parser.getNamespaceURI();
		
		// create an OMBlob if the element is an <xop:Include>
		if (elementName.equalsIgnoreCase("Include")
				& namespaceURI
				.equalsIgnoreCase("http://www.w3.org/2004/08/xop/include")) {
			
			OMBlob node;
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
				node = new OMBlob(contentID, lastNode.getParent(), MtomBuilder, this);
				lastNode.setNextSibling(node);
				node.setPreviousSibling(lastNode);
			} else {
				OMElement e = (OMElement) lastNode;
				node = new OMBlob(contentID, (OMElement) lastNode, MtomBuilder,this);
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