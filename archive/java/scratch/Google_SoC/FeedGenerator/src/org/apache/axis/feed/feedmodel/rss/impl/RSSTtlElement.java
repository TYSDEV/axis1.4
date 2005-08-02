package org.apache.axis.feed.feedmodel.rss.impl;

import org.apache.axis.feed.feedmodel.rss.Constants;
import org.apache.axis.feed.feedmodel.rss.TtlElement;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 22, 2005
 * Time: 10:34:41 AM
 * To change this template use File | Settings | File Templates.
 */
public class RSSTtlElement implements TtlElement {
    private int ttl = 0;

    public RSSTtlElement(int ttl) {
        this.ttl = ttl;
    }

    public void serialize(XMLStreamWriter streamWriter) throws XMLStreamException {
        streamWriter.writeStartElement(Constants.RSS_PREFIX, Constants.TTL_ELEMENT_NAME, Constants.RSS_NAMESPACE_URI);
        if (new Integer(ttl) != null)
            streamWriter.writeCharacters(new Integer(ttl).toString());
        streamWriter.writeEndElement();
    }

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }
}
