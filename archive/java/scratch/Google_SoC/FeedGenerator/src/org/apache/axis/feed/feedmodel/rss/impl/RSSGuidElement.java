package org.apache.axis.feed.feedmodel.rss.impl;

import org.apache.axis.feed.feedmodel.rss.Constants;
import org.apache.axis.feed.feedmodel.rss.GuidElement;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 5, 2005
 * Time: 9:53:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class RSSGuidElement implements GuidElement {

    private String guid;
    private boolean isPermaLink = true;

    public RSSGuidElement(String guid) {
        this.guid = guid;
    }

    public RSSGuidElement(String guid, boolean permaLink) {
        this.guid = guid;
        isPermaLink = permaLink;
    }

    public void serialize(XMLStreamWriter streamWriter) throws XMLStreamException {
        streamWriter.writeStartElement(Constants.RSS_PREFIX, Constants.GUID_ID_STRING, Constants.RSS_NAMESPACE_URI);
        streamWriter.writeAttribute(Constants.RSS_PREFIX, Constants.GUID_ISPERMERLINK_ATTR, this.getSringofIsPermaLink());

        if (guid != null)
            streamWriter.writeCharacters(guid);
        streamWriter.writeEndElement();

    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public boolean isPermaLink() {
        return isPermaLink;
    }

    private String getSringofIsPermaLink() {
        String temp = "true";
        if (isPermaLink == false) {
            temp = "false";
        }
        return temp;
    }

    public void setPermaLink(boolean permaLink) {
        isPermaLink = permaLink;
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RSSGuidElement)) return false;

        final RSSGuidElement rssGuidElement = (RSSGuidElement) o;

        if (isPermaLink != rssGuidElement.isPermaLink) return false;
        if (guid != null ? !guid.equals(rssGuidElement.guid) : rssGuidElement.guid != null) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (guid != null ? guid.hashCode() : 0);
        result = 29 * result + (isPermaLink ? 1 : 0);
        return result;
    }
}
