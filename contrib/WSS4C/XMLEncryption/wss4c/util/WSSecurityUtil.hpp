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

#if !defined(_WSSECURITYUTIL_H_INCLUDED_)
#define _WSSECURITYUTIL_H_INCLUDED_

WSS4C_UTIL_NAMESPACE_BEGIN

class WSSecurityUtil
{
public:
	WSSecurityUtil();
	virtual ~WSSecurityUtil();

	/**
	 *	Returns the first WS-Security header element for a given actor.
	 *	Only one WS-Security header is allowed for an actor.
	 *	@return the wsse:Security element or null if no such element was found
	 */
	static DOMElement* getSecurityHeader(WSSConfig* cnfg, DOMDocument* doc, 
			string actor, SOAPConstants* sc);
	/**
	 *	Compare two actor strings and returns true if these are equal.
	 *	Takes care of null length strings and uses ignore case.
	 */
	static bool isActorEqual(string actor, string hActor);
	/**
	 *	Get direct child with specified child name localname and namespace.
	 */
	static DOMNode* getDirectChild(DOMNode* fNode, string localName, string nmspace);
	/**
	 *	Get a direct child with specified localname and one of the WSSE namespaces.
	 */
	static DOMNode* getDirectChildWSSE(DOMNode* fNode, string localName);

	static string getAttributeValueWSU(DOMElement* element, string attrName, string wsuNmspace);

	static SOAPConstants* getSoapConstants(DOMElement* startElement);

	/**	Set the namespace if it's not already set. */
	static string setNamespace(DOMElement* element, string ns, string prefix);

	static string getPrefixNS(string uri, DOMNode* e);

	/** Find the first ws-security header block */
	static DOMElement* findWsseSecurityHeaderBlock(WSSConfig* wsscnfg, DOMDocument* doc,
		DOMElement* envelope, bool doCreate);
	/** Find the first ws-security header block for a given actor */
	static DOMElement* findWsseSecurityHeaderBlock(WSSConfig* wsscnfg, DOMDocument* doc, 
		DOMElement* envelope, string actor, bool doCreate);

	/**	prepand a child element	 */
	static DOMElement* prependChildElement(DOMDocument* doc, DOMElement* parent, 
		DOMElement* child, bool addWhitespaces);

private:
	/** create a new element in the same namespace */
	static DOMElement* createElementInNamespace(DOMElement* parent, string localName);

	/** find a child element with given namespace and local name */
	static DOMElement* findChildElement(DOMElement* parent, string namespaceUri, 
		string localName);
};

WSS4C_UTIL_NAMESPACE_END

#endif // !defined(_WSSECURITYUTIL_H_INCLUDED_)
