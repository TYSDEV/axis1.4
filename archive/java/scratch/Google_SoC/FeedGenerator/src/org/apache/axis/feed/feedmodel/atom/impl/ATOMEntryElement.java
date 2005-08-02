package org.apache.axis.feed.feedmodel.atom.impl;

import org.apache.axis.feed.feedmodel.atom.*;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 19, 2005
 * Time: 10:04:19 AM
 * To change this template use File | Settings | File Templates.
 */
public class ATOMEntryElement implements ATOMEntry {

    private ContentElement titleElement;
    private DateElement modifiedElement;
    private LinkElement linkElement;
    private DateElement issuedElement;

    private ContentElement atomSummaryElement = null;
    private ContentElement atomContentElement = null;

    private IDElement ATOMId = null;

    public ATOMEntryElement(ContentElement titleElement, DateElement modifiedElement, LinkElement linkElement, DateElement issuedElement) {
        this.titleElement = titleElement;
        this.modifiedElement = modifiedElement;
        this.linkElement = linkElement;
        this.issuedElement = issuedElement;
    }

    public void serialize(XMLStreamWriter streamWriter) throws XMLStreamException {
        streamWriter.writeStartElement(ATOMConstants.NAMWSPASE_PREFIX, ATOMConstants.ENTRY_ELEMENT_NAME, ATOMConstants.NAMESPASE_URL);

        if (titleElement != null) {
            titleElement.serialize(streamWriter);
        }
        if (linkElement != null) {
            linkElement.serialize(streamWriter);
        }
        if (modifiedElement != null) {
            modifiedElement.serialize(streamWriter);
        }
        if (issuedElement != null) {
            issuedElement.serialize(streamWriter);
        }
        if (atomContentElement != null) {
            atomContentElement.serialize(streamWriter);
        }
        if (atomSummaryElement != null) {
            atomSummaryElement.serialize(streamWriter);
        }
        if (ATOMId != null) {
            ATOMId.serialize(streamWriter);
        }
        streamWriter.writeEndElement();

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

    public DateElement getIssuedElement() {
        return issuedElement;
    }

    public void setIssuedElement(DateElement issuedElement) {
        this.issuedElement = issuedElement;
    }

    public ContentElement getAtomSummaryElement() {
        return atomSummaryElement;
    }

    public void setAtomSummaryElement(ContentElement atomSummaryElement) {
        this.atomSummaryElement = atomSummaryElement;
    }

    public ContentElement getAtomContentElement() {
        return atomContentElement;
    }

    public void setAtomContentElement(ContentElement atomContentElement) {
        this.atomContentElement = atomContentElement;
    }

    public IDElement getATOMId() {
        return ATOMId;
    }

    public void setATOMId(IDElement ATOMId) {
        this.ATOMId = ATOMId;
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ATOMEntryElement)) return false;

        final ATOMEntryElement atomEntryElement = (ATOMEntryElement) o;

        if (atomContentElement != null ? !atomContentElement.equals(atomEntryElement.atomContentElement) : atomEntryElement.atomContentElement != null) return false;
        if (atomSummaryElement != null ? !atomSummaryElement.equals(atomEntryElement.atomSummaryElement) : atomEntryElement.atomSummaryElement != null) return false;
        if (linkElement != null ? !linkElement.equals(atomEntryElement.linkElement) : atomEntryElement.linkElement != null) return false;
        if (titleElement != null ? !titleElement.equals(atomEntryElement.titleElement) : atomEntryElement.titleElement != null) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (titleElement != null ? titleElement.hashCode() : 0);
        result = 29 * result + (linkElement != null ? linkElement.hashCode() : 0);
        result = 29 * result + (atomSummaryElement != null ? atomSummaryElement.hashCode() : 0);
        result = 29 * result + (atomContentElement != null ? atomContentElement.hashCode() : 0);
        return result;
    }
}
