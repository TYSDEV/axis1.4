/////////////////////////////////////////////////////////////////////////////
// This is the Client Stub Class genarated by the tool WSDL2Ws
// InteropTestPortType.h: interface for the InteropTestPortTypeclass.
//
//////////////////////////////////////////////////////////////////////
#if !defined(__INTEROPTESTPORTTYPE_CLIENTSTUB_H__INCLUDED_)
#define __INTEROPTESTPORTTYPE_CLIENTSTUB_H__INCLUDED_

#include <axis/client/Call.h>
#include "SOAPStruct.h"
#include "ArrayOffloat.h"
#include "ArrayOfSOAPStruct.h"
#include "ArrayOfint.h"
#include "ArrayOfstring.h"

class InteropTestPortType 
{
private:
	Call* m_pCall;
public:
	InteropTestPortType();
public:
	virtual ~InteropTestPortType();
public: 
	string echoString(string Value0);
	ArrayOfstring echoStringArray(ArrayOfstring Value0);
	int echoInteger(int Value0);
	ArrayOfint echoIntegerArray(ArrayOfint Value0);
	float echoFloat(float Value0);
	ArrayOffloat echoFloatArray(ArrayOffloat Value0);
	SOAPStruct* echoStruct(SOAPStruct* Value0);
	ArrayOfSOAPStruct echoStructArray(ArrayOfSOAPStruct Value0);
	void echoVoid();
	Axis_Base64Binary echoBase64(Axis_Base64Binary Value0);
	Axis_DateTime echoDate(Axis_DateTime Value0);
	Axis_HexBinary echoHexBinary(Axis_HexBinary Value0);
	Axis_Decimal echoDecimal(Axis_Decimal Value0);
	Axis_Boolean echoBoolean(Axis_Boolean Value0);
};

#endif // !defined(__INTEROPTESTPORTTYPE_CLIENTSTUB_H__INCLUDED_)
