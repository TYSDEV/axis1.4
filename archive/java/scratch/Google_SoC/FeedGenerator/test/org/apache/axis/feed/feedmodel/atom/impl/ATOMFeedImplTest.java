/*
 * Created on Jul 27, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.apache.axis.feed.feedmodel.atom.impl;

import junit.framework.TestCase;
import org.apache.axis.feed.feedmodel.atom.*;
import org.apache.axis.feed.feedmodel.atom.factory.ATOMFactory;
import org.apache.axis.feed.feedmodel.AbstractFeedFactory;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author INDIKA
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ATOMFeedImplTest extends TestCase {
    private String versionAttr;
    private ContentElement titleElement;
    private DateElement modifiedElement;
    private DateElement issuedDateElement;
    private LinkElement linkElement;

    private PersonalElement authorElement;
    private ATOMEntry atomEntry;
    private ATOMFeed atomFeed;

	public static void main(String[] args) {

	}
     /*
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();

        ATOMFactory fac = (ATOMFactory) AbstractFeedFactory.getFeedFactory("ATOM");
        atomFeed = fac.createFeedElement("0.3", new ATOMTitleElement("title "), new ATOMModifiedElement(new Date()), fac.createATOMLinkElement("alternate", "http://www.ws.apache.org/", "text/html"), new ATOMAuthorElement(new PersonalNameElementImpl("auother name")));
        titleElement = fac.createATOMTitleElement("entrytile");
        modifiedElement=   fac.createATOMModifiedElement(new Date());
        issuedDateElement=     fac.createATOMIsuuedElement(new Date());
        authorElement= fac.createATOMAuthorElement(fac.createPersonalNameElement("indika"));
        linkElement= fac.createATOMLinkElement("alternate", "http://www.ws.apache.org/", "text/html");
        atomEntry = fac.createEntryElement(titleElement, modifiedElement, linkElement,issuedDateElement );


	}

	/*
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	public void testAddEntry() {
        assertFalse(atomFeed.getEntries().hasNext());
        atomFeed.addEntry(atomEntry);
        assertTrue(atomFeed.getEntries().hasNext());
	}

	public void testGetTitleElement() {
        assertNotNull(atomFeed.getTitleElement());
        assertNotSame(titleElement,atomFeed.getTitleElement());
	}

	public void testSetTitleElement() {
        assertNotSame(titleElement,atomFeed.getTitleElement());
        atomFeed.setTitleElement(titleElement);
        assertEquals(titleElement,atomFeed.getTitleElement());
	}

	public void testGetModifiedElement() {
        assertNotNull(atomFeed.getModifiedElement());
        assertNotSame(modifiedElement,atomFeed.getModifiedElement());

	}

	public void testSetModifiedElement() {
         assertNotSame(modifiedElement,atomFeed.getModifiedElement());
        atomFeed.setModifiedElement(modifiedElement);
        assertEquals(modifiedElement,atomFeed.getModifiedElement());
	}

	public void testGetLinkElement() {
        assertNotNull(atomFeed.getLinkElement());
        assertNotSame(linkElement,atomFeed.getLinkElement());

	}

	public void testSetLinkElement() {
         assertNotSame(linkElement,atomFeed.getLinkElement());
         atomFeed.setLinkElement(linkElement);
        assertEquals(linkElement,atomFeed.getLinkElement());

	}

	public void testGetVersionAttr() {
        assertNotNull(atomFeed.getVersionAttr());
	}

	public void testSetVersionAttr() {
        atomFeed.setVersionAttr("1.0");
        assertNotSame("0.3",atomFeed.getVersionAttr());
	}

}
