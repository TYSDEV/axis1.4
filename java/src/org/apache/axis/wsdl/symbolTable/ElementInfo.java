package org.apache.axis.wsdl.symbolTable;

import javax.xml.namespace.QName;

/**
 *  This is basically the ElementDecl (code cut and pasted)
 */
public class ElementInfo {
    private QName name;
    private QName type;
    // The following property is set if minOccurs=0.
    // An item that is not set and has minOccurs=0 
    // should not be passed over the wire.  This
    // is slightly different than nillable=true which
    // causes nil=true to be passed over the wire.
    private int minOccurs = 0;
    private int maxOccurs = 1;

    private boolean nillable = false;

    // Indicate if the ElementDecl represents
    // an xsd:any element
    private boolean anyElement = false;

    public ElementInfo() {
    }

    public ElementInfo(QName name,QName type) {
        this.type = type;
        this.name = name;
    }

    public QName getType() {
        return type;
    }

    public void setType(QName type) {
        this.type = type;
    }

    public QName getName() {
        return name;
    }

    public void setName(QName name) {
        this.name = name;
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
        maxOccurs = i;
    }

    /**
     * @param i
     */
    public void setMinOccurs(int i) {
        minOccurs = i;
    }

}
