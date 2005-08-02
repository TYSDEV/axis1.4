package org.apache.axis.feed.feedmodel.atom.impl;

import org.apache.axis.feed.feedmodel.atom.ATOMConstants;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;


/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 19, 2005
 * Time: 10:09:03 AM
 * To change this template use File | Settings | File Templates.
 */
public class ATOMSummaryElement extends ContentElementImpl {
    public ATOMSummaryElement(String content) {
        super(content);
    }


    public void serialize(XMLStreamWriter streamWriter) throws XMLStreamException {
        streamWriter.writeStartElement(ATOMConstants.NAMWSPASE_PREFIX, ATOMConstants.SUMMARY_ELEMENT_NAME, ATOMConstants.NAMESPASE_URL);
        super.serialize(streamWriter);
    }
}
