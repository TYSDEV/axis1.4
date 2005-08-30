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

#if !defined(_WSENCRYPTBODY_H_INCLUDED_)
#define _WSENCRYPTBODY_H_INCLUDED_


#include <wss4c/message/WSBaseMessage.hpp>

/**
 *	Encrypts a SOAP body inside a SOAP envelope according to WS Specification, X509 profile
 *	and adds the encryption data.	
 */

WSS4C_MESSAGE_NAMESPACE_BEGIN
	
class WSEncryptBody : public WSBaseMessage
{

public:
	/**	Constructor */
	WSEncryptBody();
	/**	
	 *	Constructor 
	 *	@param actor The actor name of the <code>wsse:Security</code> header
	 */
	WSEncryptBody(string actor);
	/**
	 *	Constructor
	 *	@param actor The actor name of the <code>wsse:Security</code> header
	 *	@param mu Set <code>mustUnderstand</code> to true or false
	 */
	WSEncryptBody(string actor, bool mu);
	/**
	 *	Constructor
	 *	@param wssConfig Configuration options for processing and building the <code>
	 *	wsse:Security</code> header
	 *	@param actor The actor name of the <code>wsse:Security</code> header
	 *	@param mu Set <code>mustUnderstand</code> to true or false
	 */
	WSEncryptBody(WSSConfig* wssConfig, string actor, bool mu);
	/**	Destructor */
	virtual ~WSEncryptBody();

	/**
	 *	Sets the key to use during embedded encryption
	 *	@param key to use during encryption, expressed as a byte array. The key must
	 *	fit the selected symmetric algorithm.
	 */
	void setKey(unsigned char* key);
	
	/**
	 *	Sets the algorithm that encrypts the symmetric key. The default is 
	 *	<code>WSCONST_KEYTRANSPORT_RSA15</code>.
	 *	@param keyEncId The key encoding algorithm.
	 */
	void setKeyEnc(int keyEncId);

	/**
	 *	Sets the username to get the encryption certificate. The public key of this
	 *	certificate is used, thus no password necessary. The username is a keystore
	 *	alias usually.
	 */
	void setUserInfo(string username);

	/**
	 *	Sets the key name for EMBEDDED_KEYNAME
	 */
	void setEmbeddedKeyname(string keyname);

	/**
	 *	Sets the X509 certificate to use for encryption. If this is set <b>and</b>
	 *	and the key identifier is set to <code>DirectReference</code> then use this
	 *	certificate to get the public key for encryption.
	 *
	 *	@param cert The X509 certificate to use for encryption.
	 */
	void useThisCert(/* TODO: INCOMPLETE */);

protected:
	unsigned char *embeddedKey;
	string embeddedKeyName;
	int keyEncAlgoId;
	string user;

};

WSS4C_MESSAGE_NAMESPACE_END

#endif // !defined(_WSBASEMESSAGE_H_INCLUDED_)
