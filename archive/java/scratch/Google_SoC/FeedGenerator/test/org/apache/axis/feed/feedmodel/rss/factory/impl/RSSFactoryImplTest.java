/*
 * Created on Jul 28, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.apache.axis.feed.feedmodel.rss.factory.impl;

import junit.framework.TestCase;
import org.apache.axis.feed.feedmodel.rss.*;
import org.apache.axis.feed.feedmodel.rss.factory.RSSFactory;
import org.apache.axis.feed.feedmodel.rss.impl.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author INDIKA
 *         <p/>
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class RSSFactoryImplTest extends TestCase {

    private RSSFactory feedFactory;

    private Item item = null;


    private GuidElement guidElement = null;

    private LastBuildDate lastBuildDate = null;
    private String domain = null;

    private GeneratorElement generatorElement;

    private LanguageElement languageElement;

    private DocsElement docsElement;


    public static void main(String[] args) {
    }

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        feedFactory = new RSSFactoryImpl();
    }

    /*
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Constructor for RSSFactoryImplTest.
     *
     * @param name
     */
    public RSSFactoryImplTest(String name) {
        super(name);
    }


    /*
     * Class under test for Item createItem()
     */
    public void testCreateItem() {
        item = new RSSItem();
        assertEquals(item, feedFactory.createItem());
    }

    /*
     * Class under test for Item createItem(String, URL, String)
     */
    public void testCreateItemStringURLString() {
        try {
            item = new RSSItem("title", new URL("http://www.google.com/"), "description");
            assertEquals(item, feedFactory.createItem("title", new URL("http://www.google.com/"), "description"));

        } catch (MalformedURLException e) {
            fail(e.toString());
        }


    }


    /*
     * Class under test for GuidElement createGuidElement(String)
     */
    public void testCreateGuidElementString() {
        guidElement = new RSSGuidElement("123");
        assertEquals(guidElement, feedFactory.createGuidElement("123"));
    }

    /*
     * Class under test for GuidElement createGuidElement(String, boolean)
     */
    public void testCreateGuidElementStringboolean() {
        guidElement = new RSSGuidElement("123", false);
        assertEquals(guidElement, feedFactory.createGuidElement("123", false));
    }


    public void testCreateLastBuildDate() {
        lastBuildDate = new LastBuildDateImpl(new Date());
        assertEquals(lastBuildDate, feedFactory.createLastBuildDate(new Date()));

    }


    public void testCreateDocsElement() {
        try {
            docsElement = new RSSDocsElement(new URL("http://www.google.com/"));
            assertEquals(docsElement, feedFactory.createDocsElement(new URL("http://www.google.com/")));
        } catch (MalformedURLException e) {
            fail(e.toString());
        }


    }


    public void testCreateGeneratorElement() {
        generatorElement = new RSSGeneratorElement("feeder 2.0");
        assertEquals(generatorElement, feedFactory.createGeneratorElement("feeder 2.0"));
    }

    public void testCreateLanguageElement() {
        languageElement = new RSSLanguageElement("en-us");
        assertEquals(languageElement, feedFactory.createLanguageElement("en-us"));
    }

}
