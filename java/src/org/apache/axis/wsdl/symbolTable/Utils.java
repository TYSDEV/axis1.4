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
package org.apache.axis.wsdl.symbolTable;

import org.apache.axis.Constants;
import org.apache.axis.utils.JavaUtils;
import org.apache.axis.utils.XMLUtils;
import org.apache.axis.wsdl.jaxme.JAXMEInternalException;
import org.apache.ws.jaxme.xs.XSEnumeration;
import org.apache.ws.jaxme.xs.XSSimpleType;
import org.apache.ws.jaxme.xs.XSType;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.namespace.QName;
import javax.xml.rpc.holders.BooleanHolder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * This class contains static utility methods for the emitter.
 * 
 * @author Rich Scheuerle  (scheu@us.ibm.com)
 * @author Tom Jordahl (tomj@macromedia.com)
 */
public class Utils {

    /** cache of namespaces -> maps of localNames -> QNames */
    static final Map nsmap = new HashMap();

    /**
     * Find or create a QName with the specified namespace/localName.
     * 
     * @param namespace 
     * @param localName 
     * @return 
     */
    static QName findQName(String namespace, String localName) {

        QName qname = null;

        // get the inner map, using the namespace as a key
        Map ln2qn = (Map) nsmap.get(namespace);

        if (null == ln2qn) {        // cache miss
            ln2qn = new HashMap();

            nsmap.put(namespace, ln2qn);

            qname = new QName(namespace, localName);

            ln2qn.put(localName, qname);
        } else {                    // cache hit
            qname = (QName) ln2qn.get(localName);

            if (null == qname) {    // cache miss
                qname = new QName(namespace, localName);

                ln2qn.put(localName, qname);
            } else {

                // cache hit
            }
        }

        return qname;
    }

    /**
     * getNillableQName returns the QName to use if the nillable=true
     * attribute is used.
     * For example, in JAX-RPC:
     * The QName "xsd:int" maps to a java int.
     * However if an element with a type="xsd:int" also has the
     * "nillable=true" attribute, the type should be an Integer (not an int).
     * So in these circumstances, this routine is called with xsd:int to
     * get a suitable qname (soapenc:int) which maps to Integer.
     * 
     * @param qName QName
     * @return 
     */
    public static QName getNillableQName(QName qName) {

        QName rc = qName;

        if (Constants.isSchemaXSD(rc.getNamespaceURI())) {
            String localName = rc.getLocalPart();

            if (localName.equals("int") || localName.equals("long")
                    || localName.equals("short") || localName.equals("float")
                    || localName.equals("double")
                    || localName.equals("boolean")
                    || localName.equals("byte")) {
                rc = findQName(Constants.URI_DEFAULT_SOAP_ENC,
                        qName.getLocalPart());
            } else if (localName.equals("base64Binary")) {
                rc = findQName(Constants.URI_DEFAULT_SOAP_ENC, "base64");
            } else if (localName.equals("hexBinary")) {
                rc = findQName(Constants.URI_DEFAULT_SCHEMA_XSD, "hexBinary");
            }
        }

        return rc;
    }

    /**
     * Given a node, return the value of the given attribute.
     * If the attribute does not exist, searching continues through ancestor nodes until found.
     * This method is useful for finding attributes that pertain to a group of contained
     * nodes (i.e. xlmns, xmlns:tns, targetNamespace, name)
     * 
     * @param node 
     * @param attr 
     * @return 
     */
    public static String getScopedAttribute(Node node, String attr) {

        if (node == null) {
            return null;
        }

        if (node.getAttributes() == null) {
            return getScopedAttribute(node.getParentNode(), attr);
        }

        Node attrNode = node.getAttributes().getNamedItem(attr);

        if (attrNode != null) {
            return attrNode.getNodeValue();
        } else {
            return getScopedAttribute(node.getParentNode(), attr);
        }
    }

    /**
     * Given a node, return the value of the given attribute.
     * Returns null if the attribute is not found
     * 
     * @param node 
     * @param attr 
     * @return 
     */
    public static String getAttribute(Node node, String attr) {

        if ((node == null) || (node.getAttributes() == null)) {
            return null;
        }

        Node attrNode = node.getAttributes().getNamedItem(attr);

        if (attrNode != null) {
            return attrNode.getNodeValue();
        } else {
            return null;
        }
    }

