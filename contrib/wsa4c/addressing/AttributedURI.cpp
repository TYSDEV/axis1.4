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


#include "AttributedURI.hpp"  
#include <stdio.h>
#include <stdlib.h>
  
AttributedUri::AttributedUri() 
{
    m_pachLocalName = NULL;
    m_pachUri = NULL;
}

AttributedUri::AttributedUri(const AxisChar * pachLocalName,const AxisChar * pachUri)
{
    if(pachLocalName != NULL && pachUri != NULL)
    {
        m_pachLocalName = (AxisChar*)malloc(strlen(pachLocalName)+1);
        strcpy(m_pachLocalName,pachLocalName);
        m_pachUri = (AxisChar*)malloc(strlen(pachUri)+1);
        strcpy(m_pachUri, pachUri);	   
    }
        
}

AttributedUri::~AttributedUri()
{
    if(m_pachLocalName!=NULL)
    {
	    free(m_pachLocalName);
        m_pachLocalName = NULL;
    }
    if(m_pachUri!=NULL)
    {
        free(m_pachUri);
        m_pachUri = NULL;
    }
}

void AttributedUri::setUri(const AxisChar * pachUri)
{
    if(m_pachUri!=NULL)
        free(m_pachUri);
    m_pachUri = (AxisChar*)malloc(strlen(pachUri)+1);
    strcpy(m_pachUri, pachUri);	
}

AxisChar * AttributedUri::getUri()
{
    return m_pachUri;
}

void AttributedUri::setLocalName(const AxisChar * pachLocalName)
{
    if(m_pachLocalName!=NULL)
        free(m_pachLocalName);
    m_pachLocalName = (AxisChar*)malloc(strlen(pachLocalName)+1);
    strcpy(m_pachLocalName,pachLocalName);
}

AxisChar * AttributedUri::getLocalName()
{
    return m_pachLocalName;
}

void AttributedUri::setMustUnderstand(bool bMustUnderstand)
{
    m_bMustUnderstand = bMustUnderstand;
}


bool AttributedUri::isMustUnderstand()
{
    return m_bMustUnderstand;
}

IHeaderBlock * AttributedUri::toSoapHeaderBlock(IMessageData *pIMsg)
{
	IHandlerSoapSerializer* pISZ;
	pIMsg->getSoapSerializer(&pISZ);
   
	IHeaderBlock* pIHeaderBlock= pISZ->createHeaderBlock();
    pIHeaderBlock->setLocalName(m_pachLocalName);
    pIHeaderBlock->setUri(Constants.NS_URI_ADDRESSING);
    
	BasicNode* pBasicNode = pIHeaderBlock->createChild(CHARACTER_NODE,  
                            NULL,NULL,NULL,m_pachUri);
    if(m_bMustUnderstand)
    {   
       // pIHeaderBlock->createAttribute(Constants.MUSTUNDERSTAND, 
       // NULL, Constants.NS_URI_SOAP_ENVELOPE, "1");
    }
    pIHeaderBlock->addChild(pBasicNode);
    
    return pIHeaderBlock;

}

