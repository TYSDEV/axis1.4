/*
 * Created on Mar 9, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.apache.axis.mtom;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;

import javax.activation.DataHandler;

import junit.framework.TestCase;

import org.apache.axis.impl.llom.OMAttributeImpl;
import org.apache.axis.impl.llom.OMElementImpl;
import org.apache.axis.impl.llom.OMNamespaceImpl;
import org.apache.axis.impl.llom.builder.StAXOMBuilder;
import org.apache.axis.impl.llom.mtom.MTOMXMLStreamWriter;
import org.apache.axis.impl.llom.mtom.OMBlob;
import org.apache.axis.impl.llom.serialize.SimpleOMSerializer;
import org.apache.axis.om.OMAttribute;
import org.apache.axis.om.OMElement;

/**
 * @author TGunarathne
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class MTOMTest extends TestCase {
	String expectedObject;

	String outFileName;

	StAXOMBuilder builder;

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
		serializeSetUp();
		builder = new StAXOMBuilder(new FileInputStream(outFileName));

	}

	protected void serializeSetUp() {
		File outMTOMFile;

		try {

			outMTOMFile = new File(outFileName);
			MTOMXMLStreamWriter MTOMWriter = new MTOMXMLStreamWriter(
					new FileOutputStream(outMTOMFile));

			SimpleOMSerializer MTOMser = new SimpleOMSerializer();

			OMNamespaceImpl soap = new OMNamespaceImpl(
					"http://www.w3.org/2003/05/soap-envelope", "soap");
			OMElement Envelope = new OMElementImpl("Envelope", soap);
			OMElement Body = new OMElementImpl("Body", soap);

			OMNamespaceImpl m = new OMNamespaceImpl(
					"http://www.example.org/stuff", "m");
			OMElement data = new OMElementImpl("data", m);

			OMNamespaceImpl mime = new OMNamespaceImpl(
					"http://www.w3.org/2003/06/xmlmime", "m");

			OMElement text = new OMElementImpl("name", m);
			OMAttribute cType1 = new OMAttributeImpl("contentType", mime,
					"text/plain");
			text.insertAttribute(cType1);
			expectedObject = new String("Programming Project");
			expectedDH = new DataHandler(expectedObject, "text/plain");
			OMBlob textData = new OMBlob(expectedDH);

			Envelope.addChild(Body);
			Body.addChild(data);
			data.addChild(text);
			text.addChild(textData);

			MTOMser.serialize(Envelope, MTOMWriter);
			MTOMWriter.flush();
			MTOMWriter.complete();

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
			OMBlob blob = (OMBlob) child.getFirstChild();
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