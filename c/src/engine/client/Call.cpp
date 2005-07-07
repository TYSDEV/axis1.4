/*
 *   Copyright 2003-2004 The Apache Software Foundation.
// (c) Copyright IBM Corp. 2004, 2005 All Rights Reserved
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 * 
 * @author Sanjaya Singharage (sanjayas@opensource.lk)
 * @author Susantha Kumara (susantha@opensource.lk, skumara@virtusa.com)
 * @author Samisa Abeysinghe (sabeysinghe@virtusa.com)
 *
 */

#include <axis/client/Call.hpp>
#include <axis/AxisException.hpp>
#include "../../common/AxisConfig.h"
#include "ClientAxisEngine.h"
#include "../SOAPTransportFactory.h"
#include "../../transport/SOAPTransport.h"
#include "../../soap/SoapSerializer.h"
#include "../../soap/SoapDeSerializer.h"
#include "../../soap/HeaderBlock.h"
#include "../../common/AxisGenException.h"
#include "../../soap/Attribute.h"

extern AXIS_CPP_NAMESPACE_PREFIX AxisConfig* g_pConfig;

extern "C" int initialize_module (int bServer);
extern "C" int uninitialize_module ();

AXIS_CPP_NAMESPACE_USE

bool CallBase::bInitialized = false;

Call::Call ()
:m_pcEndPointUri(NULL), m_strProxyHost(""), m_uiProxyPort(0), m_bUseProxy(false),
m_bCallInitialized(false)
{
    m_pAxisEngine = NULL;
    m_pIWSSZ = NULL;
    m_pIWSDZ = NULL;
    initialize_module (0);
    
    m_pTransport = NULL;
    m_nTransportType = APTHTTP1_1;
    
    m_nStatus = AXIS_SUCCESS;
    m_pchSessionID = NULL;
	m_pContentIdSet = new ContentIdSet();

    // Setup Transport
    try
    {
        // Get a transport object from factory
        if( !m_pTransport)
		{
            m_pTransport = SOAPTransportFactory::getTransportObject(m_nTransportType);

			if( !m_pTransport)
			{
				m_nStatus = AXIS_FAIL;
			}
		}

        // SSL channel related initilizations
        char * pcSSLChannelInfo = g_pConfig->getAxisConfProperty( AXCONF_SECUREINFO);
		if( pcSSLChannelInfo && strlen( pcSSLChannelInfo) > 0)
		{
			char *	pszArgPtr = NULL;
			int		iArgIndex = 0;
			string	sArguments[8];

			pszArgPtr = strtok( pcSSLChannelInfo, ",");

			while( pszArgPtr != NULL && iArgIndex < 8)
			{
				sArguments[iArgIndex] = pszArgPtr;

				iArgIndex++;

				pszArgPtr = strtok( NULL, ",");

				while( pszArgPtr != NULL && *pszArgPtr == ' ' && *pszArgPtr != '\0')
				{
					pszArgPtr++;
				}
			}

			m_nStatus = m_pTransport->setTransportProperty( SECURE_PROPERTIES, (const char *) &sArguments);
		}

        // Engine initialization
        m_pAxisEngine = new ClientAxisEngine ();
        if (!m_pAxisEngine) 
        {
        	m_nStatus = AXIS_FAIL;
        }
        m_nStatus = m_pAxisEngine->initialize ();

    }
    catch( AxisException& e)
    {
		char *	pszError = new char[strlen( e.what()) + 1];
		strcpy( pszError, e.what());

		throw AxisGenException( e.getExceptionCode(), const_cast<char*>(pszError));
    }
    catch(...)
    {
        throw;
    }
}

