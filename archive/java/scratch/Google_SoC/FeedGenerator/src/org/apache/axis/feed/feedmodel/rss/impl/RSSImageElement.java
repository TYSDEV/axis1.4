package org.apache.axis.feed.feedmodel.rss.impl;

import org.apache.axis.feed.feedmodel.rss.Constants;
import org.apache.axis.feed.feedmodel.rss.ImageElement;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.net.URL;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 5, 2005
 * Time: 9:54:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class RSSImageElement implements ImageElement {

    private URL url;
    private URL link;
    private String title;

    private String description = null;
    private Integer height = null;
    private Integer width = null;

    public RSSImageElement(URL link, String title, URL url) {
        this.link = link;
        this.url = url;
        this.title = title;
    }

    public void serialize(XMLStreamWriter streamWriter) throws XMLStreamException {
        streamWriter.writeStartElement(Constants.RSS_PREFIX, Constants.IMAGE_TITLE_STRING, Constants.RSS_NAMESPACE_URI);
        streamWriter.writeCharacters(title);
        streamWriter.writeEndElement();
        streamWriter.writeStartElement(Constants.RSS_PREFIX, Constants.IMAGE_LINK_STRING, Constants.RSS_NAMESPACE_URI);
        streamWriter.writeCharacters(link.toString());
        streamWriter.writeEndElement();
        streamWriter.writeStartElement(Constants.RSS_PREFIX, Constants.IMAGE_URL_STRING, Constants.RSS_NAMESPACE_URI);
        streamWriter.writeCharacters(url.toString());
        streamWriter.writeEndElement();
        streamWriter.writeStartElement(Constants.RSS_PREFIX, Constants.IMAGE_WIDTH_STRING, Constants.RSS_NAMESPACE_URI);
        if (width != null)
            streamWriter.writeCharacters((width).toString());
        streamWriter.writeEndElement();
        streamWriter.writeStartElement(Constants.RSS_PREFIX, Constants.IMAGE_HEIGHT_STRING, Constants.RSS_NAMESPACE_URI);
        if (height != null)
            streamWriter.writeCharacters(height.toString());
        streamWriter.writeEndElement();
        streamWriter.writeStartElement(Constants.RSS_PREFIX, Constants.IMAGE__DESCRIPTION_STRING, Constants.RSS_NAMESPACE_URI);
        if (description != null)
            streamWriter.writeEndElement();
        streamWriter.writeCharacters(description);

    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public URL getLink() {
        return link;
    }

    public void setLink(URL link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }
}
