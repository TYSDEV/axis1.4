package org.apache.axis.impl.llom.mtom;

import javax.activation.DataHandler;
import javax.mail.internet.MimeBodyPart;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.ListIterator;

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
public class MTOMBuilder {
    LinkedList parts;

    InputStream inStream;

    MIMEParser parser;

    public MTOMBuilder(InputStream inStream) {
        this.inStream = inStream;
        this.parser = new MIMEParser(inStream);
        parts = new LinkedList();
    }

    public/* Serializable */DataHandler getDataHandler(String cid)
            throws Exception {
        /*
         * First checks whether the part is already parsed by checking the parts
         * linked list. If it is not parsed yet then call the getnextPart() till
         * we find the required part.
         */
        MimeBodyPart part;

        boolean found = false;
        ListIterator partsIterator = parts.listIterator();
        while (partsIterator.hasNext()) {
            part = (MimeBodyPart) partsIterator.next();
            if (cid.equals(part.getContentID())) {
                found = true;
                DataHandler dh = part.getDataHandler();
                return dh;
            }
        }
        while (!found) {
            part = this.getNextPart();

            if (part == null) {
                break;
            }
            String partCid = part.getContentID();
            String cida = "<" + cid + ">";
            if (cida.equals(partCid)) {
                found = true;
                DataHandler dh = part.getDataHandler();
                return dh;
            }
        }
        return null;
    }

    public XMLStreamReader getParser() throws Exception {

        if (parser.mimeMessage()) {
            MimeBodyPart root = getRoot();
            return XMLInputFactory.newInstance().createXMLStreamReader(root.getInputStream());
        } else {
            return XMLInputFactory.newInstance()
                    .createXMLStreamReader(inStream);
        }
    }

    public MimeBodyPart getRoot() throws Exception {
        MimeBodyPart root;
        if (parts.isEmpty()) {
            root = parser.getPart();
            parts.add(root);
        } else {
            root = (MimeBodyPart) parts.getFirst();
        }
        System.out.println("MIME root parsed. NO of parts parsed :" + parts.size());
        return root;
    }

    public MimeBodyPart getNextPart() throws Exception {
        MimeBodyPart nextPart;
        nextPart = parser.getPart();
        if (nextPart != null) {
            parts.add(nextPart);
            System.out.println("Next part parsed. NO of parts parsed :" + parts.size());
            return nextPart;
        } else
            return null;
    }

}