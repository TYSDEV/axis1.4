package org.apache.axis.feed.feedmodel.rss.impl;

import org.apache.axis.feed.feedmodel.rss.*;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 5, 2005
 * Time: 9:51:27 AM
 * To change this template use File | Settings | File Templates.
 */
public class RSSChannel implements Channel {

    private TtlElement ttlElement = null;
    private ManagingEditorElement managingEditorElement = null;
    private GeneratorElement generatorElement = null;
    private CopyrightElement copyrightElement = null;
    private LanguageElement languageElement = null;
    private WebMasterElement webMasterElement = null;
    private DocsElement docsElement = null;
    private String title;
    private URL link;
    private String description;
    private CategoryElement categoryElement = null;
    private ImageElement imageElement = null;
    private CloudElement cloudElement = null;
    private PubDate pubDate = null;
    private LastBuildDate lastBuildDate = null;
    private int count = 0;
    private ArrayList categoryList = null;
    private ArrayList itemContainer = null;

    public RSSChannel(String title, URL link, String description) {
        itemContainer = new ArrayList();
        this.title = title;
        this.link = link;
        this.description = description;
    }

    public void addItem(Item item) {
        if (!itemContainer.contains(item)) {
            itemContainer.add(item);
            count++;
        }
    }

    public void remove(Item item) {
        if (itemContainer.contains(item)) {
            if (itemContainer.remove(item)) ;
        }
    }

    public void addImageElement(ImageElement imageElement) {
        this.imageElement = imageElement;
    }

    public void addCloudElement(CloudElement cloudElement) {
        this.cloudElement = cloudElement;
    }

    public void addPudDate(PubDate pubDate) {
        this.pubDate = pubDate;
    }

    public void addLastBuildDate(LastBuildDate lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public URL getLink() {
        return link;
    }

    public void setLink(URL link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void serialize(XMLStreamWriter streamWriter) throws XMLStreamException {
        streamWriter.writeStartElement(Constants.RSS_PREFIX, Constants.CHANNEL_NAME, Constants.RSS_NAMESPACE_URI);

        streamWriter.writeStartElement(Constants.RSS_PREFIX, Constants.CHANNEL_TITLE_STRING, Constants.RSS_NAMESPACE_URI);
        if (title != null)
            streamWriter.writeCharacters(title);
        streamWriter.writeEndElement();
        streamWriter.writeStartElement(Constants.RSS_PREFIX, Constants.CHANNEL_LINK_STRING, Constants.RSS_NAMESPACE_URI);
        if (link != null)
            streamWriter.writeCharacters(link.toString());
        streamWriter.writeEndElement();
        streamWriter.writeStartElement(Constants.RSS_PREFIX, Constants.CHANNEL_DESCRIPTON_STRING, Constants.RSS_NAMESPACE_URI);
        if (description != null)
            streamWriter.writeCharacters(description);
        streamWriter.writeEndElement();
        if (cloudElement != null)
            cloudElement.serialize(streamWriter);
        if (pubDate != null)
            pubDate.serialize(streamWriter);
        if (lastBuildDate != null)
            lastBuildDate.serialize(streamWriter);
        if (categoryElement != null)
            categoryElement.serialize(streamWriter);
        if (generatorElement != null)
            generatorElement.serialize(streamWriter);
        if (copyrightElement != null)
            copyrightElement.serialize(streamWriter);
        if (docsElement != null)
            docsElement.serialize(streamWriter);
        if (webMasterElement != null) {
            webMasterElement.serialize(streamWriter);
        }
        if (managingEditorElement != null)
            managingEditorElement.serialize(streamWriter);
        if (languageElement != null)
            languageElement.serialize(streamWriter);
        if (ttlElement != null)
            ttlElement.serialize(streamWriter);


        Iterator enumeration = this.getItems();
        if (enumeration != null) {
            while (enumeration.hasNext()) {

                ((Item) enumeration.next()).serialize(streamWriter);
            }
        }

        streamWriter.writeEndElement();
    }

    public CategoryElement getCategoryElement() {
        return categoryElement;
    }

    public void addCategoryElement(CategoryElement categoryElement) {
        this.categoryElement = categoryElement;
        categoryList = new ArrayList();
        categoryList.add(categoryElement);

    }


    public CloudElement getCloudElement() {
        return cloudElement;
    }

    public PubDate getPubDate() {
        return pubDate;
    }

    public LastBuildDate getLastBuildDate() {
        return lastBuildDate;
    }


    public ImageElement getImageElement() {
        return imageElement;
    }

    public Iterator getItems() {
        Iterator enumeration = null;
        if (itemContainer != null) {
            enumeration = this.itemContainer.iterator();
        }
        return enumeration;
    }


    public TtlElement getTtlElement() {
        return ttlElement;
    }

    public void setTtlElement(TtlElement ttlElement) {
        this.ttlElement = ttlElement;
    }

    public ManagingEditorElement getManagingEditorElement() {
        return managingEditorElement;
    }

    public void setManagingEditorElement(ManagingEditorElement managingEditorElement) {
        this.managingEditorElement = managingEditorElement;
    }

    public GeneratorElement getGeneratorElement() {
        return generatorElement;
    }

    public void setGeneratorElement(GeneratorElement generatorElement) {
        this.generatorElement = generatorElement;
    }

    public WebMasterElement getWebMasterElement() {
        return webMasterElement;
    }

    public void setWebMasterElement(WebMasterElement webMasterElement) {
        this.webMasterElement = webMasterElement;
    }

    public DocsElement getDocsElement() {
        return docsElement;
    }

    public void setDocsElement(DocsElement docsElement) {
        this.docsElement = docsElement;
    }

    public LanguageElement getLanguageElement() {
        return languageElement;
    }

    public void setLanguageElement(LanguageElement languageElement) {
        this.languageElement = languageElement;
    }

    public CopyrightElement getCopyrightElement() {
        return copyrightElement;
    }

    public void setCopyrightElement(CopyrightElement copyrightElement) {
        this.copyrightElement = copyrightElement;
    }
}
