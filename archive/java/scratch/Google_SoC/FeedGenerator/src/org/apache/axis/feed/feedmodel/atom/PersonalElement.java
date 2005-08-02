package org.apache.axis.feed.feedmodel.atom;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 19, 2005
 * Time: 9:43:18 AM
 * To change this template use File | Settings | File Templates.
 */
public interface PersonalElement {
    public abstract void serialize(XMLStreamWriter streamWriter) throws XMLStreamException;


    public PersonalNameElement getName();

    public void setName(PersonalNameElement name);

    public PersonalURLElement getURL();

    public void setURL(PersonalURLElement URL);

    public PersonalEmailElement getEmail();

    public void setEmail(PersonalEmailElement email);


}
