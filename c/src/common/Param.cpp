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

// Param.cpp: implementation of the Param class.
//
//////////////////////////////////////////////////////////////////////

#include "Param.h"
#include "ArrayBean.h"
#include "BasicTypeSerializer.h"
#include <stdlib.h>

//////////////////////////////////////////////////////////////////////
// Construction/Destruction
//////////////////////////////////////////////////////////////////////

Param::Param(const Param& param)
{
	m_sName = param.m_sName;
	m_sValue = param.m_sValue;
	m_Type = param.m_Type;	
	if (m_Type == USER_TYPE) 
	{
		m_Value.pCplxObj = new ComplexObjectHandler;
		m_Value.pCplxObj->m_TypeName = param.m_Value.pCplxObj->m_TypeName;
		m_Value.pCplxObj->m_URI = param.m_Value.pCplxObj->m_URI;
	}
	else if(m_Type == XSD_ARRAY)
	{
		m_Value.pArray = new ArrayBean();
		m_Value.pArray->m_TypeName = param.m_Value.pArray->m_TypeName;
		m_Value.pArray->m_URI = param.m_Value.pArray->m_URI;
		m_Value.pArray->m_type = param.m_Value.pArray->m_type;
		m_Value.pArray->m_size = param.m_Value.pArray->m_size;
		m_Value.pArray->m_ItemName = param.m_Value.pArray->m_ItemName;
		//copy constructor is not intended to use to copy the array in
		//union v
	}	
	else 
	{
		m_Value = param.m_Value;
	}
}

Param::Param(const AxisChar* str, XSDTYPE type)
{
	m_sValue = str;
	m_Type = type;
	switch (type)
	{
	case XSD_STRING: m_sName = L"String"; break;
	case XSD_BASE64BINARY: m_sName = L"Base64BinaryString"; break;
	case XSD_HEXBINARY: m_sName = L"HexBinaryString"; break;
	}
}

Param::Param(int nValue)
{
	m_sName = L"Int";
	m_Value.nValue = nValue;
	m_Type = XSD_INT;
}

Param::Param(float fValue)
{
	m_sName = L"Float";
	m_Value.fValue = fValue;
	m_Type = XSD_FLOAT;
}

Param::Param(double dValue)
{
	m_sName = L"Double";
	m_Value.dValue = dValue;
	m_Type = XSD_DOUBLE;
}

Param::~Param()
{
	switch (m_Type){
	case XSD_ARRAY:
		if (m_Value.pArray) delete m_Value.pArray;
		break;
	case USER_TYPE:
		if (m_Value.pCplxObj->pObject) m_Value.pCplxObj->pDelFunct(m_Value.pCplxObj->pObject);
		delete m_Value.pCplxObj;
		break;
	default:;
	}
}

const AxisString& Param::GetString()
{
	if (m_Type == XSD_STRING){}
	else if (m_Type == XSD_UNKNOWN) //see GetInt() to see why we do this
	{
		m_Type = XSD_STRING;
	}
	else 
	{
		//exception
	}
	return m_sValue;
}

const AxisString& Param::GetHexString()
{
	if (m_Type == XSD_HEXBINARY){}
	else if (m_Type == XSD_UNKNOWN) //see GetInt() to see why we do this
	{
		m_Type = XSD_HEXBINARY;
	}
	else 
	{
		//exception
	}
	return m_sValue;
}

const AxisString& Param::GetBase64String()
{
	if (m_Type == XSD_BASE64BINARY){}
	else if (m_Type == XSD_UNKNOWN) //see GetInt() to see why we do this
	{
		m_Type = XSD_BASE64BINARY;
	}
	else 
	{
		//exception
	}
	return m_sValue;
}

int Param::GetInt()
{
	if (m_Type == XSD_INT){}
	else if (m_Type == XSD_UNKNOWN) 
	{
		//this situation comes when the soap does not contain the type of a parameter
		//but the wrapper class method knows exactly what the type of this parameter is.
		//then the deserializer must have put the value as a string and type as XSD_UNKNOWN.
		//so convert the m_sValue in to an int and change the types etc
		m_Type = XSD_INT;
		SetValue(m_sValue.c_str());
	}
	else 
	{
		//exception
	}
	return m_Value.nValue;
}

