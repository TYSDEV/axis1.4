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
#include <axis/server/GDefine.hpp>
class Constants
{
public:
	Constants();
	~Constants();

	/*
     * Field NS_PREFIX_ADDRESSING
     */
	static AxisChar * NS_PREFIX_ADDRESSING;
  
    /*
     * Field NS_URI_ADDRESSING
     */
    static AxisChar * NS_URI_ADDRESSING;
  
    /*
     * Field NS_URI_ANONYMOUS
     */
	static AxisChar * NS_URI_ANONYMOUS;
  
    /*
     * Field ENDPOINT_REFERENCE
     */
    static AxisChar * ENDPOINT_REFERENCE;
  
    /*
     * Field MESSAGE_ID
     */
	static AxisChar * MESSAGE_ID;
  
    /*
     * Field RELATES_TO
     */
	static AxisChar * RELATES_TO;
  
    /*
     * Field RELATIONSHIP_TYPE
     */
	static AxisChar * RELATIONSHIP_TYPE;
 
    /*
     * Field ACTION
     */
	static AxisChar * ACTION;
  
    /*
     * Field ADDRESS
     */
	static AxisChar * ADDRESS;
  
    /*
     * Field REFERENCE_PROPERTIES
     */
	static AxisChar * REFERENCE_PROPERTIES;
  
    /*
     * Field SERVICE_NAME
     */
    static AxisChar * SERVICE_NAME;
  
    /*
     * Field PORT_NAME
     */
	static AxisChar * PORT_NAME;
  
    /*
     * Field PORT_TYPE
     */
    static AxisChar * PORT_TYPE;
  
    /*
     * Field RESPONSE
     */
	static AxisChar * RESPONSE;
 
    /*
     * Field TO
     */
    static AxisChar * TO;
 
    /*
     * Field REPLY_TO
     */
    static AxisChar * REPLY_TO;
 
    /*
     * Field FAULT_TO
     */
    static AxisChar * FAULT_TO;
 
    /*
     * Field FROM
     */
	static AxisChar * FROM;
 
    /*
     * Field RECIPIENT
     */
    static AxisChar * RECIPIENT;
 
    /*
     * Field ENV_ADDRESSING_REQUEST_HEADERS
     */
	static AxisChar * ENV_ADDRESSING_REQUEST_HEADERS;
 
    /*
     * Field ENV_ADDRESSING_RESPONSE_HEADERS
     */
	static AxisChar * ENV_ADDRESSING_RESPONSE_HEADERS;
};
#endif
