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
#include "AddressingHeaders.hpp"
#include "AttributedQName.hpp"
#include "ServiceNameType.hpp"
#include "PortType.hpp"

/***
 * Class AddressingHeaders
 * 
 */
AddressingHeaders::AddressingHeaders()
{
    init();    
}

AddressingHeaders::AddressingHeaders(IMessageData *pIMsg)              
{
	new AddressingHeaders(pIMsg,false,false,false);
}
 
AddressingHeaders::AddressingHeaders(IMessageData *pIMsg, bool process, bool remove)      
{
    new AddressingHeaders( pIMsg, process, remove, false);   
}

AddressingHeaders::AddressingHeaders(IMessageData *pIMsg, bool process , bool remove, bool setMustUnderstand)              
{
    this->init();
    
	IHandlerSoapDeSerializer* pIDSZ;
	pIMsg->getSoapDeSerializer(&pIDSZ);

	IHeaderBlock * pHeaderBlock = pIDSZ->getHeaderBlock(Constants.MESSAGE_ID,Constants.NS_URI_ADDRESSING);	
    
    if(pHeaderBlock != NULL)
	{
		m_pMessageId = new MessageId((AxisChar*)pHeaderBlock->getFirstChild()->getValue());
		const AxisChar * pachValue = pHeaderBlock->getAttributeValue(Constants.MUSTUNDERSTAND,Constants.NS_PREFIX_SOAP_ENVELOPE);
        if(pachValue != NULL && strcmp(pachValue,"1")==0)
            m_pMessageId->setMustUnderstand(true);
	}
    
    pHeaderBlock = pIDSZ->getHeaderBlock(Constants.TO,Constants.NS_URI_ADDRESSING);	
	if(pHeaderBlock != NULL)
	{
		m_pTo = new To(pHeaderBlock->getFirstChild()->getValue());
		const AxisChar * pachValue = pHeaderBlock->getAttributeValue(Constants.MUSTUNDERSTAND,Constants.NS_PREFIX_SOAP_ENVELOPE);
        if(pachValue != NULL && strcmp(pachValue,"1")==0)
            m_pMessageId->setMustUnderstand(true);
	}
    
    pHeaderBlock = pIDSZ->getHeaderBlock(Constants.ACTION,Constants.NS_URI_ADDRESSING);	
	if(pHeaderBlock != NULL)
	{
		m_pAction = new Action((AxisChar*)pHeaderBlock->getFirstChild()->getValue());
		const AxisChar * pachValue = pHeaderBlock->getAttributeValue(Constants.MUSTUNDERSTAND,Constants.NS_PREFIX_SOAP_ENVELOPE);
        if(pachValue != NULL && strcmp(pachValue,"1")==0)
            m_pMessageId->setMustUnderstand(true);
	}
    
    pHeaderBlock = pIDSZ->getHeaderBlock(Constants.FROM,Constants.NS_URI_ADDRESSING);	
	if(pHeaderBlock != NULL && pHeaderBlock->getFirstChild()!=NULL)
    {
        m_pFrom = new From(pHeaderBlock);
       	const AxisChar * pachValue = pHeaderBlock->getAttributeValue(Constants.MUSTUNDERSTAND,Constants.NS_PREFIX_SOAP_ENVELOPE);
        if(pachValue != NULL && strcmp(pachValue,"1")==0)
            m_pMessageId->setMustUnderstand(true);   
	}

    pHeaderBlock = pIDSZ->getHeaderBlock(Constants.REPLY_TO,Constants.NS_URI_ADDRESSING);	
	if(pHeaderBlock != NULL)
	{
        m_pReplyTo = new ReplyTo(pHeaderBlock);
		const AxisChar * pachValue = pHeaderBlock->getAttributeValue(Constants.MUSTUNDERSTAND,Constants.NS_PREFIX_SOAP_ENVELOPE);
        if(pachValue != NULL && strcmp(pachValue,"1")==0)
            m_pMessageId->setMustUnderstand(true);
	}
 
    pHeaderBlock = pIDSZ->getHeaderBlock(Constants.FAULT_TO,Constants.NS_URI_ADDRESSING);	
	if(pHeaderBlock != NULL)
	{
        m_pFaultTo = new FaultTo(pHeaderBlock);
		const AxisChar * pachValue = pHeaderBlock->getAttributeValue(Constants.MUSTUNDERSTAND,Constants.NS_PREFIX_SOAP_ENVELOPE);
        if(pachValue != NULL && strcmp(pachValue,"1")==0)
            m_pMessageId->setMustUnderstand(true);
	}
 
    pHeaderBlock = pIDSZ->getHeaderBlock(Constants.RELATES_TO,Constants.NS_URI_ADDRESSING);	
	if(pHeaderBlock != NULL)
	{
		addRelatesTo((AxisChar*)pHeaderBlock->getFirstChild()->getValue(),NULL);
	}
   
	pHeaderBlock = pIDSZ->getHeaderBlock(Constants.REFERENCE_PROPERTIES,Constants.NS_URI_ADDRESSING);	
	if(pHeaderBlock != NULL)
	{
		m_pReferenceProperties = new ReferenceProperties(pHeaderBlock);
	}
	
}