float Param::GetFloat()
{
	if (m_Type == XSD_FLOAT){}
	else if (m_Type == XSD_UNKNOWN)
	{
		m_Type = XSD_FLOAT;
		SetValue(m_sValue.c_str());
	}
	else
	{
		//exception
	}
	return m_Value.fValue;
}

XSDTYPE Param::GetType() const
{
	return m_Type;
}

int Param::serialize(IWrapperSoapSerializer& pSZ)
{
	AxisString ATprefix;
	switch (m_Type){
	case XSD_INT:
		pSZ << m_BTSZ.serialize(m_sName.c_str(), m_Value.nValue);
		break;
	case XSD_FLOAT:
		pSZ << m_BTSZ.serialize(m_sName.c_str(), m_Value.fValue);
		break;
	case XSD_STRING:
		pSZ << m_BTSZ.serialize(m_sName.c_str(), m_sValue.c_str());
		break;
	case XSD_HEXBINARY:
		pSZ << m_BTSZ.serialize(m_sName.c_str(), m_sValue.c_str(), XSD_HEXBINARY);
		break;
	case XSD_BASE64BINARY:
		pSZ << m_BTSZ.serialize(m_sName.c_str(), m_sValue.c_str(), XSD_BASE64BINARY);
		break;
	case XSD_ARRAY:
		//pSZ << "<abc:ArrayOfPhoneNumbers xmlns:abc="http://example.org/2001/06/numbers"
		//				xmlns:enc="http://www.w3.org/2001/06/soap-encoding" 
        //              enc:arrayType="abc:phoneNumberType[2]" >";
		if (!m_Value.pArray) return FAIL; //error condition
		pSZ << "<";
		if (!m_strPrefix.empty())
		{
			pSZ << m_strPrefix.c_str() << ":" << m_sName.c_str() << " xmlns:" << m_strPrefix.c_str() << "=\"" << m_strUri.c_str() << "\"";
		}
		else
		{
			pSZ << m_sName.c_str();
		}
		//get a prefix from Serializer
		ATprefix = pSZ.getNewNamespacePrefix();

		pSZ << " xmlns:enc"; 
		pSZ << "=\"http://www.w3.org/2001/06/soap-encoding\" ";
		if (m_Value.pArray->m_type == USER_TYPE)
		{
			pSZ << "xmlns:" << ATprefix.c_str() << "=" << m_Value.pArray->m_URI.c_str() << " "; 
		}
		pSZ << "enc:arrayType=";
		if (m_Value.pArray->m_type == USER_TYPE)
		{
			pSZ << ATprefix.c_str() << ":" << m_Value.pArray->m_TypeName.c_str(); 
		}
		else //basic type array
		{
			pSZ << "xsd:";
			pSZ << m_BTSZ.BasicTypeStr(m_Value.pArray->m_type);
		}
		{
			char Buf[10]; //maximum array dimension is 99999999
			for (list<int>::iterator it=m_Value.pArray->m_size.begin(); it!=m_Value.pArray->m_size.end(); it++)
			{
				sprintf(Buf,"[%d]", *it);
				pSZ << Buf;
			}
		}
		pSZ << ">";
		m_Value.pArray->Serialize(pSZ); //Only serializes the inner items
		pSZ << "</";
		if (!m_strPrefix.empty())
		{
			pSZ << m_strPrefix.c_str() << ":" << m_sName.c_str(); 
		}
		else
		{
			pSZ << m_sName.c_str();
		}
		pSZ << ">";
		break;
	case USER_TYPE:
		m_Value.pCplxObj->pSZFunct(m_Value.pCplxObj->pObject, pSZ);
		break;
	default:;
	}
	return SUCCESS;
}

////////////////////////////////////////////////////////////////////////
//This method is used by the deserializer to set the value after setting the type.
//Also this method assumes that the type is already set and it is a basic type.
int Param::SetValue(const AxisChar* sValue)
{
	AxisChar* endptr;
	m_sValue = sValue; //Whatever the type we put the string representation of the value
	switch (m_Type)
	{
	case XSD_INT:
		m_Value.nValue = wcstol(sValue, &endptr, 10);
		break;
	case XSD_FLOAT:
		m_Value.fValue = wcstod(sValue, &endptr);
		break;
	case XSD_STRING:
	case XSD_HEXBINARY:
	case XSD_BASE64BINARY:
		m_sValue = sValue;
		break;
	//Continue this for all basic types
	case XSD_ARRAY:
	case USER_TYPE:
		//this is an error situation - probably something wrong with the soap
		break;
	default:
		return FAIL; //this is an unexpected situation
	}
	return SUCCESS;
}

