package org.apache.axis.feed.feedmodel.rss.impl;

import junit.framework.TestCase;
import org.apache.axis.feed.feedmodel.AbstractFeedFactory;
import org.apache.axis.feed.feedmodel.rss.*;
import org.apache.axis.feed.feedmodel.rss.factory.RSSFactory;

import java.net.URL;
import java.util.Iterator;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 27, 2005
 * Time: 3:48:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class RSSModelTest extends TestCase {

    private RSSFeed rssFeed;
    private Channel channel;
    private Item item;
    private String description;
    private String title;
    private URL link;
    private SourceElement sourceElement;

    private LastBuildDate lastBuildDate ;

    private GuidElement guidElement;
    private PubDate pubDate;
    private CategoryElement categoryElement;


    public static void main(String[] args) {
    }

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        RSSFactory fac = (RSSFactory) AbstractFeedFactory.getFeedFactory("RSS");
        rssFeed = (RSSFeed) fac.createFeed("2.0");
        channel = fac.createChannel("title", new URL("http://www.apache.org"), "feedChannel");
        rssFeed.addChannel(channel);
        item = fac.createItem();
        description = "descriptoon";
        title = "tilte";
        link = new URL("http://ws.apache.org/axis2/");
        sourceElement = new RSSSourceElement("sourceElement");
        guidElement = new RSSGuidElement("guidElement") ;
        pubDate= new PubDateImpl(new Date()) ;
        categoryElement = new RSSCategoryElement("category");
        lastBuildDate= new LastBuildDateImpl(new Date()) ;
        item.setTitle(title);
        item.setDescription(description);
        item.setLink(link);
        rssFeed.getChannel().addItem(item);
        item.addGuidElement(guidElement);
        item.addSourceElement(sourceElement);
        item.addPubDate(pubDate);
        item.addCategoryElement(categoryElement);
        channel.addLastBuildDate(lastBuildDate);

    }

    public void testAll() {

        assertEquals(channel, rssFeed.getChannel());
        Iterator iterator = rssFeed.getChannel().getItems();
        while (iterator.hasNext()) {
            Item item1 = (Item) iterator.next();
            assertEquals(item, item1);
            assertEquals(description, item1.getDescription());
            assertEquals(title, item1.getTitle());
            assertEquals(link, item1.getLink());
            assertEquals(sourceElement,item1.getSourceElement());
            assertEquals(guidElement,item1.getGuidElement());
            assertEquals(pubDate,item1.getPubDate());
            assertEquals(sourceElement,item1.getSourceElement());
            assertEquals(categoryElement,item1.getCategoryElement());

        }
        assertEquals(lastBuildDate,rssFeed.getChannel().getLastBuildDate());

    }

    /*
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
    }


}
