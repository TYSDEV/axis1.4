package test.utils;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.axis.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PipedOutputStream;
import java.io.Reader;
import java.io.StringReader;

public class TestXMLUtils extends TestCase
{

    public TestXMLUtils (String name) {
        super(name);
    }

    public static Test suite() {
        return new TestSuite(TestXMLUtils.class);
    }

    public void setup() {
    }

    public void testNewDocumentNoArgConstructor() throws Exception
    {
        Document doc = XMLUtils.newDocument();
        assertNotNull("Did not get a new Document", doc);
    }

    public void testNewDocumentInputSource() throws Exception
    {
        Reader reader = (Reader)this.getTestXml("reader");
        InputSource inputsrc = new InputSource(reader);
        Document doc = XMLUtils.newDocument(inputsrc);
        assertNotNull("Did not get a new Document", doc);
    }

    public void testNewDocumentInputStream() throws Exception
    {
        InputStream iostream = (InputStream)this.getTestXml("inputstream");
        InputSource inputsrc = new InputSource(iostream);
        Document doc = XMLUtils.newDocument(inputsrc);
        assertNotNull("Did not get a new Document", doc);
    }

    /* This test will fail unless you are connected to the Web, so just skip
    * it unless you really want to test it.  When not connected to the Web you
    * will get an UnknownHostException.
    */
    /*
    public void testNewDocumentURI()
    {
        String uri = "http://java.sun.com/j2ee/dtds/web-app_2.2.dtd";
        Document doc = XMLUtils.newDocument(uri);
        assertNotNull("Did not get a new Document", doc);
    }
    */

    public void testDocumentToString() throws Exception
    {
        Reader reader = (Reader)this.getTestXml("reader");
        InputSource inputsrc = new InputSource(reader);
        Document doc = XMLUtils.newDocument(inputsrc);

        String xmlString = (String)this.getTestXml("string");
        String result = XMLUtils.DocumentToString(doc);
        assertEquals("xmlString is not the same as result", xmlString, result);
    }

    /**
    * This test method is somewhat complex, but it solves a problem people have
    * asked me about, which is how to unit test a method that has void return
    * type but writes its output to a writer.  So half the reason for
    * creating and using it here is as a reference point.
    */
    public void testElementToWriter() throws Exception
    {
        /* Get the Document and one of its elements. */
        Reader xmlReader = (Reader)this.getTestXml("reader");
        InputSource inputsrc = new InputSource(xmlReader);
        Document doc = XMLUtils.newDocument(inputsrc);
        NodeList nl = doc.getElementsByTagName("display-name");
        Element elem = (Element)nl.item(0);
        String expected = "<display-name>Apache-Axis</display-name>";

        /*
        * Create a PipedOutputStream to get the output from the tested method.
        * Pass the PipedOutputStream to the ConsumerPipe's constructor, which
        * will create a PipedInputStream in a separate thread.
        */
        PipedOutputStream out = new PipedOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(out);
        ConsumerPipe cpipe = new ConsumerPipe(out);

        /*
        * Call the method under test, passing the PipedOutStream to trap the
        * results.
        */
        XMLUtils.ElementToWriter(elem, writer);

        /*
        * The output of the test will be piped to ConsumerPipe's PipedInputStream, which
        * is used to read the bytes of the stream into an array.  It then creates a
        * String for comparison from that byte array.
        */
        out.flush();
        String result = cpipe.getResult();
        //don't forget to close this end of the pipe (ConsumerPipe closes the other end).
        out.close();

        assertEquals("Did not get the expected result", expected, result);
    }

    /**
    * For explanation of the methodology used to test this method, see notes in
    * previous test method.
    */
    public void testDocumentToStream() throws Exception
    {
        Reader reader = (Reader)this.getTestXml("reader");
        InputSource inputsrc = new InputSource(reader);
        Document doc = XMLUtils.newDocument(inputsrc);

        PipedOutputStream out = new PipedOutputStream();
        ConsumerPipe cpipe = new ConsumerPipe(out);

        XMLUtils.DocumentToStream(doc, out);
        out.flush();
        String result = cpipe.getResult();
        out.close();

        String expected = (String)this.getTestXml("string");
        assertEquals("Did not get the expected result", expected, result);
    }

    public void testElementToString() throws Exception
    {
        Reader reader = (Reader)this.getTestXml("reader");
        InputSource inputsrc = new InputSource(reader);
        Document doc = XMLUtils.newDocument(inputsrc);

        NodeList nl = doc.getElementsByTagName("display-name");
        Element elem = (Element)nl.item(0);
        String expected = "<display-name>Apache-Axis</display-name>";
        String result = XMLUtils.ElementToString(elem);
        assertEquals("Element tag name is not 'display-name', it is: " + elem.getTagName(),
                     "display-name", elem.getTagName());
        assertEquals("Did not get the expected result", expected, result);
    }

    public void testGetInnerXMLString() throws Exception
    {
        Reader reader = (Reader)this.getTestXml("reader");
        InputSource inputsrc = new InputSource(reader);
        Document doc = XMLUtils.newDocument(inputsrc);

        NodeList nl = doc.getElementsByTagName("display-name");
        Element elem = (Element)nl.item(0);
        String expected = "Apache-Axis";
        String result = XMLUtils.getInnerXMLString(elem);
        assertEquals(expected, result);
    }

