/*
 * Created on Apr 12, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.apache.axis.om.impl.llom;

import org.apache.axis.om.OMComment;
import org.apache.axis.om.OMException;
import org.apache.axis.om.OMNode;
import org.apache.axis.om.OMElement;

import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.XMLStreamException;

/**
 * @author sunja07
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class OMCommentImpl extends OMNodeImpl implements OMComment {

	//Object parent;		
	
	/**
	 * @param parentNode
	 * @param contentText
	 */
	public OMCommentImpl(OMElement parentNode, String contentText) throws OMException{
		if(parentNode != null) {
			this.parent = (OMElementImpl)parentNode;
		} else {
			throw new OMException("Comment should have a non-null Document or Element as its parent");
		}
		setValue(contentText);
	}

	/**
	 * @param parentNode
	 */
	public OMCommentImpl(OMElement parentNode) {
		this(parentNode, null);
	}

	public void setParent(OMElement element) {

        if( ((OMElement)this.parent) == element){
            return;
        }

        if (element instanceof OMElement) {
            if(this.parent != null){
                this.detach();
            }
            this.parent = (OMElementImpl) element;
        }
    }
		
	public String getContent() {
		return getValue();
	}
	
	public void serialize (XMLStreamWriter writer) throws XMLStreamException {
		serialize(writer, false);
	}
	
	public void serialize (XMLStreamWriter writer, boolean cache) throws XMLStreamException {
		writer.writeComment(this.value);
		OMNode nextSibling = this.getNextSibling();
        if (nextSibling != null) {
            nextSibling.serialize(writer, cache);
        }
	}
}
