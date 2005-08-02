package org.apache.axis.feed.feedmodel.atom;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 19, 2005
 * Time: 10:57:37 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ATOMEntry {
    public abstract void serialize(XMLStreamWriter streamWriter) throws XMLStreamException;

    public ContentElement getTitleElement();

    public void setTitleElement(ContentElement titleElement);

    public DateElement getModifiedElement();

    public void setModifiedElement(DateElement modifiedElement);

    public LinkElement getLinkElement();

    public void setLinkElement(LinkElement linkElement);

    public DateElement getIssuedElement();

    public void setIssuedElement(DateElement issuedElement);

    public ContentElement getAtomSummaryElement();

    public void setAtomSummaryElement(ContentElement atomSummaryElement);

    public ContentElement getAtomContentElement();

    public void setAtomContentElement(ContentElement atomContentElement);

    public IDElement getATOMId();

    public void setATOMId(IDElement ATOMId);
}
