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

import org.apache.axis.om.OMDTD;
import org.apache.axis.om.OMElement;
import org.apache.axis.om.OMException;
import org.apache.axis.om.OMNode;

/**
 * @author sunja07
 *
 */
public class OMDTDImpl extends OMNodeImpl implements OMDTD {

	String value;

	public OMDTDImpl(OMElement parentNode, String content) {
		super(parentNode);
		this.value = content;
	}

	/**
	 * Method serialize
	 *
	 * @param writer
	 * @param cache
	 */
	private void serialize(XMLStreamWriter writer, boolean cache)
			throws XMLStreamException {
		writer.writeDTD(this.value);
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
	 * Method serializeWithCache
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
