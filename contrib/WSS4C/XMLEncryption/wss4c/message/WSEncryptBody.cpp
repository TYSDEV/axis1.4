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
#include "WSEncryptBody.hpp"

WSS4C_MESSAGE_NAMESPACE_BEGIN

void WSEncryptBody::setKey(unsigned char *key)
{
	embeddedKey = key;
}

void WSEncryptBody::setKeyEnc(int keyEncId)
{
	keyEncAlgoId = keyEncId;
}

void WSEncryptBody::setUserInfo(string username)
{
	user = username;
}

void WSEncryptBody::setEmbeddedKeyname(string keyname)
{
	embeddedKeyName = keyname;
}

//////////////////////////////////////////////////////////////////////
// Construction/Destruction
//////////////////////////////////////////////////////////////////////
WSEncryptBody::WSEncryptBody()
{
}
WSEncryptBody::WSEncryptBody(string actor) : WSBaseMessage(actor)
{
}
WSEncryptBody::WSEncryptBody(string actor, bool mu) : WSBaseMessage(actor, mu)
{
}
WSEncryptBody::WSEncryptBody(WSSConfig* wssConfig, string actor, bool mu): WSBaseMessage(wssConfig, actor, mu)
{
}
WSEncryptBody::~WSEncryptBody()
{
}

WSS4C_MESSAGE_NAMESPACE_END