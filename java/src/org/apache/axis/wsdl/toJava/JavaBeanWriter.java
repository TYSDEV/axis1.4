/*
 * The Apache Software License, Version 1.1
 *
 *
 * Copyright (c) 2001-2003 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "Axis" and "Apache Software Foundation" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache",
 *    nor may "Apache" appear in their name, without prior written
 *    permission of the Apache Software Foundation.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 */
package org.apache.axis.wsdl.toJava;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import javax.xml.namespace.QName;

import org.apache.axis.Constants;
import org.apache.axis.utils.JavaUtils;
import org.apache.axis.utils.Messages;
import org.apache.axis.wsdl.symbolTable.ElementInfo;
import org.apache.axis.wsdl.symbolTable.SchemaType;
import org.apache.axis.wsdl.symbolTable.SymbolTable;
import org.apache.axis.wsdl.symbolTable.TypeEntry;

/**
 * This is Wsdl2java's Complex Type Writer.  It writes the <typeName>.java file.
 */
public class JavaBeanWriter extends JavaClassWriter {

    /** Field sType */
    private SchemaType sType;

    /** Field elements */
    private Vector elements;

    /** Field attributes */
    private HashMap attributes;

    /** Field extendType */
    private TypeEntry extendType;

    /** Field baseType */
    private TypeEntry baseType;

    /** Field helper */
    protected JavaBeanHelperWriter helper;

    /** Field localnames */
    protected Vector localnames = new Vector();    // even indices: types, odd: vars

    /** Field inheritednames */
    protected Vector inheritednames =
            new Vector();                           // even indices: types, odd: vars

    /** Field simpleValueType */
    protected String simpleValueType = null;    // name of type of simple value

    /** Field pw */
    protected PrintWriter pw;

    /** Field symbolTable */
    protected SymbolTable symbolTable;

    // The following fields can be set by extended classes
    // to control processing

    /** Field enableDefaultConstructor */
    protected boolean enableDefaultConstructor = true;

    /** Field enableFullConstructor */
    protected boolean enableFullConstructor = false;

    /** Field enableSimpleConstructors */
    protected boolean enableSimpleConstructors = false;

    /** Field enableToString */
    protected boolean enableToString = false;

    /** Field enableSetters */
    protected boolean enableSetters = true;

    /** Field enableGetters */
    protected boolean enableGetters = true;

    /** Field enableEquals */
    protected boolean enableEquals = true;

    /** Field enableHashCode */
    protected boolean enableHashCode = true;

    /**
     * Constructor.
     * 
     * @param emitter     
     * @param type        The type representing this class
     * @param elements    Vector containing QNames of all elements.
     * @param extendType  The type representing the extended class (or null)
     * @param attributes  Vector containing the attribute types and names
     * @param helper      Helper class writer
     * @param symbolTable 
     */
    protected JavaBeanWriter(Emitter emitter, TypeEntry type, HashMap elements,
                             TypeEntry extendType, HashMap attributes,
                             JavaWriter helper, SymbolTable symbolTable) {

        super(emitter, type.getName(), "complexType");

        this.baseType = type;
        this.sType = symbolTable.getSchemaType(type.getQName());
        this.elements = new Vector(elements.values());
        this.attributes = attributes;
        this.extendType = extendType;
        this.symbolTable = symbolTable;
        this.helper = (JavaBeanHelperWriter) helper;

        if (type.isSimpleType()) {
            enableSimpleConstructors = true;
            enableToString = true;
        }
    }    // ctor

    /**
     * Generate the binding for the given complex type.
     * 
     * @param pw 
     * @throws IOException  
     * @throws SAXException 
     */
    protected void writeFileBody(PrintWriter pw)
            throws IOException{

        this.pw = pw;

        // Populate Names Vector with the names and types of the members.
        // The write methods use the names vector whenever they need to get
        // a member name or type.
        preprocess();

        // Write Member Fields
        writeMemberFields();

        // Write the default constructor
        if (enableDefaultConstructor) {
            writeDefaultConstructor();
        }

        // Write Full Constructor
        if (enableFullConstructor) {
            writeFullConstructor();
        }

        // Write SimpleConstructors
        if (enableSimpleConstructors) {
            writeSimpleConstructors();
        }

        // Write ToString method
        if (enableToString) {
            writeToStringMethod();
        }

        // Write accessor methods
        writeAccessMethods();

        // Write general purpose equals and hashCode methods
        if (enableEquals) {
            writeEqualsMethod();
        }

        if (enableHashCode) {
            writeHashCodeMethod();
        }

        // Write the meta data into a Helper class or
        // embed it in the bean class
        if (!emitter.isHelperWanted()) {

            // Write the helper info into the bean class
            helper.setPrintWriter(pw);
        }

        helper.generate();
    }    // writeFileBody

