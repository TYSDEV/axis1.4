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
 * @author Susantha Kumara (skumara@virtusa.com)
 *
 */

// SoapDeSerializer.cpp: implementation of the SoapDeSerializer class.
//
//////////////////////////////////////////////////////////////////////
#include <axis/soap/SoapEnvelope.h>
#include <axis/soap/SoapHeader.h>
#include <axis/soap/SoapMethod.h>
#include <axis/soap/SoapBody.h>
#include <axis/soap/SoapFault.h>
#include <axis/soap/SoapDeSerializer.h>
#include <axis/common/GDefine.h>
#include <axis/common/Packet.h>

//////////////////////////////////////////////////////////////////////
// Construction/Destruction
//////////////////////////////////////////////////////////////////////

SoapDeSerializer::SoapDeSerializer()
{
	m_pHandler = new XMLStreamHandler();
	m_pParser = XMLReaderFactory::createXMLReader();
    m_pParser->setContentHandler(m_pHandler);
    m_pParser->setErrorHandler(m_pHandler);
	m_pInputStream = NULL;
	m_pLastArrayParam = NULL;
	m_Status = AXIS_SUCCESS;
}

SoapDeSerializer::~SoapDeSerializer()
{
	delete m_pHandler;
	delete m_pParser;
}

int SoapDeSerializer::SetInputStream(const Ax_soapstream* pInputStream)
{
	m_pInputStream = pInputStream;
	//---------------------start--------------------------
	//Deserialize
	//---------START XERCES SAX2 SPCIFIC CODE---------//
	//a huge buffer to store the whole soap request stream
	//to store the number of chars returned by get_request_bytes
	int nChars = 0;
	//request a huge number of bytes to get the whole soap request
	//when pull parsing is used this should change
	if (NULL != m_pInputStream->transport.pGetFunct)
		m_pInputStream->transport.pGetFunct(m_hugebuffer, HUGE_BUFFER_SIZE, &nChars, m_pInputStream->str.ip_stream);
	//if no soap then quit
	if (nChars <= 0) return AXIS_FAIL;
	MemBufInputSource Input((const unsigned char*)m_hugebuffer, nChars , "bufferid");
	//Input.setEncoding("UTF-16");
	m_pParser->parse(Input);
	return m_pHandler->Success();
}

SoapEnvelope* SoapDeSerializer::GetEnvelope()
{
	return m_pHandler->m_pEnv;
}

ISoapHeader* SoapDeSerializer::GetHeader()
{
	//actually here a dynamic cast is not needed. But it is
	// done for safe side, incase SoapHeader derives from 
	// more that one interface and the deriving order changes.
	return static_cast<ISoapHeader*>(m_pHandler->m_pHead);
}

SoapBody* SoapDeSerializer::GetBody()
{
	return m_pHandler->m_pBody;
}

SoapMethod* SoapDeSerializer::GetMethod()
{
	return m_pHandler->m_pMethod;
}

SoapFault* SoapDeSerializer::GetFault()
{
	return m_pHandler->m_pFault;
}

//this function is more usefull with XMLpull parser
int SoapDeSerializer::Deserialize(IParam* pIParam, int bHref)
{
	Param* pParam = (Param*)pIParam;
	//if multiref add to m_Multirefs map
	//else call its deserializer
	switch (pParam->m_Type)
	{
	case XSD_ARRAY:
		if (pParam->m_Value.pArray && pParam->m_Value.pArray->m_value.sta)
		{
			pParam->m_Value.pArray->DeSerialize(this);
			//After deserializing the array is handed over to the Application or web service
			//So dont keep it here any longer.
			pParam->m_Value.pArray->m_value.sta = NULL;
		}
		else
			return AXIS_FAIL;
		break;
	case USER_TYPE:
		if (pParam->m_Value.pCplxObj)
		{
			if (pParam->m_Value.pCplxObj->pObject && pParam->m_Value.pCplxObj->pDZFunct)
				pParam->m_Value.pCplxObj->pDZFunct(pParam->m_Value.pCplxObj->pObject, this);
			//After deserializing the complex object is handed over to the Application or web service
			//So dont keep it here any longer.
			pParam->m_Value.pCplxObj->pObject = NULL;
		}
		else
			return AXIS_FAIL;
		break;
	default:; //no need of calling this function for basic types - error condition
	}
	return AXIS_SUCCESS;
}

