package org.apache.axis.feed.feedmodel.rss.factory;

import org.apache.axis.feed.feedmodel.FeedFactory;
import org.apache.axis.feed.feedmodel.rss.*;

import java.net.URL;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 5, 2005
 * Time: 9:24:45 AM
 * To change this template use File | Settings | File Templates.
 */
public interface RSSFactory extends FeedFactory {

    public Channel createChannel(String title, URL link, String description);

    public Item createItem();

    public Item createItem(String title, URL link, String description);

    // link of source = links to the XMLization of the source
    public SourceElement createSourceElement(String sourcename, String linkOfSource);

    public SourceElement createSourceElement(String sourcename);

    /*
    */
    public GuidElement createGuidElement(String guid);

    public GuidElement createGuidElement(String guid, boolean isPermaLink);

    public ImageElement createImageElement(URL URlofImage, String title, URL linkOfSite);

    public EnclosureElement createEnclosureElement(URL url, int lenght, String MIMEType);

    public CategoryElement createCategoryElement(String categoryName);

    public CategoryElement createCategoryElement(String categoryName, String domain);

    public CloudElement createCloudElement(int port, String path, String registerProcedure, String protocol, String domain);

    public LastBuildDate createLastBuildDate(Date lastBuilddate);

    public PubDate createPubDate(Date pubDate);

    public WebMasterElement createWebMaserElement(String webmaster);

    public DocsElement createDocsElement(URL docsURl);

    public ManagingEditorElement createManagingEditorElement(String managingEditorEmail);

    public CopyrightElement createCopyrightElement(String copyRight);

    public TtlElement createTtlElement(int ttl);

    public GeneratorElement createGeneratorElement(String generator);

    public LanguageElement createLanguageElement(String langauge);


}
