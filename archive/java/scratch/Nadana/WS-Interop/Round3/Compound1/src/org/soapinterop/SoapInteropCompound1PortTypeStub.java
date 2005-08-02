
    package org.soapinterop;

    /*
     *  Auto generated java implementation by the Axis code generator
    */

    public class SoapInteropCompound1PortTypeStub extends org.apache.axis2.clientapi.Stub implements SoapInteropCompound1PortType{
        public static final String AXIS2_HOME = ".";
        protected static org.apache.axis2.description.OperationDescription[] _operations;

        static{

           //creating the Service
           _service = new org.apache.axis2.description.ServiceDescription(new javax.xml.namespace.QName("http://soapinterop.org/","SoapInteropCompound1PortType"));

           //creating the operations
           org.apache.axis2.description.OperationDescription __operation;
           _operations = new org.apache.axis2.description.OperationDescription[2];
      
          __operation = new org.apache.axis2.description.OperationDescription();
          __operation.setName(new javax.xml.namespace.QName("http://soapinterop.org/", "echoPerson"));
          _operations[0]=__operation;
          _service.addOperation(__operation);
     
          __operation = new org.apache.axis2.description.OperationDescription();
          __operation.setName(new javax.xml.namespace.QName("http://soapinterop.org/", "echoDocument"));
          _operations[1]=__operation;
          _service.addOperation(__operation);
     
       }

       /**
        * Constructor
        */
        public SoapInteropCompound1PortTypeStub(String axis2Home,String targetEndpoint) throws java.lang.Exception {

			this.toEPR = new org.apache.axis2.addressing.EndpointReference(org.apache.axis2.addressing.AddressingConstants.WSA_TO, targetEndpoint);
		    //creating the configuration
           _configurationContext = new org.apache.axis2.context.ConfigurationContextFactory().buildClientConfigurationContext(axis2Home);
           _configurationContext.getAxisConfiguration().addService(_service);
           _serviceContext = _configurationContext.createServiceContext(_service.getName());

	    }

        /**
        * Default Constructor
        */
        public SoapInteropCompound1PortTypeStub() throws java.lang.Exception {
		    //this(AXIS2_HOME,"http://mssoapinterop.org/stkv3/wsdl/Compound1.wsdl" );
            this(AXIS2_HOME,"http://localhost:8081/stkv3/wsdl/Compound1.wsdl" );
	    }



     
        /**
         * Auto generated method signature
         * @see org.soapinterop.SoapInteropCompound1PortType#echoPerson
         *@param param16
         */
        public  org.soapinterop.xsd.ResultPersonDocument echoPerson(org.soapinterop.xsd.XPersonDocument param16) throws java.rmi.RemoteException{

		    org.apache.axis2.clientapi.Call _call = new org.apache.axis2.clientapi.Call(_serviceContext);
 		    org.apache.axis2.context.MessageContext _messageContext = getMessageContext();
            _call.setTo(this.toEPR);
            _call.setSoapAction("http://soapinterop/echoPerson");
            org.apache.axis2.soap.SOAPEnvelope env = null;
            env = createEnvelope();
            
                       //Style is Doc
                       setValueDoc(env,org.soapinterop.databinding.echoPersonDatabindingSupporter.toOM(param16));
                      
             _messageContext.setEnvelope(env);
             
             org.apache.axis2.context.MessageContext  _returnMessageContext = _call.invokeBlocking(_operations[0], _messageContext);
             org.apache.axis2.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();
             java.lang.Object object = org.soapinterop.databinding.echoPersonDatabindingSupporter.fromOM(getElement(_returnEnv,"doc"),org.soapinterop.xsd.ResultPersonDocument.class);
             return (org.soapinterop.xsd.ResultPersonDocument)object;
                 

            
        }
        
         /**
         * Auto generated method signature
         * @see org.soapinterop.SoapInteropCompound1PortType#startechoPerson
         *@param param16
         */
        public  void startechoPerson(org.soapinterop.xsd.XPersonDocument param16,final org.soapinterop.SoapInteropCompound1PortTypeCallbackHandler callback) throws java.rmi.RemoteException{
             org.apache.axis2.clientapi.Call _call = new org.apache.axis2.clientapi.Call(_serviceContext);
 		     org.apache.axis2.context.MessageContext _messageContext = getMessageContext();
             _call.setTo(this.toEPR);
            _call.setSoapAction("http://soapinterop/echoPerson");
             org.apache.axis2.soap.SOAPEnvelope env = createEnvelope();
             
                         //Style is Doc
                       setValueDoc(env,org.soapinterop.databinding.echoPersonDatabindingSupporter.toOM(param16));
                      
             _messageContext.setEnvelope(env);
		      _call.invokeNonBlocking(_operations[0], _messageContext, new org.apache.axis2.clientapi.Callback(){
                   public void onComplete(org.apache.axis2.clientapi.AsyncResult result){
                         callback.receiveResultechoPerson(result);
                   }
                   public void reportError(java.lang.Exception e){
                         callback.receiveErrorechoPerson(e);
                   }

              }
            );

            
        }
      
        /**
         * Auto generated method signature
         * @see org.soapinterop.SoapInteropCompound1PortType#echoDocument
         *@param param18
         */
        public  org.soapinterop.xsd.ResultDocumentDocument1 echoDocument(org.soapinterop.xsd.XDocumentDocument1 param18) throws java.rmi.RemoteException{

		    org.apache.axis2.clientapi.Call _call = new org.apache.axis2.clientapi.Call(_serviceContext);
 		    org.apache.axis2.context.MessageContext _messageContext = getMessageContext();
            _call.setTo(this.toEPR);
            _call.setSoapAction("http://soapinterop/echoDocument");
            org.apache.axis2.soap.SOAPEnvelope env = null;
            env = createEnvelope();
            
                       //Style is Doc
                       setValueDoc(env,org.soapinterop.databinding.echoDocumentDatabindingSupporter.toOM(param18));
                      
             _messageContext.setEnvelope(env);
             
             org.apache.axis2.context.MessageContext  _returnMessageContext = _call.invokeBlocking(_operations[1], _messageContext);
             org.apache.axis2.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();
             java.lang.Object object = org.soapinterop.databinding.echoDocumentDatabindingSupporter.fromOM(getElement(_returnEnv,"doc"),org.soapinterop.xsd.ResultDocumentDocument1.class);
             return (org.soapinterop.xsd.ResultDocumentDocument1)object;
                 

            
        }
        
         /**
         * Auto generated method signature
         * @see org.soapinterop.SoapInteropCompound1PortType#startechoDocument
         *@param param18
         */
        public  void startechoDocument(org.soapinterop.xsd.XDocumentDocument1 param18,final org.soapinterop.SoapInteropCompound1PortTypeCallbackHandler callback) throws java.rmi.RemoteException{
             org.apache.axis2.clientapi.Call _call = new org.apache.axis2.clientapi.Call(_serviceContext);
 		     org.apache.axis2.context.MessageContext _messageContext = getMessageContext();
             _call.setTo(this.toEPR);
            _call.setSoapAction("http://soapinterop/echoDocument");
             org.apache.axis2.soap.SOAPEnvelope env = createEnvelope();
             
                         //Style is Doc
                       setValueDoc(env,org.soapinterop.databinding.echoDocumentDatabindingSupporter.toOM(param18));
                      
             _messageContext.setEnvelope(env);
		      _call.invokeNonBlocking(_operations[1], _messageContext, new org.apache.axis2.clientapi.Callback(){
                   public void onComplete(org.apache.axis2.clientapi.AsyncResult result){
                         callback.receiveResultechoDocument(result);
                   }
                   public void reportError(java.lang.Exception e){
                         callback.receiveErrorechoDocument(e);
                   }

              }
            );

            
        }
      
    }
    