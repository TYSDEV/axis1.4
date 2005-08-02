package org.apache.axis.feed.feedmodel.atom.impl;

import org.apache.axis.feed.feedmodel.atom.PersonalElement;
import org.apache.axis.feed.feedmodel.atom.PersonalEmailElement;
import org.apache.axis.feed.feedmodel.atom.PersonalNameElement;
import org.apache.axis.feed.feedmodel.atom.PersonalURLElement;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 19, 2005
 * Time: 10:28:21 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class PersonalElementImpl implements PersonalElement {

    private PersonalNameElement name = null;
    private PersonalURLElement URL = null;
    private PersonalEmailElement email = null;

    public PersonalElementImpl(PersonalNameElement name) {
        this.name = name;
    }


    public void serialize(XMLStreamWriter streamWriter) throws XMLStreamException {
        if (name != null) {
            name.serialize(streamWriter);
        }
        if (email != null) {
            email.serialize(streamWriter);
        }
        if (URL != null) {
            URL.serialize(streamWriter);
        }
    }

    public PersonalNameElement getName() {
        return name;
    }

    public void setName(PersonalNameElement name) {
        this.name = name;
    }

    public PersonalURLElement getURL() {
        return URL;
    }

    public void setURL(PersonalURLElement URL) {
        this.URL = URL;
    }

    public PersonalEmailElement getEmail() {
        return email;
    }

    public void setEmail(PersonalEmailElement email) {
        this.email = email;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonalElementImpl)) return false;

        final PersonalElementImpl personalElement = (PersonalElementImpl) o;

        if (URL != null ? !URL.equals(personalElement.URL) : personalElement.URL != null) return false;
        if (email != null ? !email.equals(personalElement.email) : personalElement.email != null) return false;
        if (name != null ? !name.equals(personalElement.name) : personalElement.name != null) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (name != null ? name.hashCode() : 0);
        result = 29 * result + (URL != null ? URL.hashCode() : 0);
        result = 29 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
