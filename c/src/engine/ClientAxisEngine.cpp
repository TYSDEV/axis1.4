// ClientAxisEngine.cpp: implementation of the ClientAxisEngine class.
//
//////////////////////////////////////////////////////////////////////

#include "ClientAxisEngine.h"
#include "../wsdd/WSDDDeployment.h"
#include "HandlerPool.h"

extern WSDDDeployment* g_pWSDDDeployment;
extern HandlerPool* g_pHandlerPool;

//////////////////////////////////////////////////////////////////////
// Construction/Destruction
//////////////////////////////////////////////////////////////////////

ClientAxisEngine::ClientAxisEngine()
{

}

ClientAxisEngine::~ClientAxisEngine()
{

}

MessageData* ClientAxisEngine::GetMessageData()
{
	return m_pMsgData;
}

int ClientAxisEngine::Process(Ax_soapstream* soap)
{
	int Status;
	const WSDDService* pService = NULL;
	string sSessionId = soap->sessionid;
	int nSoapVersion;

	do {
		//populate MessageData with transport information
		send_transport_information(soap);
		const char* cService = get_header(soap, SOAPACTIONHEADER);
		if(SUCCESS != m_pSZ->SetOutputStream(soap->str.op_stream))
		{
			break;
		}

/*
		//Get Global and Transport Handlers
		if(SUCCESS != (Status = InitializeHandlers(sSessionId, soap->trtype)))
		{
		  break; //do .. while(0)
		}
    	//Get Service specific Handlers from the pool if configured any
		if(SUCCESS != (Status = g_pHandlerPool->GetRequestFlowHandlerChain(&m_pSReqFChain, sSessionId, pService)))
		{        
		  break; //do .. while(0)
		}
		if(SUCCESS != (Status = g_pHandlerPool->GetResponseFlowHandlerChain(&m_pSResFChain, sSessionId, pService)))
		{        
		  break; //do .. while(0)
		}
*/

		//Invoke all handlers and then the remote webservice
		Status = Invoke(m_pMsgData); //we generate response in the same way even if this has failed
	}
	while(0);

		receive_transport_information(soap);
		m_pDZ->SetInputStream(soap->str.ip_stream);

	//Pool back the Service specific handlers
	if (m_pSReqFChain) g_pHandlerPool->PoolHandlerChain(m_pSReqFChain, sSessionId);
	if (m_pSResFChain) g_pHandlerPool->PoolHandlerChain(m_pSResFChain, sSessionId);
	//Pool back the Global and Transport handlers
	//UnInitializeHandlers(sSessionId, soap->trtype);
	return Status;
}

int ClientAxisEngine::Invoke(MessageData* pMsg)
{
	enum AE_LEVEL {AE_START=1, AE_TRH, AE_GLH, AE_SERH, AE_SERV};
	int Status = FAIL;
	int level = AE_START;
	/*
	No Client side handlers for now. Therefore returns SUCCESS
	*/
	Status = SUCCESS;

	/*
	do
	{
		//invoke client side service specific request handlers
		if (m_pSReqFChain)
		{
			if(SUCCESS != (Status = m_pSReqFChain->Invoke(pMsg)))
			{
				m_pSZ->setSoapFault(SoapFault::getSoapFault(SF_CLIENTHANDLERFAILED));
				break; //do .. while (0)
			}
		}
//		AXISTRACE1("AFTER invoke service specific request handlers");
		level++; //AE_SERH		//invoke transport request handlers
		//invoke global request handlers
		if (m_pGReqFChain)
		{
			if(SUCCESS != (Status = m_pGReqFChain->Invoke(pMsg)))
			{
				m_pSZ->setSoapFault(SoapFault::getSoapFault(SF_CLIENTHANDLERFAILED));
				break; //do .. while (0)
			}		
		}
//        AXISTRACE1("AFTER invoke global request handlers");
		level++; //AE_GLH	
		if (m_pTReqFChain) {
			if(SUCCESS != (Status = m_pTReqFChain->Invoke(pMsg)))
			{
				m_pSZ->setSoapFault(SoapFault::getSoapFault(SF_CLIENTHANDLERFAILED));
				break; //do .. while (0)
			}
		}
//		AXISTRACE1("AFTER invoke transport request handlers");
		level++; // AE_TRH


	}
	while(0);
	*/

	/*
	switch (level)
	{
	case AE_SERV: //everything success
		Status = SUCCESS;
		//no break;
	case AE_SERH: //actual web service handler has failed
		//invoke web service specific response handlers
		if (m_pSResFChain)
		{
			m_pSResFChain->Invoke(pMsg);
		}
		//no break;
	case AE_GLH: //web service specific handlers have failed
		//invoke global response handlers
		if (m_pGResFChain)
		{
			m_pGResFChain->Invoke(pMsg);
		}
		//no break;
	case AE_TRH: //global handlers have failed
		if (m_pTResFChain) 
		{
			m_pTResFChain->Invoke(pMsg);
		}
		//no break;
	case AE_START:;//transport handlers have failed
	};
	*/
//	AXISTRACE1("end axisengine process()");
	return Status;
}

void ClientAxisEngine::OnFault(MessageData* pMsg)
{

}
