package org.apache.axis.impl.llom.mtom;

import javax.activation.DataHandler;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.xml.namespace.NamespaceContext;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Random;

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
 */
public class MTOMXMLStreamWriter implements XMLStreamWriter {

    OutputStream outStream;

    BufferedOutputStream soapStream;

    XMLStreamWriter writer;

    Random rnd;

    LinkedList partList;

    ByteArrayOutputStream byteStream;
    public static String[] filter = new String[]{"Message-ID"};

    public MTOMXMLStreamWriter(OutputStream out) throws XMLStreamException,
            FactoryConfigurationError {
        super();
        this.outStream = out;

        byteStream = new ByteArrayOutputStream();
        //soapStream = new BufferedOutputStream(byteStream);
        writer = XMLOutputFactory.newInstance().createXMLStreamWriter(byteStream);
        rnd = new Random();
        partList = new LinkedList();
    }

    public String createPart(OMBlob blob) {
        String cid = "http://example.org/my.hsh" + (rnd.nextInt());
        AttachmentPart part = new AttachmentPart(cid, blob);
        partList.add(part);
        return cid;
    }

    public MimeBodyPart createMimePart(String cid, OMBlob blob) throws Exception {
        MimeBodyPart mb = new MimeBodyPart();
        mb.setDataHandler(blob.getDataHandler());
        mb.addHeader("Content-Transfer-Encoding", "binary");
        mb.addHeader("Content-ID", "<" + cid + ">");
        return mb;

    }

    public void complete() throws Exception {
        DataHandler dh = new DataHandler(byteStream.toString(), "text/xml");
        MimeBodyPart mb = new MimeBodyPart();
        mb.setDataHandler(dh);
        mb.addHeader("Content-Type", "application/xop+xml");
        mb.addHeader("Content-Transfer-Encoding", "8bit");
        String cid = "<http://example.org/my.hsh>";
        mb.addHeader("Content-ID", cid);

        //MimeMessage message = new MimeMessage(Session.getDefaultInstance(new java.util.Properties()));

        Properties props = new Properties();
        javax.mail.Session session = javax.mail.Session.getInstance(props, null);
        javax.mail.internet.MimeMessage message = new javax.mail.internet.MimeMessage(session);
        MimeMultipart mes = new MimeMultipart("Related");
        mes.addBodyPart(mb);
        Iterator partIterator = partList.iterator();
        while (partIterator.hasNext()) {
            AttachmentPart part = (AttachmentPart) partIterator.next();
            mes.addBodyPart(createMimePart(part.getCid(), part.getBlob()));
        }
        mes.writeTo(System.out);
        System.out.println("----------------------------");
        message.setContent(mes);
        message.removeHeader("Message-Id");
        message.writeTo(System.out);
        message.writeTo(outStream, filter);
    }

    public void writeStartElement(String localName) throws XMLStreamException {
        writer.writeStartElement(localName);
    }

    public void writeStartElement(String namespaceURI, String localName)
            throws XMLStreamException {
        writer.writeStartElement(namespaceURI, localName);

    }

    public void writeStartElement(String prefix, String localName,
                                  String namespaceURI) throws XMLStreamException {
        writer.writeStartElement(prefix, localName, namespaceURI);

    }

    /*
     * (non-Javadoc)
     *
     * @see javax.xml.stream.XMLStreamWriter#writeEmptyElement(java.lang.String,
     *      java.lang.String)
     */
    public void writeEmptyElement(String namespaceURI, String localName)
            throws XMLStreamException {
        writer.writeEmptyElement(namespaceURI, localName);

    }

    /*
     * (non-Javadoc)
     *
     * @see javax.xml.stream.XMLStreamWriter#writeEmptyElement(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public void writeEmptyElement(String prefix, String localName,
                                  String namespaceURI) throws XMLStreamException {
        writer.writeEmptyElement(prefix, localName, namespaceURI);

    }

    /*
     * (non-Javadoc)
     *
     * @see javax.xml.stream.XMLStreamWriter#writeEmptyElement(java.lang.String)
     */
    public void writeEmptyElement(String localName) throws XMLStreamException {
        writer.writeEmptyElement(localName);

    }

    /*
     * (non-Javadoc)
     *
     * @see javax.xml.stream.XMLStreamWriter#writeEndElement()
     */
    public void writeEndElement() throws XMLStreamException {
        writer.writeEndElement();

    }

    /*
     * (non-Javadoc)
     *
     * @see javax.xml.stream.XMLStreamWriter#writeEndDocument()
     */
    public void writeEndDocument() throws XMLStreamException {
        writer.writeEndDocument();

    }

    /*
     * (non-Javadoc)
     *
     * @see javax.xml.stream.XMLStreamWriter#close()
     */
    public void close() throws XMLStreamException {
        writer.close();

    }

    /*
     * (non-Javadoc)
     *
     * @see javax.xml.stream.XMLStreamWriter#flush()
     */
    public void flush() throws XMLStreamException {
        writer.flush();

    }

    /*
     * (non-Javadoc)
     *
     * @see javax.xml.stream.XMLStreamWriter#writeAttribute(java.lang.String,
     *      java.lang.String)
     */
    public void writeAttribute(String localName, String value)
            throws XMLStreamException {
        writer.writeAttribute(localName, value);

    }

    /*
     * (non-Javadoc)
     *
     * @see javax.xml.stream.XMLStreamWriter#writeAttribute(java.lang.String,
     *      java.lang.String, java.lang.String, java.lang.String)
     */
    public void writeAttribute(String prefix, String namespaceURI,
                               String localName, String value) throws XMLStreamException {
        writer.writeAttribute(prefix, namespaceURI, localName, value);

    }

