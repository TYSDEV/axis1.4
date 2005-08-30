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

#include <wss4c/wssenc.hpp>
#include <wss4c/wss4c_utils/WssString.hpp>
#include <wss4c/WSConstants.hpp>
#include "SOAP12Constants.hpp"

WSS4C_NAMESPACE_BEGIN

QName* SOAP12Constants::headerQName;
QName* SOAP12Constants::bodyQName;
QName* SOAP12Constants::roleQName;

const std::string SOAP12Constants::PROP_WEBMETHOD = "soap12.webmethod";

string SOAP12Constants::getEnvelopeUri()
{
	return WSCONST_URI_SOAP12_ENV;
}

QName* SOAP12Constants::getHeaderQName()
{
	return SOAP12Constants::headerQName;
}

QName* SOAP12Constants::getBodyQName()
{
	return SOAP12Constants::bodyQName;
}

QName* SOAP12Constants::getRoleAttributeQName()
{
	return SOAP12Constants::roleQName;
}

string SOAP12Constants::getNextRoleUri()
{
	return WSCONST_URI_SOAP12_NEXT_ROLE;
}

string SOAP12Constants::getMustUnderstand()
{
	return "true";
}

//////////////////////////////////////////////////////////////////////
// Construction/Destruction
//////////////////////////////////////////////////////////////////////

SOAP12Constants::SOAP12Constants()
{
	WssString soapUri(WSCONST_URI_SOAP12_ENV);
	
	SOAP12Constants::headerQName = new QName(
				soapUri.toXmlChArray(), 
				WssString(WSCONST_ELEM_HEADER).toXmlChArray(),
				0);
	SOAP12Constants::bodyQName   = new QName(
				soapUri.toXmlChArray(), 
				WssString(WSCONST_ELEM_BODY).toXmlChArray(),
				0);
	SOAP12Constants::roleQName   = new QName(
				soapUri.toXmlChArray(), 
				WssString(WSCONST_ATTR_ACTOR).toXmlChArray(),
				0);
}

SOAP12Constants::~SOAP12Constants()
{
}

WSS4C_NAMESPACE_END