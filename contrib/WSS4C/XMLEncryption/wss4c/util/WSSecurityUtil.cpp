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

#include <wss4c/WSSConfig.hpp>
#include <wss4c/SOAPConstants.hpp>
#include <wss4c/SOAP11Constants.hpp>
#include <wss4c/SOAP12Constants.hpp>
#include <wss4c/WSConstants.hpp>

#include "WSSecurityUtil.hpp"
#include "../wss4c_utils/WssString.hpp"

WSS4C_UTIL_NAMESPACE_BEGIN

DOMElement* WSSecurityUtil::getSecurityHeader(WSSConfig* cnfg, DOMDocument *doc, 
													  string actor, SOAPConstants* sc)
{
	DOMElement* soapHdrElement = (DOMElement*)getDirectChild(doc->getFirstChild(), 
									WssString(sc->getHeaderQName()->getLocalPart()).toStdString(),
									sc->getEnvelopeUri());

	if (! soapHdrElement ) // No soap header at all!
	{
		return NULL;
	}
	
	// Get all wsse:Security nodes
	DOMNodeList* lst = NULL;
	int len = 0;
	// Get the wsse namespaces array
	string wsseNsArray[] = WSCONST_WSSE_NS_ARRAY;
	
	XMLCh* ns;
	XMLCh* wsse_ln = XMLString::transcode(WSCONST_WSSE_LN);
	
	if (cnfg->getProcessNonCompliantMessages())
	{
		for (int i=0; len == 0 && i < ARRAYLEN(wsseNsArray); i++)
		{
			ns = XMLString::transcode(wsseNsArray[i].c_str());
			lst = soapHdrElement->getElementsByTagNameNS(ns, wsse_ln);
			len = lst->getLength();
			XMLString::release(&ns);
		}
	}
	else
	{
		ns = XMLString::transcode(cnfg->getWsseNS().c_str());
		lst = soapHdrElement->getElementsByTagNameNS(ns, wsse_ln);
		XMLString::release(&ns);
	}
	XMLString::release(&wsse_ln);
	
	if (! lst )
		return NULL;
	else
		len = lst->getLength();

	DOMElement* elem;
	DOMAttr* attr;
	string hActor;
	for (int i=0; i < len; i++)
	{
		elem = (DOMElement*)lst->item(i);
		attr = elem->getAttributeNodeNS(
				WssString(sc->getEnvelopeUri()).toXmlChArray(), 
				sc->getRoleAttributeQName()->getLocalPart());
		hActor = (attr) ? WssString(attr->getValue()).toStdString() : 0 ;
		if (WSSecurityUtil::isActorEqual(actor, hActor))
			return elem;
	}

	return NULL;
}

bool WSSecurityUtil::isActorEqual(string actor, string hActor)
{
	if ( hActor.empty() && actor.empty() )
	{
		return true;
	}
	else
	{
		if ( stricmp(actor.c_str(), hActor.c_str()) == 0 )
			return true;
		else
			return false;
	}
}

DOMNode* WSSecurityUtil::getDirectChild(DOMNode* fNode, string localName, string nmspace)
{
	XMLCh* xloclaName = XMLString::transcode(localName.c_str());
	XMLCh* xns = XMLString::transcode(nmspace.c_str());
	DOMNode* retval = NULL;

	for (DOMNode* currentChild = fNode->getFirstChild();
		currentChild != NULL;
		currentChild = currentChild->getNextSibling())
	{
		if ( XMLString::compareString(xloclaName, currentChild->getLocalName()) 
			&& XMLString::compareString(xns, currentChild->getNamespaceURI()) )
		{
			retval = currentChild;
			break;
		}
	}

	return retval;
}

DOMNode* WSSecurityUtil::getDirectChildWSSE(DOMNode* fNode, string localName)
{
	DOMNode* child;
	string wsseNsArray[] = WSCONST_WSSE_NS_ARRAY;
	for (int i = 0; 
		child == NULL && i < ARRAYLEN(wsseNsArray); 
		++i)
	{
		child = getDirectChild(fNode, localName, wsseNsArray[i]);
	}
	return child;
}

string WSSecurityUtil::getAttributeValueWSU(DOMElement* element, string attrName, string wsuNmspace)
{
	const XMLCh *localName = WssString(attrName).toXmlChArray(); 
	WssString nsUri, xmlVal;

	if (wsuNmspace.empty())
	{
		string wsuNsArray[] = WSCONST_WSU_NS_ARRAY;

		for (int i=0; xmlVal.isEmpty() && ARRAYLEN(wsuNsArray); ++i)
		{
			nsUri = wsuNsArray[i];
			xmlVal = element->getAttributeNS(nsUri.toXmlChArray(), localName);
		}
	}
	else
	{
		nsUri = wsuNmspace;
		xmlVal = element->getAttributeNS(nsUri.toXmlChArray(), localName);
	}
	return xmlVal.toStdString();
}

SOAPConstants* WSSecurityUtil::getSoapConstants(DOMElement* startElement)
{
	SOAPConstants* retVal;
	DOMDocument* doc = startElement->getOwnerDocument();
	char* ns = XMLString::transcode(doc->getDocumentElement()->getNamespaceURI());

	if ( strcmp(ns, WSCONST_URI_SOAP12_ENV) == 0 )
		retVal = new SOAP12Constants();
	else
		retVal = new SOAP11Constants();
	
	XMLString::release(&ns);
	return retVal;
}

