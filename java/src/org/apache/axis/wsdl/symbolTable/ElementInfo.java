package org.apache.axis.wsdl.symbolTable;

import javax.xml.namespace.QName;

import org.apache.axis.wsdl.jaxme.JAXMEInternalException;

/**
 * This is basically the ElementDecl (code cut and pasted)
 */
public class ElementInfo {

	/** Field name */
	private QName name;

	private SchemaType type;

	// The following property is set if minOccurs=0.
	// An item that is not set and has minOccurs=0
	// should not be passed over the wire.  This
	// is slightly different than nillable=true which
	// causes nil=true to be passed over the wire.

	/** Field minOccurs */
	private int minOccurs = 0;

	/** Field maxOccurs */
	private int maxOccurs = 1;

	/** Field nillable */
	private boolean nillable = false;

	// Indicate if the ElementDecl represents
	// an xsd:any element

	/** Field anyElement */
	private boolean anyElement = false;

	public ElementInfo(QName name,SchemaType type) {
		this.type = type;
		this.name = name;
		if(this.name == null)
			throw new JAXMEInternalException("name of SchemaType can not be null");
		if(this.type == null)	 
			throw new JAXMEInternalException("type of SchemaType can not be null");
	}



	public SchemaType getType() {
		return type;
	}

	public void setType(SchemaType type) {
		this.type = type;
	}

	/**
	 * Method getName
	 * 
	 * @return 
	 */
	public QName getName() {
		return name;
	}

	/**
	 * Method setName
	 * 
	 * @param name 
	 */
	public void setName(QName name) {
		this.name = name;
	}

	/**
	 * Method getMinOccursIs0
	 * 
	 * @return 
	 */
	public boolean getMinOccursIs0() {
		return (minOccurs == 0);
	}

	/**
	 * Method setNillable
	 * 
	 * @param nillable 
	 */
	public void setNillable(boolean nillable) {
		this.nillable = nillable;
	}

	/**
	 * Method getNillable
	 * 
	 * @return 
	 */
	public boolean getNillable() {
		return nillable;
	}

	/**
	 * Method getAnyElement
	 * 
	 * @return 
	 */
	public boolean getAnyElement() {
		return anyElement;
	}

	/**
	 * Method setAnyElement
	 * 
	 * @param anyElement 
	 */
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
}  