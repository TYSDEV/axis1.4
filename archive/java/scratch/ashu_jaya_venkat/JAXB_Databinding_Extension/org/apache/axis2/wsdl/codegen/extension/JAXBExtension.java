package org.apache.axis2.wsdl.codegen.extension;

import java.io.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.wsdl.WSDLException;
import javax.xml.namespace.QName;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.axis2.wsdl.codegen.CodeGenConfiguration;
import org.apache.axis2.wsdl.codegen.CodeGenerationException;
import org.apache.axis2.wsdl.codegen.CommandLineOption;
import org.apache.axis2.wsdl.codegen.CommandLineOptionConstants;
import org.apache.axis2.wsdl.databinding.DefaultTypeMapper;
import org.apache.axis2.wsdl.databinding.JavaTypeMapper;
import org.apache.axis2.wsdl.util.XMLUtils;
import org.apache.wsdl.WSDLExtensibilityElement;
import org.apache.wsdl.WSDLTypes;

import com.sun.codemodel.JCodeModel;
import com.sun.tools.xjc.Plugin;
import com.sun.tools.xjc.XJC2Task;
import com.sun.tools.xjc.api.Mapping;
import com.sun.tools.xjc.api.S2JJAXBModel;
import com.sun.tools.xjc.api.SchemaCompiler;
import com.sun.tools.xjc.api.TypeAndAnnotation;
import com.sun.tools.xjc.api.XJC;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.*;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.Target;

public class JAXBExtension extends AbstractCodeGenerationExtension implements
		CodeGenExtension {
	private static final String DEFAULT_STS_NAME = "foo";

	public void init(CodeGenConfiguration configuration) {
		this.configuration = configuration;
	}

	public void engage() throws CodeGenerationException {
		
		try {
			
			WSDLTypes typesList = configuration.getWom().getTypes();
            if (typesList == null) {
                //there are no types to be code generated
                //However if the type mapper is left empty it will be a problem for the other
                //processes. Hence the default type mapper is set to the configuration
                this.configuration.setTypeMapper(new DefaultTypeMapper());
                return;
            }
            
            List typesArray = typesList.getExtensibilityElements();
            WSDLExtensibilityElement extensiblityElt = null;
            
            
            //Get the schema information from WSDL by parsing it in a DOM document
            //and extracting the schema part
            
            String uri = ((CommandLineOption)configuration.getParser().getAllOptions().get(
            		CommandLineOptionConstants.WSDL_LOCATION_URI_OPTION)).getOptionValue();
            File file = new File(uri);
            
            Document doc;
            try {
                doc = XMLUtils.newDocument(uri);
            } catch (ParserConfigurationException e) {
                throw new WSDLException(WSDLException.PARSER_ERROR,
                        "Parser Configuration Error",
                        e);
            } catch (SAXException e) {
                throw new WSDLException(WSDLException.PARSER_ERROR,
                        "Parser SAX Error",
                        e);

            } catch (IOException e) {
                throw new WSDLException(WSDLException.INVALID_WSDL, "IO Error", e);
            }
             
            Node schemaNode = getSchemaNode(doc);
            String schemaStr = parseToString(schemaNode);
           
            //invoke XJC programatically
            SchemaCompiler sc = XJC.createSchemaCompiler();
         
            StringReader sr = new StringReader(schemaStr);
            BufferedReader br = new BufferedReader(sr); 
            InputSource is = new InputSource(br);
            is.setSystemId("www.SystemId.org");
            sc.parseSchema(is);
            S2JJAXBModel jaxbModel = sc.bind();
            
            //Write Java files created from schema
            JCodeModel cm = jaxbModel.generateCode(new Plugin[]{}, null);
            String outputDir = ((CommandLineOption)configuration.getParser().getAllOptions().get(
            		CommandLineOptionConstants.OUTPUT_LOCATION_OPTION)).getOptionValue();
            cm.build(new File(outputDir));
            
            //Polulate typemapper
            Iterator mappingsIter =  jaxbModel.getMappings().iterator();
            JavaTypeMapper mapper = new JavaTypeMapper();
            while(mappingsIter.hasNext()){
            	Mapping mapping = (Mapping)mappingsIter.next();
            	QName xmlType = mapping.getElement();
            	String javaType = mapping.getType().getTypeClass();
            	System.out.println(xmlType + " ::: " + javaType);
            	mapper.addTypeMapping(xmlType, javaType);
            }
            this.configuration.setTypeMapper(mapper);
			
		}catch (Exception e) {
			 throw new RuntimeException(e);
		}

	}
	
	private Node getSchemaNode(Document doc){
		Node definition = doc.getFirstChild();
		QName schemaName = new QName("http://www.w3.org/2001/XMLSchema", "schema");
		Node schema = XMLUtils.findNode(definition, schemaName);
		//Attr attrib = doc.createAttributeNS("http://www.w3.org/2001/XMLSchema", new QName("xsd").toString());
		Attr attrib = doc.createAttribute("xmlns:xsd");
		attrib.setValue("http://www.w3.org/2001/XMLSchema");
		((Element)schema).setAttributeNode(attrib);
		return schema;
	}
	
	private static String parseToString (Node node)throws TransformerException{
		
		StringBuffer buffer = new StringBuffer();
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		StringWriter stringWriter = new StringWriter(128);
		transformer.transform(new DOMSource(node), new StreamResult(stringWriter));
		buffer =  stringWriter.getBuffer();
		return buffer.toString();
	}

}
