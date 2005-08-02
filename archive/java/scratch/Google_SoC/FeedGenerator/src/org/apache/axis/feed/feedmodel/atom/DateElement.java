package org.apache.axis.feed.feedmodel.atom;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 19, 2005
 * Time: 10:25:27 AM
 * To change this template use File | Settings | File Templates.
 */
public interface DateElement {

    public abstract void serialize(XMLStreamWriter streamWriter) throws XMLStreamException;

    public Date getDate();

    public void setDate(Date date);

}