Call::~Call ()
{
    m_pAxisEngine->unInitialize ();
    delete m_pAxisEngine;
    m_pAxisEngine = NULL;
	SOAPTransportFactory::destroyTransportObject(m_pTransport);
	m_pTransport = NULL;
    uninitialize_module();
    if (m_pcEndPointUri)
        delete [] m_pcEndPointUri;  
	m_pcEndPointUri = NULL;
	delete m_pContentIdSet;
	m_pContentIdSet = NULL;

	list<ISoapAttachment*>::iterator it = m_attachments.begin();
	while (it != m_attachments.end())
	{
		delete *it;
		it++;
	}
	m_attachments.clear();
}

int Call::setEndpointURI (const char* pchEndpointURI)
{
    m_pTransport->setEndpointUri(pchEndpointURI);

    return AXIS_SUCCESS;
}

void Call::setOperation (const char* pchOperation, const char* pchNamespace)
{
    m_pIWSSZ->createSoapMethod (pchOperation, pchNamespace);
    m_pAxisEngine->getMessageData()->setOperationName(pchOperation);
}

void Call::addParameter (void* pValue, const char* pchName, XSDTYPE nType)
{
     m_nStatus = m_pIWSSZ->addOutputParam (pchName, pValue, nType);
}

/*
 * Method used to add arrays of basic types as parameters
 */
void Call::addBasicArrayParameter (Axis_Array* pArray, XSDTYPE nType,
    const AxisChar* pName)
{
    m_nStatus = m_pIWSSZ->addOutputBasicArrayParam (pArray, nType, pName);
}

void Call::addCmplxArrayParameter (Axis_Array* pArray, void* pSZFunct,
    void* pDelFunct, void* pSizeFunct, const AxisChar* pName, 
    const AxisChar* pNamespace)
{
     m_nStatus = m_pIWSSZ->addOutputCmplxArrayParam (pArray, pSZFunct, pDelFunct, pSizeFunct,
        pName, pNamespace);
}

void Call::addCmplxParameter (void* pObject, void* pSZFunct, void* pDelFunct,
    const AxisChar* pName, const AxisChar* pNamespace)
{
     m_nStatus = m_pIWSSZ->addOutputCmplxParam (pObject, pSZFunct, pDelFunct, pName,
        pNamespace);
}

int Call::invoke ()
{
        m_nStatus =  m_pAxisEngine->process(m_pTransport);

    return m_nStatus;
}

class HandlerProperty
{
public:
	HandlerProperty(AxisChar *n, void *v, int l) {name=n; value=v; len=l;}

	// The storage pointed at by name and value is not owned by HandlerProperty
	AxisChar *name;
	void *value;
	int len;
};

int Call::initialize(PROVIDERTYPE nStyle)
/* Does this mean that the stub that uses this Call object as well as all 
 * client side handlers have the same PROVIDERTYPE ? 
 */
{
    m_bCallInitialized = true;

    /* Initialize re-usable objects of this instance (objects may have been 
     * populated by the previous call.)
     */
    try
    {
        m_nStatus = AXIS_SUCCESS;
            MessageData *msgData = m_pAxisEngine->getMessageData ();
            if (msgData)
            {
				list<void*>::iterator it = m_handlerProperties.begin();
				while (it != m_handlerProperties.end())
				{
					HandlerProperty *pHp = (HandlerProperty*)(*it);
					msgData->setProperty(pHp->name, pHp->value, pHp->len);
					delete pHp;
					it++;
				}
				m_handlerProperties.clear();

                msgData->getSoapSerializer ((IWrapperSoapSerializer**) 
                    (&m_pIWSSZ));
                msgData->getSoapDeSerializer ((IWrapperSoapDeSerializer**) 
                    (&m_pIWSDZ));
                if (m_pIWSSZ && m_pIWSDZ)
                {
                    m_pIWSSZ->setCurrentProviderType (nStyle);
                    m_pIWSDZ->setCurrentProviderType (nStyle);

                    m_pIWSSZ->reset();
                    m_pIWSDZ->init();
                    
                    switch (nStyle)
                    {
                        case C_RPC_PROVIDER:
                        case CPP_RPC_PROVIDER:
                            m_pIWSSZ->setStyle (RPC_ENCODED);
                            m_pIWSDZ->setStyle (RPC_ENCODED);
                            break;
                        case C_DOC_PROVIDER:
                        case CPP_DOC_PROVIDER:
                            m_pIWSSZ->setStyle (DOC_LITERAL);
                            m_pIWSDZ->setStyle (DOC_LITERAL);
                            break;
                        case COM_PROVIDER:
                            // TODO: ??
                            break;
                        default:;
                                //TODO: ??
                    }
                    if(m_pchSessionID)
                    {
                        msgData->setProperty("sessionid", m_pchSessionID);
                    }

					m_pIWSSZ->setContentIdSet(m_pContentIdSet);
					list<ISoapAttachment*>::iterator itAtt = m_attachments.begin();
					while (itAtt != m_attachments.end())
					{
						m_pIWSSZ->addAttachment((*itAtt)->getHeader(AXIS_CONTENT_ID),*itAtt);
						itAtt++;
					}
					m_attachments.clear();
                    return AXIS_SUCCESS;
                }
            }
            m_nStatus = AXIS_FAIL;
            return AXIS_FAIL;
        m_nStatus = AXIS_FAIL;        
        return AXIS_FAIL;
    }
    catch (AxisException& e)
    {
        e = e;
        m_nStatus = AXIS_FAIL;
        throw;
    }
    catch (...)
    {
        m_nStatus = AXIS_FAIL;        
        throw;
    }
}

