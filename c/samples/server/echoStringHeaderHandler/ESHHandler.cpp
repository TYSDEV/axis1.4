/* -*- C++ -*- */

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
 *
 * @author Roshan Weerasuriya (roshan@jkcs.slt.lk, roshan@opensource.lk)
 *
 */

// ESHHandler.cpp: implementation of the ESHHandler class.
//
//////////////////////////////////////////////////////////////////////

#include "ESHHandler.h"
#include "../../../../common/GDefine.h"
#include "../../../../common/IHandlerSoapSerializer.h"
#include "../../../../common/IHandlerSoapDeSerializer.h"
#include "../../../../soap/HeaderBlock.h"
#include "../../../../soap/SoapHeader.h"
#include "../../../../soap/BasicNode.h"
#include <iostream>

//////////////////////////////////////////////////////////////////////
// Construction/Destruction
//////////////////////////////////////////////////////////////////////

ESHHandler::ESHHandler()
{

}

ESHHandler::~ESHHandler()
{

}

string ESHHandler::GetOption(string sArg)
{
	return NULL;
}

void ESHHandler::SetOption(string sOption, string Value)
{

}

void ESHHandler::SetOptionList(map<string, string>* OptionList)
{

}

int ESHHandler::Invoke(IMessageData *pIMsg)
{
	if(pIMsg->isPastPivot()) {
		//this is a response
		IHandlerSoapSerializer* pISZ;
		pIMsg->getSoapSerializer(&pISZ);

		IHeaderBlock* pIHeaderBlock= pISZ->createHeaderBlock();

		pIHeaderBlock->setLocalName("echoMeStringResponse");
		pIHeaderBlock->setUri("http://soapinterop.org/echoheader/");
		pIHeaderBlock->setPrefix("m");

		string sHeaderVal= pIMsg->getProperty(string("EchoStringHeaderHandlerPr1.id"));
		sHeaderVal+= " After Append by Handler";
		pIHeaderBlock->setValue(sHeaderVal);

		BasicNode* pBasicNode = pIHeaderBlock->createChild(CHARACTER_NODE);
		pBasicNode->setValue(sHeaderVal);
		pIHeaderBlock->addChild(pBasicNode);

		cout<<"in the header invoke"<<endl;
		
	} else {
		//this is a request
		IHandlerSoapDeSerializer* pIHandlerSoapDeSerializer;
		pIMsg->getSoapDeSerializer(&pIHandlerSoapDeSerializer);

		ISoapHeader* pISoapHeader= pIHandlerSoapDeSerializer->GetHeader();
		IHeaderBlock* pIHeaderBlock= pISoapHeader->getHeaderBlock();
		BasicNode* pBasicNode= pIHeaderBlock->getFirstChild();
		
		string sHeaderValue;
		if((pBasicNode->getNodeType()) == CHARACTER_NODE) {
			sHeaderValue= pBasicNode->getValue();
		}

		pIMsg->setProperty(string("EchoStringHeaderHandlerPr1.id"), sHeaderValue);
		//pIMsg->setProperty(string("EchoSt"), sHeaderValue);
		
	}

	return SUCCESS;
}

void ESHHandler::OnFault(IMessageData *pIMsg)
{

}

int ESHHandler::Init()
{
	//do any initialization, resetting of values

	return SUCCESS;
}

int ESHHandler::Fini()
{
	//do any finalizatoin

	return SUCCESS;
}
