
    package org.soapinterop;

    /*
     *  Auto generated java implementation by the Axis code generator
    */

    public class SoapInteropCompound2PortTypeStub extends org.apache.axis2.clientapi.Stub implements SoapInteropCompound2PortType{
        public static final String AXIS2_HOME = ".";
        protected static org.apache.axis2.description.OperationDescription[] _operations;

        static{

           //creating the Service
           _service = new org.apache.axis2.description.ServiceDescription(new javax.xml.namespace.QName("http://soapinterop.org/","SoapInteropCompound2PortType"));

           //creating the operations
           org.apache.axis2.description.OperationDescription __operation;
           _operations = new org.apache.axis2.description.OperationDescription[1];
      
          __operation = new org.apache.axis2.description.OperationDescription();
          __operation.setName(new javax.xml.namespace.QName("http://soapinterop.org/", "echoEmployee"));
          _operations[0]=__operation;
          _service.addOperation(__operation);
     
       }

       /**
        * Constructor
        */
        public SoapInteropCompound2PortTypeStub(String axis2Home,String targetEndpoint) throws java.lang.Exception {

			this.toEPR = new org.apache.axis2.addressing.EndpointReference(org.apache.axis2.addressing.AddressingConstants.WSA_TO, targetEndpoint);
		    //creating the configuration
           _configurationContext = new org.apache.axis2.context.ConfigurationContextFactory().buildClientConfigurationContext(axis2Home);
           _configurationContext.getAxisConfiguration().addService(_service);
           _serviceContext = _configurationContext.createServiceContext(_service.getName());

	    }

        /**
        * Default Constructor
        */
        public SoapInteropCompound2PortTypeStub() throws java.lang.Exception {
		    //this(AXIS2_HOME,"http://soapinterop.java.sun.com:80/round3/groupd/compound2" );
             this(AXIS2_HOME,"http://localhost:8000/round3/groupd/compound2" );
	    }



     
        /**
         * Auto generated method signature
         * @see org.soapinterop.SoapInteropCompound2PortType#echoEmployee
         *@param param8
         */
        public  org.soapinterop.employee.ResultEmployeeDocument echoEmployee(org.soapinterop.employee.XEmployeeDocument param8) throws java.rmi.RemoteException{

		    org.apache.axis2.clientapi.Call _call = new org.apache.axis2.clientapi.Call(_serviceContext);
 		    org.apache.axis2.context.MessageContext _messageContext = getMessageContext();
            _call.setTo(this.toEPR);
            _call.setSoapAction("#echoEmployee");
            org.apache.axis2.soap.SOAPEnvelope env = null;
            env = createEnvelope();
            
                       //Style is Doc
                       setValueDoc(env,org.soapinterop.databinding.echoEmployeeDatabindingSupporter.toOM(param8));
                      
             _messageContext.setEnvelope(env);
             
             org.apache.axis2.context.MessageContext  _returnMessageContext = _call.invokeBlocking(_operations[0], _messageContext);
             org.apache.axis2.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();
             java.lang.Object object = org.soapinterop.databinding.echoEmployeeDatabindingSupporter.fromOM(getElement(_returnEnv,"doc"),org.soapinterop.employee.ResultEmployeeDocument.class);
             return (org.soapinterop.employee.ResultEmployeeDocument)object;
                 

            
        }
        
         /**
         * Auto generated method signature
         * @see org.soapinterop.SoapInteropCompound2PortType#startechoEmployee
         *@param param8
         */
        public  void startechoEmployee(org.soapinterop.employee.XEmployeeDocument param8,final org.soapinterop.SoapInteropCompound2PortTypeCallbackHandler callback) throws java.rmi.RemoteException{
             org.apache.axis2.clientapi.Call _call = new org.apache.axis2.clientapi.Call(_serviceContext);
 		     org.apache.axis2.context.MessageContext _messageContext = getMessageContext();
             _call.setTo(this.toEPR);
            _call.setSoapAction("#echoEmployee");
             org.apache.axis2.soap.SOAPEnvelope env = createEnvelope();
             
                         //Style is Doc
                       setValueDoc(env,org.soapinterop.databinding.echoEmployeeDatabindingSupporter.toOM(param8));
                      
             _messageContext.setEnvelope(env);
		      _call.invokeNonBlocking(_operations[0], _messageContext, new org.apache.axis2.clientapi.Callback(){
                   public void onComplete(org.apache.axis2.clientapi.AsyncResult result){
                         callback.receiveResultechoEmployee(result);
                   }
                   public void reportError(java.lang.Exception e){
                         callback.receiveErrorechoEmployee(e);
                   }

              }
            );

            
        }
      
    }
    