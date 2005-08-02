package org.apache.axis.feed.feedmodel.atom.impl;

import org.apache.axis.feed.feedmodel.atom.DateElement;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 19, 2005
 * Time: 10:25:59 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class DateElementImpl implements DateElement {
    private Date date;

    public DateElementImpl(Date date) {
        this.date = date;
    }

    public void serialize(XMLStreamWriter streamWriter) throws XMLStreamException {
        if (date != null)
            streamWriter.writeCharacters(date.toString());
        streamWriter.writeEndElement();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DateElementImpl)) return false;

        final DateElementImpl dateElement = (DateElementImpl) o;

        if (date != null ? !date.equals(dateElement.date) : dateElement.date != null) return false;

        return true;
    }

    public int hashCode() {
        return (date != null ? date.hashCode() : 0);
    }
}
