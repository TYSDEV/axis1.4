package org.apache.axis.feed.feedmodel.atom;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.net.URL;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 19, 2005
 * Time: 9:44:04 AM
 * To change this template use File | Settings | File Templates.
 */
public interface PersonalURLElement {
    public abstract void serialize(XMLStreamWriter streamWriter) throws XMLStreamException;

    public URL getUrl();

    public void setUrl(URL url);
}
