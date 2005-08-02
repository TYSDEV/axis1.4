package org.apache.axis.feed.feedmodel;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 1, 2005
 * Time: 9:30:18 AM
 * To change this template use File | Settings | File Templates.
 */
public interface Feed {

    public abstract void serialize(XMLStreamWriter streamWriter) throws XMLStreamException;
}