IParam* SoapDeSerializer::GetParam()
{
	IParam* pNextParam = m_pHandler->GetParam();
	if ((AXIS_SUCCESS != m_Status) || (NULL == pNextParam) || (AXIS_SUCCESS != m_pHandler->m_Success))
	{
		m_Status = AXIS_FAIL;
		return NULL;
	}
	return pNextParam;
}

int SoapDeSerializer::Init()
{
	m_hugebuffer[0] = '\0';
	m_pLastArrayParam = NULL;
	m_pHandler->Init();
	m_Status = AXIS_SUCCESS;
	return AXIS_SUCCESS;
}



const AxisChar* SoapDeSerializer::GetMethodName()
{
	if (m_pHandler->m_pMethod)
	{
		return m_pHandler->m_pMethod->getMethodName();
	}
	return NULL;
}

int SoapDeSerializer::GetVersion()
{
	return m_pHandler->m_nSoapVersion;	
}

Axis_Array SoapDeSerializer::GetArray(void* pDZFunct, void* pCreFunct, void* pDelFunct, void* pSizeFunct, const AxisChar* pchTypeName, const AxisChar* pchURI)
{
	Axis_Array Array = {NULL, 0};
	Param *param = (Param*)GetParam();
	if (!param) return Array;
	if (XSD_ARRAY != param->GetType()) return Array; //UNEXPECTED_PARAM_TYPE
	/* Check whether the array is of expected type */
//	if (param->GetTypeName() == pchTypeName) return Array; //UNEXPECTED_PARAM_TYPE
//	if (param->GetURI() == pchURI) return Array; //UNEXPECTED_PARAM_TYPE
	
	if(AXIS_SUCCESS != param->GetArraySize(&Array.m_Size)) return Array;
	if (Array.m_Size > 0)
	{
		Array.m_Array = ((AXIS_OBJECT_CREATE_FUNCT)pCreFunct)(true, Array.m_Size);
		if (!Array.m_Array) 
		{
			Array.m_Size = 0;
			return Array;
		}
	}
	else 
		return Array; //CF_ZERO_ARRAY_SIZE_ERROR
	if (AXIS_SUCCESS != param->SetArrayElements((void*)(Array.m_Array), (AXIS_DESERIALIZE_FUNCT)pDZFunct, 
		(AXIS_OBJECT_DELETE_FUNCT)pDelFunct, (AXIS_OBJECT_SIZE_FUNCT)pSizeFunct))
	{
		((AXIS_OBJECT_DELETE_FUNCT)pDelFunct)(Array.m_Array, true, Array.m_Size);
		Array.m_Array = NULL;
		Array.m_Size = 0;
		return Array;
	}
	if (AXIS_SUCCESS != Deserialize(param,0))
	{
		((AXIS_OBJECT_DELETE_FUNCT)pDelFunct)(Array.m_Array, true, Array.m_Size);
		Array.m_Array = NULL;
		Array.m_Size = 0;
		return Array;
	}
	return Array;
}

int SoapDeSerializer::GetArraySize()
{
	int nSize = 0;
	m_pLastArrayParam = (Param*)GetParam();
	if (m_pLastArrayParam)
		m_pLastArrayParam->GetArraySize(&nSize);
	return nSize;
}

