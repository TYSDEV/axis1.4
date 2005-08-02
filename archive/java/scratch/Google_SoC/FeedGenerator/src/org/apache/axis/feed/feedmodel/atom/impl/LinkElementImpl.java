package org.apache.axis.feed.feedmodel.atom.impl;

import org.apache.axis.feed.feedmodel.atom.ATOMConstants;
import org.apache.axis.feed.feedmodel.atom.LinkElement;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 19, 2005
 * Time: 10:47:50 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class LinkElementImpl implements LinkElement {
    private String relAttribute;
    private String typeAttribute;
    private String titleAttribute = null;
    private String herfAttribute;

    public LinkElementImpl(String relAttribute, String herfAttribute, String typeAttribute) {
        this.relAttribute = relAttribute;
        this.herfAttribute = herfAttribute;
        this.typeAttribute = typeAttribute;
    }

    public void serialize(XMLStreamWriter streamWriter) throws XMLStreamException {
        if (relAttribute != null) {
            streamWriter.writeAttribute(ATOMConstants.NAMWSPASE_PREFIX, ATOMConstants.LINK_REL_ATTR, relAttribute);

        }
        if (typeAttribute != null) {
            streamWriter.writeAttribute(ATOMConstants.NAMWSPASE_PREFIX, ATOMConstants.LINK_TYPE_ATTR, typeAttribute);

        }
        if (herfAttribute != null) {
            streamWriter.writeAttribute(ATOMConstants.NAMWSPASE_PREFIX, ATOMConstants.LINK_HERF_ATTR, herfAttribute);

        }
        if (titleAttribute != null) {
            streamWriter.writeAttribute(ATOMConstants.NAMWSPASE_PREFIX, ATOMConstants.LINK_TITLE_ATTR, titleAttribute);

        }
        streamWriter.writeEndElement();
    }

    public String getRelAttribute() {
        return relAttribute;
    }

    public void setRelAttribute(String relAttribute) {
        this.relAttribute = relAttribute;
    }

    public String getTypeAttribute() {
        return typeAttribute;
    }

    public void setTypeAttribute(String typeAttribute) {
        this.typeAttribute = typeAttribute;
    }

    public String getTitleAttribute() {
        return titleAttribute;
    }

    public void setTitleAttribute(String titleAttribute) {
        this.titleAttribute = titleAttribute;
    }

    public String getHerfAttribute() {
        return herfAttribute;
    }

    public void setHerfAttribute(String herfAttribute) {
        this.herfAttribute = herfAttribute;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LinkElementImpl)) return false;

        final LinkElementImpl linkElement = (LinkElementImpl) o;

        if (herfAttribute != null ? !herfAttribute.equals(linkElement.herfAttribute) : linkElement.herfAttribute != null) return false;
        if (relAttribute != null ? !relAttribute.equals(linkElement.relAttribute) : linkElement.relAttribute != null) return false;
        if (typeAttribute != null ? !typeAttribute.equals(linkElement.typeAttribute) : linkElement.typeAttribute != null) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (relAttribute != null ? relAttribute.hashCode() : 0);
        result = 29 * result + (typeAttribute != null ? typeAttribute.hashCode() : 0);
        result = 29 * result + (herfAttribute != null ? herfAttribute.hashCode() : 0);
        return result;
    }
}
