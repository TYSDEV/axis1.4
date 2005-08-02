package org.apache.axis.feed.feedmodel.rss;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.net.URL;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 5, 2005
 * Time: 10:19:50 AM
 * To change this template use File | Settings | File Templates.
 */
public interface Channel {

    public void addItem(Item item);

    public void remove(Item item);

    public void addImageElement(ImageElement imageElement);

    public void addCloudElement(CloudElement cloudElement);

    public void addPudDate(PubDate pubDate);

    public void addLastBuildDate(LastBuildDate lastBuildDate);

    public String getTitle();

    public void setTitle(String title);

    public URL getLink();

    public void setLink(URL link);

    public String getDescription();

    public CategoryElement getCategoryElement();

    public void addCategoryElement(CategoryElement categoryElement);

    public Iterator getItems();


    public void setDescription(String description);


    public TtlElement getTtlElement();

    public void setTtlElement(TtlElement ttlElement);

    public ManagingEditorElement getManagingEditorElement();

    public void setManagingEditorElement(ManagingEditorElement managingEditorElement);

    public GeneratorElement getGeneratorElement();

    public CloudElement getCloudElement();

    public PubDate getPubDate();

    public LastBuildDate getLastBuildDate();

    public void setGeneratorElement(GeneratorElement generatorElement);

    public WebMasterElement getWebMasterElement();

    public void setWebMasterElement(WebMasterElement webMasterElement);

    public DocsElement getDocsElement();

    public void setDocsElement(DocsElement docsElement);

    public LanguageElement getLanguageElement();

    public void setLanguageElement(LanguageElement languageElement);

    public CopyrightElement getCopyrightElement();

    public void setCopyrightElement(CopyrightElement copyrightElement);

    public abstract void serialize(XMLStreamWriter streamWriter) throws XMLStreamException;

}
