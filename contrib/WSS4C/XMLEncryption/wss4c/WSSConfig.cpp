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
#include <wss4c/WSSConfig.hpp>

WSS4C_NAMESPACE_BEGIN

/*
 *	Returns a new instance configured with the default values
 */
WSSConfig* WSSConfig::getNewInstance(void)
{
	return new WSSConfig();
}

/*
 *	Returns a static WSConfig instance that is configured with the latest
 *	OASIS WS-Security settings
 */
WSSConfig* WSSConfig::getDefaultWSConfig(void)
{
	return defaultConfig; 
			// TODO Note: defaultConfig is never constructed or assigened to within
			// this class. Therefor look out for uninitialized use of this.
}

/*
 *	Gets or sets the WS-Security namespace. The default value is 
 *	WSSE_NS_OASIS_1_0
 */
string WSSConfig::getWsseNS(void)
{
	return wsse_ns;
}
void WSSConfig::setWsseNS(string wsseNamespace)
{
	wsse_ns = wsseNamespace;
}

/*
 *	GEts or sets the WS-Utility namespace. The dafault value is WSU_NS_OASIS_1_0
 */
string WSSConfig::getWsuNS(void)
{
	return wsu_ns;
}
void WSSConfig::setWsuNS(string wsuNamespace)
{
	wsu_ns = wsuNamespace;
}

/*
 *	Returns true if the BinarySecurityToken EncodingType and ValueType attributes
 *	should be namespace qualified
 */
bool WSSConfig::isBSTAttributesQualified(void)
{
	return qualifyBSTAttributes;
}
/*
 *	Specify if the BinarySecurityToken EncodingType and ValueType attributes should
 *	be namespace qualified. The default value is false.
 */
void WSSConfig::setBSTAttributesQualified(bool qualify)
{
	qualifyBSTAttributes = qualify;
}

/*
 *	Returns true if BinarySecurityToken EncodingType and ValueType attribute values
 *	should be prefixed with "wsse" or otherwise qualified with the wsse namespace (false)
 */
bool WSSConfig::isBSTValuesPrefixed()
{
	return prefixBSTValues;
}
/*
 *	Set the option whether the BinarySecurityToken EncodingType and ValueType attribute values
 *	should be prefixed with wsse or otherwise qualified with the wsse namespace (false)
 */
void WSSConfig::setBSTValuesPrefixed(bool prefix)
{
	prefixBSTValues = prefix;
}

/*
 *	Returns true if the ID attribute placed in the signature target element is qualified with
 *	wsu namespace
 */
bool WSSConfig::isTargetIdQualified()
{
	return targetIdQualified;
}
/*
 *	Sets the option whether the Id attribute placed in the signature target should be qualified
 *	with the wsu namespace
 */
void WSSConfig::setTargetIdQualified(bool qualify)
{
	targetIdQualified = qualify;
}

/*
 *	Retruns TIMESTAMP_IN_SECURITY_ELEMENT if the wsu:Timestamp element is placed inside the 
 *	wsse:Security element. TIMESTAMP_IN_HEADER_ELEMENT if it is placed under the header, directly
 *	outside the wsse:Security element
 */
int WSSConfig::getTimestampLocation()
{
	return timestampLocation;
}
/*
 *	Sets an option whether the wsu:Timestamp element is placed inside the wsse:Security element.
 *	Set it to false for placement in the the header, outside the wsse:Security element.
 */
void WSSConfig::setTimestampLocation(int timestampElementLocation)
{
	timestampLocation = timestampElementLocation;
}

/*
 *	Returns true if WSS4C attempts to process non-compliant WS-Security messages, such as 
 *	WS-Security headers with older OASIS spec namespaces.
 */
bool WSSConfig::getProcessNonCompliantMessages()
{
	return processNonCompliantMessages;
}
/*
 *	Sets an option whether WSS4C should attempt to process non-compliant WS-Security messages,
 *	such as WS-Security headers with older OASIS spec namespaces.
 */
void WSSConfig::setProcessNonCompliantMessages(bool attemptProcess)
{
	processNonCompliantMessages = attemptProcess;
}

//////////////////////////////////////////////////////////////////////
// Construction/Destruction
//////////////////////////////////////////////////////////////////////

WSSConfig::WSSConfig()
{
	wsse_ns = WSCONST_WSSE_NS_OASIS_1_0;
	wsu_ns = WSCONST_WSU_NS_OASIS_1_0;
	qualifyBSTAttributes = false;
	prefixBSTValues = false;
	targetIdQualified = false;
	processNonCompliantMessages = true;
	timestampLocation = TIMESTAMP_IN_SECURITY_ELEMENT;
}

WSSConfig::~WSSConfig()
{
}

WSS4C_NAMESPACE_END