int Call::unInitialize ()
{
    m_bCallInitialized = false;
    if (m_pAxisEngine)
    {
		//Initialization,serialization, invokation or check message success 
		if ( m_nStatus == AXIS_SUCCESS &&  m_pIWSDZ != NULL ) 
		{
			// Test if deserialization failed 
			m_nStatus = m_pIWSDZ->getStatus();
		}
		MessageData *msgData = m_pAxisEngine->getMessageData();	
        AxisChar * pachTemp = (AxisChar *)msgData->getProperty("sessionid");
        int len = strlen(pachTemp);
        if ( len > 0 ) // Samisa: check if there is a session key
        {
            if (m_pchSessionID) // Samisa: deallocate before allocation
            {
                delete [] m_pchSessionID;
                m_pchSessionID = NULL;
            }
            m_pchSessionID = new char[len + 1];  // Samisa: should have space for terminating char
            strcpy(m_pchSessionID, pachTemp);
        }
        else //Samisa: there is no session key
        {
            if (m_pchSessionID) 
                delete [] m_pchSessionID;
            m_pchSessionID = NULL;
        }
    }
    closeConnection ();
    return AXIS_SUCCESS;
}

int Call::setProtocol (AXIS_PROTOCOL_TYPE protocol)
{
	int success = AXIS_FAIL;
    if (m_pTransport)
    {
    	if( m_pTransport->setProtocol(protocol)==AXIS_SUCCESS)
    	{
    		m_nTransportType=protocol;
    		success = AXIS_SUCCESS;
    	}
    }
    else
    {
    		m_nTransportType=protocol;
    		success = AXIS_SUCCESS;
    }
   	return success;
}

AXIS_PROTOCOL_TYPE Call::getProtocol ()
{
	if(m_pTransport)
	{
		return m_pTransport->getProtocol();
	}
	else
	{
		return m_nTransportType;
	}
}

