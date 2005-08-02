package org.apache.axis.feed.feedmodel.atom;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 19, 2005
 * Time: 9:44:35 AM
 * To change this template use File | Settings | File Templates.
 */
public interface PersonalNameElement {
    public abstract void serialize(XMLStreamWriter streamWriter) throws XMLStreamException;

    public String getName();

    public void setName(String name);
}
