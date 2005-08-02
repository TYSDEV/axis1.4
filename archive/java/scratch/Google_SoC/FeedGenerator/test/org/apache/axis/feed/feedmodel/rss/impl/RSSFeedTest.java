/*
 * Created on Jul 27, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.apache.axis.feed.feedmodel.rss.impl;

import junit.framework.TestCase;
import org.apache.axis.feed.feedmodel.rss.Channel;

import java.net.URL;

/**
 * @author INDIKA
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RSSFeedTest extends TestCase {
    private Channel  channel;
    private RSSFeed rssFeed;

	public static void main(String[] args) {
	}

	/*
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
        channel= new RSSChannel("axis2",new URL("http://ws.apache.org/axis2/"),"test case for feed channel");
        rssFeed = new RSSFeed("2.0") ;

	}

	/*
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Constructor for RSSFeedTest.
	 * @param name
	 */
	public RSSFeedTest(String name) {
		super(name);
	}

	public void testAddChannel() {
       rssFeed.addChannel(channel);
        assertNotNull(rssFeed.getChannel());
        assertEquals(channel,rssFeed.getChannel());
	}

	public void testGetVersion() {
        assertNotNull(rssFeed.getVersion());
        assertEquals("2.0",rssFeed.getVersion());
	}

	public void testGetChannel() {
         assertNull(rssFeed.getChannel());
	}

}
