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
  
AttributedUri::AttributedUri() 
{
}

AttributedUri::AttributedUri(AxisChar * pachLocalName,AxisChar * pachUri)
{
	m_pachLocalName = (AxisChar*) malloc(strlen(pachUri)+1);
    strcpy(m_pachLocalName,pachLocalName);
    m_pachUri = (AxisChar*) malloc(strlen(pachUri)+1);
    strcpy(m_pachUri, pachUri);	   
}

AttributedUri::~AttributedUri()
{
	free(m_pachLocalName);
    free(m_pachUri);
}

void AttributedUri::setUri(AxisChar * pachUri)
{
    free(m_pachUri);
    m_pachUri = (AxisChar*) malloc(strlen(pachUri)+1);
    strcpy(m_pachUri, pachUri);	
}

AxisChar * AttributedUri::getUri()
{
    return m_pachUri;
}

void AttributedUri::setLocalName(AxisChar * pachLocalName)
{
    free(m_pachLocalName);
    m_pachLocalName = (AxisChar*) malloc(strlen(pachLocalName)+1);
    strcpy(m_pachLocalName,pachLocalName);
}

AxisChar * AttributedUri::getLocalName()
{
    return m_pachLocalName;
}

AxisChar * AttributedUri::getLocalName();

IHeaderBlock * AttributedUri::toSoapHeaderBlock(IMessageData *pIMsg)
{
	IHandlerSoapSerializer* pISZ;
	pIMsg->getSoapSerializer(&pISZ);

	IHeaderBlock* pIHeaderBlock= pISZ->createHeaderBlock();

	pIHeaderBlock->setLocalName(m_pachLocalName);
    pIHeaderBlock->setUri(Constants.NS_URI_ADDRESSING);
		        
	printf("in the WsaHandler::Invoke : %s\n");

	BasicNode* pBasicNode = pIHeaderBlock->createChild(CHARACTER_NODE);
	pBasicNode->setValue(m_pachUri);
	
	pIHeaderBlock->addChild(pBasicNode);
    
    return pIHeaderBlock;

}

