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


#if !defined(_WSAHANDLER_H____OF_AXIS_INCLUDED_)
#define _WSAHANDLER_H____OF_AXIS_INCLUDED_

#include <axis/Handler.hpp>
#include <axis/IMessageData.hpp>
#include "..\utils\UUIDGen.hpp"
#include "AddressingHeaders.hpp"

AXIS_CPP_NAMESPACE_USE

class WsaServerHandler : public Handler
{
private:
    UUIDGen * pUUIDGen;
    FILE * f;
    void processServerResponse(IMessageData * pIMsg);
    void processServerRequest(IMessageData * pIMsg);
    void processFault(IMessageData * pIMsg);

    void forwardMessage(EndpointReferenceType * pReplyTo,
                   IMessageData * pIMsg);
    void setTargetService(IMessageData * pIMsg,
                                     AddressingHeaders * pHeaders);
	
public:
	int AXISCALL fini();
	int AXISCALL init();
	void AXISCALL onFault(void* pvIMsg);
	int AXISCALL invoke(void* pvIMsg);
	WsaServerHandler();
	virtual ~WsaServerHandler();   
    
};

#endif 