package org.apache.axis.wsdl.symbolTable;

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
 * @author Srinath Perera(hemapani@opensource.lk)
 */
public class SchemaType extends Type {

	protected HashMap attrbuteInfo = new HashMap();
	protected HashMap elementInfo = new HashMap();
	protected boolean isArray;
	protected XSType jaxmetype;

	public static final QName ARRAY_TYPE = new QName("arrayType");
	public static final QName SIMPLE_CONTENT = new QName("simpleContent");

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
    public SchemaType(QName pqName, TypeEntry refType, String dims) {
        super(pqName, refType, null, dims);
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        try {
            Iterator it1 = this.getAttributeNames();
            Iterator it2 = this.getElementNames();
            String val =
                "SchemaType"
                    + this.jaxmetype.getName()
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
                    + "attributes = "
                    + "(";
            while (it1.hasNext()) {
                QName qn = (QName) it1.next();
                val =
                    val
                        + "("
                        + qn
                        + "="
                        + this.getAttributeTypeByName(qn)
                        + ")\n";
            }
            val = val + ")\n";
            val = val + "elements = " + "(";
            while (it2.hasNext()) {
                QName qn = (QName) it2.next();
                val =  val
                        + "("
                        + qn
                        + "="
                        + this.getElementTypeByName(qn)
                        + ")\n";
            }
            val = val + ")\n";
            return val;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }


    public void addAttributes(QName name, QName type) {
        if (!this.attrbuteInfo.containsKey(name))
            this.attrbuteInfo.put(name, type);
    }

    public QName getAttributeTypeByName(QName name) {
        Object obj = this.attrbuteInfo.get(name);
        return (obj == null) ? null : (QName) obj;
    }

    public Iterator getAttributeNames() {
        return this.attrbuteInfo.keySet().iterator();
    }

    public void addElement(ElementInfo elementInfo) {
        if (!this.attrbuteInfo.containsKey(elementInfo.getName()))
            this.elementInfo.put(elementInfo.getName(), elementInfo);
    }

    public ElementInfo getElementTypeByName(QName name) {
        Object obj = this.elementInfo.get(name);
        return (obj == null) ? null : (ElementInfo) obj;
    }

    public Iterator getElementNames() {
        return this.elementInfo.keySet().iterator();
    }

    /**
     * @return
     */
    public boolean isArray() {
        return isArray;
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

    public QName getRestrictionBase() throws SAXException {
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
    }

	/**
	 * 
	 * @return get the extension type if this is extended or null
	 * @throws SAXException
	 */

    public QName getExtentionBase() throws SAXException {
        return getExtention(this.jaxmetype);
    }

    private QName getExtention(XSType type) throws SAXException {
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
    }

    public QName getArrayType() {
        ElementInfo obj = this.getElementTypeByName(ARRAY_TYPE);
        return (obj == null ? null : obj.getName());
    }

    public HashMap getAttributeInfo() {
        return attrbuteInfo;
    }
    public HashMap getElementInfo() {
        return elementInfo;
    }
}