    /**
     * Given a node, return the attributes that have the specified local name.
     * Returns null if the attribute is not found
     * 
     * @param node      
     * @param localName 
     * @return 
     */
    public static Vector getAttributesWithLocalName(Node node,
                                                    String localName) {

        Vector v = new Vector();

        if (node == null) {
            return v;
        }

        NamedNodeMap map = node.getAttributes();

        if (map != null) {
            for (int i = 0; i < map.getLength(); i++) {
                Node attrNode = map.item(i);

                if ((attrNode != null)
                        && attrNode.getLocalName().equals(localName)) {
                    v.add(attrNode);
                }
            }
        }

        return v;
    }

    /**
     * An xml element may have a name.
     * For example &lt.element name="foo" type="b:bar"&gt.
     * has the name "element".  This routine gets the full QName of the element.
     * 
     * @param node 
     * @return 
     */
    public static QName getNodeQName(Node node) {

        if (node == null) {
            return null;
        }

        String localName = node.getLocalName();

        if (localName == null) {
            return null;
        }

        String namespace = node.getNamespaceURI();

        return (findQName(namespace, localName));
    }









    /**
     * Convert a prefixed name into a qname
     * 
     * @param node         
     * @param prefixedName 
     * @return 
     */
    public static QName getQNameFromPrefixedName(Node node,
                                                 String prefixedName) {

        String localName = prefixedName.substring(prefixedName.lastIndexOf(":")
                + 1);
        String namespace = null;

        // Associate the namespace prefix with a namespace
        if (prefixedName.length() == localName.length()) {
            namespace = getScopedAttribute(
                    node, "xmlns");    // Get namespace for unqualified reference
        } else {
            namespace = getScopedAttribute(node,
                    "xmlns:"
                    + prefixedName.substring(0,
                            prefixedName.lastIndexOf(":")));
        }

        return (findQName(namespace, localName));
    }

//	/**
//	 * This method returns a set of all types that are derived
//	 * from this type via an extension of a complexType
//	 */
//	public static HashSet getDerivedTypes(TypeEntry type, SymbolTable symbolTable) {
//		HashSet types = new HashSet();
//		if (type != null && type.getNode() != null) {
//			getDerivedTypes(type, types, symbolTable);
//		} else if (Constants.isSchemaXSD(type.getQName().getNamespaceURI()) &&
//				   (type.getQName().getLocalPart().equals("anyType")||
//					type.getQName().getLocalPart().equals("any"))) {
//			// All types are derived from anyType
//			types.addAll(symbolTable.getTypeIndex().values());
//		}
//		return types;
//	} // getNestedTypes

//	private static void getDerivedTypes(TypeEntry type, HashSet types, SymbolTable symbolTable) {
//
//		// If all types are in the set, return
//		if (types.size() == symbolTable.getTypeEntryCount()) {
//			return;
//		}
//
//		// Search the dictionary for derived types of type
//		for (Iterator it = symbolTable.getTypeIndex().values().iterator(); it.hasNext();) {
//			Type t = (Type)it.next();
//			if (t instanceof DefinedType &&
//				t.getNode() != null &&
//				!types.contains(t) &&
//				(((DefinedType)t).getComplexTypeExtensionBase(symbolTable) == type)) {
//				types.add(t);
//				getDerivedTypes(t, types, symbolTable);
//			}
//		}
//	} // getDerivedTypes


	/**
	 * This method returns a set of all types that are derived
	 * from this type via an extension of a complexType
	 */
	public static HashSet getDerivedTypes(TypeEntry type, SymbolTable symbolTable) {
		HashSet types = new HashSet();
		if (type != null) {
			getDerivedTypes(type, types, symbolTable);
		} else if (Constants.isSchemaXSD(type.getQName().getNamespaceURI()) &&
				   (type.getQName().getLocalPart().equals("anyType")||
					type.getQName().getLocalPart().equals("any"))) {
			// All types are derived from anyType
			types.addAll(symbolTable.getTypeIndex().values());
		}
		return types;
	} // getNestedTypes