	/**
	 * Builds the names String vector.
	 * The even indices are the java class names of the
	 * member fields.  The odd indices are the member variable
	 * names.
	 * Also sets the simpleValueType variable to the
	 * java class name of the simple value if this bean represents
	 * a simple type
	 */
//	JAXME_REFACTOR///////////////////////////////////////////////////////////////
//	TODO
/*	protected void preprocess() {
		  // Add element names
		  if (elements != null) {
			  for (int i = 0; i < elements.size(); i++) {
				  ElementDecl elem = (ElementDecl)elements.get(i);
				  String typeName = elem.getType().getName();
				  String variableName;
				  if (elem.getAnyElement()) {
					  typeName = "org.apache.axis.message.MessageElement []";
					  variableName = Constants.ANYCONTENT;
				  } else {
					  String elemName = elem.getName().getLocalPart();
					  variableName = Utils.xmlNameToJava(elemName);
				  }
				  names.add(typeName);
				  names.add(variableName);
				  if (type.isSimpleType() &&
					  variableName.equals("value")) {
					  simpleValueType = typeName;
				  }
			  }
		  }
		  // Add attribute names
		  if (attributes != null) {
			  for (int i = 0; i < attributes.size(); i += 2) {
				  String typeName = ((TypeEntry) attributes.get(i)).getName();
				  QName xmlName = (QName) attributes.get(i + 1);
				  String variableName =
					  Utils.xmlNameToJava(xmlName.getLocalPart());
				  names.add(typeName);
				  names.add(variableName);
				  if (type.isSimpleType() &&
					  variableName.equals("value")) {
					  simpleValueType = typeName;
				  }
			  }
		  }
	  }
*/

//NEW CODE////////////////////////////////////////////////////////////////////////////////////  
	protected void preprocess() {
		/* there is a complication arise by the way JAXME works. when you ask JAXME to give you 
		 * element/attributes it give you attributes from the super classes as well. we 
		 * should remove them from nodes.
		 * TODO We are doing this test twoice in the class should be refactor
		 */
    	 
		HashMap inheritedAttributes = null; 
		HashMap inheritedElements = null;
		if(extendType != null && extendType.getQName()!= null){ 
			SchemaType superType =  symbolTable.getSchemaType(extendType.getQName());
			inheritedAttributes = superType.getAttributeInfo();
			inheritedElements = superType.getElementInfo();
		}
    	
		//create the inherited list 
//		Iterator temp = inheritedElements.keySet().iterator();
//		QName name = null;
//		while(temp.hasNext()){
//			name = (QName)temp.next();
//			inheritednames.add(name);
//			inheritednames.add(inheritedElements.get(name));
//		}
//		temp = inheritedAttributes.keySet().iterator();
//		while(temp.hasNext()){
//				name = (QName)temp.next();
//				inheritednames.add(name);
//				inheritednames.add(inheritedAttributes.get(name));
//		}
//		//all inherited types added
    	
    	
    	
    	
		//create the local list
		// Add element names
		if (elements != null) {
    
			for (int i =0;i< elements.size();i++) {
				ElementInfo elementInfo = (ElementInfo)elements.get(i);
				QName eleQ = elementInfo.getName();
				
				//TODO we are not clear how to get the XML QName to java name mapping is done here. 
				// so we get a type from the type map and get  the "name" from the type.
				//should be replace with something better 
				
			   SchemaType containedElement = elementInfo.getType();
			   String typeName = containedElement.getName();
			   String variableName;
               
				if ("any".equals(elementInfo.getType().getQName().getLocalPart()) ) {
					typeName = "org.apache.axis.message.MessageElement []";
					variableName = Constants.ANYCONTENT;
				} else {
				   // String elemName = elem.getName().getLocalPart();
				   // variableName = Utils.xmlNameToJava(elemName);
					 // ?????? How to get the variable Name ????
					  variableName = Utils.xmlNameToJava(eleQ.getLocalPart());
				}
                
                
                
				//add only if not inherited
				if(inheritedElements == null ||!inheritedElements.containsKey(eleQ)){
					localnames.add(typeName);
					localnames.add(variableName);
				}
             
				if (sType.isSimpleType() &&
					variableName.equals("value")) {
					simpleValueType = typeName;
				}
			}

		}
		// Add attribute names
      
		if (attributes != null) {
			Iterator attributeNames = attributes.keySet().iterator();
      
			for (;attributeNames.hasNext();) {
				QName attributeName = (QName)attributeNames.next();
				SchemaType attributeType = (SchemaType)attributes.get(attributeName);
 
 
				String variableName =
					Utils.xmlNameToJava(attributeName.getLocalPart());

				//TODO we are not clear how to get the XML QName to java name mapping is done here. 
				// so we get a type from the type map and get  the "name" from the type.
				//should be replace with something better                          	
				String typeName = attributeType.getName();
 				
				//add not inherited
				if(inheritedAttributes == null ||!inheritedAttributes.containsKey(attributeName)){
					localnames.add(typeName);
					localnames.add(variableName);
				}
				
				if (sType.isSimpleType() &&
					variableName.equals("value")) {
					simpleValueType = typeName;
				}
			}
		}
        
		if(extendType != null && extendType.getDimensions().equals("[]")) {
			String typeName = extendType.getName();
			String elemName = extendType.getQName().getLocalPart();
			String variableName = Utils.xmlNameToJava(elemName);
			localnames.add(typeName);
			localnames.add(variableName);
		}
	}

//	////////////////////////////////////////////////////////////////////////////////////  
 
