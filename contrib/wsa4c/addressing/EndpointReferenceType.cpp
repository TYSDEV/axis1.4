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
 
EndpointReferenceType::EndpointReferenceType(const AxisChar* pachLocalName)
{
	init();
    setLocalName(pachLocalName);   
}
  	  
EndpointReferenceType::EndpointReferenceType(const AxisChar* pachLocalName,const AxisChar * pachAddress)
{
    init();
    setLocalName(pachLocalName);
    setAddress(pachAddress);   
}

EndpointReferenceType::EndpointReferenceType(EndpointReferenceType * pEndpoint)
{
    setAddress(pEndpoint->getAddress()->getUri());
    setPortType(pEndpoint->getPortType());
    setServiceName(pEndpoint->getServiceName());
    setProperties(new ReferenceProperties(pEndpoint->getProperties()));
       
}

EndpointReferenceType::EndpointReferenceType(IHeaderBlock * pIHeaderBlock)
{  
            
    init();
    setLocalName(pIHeaderBlock->getLocalName());   
    for(int i=0; i<pIHeaderBlock->getNoOfChildren(); i++)
    {
                
        BasicNode * pChild = pIHeaderBlock->getFirstChild();//getChild(i);
        
        if(strcmp(pChild->getLocalName(),Constants.ADDRESS)==0)
        {
            m_pAddress = new Address(pChild->getFirstChild()->getValue());
        }

        if(strcmp(pChild->getLocalName(),Constants.REFERENCE_PROPERTIES)==0)
        {
            m_pRefprops = new ReferenceProperties();
                     
            for(int j=0; j<pChild->getNoOfChildren(); j++)
            {
                BasicNode * pProp = pChild->getChild(j);
                //m_pRefprops->setPrefixUri(pProp->getPrefix());
                m_pRefprops->addProperty(pProp->getLocalName(),pChild->getFirstChild()->getValue());
            }
        }
        if(strcmp(pChild->getLocalName(),Constants.SERVICE_NAME)==0)
        {
            m_pServiceName = new ServiceNameType(pChild->getFirstChild()->getValue(),NULL);
        }
        if(strcmp(pChild->getLocalName(),Constants.PORT_TYPE)==0)
        {
            m_pPortType = new PortType(pChild->getFirstChild()->getValue());
        }    
    }
}

void EndpointReferenceType::init()
{
    m_pAddress = NULL;
    m_pRefprops = NULL;
    m_pPortType = NULL;
    m_pServiceName = NULL;
    m_pachLocalName = NULL;
}

EndpointReferenceType::~EndpointReferenceType()
{
    if(m_pachLocalName!=NULL)
        delete(m_pachLocalName);
}
  
AttributedUri * EndpointReferenceType::getAddress()
{
    return m_pAddress;
}
 
void EndpointReferenceType::setAddress(const AxisChar * pachAddress)
{
    m_pAddress = new Address(pachAddress);
}
 
AttributedQName * EndpointReferenceType::getPortType()
{
    return m_pPortType;
}
 
void EndpointReferenceType::setPortType(AttributedQName * portType)
{
    m_pPortType = new PortType(Constants.PORT_TYPE,portType->getUri());
}
 
ReferenceProperties * EndpointReferenceType::getProperties()
{
    return m_pRefprops;
}
 
void EndpointReferenceType::setProperties(ReferenceProperties * pRefprops)
{
    m_pRefprops = new ReferenceProperties(pRefprops);
}
 
ServiceNameType * EndpointReferenceType::getServiceName()
{
    return m_pServiceName;
}

void EndpointReferenceType::setServiceName(ServiceNameType * pServiceName)
{
    m_pServiceName = new ServiceNameType(Constants.SERVICE_NAME,pServiceName->getUri(),NULL);
}

void EndpointReferenceType::setMustUnderstand(bool bMustUnderstand)
{
    m_bMustUnderstand = bMustUnderstand;
}

bool EndpointReferenceType::isMustUnderstand()
{
    return m_bMustUnderstand;
}

