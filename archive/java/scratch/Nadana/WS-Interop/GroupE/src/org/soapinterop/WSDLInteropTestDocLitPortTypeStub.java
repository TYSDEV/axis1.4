
    package org.soapinterop;

    /*
     *  Auto generated java implementation by the Axis code generator
    */

    public class WSDLInteropTestDocLitPortTypeStub extends org.apache.axis2.clientapi.Stub implements WSDLInteropTestDocLitPortType{
        public static final String AXIS2_HOME = ".";
        protected static org.apache.axis2.description.OperationDescription[] _operations;

        static{

           //creating the Service
           _service = new org.apache.axis2.description.ServiceDescription(new javax.xml.namespace.QName("http://soapinterop.org/WSDLInteropTestDocLit","WSDLInteropTestDocLitPortType"));

           //creating the operations
           org.apache.axis2.description.OperationDescription __operation;
           _operations = new org.apache.axis2.description.OperationDescription[4];
      
          __operation = new org.apache.axis2.description.OperationDescription();
          __operation.setName(new javax.xml.namespace.QName("http://soapinterop.org/WSDLInteropTestDocLit", "echoVoid"));
          _operations[0]=__operation;
          _service.addOperation(__operation);
     
          __operation = new org.apache.axis2.description.OperationDescription();
          __operation.setName(new javax.xml.namespace.QName("http://soapinterop.org/WSDLInteropTestDocLit", "echoStringArray"));
          _operations[1]=__operation;
          _service.addOperation(__operation);
     
          __operation = new org.apache.axis2.description.OperationDescription();
          __operation.setName(new javax.xml.namespace.QName("http://soapinterop.org/WSDLInteropTestDocLit", "echoStruct"));
          _operations[2]=__operation;
          _service.addOperation(__operation);
     
          __operation = new org.apache.axis2.description.OperationDescription();
          __operation.setName(new javax.xml.namespace.QName("http://soapinterop.org/WSDLInteropTestDocLit", "echoString"));
          _operations[3]=__operation;
          _service.addOperation(__operation);
     
       }

       /**
        * Constructor
        */
        public WSDLInteropTestDocLitPortTypeStub(String axis2Home,String targetEndpoint) throws java.lang.Exception {

			this.toEPR = new org.apache.axis2.addressing.EndpointReference(org.apache.axis2.addressing.AddressingConstants.WSA_TO, targetEndpoint);
		    //creating the configuration
           _configurationContext = new org.apache.axis2.context.ConfigurationContextFactory().buildClientConfigurationContext(axis2Home);
           _configurationContext.getAxisConfiguration().addService(_service);
           _serviceContext = _configurationContext.createServiceContext(_service.getName());

	    }

        /**
        * Default Constructor
        */
        public WSDLInteropTestDocLitPortTypeStub() throws java.lang.Exception {
		    //this(AXIS2_HOME,"http://mssoapinterop.org/stkv3/wsdl/interopTestDocLit.wsdl" );
            this(AXIS2_HOME,"http://localhost:8000///stkv3/wsdl/interopTestDocLit.wsdl" );


	    }



     
        /**
         * Auto generated method signature
         * @see org.soapinterop.WSDLInteropTestDocLitPortType#echoVoid
         *
         */
        public  void echoVoid() throws java.rmi.RemoteException{

		    org.apache.axis2.clientapi.Call _call = new org.apache.axis2.clientapi.Call(_serviceContext);
 		    org.apache.axis2.context.MessageContext _messageContext = getMessageContext();
            _call.setTo(this.toEPR);
            _call.setSoapAction("http://soapinterop.org/");
            org.apache.axis2.soap.SOAPEnvelope env = null;
            env = createEnvelope();
            
                       //Style is Doc. No input parameters
                       setValueDoc(env,null);
                      
             _messageContext.setEnvelope(env);
             
               _call.invokeBlocking(_operations[0], _messageContext);
               return;
              

            
        }
        
         /**
         * Auto generated method signature
         * @see org.soapinterop.WSDLInteropTestDocLitPortType#startechoVoid
         *
         */
        public  void startechoVoid(final org.soapinterop.WSDLInteropTestDocLitPortTypeCallbackHandler callback) throws java.rmi.RemoteException{
             org.apache.axis2.clientapi.Call _call = new org.apache.axis2.clientapi.Call(_serviceContext);
 		     org.apache.axis2.context.MessageContext _messageContext = getMessageContext();
             _call.setTo(this.toEPR);
            _call.setSoapAction("http://soapinterop.org/");
             org.apache.axis2.soap.SOAPEnvelope env = createEnvelope();
             
                       //Style is Doc. No input parameters
                       setValueDoc(env,null);
                      
             _messageContext.setEnvelope(env);
		      _call.invokeNonBlocking(_operations[0], _messageContext, new org.apache.axis2.clientapi.Callback(){
                   public void onComplete(org.apache.axis2.clientapi.AsyncResult result){
                         callback.receiveResultechoVoid(result);
                   }
                   public void reportError(java.lang.Exception e){
                         callback.receiveErrorechoVoid(e);
                   }

              }
            );

            
        }
      
        /**
         * Auto generated method signature
         * @see org.soapinterop.WSDLInteropTestDocLitPortType#echoStringArray
         *@param param34
         */
        public  org.soapinterop.xsd.EchoStringArrayReturnDocument echoStringArray(org.soapinterop.xsd.EchoStringArrayParamDocument param34) throws java.rmi.RemoteException{

		    org.apache.axis2.clientapi.Call _call = new org.apache.axis2.clientapi.Call(_serviceContext);
 		    org.apache.axis2.context.MessageContext _messageContext = getMessageContext();
            _call.setTo(this.toEPR);
            _call.setSoapAction("http://soapinterop.org/");
            org.apache.axis2.soap.SOAPEnvelope env = null;
            env = createEnvelope();
            
                       //Style is Doc
                       setValueDoc(env,org.soapinterop.databinding.echoStringArrayDatabindingSupporter.toOM(param34));
                      
             _messageContext.setEnvelope(env);
             
             org.apache.axis2.context.MessageContext  _returnMessageContext = _call.invokeBlocking(_operations[1], _messageContext);
             org.apache.axis2.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();
             java.lang.Object object = org.soapinterop.databinding.echoStringArrayDatabindingSupporter.fromOM(getElement(_returnEnv,"doc"),org.soapinterop.xsd.EchoStringArrayReturnDocument.class);
             return (org.soapinterop.xsd.EchoStringArrayReturnDocument)object;
                 

            
        }
        
         /**
         * Auto generated method signature
         * @see org.soapinterop.WSDLInteropTestDocLitPortType#startechoStringArray
         *@param param34
         */
        public  void startechoStringArray(org.soapinterop.xsd.EchoStringArrayParamDocument param34,final org.soapinterop.WSDLInteropTestDocLitPortTypeCallbackHandler callback) throws java.rmi.RemoteException{
             org.apache.axis2.clientapi.Call _call = new org.apache.axis2.clientapi.Call(_serviceContext);
 		     org.apache.axis2.context.MessageContext _messageContext = getMessageContext();
             _call.setTo(this.toEPR);
            _call.setSoapAction("http://soapinterop.org/");
             org.apache.axis2.soap.SOAPEnvelope env = createEnvelope();
             
                         //Style is Doc
                       setValueDoc(env,org.soapinterop.databinding.echoStringArrayDatabindingSupporter.toOM(param34));
                      
             _messageContext.setEnvelope(env);
		      _call.invokeNonBlocking(_operations[1], _messageContext, new org.apache.axis2.clientapi.Callback(){
                   public void onComplete(org.apache.axis2.clientapi.AsyncResult result){
                         callback.receiveResultechoStringArray(result);
                   }
                   public void reportError(java.lang.Exception e){
                         callback.receiveErrorechoStringArray(e);
                   }

              }
            );

            
        }
      
        /**
         * Auto generated method signature
         * @see org.soapinterop.WSDLInteropTestDocLitPortType#echoStruct
         *@param param36
         */
        public  org.soapinterop.xsd.EchoStructReturnDocument echoStruct(org.soapinterop.xsd.EchoStructParamDocument param36) throws java.rmi.RemoteException{

		    org.apache.axis2.clientapi.Call _call = new org.apache.axis2.clientapi.Call(_serviceContext);
 		    org.apache.axis2.context.MessageContext _messageContext = getMessageContext();
            _call.setTo(this.toEPR);
            _call.setSoapAction("http://soapinterop.org/");
            org.apache.axis2.soap.SOAPEnvelope env = null;
            env = createEnvelope();
            
                       //Style is Doc
                       setValueDoc(env,org.soapinterop.databinding.echoStructDatabindingSupporter.toOM(param36));
                      
             _messageContext.setEnvelope(env);
             
             org.apache.axis2.context.MessageContext  _returnMessageContext = _call.invokeBlocking(_operations[2], _messageContext);
             org.apache.axis2.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();
             java.lang.Object object = org.soapinterop.databinding.echoStructDatabindingSupporter.fromOM(getElement(_returnEnv,"doc"),org.soapinterop.xsd.EchoStructReturnDocument.class);
             return (org.soapinterop.xsd.EchoStructReturnDocument)object;
                 

            
        }
        
         /**
         * Auto generated method signature
         * @see org.soapinterop.WSDLInteropTestDocLitPortType#startechoStruct
         *@param param36
         */
        public  void startechoStruct(org.soapinterop.xsd.EchoStructParamDocument param36,final org.soapinterop.WSDLInteropTestDocLitPortTypeCallbackHandler callback) throws java.rmi.RemoteException{
             org.apache.axis2.clientapi.Call _call = new org.apache.axis2.clientapi.Call(_serviceContext);
 		     org.apache.axis2.context.MessageContext _messageContext = getMessageContext();
             _call.setTo(this.toEPR);
            _call.setSoapAction("http://soapinterop.org/");
             org.apache.axis2.soap.SOAPEnvelope env = createEnvelope();
             
                         //Style is Doc
                       setValueDoc(env,org.soapinterop.databinding.echoStructDatabindingSupporter.toOM(param36));
                      
             _messageContext.setEnvelope(env);
		      _call.invokeNonBlocking(_operations[2], _messageContext, new org.apache.axis2.clientapi.Callback(){
                   public void onComplete(org.apache.axis2.clientapi.AsyncResult result){
                         callback.receiveResultechoStruct(result);
                   }
                   public void reportError(java.lang.Exception e){
                         callback.receiveErrorechoStruct(e);
                   }

              }
            );

            
        }
      
        /**
         * Auto generated method signature
         * @see org.soapinterop.WSDLInteropTestDocLitPortType#echoString
         *@param param38
         */
        public  org.soapinterop.xsd.EchoStringReturnDocument echoString(org.soapinterop.xsd.EchoStringParamDocument param38) throws java.rmi.RemoteException{

		    org.apache.axis2.clientapi.Call _call = new org.apache.axis2.clientapi.Call(_serviceContext);
 		    org.apache.axis2.context.MessageContext _messageContext = getMessageContext();
            _call.setTo(this.toEPR);
            _call.setSoapAction("http://soapinterop.org/");
            org.apache.axis2.soap.SOAPEnvelope env = null;
            env = createEnvelope();
            
                       //Style is Doc
                       setValueDoc(env,org.soapinterop.databinding.echoStringDatabindingSupporter.toOM(param38));
                      
             _messageContext.setEnvelope(env);
             
             org.apache.axis2.context.MessageContext  _returnMessageContext = _call.invokeBlocking(_operations[3], _messageContext);
             org.apache.axis2.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();
             java.lang.Object object = org.soapinterop.databinding.echoStringDatabindingSupporter.fromOM(getElement(_returnEnv,"doc"),org.soapinterop.xsd.EchoStringReturnDocument.class);
             return (org.soapinterop.xsd.EchoStringReturnDocument)object;
                 

            
        }
        
         /**
         * Auto generated method signature
         * @see org.soapinterop.WSDLInteropTestDocLitPortType#startechoString
         *@param param38
         */
        public  void startechoString(org.soapinterop.xsd.EchoStringParamDocument param38,final org.soapinterop.WSDLInteropTestDocLitPortTypeCallbackHandler callback) throws java.rmi.RemoteException{
             org.apache.axis2.clientapi.Call _call = new org.apache.axis2.clientapi.Call(_serviceContext);
 		     org.apache.axis2.context.MessageContext _messageContext = getMessageContext();
             _call.setTo(this.toEPR);
            _call.setSoapAction("http://soapinterop.org/");
             org.apache.axis2.soap.SOAPEnvelope env = createEnvelope();
             
                         //Style is Doc
                       setValueDoc(env,org.soapinterop.databinding.echoStringDatabindingSupporter.toOM(param38));
                      
             _messageContext.setEnvelope(env);
		      _call.invokeNonBlocking(_operations[3], _messageContext, new org.apache.axis2.clientapi.Callback(){
                   public void onComplete(org.apache.axis2.clientapi.AsyncResult result){
                         callback.receiveResultechoString(result);
                   }
                   public void reportError(java.lang.Exception e){
                         callback.receiveErrorechoString(e);
                   }

              }
            );

            
        }
      
    }
    