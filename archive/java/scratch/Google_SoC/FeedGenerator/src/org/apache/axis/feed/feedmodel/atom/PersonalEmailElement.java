package org.apache.axis.feed.feedmodel.atom;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 19, 2005
 * Time: 9:44:22 AM
 * To change this template use File | Settings | File Templates.
 */
public interface PersonalEmailElement {
    public abstract void serialize(XMLStreamWriter streamWriter) throws XMLStreamException;

    public String getEmail();

    public void setEmail(String email);
}
