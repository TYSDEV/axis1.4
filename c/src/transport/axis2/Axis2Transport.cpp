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

/*
 * @author Samisa Abeysinghe (sabeysinghe@virtusa.com)
 *
 */


#ifdef WIN32
#pragma warning (disable : 4786)
#pragma warning (disable : 4101)
#endif

#include "Axis2Transport.h"

#include <stdio.h>
#include <iostream>

Axis2Transport::Axis2Transport()
        : m_bURIChanged( false ), 
        m_strHTTPProtocol("HTTP/1.1"), m_strHTTPMethod("POST"), 
        m_bChunked(false), m_bReadPastHTTPHeaders(false) ,m_strProxyHost( "" ), m_uiProxyPort( 0 ), m_bUseProxy( false )
{
    m_pcEndpointUri = NULL;
    m_pReleaseBufferCallback = 0;
    m_strBytesToSend = "";
    m_iBytesLeft = 0;
    m_iContentLength = 0;
    m_pcReceived = 0;
}

Axis2Transport::~Axis2Transport()
{
    if ( m_pcEndpointUri )
        free( m_pcEndpointUri );
}

void Axis2Transport::setEndpointUri( const char* pcEndpointUri )
{
    if ( m_Channel.getURL() )
        if ( strcmp( m_Channel.getURL(), pcEndpointUri ) == 0 )
            return ; // no change to the current URI; hence do nothing

    // we have a new URI
    m_Channel.setURL( pcEndpointUri );
    m_bURIChanged = true;
}

int Axis2Transport::openConnection()
{
    //Samisa: I wonder whether this should be a SOAPTransport API call.
    //It should not be the job of the upper layers to tell the trasport
    //to open and close connections. Rather the transport should determine
    //when to do that, when sendBytes is called.

    return AXIS_SUCCESS;

}

void Axis2Transport::closeConnection()
{
        m_bReadPastHTTPHeaders = false; // get ready for a new message
        m_strReceived = "";    //clear the message buffer in preperation of the next read
        m_iContentLength = 0;
}


AXIS_TRANSPORT_STATUS Axis2Transport::flushOutput()
{
    if ( m_bURIChanged )
        m_Channel.open();

    char buff[ 8 ];
    sprintf( buff, "%d", m_strBytesToSend.length() );
    this->setTransportProperty( "Content-Length" , buff );

    try
    {
        m_Channel << this->getHTTPHeaders();
        m_Channel << this->m_strBytesToSend.c_str();
    }
    catch(AxisTransportException& e)
    {
        throw;
    }
    catch(AxisException& e)
    {
        throw;
    }
    catch(...)
    {
        throw;
    }

    m_strBytesToSend = "";
    return TRANSPORT_FINISHED;
}

const char* Axis2Transport::getHTTPHeaders()
{
    URL& url = m_Channel.getURLObject();

    std::string strHTTPHeaders = m_strHTTPMethod + " ";
    //strHTTPHeaders += std::string(url.getURL()) + " ";
    strHTTPHeaders += std::string(url.getResource()) + " ";
    strHTTPHeaders += m_strHTTPProtocol + "\r\n";

    strHTTPHeaders += std::string("Host: ") + url.getHostName();

    unsigned short port = url.getPort ();
    char buff[8];
    sprintf (buff, "%u", port);
    strHTTPHeaders += ":";
    strHTTPHeaders += buff;
    strHTTPHeaders += "\r\n";

    strHTTPHeaders += "Content-Type: text/xml; charset=UTF-8\r\n";

    // Set other HTTP headers
    for (unsigned int i = 0; i < m_vHTTPHeaders.size (); i++)
    {

        strHTTPHeaders += m_vHTTPHeaders[i].first;
        strHTTPHeaders += ": ";
        strHTTPHeaders += m_vHTTPHeaders[i].second;
        strHTTPHeaders += "\r\n";
    }

    strHTTPHeaders += "\r\n";

    return strHTTPHeaders.c_str();
}

const char* Axis2Transport::getHTTPMethod()
{
    return m_strHTTPMethod.c_str();
}

void Axis2Transport::setHTTPMethod(const char* cpMethod)
{
    m_strHTTPMethod = std::string(cpMethod);
}

AXIS_TRANSPORT_STATUS Axis2Transport::sendBytes( const char* pcSendBuffer,
        const void* pBufferId )
{
    m_strBytesToSend += std::string( pcSendBuffer );

    return TRANSPORT_IN_PROGRESS;
}


