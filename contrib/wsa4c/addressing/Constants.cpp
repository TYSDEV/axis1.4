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

