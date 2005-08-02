package org.apache.axis.feed.feedmodel.rss;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.net.URL;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 5, 2005
 * Time: 10:20:12 AM
 * To change this template use File | Settings | File Templates.
 */
public interface EnclosureElement {

    public abstract void serialize(XMLStreamWriter streamWriter) throws XMLStreamException;

    public URL getUrl();

    public void setUrl(URL url);

    public int getLenght();

    public void setLenght(int lenght);

    public String getMIMEType();

    public void setMIMEType(String MIMEType);


}
