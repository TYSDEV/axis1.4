/*
 * Created on Jul 27, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.apache.axis.feed.feedmodel.atom.impl;

import junit.framework.TestCase;
import org.apache.axis.feed.feedmodel.atom.ContentElement;
import org.apache.axis.feed.feedmodel.atom.DateElement;
import org.apache.axis.feed.feedmodel.atom.LinkElement;
import org.apache.axis.feed.feedmodel.atom.ATOMEntry;
import org.apache.axis.feed.feedmodel.atom.factory.ATOMFactory;
import org.apache.axis.feed.feedmodel.AbstractFeedFactory;

import java.util.Date;


public class ATOMEntryElementTest extends TestCase {
    private ATOMEntry  atomEntry;
    private ContentElement titleElement;
    private DateElement modifiedElement;
    private LinkElement linkElement;
    private DateElement issuedElement;
    private ATOMID atomid;

    private ContentElement atomSummaryElement = null;
    private ContentElement atomContentElement = null;

	public static void main(String[] args) {
	}

	/*
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
        ATOMFactory fac = (ATOMFactory) AbstractFeedFactory.getFeedFactory("ATOM");
        titleElement = fac.createATOMTitleElement("entrytile");
        modifiedElement=   fac.createATOMModifiedElement(new Date());
        issuedElement=     fac.createATOMIsuuedElement(new Date());
        linkElement= fac.createATOMLinkElement("alternate", "http://www.ws.apache.org/", "text/html");
        atomEntry = fac.createEntryElement(titleElement, modifiedElement, linkElement,issuedElement );

	}

	/*
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Constructor for ATOMEntryElementTest.
	 * @param name
	 */
	public ATOMEntryElementTest(String name) {
		super(name);
	}

	public void testGetTitleElement() {
        assertNotNull(atomEntry.getTitleElement());
        assertEquals(titleElement,atomEntry.getTitleElement());
	}

	public void testSetTitleElement() {
         titleElement = new ATOMContentElement("new Title") ;
         assertNotSame(titleElement,atomEntry.getTitleElement());
         assertNotSame(titleElement.getContent(),atomEntry.getTitleElement().getContent());
         atomEntry.setTitleElement(titleElement);
         assertEquals(titleElement,atomEntry.getTitleElement());
	}

	public void testGetModifiedElement() {
         assertNotNull(atomEntry.getModifiedElement());
        assertEquals(modifiedElement,atomEntry.getModifiedElement());
	}

	public void testSetModifiedElement() {
        modifiedElement  = new ATOMModifiedElement(new Date()) ;
        atomEntry.setModifiedElement(modifiedElement);
        assertEquals(modifiedElement,atomEntry.getModifiedElement());
	}

	public void testGetLinkElement() {
        assertNotNull(atomEntry.getLinkElement());
        assertEquals(linkElement,atomEntry.getLinkElement());
	}

	public void testSetLinkElement() {
        linkElement= new ATOMLinkElement("alternate", "http://www.apache.org/", "text/html");
        assertNotSame(linkElement,atomEntry.getLinkElement());
        atomEntry.setLinkElement(linkElement);
        assertEquals(linkElement,atomEntry.getLinkElement());
	}

	public void testGetATOMId() {
       assertNull(atomEntry.getATOMId());
      atomid = new ATOMID("id");

      atomEntry.setATOMId(atomid);
        assertEquals(atomid,atomEntry.getATOMId());
	}

	public void testSetATOMId() {
        atomid = new ATOMID("id");
        atomEntry.setATOMId(atomid);
        assertEquals(atomid,atomEntry.getATOMId());
        atomid.setId("newId");
        ATOMID atomidNew =new ATOMID("newId");
        assertEquals(atomidNew.getId(),atomid.getId());
        assertNotSame("id",atomid.getId());
        assertEquals(atomidNew.getId(),atomEntry.getATOMId().getId());

	}

}
