
    package org.soapinterop;

    /**
     *  Auto generated Callback class by the Axis code generator
     */

    public class SoapInteropCompound2PortTypeCallbackHandler{
    
    
    
	private Object clientData;
		
		
	/**
	* User can pass in any object that needs to be accessed once the NonBlocking 
	* Web service call is finished and appropreate method of this CallBack is called.
	* @param clientData Object mechanism by which the user can pass in user data
	* that will be avilable at the time this callback is called.
	*/
	public SoapInteropCompound2PortTypeCallbackHandler(Object clientData){
		this.clientData = clientData;
	}


	
         /**
         * auto generated Axis2 call back method for echoEmployee method
         *
         */
        public void receiveResultechoEmployee(org.apache.axis2.clientapi.AsyncResult result) {
			//Fill here with the code to handle the response
			
        }

        /**
         * auto generated Axis2 Error handler
         *
         */
        public void receiveErrorechoEmployee(java.lang.Exception e) {
			//Fill here with the code to handle the exception

        }
     

     
    }
    