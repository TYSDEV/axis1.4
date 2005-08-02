package org.apache.axis.feed.feedmodel.atom.impl;

import org.apache.axis.feed.feedmodel.atom.*;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 19, 2005
 * Time: 11:40:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class ATOMFeedImpl implements org.apache.axis.feed.feedmodel.atom.ATOMFeed {

    private String versionAttr;
    private ContentElement titleElement;
    private DateElement modifiedElement;
    private LinkElement linkElement;
    private IDElement idElement = null;
    private ATOMAuthorElement authorElement;
    private ArrayList entryContainer = null;
    private int count = 1;

    public ATOMFeedImpl(String versionAttr, ContentElement titleElement, DateElement modifiedElement, LinkElement linkElement, ATOMAuthorElement authorElement) {
        this.versionAttr = versionAttr;
        this.titleElement = titleElement;
        this.modifiedElement = modifiedElement;
        this.linkElement = linkElement;
        this.authorElement = authorElement;
        entryContainer = new ArrayList();
    }

    public void serialize(XMLStreamWriter streamWriter) throws XMLStreamException {
        streamWriter.writeStartDocument();
        streamWriter.writeCharacters("\n");
        streamWriter.writeStartElement(ATOMConstants.FEED_ELEMENT_NAME);
        streamWriter.writeNamespace(ATOMConstants.NAMWSPASE_PREFIX, ATOMConstants.NAMESPASE_URL);
        streamWriter.writeAttribute(ATOMConstants.FEED_VERSION_ATTR, versionAttr);

        if (titleElement != null) {
            titleElement.serialize(streamWriter);
        }
        if (linkElement != null) {
            linkElement.serialize(streamWriter);
        }
        if (modifiedElement != null) {
            modifiedElement.serialize(streamWriter);
        }

        if (authorElement != null) {
            authorElement.serialize(streamWriter);

        }
        if (idElement != null) {
            idElement.serialize(streamWriter);
        }

        Iterator enumeration = this.getEntries();
        if (enumeration != null) {
            while (enumeration.hasNext()) {

                ((ATOMEntry) enumeration.next()).serialize(streamWriter);
            }
        }

        streamWriter.writeEndElement();
        streamWriter.writeEndDocument();
    }

    public void addEntry(ATOMEntry entryElement) {
        if (entryElement != null) {
            if (!entryContainer.contains(entryElement)) {
                entryContainer.add(entryElement);
            }
        }
    }

    public void remove(ATOMEntry atomEntry) {
        if (atomEntry != null)
            entryContainer.remove(atomEntry);
    }

    public Iterator getEntries() {
        return entryContainer.iterator();
    }

    public ContentElement getTitleElement() {
        return titleElement;
    }

    public void setTitleElement(ContentElement titleElement) {
        this.titleElement = titleElement;
    }

    public DateElement getModifiedElement() {
        return modifiedElement;
    }

    public void setModifiedElement(DateElement modifiedElement) {
        this.modifiedElement = modifiedElement;
    }

    public LinkElement getLinkElement() {
        return linkElement;
    }

    public void setLinkElement(LinkElement linkElement) {
        this.linkElement = linkElement;
    }

    public IDElement getIdElement() {
        return idElement;
    }

    public void setIdElement(IDElement idElement) {
        this.idElement = idElement;
    }

    public ATOMAuthorElement getAuthorElement() {
        return authorElement;
    }

    public void setAuthorElement(ATOMAuthorElement authorElement) {
        this.authorElement = authorElement;
    }

    public String getVersionAttr() {
        return versionAttr;
    }

    public void setVersionAttr(String versionAttr) {
        this.versionAttr = versionAttr;
    }
}