	private static void getDerivedTypes(TypeEntry type, HashSet types, SymbolTable symbolTable) {

		// If all types are in the set, return
		if (types.size() == symbolTable.getTypeEntryCount()) {
			return;
		}

		// Search the dictionary for derived types of type
		for (Iterator it = symbolTable.getTypeIndex().values().iterator(); it.hasNext();) {
			Type t = (Type)it.next();
			if (t instanceof SchemaType &&
				!types.contains(t) &&
				(symbolTable.getType(((SchemaType)t).getExtentionBase()) == type)) {
				types.add(t);
				getDerivedTypes(t, types, symbolTable);
			}
		}
	} // getDerivedTypes

	/**
	 * This method returns a set of all the nested types.
	 * Nested types are types declared within this TypeEntry (or descendents)
	 * plus any extended types and the extended type nested types
	 * The elements of the returned HashSet are Types.
	 * @param type is the type entry to consider
	 * @param symbolTable is the symbolTable
	 * @param derivedFlag should be set if all dependendent derived types should also be 
	 * returned.
	 */
	public static HashSet getNestedTypes(TypeEntry type, SymbolTable symbolTable, 
										 boolean derivedFlag) {
		HashSet types = new HashSet();
		getNestedTypes(type, types, symbolTable, derivedFlag);
        
		return types;
	} // getNestedTypes

	private static void getNestedTypes(
			TypeEntry type, HashSet types, SymbolTable symbolTable, 
			boolean derivedFlag) {
				
		if (type == null) {
			return;
		}

		// If all types are in the set, return
		if (types.size() == symbolTable.getTypeEntryCount()) {
			return;
		}
        
		// Process types derived from this type
		if (derivedFlag) {
			HashSet derivedTypes = getDerivedTypes(type, symbolTable);
			Iterator it = derivedTypes.iterator();
			while(it.hasNext()) {
				TypeEntry derivedType = (TypeEntry) it.next();
				if (!types.contains(derivedType)) {
					types.add(derivedType);
					getNestedTypes(derivedType, types, symbolTable, derivedFlag);
				}
			}
		}
////////JAXME_REFACTOR///////////////////////////////////////////////////////////////        
//		  // Continue only if the node exists
//		  if(type.getNode() == null) {
//			  return;
//		  }
//		  Node node = type.getNode();
//
//		  // Process types declared in this type
//		  Vector v = SchemaUtils.getContainedElementDeclarations(node, symbolTable);
//		  if (v != null) {
//			  for (int i = 0; i < v.size(); i++) {
//				  ElementDecl elem = (ElementDecl)v.get(i);
//				  if (!types.contains(elem.getType())) {
//					  types.add(elem.getType());
//					  getNestedTypes(elem.getType(), 
//									 types, 
//									 symbolTable, derivedFlag);
//				  }
//			  }
//		  }
//
//		  // Process attributes declared in this type
//		  v = SchemaUtils.getContainedAttributeTypes(node, symbolTable);
//		  if (v != null) {
//			  for (int i = 0; i < v.size(); i+=2) {
//				  if (!types.contains(v.get(i))) {
//					  types.add(v.get(i));
//					  getNestedTypes(
//							  ((TypeEntry) v.get(i)), types, symbolTable, derivedFlag);
//				  }
//			  }
//		  }
//		  // Process referenced types
//		  if (type.getRefType() != null &&
//			  !types.contains(type.getRefType())) {
//			  types.add(type.getRefType());
//			  getNestedTypes(type.getRefType(), types, symbolTable, derivedFlag);
//		  }
//
//		  /* Anonymous processing and should be automatically handled by the 
//			 reference processing above
//		  // Get the anonymous type of the element
//		  QName anonQName = SchemaUtils.getElementAnonQName(node);
//		  if (anonQName != null) {
//			  TypeEntry anonType = symbolTable.getType(anonQName);
//			  if (anonType != null && !types.contains(anonType)) {
//				  types.add(anonType);
//			  }
//		  }
//
//		  // Get the anonymous type of an attribute
//		  anonQName = SchemaUtils.getAttributeAnonQName(node);
//		  if (anonQName != null) {
//			  TypeEntry anonType = symbolTable.getType(anonQName);
//			  if (anonType != null && !types.contains(anonType)) {
//				  types.add(anonType);
//			  }
//		  }
//		  */
//
//		  // Process extended types
//		  TypeEntry extendType = SchemaUtils.getComplexElementExtensionBase(node, symbolTable);
//		  if (extendType != null) {
//			  if (!types.contains(extendType)) {
//				  types.add(extendType);
//				  getNestedTypes(extendType, types, symbolTable, derivedFlag);
//			  }
//		  }
//
//		  /* Array component processing should be automatically handled by the
//			 reference processing above.
//		  // Process array components
//		  QName componentQName = SchemaUtils.getArrayComponentQName(node, new IntHolder(0));
//		  TypeEntry componentType = symbolTable.getType(componentQName);
//		  if (componentType == null) {
//			  componentType = symbolTable.getElement(componentQName);
//		  }
//		  if (componentType != null) {
//			  if (!types.contains(componentType)) {
//				  types.add(componentType);
//				  getNestedTypes(componentType, types, symbolTable, derivedFlag);
//			  }
//		  }
//		  */
//
//
//NEW CODE//////////////////////////////////////////////////////////////////////////////////////        
  // Process referenced types
  if (type.getRefType() != null &&
	  !types.contains(type.getRefType())) {
	  types.add(type.getRefType());
	  getNestedTypes(type.getRefType(), types, symbolTable, derivedFlag);
  }


   // Process types declared in this type
   SchemaType stype; 
   if(type instanceof SchemaType)
		stype = (SchemaType)type;
   else
		stype = symbolTable.getSchemaType(type.getQName());
   System.out.println(type.getQName());
   if(stype == null && type.getQName().getLocalPart().indexOf((int)'[')>0){
		//TODO FIXTHIS this is becouse x[number] types (arrays)
		//still the array handling have problems and it compansate by the 
		//ref type. so afford to return 
		return;   		
   }
   
	Iterator v = stype.getElementNames();
	if (v != null) {
		for (;v.hasNext();) {
			TypeEntry entry = symbolTable.getSchemaType(stype.getElementTypeByName((QName)v.next()).getType().getQName());
			if (!types.contains(entry)) {
				types.add(entry);
				getNestedTypes(entry, 
							   types, 
							   symbolTable, derivedFlag);
			}
		}
	}

	// Process attributes declared in this type
	v = stype.getAttributeNames();
	if (v != null) {
		for (;v.hasNext();) {
			TypeEntry entry = symbolTable.getType(stype.getAttributeTypeByName((QName)v.next()).getQName());
			if (!types.contains(entry)) {
				types.add(entry);
				getNestedTypes(entry, 
							   types, 
							   symbolTable, derivedFlag);
			}
		}
	}


		// Process extended types
		QName extendedname = stype.getExtentionBase();
		if(extendedname!= null){
			TypeEntry extendType = symbolTable.getType(extendedname);
			if (extendType != null) {
				if (!types.contains(extendType)) {
					types.add(extendType);
					getNestedTypes(extendType, types, symbolTable, derivedFlag);
				}
			}
		}
//////////////////////////////////////////////////////////////////////////////////		
	} // getNestedTypes

