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

#if !defined(_SOAP12CONSTANTS_H_INCLUDED_)
#define _SOAP12CONSTANTS_H_INCLUDED_

#include "SOAPConstants.hpp"

WSS4C_NAMESPACE_BEGIN

class SOAP12Constants : public SOAPConstants
{
public:
	SOAP12Constants();
	virtual ~SOAP12Constants();
	
	string getEnvelopeUri();
	QName* getHeaderQName();
	QName* getBodyQName();
	QName* getRoleAttributeQName();
	string getNextRoleUri();
	string getMustUnderstand();
	
	/** MessageContext property name for webmethod */
	static const string PROP_WEBMETHOD;

private:
	static QName* headerQName;
	static QName* bodyQName;
	static QName* roleQName;
};

WSS4C_NAMESPACE_END

#endif // !defined(_SOAP12CONSTANTS_H_INCLUDED_)
