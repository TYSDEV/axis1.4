package org.apache.axis.feed.feedmodel.rss;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 5, 2005
 * Time: 10:19:39 AM
 * To change this template use File | Settings | File Templates.
 */
public interface CategoryElement {

    public abstract void serialize(XMLStreamWriter streamWriter) throws XMLStreamException;

    public String getCategoryName();

    public void setCategoryName(String categoryName);

    public String getDomain();

    public void setDomain(String domain);


}
