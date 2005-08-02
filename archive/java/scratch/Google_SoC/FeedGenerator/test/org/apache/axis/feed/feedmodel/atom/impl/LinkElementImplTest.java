/*
 * Created on Jul 28, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.apache.axis.feed.feedmodel.atom.impl;

import junit.framework.TestCase;
import org.apache.axis.feed.feedmodel.atom.LinkElement;

/**
 * @author INDIKA
 *         <p/>
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class LinkElementImplTest extends TestCase {
    private String relAttribute;
    private String typeAttribute;
    private String titleAttribute = null;
    private String herfAttribute;
    private LinkElement linkElement;

    public static void main(String[] args) {
    }

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        relAttribute = "alternate";
        typeAttribute = "text/html";
        titleAttribute = "Title";
        herfAttribute = "http://www.ws.apache.org/";
        linkElement = new ATOMLinkElement("alternate", "http://www.ws.apache.org/", "text/html");

    }


    /*
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Constructor for LinkElementImplTest.
     *
     * @param name
     */
    public LinkElementImplTest(String name) {
        super(name);
    }


    public void testGetRelAttribute() {
        assertNotNull(linkElement.getRelAttribute());
    }

    public void testSetRelAttribute() {
        linkElement.setRelAttribute(relAttribute);
        assertEquals(relAttribute, linkElement.getRelAttribute());

    }

    public void testGetTypeAttribute() {
        assertNotNull(linkElement.getTypeAttribute());
    }

    public void testSetTypeAttribute() {
        linkElement.setTypeAttribute(typeAttribute);
        assertEquals(typeAttribute, linkElement.getTypeAttribute());
    }

    public void testGetTitleAttribute() {
        assertNull(linkElement.getTitleAttribute());
    }

    public void testSetTitleAttribute() {
        linkElement.setTitleAttribute(titleAttribute);
        assertEquals(titleAttribute, linkElement.getTitleAttribute());
    }

    public void testGetHerfAttribute() {
        assertNotNull(linkElement.getHerfAttribute());
    }

    public void testSetHerfAttribute() {
        linkElement.setHerfAttribute(herfAttribute);
        assertEquals(herfAttribute, linkElement.getHerfAttribute());
    }

}
