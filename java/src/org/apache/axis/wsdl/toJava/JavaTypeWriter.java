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

import org.apache.axis.wsdl.gen.Generator;
import org.apache.axis.wsdl.symbolTable.SchemaType;
import org.apache.axis.wsdl.symbolTable.SymTabEntry;
import org.apache.axis.wsdl.symbolTable.SymbolTable;
import org.apache.axis.wsdl.symbolTable.TypeEntry;
import org.xml.sax.SAXException;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.util.HashMap;

/**
 * This is Wsdl2java's Type Writer.  It writes the following files, as appropriate:
 * <typeName>.java, <typeName>Holder.java.
 */
public class JavaTypeWriter implements Generator {

    /** Field HOLDER_IS_NEEDED */
    public static final String HOLDER_IS_NEEDED = "Holder is needed";

    /** Field typeWriter */
    private Generator typeWriter = null;

    /** Field holderWriter */
    private Generator holderWriter = null;

    /** Field symbolTable */
    private SymbolTable symbolTable = null;

	/**
	 * Constructor.
	 */
	//	JAXME_REFACTOR///////////////////////////////////////////////////////////////
	/*	public JavaTypeWriter(
				 Emitter emitter,
				 TypeEntry type,
				 SymbolTable symbolTable) {
    
			 if (type.isReferenced() && !type.isOnlyLiteralReferenced()) {
    
				 // Determine what sort of type this is and instantiate 
				 // the appropriate Writer.
				 Node node = type.getNode();
    
				 // If it's an array, don't emit a class
				 if (!type.getName().endsWith("[]")) {
    
					 // Generate the proper class for either "complex" or "enumeration" types
					 Vector v = Utils.getEnumerationBaseAndValues(
							 node, symbolTable);
					 if (v != null) {
						 typeWriter = getEnumTypeWriter(emitter, type, v);
					 }
					 else {
						 TypeEntry base = SchemaUtils.getComplexElementExtensionBase(
							node, symbolTable);
						 if (base == null) {
							 QName baseQName = SchemaUtils.getSimpleTypeBase(
								node);
							 if (baseQName != null) {
								 base = symbolTable.getType(baseQName);
							 }
						 }
    
						 typeWriter = getBeanWriter(
								 emitter, 
								 type, 
								 SchemaUtils.getContainedElementDeclarations(
									 node, 
									 symbolTable),
								 base,
								 SchemaUtils.getContainedAttributeTypes(
									  node, 
									  symbolTable));
					 }
				 }
    
				 // If the holder is needed (ie., something uses this type as an out or inout
				 // parameter), instantiate the holder writer.
				 if (holderIsNeeded(type)) {
					 holderWriter = getHolderWriter(emitter, type);
				 }
			 }
		 } // ctor
	*/
	//	TODO
	//	NEW CODE////////////////////////////////////////////////////////////////////////////////////        					
	public JavaTypeWriter(
		Emitter emitter,
		TypeEntry type,
		SymbolTable symbolTable)
		throws SAXException {

		this.symbolTable = symbolTable;
		SchemaType sType = symbolTable.getSchemaType(type.getQName());
        

		/*
		 * What does the above algorithm suppose to do ?
		 * if -- is it an enumeration create a EnumTypewriter 
		 * else -- look for a base.
		 * Then put it all intoBean  writer
		 */

		if (type.isReferenced() && !type.isOnlyLiteralReferenced()) {

			if (!type.getName().endsWith("[]")) {

				TypeEntry base = null;

            
                						 
				if (sType.getJaxmetype() != null
					&& sType.getJaxmetype().isSimple() == true
					&& sType.getJaxmetype().getSimpleType().getEnumerations()
						!= null) {
					typeWriter = getEnumTypeWriter(emitter, type);
				} else {
					QName baseQname = sType.getExtentionBase();
					if (baseQname != null) {
						base = symbolTable.getType(baseQname);
					}
				}

				//The even indices are the element types (TypeEntry) and
				//the odd indices are the corresponding names (Strings).

				typeWriter =
					getBeanWriter(
						emitter,
						type,
						sType.getElementInfo(),
						base,
						sType.getAttributeInfo());
				// If the holder is needed (ie., something uses this type as an out or inout
				// parameter), instantiate the holder writer.
			}
			//in anycase check for 
			if (holderIsNeeded(type)) {
				holderWriter = getHolderWriter(emitter, type);
			}

		}

	} // ctor

	///////////////////////////////////////////////////////////////////////////////////////        
	/**
	 * Write all the service bindnigs:  service and testcase.
	 */
	public void generate() throws IOException{
		if (typeWriter != null) {
			typeWriter.generate();
		}
		if (holderWriter != null) {
			holderWriter.generate();
		}
	} // generate

	/**
	 * Does anything use this type as an inout/out parameter?  Query the Type dynamicVar
	 */
	private boolean holderIsNeeded(SymTabEntry entry) {
		Boolean holderIsNeeded =
			(Boolean) entry.getDynamicVar(HOLDER_IS_NEEDED);
		return (holderIsNeeded != null && holderIsNeeded.booleanValue());
	} // holderIsNeeded