	  /**
	   * Returns the appropriate extends text
	   * @return "" or "abstract "
	   */
	  protected String getClassModifiers() {
		  //TODO we drop abstract classes for the time being
//			Node node = baseType.getNode();
//			if (node != null) {
//				if (JavaUtils.isTrueExplicitly(Utils.getAttribute(node, "abstract"))) {
//					return super.getClassModifiers() + "abstract ";
//				}
//			}
		  return super.getClassModifiers();
	  } // getClassModifiers

	/**
	 * Returns the appropriate extends text
	 * @return "" or " extends <class> "
	 */
	protected String getExtendsText() {
		// See if this class extends another class
		String extendsText = "";
		if (extendType != null && !sType.isSimpleType() && extendType.getDimensions().length()==0) {
			extendsText = " extends " + extendType.getName() + " ";
		}
		return extendsText;
	}

    /**
     * Returns the appropriate implements text
     * 
     * @return " implements <classes> "
     */
    protected String getImplementsText() {

        // See if this class extends another class
        String implementsText = " implements java.io.Serializable";

        if (sType.isSimpleType()) {
            implementsText += ", org.apache.axis.encoding.SimpleType";
        }

        implementsText += " ";

        return implementsText;
    }

    /**
     * Writes the member fields.
     */
    protected void writeMemberFields() {

        // Define the member element of the bean
        for (int i = 0; i < localnames.size(); i += 2) {
            String typeName = (String) localnames.get(i);
            String variable = (String) localnames.get(i + 1);

            // Declare the bean element
            pw.print("    private " + typeName + " " + variable + ";");

            // label the attribute fields.
            if ((elements == null) || (i >= (elements.size() * 2))) {
                pw.println("  // attribute");
            } else {
                pw.println();
            }
        }

        pw.println();
    }

    /**
     * Writes the default constructor.
     */
    protected void writeDefaultConstructor() {

        // Define the default constructor
        pw.println("    public " + className + "() {");
        pw.println("    }");
        pw.println();
    }

