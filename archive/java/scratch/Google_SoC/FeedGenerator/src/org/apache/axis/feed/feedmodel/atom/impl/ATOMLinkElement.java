package org.apache.axis.feed.feedmodel.atom.impl;

import org.apache.axis.feed.feedmodel.atom.ATOMConstants;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;


/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 19, 2005
 * Time: 9:57:53 AM
 * To change this template use File | Settings | File Templates.
 */
public class ATOMLinkElement extends LinkElementImpl {
    public ATOMLinkElement(String relAttribute, String herfAttribute, String typeAttribute) {
        super(relAttribute, herfAttribute, typeAttribute);
    }

    public void serialize(XMLStreamWriter streamWriter) throws XMLStreamException {
        streamWriter.writeStartElement(ATOMConstants.NAMWSPASE_PREFIX, ATOMConstants.LINK_ELEMENT_NAME, ATOMConstants.NAMESPASE_URL);
        super.serialize(streamWriter);

    }
}
