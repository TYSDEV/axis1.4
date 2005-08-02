package org.apache.axis.feed.feedmodel.atom.impl;

import junit.framework.TestCase;

import java.util.Date;
import java.util.Iterator;

import org.apache.axis.feed.feedmodel.atom.*;
import org.apache.axis.feed.feedmodel.atom.factory.ATOMFactory;
import org.apache.axis.feed.feedmodel.AbstractFeedFactory;

/**
 * Created by IntelliJ IDEA.
 * User: INDIKA
 * Date: Jul 27, 2005
 * Time: 4:32:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class ATOMModelTest extends TestCase{
    private ATOMEntry atomEntry;
    private  ATOMFeed  atomFeed;
    private ContentElement titleElement;
    private DateElement modifiedDateElement;
    private DateElement issuedDateElement;
    private LinkElement linkElement;
    private ATOMID atomid;


    public static void main(String[] args) {
	}

	/*
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();

        ATOMFactory fac = (ATOMFactory) AbstractFeedFactory.getFeedFactory("ATOM");
        atomFeed = fac.createFeedElement("0.3", new ATOMTitleElement("title "), new ATOMModifiedElement(new Date()), new ATOMLinkElement("alternate", "herf", "text/palin"), new ATOMAuthorElement(new PersonalNameElementImpl("anoter name")));
        titleElement = fac.createATOMTitleElement("entrytile");
        modifiedDateElement=   fac.createATOMModifiedElement(new Date());
        issuedDateElement=     fac.createATOMIsuuedElement(new Date());
        atomid = new ATOMID("id") ;
        linkElement= fac.createATOMLinkElement("alternate", "http://www.ws.apache.org/", "text/html");
        atomEntry = fac.createEntryElement(titleElement, modifiedDateElement, linkElement,issuedDateElement );
        atomFeed.addEntry(atomEntry);
        atomEntry.setATOMId(atomid);
	}

	/*
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}
    public void testAll(){
        Iterator iterator = atomFeed.getEntries();
        while(iterator.hasNext()){
            ATOMEntry  entry = (ATOMEntry)iterator.next();
        assertEquals(atomEntry,entry);
        assertEquals(titleElement,entry.getTitleElement());
        assertEquals(modifiedDateElement,entry.getModifiedElement());
        assertEquals(issuedDateElement,entry.getIssuedElement());
        assertEquals(linkElement,entry.getLinkElement());
        assertEquals(atomid,entry.getATOMId());
        }
    }

}
