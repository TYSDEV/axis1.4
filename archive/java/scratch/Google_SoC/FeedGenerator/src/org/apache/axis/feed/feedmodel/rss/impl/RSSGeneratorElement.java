package org.apache.axis.feed.feedmodel.rss.impl;

import org.apache.axis.feed.feedmodel.rss.Constants;
import org.apache.axis.feed.feedmodel.rss.GeneratorElement;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 22, 2005
 * Time: 10:35:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class RSSGeneratorElement implements GeneratorElement {
    private String generator = null;

    public RSSGeneratorElement(String generator) {

        this.generator = generator;
    }

    public void serialize(XMLStreamWriter streamWriter) throws XMLStreamException {
        streamWriter.writeStartElement(Constants.RSS_PREFIX, Constants.GENERATOR_ELEMT_NAME, Constants.RSS_NAMESPACE_URI);
        if (generator != null)
            streamWriter.writeCharacters(generator);
        streamWriter.writeEndElement();
    }

    public String getGenerator() {
        System.out.println(generator);
        return generator;
    }

    public void setGenerator(String generator) {
        this.generator = generator;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RSSGeneratorElement)) return false;

        final RSSGeneratorElement rssGeneratorElement = (RSSGeneratorElement) o;

        if (generator != null ? !generator.equals(rssGeneratorElement.generator) : rssGeneratorElement.generator != null) return false;

        return true;
    }

    public int hashCode() {
        return (generator != null ? generator.hashCode() : 0);
    }
}
