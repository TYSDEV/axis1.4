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

#include <stdlib.h>
#include <stdio.h>
#include "WsaServerHandler.hpp"
#include <axis/GDefine.hpp>
#include <axis/IHandlerSoapSerializer.hpp>
#include <axis/IHandlerSoapDeSerializer.hpp>
#include <axis/IHeaderBlock.hpp>
#include <axis/BasicNode.hpp>
#include <iostream>
#include "AddressingHeaders.hpp"
#include "EndpointReferenceType.hpp"
#include "MessageID.hpp"


WsaServerHandler::WsaServerHandler()
{
}

WsaServerHandler::~WsaServerHandler()
{
}

int WsaServerHandler::invoke(void *pvIMsg)
{
	IMessageData *pIMsg = (IMessageData*) pvIMsg;
    IHandlerSoapSerializer* pISZ; 
    pIMsg->getSoapSerializer(&pISZ);  
    pISZ->addNamespaceToEnvelope(Constants.NS_URI_ADDRESSING,Constants.NS_PREFIX_ADDRESSING); 
  
        
	if(pIMsg->isPastPivot()) 
    {
		/*this is a server response*/
		processServerResponse(pIMsg);
    }
    else
    {
        /*this is server request*/
        processServerRequest(pIMsg);
    }

	
	return AXIS_SUCCESS;
}

void WsaServerHandler::onFault(void *pvIMsg)
{
    IMessageData *pIMsg = (IMessageData*) pvIMsg;
    processFault(pIMsg);
}

int WsaServerHandler::init()
{
	//do any initialization, resetting of values
    return AXIS_SUCCESS;
}

int WsaServerHandler::fini()
{
	//do any finalizatoin
   	return AXIS_SUCCESS;
}

void WsaServerHandler::processServerResponse(IMessageData * pIMsg)
{
   
    /*Get the Request Headers from the pIMsg data
      This requires the addition of requestHeader attribute from IMessageData*/
	
	AddressingHeaders * pReqHeaders = (AddressingHeaders*)pIMsg->getComplexProperty(Constants.ENV_ADDRESSING_REQUEST_HEADERS);
   
    if (pReqHeaders == NULL) 
        return;
	
    AddressingHeaders * pResHeaders = (AddressingHeaders*) pIMsg->getComplexProperty(Constants.ENV_ADDRESSING_RESPONSE_HEADERS);
	if(pResHeaders==NULL)
		pResHeaders = new AddressingHeaders();
	 
    // set From
    From * pFromEpr = pResHeaders->getFrom();
    if (pFromEpr == NULL) 
	{
        To * pTo = pReqHeaders->getTo();
        if (pTo != NULL && pTo->getUri()!=NULL)
		{
			pFromEpr = new From(pTo->getUri());
            pResHeaders->setFrom(pFromEpr);         
		}
    }
  
    // set Action
    Action * pAction = pResHeaders->getAction();
    if (pAction == NULL) 
	{
    // not set - try request headers
        pAction = pReqHeaders->getAction();
        if (pAction != NULL && pAction->getUri()!=NULL) 
		{
            AxisChar * pTempChar = (AxisChar*) malloc(strlen("Response")+strlen(pAction->getUri())+1);
            strcpy(pTempChar,pAction->getUri());
            strcat(pTempChar,"Response");
            pResHeaders->setAction(new Action(pTempChar)) ;
            free(pTempChar);
        }
    }
  
    if (pResHeaders->getTo() == NULL && pReqHeaders->getFrom() != NULL) 
    {
       From * pFrom = pReqHeaders->getFrom();
       pResHeaders->setTo(pFrom->getAddress()->getUri());
    } 
    else 
    {
        pResHeaders->setTo(new To(Constants.NS_URI_ANONYMOUS));
    }
    
    //Set Reference Properties
    if (pReqHeaders->getReferenceProperties() != NULL)
    {  
        ReferenceProperties * pReqprops = pReqHeaders->getReferenceProperties();

        ReferenceProperties * pResprops = new ReferenceProperties(pReqprops);

        pResHeaders->setReferenceProperties(pResprops);         
    }
    
    // process RelatesTo
    MessageId * pMsgId = pReqHeaders->getMessageId();
    if (pMsgId != NULL) 
	{
     
        pResHeaders->addRelatesTo(pMsgId->getUri(),NULL);      
      
    }

    //We require a message id generator here;
    AxisChar * pachTempUUID = (AxisChar*)malloc(42);
    strcpy(pachTempUUID,"uuid:");
    strcat(pachTempUUID,pUUIDGen->nextUUID());
	
    IHandlerSoapSerializer* pISZ;
	pIMsg->getSoapSerializer(&pISZ);
    IHeaderBlock * pIHeaderBlock = NULL;
	if(pISZ != NULL)
		pIHeaderBlock = pISZ->getHeaderBlock(Constants.MESSAGE_ID,Constants.NS_URI_ADDRESSING);

	if(pIHeaderBlock == NULL)
	{
		MessageId * pMessageId = new MessageId(pachTempUUID);
		pMessageId->setMustUnderstand(true);
        pResHeaders->setMessageId(pMessageId);
	}
	else
	{
		//either remove older header and put the new header/update the older header
		MessageId * pMessageId = new MessageId(pachTempUUID);
        pMessageId->setMustUnderstand(true);
		pResHeaders->setMessageId(pMessageId);
	}

    //free(pachTempUUID);
    pResHeaders->toSoapMessage(pIMsg);        
         
    // process ReplyTo
    ReplyTo * pReplyTo = pReqHeaders->getReplyTo();
    if (pReplyTo != NULL) 
	{
        AttributedUri * pAddress = (AttributedUri*)pReplyTo->getAddress();
        if (pAddress != NULL) 
		{
            AxisChar * pachUri = pAddress->getUri();
            if (pachUri != NULL && strcmp(pachUri,Constants.NS_URI_ANONYMOUS)!=0)
			{
                //forward message
            }
        }
    }
}

