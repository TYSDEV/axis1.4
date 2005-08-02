package org.apache.axis.feed.feedmodel.atom;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 19, 2005
 * Time: 10:20:34 AM
 * To change this template use File | Settings | File Templates.
 */
public interface TypeAttribute {
    public abstract void serialize(XMLStreamWriter streamWriter) throws XMLStreamException;

    public String getMediaType();

    public void setMediaType(String mediaType);
}