int SoapDeSerializer::GetArray(Axis_Array* pArray, XSDTYPE nType)
{
	if (!m_pLastArrayParam) 
	{ //This cannot happen unless there is something wrong with wrapper
		DeleteArray(pArray, nType);
		return AXIS_FAIL;
	}
	if (XSD_ARRAY != m_pLastArrayParam->GetType())//UNEXPECTED_PARAM_TYPE
	{ //This cannot happen unless there is something wrong with wrapper
		DeleteArray(pArray, nType);
		return AXIS_FAIL;
	}
	
	if (AXIS_SUCCESS != m_pLastArrayParam->SetArrayElements((void*)(pArray->m_Array)))
	{
		DeleteArray(pArray, nType);
		return AXIS_FAIL;
	}
	if (AXIS_SUCCESS != Deserialize(m_pLastArrayParam,0))
	{
		DeleteArray(pArray, nType);
		return AXIS_FAIL;
	}
	return AXIS_SUCCESS;
}

Axis_Array SoapDeSerializer::GetArray(XSDTYPE nType)
{
	Axis_Array Array = {NULL, 0};
	Param *param = (Param*)GetParam();
	if (!param) return Array;
	if (XSD_ARRAY != param->GetType()) return Array; //UNEXPECTED_PARAM_TYPE
	
	if(AXIS_SUCCESS != param->GetArraySize(&Array.m_Size)) return Array;
	if (Array.m_Size > 0)
	{
		Array.m_Array = CreateArray(nType, Array.m_Size);
		if (!Array.m_Array) 
		{
			Array.m_Size = 0;
			return Array;
		}
	}
	else 
		return Array; //CF_ZERO_ARRAY_SIZE_ERROR
	if (AXIS_SUCCESS != param->SetArrayElements((void*)(Array.m_Array)))
	{
		DeleteArray(&Array, nType);
		return Array;
	}
	if (AXIS_SUCCESS != Deserialize(param,0))
	{
		DeleteArray(&Array, nType);
		return Array;
	}
	return Array;
}
void* SoapDeSerializer::GetObject(void* pDZFunct, void* pCreFunct, void* pDelFunct, const AxisChar* pchTypeName, const AxisChar* pchURI)
{
	Param *param = (Param*)GetParam();
	if (!param) return NULL;
	if (USER_TYPE != param->GetType()) return NULL; //UNEXPECTED_PARAM_TYPE
	/* Check whether the object is of expected type */
//	if (param->GetTypeName() == pchTypeName) return NULL; //UNEXPECTED_PARAM_TYPE
//	if (param->GetURI() == pchURI) return NULL; //UNEXPECTED_PARAM_TYPE

	void* pObject = ((AXIS_OBJECT_CREATE_FUNCT)pCreFunct)();
	if (!pObject) return NULL;

	if (AXIS_SUCCESS != param->SetUserType(pObject, (AXIS_DESERIALIZE_FUNCT)pDZFunct, (AXIS_OBJECT_DELETE_FUNCT)pDelFunct))
	{
		((AXIS_OBJECT_DELETE_FUNCT)pDelFunct)(pObject);
		return NULL;
	}
	if (AXIS_SUCCESS != Deserialize(param,0))
	{
		((AXIS_OBJECT_DELETE_FUNCT)pDelFunct)(pObject);
		return NULL;
	}
	return pObject;
}