AddressingHeaders::~AddressingHeaders()
{
    if(m_pAction != NULL)
        delete(m_pAction);
    if(m_pReplyTo != NULL)
        delete(m_pReplyTo);
    if(m_pTo != NULL)
        delete(m_pTo);
    if(m_pFaultTo != NULL)
        delete(m_pFrom);
    if(m_pFrom != NULL)
        delete(m_pFrom);
    if(m_pMessageId != NULL)
        delete(m_pMessageId);
    if(m_pReferenceProperties != NULL)
        delete(m_pReferenceProperties);
}

void AddressingHeaders::init()
{
    m_pAction = NULL;
    m_pReplyTo = NULL;
    m_pTo = NULL;
    m_pFaultTo = NULL;
    m_pFrom = NULL;
    m_pMessageId = NULL;
    m_pReferenceProperties = NULL;
}
     
Action * AddressingHeaders::getAction() 
{
    return m_pAction;
}
 
void AddressingHeaders::setAction(Action * pAction) 
{
    m_pAction = pAction;
}
 
To * AddressingHeaders::getTo() 
{
    return m_pTo;
}

void AddressingHeaders::setTo(AxisChar * pachUri)
{
    if(m_pTo != NULL)
        delete(m_pTo);
    m_pTo = new To(pachUri);
}
 
void AddressingHeaders::setTo(To * pTo) 
{
    m_pTo = pTo;
}
 
void AddressingHeaders::setMessageId(MessageId * pMessageId) 
{
    m_pMessageId = pMessageId;
}
     
void AddressingHeaders::setReferenceProperties(ReferenceProperties * pRefProps) 
{
    m_pReferenceProperties = pRefProps;
}
     
ReferenceProperties * AddressingHeaders::getReferenceProperties() 
{
    return m_pReferenceProperties;
}
 
void AddressingHeaders::toSoapMessage(IMessageData *pIMsg)
{   
    if (m_pMessageId != NULL)
	   m_pMessageId->toSoapHeaderBlock(pIMsg);	

    if (m_pAction != NULL)
        m_pAction->toSoapHeaderBlock(pIMsg);       

	if(m_pTo != NULL)
        m_pTo->toSoapHeaderBlock(pIMsg);
   
    if (m_pFrom != NULL)   
        m_pFrom->toSoapHeaderBlock(pIMsg);
   
	if (m_pReplyTo != NULL)
        m_pReplyTo->toSoapHeaderBlock(pIMsg);	
     
	if (m_pFaultTo != NULL)
        m_pFaultTo->toSoapHeaderBlock(pIMsg);		
     	
    list<RelatesTo*>::iterator relatesTo = m_relatesToList.begin();
    while(relatesTo != m_relatesToList.end())
	{
        (*relatesTo)->toSoapHeaderBlock(pIMsg);
		relatesTo++;
	}
}
 
MessageId * AddressingHeaders::getMessageId() 
{
    return m_pMessageId;
}
 
list<RelatesTo*> AddressingHeaders::getRelatesToList()
{
    return m_relatesToList;
}
 
void AddressingHeaders::setRelatesToList(list<RelatesTo*> relatesToList)
{
    m_relatesToList = relatesToList;
}

void AddressingHeaders::addRelatesTo(AxisChar * pachUri, RelationshipType * pType)
{
    RelatesTo * pRelatesTo = new RelatesTo(pachUri);
    if(pType != NULL)
        pRelatesTo->setRelationshipType(pType);
    m_relatesToList.push_back(pRelatesTo);   
}

From * AddressingHeaders::getFrom() 
{
    return m_pFrom;
}
 
void AddressingHeaders::setFrom(From * pFrom) 
{   
    m_pFrom = pFrom;
}

ReplyTo * AddressingHeaders::getReplyTo() 
{
    return m_pReplyTo;
}
 
void AddressingHeaders::setReplyTo(ReplyTo * pReplyTo) 
{
    m_pReplyTo = pReplyTo;
}


EndpointReferenceType * AddressingHeaders::getFaultTo() 
{
    return m_pFaultTo;
}

void AddressingHeaders::setFaultTo(FaultTo * pFaultTo) 
{
    this->m_pFaultTo = new FaultTo(pFaultTo->getAddress()->getUri());
}
 
bool AddressingHeaders::isSetMustUnderstand() 
{
    return m_bSetMustUnderstand;
}

void AddressingHeaders::setSetMustUnderstand(bool setMustUnderstand) 
{
    m_bSetMustUnderstand = setMustUnderstand;
}

