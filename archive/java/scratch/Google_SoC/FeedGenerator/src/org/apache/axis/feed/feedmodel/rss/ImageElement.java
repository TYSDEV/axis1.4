package org.apache.axis.feed.feedmodel.rss;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.net.URL;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 5, 2005
 * Time: 10:20:44 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ImageElement {

    public abstract void serialize(XMLStreamWriter streamWriter) throws XMLStreamException;

    public URL getUrl();

    public void setUrl(URL url);

    public URL getLink();

    public void setLink(URL link);

    public String getTitle();

    public void setTitle(String title);

    public String getDescription();

    public void setDescription(String description);


    public Integer getHeight();

    public void setHeight(Integer height);

    public Integer getWidth();

    public void setWidth(Integer width);

}
