package org.apache.axis.feed.feedmodel.atom.impl;

import org.apache.axis.feed.feedmodel.atom.ATOMConstants;
import org.apache.axis.feed.feedmodel.atom.PersonalNameElement;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 19, 2005
 * Time: 10:27:27 AM
 * To change this template use File | Settings | File Templates.
 */
public class PersonalNameElementImpl implements PersonalNameElement {
    private String name;

    public PersonalNameElementImpl(String name) {
        this.name = name;
    }

    public void serialize(XMLStreamWriter streamWriter) throws XMLStreamException {
        streamWriter.writeStartElement(ATOMConstants.NAMWSPASE_PREFIX, ATOMConstants.PERSONAL_NAME_ELEMENT_NAME, ATOMConstants.NAMESPASE_URL);
        if (name != null) {
            streamWriter.writeCharacters(name);

        }
        streamWriter.writeEndElement();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonalNameElementImpl)) return false;

        final PersonalNameElementImpl personalNameElement = (PersonalNameElementImpl) o;

        if (name != null ? !name.equals(personalNameElement.name) : personalNameElement.name != null) return false;

        return true;
    }

    public int hashCode() {
        return (name != null ? name.hashCode() : 0);
    }
}