    public void testGetPrefix() throws Exception
    {
        Document doc = XMLUtils.newDocument();

        Element elem = doc.createElementNS("", "svg");
        elem.setAttribute("xmlns:svg", "\"http://www.w3.org/2000/svg\"");
        elem.setAttribute("xmlns:xlink", "\"http://www.w3.org/1999/xlink\"");
        elem.setAttribute("xmlns:xhtml", "\"http://www.w3.org/1999/xhtml\"");

        String expected = "svg";
        String result = XMLUtils.getPrefix("\"http://www.w3.org/2000/svg\"", elem);
        assertEquals("Did not get the expected result", expected, result);
        expected = "xlink";
        result = XMLUtils.getPrefix("\"http://www.w3.org/1999/xlink\"", elem);
        assertEquals("Did not get the expected result", expected, result);
        expected = "xhtml";
        result = XMLUtils.getPrefix("\"http://www.w3.org/1999/xhtml\"", elem);
        assertEquals("Did not get the expected result", expected, result);
    }

    public void testGetNamespace() throws Exception
    {
        String testDoc = "<svg xmlns:svg=\"http://www.w3.org/2000/svg\"/>";
        InputSource inputsrc = new InputSource(new StringReader(testDoc));
        Document doc = XMLUtils.newDocument(inputsrc);
        assertNotNull("Got a null document", doc);

        NodeList nl = doc.getElementsByTagName("svg");
        Element elem = (Element)nl.item(0);

        String expected = "http://www.w3.org/2000/svg";
        String result = XMLUtils.getNamespace("svg", elem);
        assertEquals("Did not get the expected result", expected, result);
    }

    /**
    * This is a utility method for creating XML document input sources for this
    * JUnit test class.  The returned Object should be cast to the type you
    * request via the gimme parameter.
    *
    * @param gimme A String specifying the underlying type you want the XML
    * input source returned as; one of "string", "reader", or "inputstream."
    */
    public Object getTestXml(String gimme)
    {
        String lineSep = System.getProperty("line.separator");
        StringBuffer sb = new StringBuffer();
          sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + lineSep)
        //The System ID will cause an unknown host exception unless you are
        //connected to the Internet, so comment it out for testing.
          //.append("<!DOCTYPE web-app PUBLIC \"-//Sun Microsystems, Inc.//DTD Web Application 2.2//EN\"" + lineSep)
          //.append("\"http://java.sun.com/j2ee/dtds/web-app_2.2.dtd\">" + lineSep)
          .append("<web-app>" + lineSep)
          .append("<display-name>Apache-Axis</display-name>" + lineSep)
          .append("<servlet>" + lineSep)
          .append("<servlet-name>AxisServlet</servlet-name>" + lineSep)
          .append("<display-name>Apache-Axis Servlet</display-name>" + lineSep)
          .append("<servlet-class>" + lineSep)
          .append("org.apache.axis.transport.http.AxisServlet" + lineSep)
          .append("</servlet-class>" + lineSep)
          .append("</servlet>" + lineSep)
          .append("<servlet-mapping>" + lineSep)
          .append("<servlet-name>AxisServlet</servlet-name>" + lineSep)
          .append("<url-pattern>servlet/AxisServlet</url-pattern>" + lineSep)
          .append("<url-pattern>*.jws</url-pattern>" + lineSep)
          .append("</servlet-mapping>" + lineSep)
          .append("</web-app>");

        String xmlString = sb.toString();

        if (gimme.equals("string"))
        {
            return xmlString;
        }
        else if (gimme.equals("reader"))
        {
            StringReader strReader = new StringReader(xmlString);
            return strReader;
        }
        else if (gimme.equals("inputstream"))
        {
            ByteArrayInputStream byteStream = new ByteArrayInputStream(xmlString.getBytes());
            return byteStream;
        }
        else return null;
    }

    public void testDOM2Writer() throws Exception
    {
        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        sb.append("<xsd:schema targetNamespace=\"http://tempuri.org\"");
        sb.append("            xmlns=\"http://tempuri.org\"");
        sb.append("            xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">");
        sb.append("  <xsd:annotation>");
        sb.append("    <xsd:documentation xml:lang=\"en\">");
        sb.append("      Purchase order schema for Example.com.");
        sb.append("      Copyright 2000 Example.com. All rights reserved.");
        sb.append("    </xsd:documentation>");
        sb.append("  </xsd:annotation>");
        sb.append("</xsd:schema>");

        StringReader strReader = new StringReader(sb.toString());
        InputSource inputsrc = new InputSource(strReader);
        Document doc = XMLUtils.newDocument(inputsrc);

        String output = org.apache.axis.utils.DOM2Writer.nodeToString(doc,false);
        assertTrue(output.indexOf("http://www.w3.org/XML/1998/namespace")==-1);
    }

    public static void main(String[] args) throws Exception
    {
        TestXMLUtils test = new TestXMLUtils("TestXMLUtils");
        test.testDOM2Writer();
    }
}
