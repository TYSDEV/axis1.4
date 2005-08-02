package org.apache.axis.feed.feedmodel.atom.impl;

import org.apache.axis.feed.feedmodel.atom.ATOMConstants;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 19, 2005
 * Time: 10:03:42 AM
 * To change this template use File | Settings | File Templates.
 */
public class ATOMModifiedElement extends DateElementImpl {
    public ATOMModifiedElement(Date date) {
        super(date);
    }

    public void serialize(XMLStreamWriter streamWriter) throws XMLStreamException {
        streamWriter.writeStartElement(ATOMConstants.NAMWSPASE_PREFIX, ATOMConstants.MODIFIED_DATE_ELEMET_NAME, ATOMConstants.NAMESPASE_URL);

        super.serialize(streamWriter);
    }
}
