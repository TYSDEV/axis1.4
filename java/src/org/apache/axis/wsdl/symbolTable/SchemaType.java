package org.apache.axis.wsdl.symbolTable;

import org.apache.axis.wsdl.jaxme.JAXMEInternalException;
import org.apache.ws.jaxme.xs.XSComplexType;
import org.apache.ws.jaxme.xs.XSType;
import org.xml.sax.SAXException;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Iterator;

/**
 * This class represent a Schema type parsed with the
 * JAXME. The only point this code show the JAXME logic to the
 * axis is the Simple types. That should be discussed and replaces.
 * 
 * @author Srinath Perera(hemapani@opensource.lk)
 */
public class SchemaType extends Type {

	/** Field attrbuteInfo */
	protected HashMap attrbuteInfo = new HashMap();

	/** Field elementInfo */
	protected HashMap elementInfo = new HashMap();

	/** Field isArray */
	protected boolean isArray;

	/** Field jaxmetype */
	protected XSType jaxmetype;
	protected int arrayDimension = 0;
	/** Field ARRAY_TYPE */
	public static final QName ARRAY_TYPE = new QName("arrayType");
    
	public static final QName ANY_TYPE = new QName("any");

	/** Field SIMPLE_CONTENT */
	public static final QName SIMPLE_CONTENT = new QName("value");

	/**
	 * @param pqName 
	 */
	public SchemaType(QName pqName) {
		super(pqName, null);
	}



	/**
	 * @param pqName
	 * @param refType
	 * @param dims
	 */
//	public SchemaType(QName pqName, TypeEntry refType, String dims) {
//		super(pqName, refType, null, dims);
//	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		try {
			Iterator it1 = this.getAttributeNames();
			Iterator it2 = this.getElementNames();
			SchemaType reftype = (SchemaType)getRefType();
			
