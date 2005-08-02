package org.apache.axis.feed.feedmodel.atom;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 19, 2005
 * Time: 11:05:14 AM
 * To change this template use File | Settings | File Templates.
 */
public interface IDElement {
    public abstract void serialize(XMLStreamWriter streamWriter) throws XMLStreamException;

    public String getId();

    public void setId(String id);
}