int SoapDeSerializer::GetInt()
{
	int Value = 0;
	Param *param = (Param*)GetParam();
	if (!param) return 0;
	if (AXIS_SUCCESS != param->GetInt(&Value)) m_Status = AXIS_FAIL;
	return Value;
}
unsigned int SoapDeSerializer::GetUnsignedInt()
{
	unsigned int Value = 0;
	Param *param = (Param*)GetParam();
	if (!param) return 0;
	if (AXIS_SUCCESS != param->GetUnsignedInt(&Value)) m_Status = AXIS_FAIL;
	return Value;
}
short SoapDeSerializer::GetShort()
{
	short Value = 0;
	Param *param = (Param*)GetParam();
	if (!param) return 0;
	if (AXIS_SUCCESS != param->GetShort(&Value)) m_Status = AXIS_FAIL;
	return Value;
}
unsigned short SoapDeSerializer::GetUnsignedShort()
{
	unsigned short Value = 0;
	Param *param = (Param*)GetParam();
	if (!param) return 0;
	if (AXIS_SUCCESS != param->GetUnsignedShort(&Value)) m_Status = AXIS_FAIL;
	return Value;
}
char SoapDeSerializer::GetByte()
{
	char Value = 0;
	Param *param = (Param*)GetParam();
	if (!param) return 0;
	if (AXIS_SUCCESS != param->GetByte(&Value)) m_Status = AXIS_FAIL;
	return Value;
}
unsigned char SoapDeSerializer::GetUnsignedByte()
{
	unsigned char Value = 0;
	Param *param = (Param*)GetParam();
	if (!param) return 0;
	if (AXIS_SUCCESS != param->GetUnsignedByte(&Value)) m_Status = AXIS_FAIL;
	return Value;
}
long SoapDeSerializer::GetLong()
{
	long Value = 0;
	Param *param = (Param*)GetParam();
	if (!param) return 0;
	if (AXIS_SUCCESS != param->GetLong(&Value)) m_Status = AXIS_FAIL;
	return Value;
}
long SoapDeSerializer::GetInteger()
{
	long Value = 0;
	Param *param = (Param*)GetParam();
	if (!param) return 0;
	if (AXIS_SUCCESS != param->GetInteger(&Value)) m_Status = AXIS_FAIL;
	return Value;
}
unsigned long SoapDeSerializer::GetUnsignedLong()
{
	unsigned long Value = 0;
	Param *param = (Param*)GetParam();
	if (!param) return 0;
	if (AXIS_SUCCESS != param->GetUnsignedLong(&Value)) m_Status = AXIS_FAIL;
	return Value;
}
float SoapDeSerializer::GetFloat()
{
	float Value = 0;
	Param *param = (Param*)GetParam();
	if (!param) return 0;
	if (AXIS_SUCCESS != param->GetFloat(&Value)) m_Status = AXIS_FAIL;
	return Value;
}
double SoapDeSerializer::GetDouble()
{
	double Value = 0;
	Param *param = (Param*)GetParam();
	if (!param) return 0;
	if (AXIS_SUCCESS != param->GetDouble(&Value)) m_Status = AXIS_FAIL;
	return Value;
}
double SoapDeSerializer::GetDecimal()
{
	double Value = 0;
	Param *param = (Param*)GetParam();
	if (!param) return 0;
	if (AXIS_SUCCESS != param->GetDecimal(&Value)) m_Status = AXIS_FAIL;
	return Value;
}
const AxisChar* SoapDeSerializer::GetString()
{
	const AxisChar* Value = NULL;
	Param *param = (Param*)GetParam();
	if (!param) return NULL;
	if (AXIS_SUCCESS != param->GetString(&Value)) m_Status = AXIS_FAIL;
	return Value;
}
const AxisChar* SoapDeSerializer::GetAnyURI()
{
	const AxisChar* Value = NULL;
	Param *param = (Param*)GetParam();
	if (!param) return NULL;
	if (AXIS_SUCCESS != param->GetAnyURI(&Value)) m_Status = AXIS_FAIL;
	return Value;
}
const AxisChar* SoapDeSerializer::GetQName()
{
	const AxisChar* Value = NULL;
	Param *param = (Param*)GetParam();
	if (!param) return NULL;
	if (AXIS_SUCCESS != param->GetQName(&Value)) m_Status = AXIS_FAIL;
	return Value;
}
const AxisChar* SoapDeSerializer::GetHexString()
{
	const AxisChar* Value = NULL;
	Param *param = (Param*)GetParam();
	if (!param) return NULL;
	if (AXIS_SUCCESS != param->GetHexString(&Value)) m_Status = AXIS_FAIL;
	return Value;
}
const AxisChar* SoapDeSerializer::GetBase64String()
{
	const AxisChar* Value = NULL;
	Param *param = (Param*)GetParam();
	if (!param) return NULL;
	if (AXIS_SUCCESS != param->GetBase64String(&Value)) m_Status = AXIS_FAIL;
	return Value;
}
struct tm SoapDeSerializer::GetDateTime()
{
	tm tmTemp;
	Param *param = (Param*)GetParam();
	if (!param) return tmTemp;
	if (AXIS_SUCCESS != param->GetDateTime(&tmTemp)) m_Status = AXIS_FAIL;
	return tmTemp;
}
struct tm SoapDeSerializer::GetDate()
{
	tm tmTemp;
	Param *param = (Param*)GetParam();
	if (!param) return tmTemp;
	if (AXIS_SUCCESS != param->GetDate(&tmTemp)) m_Status = AXIS_FAIL;
	return tmTemp;
}
struct tm SoapDeSerializer::GetTime()
{
	tm tmTemp;
	Param *param = (Param*)GetParam();
	if (!param) return tmTemp;
	if (AXIS_SUCCESS != param->GetTime(&tmTemp)) m_Status = AXIS_FAIL;
	return tmTemp;
}
long SoapDeSerializer::GetDuration()
{
	long Value = 0;
	Param *param = (Param*)GetParam();
	if (!param) return 0;
	if (AXIS_SUCCESS != param->GetDuration(&Value)) m_Status = AXIS_FAIL;
	return Value;
}

