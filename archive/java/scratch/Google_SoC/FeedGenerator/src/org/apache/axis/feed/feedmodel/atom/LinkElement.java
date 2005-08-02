package org.apache.axis.feed.feedmodel.atom;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 19, 2005
 * Time: 10:47:42 AM
 * To change this template use File | Settings | File Templates.
 */
public interface LinkElement {
    public abstract void serialize(XMLStreamWriter streamWriter) throws XMLStreamException;

    public String getRelAttribute();

    public void setRelAttribute(String relAttribute);

    public String getTypeAttribute();

    public void setTypeAttribute(String typeAttribute);

    public String getTitleAttribute();

    public void setTitleAttribute(String titleAttribute);

    public String getHerfAttribute();

    public void setHerfAttribute(String herfAttribute);
}