int Call::setTransportProperty( AXIS_TRANSPORT_INFORMATION_TYPE type, const char* value)
{
	int	iSuccess = AXIS_SUCCESS;

    // Samisa - if SOAPAction is being set add extra "" to value
    if (type == SOAPACTION_HEADER)
    {
        char* tempvalue = new char[strlen(value) + 3];
        sprintf( tempvalue, "\"%s\"", value);
        m_pTransport->setTransportProperty(type, tempvalue);
        delete [] tempvalue;
    }
    else
	{
		try
		{
			iSuccess = m_pTransport->setTransportProperty( type, value);
		}
		catch( AxisException& e)
		{
			char *	pszError = new char[strlen( e.what()) + 1];
			strcpy( pszError, e.what());

			throw AxisGenException(e.getExceptionCode(), const_cast<char*>(pszError));
		}
	}

	if( iSuccess < 0)
	{
		string	sError = m_pTransport->getLastChannelError();
		char *	pszError = new char[sError.length() + 1];
		strcpy( pszError, sError.c_str());
		throw AxisGenException( -iSuccess, const_cast<char*>(pszError));
	}

    return iSuccess;
}

const char* Call::getTransportProperty(const char *key, bool response) 
{
	if (m_pTransport) return m_pTransport->getTransportProperty(key,response);
	else return NULL;
}

int Call::setHandlerProperty(AxisChar* name, void* value, int len)
{
	// Unfortunately we have to cache the handler properties here
	// in the Call object since the m_pMsgData is not set up
	// until Call::initialize which doesn't happen until the actual
	// web service is invoked.
	m_handlerProperties.push_back(new HandlerProperty(name,value,len));
	return AXIS_SUCCESS;
}

/*
 * This method closes the connection of this object to the server
 */
void Call::closeConnection()
{
	if (m_pTransport) {
        m_pTransport->closeConnection();
	}
}

void Call::setSOAPVersion (SOAP_VERSION version)
{
    m_pIWSSZ->setSoapVersion (version);
}

Axis_Array Call::getBasicArray (XSDTYPE nType, const AxisChar* pName,
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getBasicArray (nType, pName, pNamespace);
}

Axis_Array Call::getCmplxArray (void* pDZFunct, void* pCreFunct, 
    void* pDelFunct, void* pSizeFunct, const AxisChar* pName, 
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getCmplxArray (pDZFunct, pCreFunct, pDelFunct, pSizeFunct,
        pName, pNamespace);
}

xsd__int * Call::getElementAsInt (const AxisChar* pName, const AxisChar* pNamespace)
{
    return m_pIWSDZ->getElementAsInt (pName, pNamespace);
}

xsd__boolean * Call::getElementAsBoolean (const AxisChar* pName, 
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getElementAsBoolean (pName, pNamespace);
}

xsd__unsignedInt * Call::getElementAsUnsignedInt (const AxisChar* pName,
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getElementAsUnsignedInt (pName, pNamespace);
}

xsd__short * Call::getElementAsShort (const AxisChar* pName, 
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getElementAsShort (pName, pNamespace);
}

xsd__unsignedShort * Call::getElementAsUnsignedShort (const AxisChar* pName,
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getElementAsUnsignedShort (pName, pNamespace);
}

xsd__byte * Call::getElementAsByte (const AxisChar* pName, const AxisChar* pNamespace)
{
    return m_pIWSDZ->getElementAsByte (pName, pNamespace);
}

xsd__unsignedByte * Call::getElementAsUnsignedByte (const AxisChar* pName,
    const AxisChar * pNamespace)
{
    return m_pIWSDZ->getElementAsUnsignedByte (pName, pNamespace);
}

xsd__long * Call::getElementAsLong (const AxisChar* pName, const AxisChar* pNamespace)
{
    return m_pIWSDZ->getElementAsLong (pName, pNamespace);
}

xsd__integer * Call::getElementAsInteger (const AxisChar* pName, 
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getElementAsInteger (pName, pNamespace);
}

xsd__unsignedLong * Call::getElementAsUnsignedLong (const AxisChar* pName,
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getElementAsUnsignedLong (pName, pNamespace);
}

xsd__float * Call::getElementAsFloat (const AxisChar* pName, 
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getElementAsFloat (pName, pNamespace);
}

xsd__double * Call::getElementAsDouble (const AxisChar* pName, 
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getElementAsDouble (pName, pNamespace);
}