IHeaderBlock * EndpointReferenceType::toSoapHeaderBlock(IMessageData *pIMsg)
{   
    IHandlerSoapSerializer* pISZ;
	pIMsg->getSoapSerializer(&pISZ);

    IHeaderBlock* pIHeaderBlock= pISZ->createHeaderBlock();
    pIHeaderBlock->setLocalName(m_pachLocalName);
	pIHeaderBlock->setUri(Constants.NS_URI_ADDRESSING);

    if(m_pAddress != NULL && m_pAddress->getUri() != NULL)
    {    
        BasicNode * pElementNode = pIHeaderBlock->createChild(ELEMENT_NODE,  
                                   m_pAddress->getLocalName(), Constants.NS_PREFIX_ADDRESSING,Constants.NS_URI_ADDRESSING, 
                                   NULL);
        BasicNode * pCharacterNode = pIHeaderBlock->createChild(CHARACTER_NODE,  
                                   NULL,NULL,NULL, m_pAddress->getUri());
        pElementNode->addChild(pCharacterNode);
		pIHeaderBlock->addChild(pElementNode);  
    }
   
    if(m_pRefprops != NULL)
    {
        BasicNode * parentNode = pIHeaderBlock->createChild(ELEMENT_NODE,  
                                   m_pRefprops->getLocalName(), Constants.NS_PREFIX_ADDRESSING,Constants.NS_URI_ADDRESSING, 
                                   NULL); 
        map<AxisChar *,AxisChar *,ReferenceProperties::ltstr>  refProps =  m_pRefprops->getProperties();
        map<AxisChar *,AxisChar *,ReferenceProperties::ltstr>::iterator prop =refProps.begin();
        while(prop != refProps.end())
	    {          
            BasicNode * pElementNode = pIHeaderBlock->createChild(ELEMENT_NODE,  
                                   (*prop).first,Constants.NS_PREFIX_ADDRESSING,Constants.NS_URI_ADDRESSING, 
                                   NULL); 
           // if(strlen(m_pRefprops->getPrefix())!=0)
           //     pElementNode->setPrefix(m_pRefprops->getPrefix);            
            BasicNode * pCharacterNode = pIHeaderBlock->createChild(CHARACTER_NODE,  
                                         NULL,NULL,NULL,prop->second);
            pElementNode->addChild(pCharacterNode);
            parentNode->addChild(pElementNode);
		    prop++;
        }
        pIHeaderBlock->addChild(parentNode);
	
    }
   
    if(m_pPortType != NULL)
    {
        BasicNode * pElementNode = pIHeaderBlock->createChild(ELEMENT_NODE,
                                   m_pPortType->getLocalName(),NULL,Constants.NS_URI_ADDRESSING,
                                   NULL);       
        BasicNode * pCharacterNode = pIHeaderBlock->createChild(CHARACTER_NODE,  
                                                    NULL,NULL,NULL,m_pPortType->getQname());
        pElementNode->addChild(pCharacterNode);       
        pIHeaderBlock->addChild(pElementNode); 
    }

    if( m_pServiceName != NULL)
    {
		BasicNode * pElementNode = pIHeaderBlock->createChild(ELEMENT_NODE,
                                   m_pServiceName->getLocalName(),NULL,Constants.NS_URI_ADDRESSING,
                                   NULL);
        if(strlen(m_pServiceName->getPortName())!=0)
            pElementNode->createAttribute(Constants.PORT_NAME,"",m_pServiceName->getPortName());
              
        BasicNode * pCharacterNode = pIHeaderBlock->createChild(CHARACTER_NODE,  
                                                    NULL,NULL,NULL,m_pServiceName->getQname());;
        pElementNode->addChild(pCharacterNode);
        pIHeaderBlock->addChild(pElementNode); 
    }
    return pIHeaderBlock; 
}

void EndpointReferenceType::setLocalName(const AxisChar * pachLocalName)
{
    if(m_pachLocalName != NULL)
        delete(m_pachLocalName);
    m_pachLocalName = new AxisChar(strlen(pachLocalName)+1);
    strcpy(m_pachLocalName,pachLocalName);
}
