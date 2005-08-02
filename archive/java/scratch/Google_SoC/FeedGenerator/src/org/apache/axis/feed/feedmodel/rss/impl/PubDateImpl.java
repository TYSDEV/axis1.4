package org.apache.axis.feed.feedmodel.rss.impl;

import org.apache.axis.feed.feedmodel.rss.Constants;
import org.apache.axis.feed.feedmodel.rss.PubDate;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 5, 2005
 * Time: 10:23:56 AM
 * To change this template use File | Settings | File Templates.
 */
public class PubDateImpl implements PubDate {

    private Date pubDate = null;


    public PubDateImpl(Date pubDate) {
        this.pubDate = pubDate;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }


    public void serialize(XMLStreamWriter streamWriter) throws XMLStreamException {
        streamWriter.writeStartElement(Constants.RSS_PREFIX, Constants.PUB_DATE_NAME, Constants.RSS_NAMESPACE_URI);
        if (pubDate != null)
            streamWriter.writeCharacters(pubDate.toString());
        streamWriter.writeEndElement();
    }
}