xsd__decimal * Call::getElementAsDecimal (const AxisChar* pName, 
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getElementAsDecimal (pName, pNamespace);
}

xsd__string Call::getElementAsString (const AxisChar* pName, 
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getElementAsString (pName, pNamespace);
}

xsd__anyURI Call::getElementAsAnyURI (const AxisChar* pName, 
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getElementAsAnyURI (pName, pNamespace);
}

xsd__QName Call::getElementAsQName (const AxisChar* pName, 
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getElementAsQName (pName, pNamespace);
}

xsd__hexBinary * Call::getElementAsHexBinary (const AxisChar* pName, 
    const AxisChar * pNamespace)
{
    return m_pIWSDZ->getElementAsHexBinary (pName, pNamespace);
}

xsd__base64Binary * Call::getElementAsBase64Binary (const AxisChar* pName,
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getElementAsBase64Binary (pName, pNamespace);
}

xsd__dateTime * Call::getElementAsDateTime (const AxisChar* pName,
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getElementAsDateTime (pName, pNamespace);
}

xsd__date * Call::getElementAsDate (const AxisChar* pName, 
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getElementAsDate (pName, pNamespace);
}

xsd__time * Call::getElementAsTime (const AxisChar* pName, 
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getElementAsTime (pName, pNamespace);
}

xsd__duration * Call::getElementAsDuration (const AxisChar* pName, 
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getElementAsDuration (pName, pNamespace);
}

xsd__gYearMonth * Call::getElementAsGYearMonth (const AxisChar* pName, 
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getElementAsGYearMonth (pName, pNamespace);
}

xsd__gYear * Call::getElementAsGYear (const AxisChar* pName, 
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getElementAsGYear (pName, pNamespace);
}

xsd__gMonthDay * Call::getElementAsGMonthDay (const AxisChar* pName, 
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getElementAsGMonthDay (pName, pNamespace);
}

xsd__gDay * Call::getElementAsGDay (const AxisChar* pName, 
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getElementAsGDay (pName, pNamespace);
}

xsd__gMonth * Call::getElementAsGMonth (const AxisChar* pName, 
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getElementAsGMonth (pName, pNamespace);
}

xsd__nonPositiveInteger * Call::getElementAsNonPositiveInteger (const AxisChar* pName, 
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getElementAsNonPositiveInteger (pName, pNamespace);
}

xsd__negativeInteger * Call::getElementAsNegativeInteger (const AxisChar* pName, 
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getElementAsNegativeInteger (pName, pNamespace);
}

xsd__nonNegativeInteger * Call::getElementAsNonNegativeInteger (const AxisChar* pName, 
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getElementAsNonNegativeInteger (pName, pNamespace);
}

xsd__positiveInteger * Call::getElementAsPositiveInteger (const AxisChar* pName, 
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getElementAsPositiveInteger (pName, pNamespace);
}

xsd__normalizedString Call::getElementAsNormalizedString (const AxisChar* pName, 
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getElementAsNormalizedString (pName, pNamespace);
}

xsd__token Call::getElementAsToken (const AxisChar* pName, 
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getElementAsToken (pName, pNamespace);
}

xsd__language Call::getElementAsLanguage (const AxisChar* pName, 
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getElementAsLanguage (pName, pNamespace);
}

xsd__Name Call::getElementAsName (const AxisChar* pName, 
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getElementAsName (pName, pNamespace);
}

xsd__NCName Call::getElementAsNCName (const AxisChar* pName, 
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getElementAsNCName (pName, pNamespace);
}

xsd__ID Call::getElementAsID (const AxisChar* pName, 
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getElementAsID (pName, pNamespace);
}

xsd__IDREF Call::getElementAsIDREF (const AxisChar* pName, 
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getElementAsIDREF (pName, pNamespace);
}

xsd__IDREFS Call::getElementAsIDREFS (const AxisChar* pName, 
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getElementAsIDREFS (pName, pNamespace);
}

