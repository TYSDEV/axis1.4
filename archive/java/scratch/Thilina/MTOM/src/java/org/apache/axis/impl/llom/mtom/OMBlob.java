package org.apache.axis.impl.llom.mtom;

import javax.activation.DataHandler;

import org.apache.axis.impl.llom.OMNodeImpl;
import org.apache.axis.om.OMElement;
import org.apache.axis.om.OMException;
import org.apache.axis.om.OMNode;

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

	private String cid = null;

	private MTOMBuilder builder;

	private DataHandler DH = null;

	// boolean MTOMable = true;

	public OMBlob(DataHandler DH) {
		this.DH = DH;
		//	this.MTOMable = MTOMable;
	}

	public OMBlob(String cid, OMElement parent, MTOMBuilder mimeParser) {
		super(parent);
		this.cid = cid;
		this.builder = mimeParser;
	}

	public java.io.OutputStream getStream() throws Exception {
		if (DH == null) {
			getDataHandler();
		}
		return DH.getOutputStream();
	}

	public String getValue() throws OMException {
		throw new OMException(
				"Blob contains Binary data. Returns Stream or Datahandler only");
	}

	public DataHandler getDataHandler() throws Exception {
		if (DH == null) {
			DH = builder.getDataHandler(cid);
		}
		return DH;
	}

	public short getType() throws OMException {
		return OMNode.BLOB_NODE;
	}

	public boolean isComplete() {
		return done;
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
							  * Object getObject() throws Exception { if
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