package org.apache.axis.mtom;

import org.apache.axis.impl.llom.builder.StAXOMBuilder;
import org.apache.axis.impl.llom.mtom.OMBlob;
import org.apache.axis.impl.llom.serialize.SimpleOMSerializer;
import org.apache.axis.om.OMElement;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;

/**
 * @author Thilina Gunarathne Reads in the output file given by
 *         MTOMOMSerializeTest
 */
public class MTOMFileTest{

    public static void main(String args[]) {
        StAXOMBuilder builder;
        File tempFile;

        try {

            builder = new StAXOMBuilder(new FileInputStream("src/test-resources/OMSerializeMTOMOut.txt"));

            SimpleOMSerializer serializer = new SimpleOMSerializer();
            OMElement root = (OMElement) builder.getDocumentElement();

            while (!builder.isCompleted()) {

            }
            System.out.println(root.getLocalName() + " : " + root.getNamespaceName());
            OMElement body = (OMElement) root.getFirstChild();
            System.out.println(body.getLocalName() + " : " + body.getNamespaceName());
            OMElement data = (OMElement) body.getFirstChild();
            System.out.println(data.getLocalName() + " : " + data.getNamespaceName());
            Iterator childIt = data.getChildren();
            while (childIt.hasNext()) {
                OMElement child = (OMElement) childIt.next();
                OMBlob blob = (OMBlob) child.getFirstChild();
                System.out.println(child.getLocalName() + ":-\t"
                        + blob.getObject());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}