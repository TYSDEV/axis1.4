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
 *
 *
 * @author Sanjaya Singharage (sanjayasing@opensource.lk)
 * @author Susantha Kumara (susantha@opensource.lk, skumara@virtusa.com)
 *
 */
#ifdef WIN32
#pragma warning (disable : 4786)
#endif

#if !defined(__PACKET_H_OF_AXIS_INCLUDED__)
#define __PACKET_H_OF_AXIS_INCLUDED__

#include "GDefine.h"

typedef enum 
{
    SOAPACTION_HEADER,
    SERVICE_URI,
    OPERATION_NAME,
    SOAP_MESSAGE_LENGTH
} AXIS_TRANSPORT_INFORMATION_TYPE;

typedef enum
{
    AXIS_HTTP_GET,
    AXIS_HTTP_POST,
    AXIS_HTTP_UNSUPPORTED
} AXIS_HTTP_METHOD;

/*
 * This structure is used to keep string key/value pairs such as 
 * http headers. set_header
 * function can be used to add headers to a Ax_soapstream
 */ 
typedef struct
{
    char* headername;
    char* headervalue;
} Ax_header;

typedef enum
{
    TRANSPORT_FINISHED=0,
    TRANSPORT_IN_PROGRESS,
    TRANSPORT_FAILED
} AXIS_TRANSPORT_STATUS;

typedef void (AXISCALL * AXIS_ENGINE_CALLBACK_RELEASE_SEND_BUFFER)
(const char*, const void*);
 
typedef struct
{
    const void* pBufferId;
    const char* pcBuffer;
} BufferInfo;

/* NO_OF_SERIALIZE_BUFFERS should be equal to the corresponding 
 * value in the axis configuration file 
 */
#define NO_OF_SERIALIZE_BUFFERS 20

#ifdef __cplusplus
extern "C"
{
#endif

/*
 * This function is implemented in axis and should be called ONCE to 
 * uninitialize Axis Engine when the 
 * Axis SOAP processor shuts down.
 */
STORAGE_CLASS_INFO int uninitialize_module();

/*
 * This function is implemented in axis and should be called ONCE to 
 * initialize Axis Engine.
 */
STORAGE_CLASS_INFO int initialize_module(int bServer);

/*
 * This callback function is implemented in axis and should be called by the 
 * transport module in order to
 * start processing a SOAP message. 
 * @param 
 * stream - Ax_soapstream object contains information about the SOAP stream 
 *          and the message. This also should be populated with the 
 *          transport module.
 */
STORAGE_CLASS_INFO int process_request(void* stream);

/*
 * This callback function is implemented in axis and should be called by 
 * the transport module. 
 * @param 
 *        buffer - Buffer passed to transport by calling transport's 
 *      AXIS_MODULE_CALLBACK_SEND_MESSAGE_BYTES 
 *                 callback
 *        bufferid - Id of the buffer passed to transport by calling transport's 
 *               AXIS_MODULE_CALLBACK_SEND_MESSAGE_BYTES callback
 *        stream - Ax_soapstream object passed to transport by calling 
 *               transport's AXIS_MODULE_CALLBACK_SEND_MESSAGE_BYTES callback
 */
STORAGE_CLASS_INFO void axis_buffer_release(const char* buffer, 
                                            const void* bufferid, 
                                            const void* stream);

#ifdef __cplusplus
}
#endif

#endif




