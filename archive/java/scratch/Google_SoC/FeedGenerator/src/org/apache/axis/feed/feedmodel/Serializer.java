package org.apache.axis.feed.feedmodel;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 9, 2005
 * Time: 1:48:11 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Serializer {
    public abstract void serialize(XMLStreamWriter streamWriter) throws XMLStreamException;
}
