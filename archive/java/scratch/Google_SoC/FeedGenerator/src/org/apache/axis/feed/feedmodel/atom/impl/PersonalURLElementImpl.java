package org.apache.axis.feed.feedmodel.atom.impl;

import org.apache.axis.feed.feedmodel.atom.ATOMConstants;
import org.apache.axis.feed.feedmodel.atom.PersonalURLElement;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.net.URL;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 19, 2005
 * Time: 10:27:13 AM
 * To change this template use File | Settings | File Templates.
 */
public class PersonalURLElementImpl implements PersonalURLElement {
    private URL url;

    public PersonalURLElementImpl(URL url) {
        this.url = url;
    }

    public void serialize(XMLStreamWriter streamWriter) throws XMLStreamException {
        streamWriter.writeStartElement(ATOMConstants.NAMWSPASE_PREFIX, ATOMConstants.PERSONAL_URL_ELEMENT_NAME, ATOMConstants.NAMESPASE_URL);
        if (url != null)
            streamWriter.writeCharacters(url.toString());
        streamWriter.writeEndElement();
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }
}
