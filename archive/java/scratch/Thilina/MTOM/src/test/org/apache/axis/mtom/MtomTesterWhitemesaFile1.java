package org.apache.axis.mtom;

import org.apache.axis.impl.llom.builder.StAXOMBuilder;
import org.apache.axis.impl.llom.serialize.SimpleOMSerializer;
import org.apache.axis.om.OMElement;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Iterator;


/**
 * @author Thilina Gunarathne
 */
public class MtomTesterWhitemesaFile1 {

    public static void main(String args[]) {
        StAXOMBuilder test;
        File tempFile;

        try {
            test = new StAXOMBuilder((InputStream) new FileInputStream("src/test-resources/Whitemesa1MTOM.txt"));
            tempFile = new File("src/test-resources/Whitemesa1MTOMBaseOut.xml");

            XMLStreamWriter writer = XMLOutputFactory.newInstance().createXMLStreamWriter(new FileOutputStream(tempFile));
            //MTOMXmlStreamWriter writer = new MTOMXmlStreamWriter(new FileOutputStream(tempFile));
            SimpleOMSerializer ser = new SimpleOMSerializer();
            OMElement root = (OMElement) test.getDocumentElement();

            Iterator childIt = root.getChildren();
            while (childIt.hasNext()) {
                Object temp1 = childIt.next();
                System.out.println(temp1.toString());
            }
            ser.serialize(root, writer);
            writer.flush();
            //writer.complete();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}