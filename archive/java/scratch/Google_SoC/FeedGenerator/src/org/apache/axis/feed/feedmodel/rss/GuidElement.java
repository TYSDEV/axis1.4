package org.apache.axis.feed.feedmodel.rss;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 5, 2005
 * Time: 10:20:30 AM
 * To change this template use File | Settings | File Templates.
 */
public interface GuidElement {

    public abstract void serialize(XMLStreamWriter streamWriter) throws XMLStreamException;

    public String getGuid();

    public void setGuid(String guid);

    public boolean isPermaLink();

    public void setPermaLink(boolean permaLink);


}
