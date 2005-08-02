package org.apache.axis.feed.feedmodel.rss;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.net.URL;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 5, 2005
 * Time: 10:20:55 AM
 * To change this template use File | Settings | File Templates.
 */
public interface Item {

    public abstract void serialize(XMLStreamWriter streamWriter) throws XMLStreamException;

    public void addSourceElement(SourceElement sourceElement);

    public void addEnclosureElement(EnclosureElement enclosureElement);

    public void addGuidElement(GuidElement guidElement);

    public void addPubDate(PubDate pubDate);

    public void addCategoryElement(CategoryElement categoryElement);

    public String getTitle();

    public void setTitle(String title);

    public URL getLink();

    public void setLink(URL link);

    public String getDescription();

    public void setDescription(String description);

    public EnclosureElement getEnclosureElement();

    public GuidElement getGuidElement();

    public PubDate getPubDate();

    public SourceElement getSourceElement();

    public CategoryElement getCategoryElement();


}
