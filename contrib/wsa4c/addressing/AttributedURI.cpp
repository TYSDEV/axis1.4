#include "AttributedURI.hpp"  
#include <axis/server/GDefine.h>
  
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

