/*
 * XML Type:  ArrayOfstring_literal
 * Namespace: http://soapinterop.org/xsd
 * Java type: org.soapinterop.xsd.ArrayOfstringLiteral
 *
 * Automatically generated - do not modify.
 */
package org.soapinterop.xsd;


/**
 * An XML ArrayOfstring_literal(@http://soapinterop.org/xsd).
 *
 * This is a complex type.
 */
public interface ArrayOfstringLiteral extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)schema.system.foo.TypeSystemHolder.typeSystem.resolveHandle("arrayofstringliterala948type");
    
    /**
     * Gets array of all "string" elements
     */
    java.lang.String[] getStringArray();
    
    /**
     * Gets ith "string" element
     */
    java.lang.String getStringArray(int i);
    
    /**
     * Gets (as xml) array of all "string" elements
     */
    org.apache.xmlbeans.XmlString[] xgetStringArray();
    
    /**
     * Gets (as xml) ith "string" element
     */
    org.apache.xmlbeans.XmlString xgetStringArray(int i);
    
    /**
     * Returns number of "string" element
     */
    int sizeOfStringArray();
    
    /**
     * Sets array of all "string" element
     */
    void setStringArray(java.lang.String[] stringArray);
    
    /**
     * Sets ith "string" element
     */
    void setStringArray(int i, java.lang.String string);
    
    /**
     * Sets (as xml) array of all "string" element
     */
    void xsetStringArray(org.apache.xmlbeans.XmlString[]stringArray);
    
    /**
     * Sets (as xml) ith "string" element
     */
    void xsetStringArray(int i, org.apache.xmlbeans.XmlString string);
    
    /**
     * Inserts the value as the ith "string" element
     */
    void insertString(int i, java.lang.String string);
    
    /**
     * Appends the value as the last "string" element
     */
    void addString(java.lang.String string);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "string" element
     */
    org.apache.xmlbeans.XmlString insertNewString(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "string" element
     */
    org.apache.xmlbeans.XmlString addNewString();
    
    /**
     * Removes the ith "string" element
     */
    void removeString(int i);
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.soapinterop.xsd.ArrayOfstringLiteral newInstance() {
          return (org.soapinterop.xsd.ArrayOfstringLiteral) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.soapinterop.xsd.ArrayOfstringLiteral newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.soapinterop.xsd.ArrayOfstringLiteral) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.soapinterop.xsd.ArrayOfstringLiteral parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.soapinterop.xsd.ArrayOfstringLiteral) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.soapinterop.xsd.ArrayOfstringLiteral parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.soapinterop.xsd.ArrayOfstringLiteral) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.soapinterop.xsd.ArrayOfstringLiteral parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.soapinterop.xsd.ArrayOfstringLiteral) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.soapinterop.xsd.ArrayOfstringLiteral parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.soapinterop.xsd.ArrayOfstringLiteral) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.soapinterop.xsd.ArrayOfstringLiteral parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.soapinterop.xsd.ArrayOfstringLiteral) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.soapinterop.xsd.ArrayOfstringLiteral parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.soapinterop.xsd.ArrayOfstringLiteral) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.soapinterop.xsd.ArrayOfstringLiteral parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.soapinterop.xsd.ArrayOfstringLiteral) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.soapinterop.xsd.ArrayOfstringLiteral parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.soapinterop.xsd.ArrayOfstringLiteral) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.soapinterop.xsd.ArrayOfstringLiteral parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.soapinterop.xsd.ArrayOfstringLiteral) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.soapinterop.xsd.ArrayOfstringLiteral parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.soapinterop.xsd.ArrayOfstringLiteral) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.soapinterop.xsd.ArrayOfstringLiteral parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.soapinterop.xsd.ArrayOfstringLiteral) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.soapinterop.xsd.ArrayOfstringLiteral parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.soapinterop.xsd.ArrayOfstringLiteral) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.soapinterop.xsd.ArrayOfstringLiteral parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.soapinterop.xsd.ArrayOfstringLiteral) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.soapinterop.xsd.ArrayOfstringLiteral parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.soapinterop.xsd.ArrayOfstringLiteral) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        public static org.soapinterop.xsd.ArrayOfstringLiteral parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.soapinterop.xsd.ArrayOfstringLiteral) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        public static org.soapinterop.xsd.ArrayOfstringLiteral parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.soapinterop.xsd.ArrayOfstringLiteral) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