string WSSecurityUtil::setNamespace(DOMElement* element, string ns, string prefix)
{
	string pre = getPrefixNS(ns, element);
	if ( !pre.empty() )
		return pre;
	
	pre = "xmlns:" + prefix;
	XMLCh *xmlns_ns, *xmlpre, *xmlns;
	xmlns_ns = XMLString::transcode(WSCONST_XMLNS_NS);
	xmlpre = XMLString::transcode(pre.c_str());
	xmlns = XMLString::transcode(ns.c_str());

	element->setAttributeNS(xmlns_ns, xmlpre, xmlns);
	XMLString::release(&xmlns_ns);
	XMLString::release(&xmlns);
	XMLString::release(&xmlpre);
	
	return prefix;
}

string WSSecurityUtil::getPrefixNS(string uri, DOMNode* e)
{
	while (e != NULL && (e->getNodeType() == DOMElement::ELEMENT_NODE))
	{
		DOMNamedNodeMap* attrs = e->getAttributes();
		for (unsigned int n=0; n < attrs->getLength(); n++)
		{
			DOMAttr* a = (DOMAttr*)attrs->item(n);
			char* name = XMLString::transcode(a->getName());
			char* nodeVal = XMLString::transcode(a->getNodeValue());
			
			if ( (strncmp(name, "xmlns:", 6) == 0) // JAVA := name.startsWith("xmlns:")
				&& (strcmp(nodeVal, uri.c_str()) == 0) )
			{
				string retval(name);
				XMLString::release(&name);
				XMLString::release(&nodeVal);

				return retval.substr(6, retval.length()-6);
			}
			
		}
		e = e->getParentNode();
	}
	return "";
}

DOMElement* WSSecurityUtil::findWsseSecurityHeaderBlock(WSSConfig* wsscnfg, DOMDocument* doc,
														DOMElement* envelope, bool doCreate)
{
	return findWsseSecurityHeaderBlock(wsscnfg, doc, envelope, "", doCreate);
}

DOMElement* WSSecurityUtil::findWsseSecurityHeaderBlock(WSSConfig* wsscnfg, DOMDocument* doc,
														DOMElement* envelope, string actor, bool doCreate)
{
	SOAPConstants *sc = getSoapConstants(envelope);
	DOMElement* wsseSecurity = getSecurityHeader(wsscnfg, doc, actor, sc);
	if (wsseSecurity != NULL)
		return wsseSecurity;
	
	DOMElement* header = findChildElement(envelope,	sc->getEnvelopeUri(), 
								WssString(sc->getHeaderQName()->getLocalPart()).toStdString());
	if (header == NULL)
		if (doCreate)
		{
			header = createElementInNamespace(envelope, 
					WssString(sc->getHeaderQName()->getLocalPart()).toStdString());
			header = prependChildElement(doc, envelope, header, true);
		}
	
	if (doCreate)
	{
		XMLCh* ns = XMLString::transcode(wsscnfg->getWsseNS().c_str());
		XMLCh* xmlns = XMLString::transcode(WSCONST_XML_NS);
		XMLCh* tmp = XMLString::transcode("wsse:Security");
		wsseSecurity = header->getOwnerDocument()->createElementNS(ns, tmp);
		
		XMLString::release(&tmp);
		tmp = XMLString::transcode("xmlns:wsse");
		
		wsseSecurity->setAttributeNS(xmlns, tmp, ns);
		XMLString::release(&ns);
		XMLString::release(&tmp);
		XMLString::release(&xmlns);

		return prependChildElement(doc, header, wsseSecurity, true);
	}
	return NULL;
}

DOMElement* WSSecurityUtil::createElementInNamespace(DOMElement* parent, string loalName)
{
	string prefix = WssString(parent->getPrefix()).toStdString();
	WssString qName(prefix + ":" + loalName);
	
	DOMElement* retVal = parent->getOwnerDocument()->createElementNS(
								parent->getNamespaceURI(), qName.toXmlChArray());
	return retVal;
}

DOMElement* WSSecurityUtil::findChildElement(DOMElement* parent, string namespaceUri, string localName)
{
	DOMNodeList* children = parent->getChildNodes();
	XMLCh* xnsUri = XMLString::transcode(namespaceUri.c_str());
	XMLCh* xlocalName = XMLString::transcode(localName.c_str());

	int len = children->getLength();
	for (int i = 0; i < len; i++)
	{
		DOMNode* child = children->item(i);
		if (child->getNodeType() == DOMNode::ELEMENT_NODE)
		{
			DOMElement* elementChild = (DOMElement*)child;
			if ( XMLString::equals(xnsUri, elementChild->getNamespaceURI()) 
				&& XMLString::equals(xlocalName, elementChild->getLocalName()))
			{
				return elementChild;
			}
		}
	}
	return NULL;
}

DOMElement* WSSecurityUtil::prependChildElement(DOMDocument* doc, DOMElement* parent, 
												DOMElement* child, bool addWhitespaces)
{
	DOMNode* firstChild = parent->getFirstChild();
	if (firstChild == NULL)
		parent->appendChild(child);
	else
		parent->insertBefore(child, firstChild);
	
	if (addWhitespaces)
	{
		XMLCh* txt = XMLString::transcode("\n");
		DOMNode* whitespaceText = doc->createTextNode(txt);
		parent->insertBefore(whitespaceText, child);
		XMLString::release(&txt);
	}
	return child;
}

//////////////////////////////////////////////////////////////////////
// Construction/Destruction
//////////////////////////////////////////////////////////////////////

WSSecurityUtil::WSSecurityUtil()
{
}

WSSecurityUtil::~WSSecurityUtil()
{
}

WSS4C_UTIL_NAMESPACE_END