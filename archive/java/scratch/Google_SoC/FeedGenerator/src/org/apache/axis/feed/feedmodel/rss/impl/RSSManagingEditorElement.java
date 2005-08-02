package org.apache.axis.feed.feedmodel.rss.impl;

import org.apache.axis.feed.feedmodel.rss.Constants;
import org.apache.axis.feed.feedmodel.rss.ManagingEditorElement;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 22, 2005
 * Time: 10:34:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class RSSManagingEditorElement implements ManagingEditorElement {
    private String managingEditor = null;

    public RSSManagingEditorElement(String managingEditor) {
        this.managingEditor = managingEditor;
    }

    public void serialize(XMLStreamWriter streamWriter) throws XMLStreamException {
        streamWriter.writeStartElement(Constants.RSS_PREFIX, Constants.MANAGINGEDITOR_ELEMNT_NAME, Constants.RSS_NAMESPACE_URI);
        if (managingEditor != null)
            streamWriter.writeCharacters(managingEditor);
        streamWriter.writeEndElement();
    }

    public String getManagingEditor() {
        return managingEditor;
    }

    public void setManagingEditor(String managingEditor) {
        this.managingEditor = managingEditor;
    }
}