xsd__ENTITY Call::getElementAsENTITY (const AxisChar* pName, 
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getElementAsENTITY (pName, pNamespace);
}

xsd__ENTITIES Call::getElementAsENTITIES (const AxisChar* pName, 
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getElementAsENTITIES (pName, pNamespace);
}

xsd__NMTOKEN Call::getElementAsNMTOKEN (const AxisChar* pName, 
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getElementAsNMTOKEN (pName, pNamespace);
}

xsd__NMTOKENS Call::getElementAsNMTOKENS (const AxisChar* pName, 
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getElementAsNMTOKENS (pName, pNamespace);
}

xsd__NOTATION Call::getElementAsNOTATION (const AxisChar* pName, 
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getElementAsNOTATION (pName, pNamespace);
}

xsd__int * Call::getAttributeAsInt (const AxisChar* pName, const AxisChar* pNamespace)
{
    return m_pIWSDZ->getAttributeAsInt (pName, pNamespace);
}

xsd__boolean * Call::getAttributeAsBoolean (const AxisChar* pName,
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getAttributeAsBoolean (pName, pNamespace);
}

xsd__unsignedInt * Call::getAttributeAsUnsignedInt (const AxisChar* pName,
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getAttributeAsUnsignedInt (pName, pNamespace);
}

xsd__short * Call::getAttributeAsShort (const AxisChar* pName, 
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getAttributeAsShort (pName, pNamespace);
}

xsd__unsignedShort * Call::getAttributeAsUnsignedShort (const AxisChar* pName,
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getAttributeAsUnsignedShort (pName, pNamespace);
}

xsd__byte * Call::getAttributeAsByte (const AxisChar* pName, 
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getAttributeAsByte (pName, pNamespace);
}

xsd__unsignedByte * Call::getAttributeAsUnsignedByte (const AxisChar* pName,
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getAttributeAsUnsignedByte (pName, pNamespace);
}

xsd__long * Call::getAttributeAsLong (const AxisChar* pName, 
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getAttributeAsLong (pName, pNamespace);
}

xsd__integer * Call::getAttributeAsInteger (const AxisChar* pName,
    const AxisChar * pNamespace)
{
    return m_pIWSDZ->getAttributeAsInteger (pName, pNamespace);
}

xsd__unsignedLong * Call::getAttributeAsUnsignedLong (const AxisChar* pName,
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getAttributeAsUnsignedLong (pName, pNamespace);
}

xsd__float * Call::getAttributeAsFloat (const AxisChar* pName,
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getAttributeAsFloat (pName, pNamespace);
}

xsd__double * Call::getAttributeAsDouble (const AxisChar* pName,
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getAttributeAsDouble (pName, pNamespace);
}

xsd__decimal * Call::getAttributeAsDecimal (const AxisChar* pName,
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getAttributeAsDecimal (pName, pNamespace);
}

xsd__string Call::getAttributeAsString (const AxisChar* pName,
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getAttributeAsString (pName, pNamespace);
}

xsd__anyURI Call::getAttributeAsAnyURI (const AxisChar* pName,
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getAttributeAsAnyURI (pName, pNamespace);
}

xsd__QName Call::getAttributeAsQName (const AxisChar* pName,
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getAttributeAsQName (pName, pNamespace);
}

xsd__hexBinary * Call::getAttributeAsHexBinary (const AxisChar* pName,
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getAttributeAsHexBinary (pName, pNamespace);
}

xsd__base64Binary * Call::getAttributeAsBase64Binary (const AxisChar* pName,
    const AxisChar * pNamespace)
{
    return m_pIWSDZ->getAttributeAsBase64Binary (pName, pNamespace);
}

xsd__dateTime * Call::getAttributeAsDateTime (const AxisChar* pName,
    const AxisChar * pNamespace)
{
    return m_pIWSDZ->getAttributeAsDateTime (pName, pNamespace);
}

