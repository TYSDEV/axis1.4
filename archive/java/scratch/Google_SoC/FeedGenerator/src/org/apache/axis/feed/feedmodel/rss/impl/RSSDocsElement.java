package org.apache.axis.feed.feedmodel.rss.impl;

import org.apache.axis.feed.feedmodel.rss.Constants;
import org.apache.axis.feed.feedmodel.rss.DocsElement;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.net.URL;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 22, 2005
 * Time: 10:36:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class RSSDocsElement implements DocsElement {
    private URL docsURL = null;

    public RSSDocsElement(URL docsURL) {
        this.docsURL = docsURL;
    }

    public void serialize(XMLStreamWriter streamWriter) throws XMLStreamException {
        streamWriter.writeStartElement(Constants.RSS_PREFIX, Constants.DOCS_ELEMENT_NAME, Constants.RSS_NAMESPACE_URI);
        if (docsURL != null)
            streamWriter.writeCharacters(docsURL.toString());
        streamWriter.writeEndElement();
    }

    public URL getDocsURL() {
        return docsURL;
    }

    public void setDocsURL(URL docsURL) {
        this.docsURL = docsURL;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RSSDocsElement)) return false;

        final RSSDocsElement rssDocsElement = (RSSDocsElement) o;

        if (docsURL != null ? !docsURL.equals(rssDocsElement.docsURL) : rssDocsElement.docsURL != null) return false;

        return true;
    }

    public int hashCode() {
        return (docsURL != null ? docsURL.hashCode() : 0);
    }
}
