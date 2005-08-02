
	
    package org.soapinterop;

    /**
     *  Auto generated message receiver
     */

    public class SoapInteropCompound1PortTypeMessageReceiver extends org.apache.axis2.receivers.AbstractInOutSyncMessageReceiver{
    
		public void invokeBusinessLogic(org.apache.axis2.context.MessageContext msgContext, org.apache.axis2.context.MessageContext newMsgContext)
		throws org.apache.axis2.engine.AxisFault{
    
     try {

            // get the implementation class for the Web Service
            Object obj = getTheImplementationObject(msgContext);
           
            SoapInteropCompound1PortTypeSkeleton skel = (SoapInteropCompound1PortTypeSkeleton)obj;
            //Out Envelop
             org.apache.axis2.soap.SOAPEnvelope envelope = null;
             //Find the operation that has been set by the Dispatch phase.
            org.apache.axis2.description.OperationDescription op = msgContext.getOperationContext().getAxisOperation();
            if (op == null) {
                throw new org.apache.axis2.engine.AxisFault("Operation is not located, if this is doclit style the SOAP-ACTION should specified via the SOAP Action to use the RawXMLProvider");
            }
            
            String methodName;
            if(op.getName() != null & (methodName = op.getName().getLocalPart()) != null){
            
				
					
					
					if(methodName.equals("echoPerson")){
											
				
			org.soapinterop.xsd.ResultPersonDocument param9 = null;
						
				//doc style
					param9 = skel.echoPerson((org.soapinterop.xsd.XPersonDocument)org.soapinterop.databinding.echoPersonDatabindingSupporter.fromOM((org.apache.axis2.om.OMElement)msgContext.getEnvelope().getBody().getFirstChild().detach(), org.soapinterop.xsd.XPersonDocument.class));
						
					//Create a default envelop
					envelope = getSOAPFactory().getDefaultEnvelope();
					//Create a Omelement of the result if a result exist
					
					envelope.getBody().setFirstChild(org.soapinterop.databinding.echoPersonDatabindingSupporter.toOM(param9));		
					
						
						
						
					}
			   
					
					
					if(methodName.equals("echoDocument")){
											
				
			org.soapinterop.xsd.ResultDocumentDocument1 param11 = null;
						
				//doc style
					param11 = skel.echoDocument((org.soapinterop.xsd.XDocumentDocument1)org.soapinterop.databinding.echoDocumentDatabindingSupporter.fromOM((org.apache.axis2.om.OMElement)msgContext.getEnvelope().getBody().getFirstChild().detach(), org.soapinterop.xsd.XDocumentDocument1.class));
						
					//Create a default envelop
					envelope = getSOAPFactory().getDefaultEnvelope();
					//Create a Omelement of the result if a result exist
					
					envelope.getBody().setFirstChild(org.soapinterop.databinding.echoDocumentDatabindingSupporter.toOM(param11));		
					
						
						
						
					}
			   
			   
			   newMsgContext.setEnvelope(envelope);
            }
           
            

        } catch (Exception e) {
            throw org.apache.axis2.engine.AxisFault.makeFault(e);
        }
     
		 }
	
    }
    