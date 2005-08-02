package org.apache.axis.feed.feedmodel.atom.impl;

import org.apache.axis.feed.feedmodel.atom.IDElement;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 19, 2005
 * Time: 11:05:32 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class IDElementImpl implements IDElement {
    private String id;

    public IDElementImpl(String id) {
        this.id = id;
    }

    public void serialize(XMLStreamWriter streamWriter) throws XMLStreamException {
        if (id != null) {
            streamWriter.writeCharacters(id);
        }
        streamWriter.writeEndElement();

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IDElementImpl)) return false;

        final IDElementImpl idElement = (IDElementImpl) o;

        if (id != null ? !id.equals(idElement.id) : idElement.id != null) return false;

        return true;
    }

    public int hashCode() {
        return (id != null ? id.hashCode() : 0);
    }
}
