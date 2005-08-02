package org.apache.axis.feed.feedmodel.rss;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 5, 2005
 * Time: 10:21:06 AM
 * To change this template use File | Settings | File Templates.
 */
public interface SourceElement {

    public abstract void serialize(XMLStreamWriter streamWriter) throws XMLStreamException;

    public String getSourcename();

    public void setSourcename(String sourcename);

    public String getLinkOfSource();

    public void setLinkOfSource(String linkOfSource);


}
