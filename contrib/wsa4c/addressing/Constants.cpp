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


#include "Constants.hpp" 
#include <string>
#include <iostream>
using namespace std;

Constants::Constants(){};
Constants::~Constants(){};

AxisChar * Constants::NS_PREFIX_ADDRESSING = "wsa";

AxisChar * Constants::NS_URI_ADDRESSING="http://schemas.xmlsoap.org/ws/2003/03/addressing";

AxisChar * Constants::NS_URI_ANONYMOUS= *Constants::NS_URI_ADDRESSING + "/role/anonymous";

AxisChar * Constants::ENDPOINT_REFERENCE = "EndpointReference";

AxisChar * Constants::MESSAGE_ID = "MessageID";

AxisChar * Constants::RELATES_TO = "RelatesTo";

AxisChar * Constants::RELATIONSHIP_TYPE = "RelationshipType";

AxisChar * Constants::ACTION = "Action";

AxisChar * Constants::ADDRESS = "Address";

AxisChar * Constants::REFERENCE_PROPERTIES = "ReferenceProperties";

AxisChar * Constants::SERVICE_NAME = "ServiceName";

AxisChar * Constants::PORT_NAME = "PortName";

AxisChar * Constants::PORT_TYPE = "PortType";

AxisChar * Constants::RESPONSE = "Response";

AxisChar * Constants::TO = "To";

AxisChar * Constants::REPLY_TO = "ReplyTo";

AxisChar * Constants::FAULT_TO = "FaultTo";

AxisChar * Constants::FROM = "From";

AxisChar * Constants::RECIPIENT = "Recipient";

AxisChar *  Constants::ENV_ADDRESSING_REQUEST_HEADERS ="org.apache.axis.message.addressing.REQUEST.HEADERS";

AxisChar *  Constants::ENV_ADDRESSING_RESPONSE_HEADERS ="org.apache.axis.message.addressing.RESPONSE.HEADERS";

