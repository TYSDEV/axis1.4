
	
    package org.soapinterop;

    /**
     *  Auto generated message receiver
     */

    public class WSDLInteropTestDocLitPortTypeMessageReceiver extends org.apache.axis2.receivers.AbstractInOutSyncMessageReceiver{
    
		public void invokeBusinessLogic(org.apache.axis2.context.MessageContext msgContext, org.apache.axis2.context.MessageContext newMsgContext)
		throws org.apache.axis2.engine.AxisFault{
    
     try {

            // get the implementation class for the Web Service
            Object obj = getTheImplementationObject(msgContext);
           
            WSDLInteropTestDocLitPortTypeSkeleton skel = (WSDLInteropTestDocLitPortTypeSkeleton)obj;
            //Out Envelop
             org.apache.axis2.soap.SOAPEnvelope envelope = null;
             //Find the operation that has been set by the Dispatch phase.
            org.apache.axis2.description.OperationDescription op = msgContext.getOperationContext().getAxisOperation();
            if (op == null) {
                throw new org.apache.axis2.engine.AxisFault("Operation is not located, if this is doclit style the SOAP-ACTION should specified via the SOAP Action to use the RawXMLProvider");
            }
            
            String methodName;
            if(op.getName() != null & (methodName = op.getName().getLocalPart()) != null){
            
				
					
					
					if(methodName.equals("echoVoid")){
											
				
			org.soapinterop.xsd.EchoVoidResponseDocument param17 = null;
						
				//doc style
					param17 = skel.echoVoid((org.soapinterop.xsd.EchoVoidDocument)org.soapinterop.databinding.echoVoidDatabindingSupporter.fromOM((org.apache.axis2.om.OMElement)msgContext.getEnvelope().getBody().getFirstChild().detach(), org.soapinterop.xsd.EchoVoidDocument.class));
						
					//Create a default envelop
					envelope = getSOAPFactory().getDefaultEnvelope();
					//Create a Omelement of the result if a result exist
					
					envelope.getBody().setFirstChild(org.soapinterop.databinding.echoVoidDatabindingSupporter.toOM(param17));		
					
						
						
						
					}
			   
					
					
					if(methodName.equals("echoStringArray")){
											
				
			org.soapinterop.xsd.EchoStringArrayResponseDocument param19 = null;
						
				//doc style
					param19 = skel.echoStringArray((org.soapinterop.xsd.EchoStringArrayDocument)org.soapinterop.databinding.echoStringArrayDatabindingSupporter.fromOM((org.apache.axis2.om.OMElement)msgContext.getEnvelope().getBody().getFirstChild().detach(), org.soapinterop.xsd.EchoStringArrayDocument.class));
						
					//Create a default envelop
					envelope = getSOAPFactory().getDefaultEnvelope();
					//Create a Omelement of the result if a result exist
					
					envelope.getBody().setFirstChild(org.soapinterop.databinding.echoStringArrayDatabindingSupporter.toOM(param19));		
					
						
						
						
					}
			   
					
					
					if(methodName.equals("echoStruct")){
											
				
			org.soapinterop.xsd.EchoStructResponseDocument param21 = null;
						
				//doc style
					param21 = skel.echoStruct((org.soapinterop.xsd.EchoStructDocument)org.soapinterop.databinding.echoStructDatabindingSupporter.fromOM((org.apache.axis2.om.OMElement)msgContext.getEnvelope().getBody().getFirstChild().detach(), org.soapinterop.xsd.EchoStructDocument.class));
						
					//Create a default envelop
					envelope = getSOAPFactory().getDefaultEnvelope();
					//Create a Omelement of the result if a result exist
					
					envelope.getBody().setFirstChild(org.soapinterop.databinding.echoStructDatabindingSupporter.toOM(param21));		
					
						
						
						
					}
			   
					
					
					if(methodName.equals("echoString")){
											
				
			org.soapinterop.xsd.EchoStringResponseDocument param23 = null;
						
				//doc style
					param23 = skel.echoString((org.soapinterop.xsd.EchoStringDocument)org.soapinterop.databinding.echoStringDatabindingSupporter.fromOM((org.apache.axis2.om.OMElement)msgContext.getEnvelope().getBody().getFirstChild().detach(), org.soapinterop.xsd.EchoStringDocument.class));
						
					//Create a default envelop
					envelope = getSOAPFactory().getDefaultEnvelope();
					//Create a Omelement of the result if a result exist
					
					envelope.getBody().setFirstChild(org.soapinterop.databinding.echoStringDatabindingSupporter.toOM(param23));		
					
						
						
						
					}
			   
			   
			   newMsgContext.setEnvelope(envelope);
            }
           
            

        } catch (Exception e) {
            throw org.apache.axis2.engine.AxisFault.makeFault(e);
        }
     
		 }
	
    }
    