void WsaServerHandler::processServerRequest(IMessageData * pIMsg)
{
         
   if (pIMsg == NULL) {
       return;
   }
   AddressingHeaders * pReqHeaders = 
             new AddressingHeaders(pIMsg,false,false,false);
   
   pIMsg->setComplexProperty(Constants.ENV_ADDRESSING_REQUEST_HEADERS,
                                (void*)pReqHeaders,sizeof(AddressingHeaders));
         
}

void WsaServerHandler::processFault(IMessageData * pIMsg)
{
    AddressingHeaders * pReqHeaders =
              (AddressingHeaders*) pIMsg->getComplexProperty(
                  Constants.ENV_ADDRESSING_REQUEST_HEADERS);
 
    if (pReqHeaders == NULL) 
    {
             // error?
             return;
    }
 
    AddressingHeaders * pResHeaders = (AddressingHeaders*) pIMsg->getComplexProperty(Constants.ENV_ADDRESSING_RESPONSE_HEADERS);
	if(pResHeaders==NULL)
		pResHeaders = new AddressingHeaders();

    //set From
    From * pFromEpr = pResHeaders->getFrom();
    if (pFromEpr == NULL) 
	{
        To * pTo = pReqHeaders->getTo();
        if (pTo != NULL && pTo->getUri()!=NULL)
		{
			pFromEpr = new From(pTo->getUri());
            pResHeaders->setFrom(pFromEpr);         
		}
    }
    
    if (strcmp(Constants.NS_URI_ADDRESSING,Constants.NS_URI_ADDRESSING_04)==0) 
    {
        pResHeaders->setAction(new Action(Constants.FAULT_ACTION));
    }

    MessageId * pMsgId = pReqHeaders->getMessageId();
    if (pMsgId != NULL) 
	{
        pResHeaders->addRelatesTo(pMsgId->getUri(),NULL);      
    }
 
    AxisChar * pachTempUUID = (AxisChar*)malloc(42);
    strcpy(pachTempUUID,"uuid:");
    strcat(pachTempUUID,pUUIDGen->nextUUID());
	
    IHandlerSoapSerializer* pISZ;
	pIMsg->getSoapSerializer(&pISZ);
    IHeaderBlock * pIHeaderBlock = NULL;
	if(pISZ != NULL)
		pIHeaderBlock = pISZ->getHeaderBlock(Constants.MESSAGE_ID,Constants.NS_URI_ADDRESSING);
	
	if(pIHeaderBlock == NULL)
	{
		MessageId * pMessageId = new MessageId(pachTempUUID);
		pResHeaders->setMessageId(pMessageId);
	}
	else
	{
		//either remove older header and put the new header/update the older header
		MessageId * pMessageId = new MessageId(pachTempUUID);
        pResHeaders->setMessageId(pMessageId);
	}

   
    //free(pachTempUUID);
    pResHeaders->toSoapMessage(pIMsg);
 
    // process FaultTo
    EndpointReferenceType * pFaultTo = pReqHeaders->getFaultTo();
    if (pFaultTo != NULL) 
	{
        AttributedUri * pAddress = (AttributedUri*)pFaultTo->getAddress();
        if (pAddress != NULL) 
		{
            AxisChar * pachUri = pAddress->getUri();
            if (pachUri != NULL && strcmp(pachUri,Constants.NS_URI_ANONYMOUS)!=0)
			{
               
            }
        }
    }
}

void WsaServerHandler::setTargetService(IMessageData * pIMsg,
                                    AddressingHeaders * pHeaders)
{
    To * pTo = pHeaders->getTo();
    if (pTo == NULL) 
    {
        return;
    }
    AxisChar * pachToUri = pTo->getUri();
    if (pachToUri == NULL) 
    {
        return;
    }

    // set the target service
    AxisChar * pachTempService;
    AxisChar * p = pachToUri;
    while(p!=NULL)
    {
        if(*p=='/')
        {
            pachTempService = (p+1);

        }
        p++;
    }

   // pIMsg->setService(pachTempService);
}



void WsaServerHandler::forwardMessage(EndpointReferenceType * pReplyTo,
                                IMessageData * pIMsg)
{
    AttributedUri * pAddress = pReplyTo->getAddress();
 
    AddressingHeaders * pResHeaders = NULL;
    if (pIMsg != NULL) 
    {
         pResHeaders = (AddressingHeaders*) pIMsg->getComplexProperty(Constants.ENV_ADDRESSING_RESPONSE_HEADERS);
    }
    if (pResHeaders == NULL)
    {
        pResHeaders = new AddressingHeaders();
    }
         
    pResHeaders->setTo(pAddress->getUri());
    pResHeaders->setReferenceProperties(pReplyTo->getProperties());
 
    //call
}
    

     