AXIS_TRANSPORT_STATUS Axis2Transport::getBytes( char* pcBuffer, int* pSize )
{
    if ( 0 <= m_iBytesLeft )
    {
        try
        {
            m_Channel >> m_strReceived;
            if (!m_bReadPastHTTPHeaders)
            {
                do
                {
                    if (m_strReceived.find ("\r\n\r\n") == std::string::npos)
                        m_Channel >> m_strReceived;    // Assume non blocking here
                }
                while (m_strReceived.find ("\r\n\r\n") == std::string::npos);
                //now we have found the end of headers
                m_bReadPastHTTPHeaders = true;

                unsigned int pos = 0;

                // Look for content lenght
                if ((pos = m_strReceived.find ("Content-Length: ")) != std::string::npos)
                {
                    m_iContentLength = atoi (m_strReceived.substr (pos + strlen ("Content-Length: "),
                                             m_strReceived.find("\n", pos) ).c_str ());
                }

                // Check if the message is chunked
                if ((pos = m_strReceived.find ("Transfer-Encoding: chunked")) != std::string::npos)
                {
                    m_bChunked = true;
                }
                else
                {
                    m_bChunked = false;
                }

                // Skip headers and get payload
                m_strReceived = m_strReceived.substr(m_strReceived.find ("\r\n\r\n") + 4 );
            }

            // Read past headers. Deal with payload

            // make sure we have a message with some content
            if (m_strReceived.length() == 0)
                m_Channel >> m_strReceived;

            if (m_bChunked && m_iContentLength < 1 )
            {
                /*
                 *Chunked data looks like ->
                 *      Chunked-Body   = *chunk
                 *                       "0" CRLF
                 *                       footer
                 *                       CRLF
                 *
                 *      chunk          = chunk-size [ chunk-ext ] CRLF
                 *                         chunk-data CRLF
                 *
                 *      hex-no-zero    = <HEX excluding "0">
                 *
                 *      chunk-size     = hex-no-zero *HEX
                 *      chunk-ext      = *( ";" chunk-ext-name [ "=" chunk-ext-value ] )
                 *      chunk-ext-name = token
                 *      chunk-ext-val  = token | quoted-string
                 *      chunk-data     = chunk-size(OCTET)
                 *
                 *      footer         = *entity-header
                 */
                // firstly read in the chunk size line. 
                //There might be chunk extensions in there too but we may not need them                
                unsigned int endOfChunkData = m_strReceived.find ("\r\n");
                
                // make sure we have read at least some part of the message
                if (endOfChunkData == std::string::npos)
                {
                    do
                    {
                        m_Channel >> m_strReceived;
                        endOfChunkData = m_strReceived.find ("\r\n");
                    }while (endOfChunkData == std::string::npos);
                }
                
                int endOfChunkSize = endOfChunkData;

                // now get just the size of the chunk from the data
                // look to see if there are any extensions - these are put in brackets so look for those
                if (m_strReceived.substr (0, endOfChunkData).find ("(") != string::npos)
                {
                    // there are extensions so just get the chunk dataSize
                    endOfChunkSize = m_strReceived.find ("(");
                }

                // convert the hex String into the length of the chunk
                m_iContentLength = axtoi ((char *) m_strReceived.substr (0, endOfChunkSize).c_str ());
                // if the chunk size is zero then we have reached the footer
                // If we have reached the footer then we can throw it away because we don't need it
                if (m_iContentLength > 0)
                {
                    // now get the chunk without the CRLF
                    m_strReceived = m_strReceived.substr (endOfChunkData + 2);//, chunkSize);
                    // OK, now we have the chunk, we need to read in more chunks from the channel.
                    // we must have more chunks because we haven't reached the ")" which signifies the end of the
                    // now move the unparsed chunk along avoiding the 2 bytes of CRLF.
                    //unParsedChunks = unParsedChunks.substr (endOfChunkData + 2 + chunkSize + 2);
                    
                }
                else
                {
                    m_strReceived = "";
                }
            }
            else // Not chunked
            {
                //nothing to do here
            }

            //printf( "cont len = %ld, read = %ld", m_iContentLength, m_strReceived.length());

            m_pcReceived = m_strReceived.c_str();
            if ( m_pcReceived )
                m_iBytesLeft = strlen( m_pcReceived );
            else
                throw AxisTransportException( SERVER_TRANSPORT_BUFFER_EMPTY, "Reveved null" );

            m_iContentLength -= m_iBytesLeft;
        }
        catch ( AxisTransportException & e )
        {
            throw;
        }
        catch ( AxisException & e )
        {
            throw;
        }
        catch ( ... )
        {
            throw;
        }
    }
    if ( m_pcReceived )
    {
        int iToCopy = ( *pSize < m_iBytesLeft ) ? *pSize : m_iBytesLeft;
        strncpy( pcBuffer, m_pcReceived, iToCopy );
        m_iBytesLeft -= iToCopy;
        m_pcReceived += iToCopy;
        *pSize = iToCopy;
        return TRANSPORT_IN_PROGRESS;
    }
    else
    {
        m_bReadPastHTTPHeaders = false; // get ready for a new message
        m_strReceived = "";    //clear the message buffer in preperation of the next read
        return TRANSPORT_FINISHED;
    }
}