xsd__date * Call::getAttributeAsDate (const AxisChar* pName, 
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getAttributeAsDate (pName, pNamespace);
}

xsd__time * Call::getAttributeAsTime (const AxisChar* pName, 
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getAttributeAsTime (pName, pNamespace);
}

xsd__duration * Call::getAttributeAsDuration (const AxisChar* pName,
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getAttributeAsDuration (pName, pNamespace);
}

xsd__gYearMonth * Call::getAttributeAsGYearMonth (const AxisChar* pName,
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getAttributeAsGYearMonth (pName, pNamespace);
}

xsd__gYear * Call::getAttributeAsGYear (const AxisChar* pName,
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getAttributeAsGYear (pName, pNamespace);
}

xsd__gMonthDay * Call::getAttributeAsGMonthDay (const AxisChar* pName,
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getAttributeAsGMonthDay (pName, pNamespace);
}

xsd__gDay * Call::getAttributeAsGDay (const AxisChar* pName,
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getAttributeAsGDay (pName, pNamespace);
}

xsd__gMonth * Call::getAttributeAsGMonth (const AxisChar* pName,
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getAttributeAsGMonth (pName, pNamespace);
}

xsd__NOTATION Call::getAttributeAsNOTATION (const AxisChar* pName,
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getAttributeAsNOTATION (pName, pNamespace);
}

xsd__normalizedString Call::getAttributeAsNormalizedString (const AxisChar* pName,
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getAttributeAsNormalizedString (pName, pNamespace);
}

xsd__token Call::getAttributeAsToken (const AxisChar* pName,
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getAttributeAsToken (pName, pNamespace);
}

xsd__language Call::getAttributeAsLanguage (const AxisChar* pName,
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getAttributeAsLanguage (pName, pNamespace);
}

xsd__Name Call::getAttributeAsName (const AxisChar* pName,
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getAttributeAsName (pName, pNamespace);
}

xsd__NCName Call::getAttributeAsNCName (const AxisChar* pName,
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getAttributeAsNCName (pName, pNamespace);
}

xsd__ID Call::getAttributeAsID (const AxisChar* pName,
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getAttributeAsID (pName, pNamespace);
}

xsd__IDREF Call::getAttributeAsIDREF (const AxisChar* pName,
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getAttributeAsIDREF (pName, pNamespace);
}

xsd__IDREFS Call::getAttributeAsIDREFS (const AxisChar* pName,
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getAttributeAsIDREFS (pName, pNamespace);
}

xsd__ENTITY Call::getAttributeAsENTITY (const AxisChar* pName,
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getAttributeAsENTITY (pName, pNamespace);
}

xsd__ENTITIES Call::getAttributeAsENTITIES (const AxisChar* pName,
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getAttributeAsENTITIES (pName, pNamespace);
}

xsd__NMTOKEN Call::getAttributeAsNMTOKEN (const AxisChar* pName,
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getAttributeAsNMTOKEN (pName, pNamespace);
}

xsd__NMTOKENS Call::getAttributeAsNMTOKENS (const AxisChar* pName,
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getAttributeAsNMTOKENS (pName, pNamespace);
}

xsd__nonPositiveInteger * Call::getAttributeAsNonPositiveInteger (const AxisChar* pName,
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getAttributeAsNonPositiveInteger (pName, pNamespace);
}

xsd__negativeInteger * Call::getAttributeAsNegativeInteger (const AxisChar* pName,
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getAttributeAsNegativeInteger (pName, pNamespace);
}

xsd__nonNegativeInteger * Call::getAttributeAsNonNegativeInteger (const AxisChar* pName,
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getAttributeAsNonNegativeInteger (pName, pNamespace);
}

xsd__positiveInteger * Call::getAttributeAsPositiveInteger (const AxisChar* pName,
    const AxisChar* pNamespace)
{
    return m_pIWSDZ->getAttributeAsPositiveInteger (pName, pNamespace);
}

