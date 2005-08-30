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


#include "ReferenceProperties.hpp"
#include "Constants.hpp"
    
ReferenceProperties::ReferenceProperties()
{

}
ReferenceProperties::ReferenceProperties(IHeaderBlock * pIHParent)
{
	if(pIHParent!=NULL)
	{
		for(int i=0; i<pIHParent->getNoOfChildren(); i++)
		{
			BasicNode * pBasicNode = (BasicNode*) pIHParent->getChild(i);
			if(pBasicNode!=NULL)
			{
				AxisChar * pachLocalName = (AxisChar*)pBasicNode->getLocalName();
				if(pachLocalName!=NULL)
					if(pBasicNode->getFirstChild!=NULL && pBasicNode->getFirstChild()->getValue()!=NULL)
					{
						m_refProps[pachLocalName] = (AxisChar*)pBasicNode->getFirstChild()->getValue();
					}
			}
		}
	}
}

ReferenceProperties::ReferenceProperties(const AxisChar * pachLocalName)
{
	m_pachLocalName = (AxisChar*)malloc(strlen(pachLocalName)+1);
    strcpy(m_pachLocalName, pachLocalName);
    
    m_pachUri = NULL;
}

ReferenceProperties::ReferenceProperties(ReferenceProperties * pRefprops)
{
    map<AxisChar *,AxisChar *,ltstr> tempmap = pRefprops->getProperties();
    map<AxisChar *,AxisChar *,ltstr>::iterator itCurrentItem 
        = tempmap.begin();
    while (itCurrentItem != tempmap.end ())
    { 
        m_refProps[(*itCurrentItem).first] = itCurrentItem->second;       
        itCurrentItem++;
    }

    if(pRefprops->getLocalName() != NULL)
    {
        m_pachLocalName = (AxisChar*)malloc(strlen(pRefprops->getLocalName()));
        strcpy(m_pachLocalName,pRefprops->getLocalName());
    }
   /* 
    if(pRefprops->getPrefixUri() != NULL)
    {
        m_pachUri = (AxisChar*)malloc(strlen(pRefprops->getPrefixUri()));
        strcpy(m_pachUri,pRefprops->getPrefixUri());
    }*/    
}

ReferenceProperties::~ReferenceProperties()
{
    if(m_pachUri!=NULL)
        free(m_pachUri);
    if(m_pachLocalName != NULL)
	    free(m_pachLocalName);
	map <AxisChar*,AxisChar*, ltstr >::iterator itCurrentItem =
        m_refProps.begin ();

    while (itCurrentItem != m_refProps.end ())
    {
        delete [] (*itCurrentItem).first;
        delete [] itCurrentItem->second;

        itCurrentItem++;
    }

    m_refProps.clear ();
}

void ReferenceProperties::setLocalName(const AxisChar * pachLocalName)
{
    if(m_pachLocalName!=NULL)
        free(m_pachLocalName);
    m_pachLocalName = (AxisChar*)malloc(strlen(pachLocalName)+1);
    strcpy(m_pachLocalName, pachLocalName);
}

AxisChar * ReferenceProperties::getLocalName()
{
	return m_pachLocalName;
}

void ReferenceProperties::setPrefixUri(const AxisChar * pachUri)
{
    if(m_pachUri!=NULL)
        free(m_pachUri);
    m_pachUri = (AxisChar*)malloc(strlen(pachUri)+1);
    strcpy(m_pachUri,pachUri);
}

AxisChar * ReferenceProperties::getPrefixUri()
{
    return m_pachUri;
}

const AxisChar* ReferenceProperties::getProperty (AxisChar* pachName)
{
    if (m_refProps.find (pachName) != m_refProps.end ())
    {
        return m_refProps[pachName];
    }

    return "";
}

void ReferenceProperties::addProperty(const AxisChar* pachLocalName,const AxisChar* pachValue)
{
    if(pachLocalName!=NULL && pachValue != NULL)
    {      
        AxisChar* pachTmpName = (AxisChar*)malloc(strlen (pachLocalName) + 1);
        strcpy (pachTmpName, pachLocalName);
        AxisChar* pachTmpValue = (AxisChar*)malloc(strlen (pachValue) + 1);
        strcpy (pachTmpValue, pachValue);
       
		m_refProps[pachTmpName] = pachTmpValue;        
    }
}

map<AxisChar*,AxisChar*,ReferenceProperties::ltstr>  ReferenceProperties::getProperties()
{
    return m_refProps;
}

IHeaderBlock * ReferenceProperties::toSoapHeaderBlock(IMessageData *pIMsg)
{
	return toSoapHeaderBlock(pIMsg,m_pachLocalName);
}

IHeaderBlock * ReferenceProperties::toSoapHeaderBlock(IMessageData *pIMsg,const AxisChar* pName)
{
	IHandlerSoapSerializer* pISZ;
	pIMsg->getSoapSerializer(&pISZ);

	IHeaderBlock* pIHeaderBlock= pISZ->createHeaderBlock();

	pIHeaderBlock->setLocalName(pName);
	pIHeaderBlock->setUri(Constants.NS_URI_ADDRESSING);
   		        
	map<AxisChar *,AxisChar *,ltstr>::iterator itCurrentItem = m_refProps.begin();

	while(itCurrentItem != m_refProps.end())
	{	
        BasicNode * pElementNode = pIHeaderBlock->createChild(ELEMENT_NODE,  
                                   (*itCurrentItem).first, NULL,Constants.NS_URI_ADDRESSING, 
                                   NULL);
        
        BasicNode * pCharacterNode = pIHeaderBlock->createChild(CHARACTER_NODE,  
                                   NULL,NULL,NULL, itCurrentItem->second);
        
        pElementNode->addChild(pCharacterNode);
        pIHeaderBlock->addChild(pElementNode);

		itCurrentItem++;
	}
	
    return pIHeaderBlock;
	
}

void ReferenceProperties::toSoapHeaders(IMessageData *pIMsg)
{
    IHandlerSoapSerializer* pISZ;
	pIMsg->getSoapSerializer(&pISZ);	

    map<AxisChar *,AxisChar *,ltstr>::iterator itCurrentItem = m_refProps.begin();

	while(itCurrentItem != m_refProps.end())
	{	
        IHeaderBlock* pIHeaderBlock= pISZ->createHeaderBlock();

	    pIHeaderBlock->setLocalName((*itCurrentItem).first);
        if(m_pachUri != NULL)
            pIHeaderBlock->setUri(m_pachUri);
	       		        
        BasicNode * pCharacterNode = pIHeaderBlock->createChild(CHARACTER_NODE);
		pCharacterNode->setValue(itCurrentItem->second);
        pIHeaderBlock->addChild(pCharacterNode);

		itCurrentItem++;
	}
	

}