int Param::SetValue(XSDTYPE nType, uParamValue Value)
{
	m_Type = nType;
	switch (m_Type)
	{
	case XSD_INT:
		m_Value.nValue = Value.nValue;
		break;
	case XSD_FLOAT:
		m_Value.fValue = Value.fValue;
		break;
	case XSD_STRING:
	case XSD_HEXBINARY:
	case XSD_BASE64BINARY:
		m_sValue = Value.pStrValue;
		break;
	//Continue this for all basic types
	case XSD_ARRAY:
		m_Value.pArray = Value.pArray;
		break;
	case USER_TYPE:
		m_Value.pCplxObj = Value.pCplxObj;
		break;
	default:
		return FAIL; //this is an unexpected situation
	}
	return SUCCESS;
}

void Param::setPrefix(const AxisChar* prefix)
{
	m_strPrefix = prefix;
}

void Param::setUri(const AxisChar* uri)
{
	m_strUri = uri;
}

void Param::operator=(const Param &param)
{
	m_sName = param.m_sName;
	m_sValue = param.m_sValue;
	m_Type = param.m_Type;	
	if (m_Type == USER_TYPE) 
	{
		m_Value.pCplxObj = param.m_Value.pCplxObj;
	}
	else if(m_Type == XSD_ARRAY)
	{
		m_Value.pArray = param.m_Value.pArray;
	}	
	else 
	{
		m_Value = param.m_Value;
	}
}

int Param::GetArraySize()
{
	if (m_Type != XSD_ARRAY) return 0;
	return m_Value.pArray->GetArraySize();
}

/*
int Param::SetUserType(IAccessBean* pObject)
{
	if (m_Type != USER_TYPE) return FAIL;
	m_Value.pIBean = pObject;
	return SUCCESS;
}
*/

int Param::SetUserType(void* pObject, AXIS_DESERIALIZE_FUNCT pDZFunct, AXIS_OBJECT_DELETE_FUNCT pDelFunct)
{
	if (m_Type != USER_TYPE) return FAIL;
	m_Value.pCplxObj = new ComplexObjectHandler;
	m_Value.pCplxObj->pObject = pObject;
	m_Value.pCplxObj->pDZFunct = pDZFunct;
	m_Value.pCplxObj->pDelFunct = pDelFunct;
	return SUCCESS;
}

int Param::SetArrayElements(void* pElements)
{
	if (m_Type != XSD_ARRAY) return FAIL;
	if (m_Value.pArray)
	{
		if (m_Value.pArray->m_type != USER_TYPE)
		{
			m_Value.pArray->m_value.sta = pElements;
			return SUCCESS;
		}
		else //unexpected situation
		{
			return FAIL;
		}
	}
	return FAIL;
}

//following function is called to set array of user types.
int Param::SetArrayElements(void* pObject, AXIS_DESERIALIZE_FUNCT pDZFunct, AXIS_OBJECT_DELETE_FUNCT pDelFunct, AXIS_OBJECT_SIZE_FUNCT pSizeFunct)
{
	if (m_Type != XSD_ARRAY) return FAIL;
	if (m_Value.pArray)
	{
		if (m_Value.pArray->m_type == USER_TYPE)
		{
			m_Value.pArray->m_value.cta = new ComplexObjectHandler;
			m_Value.pArray->m_value.cta->pDZFunct = pDZFunct;
			m_Value.pArray->m_value.cta->pDelFunct = pDelFunct;
			m_Value.pArray->m_value.cta->pSizeFunct = pSizeFunct;
			m_Value.pArray->m_value.cta->pObject = pObject;
			return SUCCESS;
		}
		else //unexpected situation
		{
			return FAIL;
		}
	}
	return FAIL;	
}

void Param::SetName(const AxisChar* sName)
{
	m_sName = sName;
}

// ComplexObjectHandler functions
ComplexObjectHandler::ComplexObjectHandler()
{
	Init();
}

void ComplexObjectHandler::Init()
{
	pObject = NULL;
	pSZFunct = NULL;
	pDelFunct = NULL; 
	pDZFunct = NULL;
	pSizeFunct = NULL;
	m_TypeName = L"";
	m_URI = L"";	
}
