package org.apache.axis.feed.feedmodel.rss.impl;

import org.apache.axis.feed.feedmodel.Feed;
import org.apache.axis.feed.feedmodel.rss.Channel;
import org.apache.axis.feed.feedmodel.rss.Constants;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 5, 2005
 * Time: 9:51:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class RSSFeed implements Feed {

    private String version;
    private Channel channel;

    public RSSFeed(String version) {
        this.version = version;
    }

    public void addChannel(Channel channel) {

        this.channel = channel;
    }


    public String getVersion() {
        return version;
    }


    public void serialize(XMLStreamWriter streamWriter) throws XMLStreamException {
        streamWriter.writeStartDocument();
        streamWriter.writeCharacters("\n");
        streamWriter.writeStartElement(Constants.RSS_NAME);
        streamWriter.writeNamespace(Constants.RSS_PREFIX, Constants.RSS_NAMESPACE_URI);
        streamWriter.writeAttribute(Constants.RSS_VERSION_ATTR, version);

        Channel channeltemp = this.getChannel();

        (channeltemp).serialize(streamWriter);

        streamWriter.writeEndElement();
        streamWriter.writeEndDocument();

    }

    public Channel getChannel() {
        return channel;
    }
}
