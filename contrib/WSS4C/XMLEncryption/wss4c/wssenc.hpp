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

#if !defined(WSSENC_H_E2950B0C_6027_B27F_7023F6AD1FCC__INCLUDED)
#define WSSENC_H_E2950B0C_6027_B27F_7023F6AD1FCC__INCLUDED

/*#if defined(WIN32) && defined(WSS4C_DLL)
	#define WSSENC_EXPORTS __declspec(dllexport)
#elif defined(WIN32)
	#define WSSENC_EXPORTS __declspec(dllimport)
#endif*/

#define WSS4C_NAMESPACE_BEGIN namespace wss4c{
#define WSS4C_NAMESPACE_END }
#define WSS4C_NAMESPACE_USE using namespace wss4c;
#define WSS4C_MESSAGE_NAMESPACE_BEGIN namespace wss4c { namespace message{
#define WSS4C_MESSAGE_NAMESPACE_END }}
#define WSS4C_MESSAGE_NAMESPACE_USE using namespace wss4c::message;
#define WSS4C_UTIL_NAMESPACE_BEGIN namespace wss4c { namespace util{
#define WSS4C_UTIL_NAMESPACE_END }}
#define WSS4C_UTIL_NAMESPACE_USE using namespace wss4c::util;

#include <stdio.h>
#include <string>
#include <xercesc/dom/DOM.hpp>
#include <xercesc/util/QName.hpp>

#include "WSConstants.hpp"

/**
 *	Utility macro for determining array lengths
 */
#define ARRAYLEN(a) ( sizeof(a) )/( sizeof(a[0]))

using namespace std;
using namespace xercesc;

/**
 *	Inline methods for converting between STL strings and strings used
 *	inside xercesc.
 */
/*inline string _toStdString(const XMLCh* xcstr)
{
	string stdstr = "";

	if ( xcstr ) // Non null string
	{
		char* chstr = XMLString::transcode(xcstr);
		if (chstr)
			stdstr.copy(chstr, strlen(chstr));
		XMLString::release(&chstr);
	}
	return stdstr;
}
*/
#endif