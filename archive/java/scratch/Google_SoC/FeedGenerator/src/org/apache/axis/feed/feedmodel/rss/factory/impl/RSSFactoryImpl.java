package org.apache.axis.feed.feedmodel.rss.factory.impl;


import org.apache.axis.feed.feedmodel.Feed;
import org.apache.axis.feed.feedmodel.rss.*;
import org.apache.axis.feed.feedmodel.rss.factory.RSSFactory;
import org.apache.axis.feed.feedmodel.rss.impl.*;

import java.net.URL;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 5, 2005
 * Time: 9:29:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class RSSFactoryImpl implements RSSFactory {

    public Feed createFeed(String version) {
        return new RSSFeed(version);  //To change body of implemented methods use File | Settings | File Templates.
    }


    public Channel createChannel(String title, URL link, String description) {
        return new RSSChannel(title, link, description);  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Item createItem() {
        return new RSSItem();  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Item createItem(String title, URL link, String description) {
        return new RSSItem(title, link, description);  //To change body of implemented methods use File | Settings | File Templates.
    }

    // link of source = links to the XMLization of the source
    public SourceElement createSourceElement(String sourcename, String linkOfSource) {
        return new RSSSourceElement(sourcename, linkOfSource);  //To change body of implemented methods use File | Settings | File Templates.
    }

    public SourceElement createSourceElement(String sourcename) {
        return new RSSSourceElement(sourcename);  //To change body of implemented methods use File | Settings | File Templates.
    }

    /*
    */
    public GuidElement createGuidElement(String guid) {
        return new RSSGuidElement(guid);  //To change body of implemented methods use File | Settings | File Templates.
    }

    public GuidElement createGuidElement(String guid, boolean isPermaLink) {
        return new RSSGuidElement(guid, isPermaLink);  //To change body of implemented methods use File | Settings | File Templates.
    }

    public ImageElement createImageElement(URL URlofImage, String title, URL linkOfSite) {
        return new RSSImageElement(URlofImage, title, linkOfSite);  //To change body of implemented methods use File | Settings | File Templates.
    }

    public EnclosureElement createEnclosureElement(URL url, int lenght, String MIMEType) {
        return new RSSEnclosureElement(url, lenght, MIMEType);  //To change body of implemented methods use File | Settings | File Templates.
    }

    public CategoryElement createCategoryElement(String categoryName) {
        return new RSSCategoryElement(categoryName);  //To change body of implemented methods use File | Settings | File Templates.
    }

    public CategoryElement createCategoryElement(String categoryName, String domain) {
        return new RSSCategoryElement(categoryName, domain);  //To change body of implemented methods use File | Settings | File Templates.
    }

    public CloudElement createCloudElement(int port, String path, String registerProcedure, String protocol, String domain) {
        return new RSSCloudElement(port, path, registerProcedure, protocol, domain);  //To change body of implemented methods use File | Settings | File Templates.
    }

    public LastBuildDate createLastBuildDate(Date lastBuilddate) {
        return new LastBuildDateImpl(lastBuilddate);  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PubDate createPubDate(Date pubDate) {
        return new PubDateImpl(pubDate);  //To change body of implemented methods use File | Settings | File Templates.
    }

    public WebMasterElement createWebMaserElement(String webmaster) {
        return new RSSWebMasterElement(webmaster);
    }

    public DocsElement createDocsElement(URL docsURl) {
        return new RSSDocsElement(docsURl);
    }

    public ManagingEditorElement createManagingEditorElement(String managingEditorEmail) {
        return new RSSManagingEditorElement(managingEditorEmail);
    }

    public CopyrightElement createCopyrightElement(String copyRight) {
        return new RSSCopyrightElement(copyRight);
    }

    public TtlElement createTtlElement(int ttl) {
        return new RSSTtlElement(ttl);
    }

    public GeneratorElement createGeneratorElement(String generator) {
        return new RSSGeneratorElement(generator);
    }

    public LanguageElement createLanguageElement(String langauge) {
        return new RSSLanguageElement(langauge);
    }
}
