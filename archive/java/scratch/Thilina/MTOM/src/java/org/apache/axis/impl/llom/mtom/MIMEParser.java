package org.apache.axis.impl.llom.mtom;

import java.io.InputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimePartDataSource;

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
public class MIMEParser {

	int partIndex = 0;

	MIMEInputStream in;

	String mimeVersion;

	String contentType;

	boolean messageMime;

	String boundry;

	boolean complete = false;

	String start;

	MimeMessage message;

	public MIMEParser(InputStream inStream) {

		Properties props = new Properties();
		javax.mail.Session session = javax.mail.Session
				.getInstance(props, null);
		try {
			message = new MimeMessage(session, inStream);
			messageMime = true;
			//in = new MIMEInputStream(inStream);
		} catch (MessagingException e) {
			messageMime = false;
		}
	}

	public boolean mimeMessage() {
		return messageMime;
		/*
		 * String line = null; char readChar = ' '; boolean found = false; int
		 * startIndex, finishIndex, valueFinishIndex;
		 * 
		 * //Start processing the Mime Header try { in.readLine(); line =
		 * in.readLine(); } catch (Exception e) { System.out.println(e); }
		 * 
		 * line = line.trim(); String[] versionArray = line.split(":");
		 * 
		 * //Extracts and stores MIME version information if
		 * (versionArray[0].equalsIgnoreCase("MIME-Version")) { mimeVersion =
		 * versionArray[1];
		 *  /* extract the Mime boundry
		 */
		/*
		 * do { try { line = in.readLine(); } catch (Exception e) {
		 * System.out.println(e); }
		 * 
		 * //extracting the mime boundry string startIndex =
		 * line.indexOf("boundary="); } while (startIndex == -1);
		 * 
		 * finishIndex = (startIndex + ("boundary=").length()); valueFinishIndex =
		 * line.indexOf(";", finishIndex);
		 * 
		 * if (valueFinishIndex > 0) { boundry = line.substring(finishIndex,
		 * valueFinishIndex); } else { boundry = line.substring(finishIndex); }
		 * boundry = boundry.replace('"', ' '); boundry = boundry.trim();
		 *  /* Move the input stream to the begining of the MIME root part
		 */
		/*
		 * do { try { in.mark(boundry.length() + 5); readChar = (char)
		 * in.read(); } catch (Exception e) { System.out.println(e); } if
		 * (readChar == '-') { char readNextChar = ' '; try { readNextChar =
		 * (char) in.read(); } catch (IOException e3) { e3.printStackTrace(); }
		 * 
		 * if (readNextChar == '-') { char[] cBuf = new char[boundry.length()];
		 * 
		 * try { line = in.read(boundry.length()); } catch (IOException e1) {
		 * e1.printStackTrace(); }
		 * 
		 * if (line.equals(boundry)) { found = true; } else { try { in.reset(); }
		 * catch (IOException e2) { e2.printStackTrace(); } } } } } while
		 * (!found); return true; } else { return false; }
		 */
	}

	public MimeBodyPart getPart() throws Exception {
		MimeBodyPart part = null;
		/*
		 * if (!complete) {
		 * 
		 * MIMEBodyPartInputStream partStream = new MIMEBodyPartInputStream( in,
		 * boundry); part = new MimeBodyPart(partStream); if (part.getSize() ==
		 * 0 | part.getSize() < 0) { complete = true; return null; } }
		 */
		DataHandler dh = message.getDataHandler();
		MimeMultipart multiPart = new MimeMultipart((MimePartDataSource) dh
				.getDataSource());
		part = (MimeBodyPart) multiPart.getBodyPart(partIndex);

		partIndex++;
		return part;
	}
}