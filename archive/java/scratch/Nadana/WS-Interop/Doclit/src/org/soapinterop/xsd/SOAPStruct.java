/*
 * XML Type:  SOAPStruct
 * Namespace: http://soapinterop.org/xsd
 * Java type: org.soapinterop.xsd.SOAPStruct
 *
 * Automatically generated - do not modify.
 */
package org.soapinterop.xsd;


/**
 * An XML SOAPStruct(@http://soapinterop.org/xsd).
 *
 * This is a complex type.
 */
public interface SOAPStruct extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)schema.system.foo.TypeSystemHolder.typeSystem.resolveHandle("soapstruct93e7type");
    
    /**
     * Gets the "varFloat" element
     */
    float getVarFloat();
    
    /**
     * Gets (as xml) the "varFloat" element
     */
    org.apache.xmlbeans.XmlFloat xgetVarFloat();
    
    /**
     * Sets the "varFloat" element
     */
    void setVarFloat(float varFloat);
    
    /**
     * Sets (as xml) the "varFloat" element
     */
    void xsetVarFloat(org.apache.xmlbeans.XmlFloat varFloat);
    
    /**
     * Gets the "varInt" element
     */
    int getVarInt();
    
    /**
     * Gets (as xml) the "varInt" element
     */
    org.apache.xmlbeans.XmlInt xgetVarInt();
    
    /**
     * Sets the "varInt" element
     */
    void setVarInt(int varInt);
    
    /**
     * Sets (as xml) the "varInt" element
     */
    void xsetVarInt(org.apache.xmlbeans.XmlInt varInt);
    
    /**
     * Gets the "varString" element
     */
    java.lang.String getVarString();
    
    /**
     * Gets (as xml) the "varString" element
     */
    org.apache.xmlbeans.XmlString xgetVarString();
    
    /**
     * Sets the "varString" element
     */
    void setVarString(java.lang.String varString);
    
    /**
     * Sets (as xml) the "varString" element
     */
    void xsetVarString(org.apache.xmlbeans.XmlString varString);
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.soapinterop.xsd.SOAPStruct newInstance() {
          return (org.soapinterop.xsd.SOAPStruct) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.soapinterop.xsd.SOAPStruct newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.soapinterop.xsd.SOAPStruct) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.soapinterop.xsd.SOAPStruct parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.soapinterop.xsd.SOAPStruct) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.soapinterop.xsd.SOAPStruct parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.soapinterop.xsd.SOAPStruct) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.soapinterop.xsd.SOAPStruct parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.soapinterop.xsd.SOAPStruct) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.soapinterop.xsd.SOAPStruct parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.soapinterop.xsd.SOAPStruct) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.soapinterop.xsd.SOAPStruct parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.soapinterop.xsd.SOAPStruct) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.soapinterop.xsd.SOAPStruct parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.soapinterop.xsd.SOAPStruct) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.soapinterop.xsd.SOAPStruct parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.soapinterop.xsd.SOAPStruct) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.soapinterop.xsd.SOAPStruct parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.soapinterop.xsd.SOAPStruct) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.soapinterop.xsd.SOAPStruct parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.soapinterop.xsd.SOAPStruct) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.soapinterop.xsd.SOAPStruct parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.soapinterop.xsd.SOAPStruct) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.soapinterop.xsd.SOAPStruct parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.soapinterop.xsd.SOAPStruct) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.soapinterop.xsd.SOAPStruct parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.soapinterop.xsd.SOAPStruct) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.soapinterop.xsd.SOAPStruct parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.soapinterop.xsd.SOAPStruct) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.soapinterop.xsd.SOAPStruct parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.soapinterop.xsd.SOAPStruct) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        public static org.soapinterop.xsd.SOAPStruct parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.soapinterop.xsd.SOAPStruct) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        public static org.soapinterop.xsd.SOAPStruct parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.soapinterop.xsd.SOAPStruct) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
