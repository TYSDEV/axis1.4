package org.apache.axis.impl.llom.mtom;

import org.apache.axis.impl.llom.OMNodeImpl;
import org.apache.axis.impl.llom.exception.MTOMException;
import org.apache.axis.om.OMElement;
import org.apache.axis.om.OMException;
import org.apache.axis.om.OMNode;

import javax.activation.DataHandler;
import java.io.*;

//import javax.xml.transform.stream.StreamSource;
//import javax.xml.soap.SOAPException;
//import javax.xml.transform.stream.StreamSource;

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
 * @author Thilina Gunarathne thil747@yahoo.com
 */

public class OMBlob extends OMNodeImpl {

    String cid = null;

    Object binaryObject = null;

    MTOMBuilder mimeParser;

    DataHandler DH = null;

    boolean MTOMable = true;

    public OMBlob(Object object, boolean MTOMable) {
        this.MTOMable = MTOMable;
        this.binaryObject = object;
    }

    public OMBlob(String cid, OMElement parent, MTOMBuilder mimeParser) {
        super(parent);
        this.cid = cid;
        this.mimeParser = mimeParser;
    }

    public void setMTOMable(boolean MTOMable) {
        this.MTOMable = MTOMable;
    }

    public boolean isMTOMable() {
        return MTOMable;
    }

    public/*Serializable*/Object getObject() throws Exception {
        if ((binaryObject == null) & (cid != null)) {
            if (DH == null) {
                getDataHandler();
            }
            //
            /*


            javax.activation.DataSource ds = datahandler.getDataSource();
            InputStream is = null;
            try {
                is = ds.getInputStream();;
            } catch (java.io.IOException io) {
                log.error(Messages.getMessage("javaIOException00"), io);
                throw new SOAPException(io);
            }
            if (ds.getContentType().equals("text/plain")) {
                try {
                    byte[] bytes = new byte[is.available()];
                    IOUtils.readFully(is, bytes);
                    return new String(bytes);
                } catch (java.io.IOException io) {
                    log.error(Messages.getMessage("javaIOException00"), io);
                    throw new SOAPException(io);
                }
            } else if (ds.getContentType().equals("text/xml")) {
                return new StreamSource(is);
            } else if (ds.getContentType().equals("image/gif") ||
                       ds.getContentType().equals("image/jpeg")) {
                try {
                    return ImageIOFactory.getImageIO().loadImage(is);
                } catch (Exception ex) {
                    log.error(Messages.getMessage("javaIOException00"), ex);
                    throw new SOAPException(ex);
                }
            }
            return is;
            //
            */
            Object in = DH.getContent();
            if (in instanceof InputStream) {
                ObjectInputStream ois = new ObjectInputStream((InputStream) in);
                binaryObject = ois.readObject();
            } else
                binaryObject = in;
            return binaryObject;
        } else if ((binaryObject == null) & (cid == null)) {
            throw new MTOMException("OMBlob not initialised Properly");
        }
        return binaryObject;
    }

    public java.io.OutputStream getStream() throws Exception {
        ObjectOutputStream outStream;
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        if ((binaryObject == null) & (cid != null)) {
            binaryObject = getObject();
        } else if ((binaryObject == null) & (cid == null)) {
            throw new MTOMException("OMBlob not initialised Properly");
        }
        try {

            outStream = new ObjectOutputStream(byteStream);
            outStream.writeObject(binaryObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteStream;
    }

    public String getValue() throws OMException {
        throw new OMException("Blob returns objects only");
    }

    public DataHandler getDataHandler() throws Exception {
        if ((DH == null) & (binaryObject == null)) {
            DH = mimeParser.getDataHandler(cid);
        } else if (DH == null) {
            ByteArrayOutputStream out = (ByteArrayOutputStream) getStream();
            ByteArrayDataSource bads = new ByteArrayDataSource(out
                    .toByteArray(), null);
            DH = new DataHandler(bads);
        }
/*
  ManagedMemoryDataSource source = null;
        setMimeHeader(HTTPConstants.HEADER_CONTENT_TYPE, contentType);
        if (object instanceof String) {
            try {
                String s = (String) object;
                java.io.ByteArrayInputStream bais =
                        new java.io.ByteArrayInputStream(s.getBytes());
                source = new ManagedMemoryDataSource(bais,
                        ManagedMemoryDataSource.MAX_MEMORY_DISK_CACHED,
                        contentType, true);
                extractFilename(source);
                datahandler = new DataHandler(source);
                contentObject = object;
                return;
            } catch (java.io.IOException io) {
                log.error(Messages.getMessage("javaIOException00"), io);
                throw new java.lang.IllegalArgumentException(
                        Messages.getMessage("illegalArgumentException00"));
            }
        } else if (object instanceof java.io.InputStream) {
            try {
                source = new ManagedMemoryDataSource((java.io.InputStream) object,
                        ManagedMemoryDataSource.MAX_MEMORY_DISK_CACHED,
                        contentType, true);
                extractFilename(source);
                datahandler = new DataHandler(source);
                contentObject = null; // the stream has been consumed
                return;
            } catch (java.io.IOException io) {
                log.error(Messages.getMessage("javaIOException00"), io);
                throw new java.lang.IllegalArgumentException(Messages.getMessage
                        ("illegalArgumentException00"));
            }
        } else if (object instanceof StreamSource) {
            try {
                source = new ManagedMemoryDataSource(((StreamSource)object).getInputStream(),
                        ManagedMemoryDataSource.MAX_MEMORY_DISK_CACHED,
                        contentType, true);
                extractFilename(source);
                datahandler = new DataHandler(source);
                contentObject = null; // the stream has been consumed
                return;
            } catch (java.io.IOException io) {
                log.error(Messages.getMessage("javaIOException00"), io);
                throw new java.lang.IllegalArgumentException(Messages.getMessage
                        ("illegalArgumentException00"));
            }
        } else {
            throw new java.lang.IllegalArgumentException(
                    Messages.getMessage("illegalArgumentException00"));
        }
 */
        return DH;
    }

    public short getType() throws OMException {
        return OMNode.BLOB_NODE;
    }

    public boolean isComplete() {
        return done;
    }
}