void* SoapDeSerializer::CreateArray(XSDTYPE nType, int nSize)
{
	switch (nType)
	{
	case XSD_INT:
	case XSD_UNSIGNEDINT:
	case XSD_BOOLEAN:
		return new int[nSize];
	case XSD_FLOAT:
		return new float[nSize];
	case XSD_STRING:
	case XSD_HEXBINARY:
	case XSD_BASE64BINARY:
	case XSD_ANYURI:
	case XSD_QNAME:
	case XSD_NOTATION:
		return new AxisString[nSize];
	case XSD_LONG:
	case XSD_UNSIGNEDLONG:
	case XSD_INTEGER:
	case XSD_DURATION:
		return new long[nSize];
	case XSD_SHORT:
	case XSD_UNSIGNEDSHORT:
		return new short[nSize];
	case XSD_BYTE:
	case XSD_UNSIGNEDBYTE:
		return new char[nSize];
	case XSD_DOUBLE:
	case XSD_DECIMAL:
		return new double[nSize];
	case XSD_DATETIME:
	case XSD_TIME:
	case XSD_DATE:
	case XSD_YEARMONTH:
	case XSD_YEAR:
	case XSD_MONTHDAY:
	case XSD_DAY:
	case XSD_MONTH:
		return new tm[nSize];
	default:
		return NULL;
	}
}

void SoapDeSerializer::DeleteArray(Axis_Array* pArray , XSDTYPE nType)
{
	switch (nType)
	{
	case XSD_INT:
	case XSD_UNSIGNEDINT:
	case XSD_BOOLEAN:
		delete [] ((int*)pArray->m_Array); break;
	case XSD_FLOAT:
		delete [] ((float*)pArray->m_Array); break;
	case XSD_STRING:
	case XSD_HEXBINARY:
	case XSD_BASE64BINARY:
	case XSD_ANYURI:
	case XSD_QNAME:
	case XSD_NOTATION:
		delete [] ((AxisString*)pArray->m_Array); break;
	case XSD_LONG:
	case XSD_UNSIGNEDLONG:
	case XSD_INTEGER:
	case XSD_DURATION:
		delete [] ((long*)pArray->m_Array); break;
	case XSD_SHORT:
	case XSD_UNSIGNEDSHORT:
		delete [] ((short*)pArray->m_Array); break;
	case XSD_BYTE:
	case XSD_UNSIGNEDBYTE:
		delete [] ((char*)pArray->m_Array); break;
	case XSD_DOUBLE:
	case XSD_DECIMAL:
		delete [] ((double*)pArray->m_Array); break;
	case XSD_DATETIME:
	case XSD_TIME:
	case XSD_DATE:
	case XSD_YEARMONTH:
	case XSD_YEAR:
	case XSD_MONTHDAY:
	case XSD_DAY:
	case XSD_MONTH:
		delete [] ((tm*)pArray->m_Array); break;
	default:
		break;
	}
	pArray->m_Array = NULL;
	pArray->m_Size = 0;
}
