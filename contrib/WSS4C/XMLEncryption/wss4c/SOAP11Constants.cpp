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
#include <wss4c/WSConstants.hpp>
#include "SOAP11Constants.hpp"

WSS4C_NAMESPACE_BEGIN

string SOAP11Constants::getEnvelopeUri()
{
	return WSCONST_URI_SOAP11_ENV;
}

QName* SOAP11Constants::getHeaderQName()
{
	return SOAP11Constants::headerQName;
}

QName* SOAP11Constants::getBodyQName()
{
	return SOAP11Constants::bodyQName;
}

QName* SOAP11Constants::getRoleAttributeQName()
{
	return SOAP11Constants::roleQName;
}

string SOAPConstants::getNextRoleUri()
{
	return WSCONST_URI_SOAP11_NEXT_ACTOR;
}

string SOAPConstants::getMustUnderstand()
{
	return "1";
}


//////////////////////////////////////////////////////////////////////
// Construction/Destruction
//////////////////////////////////////////////////////////////////////

SOAP11Constants::SOAP11Constants()
{
	SOAP11Constants::headerQName = new QName(XMLString::transcode(WSCONST_URI_SOAP11_ENV), 
			XMLString::transcode(WSCONST_ELEM_HEADER), 0);
	SOAP11Constants::bodyQName	 = new QName(XMLString::transcode(WSCONST_URI_SOAP11_ENV), 
			XMLString::transcode(WSCONST_ELEM_BODY), 0);
	SOAP11Constants::roleQName   = new QName(XMLString::transcode(WSCONST_URI_SOAP11_ENV), 
			XMLString::transcode(WSCONST_ATTR_ACTOR), 0);
}

SOAP11Constants::~SOAP11Constants()
{
}

WSS4C_NAMESPACE_END