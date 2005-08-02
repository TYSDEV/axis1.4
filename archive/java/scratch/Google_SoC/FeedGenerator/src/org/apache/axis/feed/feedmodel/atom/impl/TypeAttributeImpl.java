package org.apache.axis.feed.feedmodel.atom.impl;

import org.apache.axis.feed.feedmodel.atom.ATOMConstants;
import org.apache.axis.feed.feedmodel.atom.TypeAttribute;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 19, 2005
 * Time: 10:27:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class TypeAttributeImpl implements TypeAttribute {
    private String mediaType = "text/plain";

    public TypeAttributeImpl(String mediaType) {
        this.mediaType = mediaType;
    }

    public void serialize(XMLStreamWriter streamWriter) throws XMLStreamException {
        if (mediaType != null) {
            streamWriter.writeAttribute(ATOMConstants.NAMWSPASE_PREFIX, ATOMConstants.TYPE_ARRTIBUTE, mediaType);

        }
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }
}
