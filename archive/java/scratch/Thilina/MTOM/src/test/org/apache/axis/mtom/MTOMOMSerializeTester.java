package org.apache.axis.mtom;

import org.apache.axis.encoding.Base64;
import org.apache.axis.impl.llom.OMAttributeImpl;
import org.apache.axis.impl.llom.OMElementImpl;
import org.apache.axis.impl.llom.OMNamespaceImpl;
import org.apache.axis.impl.llom.builder.StAXOMBuilder;
import org.apache.axis.impl.llom.mtom.MTOMXMLStreamWriter;
import org.apache.axis.impl.llom.mtom.OMBlob;
import org.apache.axis.impl.llom.serialize.SimpleOMSerializer;
import org.apache.axis.om.OMAttribute;
import org.apache.axis.om.OMElement;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @author Thilina Gunarathne Builds an AXIOM & write it as MTOM
 */
public class MTOMOMSerializeTester {

    public static void main(String args[]) {
        StAXOMBuilder test;
        File outMTOMFile;
        File outBase64File;
        File outMTOMBaseFile;

        try {

            outMTOMFile = new File("src/test-resources/OMSerializeMTOMOut.txt");
            outBase64File = new File("src/test-resources/OMSerializeBase64Out.txt");
            outMTOMBaseFile = new File("src/test-resources/OMSerializeMTOMBaseOut.txt");
            XMLStreamWriter baseWriter = XMLOutputFactory.newInstance()
                    .createXMLStreamWriter(new FileOutputStream(outBase64File));
            MTOMXMLStreamWriter MTOMWriter = new MTOMXMLStreamWriter(new FileOutputStream(outMTOMFile));
            MTOMXMLStreamWriter MTOMBaseWriter = new MTOMXMLStreamWriter(new FileOutputStream(outMTOMBaseFile));

            SimpleOMSerializer MTOMser = new SimpleOMSerializer();
            SimpleOMSerializer Baseser = new SimpleOMSerializer();
            SimpleOMSerializer MTOMBaseser = new SimpleOMSerializer();

            OMNamespaceImpl soap = new OMNamespaceImpl("http://www.w3.org/2003/05/soap-envelope", "soap");
            OMElement Envelope = new OMElementImpl("Envelope", soap);
            OMElement Body = new OMElementImpl("Body", soap);

            OMNamespaceImpl m = new OMNamespaceImpl("http://www.example.org/stuff", "m");
            OMElement data = new OMElementImpl("data", m);
            OMElement photo = new OMElementImpl("photo", m);

            OMNamespaceImpl mime = new OMNamespaceImpl("http://www.w3.org/2003/06/xmlmime", "m");
            OMAttribute cType = new OMAttributeImpl("contentType", mime,
                    "image/png");
            photo.insertAttribute(cType);
            OMBlob photoData = new OMBlob(Base64.decode("aWKKapGGyq"), true);

            OMElement sig = new OMElementImpl("sig", m);
            OMAttribute cType1 = new OMAttributeImpl("contentType", mime,
                    "application/pkcs7-signature");
            sig.insertAttribute(cType1);
            //java.awt.Image pic =
            // Toolkit.getDefaultToolkit().getImage("C:\\test.jpg");
            OMBlob sigData = new OMBlob(new String("Programming Project"), true);

            OMElement val = new OMElementImpl("value", m);
            OMBlob valData = new OMBlob(new Float(1.4556), true);

            Envelope.addChild(Body);
            Body.addChild(data);
            data.addChild(photo);
            photo.addChild(photoData);
            data.addChild(sig);
            sig.addChild(sigData);
            data.addChild(val);
            val.addChild(valData);

            MTOMser.serialize(Envelope, MTOMWriter);
            MTOMWriter.flush();
            MTOMWriter.complete();

            Baseser.serialize(Envelope, baseWriter);
            baseWriter.flush();

            valData.setMTOMable(false);
            //photoData.setMTOMable(false);
            MTOMBaseser.serialize(Envelope, MTOMBaseWriter);
            MTOMBaseWriter.flush();
            MTOMBaseWriter.complete();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}