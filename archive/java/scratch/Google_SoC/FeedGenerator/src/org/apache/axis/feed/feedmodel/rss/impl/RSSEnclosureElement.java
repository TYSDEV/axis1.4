package org.apache.axis.feed.feedmodel.rss.impl;

import org.apache.axis.feed.feedmodel.rss.EnclosureElement;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.net.URL;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 5, 2005
 * Time: 9:53:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class RSSEnclosureElement implements EnclosureElement {

    private URL url;
    private int lenght;
    private String MIMEType;

    public RSSEnclosureElement(URL url, int lenght, String MIMEType) {
        this.url = url;
        this.lenght = lenght;
        this.MIMEType = MIMEType;
    }

    public void serialize(XMLStreamWriter streamWriter) throws XMLStreamException {

    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public int getLenght() {
        return lenght;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
    }

    public String getMIMEType() {
        return MIMEType;
    }

    public void setMIMEType(String MIMEType) {
        this.MIMEType = MIMEType;
    }


}
