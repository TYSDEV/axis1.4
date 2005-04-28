/*
 * Created on Apr 21, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.apache.axis.om.impl.llom;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.apache.axis.om.OMDTD;
import org.apache.axis.om.OMElement;
import org.apache.axis.om.OMNode;

/**
 * @author sunja07
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class OMDTDImpl extends OMNodeImpl implements OMDTD {
	
	public OMDTDImpl(OMElement parentNode, String content) {
		super(parentNode);
		this.setValue(content);
	}
		
	/* (non-Javadoc)
	 * @see org.apache.axis.om.OMNode#serialize(javax.xml.stream.XMLStreamWriter, boolean)
	 */
	public void serialize(XMLStreamWriter writer, boolean cache)
			throws XMLStreamException {		
		writer.writeDTD(this.value);
		OMNode nextSibling = this.getNextSibling();
        if (nextSibling != null) {
            nextSibling.serialize(writer, cache);
        }
	}

	/* (non-Javadoc)
	 * @see org.apache.axis.om.OMNode#serialize(javax.xml.stream.XMLStreamWriter)
	 */
	public void serialize(XMLStreamWriter writer) throws XMLStreamException {
		serialize(writer,false);
	}

}
