/*
 * This file was auto-generated by the Axis C++ Web Service Generator (WSDL2Ws)
 * This file contains Client Stub Class for remote web service 
 */

#if !defined(__ARRAYTESTPORTTYPE_CLIENTSTUB_H__INCLUDED_)
#define __ARRAYTESTPORTTYPE_CLIENTSTUB_H__INCLUDED_

#include <axis/client/Stub.hpp>
#include "array_AxisClientException.h"
#include <axis/ISoapFault.hpp>
#include "intArrayType.h"

class ArrayTestPortType :public Stub
{
public:
	ArrayTestPortType(const char* pchEndpointUri, AXIS_PROTOCOL_TYPE eProtocol=APTHTTP1_1);
	ArrayTestPortType();
public:
	virtual ~ArrayTestPortType();
public: 
	intArrayType* echoIntArray(intArrayType* Value0);
	int getFaultDetail(char** ppcDetail);
};

#endif /* !defined(__ARRAYTESTPORTTYPE_CLIENTSTUB_H__INCLUDED_)*/
