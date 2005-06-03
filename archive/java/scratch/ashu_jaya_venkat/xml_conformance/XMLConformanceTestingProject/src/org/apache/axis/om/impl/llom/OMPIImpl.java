/*
 * Created on Apr 13, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.apache.axis.om.impl.llom;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.apache.axis.om.OMElement;
import org.apache.axis.om.OMException;
import org.apache.axis.om.OMNode;
import org.apache.axis.om.OMPI;

/**
 * @author sunja07
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class OMPIImpl extends OMNodeImpl implements OMPI {

	String piTarget;
	String value;
		
	/**
	 * @param parent
	 */
	public OMPIImpl(OMElement parent, String piTarget, String content) {
		super(parent);
		this.piTarget = piTarget;
		this.value = content;		
	}

	/* (non-Javadoc)
	 * @see org.apache.axis.om.OMPI#getTarget()
	 */
	public String getTarget() {
		return this.piTarget;
	}

	/* (non-Javadoc)
	 * @see org.apache.axis.om.OMPI#getContent()
	 */
	public String getContent() {
		return value;
	}

	/* (non-Javadoc)
	 * @see org.apache.axis.om.OMNode#getParent()
	 */
	public OMElement getParent() throws OMException {
		return super.getParent();
	}

	/* (non-Javadoc)
	 * @see org.apache.axis.om.OMNode#serialize(javax.xml.stream.XMLStreamWriter, boolean)
	 */
	private void serialize(XMLStreamWriter writer, boolean cache)
			throws XMLStreamException {
		writer.writeProcessingInstruction(piTarget, this.value);
		OMNode nextSibling = this.getNextSibling();
        if (nextSibling != null) {
        	if(!cache)
        		nextSibling.serialize(writer);
        	else
        		nextSibling.serializeWithCache(writer);
        }
	}

	/* (non-Javadoc)
	 * @see org.apache.axis.om.OMNode#serialize(javax.xml.stream.XMLStreamWriter)
	 */
	public void serialize(XMLStreamWriter writer) throws XMLStreamException {
		serialize(writer,false);
	}
	
	public void serializeWithCache(XMLStreamWriter writer) throws XMLStreamException {
		serialize(writer,true);
	}
	
	public void discard() throws OMException {
        if (done) {
            this.detach();
        } else {
            builder.discard(this.parent);
        }
    }

}