int Call::checkMessage (const AxisChar* pName, const AxisChar* pNamespace)
{
	 m_nStatus = m_pIWSDZ->checkMessageBody (pName, pNamespace);
    return m_nStatus;
}

void* Call::checkFault (const AxisChar* pName, const AxisChar* pNamespace)
{
	 return m_pIWSDZ->checkForFault (pName, pNamespace);
}

void* Call::getCmplxObject (void* pDZFunct, void* pCreFunct, void* pDelFunct,
    const AxisChar* pName, const AxisChar* pNamespace)
{
    return m_pIWSDZ->getCmplxObject (pDZFunct, pCreFunct, pDelFunct, pName,
        pNamespace);
}

IHeaderBlock* Call::createHeaderBlock ()
{
    return (m_pIWSSZ->createHeaderBlock ());
}

IHeaderBlock* Call::createHeaderBlock(AxisChar* pachLocalName, 
                                       AxisChar* pachUri)
{
 	return new HeaderBlock(pachLocalName, pachUri);
}

IHeaderBlock* Call::createHeaderBlock(AxisChar* pachLocalName, 
                                       AxisChar* pachUri, AxisChar* pachPrefix)
{
 	return new HeaderBlock(pachLocalName, pachUri, pachPrefix);
}

int Call::getStatus() 
{
	return m_nStatus;
}

void Call::setProxy(const char* pcProxyHost, unsigned int uiProxyPort)
{
    m_strProxyHost = pcProxyHost;
    m_uiProxyPort = uiProxyPort;
    m_bUseProxy = true;    
}

AnyType* Call::getAnyObject()
{
	return m_pIWSDZ->getAnyObject();
}

int Call::addAnyObject(AnyType* pAnyObject)
{
	return m_pIWSSZ->addOutputAnyObject(pAnyObject);
}

const AxisChar* Call::getNamespacePrefix(const AxisChar* pNamespace)
{
    return m_pIWSSZ->getNamespacePrefix(pNamespace);
}

void Call::setSOAPMethodAttribute(const AxisChar *pLocalname, const AxisChar *pPrefix, const AxisChar *pValue)
{
    setSOAPMethodAttribute(pLocalname,pPrefix,NULL,pValue);
}

void Call::setSOAPMethodAttribute(const AxisChar *pLocalname, const AxisChar *pPrefix, 
                                  const AxisChar *pUri, const AxisChar *pValue)
{
    IAttribute* pAttribute;
	if (pValue==NULL) pValue = "";
    if (NULL!=pUri)
    {
        std::list<Attribute*> attributeList;
        pAttribute = new Attribute(attributeList, pLocalname, pPrefix, pUri, pValue);
    }
    else
    {
        std::list<Attribute*> attributeList;
        pAttribute = new Attribute(attributeList, pLocalname, pPrefix, pValue);
    }
    m_pIWSSZ->setSOAPMethodAttribute(((Attribute*)pAttribute)->clone());
}

const xsd__string Call::getFaultAsXMLString()
{
	return m_pIWSDZ->getFaultAsXMLString();
}

void Call::addAttachment(ISoapAttachment* att)
{
	if (NULL==m_pIWSSZ)
		m_attachments.push_back(att);
	else
		m_pIWSSZ->addAttachment(att->getHeader(AXIS_CONTENT_ID),att);
}

ISoapAttachment* Call::createSoapAttachment()
{
	return new SoapAttachment(m_pContentIdSet);
}

void Call::addAttachmentParameter(ISoapAttachment* att, const char* pName, IAttribute **attributes, int nAttributes)
{
	m_pIWSSZ->addAttachmentParameter(att,pName,attributes,nAttributes);
}

IAttribute* Call::createAttribute(const AxisChar *pLocalname, const AxisChar *pPrefix, const AxisChar *pValue)
{
    std::list<Attribute*> attributeList;
    return new Attribute(attributeList, pLocalname, pPrefix, pValue);
}