void Axis2Transport::setTransportProperty
( AXIS_TRANSPORT_INFORMATION_TYPE type, const char* value )
{
    const char * key = NULL;
    switch ( type )
    {
    case SOAPACTION_HEADER:
        key = "SOAPAction";
        break;
    case SERVICE_URI:   // need to set ?
        break;
    case OPERATION_NAME:   // need to set ?
        break;
    case SOAP_MESSAGE_LENGTH:
        key = "Content-Length"; // this Axis transport handles only HTTP
        break;
    default:
        ;
    }
    if ( !key )
        return ;
    setTransportProperty( key, value );
}

void Axis2Transport::setTransportProperty( const char* pcKey, const char* pcValue )
{
    bool b_KeyFound = false;    

    if ( strcmp( pcKey, "SOAPAction" ) == 0 || strcmp( pcKey, "Content-Length" ) == 0 )
    {
        std::string strKeyToFind = std::string(pcKey);
        
        for ( unsigned int i = 0; i < m_vHTTPHeaders.size(); i++ )
        {
            if ( m_vHTTPHeaders[ i ].first == strKeyToFind )
            {
                m_vHTTPHeaders[ i ].second = ( string ) pcValue;
                b_KeyFound = true;                
                break;
            }
        }
    }

    if ( !b_KeyFound )
    {
        m_vHTTPHeaders.push_back ( std::make_pair ((string) pcKey, (string) pcValue ) );
    }
}

const char* Axis2Transport::getTransportProperty( AXIS_TRANSPORT_INFORMATION_TYPE eType )
{
    //TODO
    return 0;
}

const char* Axis2Transport::getServiceName()
{
    //Assume SOAPAction header to contain service name
    for ( unsigned int i = 0; i < m_vHTTPHeaders.size(); i++ )
    {
        if ( m_vHTTPHeaders[ i ].first == "SOAPAction" )
        {
            return ((string) m_vHTTPHeaders[ i ].second ).c_str();
        }
    }

    return NULL;
}

AXIS_PROTOCOL_TYPE Axis2Transport::getProtocol()
{
    return APTHTTP;
}


/**
 * This method is supposed to return whether it is http GET or POST
 */
int Axis2Transport::getSubProtocol()
{
    //TODO
    return 0;
}

void
Axis2Transport::setProxy( const char* pcProxyHost, unsigned int uiProxyPort )
{
    m_strProxyHost = pcProxyHost;
    m_uiProxyPort = uiProxyPort;
    m_bUseProxy = true;
}

void Axis2Transport::setTimeout( const long lSeconds )
{
    m_Channel.setTimeout( lSeconds );
}

const char* Axis2Transport::getHTTPProtocol()
{
    return m_strHTTPProtocol.c_str();
}

void Axis2Transport::setHTTPProtocol( int iVersion )
{
    switch(iVersion)
    {
    case 0:
        m_strHTTPProtocol = "HTTP/1.0";
        break;
    case 1:
    default:
        m_strHTTPProtocol = "HTTP/1.1";
    }
}


extern "C"
{
    STORAGE_CLASS_INFO
    int CreateInstance( SOAPTransport **inst )
    {
        *inst = new Axis2Transport();
        if ( *inst )
        {
            return AXIS_SUCCESS;
        }
        return AXIS_FAIL;
    }
    STORAGE_CLASS_INFO
    int DestroyInstance( SOAPTransport * inst )
    {
        if ( inst )
        {
            delete inst;
            return AXIS_SUCCESS;
        }
        return AXIS_FAIL;
    }
}

extern "C"
{
    STORAGE_CLASS_INFO
    void initializeLibrary( void )
    {
        // Do init actions
    }
}

extern "C"
{
    STORAGE_CLASS_INFO
    void uninitializeLibrary( void )
    {
        // Do uninit actions
    }
}


 /*
  * This converts an ascii hex string to int converter.
  */
int
axtoi (char *hexStg)
{
    int
	n = 0;			// position in string
    int
	m = 0;			// position in digit[] to shift
    int
	count;			// loop index
    int
	intValue = 0;		// integer value of hex string
    int
	digit[32];		// hold values to convert
    while (n < 32)
    {
	if (hexStg[n] == '\0')
	    break;
	if (hexStg[n] > 0x29 && hexStg[n] < 0x40)	//if 0 to 9
	    digit[n] = hexStg[n] & 0x0f;	//convert to int
	else if (hexStg[n] >= 'a' && hexStg[n] <= 'f')	//if a to f
	    digit[n] = (hexStg[n] & 0x0f) + 9;	//convert to int
	else if (hexStg[n] >= 'A' && hexStg[n] <= 'F')	//if A to F
	    digit[n] = (hexStg[n] & 0x0f) + 9;	//convert to int
	else
	    break;
	n++;
    }
    count = n;
    m = n - 1;
    n = 0;
    while (n < count)
    {
	// digit[n] is value of hex digit at position n
	// (m << 2) is the number of positions to shift
	// OR the bits into return value
	intValue = intValue | (digit[n] << (m << 2));
	m--;			// adjust the position to set
	n++;			// next digit to process
    }
    return (intValue);
}
