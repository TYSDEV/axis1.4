package org.apache.axis.om.impl.llom.mtom;

import java.io.*;
import java.io.IOException;

import javax.activation.DataHandler;
import javax.mail.MessagingException;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.apache.axis.encoding.Base64;
import org.apache.axis.om.OMAttribute;
import org.apache.axis.om.OMElement;
import org.apache.axis.om.OMException;
import org.apache.axis.om.OMNamespace;
import org.apache.axis.om.OMNode;
import org.apache.axis.om.OMXMLParserWrapper;
import org.apache.axis.om.impl.llom.OMAttributeImpl;
import org.apache.axis.om.impl.llom.OMNamespaceImpl;
import org.apache.axis.om.impl.llom.OMNodeImpl;
import org.apache.axis.om.impl.llom.serialize.StreamWriterToContentHandlerConverter;
import org.apache.axis.om.impl.llom.serialize.StreamingOMSerializer;

/**
 * Copyright 2001-2004 The Apache Software Foundation.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <p/>
 */

/**
 * @author Thilina Gunarathne 
 * thilina@opensource.lk
 */

public class OMBlob extends OMNodeImpl {
	//thinking of implementing the OMText 
	
	private String cid = null;
	
	private MTOMBuilder mtomBuilder;
	
	private OMXMLParserWrapper builder;
	
	private DataHandler DH = null;
	
	public OMBlob(DataHandler DH) {
		this.DH = DH;
	}
	
	public OMBlob(String cid, OMElement parent, MTOMBuilder mimeParser,
			OMXMLParserWrapper builder) {
		super(parent);
		this.builder = builder;
		this.cid = cid;
		this.mtomBuilder = mimeParser;
	}
	
	public java.io.OutputStream getOutputStream() throws IOException, MessagingException  {
		if (DH == null) {
			getDataHandler();
		}
		OutputStream outStream =DH.getOutputStream();
		if (!(outStream instanceof java.io.OutputStream))
		{
			outStream =  new ByteArrayOutputStream(); 
			Object object =  DH.getContent();
			ObjectOutputStream objectOutStream = new ObjectOutputStream(outStream);
			objectOutStream.writeObject(object);
		}
		return outStream;
	}
	
	public String getValue() throws OMException {
		throw new OMException(
		"Blob contains Binary data. Returns Stream or Datahandler only");
	}
	
	public DataHandler getDataHandler() throws MessagingException {
		if (DH == null) {
			DH = mtomBuilder.getDataHandler(cid);
		}
		return DH;
	}
	
	public short getType() throws OMException {
		return OMNode.BLOB_NODE;
	}
	
	public boolean isComplete() {
		return true;
	}
	
	public void serialize(XMLStreamWriter writer, boolean cache)
	throws XMLStreamException {
		boolean firstElement = false;
		
		// Special case for the pull type building with cache off
		// The getPullParser method returns the current elements itself.
		
		if (isComplete()) {
			
			// serialize own normally
			if (writer instanceof MTOMXMLStreamWriter) {
				MTOMXMLStreamWriter mtomWriter = (MTOMXMLStreamWriter) writer;
				// write the XOP:Include namespace
				OMNamespace ns = new OMNamespaceImpl(
						"http://www.w3.org/2004/08/xop/Include", "xop");
				String prefix = null;
				String nameSpaceName = null;
				if (ns != null) {
					prefix = ns.getPrefix();
					nameSpaceName = ns.getName();
					if (prefix != null) {
						mtomWriter.writeStartElement(prefix, "Include",
								nameSpaceName);
						//if (!prefixList.contains(prefix)) {
						mtomWriter.writeNamespace(prefix, nameSpaceName);
						//	prefixList.add(prefix);
						//}
					}
				}
				
				//get the Cid from the MTOMwriter
				String contentID;
				contentID = mtomWriter.writeAttachment(this);
				OMAttribute href = new OMAttributeImpl("href",
						new OMNamespaceImpl("", ""), "cid:" + contentID.trim());
				serializeAttribute(href, writer);
				mtomWriter.writeEndElement();
			} else {
				ByteArrayOutputStream byteStream;
				
					try {
						byteStream = (ByteArrayOutputStream) this.getOutputStream();
						writer.writeCharacters(Base64.encode(byteStream
								.toByteArray()));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				
			}
			if (nextSibling != null) {
				// serilize next sibling
				nextSibling.serialize(writer, cache);
			} else {
				if (parent == null) {
					return;
				} else if (parent.isComplete()) {
					return;
				} else {
					// do the special serialization
					// Only the push serializer is left now
					builder.setCache(cache);
					builder.next();
				}
			}
		}
	}
	
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
	
	// Decided to let user select whether to optimise or not only when he
	// creates OMBlob
	/*
	 * public void setMTOMable(boolean MTOMable) { this.MTOMable = MTOMable; }
	 * 
	 * public boolean isMTOMable() { return MTOMable; }
	 */
	
	// decided to provide only the Datahandler
	/* public/*Serializable *//*
	 * @author TGunarathne
	 *
	 * TODO To change the template for this generated type comment go to
	 * Window - Preferences - Java - Code Style - Code Templates
	 */
	/* Object getObject() throws Exception { if
	 * @author TGunarathne
	 *
	 * TODO To change the template for this generated type comment go to
	 * Window - Preferences - Java - Code Style - Code Templates
	 *
	* ((binaryObject == null) & (cid != null)) { if
		* (DH == null) { getDataHandler(); } // /*
	* 
	* 
	* javax.activation.DataSource ds =
		* datahandler.getDataSource(); InputStream is =
			* null; try { is = ds.getInputStream();; } catch
			* (java.io.IOException io) {
				* log.error(Messages.getMessage("javaIOException00"),
						* io); throw new SOAPException(io); } if
						* (ds.getContentType().equals("text/plain")) { try {
							* byte[] bytes = new byte[is.available()];
							* IOUtils.readFully(is, bytes); return new
							* String(bytes); } catch (java.io.IOException io) {
								* log.error(Messages.getMessage("javaIOException00"),
										* io); throw new SOAPException(io); } } else if
										* (ds.getContentType().equals("text/xml")) {
											* return new StreamSource(is); } else if
											* (ds.getContentType().equals("image/gif") ||
													* ds.getContentType().equals("image/jpeg")) { try {
														* return
														* ImageIOFactory.getImageIO().loadImage(is); }
													* catch (Exception ex) {
														* log.error(Messages.getMessage("javaIOException00"),
																* ex); throw new SOAPException(ex); } } return is; //
																*/
																/*
																 * Object in = DH.getContent(); if (in instanceof InputStream) {
																 * ObjectInputStream ois = new ObjectInputStream((InputStream) in);
																 * binaryObject = ois.readObject(); } else binaryObject = in; return
																 * binaryObject; } else if ((binaryObject == null) & (cid == null)) { throw
																 * new MTOMException("OMBlob not initialised Properly"); } return
																 * binaryObject; }
																 */
																
	}