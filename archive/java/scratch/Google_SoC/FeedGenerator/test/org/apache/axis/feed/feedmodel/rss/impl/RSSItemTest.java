/*
 * Created on Jul 27, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.apache.axis.feed.feedmodel.rss.impl;

import junit.framework.TestCase;
import org.apache.axis.feed.feedmodel.rss.Item;
import org.apache.axis.feed.feedmodel.rss.GuidElement;
import org.apache.axis.feed.feedmodel.rss.PubDate;
import org.apache.axis.feed.feedmodel.rss.CategoryElement;

import java.net.URL;
import java.net.MalformedURLException;
import java.util.Date;

/**
 * @author INDIKA
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RSSItemTest extends TestCase {
	private Item item;
    private URL link;
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
		item = new RSSItem();
         guidElement = new RSSGuidElement("guid");
        pubDate = new PubDateImpl(new Date());
        categoryElement= new RSSCategoryElement("web servise");


	}

	/*
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Constructor for RSSItemTest.
	 * @param name
	 */
	public RSSItemTest(String name) {
		super(name);
	}

	public void testGetTitle() {
        assertEquals(null,item.getTitle());
        item.setTitle("title");
        assertEquals("title",item.getTitle());
	}

	public void testSetTitle() {
        assertNotSame("titleNEW",item.getTitle());
        item.setTitle("titleNEW");
        assertEquals("titleNEW",item.getTitle());
	}

	public void testGetLink() {
        assertEquals(null,item.getLink());
        try {
            item.setLink(link=new URL("http://www.apache.org/"));
        } catch (MalformedURLException e) {
            fail(e.toString());

        }
        assertEquals(link,item.getLink());
	}

	public void testSetLink() {
        try {
            link= new URL("http://ws.apache.org/axis2/")   ;

        } catch (MalformedURLException e) {
            fail(e.toString());
        }
        assertNotSame(link,item.getLink());
        item.setLink(link);
        assertEquals(link,item.getLink());
    }

	public void testAddGuidElement() {

        assertNull(item.getGuidElement());
        assertTrue(guidElement.isPermaLink());
        guidElement.setPermaLink(false);
        assertFalse(guidElement.isPermaLink());
        item.addGuidElement(guidElement);
        assertNotNull(item.getGuidElement());
        assertEquals(guidElement,item.getGuidElement());
	}

	public void testAddPubDate() {
        assertNull(item.getPubDate());
        item.addPubDate(pubDate);
        assertNotNull(item.getPubDate());
        assertEquals(pubDate,item.getPubDate());

	}

	public void testAddCategoryElement() {
        assertNull(item.getCategoryElement());
        assertNotNull(categoryElement.getCategoryName());
        categoryElement.setDomain("axis2");
        assertEquals("axis2",categoryElement.getDomain());
        item.addCategoryElement(categoryElement);
        assertNotNull(item.getCategoryElement());
        assertEquals(categoryElement,item.getCategoryElement());
	}

	public void testGetGuidElement() {

        assertNotSame(guidElement,item.getGuidElement());
        assertNull(item.getGuidElement());
        item.addGuidElement(guidElement);
        assertEquals(guidElement.getGuid(),item.getGuidElement().getGuid());
        guidElement.setGuid("guidNEW");
        assertEquals(guidElement,item.getGuidElement());
        assertEquals("guidNEW",item.getGuidElement().getGuid());
	}

	public void testGetPubDate() {
        assertNull(item.getPubDate());
        item.addPubDate(pubDate);
        assertNotNull(item.getPubDate());
        assertEquals(pubDate,item.getPubDate());
	}

	public void testGetCategoryElement() {
        assertNull(item.getCategoryElement());
        assertNotNull(categoryElement.getCategoryName());
        categoryElement.setDomain("axis2");
        assertEquals("axis2",categoryElement.getDomain());
        item.addCategoryElement(categoryElement);
        assertNotNull(item.getCategoryElement());
        assertEquals(categoryElement,item.getCategoryElement());

	}

}
