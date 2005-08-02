/*
 * Created on Jul 28, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.apache.axis.feed.feedmodel.atom.impl;

import junit.framework.TestCase;
import org.apache.axis.feed.feedmodel.atom.ModeAttribute;
import org.apache.axis.feed.feedmodel.atom.TypeAttribute;

/**
 * @author INDIKA
 *         <p/>
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class ContentElementImplTest extends TestCase {

    private TypeAttribute typeAttribute = null;
    private ModeAttribute modeAttribute = null;
    private String content;
    private ATOMTitleElement atomTitleElement;

    public static void main(String[] args) {
    }

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        content = "new content";
        atomTitleElement = new ATOMTitleElement("title");
        typeAttribute = new TypeAttributeImpl("text/xml");
        modeAttribute = new ModeAttributeImpl("xml");

    }

    /*
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Constructor for ContentElementImplTest.
     *
     * @param name
     */
    public ContentElementImplTest(String name) {
        super(name);
    }


    public void testGetTypeAttribute() {
        assertNull(atomTitleElement.getTypeAttribute());
    }

    public void testSetTypeAttribute() {
        atomTitleElement.setTypeAttribute(typeAttribute);
        assertEquals(typeAttribute, atomTitleElement.getTypeAttribute());
    }

    public void testGetModeAttribute() {
        assertNull(atomTitleElement.getModeAttribute());
    }

    public void testSetModeAttribute() {
        atomTitleElement.setModeAttribute(modeAttribute);
        assertEquals(modeAttribute, atomTitleElement.getModeAttribute());
    }

    public void testGetContent() {
        assertNotNull(atomTitleElement.getContent());
    }

    public void testSetContent() {
        atomTitleElement.setContent(content);
        assertEquals(content, atomTitleElement.getContent());

    }

}
