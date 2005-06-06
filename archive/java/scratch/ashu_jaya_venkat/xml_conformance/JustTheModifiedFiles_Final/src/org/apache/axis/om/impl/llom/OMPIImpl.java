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

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.apache.axis.om.OMElement;
import org.apache.axis.om.OMException;
import org.apache.axis.om.OMNode;
import org.apache.axis.om.OMPI;

/**
 * @author sunja07
 *
 */
public class OMPIImpl extends OMNodeImpl implements OMPI {

	String piTarget;
	String value;

	/**
	 * Constructor OMPIImpl
	 *
	 * @param parent
	 */
	public OMPIImpl(OMElement parent, String piTarget, String content) {
		super(parent);
		this.piTarget = piTarget;
		this.value = content;
	}

	/**
	 * Method getTarget
	 *
	 * @return String
	 */
	public String getTarget() {
		return this.piTarget;
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
	 * Method getParent
	 *
	 * @return OMElement
	 */
	public OMElement getParent() throws OMException {
		return super.getParent();
	}

	/**
	 * Method serialize
	 *
	 * @param writer
	 * @param cache
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

	/**
	 * Method serialize
	 *
	 * @param writer
	 */
	public void serialize(XMLStreamWriter writer) throws XMLStreamException {
		serialize(writer,false);
	}

	/**
	 * Method serialize
	 *
	 * @param writer
	 */
	public void serializeWithCache(XMLStreamWriter writer) throws XMLStreamException {
		serialize(writer,true);
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