			String val =
				"SchemaType"
					+ this.qname
					+ "\n"
					+ "qname = "
					+ this.qname
					+ "\n"
					+ "isArray = "
					+ this.isArray
					+ "\n"
					+ "dims = "
					+ this.dims
					+ "\n"
					+ "Adims = "
					+ arrayDimension
					+ "\n"
					+ "name = "
					+ this.name
					+ "\n"
					+ "isBaseType = "
					+ this.isBaseType
					+ "\n"
					+ "isSimpleType = "
					+ this.isSimpleType
					+ "\n"
					+ "jaxmetype = "
					+ this.jaxmetype
					+ "\n"
					+ "extensionBase = "
					+ getExtentionBase()
					+ "\n"
					+ "restrictionBase = "
					+ getRestrictionBase()
					+ "\n"
					+ "isReferanced = "
					+ isReferenced()
					+ "\n"
					+ "refType = "
					+  ((reftype== null)?null:reftype.getQName())
					+ "\n"
					+ "attributes = "
					+ "(";
			while (it1.hasNext()) {
				QName qn = (QName) it1.next();
				val =
					val
						+ "("
						+ qn
						+ "="
						+ this.getAttributeTypeByName(qn).getName()
						+ ")\n";
			}
			val = val + ")\n";
			val = val + "elements = " + "(";
			while (it2.hasNext()) {
				QName qn = (QName) it2.next();
				SchemaElement info = this.getElementTypeByName(qn);
				val =  val
						+ "("
						+ qn
						+ "="
						+ "["+info.toString()+"]"
						+ "{"+info.getMaxOccurs()+"}"
						+ ")\n";
			}
			val = val + ")\n";
			return val;
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}


	public void addAttributes(QName name, SchemaType type) {
		if (!this.attrbuteInfo.containsKey(name))
			this.attrbuteInfo.put(name, type);
		else{
			throw new JAXMEInternalException("trying add existing attribute"+name);
		}			
	
	}
	public SchemaType getAttributeTypeByName(QName name) {
		Object obj = this.attrbuteInfo.get(name);
		return (obj == null) ? null : (SchemaType) obj;
	}

	/**
	 * Method getAttributeNames
	 * 
	 * @return 
	 */
	public Iterator getAttributeNames() {
		return this.attrbuteInfo.keySet().iterator();
	}

	/**
	 * Method addElement
	 * 
	 * @param elementInfo 
	 */
	public void addElement(SchemaElement elementInfo) {

		if (!this.attrbuteInfo.containsKey(elementInfo.getQName())) {
			this.elementInfo.put(elementInfo.getQName(), elementInfo);
		}else{
			throw new JAXMEInternalException("trying add existing element"+elementInfo.getQName());
		}
	}

	/**
	 * Method getElementTypeByName
	 * 
	 * @param name 
	 * @return 
	 */
	public SchemaElement getElementTypeByName(QName name) {

		Object obj = this.elementInfo.get(name);

		return (obj == null)
				? null
				: (SchemaElement) obj;
	}

	/**
	 * Method getElementNames
	 * 
	 * @return 
	 */
	public Iterator getElementNames() {
		return this.elementInfo.keySet().iterator();
	}

	/**
	 * @return 
	 */
	public boolean isArray() {
		return (this.arrayDimension > 0) || isArray;
	}

	/**
	 * @param b 
	 */
	public void setArray(boolean b) {
		isArray = b;
	}

	/**
	 * @return 
	 */
	public XSType getJaxmetype() {
		return jaxmetype;
	}

	/**
	 * @param type 
	 */
	public void setJaxmetype(XSType type) {
		jaxmetype = type;
	}

	/**
	 * @return the restricion base of this type or return null.
	 * @throws SAXException
	 */

	public QName getRestrictionBase(){
		try{	
			//TODO when the conversion completes there should not be 
			//types with jaxmetype null, yet we ignore this. This should fixed  
			if (this.jaxmetype == null)
				return null;
			if (jaxmetype.isSimple()) {
				if (jaxmetype.getSimpleType().isRestriction())
					return SymbolTable.xsQName2QName(
						jaxmetype.getSimpleType().getRestrictedType().getName());
			} else if (jaxmetype.getComplexType().isRestriction()) {
				return SymbolTable.xsQName2QName(
					jaxmetype.getComplexType().getRestrictedType().getName());
			}
			return null;
		}catch(SAXException e){
			throw new JAXMEInternalException(e);
		}	
	
	}

	/**
	 * 
	 * @return get the extension type if this is extended or null
	 * @throws SAXException
	 */

	public QName getExtentionBase(){
		QName extention = getExtention(this.jaxmetype);
		//TODO am I handling this correct
		if(SymbolTable.isInbuildType(extention)){
			//oops we can not afford to give the extenstion as value. In java you can 
			//not extend from the String or byte[] ect. So we use the deligation here 
			if(this.getElementTypeByName(SIMPLE_CONTENT) == null){
				//this.addElement(new ElementInfo());
			}	
			return null;
		}
		return 	extention;
	}

	private QName getExtention(XSType type){
		try{	
			if (type == null)
				return null;
	
			QName extendType = null;
			if (!type.isSimple()) {
				XSComplexType ctype = type.getComplexType();
				if (ctype.isExtension()) {
					extendType =
						SymbolTable.xsQName2QName(
							ctype.getExtendedType().getName());
				} else {
					if (ctype.hasSimpleContent()) {
						extendType =
							getExtention(ctype.getSimpleContent().getType());
						extendType = null;
					}
				}
			}
			return extendType;
		 }catch(SAXException e){
			 throw new JAXMEInternalException(e);
		 }	
	}

	/**
	 * @return
	 */
	public int getArrayDimension() {
		return arrayDimension;
	}	
	/**
	 * @param i
	 */
	public void setArrayDimension(int i) {
		if(i == -1 )
			i = 1;
		arrayDimension = i;
		dims ="";
		for(int j = 0;j<this.arrayDimension;j++){
			dims = dims +"[]";
		}
	}
	
	 /**
	 * Method getArrayType
	 * 
	 * @return 
	 */
	public SchemaType getArrayType() {
		SchemaElement obj = this.getElementTypeByName(ARRAY_TYPE);
		return (obj == null ? null : obj.getType());
	}
    
	public void setArrayDimension(String dims) {
			this.dims = dims;
	}

	public void setArrayType(SchemaType type) {
		this.addElement(new SchemaElement(ARRAY_TYPE,type));
		this.setArray(true);
	}	/* (non-Javadoc)
	 * @see org.apache.axis.wsdl.symbolTable.TypeEntry#getDimensions()
	 */
	public String getDimensions() {
		return dims;
	}	
	/* (non-Javadoc)
	 * @see org.apache.axis.wsdl.symbolTable.TypeEntry#getRefType()
	 */
	public TypeEntry getRefType() {
		return getArrayType();
   
	}
	/* (non-Javadoc)
	 * @see org.apache.axis.wsdl.symbolTable.SymTabEntry#setName(java.lang.String)
	 */
	public void setName(String name) {
		this.name = name;
	}	

   /**
	 * Method getAttributeInfo
	 * 
	 * @return 
	 */
	public HashMap getAttributeInfo() {
		return attrbuteInfo;
	}

	public void validate(){
		if(this.qname == null)
			throw new JAXMEInternalException("QName can not be null");
		if(isArray && (getRefType()== null))	
			throw new JAXMEInternalException("for a array type the ref can't be null");
		if(!isSimpleType()&& jaxmetype == null )
			throw new JAXMEInternalException("jaxme type can be null for simple types only");							
	}
    
	/* (non-Javadoc)
	 * @see org.apache.axis.wsdl.symbolTable.TypeEntry#isSimpleType()
	 */
	public boolean isSimpleType() {
		if(jaxmetype == null)
			return true;
		return 	jaxmetype.isSimple();		
	}    /**
	 * Method getElementInfo
	 * 
	 * @return 
	 */
	public HashMap getElementInfo() {
		return elementInfo;
	}
}
