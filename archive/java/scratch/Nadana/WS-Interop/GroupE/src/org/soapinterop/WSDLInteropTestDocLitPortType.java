

    package org.soapinterop;

    /*
     *  Auto generated java interface by the Axis code generator
     */

    public interface WSDLInteropTestDocLitPortType {
     
        /**
         * Auto generated method signature 
         *
         */
        public  void echoVoid() throws java.rmi.RemoteException;
        
         /**
         * Auto generated method signature
         *
         */
        public  void startechoVoid(final org.soapinterop.WSDLInteropTestDocLitPortTypeCallbackHandler callback) throws java.rmi.RemoteException;
        
        /**
         * Auto generated method signature 
         *@param param26
         */
        public  org.soapinterop.xsd.EchoStringArrayReturnDocument echoStringArray(org.soapinterop.xsd.EchoStringArrayParamDocument param26) throws java.rmi.RemoteException;
        
         /**
         * Auto generated method signature
         *@param param26
         */
        public  void startechoStringArray(org.soapinterop.xsd.EchoStringArrayParamDocument param26,final org.soapinterop.WSDLInteropTestDocLitPortTypeCallbackHandler callback) throws java.rmi.RemoteException;
        
        /**
         * Auto generated method signature 
         *@param param28
         */
        public  org.soapinterop.xsd.EchoStructReturnDocument echoStruct(org.soapinterop.xsd.EchoStructParamDocument param28) throws java.rmi.RemoteException;
        
         /**
         * Auto generated method signature
         *@param param28
         */
        public  void startechoStruct(org.soapinterop.xsd.EchoStructParamDocument param28,final org.soapinterop.WSDLInteropTestDocLitPortTypeCallbackHandler callback) throws java.rmi.RemoteException;
        
        /**
         * Auto generated method signature 
         *@param param30
         */
        public  org.soapinterop.xsd.EchoStringReturnDocument echoString(org.soapinterop.xsd.EchoStringParamDocument param30) throws java.rmi.RemoteException;
        
         /**
         * Auto generated method signature
         *@param param30
         */
        public  void startechoString(org.soapinterop.xsd.EchoStringParamDocument param30,final org.soapinterop.WSDLInteropTestDocLitPortTypeCallbackHandler callback) throws java.rmi.RemoteException;
        
    }
    