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
#include <time.h>
#include <wss4c/wssenc.hpp>
#include <wss4c/SOAPConstants.hpp>
#include <wss4c/WSConstants.hpp>
#include <wss4c/WSSConfig.hpp>
#include <wss4c/util/WSSecurityUtil.hpp>

#include "WSBaseMessage.hpp"
#include "../wss4c_utils/WssString.hpp"

WSS4C_UTIL_NAMESPACE_USE
WSS4C_MESSAGE_NAMESPACE_BEGIN

void WSBaseMessage::setActor(string act)
{
	actor = act;
}

void WSBaseMessage::setTimeToLive(int ttl)
{
	timeToLive = ttl;
}

void WSBaseMessage::setParts(void* pts)
{
	parts = pts;
}

void WSBaseMessage::setMustUnderstand(bool mu)
{
	mustUnderstand = mu;
}
	
void WSBaseMessage::setKeyIdentifierType(WSConstants::SubjectKeyIdentifier keyIdType)
{
	keyIdentifierType = keyIdType;
}

WSConstants::SubjectKeyIdentifier WSBaseMessage::getKeyIdentifierType()
{
	return keyIdentifierType;
}

string WSBaseMessage::setBodyId(DOMDocument* doc)
{
	SOAPConstants* soapConst = WSSecurityUtil::getSoapConstants(doc->getDocumentElement());
	DOMElement* bodyElement = (DOMElement*)WSSecurityUtil::getDirectChild(
								doc->getFirstChild(), 
								WssString(soapConst->getBodyQName()->getLocalPart()).toStdString(),
								soapConst->getEnvelopeUri());
	if ( !bodyElement )
		throw "SOAP Body Element node not found";
	
	return setWsuId(bodyElement);
}

string WSBaseMessage::setWsuId(DOMElement* bodyElement)
{
	// string id = "";
	WssString id;

	XMLCh* xmlChId = XMLString::transcode("Id");;
	XMLCh* wsuNs = XMLString::transcode(wssConfig->getWsuNS().c_str());

	// Try to get a differently qualified id in case it was created with an
	// older spec namespace.
	if (wssConfig->getProcessNonCompliantMessages())
		id = WSSecurityUtil::getAttributeValueWSU(bodyElement, "Id", "");
	
	if (wssConfig->getProcessNonCompliantMessages() || 
		!wssConfig->isTargetIdQualified())
	{
		if (id.isEmpty())
			id = bodyElement->getAttribute(xmlChId);
	}
	else
		id = bodyElement->getAttributeNS(wsuNs, xmlChId);

	if (id.isEmpty())
	{
		char buf[6];
		time_t t;
		time(&t);
		srand((unsigned int)t);
		sprintf(buf, "id-%i", rand());
		id = buf;
				// NOTE: WSS4J uses bodyElement.hashCode() for generating this id.
		if (wssConfig->isTargetIdQualified())
		{
			string prefix = WSSecurityUtil::setNamespace(bodyElement, 
								wssConfig->getWsuNS(), WSCONST_WSU_PREFIX);
	
			string pre = prefix + ":Id";
			// TODO: These two mighT cause an exception.
			XMLString::release(&xmlChId);
			xmlChId = XMLString::transcode(pre.c_str());
			
			bodyElement->setAttributeNS(wsuNs, xmlChId, id.toXmlChArray());
		}
		else
		{
			bodyElement->setAttributeNS(NULL, xmlChId, id.toXmlChArray());
		}
		XMLString::release(&wsuNs);
		XMLString::release(&xmlChId);
	}
	return id.toStdString();
}

void WSBaseMessage::setUserInfo(string usrname, string pswrd)
{
	user = usrname;
	password = pswrd;
}

DOMElement* WSBaseMessage::insertSecurityHeader(DOMDocument* doc)
{
	SOAPConstants* soapCnsts = WSSecurityUtil::getSoapConstants(doc->getDocumentElement());
	// Look up a security header block that matches the actor
	DOMElement* securityHeader = WSSecurityUtil::getSecurityHeader(wssConfig, doc, actor, soapCnsts);
	if (securityHeader == NULL) // create if nothing found
	{
		securityHeader = 
			WSSecurityUtil::findWsseSecurityHeaderBlock(wssConfig, doc, 
					doc->getDocumentElement(), actor, true);

		string soapPrefix = WSSecurityUtil::getPrefixNS(soapCnsts->getEnvelopeUri(), securityHeader);
		string newSoapPrfx = soapPrefix 
					+ ":" + 
					WssString(soapCnsts->getRoleAttributeQName()->getLocalPart()).toStdString();

		XMLCh* envUri = XMLString::transcode(soapCnsts->getEnvelopeUri().c_str());
		XMLCh* xSoapPrfx = XMLString::transcode(newSoapPrfx.c_str());
		XMLCh* tmp = XMLString::transcode(actor.c_str());

		if ( !actor.empty() )
		{
			// Check for SOAP 1.2 here and use "role" instead of "actor"
			securityHeader->setAttributeNS(envUri, xSoapPrfx, tmp);
		}

		if (mustUnderstand)
		{
			XMLString::release(&xSoapPrfx);
			XMLString::release(&tmp);
			
			newSoapPrfx = soapPrefix + ":" WSCONST_ATTR_MUST_UNDERSTAND;
			xSoapPrfx = XMLString::transcode(newSoapPrfx.c_str());
			tmp = XMLString::transcode(soapCnsts->getMustUnderstand().c_str());
			
			securityHeader->setAttributeNS(envUri, xSoapPrfx, tmp);
		}

		XMLString::release(&envUri);
		XMLString::release(&xSoapPrfx);
		XMLString::release(&tmp);
		}

	return securityHeader;
}

//////////////////////////////////////////////////////////////////////
// Construction/Destruction
//////////////////////////////////////////////////////////////////////
void WSBaseMessage::init()
{
	mustUnderstand = true;
	actor = "";
	user = "";
	password = "";
	keyIdentifierType = WSConstants::ISSUSER_SERIAL;
	timeToLive = 300;
	wssConfig = WSSConfig::getDefaultWSConfig();
}

WSBaseMessage::WSBaseMessage()
{
	init();
}

// Parameter actor - The actor name of the wsse:Security header
WSBaseMessage::WSBaseMessage(string actor)
{
	init();
	setActor(actor);
}

WSBaseMessage::WSBaseMessage(WSSConfig* wssCnfg, string actor, bool mu)
{
	wssConfig = wssCnfg;
	setActor(actor);
	setMustUnderstand(mu);
}

WSBaseMessage::WSBaseMessage(string actor, bool mu)
{
	wssConfig = WSSConfig::getDefaultWSConfig();
	setActor(actor);
	setMustUnderstand(mu);
}

WSBaseMessage::~WSBaseMessage()
{
}

WSS4C_MESSAGE_NAMESPACE_END