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

#if !defined(_WSCONSTANTS_INCLUDED_)
#define _WSCONSTANTS_INCLUDED_

// the following compliance mode values must have increasing values as new
// modes are added; a later spec should have a value > value of an an earlier spec.
#define WSCONST_OASIS_2002_07 1
#define WSCONST_OASIS_2002_12 2
#define WSCONST_OASIS_2003_06 3
#define WSCONST_OASIS_1_0 4

#define WSCONST_COMPLIANCE_MODE WSCONST_OASIS_1_0

#define WSCONST_WSSE_NS_OASIS_2002_07 "http://schemas.xmlsoap.org/ws/2002/07/secext"
#define WSCONST_WSSE_NS_OASIS_2002_12 "http://schemas.xmlsoap.org/ws/2002/12/secext"
#define WSCONST_WSSE_NS_OASIS_2003_06 "http://schemas.xmlsoap.org/ws/2003/06/secext"
#define WSCONST_WSSE_NS_OASIS_1_0 "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd"
#define WSCONST_WSSE_NS WSCONST_WSSE_NS_OASIS_1_0
#define WSCONST_WSSE_NS_ARRAY {WSCONST_WSSE_NS_OASIS_1_0, WSCONST_WSSE_NS_OASIS_2003_06, WSCONST_WSSE_NS_OASIS_2002_12, WSCONST_WSSE_NS_OASIS_2002_07}
#define WSCONST_USERNAMETOKEN_NS "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0"
#define WSCONST_SOAPMESSAGE_NS "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0"
#define WSCONST_X509TOKEN_NS "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-x509-token-profile-1.0"
#define WSCONST_WSSE_PREFIX "wsse"
#define WSCONST_WSSE_LN "Security"
#define WSCONST_WSU_NS_OASIS_2002_07 "http://schemas.xmlsoap.org/ws/2002/07/utility"
#define WSCONST_WSU_NS_OASIS_2002_12 "http://schemas.xmlsoap.org/ws/2002/12/utility"
#define WSCONST_WSU_NS_OASIS_2003_06 "http://schemas.xmlsoap.org/ws/2003/06/utility"
#define WSCONST_WSU_NS_OASIS_1_0 "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd"
#define WSCONST_WSU_NS WSCONST_WSU_NS_OASIS_1_0
#define WSCONST_WSU_NS_ARRAY {WSCONST_WSU_NS_OASIS_1_0, WSCONST_WSU_NS_OASIS_2003_06, WSCONST_WSU_NS_OASIS_2002_12, WSCONST_WSU_NS_OASIS_2002_07}
#define WSCONST_WSU_PREFIX "wsu"
#define WSCONST_SIG_NS "http://www.w3.org/2000/09/xmldsig#"
#define WSCONST_SIG_PREFIX "ds"
#define WSCONST_SIG_LN "Signature"
#define WSCONST_ENC_NS "http://www.w3.org/2001/04/xmlenc#"
#define WSCONST_ENC_PREFIX "xenc"
#define WSCONST_ENC_KEY_LN "EncryptedKey"
#define WSCONST_REF_LIST_LN "ReferenceList"
#define WSCONST_XMLNS_NS "http://www.w3.org/2000/xmlns/"
#define WSCONST_XML_NS "http://www.w3.org/XML/1998/namespace"
#define WSCONST_USERNAME_TOKEN_LN "UsernameToken"
#define WSCONST_BINARY_TOKEN_LN "BinarySecurityToken"
#define WSCONST_TIMESTAMP_TOKEN_LN "Timestamp"
#define WSCONST_USERNAME_LN "Username"
#define WSCONST_PASSWORD_LN "Password"
#define WSCONST_PASSWORD_TYPE_ATTR "Type"
#define WSCONST_NONCE_LN "Nonce"
#define WSCONST_CREATED_LN "Created"
#define WSCONST_EXPIRES_LN "Expires"

#define WSCONST_SAML_NS "urn:oasis:names:tc:SAML:1.0:assertion"
#define WSCONST_SAMLP_NS "urn:oasis:names:tc:SAML:1.0:protocol"
#define WSCONST_ASSERTION_LN "Assertion"
#define WSCONST_WSS_SAML_NS "http://docs.oasis-open.org/wss/2004/XX/oasis-2004XX-wss-saml-token-profile-1.0#"
#define WSCONST_WSS_SAML_ASSERTION "SAMLAssertion-1.1"

//
// SOAP-ENV Namespace
//
#define WSCONST_URI_SOAP11_ENV "http://schemas.xmlsoap.org/soap/envelope/"
#define WSCONST_URI_SOAP12_ENV "http://www.w3.org/2003/05/soap-envelope"
// #define WSCONST_URIS_SOAP_ENV {URI_SOAP11_ENV, URI_SOAP12_ENV}

// Misc SOAP Namespaces / URIs
#define WSCONST_URI_SOAP11_NEXT_ACTOR "http://schemas.xmlsoap.org/soap/actor/next"
#define WSCONST_URI_SOAP12_NEXT_ROLE "http://www.w3.org/2003/05/soap-envelope/role/next"
#define WSCONST_URI_SOAP12_NONE_ROLE "http://www.w3.org/2003/05/soap-envelope/role/none"
#define WSCONST_URI_SOAP12_ULTIMATE_ROLE "http://www.w3.org/2003/05/soap-envelope/role/ultimateReceiver"

