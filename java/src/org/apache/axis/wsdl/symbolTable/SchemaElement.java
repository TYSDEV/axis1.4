package org.apache.axis.wsdl.symbolTable;

import javax.xml.namespace.QName;

import org.apache.axis.wsdl.jaxme.JAXMEInternalException;

/**
 * @author hemapani
 */
public class SchemaElement extends TypeEntry {

	/**
	 * @param pqName
	 * @param refType
	 * @param pNode
	 * @param dims
	 */
	public SchemaElement(
		QName pqName,
		SchemaType stype) {
		super(pqName);
		this.type = stype;
		if(this.qname == null)
			throw new JAXMEInternalException("name of SchemaType can not be null");
		if(this.type == null)	 
			throw new JAXMEInternalException("type of SchemaType can not be null");

	}


	/**
	 * @param pqName
	 */
	public SchemaElement(QName pqName) {
		super(pqName);
		// TODO Auto-generated constructor stub
	}

	 private SchemaType type;
	 // The following property is set if minOccurs=0.
	 // An item that is not set and has minOccurs=0 
	 // should not be passed over the wire.  This
	 // is slightly different than nillable=true which
	 // causes nil=true to be passed over the wire.
	 private int minOccurs = 1;
	 private int maxOccurs = 1;

	 private boolean nillable = false;

	 // Indicate if the ElementDecl represents
	 // an xsd:any element
	 private boolean anyElement = false;


	 public SchemaType getType() {
		 return type;
	 }

	 public void setType(SchemaType type) {
		 this.type = type;
	 }


	 public void setName(QName name) {
		 this.qname = name;
	 }

	 public boolean getMinOccursIs0() {
		 return (minOccurs == 0);
	 }

	 public void setNillable(boolean nillable) {
		 this.nillable = nillable;
	 }

	 public boolean getNillable() {
		 return nillable;
	 }

	 public boolean getAnyElement() {
		 return anyElement;
	 }

	 public void setAnyElement(boolean anyElement) {
		 this.anyElement = anyElement;
	 }

	 /**
	  * @return
	  */
	 public int getMaxOccurs() {
		 return maxOccurs;
	 }

	 /**
	  * @return
	  */
	 public int getMinOccurs() {
		 return minOccurs;
	 }

	 /**
	  * @param i
	  */
	 public void setMaxOccurs(int i) {
		 if(i < 0)
			i = 99999;
		 maxOccurs = i;
	 }

	 /**
	  * @param i
	  */
	 public void setMinOccurs(int i) {
		 minOccurs = i;
	 }

	public String toString() {
		 return this.name +","+ this.type.getQName(); 
	}
	/* (non-Javadoc)
	 * @see org.apache.axis.wsdl.symbolTable.TypeEntry#getRefType()
	 */
	public TypeEntry getRefType() {
		return this.getType();
	}

	/* (non-Javadoc)
	 * @see org.apache.axis.wsdl.symbolTable.TypeEntry#setRefType(org.apache.axis.wsdl.symbolTable.TypeEntry)
	 */
	public void setRefType(SchemaType refType) {
		this.setType(refType);
	}
	
	public boolean isArrayElement(){
		return maxOccurs >1 || (maxOccurs == -1);
	}
	
	public String getName(){
		String name = this.getType().getName();
		if(isArrayElement())
			name = name +"[]";
		if(name != null && name.startsWith(".")){
			name = name.substring(1);
		}
		return name;			
	}

	/* (non-Javadoc)
	 * @see org.apache.axis.wsdl.symbolTable.TypeEntry#getBaseType()
	 */
	public String getBaseType() {
		  String baseType = super.getBaseType();
		  if(baseType == null)
		baseType = type.getBaseType();
		return baseType;
	}

}
