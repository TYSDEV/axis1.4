package org.apache.axis.om.impl.llom.mtom;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.activation.DataHandler;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

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
public class MTOMBuilder {
	LinkedList mimeBodyPartsList;

	InputStream inStream;

	MIMEParser mimeParser;

	public MTOMBuilder(InputStream inStream) {
		this.inStream = inStream;
		this.mimeParser = new MIMEParser(inStream);
		mimeBodyPartsList = new LinkedList();
	}

	public DataHandler getDataHandler(String blobContentID) throws MessagingException{
		/*
		 * First checks whether the part is already parsed by checking the parts
		 * linked list. If it is not parsed yet then call the getnextPart() till
		 * we find the required part.
		 */
		MimeBodyPart mimeBodyPart;

		boolean attachmentFound = false;
		ListIterator partsIterator = mimeBodyPartsList.listIterator();
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
	}

	public XMLStreamReader getParser() throws XMLStreamException, FactoryConfigurationError, IOException, MessagingException  {

		if (mimeParser.mimeMessage()) {
			MimeBodyPart rootMimeBodyPart = getRootMimeBodyPart();
			return XMLInputFactory.newInstance().createXMLStreamReader(
					rootMimeBodyPart.getInputStream());
		} else {
			return XMLInputFactory.newInstance()
					.createXMLStreamReader(inStream);
		}
	}

	public MimeBodyPart getRootMimeBodyPart() throws MessagingException   {
		MimeBodyPart rootMimeBodyPart;
		if (mimeBodyPartsList.isEmpty()) {
			rootMimeBodyPart = mimeParser.getMimeBodyPart();
			mimeBodyPartsList.add(rootMimeBodyPart);
		} else {
			rootMimeBodyPart = (MimeBodyPart) mimeBodyPartsList.getFirst();
		}
		System.out.println("MIME root parsed. NO of parts parsed :"
				+ mimeBodyPartsList.size());
		return rootMimeBodyPart;
	}

	public MimeBodyPart getNextMimeBodyPart() throws MessagingException  {
		MimeBodyPart nextMimeBodyPart;
		nextMimeBodyPart = mimeParser.getMimeBodyPart();
		if (nextMimeBodyPart != null) {
			mimeBodyPartsList.add(nextMimeBodyPart);
			System.out.println("Next part parsed. NO of parts parsed :"
					+ mimeBodyPartsList.size());
			return nextMimeBodyPart;
		} else
			return null;
	}

}