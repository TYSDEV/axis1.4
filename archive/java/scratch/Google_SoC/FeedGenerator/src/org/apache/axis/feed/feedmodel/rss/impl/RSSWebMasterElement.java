package org.apache.axis.feed.feedmodel.rss.impl;

import org.apache.axis.feed.feedmodel.rss.Constants;
import org.apache.axis.feed.feedmodel.rss.WebMasterElement;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 22, 2005
 * Time: 10:34:23 AM
 * To change this template use File | Settings | File Templates.
 */
public class RSSWebMasterElement implements WebMasterElement {
    private String webMaster = null;

    public RSSWebMasterElement(String webMaster) {
        this.webMaster = webMaster;
    }

    public void serialize(XMLStreamWriter streamWriter) throws XMLStreamException {
        streamWriter.writeStartElement(Constants.RSS_PREFIX, Constants.TTL_ELEMENT_NAME, Constants.RSS_NAMESPACE_URI);
//        if (new Integer(webMaster) != null)
        ///    streamWriter.writeCharacters(new Integer(webMaster).toString());
        streamWriter.writeEndElement();
    }

    public String getWebMaster() {
        return webMaster;
    }

    public void setWebMaster(String webMaster) {
        this.webMaster = webMaster;
    }
}
