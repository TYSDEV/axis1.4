#include "ReferenceProperties.hpp"
#include "Constants.hpp"
    
ReferenceProperties::ReferenceProperties()
{
}

ReferenceProperties::ReferenceProperties(AxisChar * pachLocalName)
{
	m_pachLocalName = (AxisChar*) malloc(strlen(pachLocalName)+1);
    strcpy(m_pachLocalName, pachLocalName);

    m_pachPrefix = '\0';
}
ReferenceProperties::~ReferenceProperties()
{
    free(m_pachPrefix);
	free(m_pachLocalName);
}

void ReferenceProperties::setLocalName(AxisChar * pachLocalName)
{
    free(m_pachLocalName);
    m_pachLocalName = (AxisChar*) malloc(strlen(pachLocalName)+1);
    strcpy(m_pachLocalName, pachLocalName);
}

AxisChar * ReferenceProperties::getLocalName()
{
	return m_pachLocalName;
}

void ReferenceProperties::setPrefix(AxisChar * pachPrefix)
{
    free(m_pachPrefix);
    m_pachPrefix = (AxisChar*) malloc(strlen(pachPrefix));
    strcpy(m_pachPrefix,pachPrefix);
}

AxisChar * ReferenceProperties::getPrefix()
{
    return m_pachPrefix;
}

int ReferenceProperties::setProperty(AxisChar * pachName, AxisChar * pachValue)
{
    AxisChar* pachTmpName = (AxisChar*) malloc (strlen (pachName) + 1);
    strcpy (pachTmpName, pachName);
    AxisChar* pachTmpValue = (AxisChar*) malloc (strlen (pachValue) + 1);
    strcpy (pachTmpValue, pachValue);

    m_refProps[pachTmpName] = pachTmpValue;

    return AXIS_SUCCESS;
}

const AxisChar* ReferenceProperties::getProperty (AxisChar* pachName)
{
    if (m_refProps.find (pachName) != m_refProps.end ())
    {
        return m_refProps[pachName];
    }

    return "";
}

map<AxisChar*,AxisChar*> ReferenceProperties::getProperties()
{
    return m_refProps;
}

IHeaderBlock * ReferenceProperties::toSoapHeaderBlock(IMessageData *pIMsg)
{
	return toSoapHeaderBlock(pIMsg,m_pachLocalName);
}

IHeaderBlock * ReferenceProperties::toSoapHeaderBlock(IMessageData *pIMsg, AxisChar* pName)
{
	IHandlerSoapSerializer* pISZ;
	pIMsg->getSoapSerializer(&pISZ);
//	m_children = pIMsg->getReferenceProperties();
	//For testing
	m_refProps["L1"] = "Val1";
	m_refProps["L2"] = "Val2";
	m_refProps["L3"] = "Val3";

	IHeaderBlock* pIHeaderBlock= pISZ->createHeaderBlock();

	pIHeaderBlock->setLocalName(pName);
	pIHeaderBlock->setUri(Constants.NS_URI_ADDRESSING);
   		        
	printf("in the WsaHandler::Invoke : %s\n");	

    map<AxisChar *,AxisChar *>::iterator prop = m_refProps.begin();

	while(prop != m_refProps.end())
	{		
        BasicNode * pElementNode = pIHeaderBlock->createChild(ELEMENT_NODE);
        pElementNode->setLocalName((*prop).first);
        
        BasicNode * pCharacterNode = pIHeaderBlock->createChild(CHARACTER_NODE);
		pCharacterNode->setValue(prop->second);
        pElementNode->addChild(pCharacterNode);


        pIHeaderBlock->addChild(pElementNode);
		prop++;
	}
	
    return pIHeaderBlock;
	
}