    /**
     * getEnumWriter
     * <p/>
     * JAXME_REFACTOR///////////////////////////////////////////////////////////////    protected JavaWriter getEnumTypeWriter(Emitter emitter, TypeEntry type, Vector v) {
     * return new JavaEnumTypeWriter(emitter, type, v);
     * }
     * <p/>
     * TODO
     * NEW CODE///////////////////////////////////////////////////////////////////////////////////
     * <p/>
     * JAXME_REFACTOR///////////////////////////////////////////////////////////////    protected JavaWriter getEnumTypeWriter(Emitter emitter, TypeEntry type, Vector v) {
     * return new JavaEnumTypeWriter(emitter, type, v);
     * }
     * <p/>
     * TODO
     * NEW CODE///////////////////////////////////////////////////////////////////////////////////
     * 
     * @param emitter 
     * @param type    
     * @return JAXME_REFACTOR///////////////////////////////////////////////////////////////
     *         protected JavaWriter getEnumTypeWriter(Emitter emitter, TypeEntry type, Vector v) {
     *         return new JavaEnumTypeWriter(emitter, type, v);
     *         }
     *         <p/>
     *         TODO
     *         NEW CODE////////////////////////////////////////////////////////////////////////////////////
     *         <p/>
     *         JAXME_REFACTOR///////////////////////////////////////////////////////////////
     *         protected JavaWriter getEnumTypeWriter(Emitter emitter, TypeEntry type, Vector v) {
     *         return new JavaEnumTypeWriter(emitter, type, v);
     *         }
     *         <p/>
     *         TODO
     *         NEW CODE////////////////////////////////////////////////////////////////////////////////////
     */

    // JAXME_REFACTOR///////////////////////////////////////////////////////////////

    /*
     *  protected JavaWriter getEnumTypeWriter(Emitter emitter, TypeEntry type, Vector v) {
     *           return new JavaEnumTypeWriter(emitter, type, v);
     *   }
     */

    // TODO
    // NEW CODE////////////////////////////////////////////////////////////////////////////////////
    protected JavaWriter getEnumTypeWriter(Emitter emitter, TypeEntry type) {
        return new JavaEnumTypeWriter(this.symbolTable, emitter, type);
    }

//	///////////////////////////////////////////////////////////////////////////////////////

/**
 * getBeanWriter
 **/
//	JAXME_REFACTOR///////////////////////////////////////////////////////////////
/*	protected JavaWriter getBeanWriter(Emitter emitter, TypeEntry type, 
									 Vector elements, TypeEntry base,
									 Vector attributes) {
		  JavaWriter helperWriter = getBeanHelperWriter(emitter, type, elements, base,
													attributes);
		  // If this complexType is referenced in a
		  // fault context, emit a bean-like exception 
		  // class
		  Boolean isComplexFault = (Boolean)
			  type.getDynamicVar(
								 JavaGeneratorFactory.COMPLEX_TYPE_FAULT);
		  if (isComplexFault != null && 
			  isComplexFault.booleanValue()) {
			  return new JavaBeanFaultWriter(emitter, type, 
											 elements, base, attributes, 
											 helperWriter);
		  }
		  return new JavaBeanWriter(emitter, type, 
									elements, base, attributes, 
									helperWriter);
	  }
    
*/

//	TODO
//	NEW CODE//////////////////////////////////////////////////////////////////////       					
protected JavaWriter getBeanWriter(
	Emitter emitter,
	TypeEntry type,
	HashMap elements,
	TypeEntry base,
	HashMap attributes)
	throws SAXException {
	JavaWriter helperWriter =
		getBeanHelperWriter(emitter, type, elements, base, attributes);
	// If this complexType is referenced in a
	// fault context, emit a bean-like exception 
	// class   
	Boolean isComplexFault =
		(Boolean) type.getDynamicVar(
			JavaGeneratorFactory.COMPLEX_TYPE_FAULT);
	if (isComplexFault != null && isComplexFault.booleanValue()) {
		return new JavaBeanFaultWriter(
			emitter,
			type,
			elements,
			base,
			attributes,
			helperWriter,
			symbolTable);
	}
	return new JavaBeanWriter(
		emitter,
		type,
		elements,
		base,
		attributes,
		helperWriter,
		this.symbolTable);
}

    // //////////////////////////////////////////////////////////////////////////////

    /**
     * getHelperWriter
     * 
     * @param emitter    
     * @param type       
     * @param elements   
     * @param base       
     * @param attributes 
     * @return 
     * @throws SAXException 
     */
    protected JavaWriter getBeanHelperWriter(
            Emitter emitter, TypeEntry type, HashMap elements, TypeEntry base, HashMap attributes)
            throws SAXException {
        return new JavaBeanHelperWriter(emitter, type, elements, base,
                attributes, symbolTable);
    }

    /**
     * getHolderWriter
     * 
     * @param emitter 
     * @param type    
     * @return 
     */
    protected Generator getHolderWriter(Emitter emitter, TypeEntry type) {
        return new JavaHolderWriter(emitter, type);
    }
}    // class JavaTypeWriter
