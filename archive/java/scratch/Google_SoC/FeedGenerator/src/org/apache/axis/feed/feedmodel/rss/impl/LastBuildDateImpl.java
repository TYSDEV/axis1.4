package org.apache.axis.feed.feedmodel.rss.impl;

import org.apache.axis.feed.feedmodel.rss.Constants;
import org.apache.axis.feed.feedmodel.rss.LastBuildDate;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 5, 2005
 * Time: 10:24:16 AM
 * To change this template use File | Settings | File Templates.
 */
public class LastBuildDateImpl implements LastBuildDate, Constants {

    private Date lastBuildDate = null;

    public LastBuildDateImpl(Date lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }

    public Date getLastBuildDate() {
        return lastBuildDate;
    }

    public void setLastBuildDate(Date lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }


    public void serialize(XMLStreamWriter streamWriter) throws XMLStreamException {
        streamWriter.writeStartElement(Constants.RSS_PREFIX, Constants.LAST_BUILD_DATE_NAME, Constants.RSS_NAMESPACE_URI);
        if (lastBuildDate != null)
            streamWriter.writeCharacters(lastBuildDate.toString());
        streamWriter.writeEndElement();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LastBuildDateImpl)) return false;

        final LastBuildDateImpl lastBuildDate1 = (LastBuildDateImpl) o;

        if (lastBuildDate != null ? !lastBuildDate.equals(lastBuildDate1.lastBuildDate) : lastBuildDate1.lastBuildDate != null) return false;

        return true;
    }

    public int hashCode() {
        return (lastBuildDate != null ? lastBuildDate.hashCode() : 0);
    }
}
