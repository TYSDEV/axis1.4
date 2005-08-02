package org.apache.axis.feed.feedmodel.atom;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 19, 2005
 * Time: 9:58:37 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ContentElement {
    public abstract void serialize(XMLStreamWriter streamWriter) throws XMLStreamException;

    public TypeAttribute getTypeAttribute();

    public void setTypeAttribute(TypeAttribute typeAttribute);

    public ModeAttribute getModeAttribute();


    public void setModeAttribute(ModeAttribute modeAttribute);

    public String getContent();

    public void setContent(String content);
}