  /**
   * Writes the full constructor.
   * Note that this class is not recommended for
   * JSR 101 compliant beans, but is provided for
   * extended classes which may wish to generate a full
   * constructor.
   */
  protected void writeFullConstructor(){
//JAXME_REFACTOR///////////////////////////////////////////////////////////////    	
	  /*
	   *  // The constructor needs to consider all extended types
	  Vector extendList = new Vector();
	  extendList.add(type);
	  TypeEntry parent = extendType;
	  while(parent != null) {
		  extendList.add(parent);
		  parent = SchemaUtils.getComplexElementExtensionBase(
			  parent.getNode(),
			  emitter.getSymbolTable());
	  }

	  // Now generate a list of names and types starting with
	  // the oldest parent.  (Attrs are considered before elements).
	  Vector paramTypes = new Vector();
	  Vector paramNames = new Vector();
	  for (int i=extendList.size()-1; i >= 0; i--) {
		  TypeEntry te = (TypeEntry) extendList.elementAt(i);

		  // The names of the inherited parms are mangled
		  // in case they interfere with local parms.
		  String mangle = "";
		  if (i > 0) {
			  mangle = "_" +
				  Utils.xmlNameToJava(te.getQName().getLocalPart()) +
				  "_";
		  }
		  // Process the attributes
		  Vector attributes = SchemaUtils.getContainedAttributeTypes(
			  te.getNode(), emitter.getSymbolTable());
		  if (attributes != null) {
			  for (int j=0; j<attributes.size(); j+=2) {
				  paramTypes.add(((TypeEntry) attributes.get(j)).getName());
				  String name = ((QName)attributes.get(j + 1)).getLocalPart();
				  paramNames.add(mangle + Utils.xmlNameToJava(name));
			  }
		  }
		  // Process the elements
		  Vector elements = SchemaUtils.getContainedElementDeclarations(
			  te.getNode(), emitter.getSymbolTable());
		  if (elements != null) {
			  for (int j=0; j<elements.size(); j++) {
				  ElementDecl elem = (ElementDecl)elements.get(j);
				  paramTypes.add(elem.getType().getName());
				  paramNames.add(mangle +
					  Utils.xmlNameToJava(elem.getName().getLocalPart()));
			  }
		  }
	  }
	  // Set the index where the local params start
	  int localParams = paramTypes.size() - names.size()/2;


	  // Now write the constructor signature
	  if (paramTypes.size() > 0) {
		  pw.println("    public " + className + "(");
		  for (int i=0; i<paramTypes.size(); i++) {
			  pw.print("           " + paramTypes.elementAt(i) +
					   " " + paramNames.elementAt(i));
			  if ((i+1) < paramTypes.size()) {
				  pw.println(",");
			  } else {
				  pw.println(") {");
			  }
		  }

		  // Call the extended constructor to set inherited fields
		  if (extendType != null && localParams > 0) {
			  pw.println("        super(");
			  for (int j=0; j<localParams; j++) {
				  pw.print("            " + paramNames.elementAt(j));
				  if ((j+1) < localParams) {
					  pw.println(",");
				  } else {
					  pw.println(");");
				  }
			  }
		  }
		  // Set local fields directly
		  for (int j=localParams; j<paramNames.size(); j++) {
			  pw.println("        this." + paramNames.elementAt(j) +
						 " = " + paramNames.elementAt(j)+ ";");
		  }
		  pw.println("    }");
		  pw.println();
	  }*/
	  //TODO
//NEW CODE////////////////////////////////////////////////////////////////////////////////////        
       	
	  SchemaType sBaseType = symbolTable.getSchemaType(sType.getExtentionBase());
	  Iterator attribNames = sType.getAttributeNames();//get the whole attribute
	  Iterator elementNames = sType.getElementNames();//get the whole element
		
	  Iterator attribBaseNames = null;
	  Iterator elementBaseNames = null;
	  if(sBaseType != null){
		  attribBaseNames = sBaseType.getAttributeNames();//
		  elementBaseNames = sBaseType.getElementNames();
	  }

	  Vector paramTypes = new Vector();
	  Vector paramNames = new Vector();
	  Vector paramTypesBase = new Vector();
	  Vector paramNamesBase = new Vector();
	  //we will fill up the type Vectors 
	 if (attribNames != null) {
		  while(attribNames.hasNext()){
			  QName attribName = (QName)attribNames.next();
				
			  TypeEntry entry = symbolTable.getType(
				  sType.getAttributeTypeByName(attribName).getQName());	
			  paramTypes.add(entry.getName());
		   		
			  String name = attribName.getLocalPart();
			  paramNames.add(Utils.xmlNameToJava(name));
		  }
	  }
	  if(elementNames != null){
		  while(elementNames.hasNext()){
			  ElementInfo eleDecl = sType.getElementTypeByName(
				  (QName)elementNames.next());

			  TypeEntry entry = symbolTable.getType(eleDecl.getType().getQName());	
			  paramTypes.add(entry.getName());

			  paramNames.add(Utils.xmlNameToJava(eleDecl.getName().getLocalPart()));
		  }
	  }
        	
	  if (attribBaseNames != null) {
		  while(attribBaseNames.hasNext()) {
			  QName attribBaseName = (QName)attribBaseNames.next();
				
			  TypeEntry entry = symbolTable.getType(
				  sBaseType.getAttributeTypeByName(attribBaseName).getQName());	
			  paramTypesBase.add(entry.getName());
				
			  String name = attribBaseName.getLocalPart();
			  paramNamesBase.add(Utils.xmlNameToJava(name));
		  }
	  }
	  if(elementBaseNames != null){
		  while(elementBaseNames.hasNext()){
			  ElementInfo eleDecl = sType.getElementTypeByName(
					  (QName)elementBaseNames.next());

			  TypeEntry entry = symbolTable.getType(eleDecl.getType().getQName());	
			  paramTypesBase.add(entry.getName());
				
			  paramNamesBase.add(Utils.xmlNameToJava(eleDecl.getName().getLocalPart()));
		  }
	  }      	
	  // Set the index where the local params start
	 // int localParams = paramTypes.size() - (attribBase.size()+elementBase.size());
	  // Now write the constructor signature
	  if (paramTypes.size() > 0) {
		  Vector paramDetail = new Vector();
		  for(int i = 0;i<paramTypes.size();i++){
			  paramDetail.add(paramTypes.elementAt(i)+" " +paramNames.elementAt(i));
		  }
		  pw.println("    public " + className + "(");
		  pw.println(vectorParsedString(paramDetail));
		  pw.println(") {");
            
	 }
	 // Call the extended constructor to set inherited fields
        
	   if (baseType != null && paramNamesBase.size() > 0) {
			  pw.println("        super(");
			  pw.println(vectorParsedString(paramNamesBase));
			  pw.println(");");
	  }
	  // Set local fields directly
	  for (int j=0; j<paramNames.size(); j++) {
//			  for(int k = 0;k<paramNamesBase.size();k++){
//				  if(!(paramNames.elementAt(j).equals(paramNamesBase.elementAt(k)))){
		  if(!paramNamesBase.contains(paramNames.get(j)))
			  pw.println("        this." + paramNames.get(j) +
												 " = " + paramNames.get(j)+ ";");	
                
	  }
	  if (paramTypes.size() > 0) {        
		  pw.println("    }");
		  pw.println();
	  }	
   }

