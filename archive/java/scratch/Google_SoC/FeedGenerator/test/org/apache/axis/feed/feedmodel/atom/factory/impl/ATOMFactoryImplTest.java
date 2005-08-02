/*
 * Created on Jul 28, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.apache.axis.feed.feedmodel.atom.factory.impl;

import junit.framework.TestCase;
import org.apache.axis.feed.feedmodel.atom.*;
import org.apache.axis.feed.feedmodel.atom.factory.ATOMFactory;
import org.apache.axis.feed.feedmodel.atom.impl.*;

import java.util.Date;

/**
 * @author INDIKA
 *         <p/>
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class ATOMFactoryImplTest extends TestCase {

    private ATOMFactory factory;



    private LinkElement linkElement;

   private ATOMAuthorElement authorElement = null;
    private ModeAttribute mode;
    private TypeAttribute type;
    private ATOMTitleElement titleElement = null;
    private ATOMModifiedElement modifiedElement = null;
    private ATOMID atomid = null;

    public static void main(String[] args) {
    }

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        factory = new ATOMFactoryImpl();
    }

    /*
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Constructor for ATOMFactoryImplTest.
     *
     * @param name
     */
    public ATOMFactoryImplTest(String name) {
        super(name);
    }


    public void testCreateATOMTitleElement() {
        titleElement = new ATOMTitleElement("title");
        assertEquals(titleElement, factory.createATOMTitleElement("title"));
    }


    public void testCreateATOMModifiedElement() {
        modifiedElement = new ATOMModifiedElement(new Date());
        assertEquals(modifiedElement, factory.createATOMModifiedElement(new Date()));
    }

    public void testCreateATOMAuthorElement() {
        authorElement = new ATOMAuthorElement(new PersonalNameElementImpl("indika"));
        assertEquals(authorElement, factory.createATOMAuthorElement(factory.createPersonalNameElement("indika")));
    }


    public void testCreateATOMIDElement() {
        atomid = new ATOMID("id");
        assertEquals(atomid, factory.createATOMIDElement("id"));
    }

    public void testCreateATOMLinkElement() {
        linkElement = new ATOMLinkElement("alternate", "http://www.ws.apache.org/", "text/html");
        assertEquals(linkElement, factory.createATOMLinkElement("alternate", "http://www.ws.apache.org/", "text/html"));
        assertEquals(linkElement.getHerfAttribute(), factory.createATOMLinkElement("alternate", "http://www.ws.apache.org/", "text/html").getHerfAttribute());
    }

    public void testCreateModeAttribute() {
        mode = new ModeAttributeImpl(ATOMConstants.BASE64_MODE_ARRTIBUTE);
        assertEquals(mode.getMode(), factory.createModeAttribute(ATOMConstants.BASE64_MODE_ARRTIBUTE).getMode());

    }

    public void testCreateTypeAttribute() {
        type = new TypeAttributeImpl("xml");
        assertEquals(type.getMediaType(), factory.createTypeAttribute("xml").getMediaType());

    }

}
