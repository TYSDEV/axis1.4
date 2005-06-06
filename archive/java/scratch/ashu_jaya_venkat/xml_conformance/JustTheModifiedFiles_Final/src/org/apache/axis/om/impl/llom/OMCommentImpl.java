/*
* Copyright 2004,2005 The Apache Software Foundation.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
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
 */
public class OMCommentImpl extends OMNodeImpl implements OMComment {

	protected String value;

	/**
	 * Constructor OMCommentImpl
	 *
	 * @param parentNode
	 * @param contentText
	 */
	public OMCommentImpl(OMElement parentNode, String contentText) throws OMException{
		if(parentNode != null) {
			this.parent = (OMElementImpl)parentNode;
		} else {
			throw new OMException("Comment should have a non-null Document or Element as its parent");
		}
		this.value = contentText;
	}

	/**
	 * Constructor OMCommentImpl
	 *
	 * @param parentNode
	 */
	public OMCommentImpl(OMElement parentNode) {
		this(parentNode, null);
	}

	/**
	 * Method setParent
	 * sets the parent of this OMNode to the provided <code>OMElement</code> argument
	 *
	 * @param parentNode
	 */
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

	/**
	 * Method getContent
	 *
	 * @return String
	 */
	public String getContent() {
		return value;
	}

	/**
	 * Method serialize
	 * serializes assuming no cache
	 *
	 * @param writer
	 */
	public void serialize (XMLStreamWriter writer) throws XMLStreamException {
		serialize(writer, false);
	}

	/**
	 * Method serializeWithCache
	 * serializes assuming cache to be true
	 *
	 * @param writer
	 */
	public void serializeWithCache (XMLStreamWriter writer) throws XMLStreamException {
		serialize(writer, true);
	}

	/**
	 * Method serialize
	 * serializes this OMComment into the writer
	 *
	 * @param writer
	 * @param cache
	 */
	private void serialize (XMLStreamWriter writer, boolean cache) throws XMLStreamException {
		writer.writeComment(this.value);
		OMNode nextSibling = this.getNextSibling();
        if (nextSibling != null) {
        	if(!cache)
        		nextSibling.serialize(writer);
        	else
        		nextSibling.serializeWithCache(writer);
        }
	}

	/**
	 * Method discard
	 *
	 */
	public void discard() throws OMException {
        if (done) {
            this.detach();
        } else {
            builder.discard(this.parent);
        }
    }
}
