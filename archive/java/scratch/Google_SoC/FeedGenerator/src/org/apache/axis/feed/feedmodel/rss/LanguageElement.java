package org.apache.axis.feed.feedmodel.rss;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 22, 2005
 * Time: 10:31:52 AM
 * To change this template use File | Settings | File Templates.
 */
public interface LanguageElement {
    public abstract void serialize(XMLStreamWriter streamWriter) throws XMLStreamException;

    public String getLanguage();

    public void setLanguage(String language);

}
