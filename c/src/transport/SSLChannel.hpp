/*
 *   Copyright 2003-2004 The Apache Software Foundation.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

/**
 *
 * @author damitha kumarage (damitha@hsenid.lk, damitha@opensource.lk)
 *
 */

#if !defined(_AXIS_SSLCHANNEL_HPP)
#define _AXIS_SSLCHANNEL_HPP
#include "SOAPTransport.h"
#include <string>

/**
 * @file SSLChannel.hpp
 *
 * Contains the interfaces that any ssl channel layer should implemnt in order
 * for providing ssl support. The implementation should be at lease thread safe. The
 * Axis Engine creates a separate ssl channel object for each thread.
 */

/**
 * @interface SSLChannel
 * Default implemention for ssl channel library is in axis2/ssl folder.  
 * This implementation uses OpenSSL Secure Socket Library to provide
 * SSL channelling to Axis C++ client transport.
 * When request url contains https instead of http then in the axis2
 * transport SecureChannel class is instantiated. In the constructor
 * of this class ChannelFactory is instantiated. Then in the SecureChannel::open
 * method it returns an dynamically loaded instance of an implemention
 * of SSLChannel interface. Instant methods of the SecureChannel
 * class acts as wrappers for the SSLChannel interfaces implementation methods.
 * 
 * @brief Interface for custom Secure Channel implementations
 */

AXIS_CPP_NAMESPACE_USE
class SSLChannel
{
public:
    /**
     * @brief Any initialization stuff needed
     * @return boolean value of true or false
     */
    virtual bool SSLInit() = 0;

    /**
     * @brief Open the secure connection to the remote secure end
     * @pre   1. Secure channel library is loaded in SecureChannel::open method 
     *        2. Channel library initialized in SecureChannel::open method
     *        3. A tcp connection is opened to the remote end using
     *           Channel::open in SecureChannel::open
     * @param pSock socket descriptor
     * @example OpenSSLChannel.hpp
     * @return true on successful open of the secure connection 
     */
    virtual bool openSSLConnection(unsigned int* pSock) = 0;

    /**
     * @brief Secure read operation implementation
     * @pre   1. Secure Channel library is loaded 
     *        2. Secure connection initialized and opened
     * @param msg Read buffer 
     * @example OpenSSLChannel.hpp
     * @return true if read is successful 
     */
    virtual int SSLRead(std::string& msg) = 0;

    /**
     * @brief Secure write operation implementation
     * @pre   1. Secure Channel library is loaded
     *        2. Secure connection is intialized and opened
     * @param msg Write buffer.  
     * @param pSock socket descriptor
     * @example OpenSSLChannel.hpp
     * @return true if write is successful 
     */
    virtual int SSLWrite(const std::string& msg, unsigned int* pSock) = 0; 

    /**
     * @brief close the ssl channel
     */
    virtual void closeSSLChannel() = 0;

    /**
     * @brief If error occures, set the error no
     * @param error no
     */
    virtual void setSSLError(int) = 0;

    /**
     *
     * @brief get the ssl error
     * @return ssl error message
     */
    virtual char* getSSLError() = 0;
 
};
#endif

