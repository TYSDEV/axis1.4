/*

 * Copyright 2001-2004 The Apache Software Foundation.

 * 

 * Licensed under the Apache License, Version 2.0 (the "License");

 * you may not use this file except in compliance with the License.

 * You may obtain a copy of the License at

 * 

 *      http://www.apache.org/licenses/LICENSE-2.0

 * 

 * Unless required by applicable law or agreed to in writing, software

 * distributed under the License is distributed on an "AS IS" BASIS,

 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.

 * See the License for the specific language governing permissions and

 * limitations under the License.

 */

#include "WsaClientHandler.hpp"
#include "AddressingHeaders.hpp"

int AXISCALL WsaClientHandler::fini()
{
    	return AXIS_SUCCESS;
}

int AXISCALL WsaClientHandler::init()
{       
    	return AXIS_SUCCESS;
}

void AXISCALL WsaClientHandler::onFault(void* pvIMsg)
{
}

int AXISCALL WsaClientHandler::invoke(void* pvIMsg)
{
	printf("test0\n");
    IMessageData *pIMsg = (IMessageData*) pvIMsg;
    IHandlerSoapSerializer* pISZ; 
    pIMsg->getSoapSerializer(&pISZ);  
    pISZ->addNamespaceToEnvelope(Constants.NS_URI_ADDRESSING,"wsa"); 
	printf("test1\n");
  
	if(pIMsg->isPastPivot())    		
		processClientResponse(pIMsg);/*this is a client response*/  
    else       
        processClientRequest(pIMsg);/*this is server request*/

	return AXIS_SUCCESS;
}

WsaClientHandler::WsaClientHandler()
{
}

WsaClientHandler::~WsaClientHandler()
{
}
    
void WsaClientHandler::processClientRequest(IMessageData * pIMsg)
{
    
	AddressingHeaders * pReqHeaders = (AddressingHeaders*) pIMsg->getComplexProperty(
                   Constants.ENV_ADDRESSING_REQUEST_HEADERS);
    if (pReqHeaders == NULL) 
    {
        pReqHeaders = new AddressingHeaders();
    } 

    if (pReqHeaders->getMessageId() == NULL) 
	{
		AxisChar * pachTempUUID = (AxisChar*)malloc(42);
        strcpy(pachTempUUID,"uuid:");
        strcat(pachTempUUID,pUUIDGen->nextUUID());
        MessageId * pMessageId = new MessageId(pachTempUUID);
        pMessageId->setMustUnderstand(true);
        pReqHeaders->setMessageId(pMessageId);
    }
      
    if (pReqHeaders->getTo() == NULL ) 			
	{
		AxisChar * pachToUri = (AxisChar*)pIMsg->getProperty(Constants.ENV_ADDRESSING_TO_URI);
		if(strlen(pachToUri)!=0)
			pReqHeaders->setTo(pachToUri);
         
	}
     
	AxisChar * pachActionUri = (AxisChar*) pIMsg->getProperty(Constants.ENV_ADDRESSING_ACTION_URI);
    if (strlen(pachActionUri) != 0) 
	{
		pReqHeaders->setAction(new Action(pachActionUri));
    }
	else if(pReqHeaders->getAction() != NULL) 
	{
                     
	}
		 
    if (pReqHeaders->getFrom() == NULL) 
    {
         AxisChar * pFromUri = (AxisChar*) pIMsg->getProperty(Constants.ENV_ADDRESSING_FROM_URI);
         if (strlen(pFromUri)!=0)
		 {            
             pReqHeaders->setFrom(new From(pFromUri));
         } 
		 else 
		 {   
          	 pReqHeaders->setFrom(new From(Constants.NS_URI_ANONYMOUS));		     
         }
    }

    AxisChar * pachReplyToUri = (AxisChar*) pIMsg->getProperty(Constants.ENV_ADDRESSING_REPLYTO_URI);
  
    if (pReqHeaders->getReplyTo() == NULL) 
    {
		if (strlen(pachReplyToUri) != 0)
		{
            pReqHeaders->setReplyTo(new ReplyTo(pachReplyToUri));                    
        } 
		else 
		{
            pReqHeaders->setReplyTo(new ReplyTo(pReqHeaders->getFrom()->getAddress()->getUri()));
			
        }
    }
  	         
    AxisChar* pachFaultToUri = (AxisChar*) pIMsg->getProperty(Constants.ENV_ADDRESSING_FAULTTO_URI);   
    if (pReqHeaders == NULL && strlen(pachFaultToUri)!=0) 
    {
         pReqHeaders->setFaultTo(new FaultTo(pachFaultToUri));
    }	

    ReferenceProperties * pRefprops = (ReferenceProperties*)pIMsg->getComplexProperty(Constants.ENV_ADDRESSING_REFERENCEPROPERTIES);
    //For testing
    pRefprops = new ReferenceProperties(Constants.REFERENCE_PROPERTIES);
    //pRefprops->setLocalName(Constants.REFERENCE_PROPERTIES);
    pRefprops->addProperty("Name1","Value1");
    pRefprops->addProperty("Name2","Value2");
    pRefprops->addProperty("Name3","Value3");
 
    if(pRefprops != NULL)
    {
        From * pFrom = pReqHeaders->getFrom();
        if(pFrom != NULL)
            pFrom->setProperties(pRefprops);
    }

    pIMsg->setComplexProperty(Constants.ENV_ADDRESSING_REQUEST_HEADERS, (void*)pReqHeaders,sizeof(AddressingHeaders));     
    pReqHeaders->toSoapMessage(pIMsg); 
    	  
}

void WsaClientHandler::processClientResponse(IMessageData * pIMsg)
{
 AddressingHeaders * pReqHeaders = 
             new AddressingHeaders(pIMsg,false,false,false);
 pIMsg->setComplexProperty(Constants.ENV_ADDRESSING_RESPONSE_HEADERS,
                                (void*)pReqHeaders,sizeof(AddressingHeaders));
         
}