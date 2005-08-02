package org.apache.axis.feed.feedmodel.atom.impl;

import org.apache.axis.feed.feedmodel.atom.ATOMConstants;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 19, 2005
 * Time: 10:08:31 AM
 * To change this template use File | Settings | File Templates.
 */
public class ATOMIssuedElement extends DateElementImpl {

    public ATOMIssuedElement(Date date) {
        super(date);
    }

    public void serialize(XMLStreamWriter streamWriter) throws XMLStreamException {
        streamWriter.writeStartElement(ATOMConstants.NAMWSPASE_PREFIX, ATOMConstants.ISSUED_DATE_ELEMET_NAME, ATOMConstants.NAMESPASE_URL);

        super.serialize(streamWriter);
    }
}
