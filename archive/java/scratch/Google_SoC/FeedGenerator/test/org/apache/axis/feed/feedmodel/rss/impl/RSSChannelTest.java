/*
 * Created on Jul 27, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.apache.axis.feed.feedmodel.rss.impl;

import junit.framework.TestCase;
import org.apache.axis.feed.feedmodel.rss.*;

import java.net.URL;
import java.util.Iterator;
import java.util.Date;


public class RSSChannelTest extends TestCase {

    private Channel  channel;
    private Item item;
    private PubDate pubDate;
    private LastBuildDate lastBuildDate;

    private CategoryElement categoryElement;

	public static void main(String[] args) {

	}

	/*
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
        channel= new RSSChannel("axis2",new URL("http://ws.apache.org/axis2/"),"test case for feed channel");
         pubDate = new PubDateImpl(new Date());
        categoryElement= new RSSCategoryElement("web servise");
        lastBuildDate = new LastBuildDateImpl(new Date());
	}

	/*
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Constructor for RSSChannelTest.
	 * @param name
	 */
	public RSSChannelTest(String name) {
		super(name);
	}

	public void testAddItem() {

        assertNotNull(channel);
        assertNotNull(channel.getItems());
        Iterator iterator =  channel.getItems();
        assertFalse(iterator.hasNext());
        item =new RSSItem();
        channel.addItem(item);
        assertTrue(iterator.hasNext());
        iterator= channel.getItems();
        assertNotNull(iterator.next());



	}

	public void testRemove() {
        item = new RSSItem();
        channel.remove(item);
        channel.addItem(item);
        assertTrue(channel.getItems().hasNext());
        channel.remove(item);
        assertFalse(channel.getItems().hasNext());

	}

	public void testAddPudDate() {
         assertNull(channel.getPubDate());
        channel.addPudDate(pubDate);
        assertNotNull(channel.getPubDate());
        assertEquals(pubDate,channel.getPubDate());
	}

	public void testAddLastBuildDate() {
         assertNull(channel.getLastBuildDate());
        channel.addLastBuildDate(lastBuildDate);
        assertNotNull(channel.getLastBuildDate());
        assertEquals(lastBuildDate,channel.getLastBuildDate());
	}

	public void testGetTitle() {
        assertNotNull(channel.getTitle());
	}

	public void testGetLink() {
        assertNotNull(channel.getLink());
	}

	public void testGetDescription() {
        assertNotNull(channel.getDescription());
	}

	public void testAddCategoryElement() {
        assertNull(channel.getCategoryElement());
        assertNotNull(categoryElement.getCategoryName());
        categoryElement.setDomain("axis2");
        assertEquals("axis2",categoryElement.getDomain());
        channel.addCategoryElement(categoryElement);
        assertNotNull(channel.getCategoryElement());
        assertEquals(categoryElement,channel.getCategoryElement());
	}

	public void testGetPubDate() {
        assertNull(channel.getPubDate());
        channel.addPudDate(pubDate);
        assertNotNull(channel.getPubDate());
        assertEquals(pubDate,channel.getPubDate());
	}

	public void testGetLastBuildDate() {
        assertNull(channel.getLastBuildDate());
        channel.addLastBuildDate(lastBuildDate);
        assertNotNull(channel.getLastBuildDate());
        assertEquals(lastBuildDate,channel.getLastBuildDate());

	}

}
