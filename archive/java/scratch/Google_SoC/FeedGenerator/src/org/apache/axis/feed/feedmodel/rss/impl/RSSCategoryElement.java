package org.apache.axis.feed.feedmodel.rss.impl;

import org.apache.axis.feed.feedmodel.rss.CategoryElement;
import org.apache.axis.feed.feedmodel.rss.Constants;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 5, 2005
 * Time: 9:52:57 AM
 * To change this template use File | Settings | File Templates.
 */
public class RSSCategoryElement implements CategoryElement {

    private String categoryName = null;
    private String domain = null;

    public RSSCategoryElement(String categoryName) {
        this.categoryName = categoryName;
    }

    public RSSCategoryElement(String domain, String categoryName) {
        this.domain = domain;
        this.categoryName = categoryName;
    }

    public void serialize(XMLStreamWriter streamWriter) throws XMLStreamException {
        streamWriter.writeStartElement(Constants.RSS_PREFIX, Constants.CATEGORY_STRING, Constants.RSS_NAMESPACE_URI);
        if (domain != null)
            streamWriter.writeAttribute(Constants.RSS_PREFIX, Constants.CATEGORY_DOMAIN_ATTR, domain);

        if (categoryName != null)
            streamWriter.writeCharacters(categoryName);
        streamWriter.writeEndElement();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }


}
