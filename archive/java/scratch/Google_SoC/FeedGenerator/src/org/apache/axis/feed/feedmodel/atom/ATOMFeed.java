package org.apache.axis.feed.feedmodel.atom;

import org.apache.axis.feed.feedmodel.atom.impl.ATOMAuthorElement;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 19, 2005
 * Time: 10:58:07 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ATOMFeed {
    public abstract void serialize(XMLStreamWriter streamWriter) throws XMLStreamException;

    public void addEntry(ATOMEntry entryElement);

    public void remove(ATOMEntry atomEntry);

    public Iterator getEntries();

    public ContentElement getTitleElement();

    public void setTitleElement(ContentElement titleElement);

    public DateElement getModifiedElement();

    public void setModifiedElement(DateElement modifiedElement);

    public LinkElement getLinkElement();

    public void setLinkElement(LinkElement linkElement);

    public IDElement getIdElement();

    public void setIdElement(IDElement idElement);

    public ATOMAuthorElement getAuthorElement();

    public void setAuthorElement(ATOMAuthorElement authorElement);

    public String getVersionAttr();

    public void setVersionAttr(String versionAttr);
}
