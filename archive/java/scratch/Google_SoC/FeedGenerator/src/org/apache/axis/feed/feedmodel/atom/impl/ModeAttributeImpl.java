package org.apache.axis.feed.feedmodel.atom.impl;

import org.apache.axis.feed.feedmodel.atom.ATOMConstants;
import org.apache.axis.feed.feedmodel.atom.ModeAttribute;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 19, 2005
 * Time: 10:26:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class ModeAttributeImpl implements ModeAttribute {

    private String mode = ATOMConstants.XML_MODE_ARRTIBUTE;

    public ModeAttributeImpl(String mode) {
        this.mode = mode;
    }

    public void serialize(XMLStreamWriter streamWriter) throws XMLStreamException {
        if (mode != null) {
            streamWriter.writeAttribute(ATOMConstants.NAMWSPASE_PREFIX, ATOMConstants.MODE_ARRTIBUTE, mode);

        }
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        if (mode != null)
            if (mode.matches(ATOMConstants.BASE64_MODE_ARRTIBUTE) || mode.matches(ATOMConstants.ESCAPED_MODE_ARRTIBUTE) || mode.matches(ATOMConstants.XML_MODE_ARRTIBUTE)) {
                this.mode = mode;
            }
    }
}
