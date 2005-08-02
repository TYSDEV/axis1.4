package org.apache.axis.feed.feedmodel.rss.impl;

import org.apache.axis.feed.feedmodel.rss.Constants;
import org.apache.axis.feed.feedmodel.rss.SourceElement;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 5, 2005
 * Time: 9:54:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class RSSSourceElement implements SourceElement {

    private String sourcename;
    private String linkOfSource = null;

    public RSSSourceElement(String sourcename) {
        this.sourcename = sourcename;
    }

    public RSSSourceElement(String sourcename, String linkOfSource) {
        this.sourcename = sourcename;
        this.linkOfSource = linkOfSource;
    }

    public void serialize(XMLStreamWriter streamWriter) throws XMLStreamException {
        streamWriter.writeStartElement(Constants.RSS_PREFIX, Constants.SOURCE_NAME, Constants.RSS_NAMESPACE_URI);
        if (linkOfSource != null)
            streamWriter.writeAttribute(Constants.RSS_PREFIX, "url", linkOfSource);

        if (sourcename != null)
            streamWriter.writeCharacters(sourcename);
        streamWriter.writeEndElement();
    }

    public String getSourcename() {
        return sourcename;
    }

    public void setSourcename(String sourcename) {
        this.sourcename = sourcename;
    }

    public String getLinkOfSource() {
        return linkOfSource;
    }

    public void setLinkOfSource(String linkOfSource) {
        this.linkOfSource = linkOfSource;
    }


}
