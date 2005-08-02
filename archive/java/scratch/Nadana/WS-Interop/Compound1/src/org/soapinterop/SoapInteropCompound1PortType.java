

    package org.soapinterop;

    /*
     *  Auto generated java interface by the Axis code generator
     */

    public interface SoapInteropCompound1PortType {
     
        /**
         * Auto generated method signature 
         *@param param12
         */
        public  org.soapinterop.xsd.ResultPersonDocument echoPerson(org.soapinterop.xsd.XPersonDocument param12) throws java.rmi.RemoteException;
        
         /**
         * Auto generated method signature
         *@param param12
         */
        public  void startechoPerson(org.soapinterop.xsd.XPersonDocument param12,final org.soapinterop.SoapInteropCompound1PortTypeCallbackHandler callback) throws java.rmi.RemoteException;
        
        /**
         * Auto generated method signature 
         *@param param14
         */
        public  org.soapinterop.xsd.ResultDocumentDocument1 echoDocument(org.soapinterop.xsd.XDocumentDocument1 param14) throws java.rmi.RemoteException;
        
         /**
         * Auto generated method signature
         *@param param14
         */
        public  void startechoDocument(org.soapinterop.xsd.XDocumentDocument1 param14,final org.soapinterop.SoapInteropCompound1PortTypeCallbackHandler callback) throws java.rmi.RemoteException;
        
    }
    