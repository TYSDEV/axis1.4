package org.apache.axis.feed.feedmodel.rss;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 22, 2005
 * Time: 10:30:51 AM
 * To change this template use File | Settings | File Templates.
 */
public interface GeneratorElement {
    public abstract void serialize(XMLStreamWriter streamWriter) throws XMLStreamException;

    public String getGenerator();

    public void setGenerator(String generator);
}
