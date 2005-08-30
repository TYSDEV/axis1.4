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

#if !defined(_WSBASEMESSAGE_H_INCLUDED_)
#define _WSBASEMESSAGE_H_INCLUDED_

WSS4C_MESSAGE_NAMESPACE_BEGIN

/**
 * This is the base class for WS Security messages.
 * It provide common functions and fields used by the specific message classes
 * such as sign, encrypt, and user token.
 */
class WSBaseMessage  
{
public:
   /**
    *  Constructor
    */
   WSBaseMessage();
   /**
    *  Constructor
    *  @param actor The actor name of the wsse:Security header 
    */
   WSBaseMessage(string actor);
   /**
    * Constructor
    * @param wssCnfg configuration options for processing and building security headers.
    * @param actor The actor name of the wsse:Security header.
    * @mu Set mustUnderstand to true or false.
    */
    WSBaseMessage(WSSConfig* wssCnfg, string actor, bool mu);
    /**
     * Constructor
     * @param actor The actor name of the wsse:Security header.
     * @mu Set mustUnderstand to true or false.
     */
    WSBaseMessage(string actor, bool mu);
    /**
     * Destructor
     */
	virtual ~WSBaseMessage();

    /**
     * Set actor name
     * @param actor The actor name of the wsse:Security header
     */
    void setActor(string act);
    /**
     * Set the time to live.
     * This is the time difference in seconds between the Created and the
     * Expires in Timestamp.
     * @param ttl The time to live in seconds.
     */
	void setTimeToLive(int ttl);
    /**
     * Set which parts of the message to encrypt/sign.
     * @param act The vector containing the WSEncryptionPart objects.
     * WARNING: Not properly implemented!
     */
    void setParts(void* pts);
    /**
     * Set the mustUnderstand flag for the wsse:Security header.
     * @parm mu Set mustUnderstand to true or false
     */
    void setMustUnderstand(bool mu);
    /**
     * Sets which key identifier to use.
     * @param keyIdType The key identifier type to use.
     */
    void setKeyIdentifierType(WSConstants::SubjectKeyIdentifier keyIdType);
    /**
     * Get the value of the keyIdentifierType
     * @return The value of keyIdentifierType
     */
	WSConstants::SubjectKeyIdentifier getKeyIdentifierType(void);

    /**
     *  Set the user and password info.
     *  Both information are used to get the user's private signing key
     *  @param username This is the user's alias name in the keystore that identifies
     *  the private key to sign the document.
     *  @param pswrd The user's password to get the private signing key from the
     *  keystore.
     */
    void setUserInfo(string usrname, string pswrd);
        
    protected:
    /**
     * Looks up or adds a body Id.
     * @param doc The SOAP envelope
     * @return The value of the wsu:Id attribute of the SOAP body.
     */
    string setBodyId(DOMDocument* doc);
        
    /**
     *  
     */
    string setWsuId(DOMElement* bodyElement);

    /**
     *  Creates a security header and inserts it as child into the SOAP Envelope.
     *  Check if a WS Security header block for an actor is already available
     *  in the document. If a header block is found return it, otherwise a new
     *  wsse:Security header block is created and the attributes set
     *
     *  @param doc A SOAP envelope as DOMDocument
     *  @return A wsse:Security element
     */
    DOMElement* insertSecurityHeader(DOMDocument* doc);

    string  actor;
    bool    mustUnderstand;
    string  user;
    string  password;
    void*  parts; // Specify what type of objects we're going to put here.
    int timeToLive; // Time between creation and expiary

    WSSConfig* wssConfig; 
	WSConstants::SubjectKeyIdentifier keyIdentifierType;

private:
	void init();
};

WSS4C_MESSAGE_NAMESPACE_END

#endif // !defined(_WSBASEMESSAGE_H_INCLUDED_)