    /**
     * Generate an XML prefixed attribute value with a corresponding xmlns
     * declaration for the prefix.  If there is no namespace,
     * don't prefix the name or emit the xmlns attribute.
     * <p/>
     * Caller should provide the enclosing quotes.
     * <p/>
     * Usage:  println("name=\"" + genXMLQNameString(qname, "foo") + "\""
     * 
     * @param qname  
     * @param prefix 
     * @return 
     */
    public static String genQNameAttributeString(QName qname, String prefix) {

        if ((qname.getNamespaceURI() == null)
                || qname.getNamespaceURI().equals("")) {
            return qname.getLocalPart();
        }

        return prefix + ":" + qname.getLocalPart() + "\" xmlns:" + prefix
                + "=\"" + qname.getNamespaceURI();
    }
//	///////////////////////////////////////////////////////////////////////
//	TODO this is a new method
//	///////////////////////////////////////////////////////////////////////    
	  public static boolean  isEnumeration(SchemaType stype){
		  try{
			  XSType type = stype.getJaxmetype();
			  //if type is null it is a inbuilt type. no way that it is an 
			  //enumeration
			  if(type != null && type.isSimple()){
				  XSSimpleType simpleType = type.getSimpleType();
				  XSEnumeration[] xsenu = simpleType.getEnumerations();
				  return !((xsenu == null) || (xsenu.length == 0));
			  }
			  return false;
		  }catch(SAXException e){
			  throw new JAXMEInternalException(e);
		  }
	  }/////////////////////////////////////////////////////////////////////////
}