    /*
     * (non-Javadoc)
     *
     * @see javax.xml.stream.XMLStreamWriter#writeAttribute(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public void writeAttribute(String namespaceURI, String localName,
                               String value) throws XMLStreamException {
        writer.writeAttribute(namespaceURI, localName, value);

    }

    /*
     * (non-Javadoc)
     *
     * @see javax.xml.stream.XMLStreamWriter#writeNamespace(java.lang.String,
     *      java.lang.String)
     */
    public void writeNamespace(String prefix, String namespaceURI)
            throws XMLStreamException {
        writer.writeNamespace(prefix, namespaceURI);

    }

    /*
     * (non-Javadoc)
     *
     * @see javax.xml.stream.XMLStreamWriter#writeDefaultNamespace(java.lang.String)
     */
    public void writeDefaultNamespace(String namespaceURI)
            throws XMLStreamException {
        writer.writeDefaultNamespace(namespaceURI);

    }

    /*
     * (non-Javadoc)
     *
     * @see javax.xml.stream.XMLStreamWriter#writeComment(java.lang.String)
     */
    public void writeComment(String data) throws XMLStreamException {
        writer.writeComment(data);

    }

    /*
     * (non-Javadoc)
     *
     * @see javax.xml.stream.XMLStreamWriter#writeProcessingInstruction(java.lang.String)
     */
    public void writeProcessingInstruction(String target)
            throws XMLStreamException {
        writer.writeProcessingInstruction(target);
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.xml.stream.XMLStreamWriter#writeProcessingInstruction(java.lang.String,
     *      java.lang.String)
     */
    public void writeProcessingInstruction(String target, String data)
            throws XMLStreamException {
        writer.writeProcessingInstruction(target, data);

    }

    /*
     * (non-Javadoc)
     *
     * @see javax.xml.stream.XMLStreamWriter#writeCData(java.lang.String)
     */
    public void writeCData(String data) throws XMLStreamException {
        writer.writeCData(data);

    }

    /*
     * (non-Javadoc)
     *
     * @see javax.xml.stream.XMLStreamWriter#writeDTD(java.lang.String)
     */
    public void writeDTD(String dtd) throws XMLStreamException {
        writer.writeDTD(dtd);

    }

    /*
     * (non-Javadoc)
     *
     * @see javax.xml.stream.XMLStreamWriter#writeEntityRef(java.lang.String)
     */
    public void writeEntityRef(String name) throws XMLStreamException {
        writer.writeEntityRef(name);

    }

    /*
     * (non-Javadoc)
     *
     * @see javax.xml.stream.XMLStreamWriter#writeStartDocument()
     */
    public void writeStartDocument() throws XMLStreamException {
        writer.writeStartDocument();

    }

    /*
     * (non-Javadoc)
     *
     * @see javax.xml.stream.XMLStreamWriter#writeStartDocument(java.lang.String)
     */
    public void writeStartDocument(String version) throws XMLStreamException {
        writer.writeStartDocument(version);

    }

    /*
     * (non-Javadoc)
     *
     * @see javax.xml.stream.XMLStreamWriter#writeStartDocument(java.lang.String,
     *      java.lang.String)
     */
    public void writeStartDocument(String encoding, String version)
            throws XMLStreamException {
        writer.writeStartDocument(encoding, version);

    }

    /*
     * (non-Javadoc)
     *
     * @see javax.xml.stream.XMLStreamWriter#writeCharacters(java.lang.String)
     */
    public void writeCharacters(String text) throws XMLStreamException {
        writer.writeCharacters(text);

    }

    /*
     * (non-Javadoc)
     *
     * @see javax.xml.stream.XMLStreamWriter#writeCharacters(char[], int, int)
     */
    public void writeCharacters(char[] text, int start, int len)
            throws XMLStreamException {
        writer.writeCharacters(text, start, len);

    }

    /*
     * (non-Javadoc)
     *
     * @see javax.xml.stream.XMLStreamWriter#getPrefix(java.lang.String)
     */
    public String getPrefix(String uri) throws XMLStreamException {

        return writer.getPrefix(uri);
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.xml.stream.XMLStreamWriter#setPrefix(java.lang.String,
     *      java.lang.String)
     */
    public void setPrefix(String prefix, String uri) throws XMLStreamException {
        writer.setPrefix(prefix, uri);

    }

    /*
     * (non-Javadoc)
     *
     * @see javax.xml.stream.XMLStreamWriter#setDefaultNamespace(java.lang.String)
     */
    public void setDefaultNamespace(String uri) throws XMLStreamException {
        writer.setDefaultNamespace(uri);

    }

    /*
     * (non-Javadoc)
     *
     * @see javax.xml.stream.XMLStreamWriter#setNamespaceContext(javax.xml.namespace.NamespaceContext)
     */
    public void setNamespaceContext(NamespaceContext context)
            throws XMLStreamException {
        writer.setNamespaceContext(context);

    }

    /*
     * (non-Javadoc)
     *
     * @see javax.xml.stream.XMLStreamWriter#getNamespaceContext()
     */
    public NamespaceContext getNamespaceContext() {

        return writer.getNamespaceContext();
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.xml.stream.XMLStreamWriter#getProperty(java.lang.String)
     */
    public Object getProperty(String name) throws IllegalArgumentException {

        return writer.getProperty(name);
    }
}