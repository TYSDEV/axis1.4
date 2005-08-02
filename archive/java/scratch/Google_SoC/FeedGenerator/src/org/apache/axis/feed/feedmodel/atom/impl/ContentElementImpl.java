package org.apache.axis.feed.feedmodel.atom.impl;

import org.apache.axis.feed.feedmodel.atom.ContentElement;
import org.apache.axis.feed.feedmodel.atom.ModeAttribute;
import org.apache.axis.feed.feedmodel.atom.TypeAttribute;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 19, 2005
 * Time: 10:29:13 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class ContentElementImpl implements ContentElement {

    private TypeAttribute typeAttribute = null;
    private ModeAttribute modeAttribute = null;
    private String content;


    protected ContentElementImpl(String content) {
        this.content = content;
    }


    public void serialize(XMLStreamWriter streamWriter) throws XMLStreamException {
        if (typeAttribute != null) {
            typeAttribute.serialize(streamWriter);
        }
        if (modeAttribute != null) {
            modeAttribute.serialize(streamWriter);
        }
        if (content != null)
            streamWriter.writeCharacters(content);
        streamWriter.writeEndElement();
    }


    public TypeAttribute getTypeAttribute() {
        return typeAttribute;
    }

    public void setTypeAttribute(TypeAttribute typeAttribute) {
        this.typeAttribute = typeAttribute;
    }

    public ModeAttribute getModeAttribute() {
        return modeAttribute;
    }

    public void setModeAttribute(ModeAttribute modeAttribute) {
        this.modeAttribute = modeAttribute;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContentElementImpl)) return false;

        final ContentElementImpl contentElement = (ContentElementImpl) o;

        if (content != null ? !content.equals(contentElement.content) : contentElement.content != null) return false;
        if (modeAttribute != null ? !modeAttribute.equals(contentElement.modeAttribute) : contentElement.modeAttribute != null) return false;
        if (typeAttribute != null ? !typeAttribute.equals(contentElement.typeAttribute) : contentElement.typeAttribute != null) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (typeAttribute != null ? typeAttribute.hashCode() : 0);
        result = 29 * result + (modeAttribute != null ? modeAttribute.hashCode() : 0);
        result = 29 * result + (content != null ? content.hashCode() : 0);
        return result;
    }
}
