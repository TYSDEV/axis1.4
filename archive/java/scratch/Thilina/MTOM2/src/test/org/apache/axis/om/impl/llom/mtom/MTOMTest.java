/*
 * Created on Mar 9, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.apache.axis.om.impl.llom.mtom;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;

import javax.activation.DataHandler;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import junit.framework.TestCase;

import org.apache.axis.om.OMAttribute;
import org.apache.axis.om.OMElement;
import org.apache.axis.om.impl.llom.OMAttributeImpl;
import org.apache.axis.om.impl.llom.OMBlobImpl;
import org.apache.axis.om.impl.llom.OMElementImpl;
import org.apache.axis.om.impl.llom.OMNamespaceImpl;
import org.apache.axis.om.impl.llom.builder.MTOMStAXSOAPModelBuilder;

/**
 * @author TGunarathne
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class MTOMTest extends TestCase {
    String expectedObject;

    String outFileName;

    String outBase64FileName;

    MTOMStAXSOAPModelBuilder builder;

    DataHandler expectedDH;

    public static void main(String[] args) {
        junit.swingui.TestRunner.run(MTOMTest.class);
    }

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        outFileName = "mtom/src/test-resources/OMSerializeMTOMOut.txt";
        outBase64FileName = "mtom/src/test-resources/OMSerializeBase64Out.xml";
        serializeSetUp();
        builder = new MTOMStAXSOAPModelBuilder(new FileInputStream(outFileName));

    }

    protected void serializeSetUp() {
        File outMTOMFile;
        File outBase64File;

        try {

            outMTOMFile = new File(outFileName);
            outBase64File = new File(outBase64FileName);
            MTOMXMLStreamWriter mtomWriter = new MTOMXMLStreamWriter(
                    new FileOutputStream(outMTOMFile));
            XMLStreamWriter baseWriter = XMLOutputFactory.newInstance()
                    .createXMLStreamWriter(new FileOutputStream(outBase64File));

            //SimpleOMSerializer mtomSimpleOMSerialiser = new
            // SimpleOMSerializer();

            OMNamespaceImpl soap = new OMNamespaceImpl(
                    "http://schemas.xmlsoap.org/soap/envelope/", "soap");
            OMElement envelope = new OMElementImpl("Envelope", soap);
            OMElement body = new OMElementImpl("Body", soap);

            OMNamespaceImpl dataName = new OMNamespaceImpl(
                    "http://www.example.org/stuff", "m");
            OMElement data = new OMElementImpl("data", dataName);

            OMNamespaceImpl mime = new OMNamespaceImpl(
                    "http://www.w3.org/2003/06/xmlmime", "m");

            OMElement text = new OMElementImpl("name", dataName);
            OMAttribute cType1 = new OMAttributeImpl("contentType", mime,
                    "text/plain");
            text.insertAttribute(cType1);
            expectedObject = new String("Programming Project");
            expectedDH = new DataHandler(expectedObject, "text/plain");
            OMBlobImpl textData = new OMBlobImpl(expectedDH);

            envelope.addChild(body);
            body.addChild(data);
            data.addChild(text);
            text.addChild(textData);

            envelope.serialize(baseWriter, false);
            baseWriter.flush();

            envelope.serialize(mtomWriter, false);
            //MTOMser.serialize(Envelope, MTOMWriter);
            mtomWriter.flush();
            mtomWriter.complete();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Constructor for OMBlobTest.
     * 
     * @param name
     */
    public MTOMTest(String name) {
        super(name);
    }

    public void testGetDataHandler() {
        try {
            OMElement root = (OMElement) builder.getDocumentElement();
            System.out.println(root.getLocalName() + " : "
                    + root.getNamespaceName());
            OMElement body = (OMElement) root.getFirstChild();
            System.out.println(body.getLocalName() + " : "
                    + body.getNamespaceName());
            OMElement data = (OMElement) body.getFirstChild();
            System.out.println(data.getLocalName() + " : "
                    + data.getNamespaceName());
            Iterator childIt = data.getChildren();
            //while (childIt.hasNext()) {
            OMElement child = (OMElement) childIt.next();
            OMBlobImpl blob = (OMBlobImpl) child.getFirstChild();
            /*
             * Following is the procedure the user has to follow to read objects
             * in OBBlob User has to know the object type & whether it is
             * serializable. If it is not he has to use a Custom Defined
             * DataSource to get the Object.
             */

            DataHandler actualDH;
            actualDH = blob.getDataHandler();
            //assertEquals("DataHandler
            // check",expectedDH.getContent(),(actualDH =
            // blob.getDataHandler()).getContent());
            Object actualObject = actualDH.getContent(); //This returns a
            // String cause string
            // is serializable
            assertEquals("Object check", expectedObject, (String) actualObject);

            System.out.println(child.getLocalName() + ":-\t" + actualObject);

            //}

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}