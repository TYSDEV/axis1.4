package org.apache.axis.om.impl.llom.mtom;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimePartDataSource;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.axis.om.OMException;

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
 * @author Thilina Gunarathne thilina@opensource.lk
 */
public class MTOMHelper {

    InputStream inStream;

    boolean mime;

    LinkedList mimeBodyPartsList;

    MimeMessage mimeMessage;

    int partIndex = 0;

    public MTOMHelper(InputStream inStream) throws OMException {
        this.inStream = inStream;

        mimeBodyPartsList = new LinkedList();

        Properties props = new Properties();
        javax.mail.Session session = javax.mail.Session
                .getInstance(props, null);
        try {
            mimeMessage = new MimeMessage(session, inStream);
            mime = true;
        } catch (MessagingException e) {
            mime = false;
            throw new OMException(
                    "MTOM identified message doesn't contain valid MIME Stream");
        }
    }

    public DataHandler getDataHandler(String blobContentID) throws OMException {
        /*
         * First checks whether the part is already parsed by checking the parts
         * linked list. If it is not parsed yet then call the getnextPart() till
         * we find the required part.
         */
        MimeBodyPart mimeBodyPart;

        boolean attachmentFound = false;
        ListIterator partsIterator = mimeBodyPartsList.listIterator();
        try {
            while (partsIterator.hasNext()) {
                mimeBodyPart = (MimeBodyPart) partsIterator.next();
                if (blobContentID.equals(mimeBodyPart.getContentID())) {
                    attachmentFound = true;
                    DataHandler dh = mimeBodyPart.getDataHandler();
                    return dh;
                }
            }
            while (!attachmentFound) {
                mimeBodyPart = this.getNextMimeBodyPart();

                if (mimeBodyPart == null) {
                    break;
                }
                String partContentID = mimeBodyPart.getContentID();
                String delimitedBlobContentID = "<" + blobContentID + ">";
                if (delimitedBlobContentID.equals(partContentID)) {
                    attachmentFound = true;
                    DataHandler dh = mimeBodyPart.getDataHandler();
                    return dh;
                }
            }
            return null;
        } catch (MessagingException e) {
            throw new OMException("Invalid SOAP Message in Root Mime part "
                    + e.toString());
        }
    }

    private MimeBodyPart getMimeBodyPart() throws MessagingException {
        MimeBodyPart mimeBodyPart = null;

        DataHandler dh = mimeMessage.getDataHandler();
        MimeMultipart multiPart = new MimeMultipart((MimePartDataSource) dh
                .getDataSource());
        mimeBodyPart = (MimeBodyPart) multiPart.getBodyPart(partIndex);

        partIndex++;
        return mimeBodyPart;
    }

    private MimeBodyPart getNextMimeBodyPart() throws MessagingException {
        MimeBodyPart nextMimeBodyPart;
        nextMimeBodyPart = getMimeBodyPart();
        if (nextMimeBodyPart != null) {
            mimeBodyPartsList.add(nextMimeBodyPart);
            System.out.println("Next part parsed. NO of parts parsed :"
                    + mimeBodyPartsList.size());
            return nextMimeBodyPart;
        } else
            return null;
    }

    public XMLStreamReader getParser() throws OMException, XMLStreamException, FactoryConfigurationError {

        if (mime) {
            MimeBodyPart rootMimeBodyPart;
            try {
                rootMimeBodyPart = getRootMimeBodyPart();
                return XMLInputFactory.newInstance().createXMLStreamReader(
                        rootMimeBodyPart.getInputStream());
            
            } catch (IOException e1) {
                throw new OMException(e1.toString());

            } catch (MessagingException e) {
                throw new OMException("Unable to extract root MIME part "
                        + e.toString());
            }

        } else {
            throw new OMException(
                    "MTOM identified message doesn't contain valid MIME Stream");
        }
    }

    private MimeBodyPart getRootMimeBodyPart() throws MessagingException {
        MimeBodyPart rootMimeBodyPart;
        if (mimeBodyPartsList.isEmpty()) {
            rootMimeBodyPart = getMimeBodyPart();
            mimeBodyPartsList.add(rootMimeBodyPart);
        } else {
            rootMimeBodyPart = (MimeBodyPart) mimeBodyPartsList.getFirst();
        }
        System.out.println("MIME root parsed. NO of parts parsed :"
                + mimeBodyPartsList.size());
        return rootMimeBodyPart;
    }

}