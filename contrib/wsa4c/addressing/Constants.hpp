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


#if !defined(__WSACONSTANTS_OF_AXIS_INCLUDED__)
#define __WSACONSTANTS_OF_AXIS_INCLUDED__

#include <string>
#include <axis/GDefine.hpp>
class Constants
{
public:
	Constants();
	~Constants();

    static AxisChar * NS_URI_SOAP_ENVELOPE;
    static AxisChar * NS_PREFIX_SOAP_ENVELOPE;
    static AxisChar * NS_URI_ADDRESSING;    
    static AxisChar * NS_PREFIX_ADDRESSING;
	static AxisChar * NS_URI_ANONYMOUS;
    static AxisChar * ENDPOINT_REFERENCE;
	static AxisChar * MESSAGE_ID;
	static AxisChar * RELATES_TO;
	static AxisChar * RELATIONSHIP_TYPE;
	static AxisChar * ACTION;
	static AxisChar * ADDRESS;
	static AxisChar * REFERENCE_PROPERTIES;
    static AxisChar * SERVICE_NAME;
	static AxisChar * PORT_NAME;
    static AxisChar * PORT_TYPE;
	static AxisChar * RESPONSE;
    static AxisChar * TO;
    static AxisChar * REPLY_TO;
    static AxisChar * FAULT_TO;
	static AxisChar * FROM;
    static AxisChar * RECIPIENT;
    static AxisChar * MUSTUNDERSTAND;
    static AxisChar * ENV_ADDRESSING_REQUEST_HEADERS;
	static AxisChar * ENV_ADDRESSING_RESPONSE_HEADERS;
	static AxisChar * ENV_ADDRESSING_TO_URI;
    static AxisChar * ENV_ADDRESSING_FROM_URI;
    static AxisChar * ENV_ADDRESSING_ACTION_URI;
    static AxisChar * ENV_ADDRESSING_REPLYTO_URI;
    static AxisChar * ENV_ADDRESSING_FAULTTO_URI;
    static AxisChar * ENV_ADDRESSING_SET_MUST_UNDERSTAND;
    static AxisChar * ENV_ADDRESSING_SEND_REPLYTO;
    static AxisChar * ENV_ADDRESSING_REFERENCEPROPERTIES;
    static AxisChar * FAULT_ACTION; 
    static AxisChar * NS_URI_ADDRESSING_04; 
};
#endif