#define WSCONST_ELEM_ENVELOPE "Envelope"
#define WSCONST_ELEM_HEADER "Header"
#define WSCONST_ELEM_BODY "Body"

#define WSCONST_ATTR_MUST_UNDERSTAND "mustUnderstand"
#define WSCONST_ATTR_ACTOR "actor"
#define WSCONST_ATTR_ROLE "role"

#define WSCONST_PW_DIGEST "PasswordDigest"
#define WSCONST_PASSWORD_DIGEST "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordDigest"

#define WSCONST_PW_TEXT "PasswordText"
#define WSCONST_PASSWORD_TEXT "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText"

// In order to use the following constants the xml.apache.org/security encryption header
// files should be included.
/*
#define WSCONST_KEYTRANSPORT_RSA15 EncryptionConstants.ALGO_ID_KEYTRANSPORT_RSA15
#define WSCONST_KEYTRANSPORT_RSAOEP EncryptionConstants.ALGO_ID_KEYTRANSPORT_RSAOAEP
#define WSCONST_TRIPLE_DES EncryptionConstants.ALGO_ID_BLOCKCIPHER_TRIPLEDES
#define WSCONST_AES_128 EncryptionConstants.ALGO_ID_BLOCKCIPHER_AES128
#define WSCONST_AES_256 EncryptionConstants.ALGO_ID_BLOCKCIPHER_AES256
#define WSCONST_AES_192 EncryptionConstants.ALGO_ID_BLOCKCIPHER_AES192
#define WSCONST_DSA XMLSignature.ALGO_ID_SIGNATURE_DSA
*/

#define WSCONST_KEYTRANSPORT_RSA15 1
#define WSCONST_KEYTRANSPORT_RSAOEP 2

#define WSCONST_BST_DIRECT_REFERENCE 1
#define WSCONST_ISSUER_SERIAL 2
#define WSCONST_X509_KEY_IDENTIFIER 3
#define WSCONST_SKI_KEY_IDENTIFIER 4



// Embeds a keyinfo/key name into the EncryptedData element.
// Refer to WS Security specification X509 profile
#define WSCONST_EMBEDDED_KEYNAME 5

// Embeds a keyinfo/wsse:SecurityTokenReference into the EncryptedData element.
#define WSCONST_EMBED_SECURITY_TOKEN_REF 6

#define WSCONST_NO_SECURITY 0
#define WSCONST_UT 0x1
		// Perform UsernameToken only
#define WSCONST_SIGN 0x2
		// Perform Signature
#define WSCONST_ENCR 0x4
		// Perform Encryption

/*
 *	Attention: the signed/unsigned types identify if WSS4C uses the SAML token
 *	for signature, signature key or not. It does not mean if the token contain an
 *	enveloped container.
 */
#define WSCONST_ST_UNSIGNED 0x8
		// Perform SAMLToken unsigned.
#define WSCONST_ST_SIGNED
		// Perform SAMLToken signed.

// insert timestamp
#define WSCONST_TS 0x20

#define WSCONST_NO_SERIALIZE 0x100
#define WSCONST_SERIALIZE 0x200

// init various constants to the chosen compliance mode
#if WSCONST_COMPLIANCE_MODE == WSCONST_OASIS_1_0
	#define WSCONST_WSSE_NS WSCONST_WSSE_NS_OASIS_1_0
	#define WSCONST_WSU_NS WSCONST_WSU_NS_OASIS_1_0

#elif WSCONST_COMPLIANCE_MODE == WSCONST_OASIS_2003_06
	#define WSCONST_WSSE_NS WSCONST_WSSE_NS_OASIS_2003_06
	#define WSCONST_WSU_NS WSCONST_WSU_NS_OASIS_2003_06

#elif WSCONST_COMPLIANCE_MODE == WSCONST_OASIS_2002_12
	#define WSCONST_WSSE_NS WSCONST_WSSE_NS_OASIS_2002_12
	#define WSCONST_WSU_NS WSCONST_WSU_NS_OASIS_2002_12

#elif WSCONST_COMPLIANCE_MODE == WSCONST_OASIS_2002_07
	#define WSCONST_WSSE_NS WSCONST_WSSE_NS_OASIS_2002_07
	#define WSCONST_WSU_NS WSCONST_WSU_NS_OASIS_2002_07

#else
	#define WSCONST_WSSE_NS WSCONST_WSSE_NS_OASIS_1_0
	#define WSCONST_WSU_NS WSCONST_WSU_NS_OASIS_1_0

#endif									
// end compliance mode

class WSConstants
{
public:
	enum SubjectKeyIdentifier{
		BST_DIRECT_REFERENCE, ISSUSER_SERIAL, X509_KEY_IDENTIFIER, SKI_KEY_IDENTIFIER,};
};

#endif // !defined(_WSCONSTANTS_INCLUDED_)
