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

#if !defined(_SOAPCONSTANTS_H_INCLUDED_)
#define _SOAPCONSTANTS_H_INCLUDED_

WSS4C_NAMESPACE_BEGIN

/**
 *	An interface definining SOAP constants. This allows various parts of the
 *	engine to avoid hardcoding dependence on a particular SOAP version and its
 *	associated URIs, etc.
 * 
 */
class SOAPConstants
{
public:
	SOAPConstants();
	virtual ~SOAPConstants();

	/**	Releases all resources associated with this class. */
	virtual void release();

	/** Obtain the envelope namespace for this version of SOAP */
	virtual string getEnvelopeUri() = 0;
	
	/** Obtain the QName for the Header element */
	virtual QName* getHeaderQName() = 0;

	/**	Obtain the QName for the Body element */ 
	virtual QName* getBodyQName() = 0;

	/** Obtain the QName for the role attribute (actor/role) */
	virtual	QName* getRoleAttributeQName() = 0;

	/** Obtain the "next" role/actor URI */
	virtual string getNextRoleUri() = 0;

	/** Obtain the "next" role/actor URI */
	virtual string getMustUnderstand() = 0;

protected:
	static QName* headerQName;
	static QName* bodyQName;
	static QName* roleQName;

};

WSS4C_NAMESPACE_END

#endif // !defined(_SOAPCONSTANTS_H_INCLUDED_)
