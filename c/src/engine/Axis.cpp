/*
 * The Apache Software License, Version 1.1
 *
 *
 * Copyright (c) 2002 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "SOAP" and "Apache Software Foundation" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache",
 *    nor may "Apache" appear in their name, without prior written
 *    permission of the Apache Software Foundation.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 *
 *
 * @author Sanjaya Sinharage (sanjayasing@opensource.lk)
 * @author Susantha Kumara (skumara@virtusa.com)
 *
 */
#ifdef WIN32
#pragma warning (disable : 4503)
#endif

#ifdef WIN32
#include <Windows.h> //for Sleep(0);
#else
#include <unistd.h>
#endif

#include <axis/engine/ServerAxisEngine.h>
#include <axis/common/AxisTrace.h>
#include <stdio.h>
#include <stdlib.h>
#include <malloc.h>
#include <axis/common/Packet.h>
#include <string>
#include <map>

#include <axis/soap/SoapFaults.h>
#include <axis/soap/URIMapping.h>
#include <axis/engine/HandlerLoader.h>
#include <axis/engine/AppScopeHandlerPool.h>
#include <axis/engine/RequestScopeHandlerPool.h>
#include <axis/engine/SessionScopeHandlerPool.h>
#include <axis/engine/HandlerPool.h>
#include <axis/engine/SerializerPool.h>
#include <axis/engine/DeserializerPool.h>
#include <axis/wsdd/WSDDDeployment.h>
#include <axis/common/AxisUtils.h>
#include <axis/common/AxisConfig.h>
#include <axis/wsdd/WSDDKeywords.h>
#include <axis/common/AxisTrace.h>


#define BYTESTOREAD 64
//the relative location of the wsdl files hardcoded
#define WSDLDIRECTORY	"./Axis/wsdls/"

//define all global variables of the axisengine
#ifdef _AXISTRACE
unsigned char chEBuf[1024];
#endif

//synchronized global variables.
HandlerLoader* g_pHandlerLoader;
AppScopeHandlerPool* g_pAppScopeHandlerPool;
RequestScopeHandlerPool* g_pRequestScopeHandlerPool;
SessionScopeHandlerPool* g_pSessionScopeHandlerPool;
DeserializerPool* g_pDeserializerPool;
SerializerPool* g_pSerializerPool;
HandlerPool* g_pHandlerPool;
//un synchronized read-only global variables.
WSDDDeployment* g_pWSDDDeployment;
AxisConfig* g_pConfig;
SoapSerializer* g_pSZ;
AxisTrace* g_pAT;


extern "C" int process_request(Ax_soapstream *str)
{
//	AXISTRACE1("in axis.cpp");	
	int Status = AXIS_FAIL;
	FILE * WsddFile;
	char ReadBuffer[BYTESTOREAD];
	ReadBuffer[0] = '\0';

	const WSDDServiceMap* pSrvMap = NULL;
	WSDDServiceMap::const_iterator iter;
	WSDDService* pService = NULL;

	/* If there is no send function given in the Ax_soapstream struct */
	if (!str->transport.pSendFunct) return AXIS_FAIL;

	switch (str->trtype)
	{
		case APTHTTP:
			//Handle the POST method
			if (str->so.http.ip_method == AXIS_HTTP_POST)
			{
//				AXISTRACE1("method is POST");
				AxisEngine* engine = new ServerAxisEngine();	
				if (engine)
				{
					if (AXIS_SUCCESS == engine->Initialize())
					{
						Status = engine->Process(str);       
					}
					delete engine;
				}
			}
			//Handler the GET method
			else if (str->so.http.ip_method == AXIS_HTTP_GET)
			{
				//get the uri path
				//i.e "/abc/xyz/" part of http://somehost/abc/xyz/
				string sUri = str->so.http.uri_path;
				string sUriWOAxis = "";
				string sServiceName;
				bool bNoSlash = false;

				if (sUri.find(AXIS_URI_EXTENSION) != string::npos)
				{
					sUriWOAxis = sUri.substr(sUri.find(AXIS_URI_EXTENSION) + 6);
				}
				else
				{	
					bNoSlash = true;
				}

				if (sUriWOAxis.empty())
				{
					pSrvMap = g_pWSDDDeployment->GetWSDDServiceMap();
					if (!pSrvMap) 
					{
						str->transport.pSendFunct("<html><body>\
						<h1 align=\"center\">Welcome to Axis C++</h1>\
						<br>\
						<h2>Deployment Descripter Not Found</h2>\
						<br>\
						</body></html>", str->str.op_stream);

						return AXIS_FAIL;
					}
					str->transport.pSendFunct("<html><body>\
						<h1 align=\"center\">Welcome to Axis C++</h1>\
						<br>\
						<h2 align=\"center\">List of Deployed Web services<br></h2>\
						<table width=\"100%\" border=1 align=\"center\"><tbody>", str->str.op_stream);

					str->transport.pSendFunct("<tr><td width=\"20%\"><b>Web Service</b></td>\
						<td width=\"10%\" align=\"left\"><b>WSDL</b></td>\
						<td width=\"70%\"><b>Description</b></td></tr>", str->str.op_stream);
					for (iter = pSrvMap->begin();iter != pSrvMap->end();iter++)
					{
						pService = (*iter).second;
						str->transport.pSendFunct("<tr><td width=\"20%\">", str->str.op_stream);
						str->transport.pSendFunct((char *)pService->GetServiceName(), str->str.op_stream);
						str->transport.pSendFunct("</td><td width=\"10%\" align=\"left\"><a href=\"./", str->str.op_stream);
						if (bNoSlash) str->transport.pSendFunct("axis/", str->str.op_stream); 
						str->transport.pSendFunct((char *)pService->GetServiceName(), str->str.op_stream);
						str->transport.pSendFunct("?wsdl", str->str.op_stream);
						str->transport.pSendFunct("\">wsdl</a></td><td width=\"70%\">", str->str.op_stream);
						str->transport.pSendFunct((char *)pService->GetDescription(), str->str.op_stream);
						str->transport.pSendFunct("</td></tr>", str->str.op_stream);
					}
					str->transport.pSendFunct("</tbody></table>", str->str.op_stream);
					str->transport.pSendFunct("<br><p align=\"center\">Copyright � 2001-2003 The Apache Software Foundation<br></p></body></html>", str->str.op_stream);
					Status = AXIS_SUCCESS;
				}
				else 
				{
					sServiceName = WSDLDIRECTORY + sUriWOAxis + ".wsdl";
					//check whether wsdl file is available
					if((WsddFile = fopen(sServiceName.c_str(),"r"))==NULL)
					{
						str->transport.pSendFunct("<h3>Url not available</h3>", str->str.op_stream);
						Status = AXIS_SUCCESS;
						//handle the error
					}
					else
					{
						int charcount = 0;
						while((charcount = fread(ReadBuffer, 1, BYTESTOREAD-1, WsddFile)) != 0)
						{
							*(ReadBuffer + charcount) = '\0';
							str->transport.pSendFunct(ReadBuffer, str->str.op_stream);
  						}
						Status = AXIS_SUCCESS;
						fclose(WsddFile);
					}
				}
			}
		break;

		default:
			str->transport.pSendFunct("Unknown Protocol", str->str.op_stream);
		break;
	}
     
	return Status;
}

