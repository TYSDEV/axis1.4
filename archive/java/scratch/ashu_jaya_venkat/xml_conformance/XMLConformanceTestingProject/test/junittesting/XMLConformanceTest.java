/*
 * Created on Apr 8, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package junittesting;

import java.io.File;
import org.custommonkey.xmlunit.XMLTestCase;
import org.custommonkey.xmlunit.Diff;
import org.apache.axis.om.OMElement;
import org.apache.axis.om.impl.llom.factory.OMXMLBuilderFactory;
import org.apache.axis.om.impl.llom.builder.StAXOMBuilder;
import org.apache.axis.om.impl.llom.OMDocument;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.XMLStreamException;
import org.apache.axis.om.OMFactory;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileInputStream;


/**
 * @author sunja07
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class XMLConformanceTest extends XMLTestCase {

	private static int successCount=0;
	private static int parsedCount=0;
	private static int fileCount=0;
	/**
	 * @param name
	 */
	public XMLConformanceTest(String name) {
		super(name);
	}
	
	public void setUp() {		
	}
	
	public void testXMLConformance() throws Exception{
		File testSuiteDirectory = new File("C:\\Documents and Settings\\sunja07\\Desktop\\XMLConformanceWork\\XMLSuite");
		//The following line is for checking any individual file behaviour rather than whole suite
		//File testSuiteDirectory = new File("C:\\Documents and Settings\\sunja07\\Desktop\\XMLConformanceWork\\XMLSuite\\xmlconf\\ibm\\valid\\P10\\ibm10v05.xml");
		ProcessDir(testSuiteDirectory);
		System.out.println("File count is " + fileCount);
		System.out.println("Parsed count is " + parsedCount +". This is just partial success");
		System.out.println("Complete success count is " + successCount);
	}
	
	public void ProcessDir(File dir) throws Exception
	{
		if (dir.isDirectory()) {
			//System.out.println("Processing Directory: "+dir.getAbsolutePath() );
			String[] children = dir.list();
			for (int i=0; i<children.length; i++) {
	           File child = (new File(dir, children[i]));	           
	           	ProcessDir(child);
	           }
	    } else { //meaning you got a file
           	//check if it's xml file
           	String absPath = dir.getAbsolutePath();
           	if(absPath.endsWith(".xml")) {
           		//process it
           		testSingleFileConformance(absPath);
           		if((fileCount++)%100 == 0) //|| fileCount == 1000 || fileCount == 1500 || fileCount == 2000)
    			{
    				System.out.println("This line is just for pausing the stdout log and copying it\n");
    			}
    	        
           	} else {
           		//ignore non .xml files
           	}
        }		
	}
	
	public void testSingleFileConformance(String absolutePath) throws Exception{
		OMElement rootElement;
		OMDocument document;
		//fileCount++;
		//get a stax om builder
		try {
		StAXOMBuilder staxOMBuilder = OMXMLBuilderFactory.createStAXOMBuilder(OMFactory.newInstance(), XMLInputFactory.newInstance().createXMLStreamReader(new FileInputStream(absolutePath) , "UTF-8"));

		document = (OMDocument)staxOMBuilder.getDocument();
		rootElement = staxOMBuilder.getDocumentElement();
		} catch (Exception e) {
			System.err.println("Exception trying to get hold of rootElement: " + e.getMessage());
			System.err.println("in file: "+ absolutePath +"\n");
			return;
		}		

		//we will write output into the file named TempOutputFile.txt in current directory
		String tempFile = "TempOutputFile.txt";
		XMLStreamWriter writer;
		try {
		writer = XMLOutputFactory.newInstance().
                createXMLStreamWriter(new FileOutputStream(tempFile));		
		document.serialize(writer, true); 
//        rootElement.serialize(writer, true);
		}catch(XMLStreamException e) {
			System.err.println("Error in creating XMLStreamWriter to write parsed xml into");
			return;
		} catch (Exception e) {
			System.err.println("Exception while serializing: " + e.getMessage());
			System.err.println("in file: "+ absolutePath +"\n");
			return;
		}
        writer.flush();
        writer.close();        
        parsedCount++;
        //Comparing the equality of the TempOutputFile.txt and the input xml is due
        /* */
        Diff diff;
        try {
        diff = compareXML(new FileReader(absolutePath), new FileReader("TempOutputFile.txt"));
        } catch (Exception e) {
        	System.out.println("Error comparing original and generated files for: "+absolutePath);
        	System.out.println("Error message is: "+e.getMessage());
        	return;
        }
        try {
        	assertXMLEqual(diff,true);
        	successCount++;
        } catch (Error e) {
        	System.out.println("XMLEquality failed for file: "+absolutePath);        	
        	return;
        }
        
	}
	public void tearDown() {		
	}

}
