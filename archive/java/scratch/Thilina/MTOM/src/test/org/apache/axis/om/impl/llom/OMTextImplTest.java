/*
 * Created on Mar 14, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.apache.axis.om.impl.llom;

import java.io.*;

import junit.framework.TestCase;
import org.apache.axis.encoding.*;
import javax.activation.*;
import org.apache.axis.om.impl.llom.mtom.*;

/**
 * @author TGunarathne
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class OMTextImplTest extends TestCase {
	Object expectedObject;
	
	OMTextImpl textNode;
	
	String expectedBase64;
	
	public static void main(String[] args) {
		junit.swingui.TestRunner.run(OMTextImplTest.class);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		expectedObject = new String("Lanka Software Foundation");
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		ObjectOutputStream objectOutStream = new ObjectOutputStream(byteStream);
		objectOutStream.writeObject(expectedObject);
		expectedBase64 = Base64.encode(byteStream.toByteArray());
		textNode = new OMTextImpl(expectedBase64, "text/plain");	
	}
	
	/*
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	/**
	 * Constructor for OMTextImplTest.
	 * @param name
	 */
	public OMTextImplTest(String name) {
		super(name);
	}
	
	public void testBase64Encode() {
		Object actualObject1;
		try {
			byte[] tempa = Base64.decode(expectedBase64);
			ObjectInputStream objectInStream = new ObjectInputStream(
					new ByteArrayInputStream(tempa));
			actualObject1 = objectInStream.readObject();
			assertEquals("Base64 Encoding Check", expectedObject, actualObject1);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void testGetDataHandler() {
		String actualObject;
		
		DataHandler DH = textNode.getDataHandler();
		
		ByteArrayDataSource ds = (ByteArrayDataSource) DH.getDataSource();
		try {
			InputStream inStream = ds.getInputStream();
			ObjectInputStream obj = new ObjectInputStream(inStream);
			
			actualObject = (String) obj.readObject();
			
			assertEquals("OMTextImpl GetDataHandler Check", expectedObject,
					actualObject);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}