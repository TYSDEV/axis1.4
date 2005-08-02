package org.apache.axis.feed.feedmodel.rss.impl;

import org.apache.axis.feed.feedmodel.rss.Constants;
import org.apache.axis.feed.feedmodel.rss.CopyrightElement;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 22, 2005
 * Time: 10:36:22 AM
 * To change this template use File | Settings | File Templates.
 */
public class RSSCopyrightElement implements CopyrightElement {
    private String copyright = null;

    public RSSCopyrightElement(String copyright) {
        this.copyright = copyright;
    }

    public void serialize(XMLStreamWriter streamWriter) throws XMLStreamException {
        streamWriter.writeStartElement(Constants.RSS_PREFIX, Constants.COPYRIGHT_ELEMNT_NAME, Constants.RSS_NAMESPACE_URI);
        if (copyright != null)
            streamWriter.writeCharacters(copyright);
        streamWriter.writeEndElement();
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }
}
