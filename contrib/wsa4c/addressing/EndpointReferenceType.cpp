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


#include "EndpointReferenceType.hpp"
 
EndpointReferenceType::EndpointReferenceType(AxisChar* pachLocalName)
{
    m_pachLocalName = (AxisChar*) malloc(strlen(pachLocalName)+1);
    strcpy(m_pachLocalName,pachLocalName);
	m_pAddress = NULL;
    m_pRefprops = NULL;
    m_pPortType = NULL;
    m_pServiceName = NULL;
    
  
}
  	  
EndpointReferenceType::EndpointReferenceType(AxisChar* pachLocalName,AxisChar * pachAddress)
{
    m_pachLocalName = (AxisChar*) malloc(strlen(pachLocalName)+1);
    strcpy(m_pachLocalName,pachLocalName);
    m_pAddress = new Address(pachAddress);
	m_pRefprops = NULL;
    m_pPortType = NULL;
    m_pServiceName = NULL;
    
}

EndpointReferenceType::~EndpointReferenceType()
{
    free(m_pachLocalName);
}
  
AttributedUri * EndpointReferenceType::getAddress()
{
    return m_pAddress;
}
 
void EndpointReferenceType::setAddress(AxisChar * pachAddress)
{
    m_pAddress = new Address(pachAddress);
}
 
AttributedQName * EndpointReferenceType::getPortType()
{
    return m_pPortType;
}
 
void EndpointReferenceType::setPortType(AttributedQName * portType)
{
    m_pPortType = portType;
}
 
ReferenceProperties * EndpointReferenceType::getProperties()
{
    return m_pRefprops;
}
 
void EndpointReferenceType::setProperties(ReferenceProperties * pRefprops)
{
    m_pRefprops = pRefprops;
}
 
ServiceNameType * EndpointReferenceType::getServiceName()
{
    return m_pServiceName;
}

void EndpointReferenceType::setServiceName(ServiceNameType * pServiceName)
{
    m_pServiceName = pServiceName;
}

IHeaderBlock * EndpointReferenceType::toSoapHeaderBlock(IMessageData *pIMsg)
{
    IHandlerSoapSerializer* pISZ;
	pIMsg->getSoapSerializer(&pISZ);

	IHeaderBlock* pIHeaderBlock= pISZ->createHeaderBlock();

	pIHeaderBlock->setLocalName(m_pachLocalName);
	pIHeaderBlock->setUri(Constants.NS_URI_ADDRESSING);
   		        
	printf("in the WsaHandler::Invoke : %s\n");	

    if(NULL != m_pAddress)
    {
        BasicNode * pElementNode = pIHeaderBlock->createChild(ELEMENT_NODE);
        pElementNode->setLocalName(m_pAddress->getLocalName());
        pElementNode->setPrefix(Constants.NS_PREFIX_ADDRESSING);

        BasicNode * pCharacterNode = pIHeaderBlock->createChild(CHARACTER_NODE);
        pCharacterNode->setValue(m_pAddress->getUri());
        pElementNode->addChild(pCharacterNode);
        
        pIHeaderBlock->addChild(pElementNode);  
    }

    if(NULL != m_pRefprops)
    {
        BasicNode * parentNode = pIHeaderBlock->createChild(ELEMENT_NODE);
        parentNode->setLocalName(m_pRefprops->getLocalName());
        parentNode->setPrefix(Constants.NS_PREFIX_ADDRESSING);

        map<AxisChar *,AxisChar *>::iterator prop = m_pRefprops->getProperties().begin();

	    while(prop != m_pRefprops->getProperties().end())
	    {		
            BasicNode * pElementNode = pIHeaderBlock->createChild(ELEMENT_NODE);
           // if(strlen(m_pRefprops->getPrefix())!=0)
           //     pElementNode->setPrefix(m_pRefprops->getPrefix);
            pElementNode->setLocalName((*prop).first);
        
            BasicNode * pCharacterNode = pIHeaderBlock->createChild(CHARACTER_NODE);
		    pCharacterNode->setValue(prop->second);
            pElementNode->addChild(pCharacterNode);
            parentNode->addChild(pElementNode);
		    prop++;
	    }
        
        pIHeaderBlock->addChild(parentNode);
	
    }
    
    if(NULL != m_pPortType)
    {
        BasicNode * pElementNode = pIHeaderBlock->createChild(ELEMENT_NODE);
        pElementNode->setLocalName(m_pPortType->getLocalName());
        pElementNode->setPrefix(Constants.NS_PREFIX_ADDRESSING);

        BasicNode * pCharacterNode = pIHeaderBlock->createChild(CHARACTER_NODE);
        pCharacterNode->setValue(m_pPortType->getQname());
        pElementNode->addChild(pCharacterNode);
        
        pIHeaderBlock->addChild(pElementNode); 
    }

    if(NULL != m_pServiceName)
    {
        BasicNode * pElementNode = pIHeaderBlock->createChild(ELEMENT_NODE);
        pElementNode->setLocalName(m_pServiceName->getLocalName());
        pElementNode->setPrefix(Constants.NS_PREFIX_ADDRESSING);

        if(strlen(m_pServiceName->getPortName())!=0)
        {
            pElementNode->createAttribute(Constants.PORT_NAME,"",m_pServiceName->getPortName());
             
        }
        BasicNode * pCharacterNode = pIHeaderBlock->createChild(CHARACTER_NODE);
        pCharacterNode->setValue(m_pPortType->getQname());
        pElementNode->addChild(pCharacterNode);
        
        pIHeaderBlock->addChild(pElementNode); 
    }
        
    return pIHeaderBlock;
    
  
}
