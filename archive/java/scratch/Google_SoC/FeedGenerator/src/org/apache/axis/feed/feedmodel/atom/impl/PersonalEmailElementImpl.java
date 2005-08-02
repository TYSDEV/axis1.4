package org.apache.axis.feed.feedmodel.atom.impl;

import org.apache.axis.feed.feedmodel.atom.ATOMConstants;
import org.apache.axis.feed.feedmodel.atom.PersonalEmailElement;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 19, 2005
 * Time: 10:27:42 AM
 * To change this template use File | Settings | File Templates.
 */
public class PersonalEmailElementImpl implements PersonalEmailElement {
    private String email;

    public PersonalEmailElementImpl(String email) {
        this.email = email;
    }

    public void serialize(XMLStreamWriter streamWriter) throws XMLStreamException {
        streamWriter.writeStartElement(ATOMConstants.NAMWSPASE_PREFIX, ATOMConstants.PERSONAL_EMAIL_ELEMENT_NAME, ATOMConstants.NAMESPASE_URL);
        if (email != null)
            streamWriter.writeCharacters(email);
        streamWriter.writeEndElement();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
