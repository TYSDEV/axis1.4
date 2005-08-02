/*
 * Created on Jul 28, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.apache.axis.feed.feedmodel.atom.impl;

import junit.framework.TestCase;
import org.apache.axis.feed.feedmodel.atom.DateElement;

import java.util.Date;

/**
 * @author INDIKA
 *         <p/>
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class DateElementImplTest extends TestCase {
    private Date date;
    private DateElement dateElement;

    public static void main(String[] args) {
    }

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        dateElement = new ATOMIssuedElement(new Date());
        date = new Date();
    }

    /*
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Constructor for DateElementImplTest.
     *
     * @param name
     */
    public DateElementImplTest(String name) {
        super(name);
    }


    public void testGetDate() {
        assertNotNull(dateElement.getDate());
    }

    public void testSetDate() {
        assertEquals(date, dateElement.getDate());
        dateElement.setDate(date);
        assertEquals(date, dateElement.getDate());
    }

}
