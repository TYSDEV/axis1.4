package org.apache.axis.feed.feedmodel.rss.impl;

import org.apache.axis.feed.feedmodel.rss.*;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.net.URL;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 5, 2005
 * Time: 9:51:52 AM
 * To change this template use File | Settings | File Templates.
 */
public class RSSItem implements Item {

    private String title = null;
    private URL link = null;
    private String description = null;
    private SourceElement sourceElement = null;
    private EnclosureElement enclosureElement = null;
    private GuidElement guidElement = null;
    private PubDate pubDate = null;
    private CategoryElement categoryElement = null;


    public RSSItem() {
    }

    public RSSItem(String title, URL link, String description) {
        this.title = title;
        this.link = link;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public URL getLink() {
        return link;
    }

    public void setLink(URL link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void serialize(XMLStreamWriter streamWriter) throws XMLStreamException {
        streamWriter.writeStartElement(Constants.RSS_PREFIX, Constants.ITEM_NAME, Constants.RSS_NAMESPACE_URI);
        streamWriter.writeStartElement(Constants.RSS_PREFIX, Constants.ITEM_TITLE_STRING, Constants.RSS_NAMESPACE_URI);
        if (title != null)
            streamWriter.writeCharacters(title);
        streamWriter.writeEndElement();
        streamWriter.writeStartElement(Constants.RSS_PREFIX, Constants.ITEM_LINK_STRING, Constants.RSS_NAMESPACE_URI);
        if (link != null)
            streamWriter.writeCharacters(link.toString());
        streamWriter.writeEndElement();
        streamWriter.writeStartElement(Constants.RSS_PREFIX, Constants.ITEM_DESCRIPTON_STRING, Constants.RSS_NAMESPACE_URI);
        if (description != null)
            streamWriter.writeCharacters(description);
        streamWriter.writeEndElement();
        if (enclosureElement != null)
            enclosureElement.serialize(streamWriter);

        if (sourceElement != null)
            sourceElement.serialize(streamWriter);

        if (pubDate != null)
            pubDate.serialize(streamWriter);
        if (guidElement != null)
            guidElement.serialize(streamWriter);
        if (categoryElement != null)
            categoryElement.serialize(streamWriter);
        streamWriter.writeEndElement();
    }

    public void addSourceElement(SourceElement sourceElement) {
        this.sourceElement = sourceElement;
    }

    public void addEnclosureElement(EnclosureElement enclosureElement) {
        this.enclosureElement = enclosureElement;
    }

    public void addGuidElement(GuidElement guidElement) {
        this.guidElement = guidElement;

    }

    public void addPubDate(PubDate pubDate) {
        this.pubDate = pubDate;
    }

    public void addCategoryElement(CategoryElement categoryElement) {
        this.categoryElement = categoryElement;
    }

    public EnclosureElement getEnclosureElement() {
        return enclosureElement;
    }

    public GuidElement getGuidElement() {
        return guidElement;
    }

    public PubDate getPubDate() {
        return pubDate;
    }

    public SourceElement getSourceElement() {
        return sourceElement;
    }

    public CategoryElement getCategoryElement() {
        return categoryElement;
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RSSItem)) return false;

        final RSSItem rssItem = (RSSItem) o;

        if (categoryElement != null ? !categoryElement.equals(rssItem.categoryElement) : rssItem.categoryElement != null) return false;
        if (description != null ? !description.equals(rssItem.description) : rssItem.description != null) return false;
        if (enclosureElement != null ? !enclosureElement.equals(rssItem.enclosureElement) : rssItem.enclosureElement != null) return false;
        if (guidElement != null ? !guidElement.equals(rssItem.guidElement) : rssItem.guidElement != null) return false;
        if (link != null ? !link.equals(rssItem.link) : rssItem.link != null) return false;
        if (pubDate != null ? !pubDate.equals(rssItem.pubDate) : rssItem.pubDate != null) return false;
        if (sourceElement != null ? !sourceElement.equals(rssItem.sourceElement) : rssItem.sourceElement != null) return false;
        if (title != null ? !title.equals(rssItem.title) : rssItem.title != null) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (title != null ? title.hashCode() : 0);
        result = 29 * result + (link != null ? link.hashCode() : 0);
        result = 29 * result + (description != null ? description.hashCode() : 0);
        result = 29 * result + (sourceElement != null ? sourceElement.hashCode() : 0);
        result = 29 * result + (enclosureElement != null ? enclosureElement.hashCode() : 0);
        result = 29 * result + (guidElement != null ? guidElement.hashCode() : 0);
        result = 29 * result + (pubDate != null ? pubDate.hashCode() : 0);
        result = 29 * result + (categoryElement != null ? categoryElement.hashCode() : 0);
        return result;
    }
}
