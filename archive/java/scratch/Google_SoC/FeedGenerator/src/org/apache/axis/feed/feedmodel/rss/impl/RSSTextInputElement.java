package org.apache.axis.feed.feedmodel.rss.impl;

import org.apache.axis.feed.feedmodel.rss.TextInputElement;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.net.URL;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 5, 2005
 * Time: 11:33:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class RSSTextInputElement implements TextInputElement {

    private String title;
    private String description;
    private String name;
    private URL link;

    public RSSTextInputElement(String title, String description, String name, URL link) {
        this.title = title;
        this.description = description;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public URL getLink() {
        return link;
    }

    public void setLink(URL link) {
        this.link = link;
    }

    public void serialize(XMLStreamWriter streamWriter) throws XMLStreamException {

    }


}
