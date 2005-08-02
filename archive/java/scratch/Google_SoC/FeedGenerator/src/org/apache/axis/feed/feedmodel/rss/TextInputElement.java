package org.apache.axis.feed.feedmodel.rss;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 5, 2005
 * Time: 11:33:03 AM
 * To change this template use File | Settings | File Templates.
 */
public interface TextInputElement {

    public abstract void serialize(XMLStreamWriter streamWriter) throws XMLStreamException;


}
