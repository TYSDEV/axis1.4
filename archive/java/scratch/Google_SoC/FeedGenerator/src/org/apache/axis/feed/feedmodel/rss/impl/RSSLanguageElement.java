package org.apache.axis.feed.feedmodel.rss.impl;

import org.apache.axis.feed.feedmodel.rss.Constants;
import org.apache.axis.feed.feedmodel.rss.LanguageElement;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 22, 2005
 * Time: 10:35:15 AM
 * To change this template use File | Settings | File Templates.
 */
public class RSSLanguageElement implements LanguageElement {
    private String language = "en-us";

    public RSSLanguageElement(String language) {

        this.language = language;
    }

    public void serialize(XMLStreamWriter streamWriter) throws XMLStreamException {
        streamWriter.writeStartElement(Constants.RSS_PREFIX, Constants.LANGUAGE_ELEMNT_NAME, Constants.RSS_NAMESPACE_URI);
        if (language != null)
            streamWriter.writeCharacters(language);
        streamWriter.writeEndElement();
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RSSLanguageElement)) return false;

        final RSSLanguageElement rssLanguageElement = (RSSLanguageElement) o;

        if (language != null ? !language.equals(rssLanguageElement.language) : rssLanguageElement.language != null) return false;

        return true;
    }

    public int hashCode() {
        return (language != null ? language.hashCode() : 0);
    }
}