extern "C" int initialize_module(int bServer)
{
    int status = 0;
	//order of these initialization method invocation should not be changed
//	AXISTRACE1("inside initialize_module\n");
	XMLPlatformUtils::Initialize();
	AxisEngine::m_bServer = bServer;
	AxisUtils::Initialize();
	WSDDKeywords::Initialize();
	SoapKeywordMapping::Initialize();
	TypeMapping::Initialize();
	URIMapping::Initialize();
	SoapFault::initialize();
	ModuleInitialize();
	if (bServer) //no client side wsdd processing at the moment
	{
        int status = g_pConfig->ReadConfFile();/*Read from the configuration file*/
        if(status == AXIS_SUCCESS)
        {
            char* pWsddPath = g_pConfig->GetWsddFilePath();            
            if (AXIS_SUCCESS != g_pWSDDDeployment->LoadWSDD(pWsddPath)) return AXIS_FAIL;            
            #if defined(__AXISTRACE__) 
            status = g_pAT->openFile();
            if(status == AXIS_FAIL)
            {
                return AXIS_FAIL;
            }
            #endif
        }
        else
        {
            AXISTRACE1("Reading from the configuration file failed. " \
            "Check for error in the configuration file", CRITICAL);
            /*TODO:Improve the AxisTrace so that it will log
            these kind of messages into a log file according to the
            critical level specified.
            */
            return AXIS_FAIL;
        }
	}
    else if(bServer == 0)//client side module initialization
    {
        #if defined(__AXISTRACE__) 
        status = g_pAT->openFileByClient();
        if(status == AXIS_FAIL)
        {
            return AXIS_FAIL;
        }
        #endif
    }
	return AXIS_SUCCESS;
}

extern "C" int uninitialize_module()
{
	XMLPlatformUtils::Terminate();
	ModuleUnInitialize();
	return AXIS_SUCCESS;
}


void Ax_Sleep(int nTime)
{
#ifdef WIN32
		Sleep(0);
#else
		sleep(0);
#endif
}

void ModuleInitialize()
{
	//synchronized global variables.
	g_pHandlerLoader = new HandlerLoader();
	g_pAppScopeHandlerPool = new AppScopeHandlerPool();
	g_pRequestScopeHandlerPool = new RequestScopeHandlerPool();
	g_pSessionScopeHandlerPool = new SessionScopeHandlerPool();
	g_pDeserializerPool = new DeserializerPool();
	g_pSerializerPool = new SerializerPool();
	g_pHandlerPool = new HandlerPool();
	//un synchronized read-only global variables.
	g_pWSDDDeployment = new WSDDDeployment();
    g_pConfig = new AxisConfig();
    g_pAT = new AxisTrace();
    
    
}

void ModuleUnInitialize()
{
	//synchronized global variables.
	delete g_pAppScopeHandlerPool;
	delete g_pRequestScopeHandlerPool;
	delete g_pSessionScopeHandlerPool;
	delete g_pHandlerLoader;
	delete g_pDeserializerPool;
	delete g_pSerializerPool;
	delete g_pHandlerPool;
	//un synchronized read-only global variables.
	delete g_pWSDDDeployment;
    delete g_pConfig;
    delete g_pAT;
}
