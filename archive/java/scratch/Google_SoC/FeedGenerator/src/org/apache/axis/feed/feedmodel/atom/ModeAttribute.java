package org.apache.axis.feed.feedmodel.atom;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 19, 2005
 * Time: 9:42:01 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ModeAttribute {
    public abstract void serialize(XMLStreamWriter streamWriter) throws XMLStreamException;

    public String getMode();

    public void setMode(String mode);
}