    // /////////////////////////////////////////////////////////////////////////////////////

    /*
     * Thid is the utility method for getting string from iterator detail
     */

    /**
     * Method vectorParsedString
     * 
     * @param list 
     * @return 
     */
    private String vectorParsedString(Vector list) {

        String str = " ";

        for (int i = 0; i < list.size(); i++) {
            str = str + list.elementAt(i);

            if (i + 1 < list.size()) {
                str = str + ",";
            }
        }

        return str;
    }

	/**
	 * Writes the constructors for SimpleTypes.
	 * Writes a constructor accepting a string and
	 * a constructor accepting the simple java type.
	 */
	protected void writeSimpleConstructors() {
		// If this is a simple type,need to emit a string
		// constructor and a value construtor.
		if (sType.isSimpleType() && simpleValueType != null) {
			if (!simpleValueType.equals("java.lang.String")) {
				pw.println("    public " + className + "(" +
						   simpleValueType + " value) {");
				pw.println("        this.value = value;");
				pw.println("    }");
				pw.println();
			}

			pw.println("    // " + Messages.getMessage("needStringCtor"));
			pw.println("    public " + className + "(java.lang.String value) {");
			// Make sure we wrap base types with its Object type
			String wrapper = JavaUtils.getWrapper(simpleValueType);

			if (wrapper != null) {
				pw.println("        this.value = new " + wrapper +
						   "(value)." + simpleValueType + "Value();");
			} else {
				if (simpleValueType.equals("byte[]")) {
					pw.println("        this.value = org.apache.axis.types.HexBinary.decode(value);");
				}
				else if (simpleValueType.equals("org.apache.axis.types.URI")) {
					pw.println("        try {");
					pw.println("            this.value = new org.apache.axis.types.URI(value);");
					pw.println("        }");
					pw.println("        catch (org.apache.axis.types.URI.MalformedURIException mue) {");
					pw.println("            this.value = new org.apache.axis.types.URI();");
					pw.println("       }");
				} 
				else if (simpleValueType.equals("java.util.Date")) {
				  pw.println("        try {");
				  pw.println("            this.value = (java.text.DateFormat.getDateTimeInstance()).parse(value);");
				  pw.println("        }");
				  pw.println("        catch (java.text.ParseException e){");
				  pw.println("            throw new java.lang.RuntimeException(e.toString());");
				  pw.println("        }");
				}
				else if (simpleValueType.equals("java.util.Calendar")) {
				  pw.println("        java.util.Calendar cal = java.util.Calendar.getInstance();"); 
				  pw.println("        try {");
				  pw.println("          java.util.Date dt = (java.text.DateFormat.getDateTimeInstance()).parse(value);");
				  pw.println("          cal.setTime(dt);");
				  pw.println("          this.value = cal;");
				  pw.println("        }");
				  pw.println("        catch (java.text.ParseException e){");
				  pw.println("            throw new java.lang.RuntimeException(e.toString());");
				  pw.println("        }");
				}                
				else {
					pw.println("        this.value = new " +
							   simpleValueType + "(value);");
				}
			}
			pw.println("    }");
			pw.println();
		}
	}

