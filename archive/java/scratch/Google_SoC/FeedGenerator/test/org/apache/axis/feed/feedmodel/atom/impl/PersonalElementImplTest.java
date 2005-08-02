/*
 * Created on Jul 28, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.apache.axis.feed.feedmodel.atom.impl;

import junit.framework.TestCase;
import org.apache.axis.feed.feedmodel.atom.PersonalElement;
import org.apache.axis.feed.feedmodel.atom.PersonalEmailElement;
import org.apache.axis.feed.feedmodel.atom.PersonalNameElement;
import org.apache.axis.feed.feedmodel.atom.PersonalURLElement;

import java.net.URL;

/**
 * @author INDIKA
 *         <p/>
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class PersonalElementImplTest extends TestCase {
    private PersonalNameElement name = null;
    private PersonalURLElement UrL = null;
    private PersonalEmailElement email = null;
    private PersonalElement personalElement;

    public static void main(String[] args) {
    }

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        name = new PersonalNameElementImpl("indika");
        UrL = new PersonalURLElementImpl(new URL("http://wiki.apache.org/ws/SummerOfCode/2005/RSSFeedGenerator"));
        email = new PersonalEmailElementImpl("ipkumara2003@yahoo.co.uk");
        personalElement = new ATOMAuthorElement(new PersonalNameElementImpl("indika kumara"));
    }

    /*
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Constructor for PersonalElementImplTest.
     *
     * @param name
     */
    public PersonalElementImplTest(String name) {
        super(name);
    }


    public void testGetName() {
        assertNotNull(personalElement.getName());
    }

    public void testSetName() {
        personalElement.setName(name);
        assertEquals(name, personalElement.getName());
    }

    public void testGetURL() {
        assertNull(personalElement.getURL());
    }

    public void testSetURL() {
        personalElement.setURL(UrL);
        assertNotNull(personalElement.getURL());
        assertEquals(UrL, personalElement.getURL());
    }

    public void testGetEmail() {
        assertNull(personalElement.getEmail());
    }

    public void testSetEmail() {
        personalElement.setEmail(email);
        assertEquals(email, personalElement.getEmail());
    }

}
