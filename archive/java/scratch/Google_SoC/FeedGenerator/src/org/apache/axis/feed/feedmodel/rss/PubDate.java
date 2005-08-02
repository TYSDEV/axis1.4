package org.apache.axis.feed.feedmodel.rss;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 5, 2005
 * Time: 10:22:57 AM
 * To change this template use File | Settings | File Templates.
 */
public interface PubDate {

    public abstract void serialize(XMLStreamWriter streamWriter) throws XMLStreamException;

    public Date getPubDate();

    public void setPubDate(Date pubDate);


}