	/**
	 * Writes the toString method
	 * Currently the toString method is only written for
	 * simpleTypes.
	 */
	protected void writeToStringMethod() {
		// If this is a simple type, emit a toString
		if (sType.isSimpleType() && simpleValueType != null) {
			pw.println("    // " + Messages.getMessage("needToString"));
			String wrapper = JavaUtils.getWrapper(simpleValueType);
			pw.println("    public java.lang.String toString() {");
			if (wrapper != null) {
				pw.println("        return new " + wrapper + "(value).toString();");
			} else {
				if(simpleValueType.equals("byte[]")) {
					pw.println("        return value == null ? null : org.apache.axis.types.HexBinary.encode(value);" );
				} else {              
					pw.println("        return value == null ? null : value.toString();");
				}
			}
			pw.println("    }");
			pw.println();
		}
	}

  /**
   * Writes the setter and getter methods
   */
  protected void writeAccessMethods() {
	  int j = 0;
	  // Define getters and setters for the bean elements
	  for (int i = 0; i < localnames.size(); i += 2, j++) {
		  String typeName = (String) localnames.get(i);
		  String name = (String) localnames.get(i + 1);
		  String capName = Utils.capitalizeFirstChar(name);

		  String get = "get";
		  if (typeName.equals("boolean"))
			  get = "is";

		  if (enableGetters) {
			  pw.println("    public " + typeName + " " +
						 get + capName + "() {");
			  pw.println("        return " + name + ";");
			  pw.println("    }");
			  pw.println();
		  }
		  if (enableSetters) {
			  pw.println("    public void set" + capName + "(" +
						 typeName + " " + name + ") {");
			  pw.println("        this." + name + " = " + name + ";");
			  pw.println("    }");
			  pw.println();
		  }

		  // If this is a special collection type, insert extra
		  // java code so that the serializer/deserializer can recognize
		  // the class.  This is not JAX-RPC, and will be replaced with
		  // compliant code when JAX-RPC determines how to deal with this case.
		  // These signatures comply with Bean Indexed Properties which seems
		  // like the reasonable approach to take for collection types.
		  // (It may be more efficient to handle this with an ArrayList...but
		  // for the initial support it was easier to use an actual array.)
//////////////////////////////////////////////////////////////////////////////////////
//		  if (elements != null && j < elements.size()) {
//			  ElementDecl elem = (ElementDecl)elements.get(j);
//			  if (elem.getType().getQName().getLocalPart().indexOf("[") > 0) {
//				  String compName = typeName.substring(0, typeName.lastIndexOf("["));
//				  if (enableGetters) {
//					  pw.println("    public " + compName + " " + get + capName +
//								 "(int i) {");
//					  pw.println("        return " + name + "[i];");
//					  pw.println("    }");
//					  pw.println();
//				  }
//				  if (enableSetters) {
//					  pw.println("    public void set" + capName + "(int i, " +
//								 compName + " value) {");
//					  // According to the section 7.2 of the JavaBeans
//					  // specification, the indexed setter should not
//					  // establish or grow the array.  Thus the following
//					  // code is not generated for compliance purposes.
//					  /*
//					  int bracketIndex = typeName.indexOf("[");
//					  String newingName = typeName.substring(0, bracketIndex + 1);
//					  String newingSuffix = typeName.substring(bracketIndex + 1);
//
//					  pw.println("        if (this." + name + " == null ||");
//					  pw.println("            this." + name + ".length <= i) {");
//					  pw.println("            " + typeName + " a = new " +
//								 newingName + "i + 1" + newingSuffix + ";");
//					  pw.println("            if (this." + name + " != null) {");
//					  pw.println("                for(int j = 0; j < this." + name +
//								 ".length; j++)");
//					  pw.println("                    a[j] = this." + name + "[j];");
//					  pw.println("            }");
//					  pw.println("            this." + name + " = a;");
//					  pw.println("        }");
//					  */
//					  pw.println("        this." + name + "[i] = value;");
//					  pw.println("    }");
//					  pw.println();
//				  }
//			  }
//		  }
//	  }
///NEWCODE///////////////////////////////////////////////////////////////////////////        
		  if (elements != null && j < elements.size()) {
			  ElementInfo elem = (ElementInfo)elements.get(j);
				
			  if (elem.getType().getQName().getLocalPart().indexOf("[") > 0) {
				  String compName = typeName.substring(0, typeName.lastIndexOf("["));
				  if (enableGetters) {
					  pw.println("    public " + compName + " " + get + capName +
								 "(int i) {");
					  pw.println("        return " + name + "[i];");
					  pw.println("    }");
					  pw.println();
				  }
				  if (enableSetters) {
					  pw.println("    public void set" + capName + "(int i, " +
								 compName + " value) {");
					  // According to the section 7.2 of the JavaBeans
					  // specification, the indexed setter should not
					  // establish or grow the array.  Thus the following
					  // code is not generated for compliance purposes.
					  /*
					  int bracketIndex = typeName.indexOf("[");
					  String newingName = typeName.substring(0, bracketIndex + 1);
					  String newingSuffix = typeName.substring(bracketIndex + 1);

					  pw.println("        if (this." + name + " == null ||");
					  pw.println("            this." + name + ".length <= i) {");
					  pw.println("            " + typeName + " a = new " +
								 newingName + "i + 1" + newingSuffix + ";");
					  pw.println("            if (this." + name + " != null) {");
					  pw.println("                for(int j = 0; j < this." + name +
								 ".length; j++)");
					  pw.println("                    a[j] = this." + name + "[j];");
					  pw.println("            }");
					  pw.println("            this." + name + " = a;");
					  pw.println("        }");
					  */
					  pw.println("        this." + name + "[i] = value;");
					  pw.println("    }");
					  pw.println();
				  }
			  }
		  }
	  }
  }

/**
 * Writes a general purpose equals method
 */
protected void writeEqualsMethod() {

	// The __equalsCalc field and synchronized method are necessary
	// in case the object has direct or indirect references to itself.
	pw.println("    private java.lang.Object __equalsCalc = null;");
	pw.println("    public synchronized boolean equals(java.lang.Object obj) {");

	// First do the general comparison checks
	pw.println("        if (!(obj instanceof " + className + ")) return false;");
	pw.println("        " +  className + " other = (" + className + ") obj;");
	pw.println("        if (obj == null) return false;");
	pw.println("        if (this == obj) return true;");

	// Have we been here before ? return true if yes otherwise false
	pw.println("        if (__equalsCalc != null) {");
	pw.println("            return (__equalsCalc == obj);");
	pw.println("        }");
	pw.println("        __equalsCalc = obj;");

	// Before checking the elements, check equality of the super class
	String truth = "true";
	if (extendType != null && !sType.isSimpleType()) {
		truth = "super.equals(obj)";
	}
	pw.println("        boolean _equals;");
	if (localnames.size() == 0) {
		pw.println("        _equals = " + truth + ";");
	} else {
		pw.println("        _equals = " + truth + " && ");
		for (int i = 0; i < localnames.size(); i += 2) {
			String variableType = (String) localnames.get(i);
			String variable = (String) localnames.get(i + 1);
			String get = "get";

			if (variableType.equals("boolean"))
				get = "is";

			if (variableType.equals("int") ||
					variableType.equals("long") ||
					variableType.equals("short") ||
					variableType.equals("float") ||
					variableType.equals("double") ||
					variableType.equals("boolean") ||
					variableType.equals("byte")) {
				pw.print("            this." + variable + " == other." + get +
						Utils.capitalizeFirstChar(variable) + "()");
			} else if (variableType.indexOf("[") >=0) {
				// Use java.util.Arrays.equals to compare arrays.
				pw.println("            ((this." + variable +
						   "==null && other." + get +
						   Utils.capitalizeFirstChar(variable) + "()==null) || ");
				pw.println("             (this." + variable + "!=null &&");
				pw.print("              java.util.Arrays.equals(this." + variable +
						 ", other." + get +
						 Utils.capitalizeFirstChar(variable) + "())))");

			} else {
				pw.println("            ((this." + variable +
						   "==null && other." + get +
						   Utils.capitalizeFirstChar(variable) + "()==null) || ");
				pw.println("             (this." + variable + "!=null &&");
				pw.print("              this." + variable +
						 ".equals(other." + get +
						 Utils.capitalizeFirstChar(variable) + "())))");
			}
			if (i == (localnames.size() - 2))
				pw.println(";");
			else
				pw.println(" &&");
		}
	}
	pw.println("        __equalsCalc = null;");
	pw.println("        return _equals;");
	pw.println("    }");
	pw.println("");
}

/**
 * Writes a general purpose hashCode method.
 */
protected void writeHashCodeMethod() {
	// The __hashCodeCalc field and synchronized method are necessary
	// in case the object has direct or indirect references to itself.
	pw.println("    private boolean __hashCodeCalc = false;");
	pw.println("    public synchronized int hashCode() {");
	pw.println("        if (__hashCodeCalc) {");
	pw.println("            return 0;");
	pw.println("        }");
	pw.println("        __hashCodeCalc = true;");

	// Get the hashCode of the super class
	String start = "1";
	if (extendType != null && !sType.isSimpleType()) {
		start = "super.hashCode()";
	}
	pw.println("        int _hashCode = " + start + ";");
	for (int i = 0; i < localnames.size(); i += 2) {
		String variableType = (String) localnames.get(i);
		String variable = (String) localnames.get(i + 1);
		String get = "get";

		if (variableType.equals("boolean"))
			get = "is";

		if (variableType.equals("int") ||
			variableType.equals("short") ||
			variableType.equals("byte")) {
			pw.println("        _hashCode += " + get +
					 Utils.capitalizeFirstChar(variable) + "();");
		} else if (variableType.equals("boolean")) {
			pw.println("        _hashCode += new Boolean(" + get +
					   Utils.capitalizeFirstChar(variable) + "()).hashCode();");
		} else if (variableType.equals("long")) {
			pw.println("        _hashCode += new Long(" + get +
					   Utils.capitalizeFirstChar(variable) + "()).hashCode();");
		} else if (variableType.equals("float")) {
			pw.println("        _hashCode += new Float(" + get +
					   Utils.capitalizeFirstChar(variable) + "()).hashCode();");
		} else if (variableType.equals("double")) {
			pw.println("        _hashCode += new Double(" + get +
					   Utils.capitalizeFirstChar(variable) + "()).hashCode();");
		} else if (variableType.indexOf("[") >=0) {
			// The hashCode calculation for arrays is complicated.
			// Wish there was a hashCode method in java.utils.Arrays !
			// Get the hashCode for each element of the array which is not an array.
			pw.println("        if (" + get +
					   Utils.capitalizeFirstChar(variable) + "() != null) {");
			pw.println("            for (int i=0;");
			pw.println("                 i<java.lang.reflect.Array.getLength(" + get +
					   Utils.capitalizeFirstChar(variable) + "());");
			pw.println("                 i++) {");
			pw.println("                java.lang.Object obj = java.lang.reflect.Array.get(" +
					   get +
					   Utils.capitalizeFirstChar(variable) + "(), i);");
			pw.println("                if (obj != null &&");
			pw.println("                    !obj.getClass().isArray()) {");
			pw.println("                    _hashCode += obj.hashCode();");
			pw.println("                }");
			pw.println("            }");
			pw.println("        }");
		} else {
			pw.println("        if (" + get +
					   Utils.capitalizeFirstChar(variable) + "() != null) {");
			pw.println("            _hashCode += " + get +
					   Utils.capitalizeFirstChar(variable) + "().hashCode();");
			pw.println("        }");
		}
	}
	// Reset the __hashCodeCalc variable and return
	pw.println("        __hashCodeCalc = false;");
	pw.println("        return _hashCode;");
	pw.println("    }");
	pw.println("");
}
}    // class JavaBeanWriter
