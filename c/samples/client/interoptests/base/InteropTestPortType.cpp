///////////////////////////////////////////////////////////////////////
// This is the Client Stub implementation file genarated by WSDL2Ws tool.
// InteropTestPortType.cpp: implemtation for the InteropTestPortType.
//
//////////////////////////////////////////////////////////////////////

#include "InteropTestPortType.h"

#include <axis/common/AxisWrapperAPI.h>

extern int Axis_DeSerialize_SOAPStruct(SOAPStruct* param, IWrapperSoapDeSerializer *pDZ);
extern void* Axis_Create_SOAPStruct(bool bArray = false, int nSize=0);
extern void Axis_Delete_SOAPStruct(SOAPStruct* param, bool bArray = false, int nSize=0);
extern int Axis_Serialize_SOAPStruct(SOAPStruct* param, IWrapperSoapSerializer& pSZ, bool bArray = false);
extern int Axis_GetSize_SOAPStruct();

InteropTestPortType::InteropTestPortType()
{
	m_pCall = new Call();
	m_pCall->SetProtocol(APTHTTP);
	m_pCall->SetHeader("SOAPAction", "InteropBase");
	m_pCall->SetEndpointURI("http://192.168.101.4/axis/InteropBase");
}

InteropTestPortType::~InteropTestPortType()
{
	delete m_pCall;
}


//Methods corresponding to the web service methods

/////////////////////////////////////////////////////////////////
// This method wrap the service methodechoString
//////////////////////////////////////////////////////////////////
string InteropTestPortType::echoString(string Value0)
{
	int nStatus;
	string Ret;
	if (AXIS_SUCCESS != m_pCall->Initialize()) return Ret;
	m_pCall->SetSOAPVersion(SOAP_VER_1_1);
	m_pCall->SetOperation("echoString", "http://soapinterop.org/");
	m_pCall->AddParameter(Value0, "inputString", XSD_STRING);
	m_pCall->SetReturnType(XSD_STRING);
	nStatus = m_pCall->Invoke();
	if (AXIS_SUCCESS == nStatus)
	{
		m_pCall->GetResult()->GetString(&Ret);
	}
	m_pCall->UnInitialize();
	return Ret;
}


/////////////////////////////////////////////////////////////////
// This method wrap the service methodechoStringArray
//////////////////////////////////////////////////////////////////
ArrayOfstring InteropTestPortType::echoStringArray(ArrayOfstring Value0)
{
	int nStatus;
	ArrayOfstring RetArray = {NULL, 0};
	if (AXIS_SUCCESS != m_pCall->Initialize()) return RetArray;
	m_pCall->SetSOAPVersion(SOAP_VER_1_1);
	m_pCall->SetOperation("echoStringArray", "http://soapinterop.org/");
	m_pCall->AddParameter((Axis_Array*)(&Value0), XSD_STRING, "inputStringArray");
	m_pCall->SetReturnType((Axis_Array*)(&RetArray), XSD_STRING);
	nStatus = m_pCall->Invoke();
	if (AXIS_SUCCESS != nStatus)
	{
		delete RetArray.m_Array;
		RetArray.m_Array = NULL;
		RetArray.m_Size = 0;
	}
	m_pCall->UnInitialize();
	return RetArray;
}


/////////////////////////////////////////////////////////////////
// This method wrap the service methodechoInteger
//////////////////////////////////////////////////////////////////
int InteropTestPortType::echoInteger(int Value0)
{
	int nStatus;
	int Ret;
	if (AXIS_SUCCESS != m_pCall->Initialize()) return Ret;
	m_pCall->SetSOAPVersion(SOAP_VER_1_1);
	m_pCall->SetOperation("echoInteger", "http://soapinterop.org/");
	m_pCall->AddParameter(Value0, "inputInteger", XSD_INT);
	m_pCall->SetReturnType(XSD_INT);
	nStatus = m_pCall->Invoke();
	if (AXIS_SUCCESS == nStatus)
	{
		m_pCall->GetResult()->GetInt(&Ret);
	}
	m_pCall->UnInitialize();
	return Ret;
}


/////////////////////////////////////////////////////////////////
// This method wrap the service methodechoIntegerArray
//////////////////////////////////////////////////////////////////
ArrayOfint InteropTestPortType::echoIntegerArray(ArrayOfint Value0)
{
	int nStatus;
	ArrayOfint RetArray = {NULL, 0};
	if (AXIS_SUCCESS != m_pCall->Initialize()) return RetArray;
	m_pCall->SetSOAPVersion(SOAP_VER_1_1);
	m_pCall->SetOperation("echoIntegerArray", "http://soapinterop.org/");
	m_pCall->AddParameter((Axis_Array*)(&Value0), XSD_INT, "inputIntegerArray");
	m_pCall->SetReturnType((Axis_Array*)(&RetArray), XSD_INT);
	nStatus = m_pCall->Invoke();
	if (AXIS_SUCCESS != nStatus)
	{
		delete RetArray.m_Array;
		RetArray.m_Array = NULL;
		RetArray.m_Size = 0;
	}
	m_pCall->UnInitialize();
	return RetArray;
}


/////////////////////////////////////////////////////////////////
// This method wrap the service methodechoFloat
//////////////////////////////////////////////////////////////////
float InteropTestPortType::echoFloat(float Value0)
{
	int nStatus;
	float Ret;
	if (AXIS_SUCCESS != m_pCall->Initialize()) return Ret;
	m_pCall->SetSOAPVersion(SOAP_VER_1_1);
	m_pCall->SetOperation("echoFloat", "http://soapinterop.org/");
	m_pCall->AddParameter(Value0, "inputFloat", XSD_FLOAT);
	m_pCall->SetReturnType(XSD_FLOAT);
	nStatus = m_pCall->Invoke();
	if (AXIS_SUCCESS == nStatus)
	{
		m_pCall->GetResult()->GetFloat(&Ret);
	}
	m_pCall->UnInitialize();
	return Ret;
}


/////////////////////////////////////////////////////////////////
// This method wrap the service methodechoFloatArray
//////////////////////////////////////////////////////////////////
ArrayOffloat InteropTestPortType::echoFloatArray(ArrayOffloat Value0)
{
	int nStatus;
	ArrayOffloat RetArray = {NULL, 0};
	if (AXIS_SUCCESS != m_pCall->Initialize()) return RetArray;
	m_pCall->SetSOAPVersion(SOAP_VER_1_1);
	m_pCall->SetOperation("echoFloatArray", "http://soapinterop.org/");
	m_pCall->AddParameter((Axis_Array*)(&Value0), XSD_FLOAT, "inputFloatArray");
	m_pCall->SetReturnType((Axis_Array*)(&RetArray), XSD_FLOAT);
	nStatus = m_pCall->Invoke();
	if (AXIS_SUCCESS != nStatus)
	{
		delete RetArray.m_Array;
		RetArray.m_Array = NULL;
		RetArray.m_Size = 0;
	}
	m_pCall->UnInitialize();
	return RetArray;
}


/////////////////////////////////////////////////////////////////
// This method wrap the service methodechoStruct
//////////////////////////////////////////////////////////////////
SOAPStruct* InteropTestPortType::echoStruct(SOAPStruct* Value0)
{
	int nStatus;
	SOAPStruct* pReturn = NULL;
	if (AXIS_SUCCESS != m_pCall->Initialize()) return pReturn;
	m_pCall->SetSOAPVersion(SOAP_VER_1_1);
	m_pCall->SetOperation("echoStruct", "http://soapinterop.org/");
	m_pCall->AddParameter(Value0, (void*) Axis_Serialize_SOAPStruct, (void*) Axis_Delete_SOAPStruct, "inputStruct");
	m_pCall->SetReturnType((void*) Axis_DeSerialize_SOAPStruct, (void*) Axis_Create_SOAPStruct, (void*) Axis_Delete_SOAPStruct, Axis_TypeName_SOAPStruct, Axis_URI_SOAPStruct);
	nStatus = m_pCall->Invoke();
	if (AXIS_SUCCESS == nStatus)
	{
		m_pCall->GetResult((void**)&pReturn);
	}
	m_pCall->UnInitialize();
	return pReturn;
}


/////////////////////////////////////////////////////////////////
// This method wrap the service methodechoStructArray
//////////////////////////////////////////////////////////////////
ArrayOfSOAPStruct InteropTestPortType::echoStructArray(ArrayOfSOAPStruct Value0)
{
	int nStatus;
	ArrayOfSOAPStruct RetArray = {NULL, 0};
	if (AXIS_SUCCESS != m_pCall->Initialize()) return RetArray;
	m_pCall->SetSOAPVersion(SOAP_VER_1_1);
	m_pCall->SetOperation("echoStructArray", "http://soapinterop.org/");
	m_pCall->AddParameter((Axis_Array*)(&Value0), (void*)Axis_Serialize_SOAPStruct, (void*)Axis_Delete_SOAPStruct, (void*) Axis_GetSize_SOAPStruct, Axis_TypeName_SOAPStruct, Axis_URI_SOAPStruct, "inputStructArray");
	m_pCall->SetReturnType((Axis_Array*)(&RetArray), (void*) Axis_DeSerialize_SOAPStruct, (void*) Axis_Create_SOAPStruct, (void*) Axis_Delete_SOAPStruct, (void*) Axis_GetSize_SOAPStruct, Axis_TypeName_SOAPStruct, Axis_URI_SOAPStruct);
	nStatus = m_pCall->Invoke();
	if (AXIS_SUCCESS != nStatus)
	{
		delete RetArray.m_Array;
		RetArray.m_Array = NULL;
		RetArray.m_Size = 0;
	}
	m_pCall->UnInitialize();
	return RetArray;
}


/////////////////////////////////////////////////////////////////
// This method wrap the service methodechoVoid
//////////////////////////////////////////////////////////////////
void InteropTestPortType::echoVoid()
{
	int nStatus;
	if (AXIS_SUCCESS != m_pCall->Initialize()) return ;
	m_pCall->SetSOAPVersion(SOAP_VER_1_1);
	m_pCall->SetOperation("echoVoid", "http://soapinterop.org/");
	nStatus = m_pCall->Invoke();
	if (AXIS_SUCCESS != nStatus)
	{
		//What to do ? . Throw an exception ??? 
	}
	m_pCall->UnInitialize();
}


/////////////////////////////////////////////////////////////////
// This method wrap the service methodechoBase64
//////////////////////////////////////////////////////////////////
Axis_Base64Binary InteropTestPortType::echoBase64(Axis_Base64Binary Value0)
{
	int nStatus;
	Axis_Base64Binary Ret;
	if (AXIS_SUCCESS != m_pCall->Initialize()) return Ret;
	m_pCall->SetSOAPVersion(SOAP_VER_1_1);
	m_pCall->SetOperation("echoBase64", "http://soapinterop.org/");
	m_pCall->AddParameter(Value0, "inputBase64", XSD_BASE64BINARY);
	m_pCall->SetReturnType(XSD_BASE64BINARY);
	nStatus = m_pCall->Invoke();
	if (AXIS_SUCCESS == nStatus)
	{
		m_pCall->GetResult()->GetBase64String(&Ret);
	}
	m_pCall->UnInitialize();
	return Ret;
}


/////////////////////////////////////////////////////////////////
// This method wrap the service methodechoDate
//////////////////////////////////////////////////////////////////
Axis_DateTime InteropTestPortType::echoDate(Axis_DateTime Value0)
{
	int nStatus;
	Axis_DateTime Ret;
	if (AXIS_SUCCESS != m_pCall->Initialize()) return Ret;
	m_pCall->SetSOAPVersion(SOAP_VER_1_1);
	m_pCall->SetOperation("echoDate", "http://soapinterop.org/");
	m_pCall->AddParameter(Value0, "inputDate", XSD_DATETIME);
	m_pCall->SetReturnType(XSD_DATETIME);
	nStatus = m_pCall->Invoke();
	if (AXIS_SUCCESS == nStatus)
	{
		m_pCall->GetResult()->GetDateTime(&Ret);
	}
	m_pCall->UnInitialize();
	return Ret;
}


/////////////////////////////////////////////////////////////////
// This method wrap the service methodechoHexBinary
//////////////////////////////////////////////////////////////////
Axis_HexBinary InteropTestPortType::echoHexBinary(Axis_HexBinary Value0)
{
	int nStatus;
	Axis_HexBinary Ret;
	if (AXIS_SUCCESS != m_pCall->Initialize()) return Ret;
	m_pCall->SetSOAPVersion(SOAP_VER_1_1);
	m_pCall->SetOperation("echoHexBinary", "http://soapinterop.org/");
	m_pCall->AddParameter(Value0, "inputHexBinary", XSD_HEXBINARY);
	m_pCall->SetReturnType(XSD_HEXBINARY);
	nStatus = m_pCall->Invoke();
	if (AXIS_SUCCESS == nStatus)
	{
		m_pCall->GetResult()->GetHexString(&Ret);
	}
	m_pCall->UnInitialize();
	return Ret;
}


/////////////////////////////////////////////////////////////////
// This method wrap the service methodechoDecimal
//////////////////////////////////////////////////////////////////
Axis_Decimal InteropTestPortType::echoDecimal(Axis_Decimal Value0)
{
	int nStatus;
	Axis_Decimal Ret;
	if (AXIS_SUCCESS != m_pCall->Initialize()) return Ret;
	m_pCall->SetSOAPVersion(SOAP_VER_1_1);
	m_pCall->SetOperation("echoDecimal", "http://soapinterop.org/");
	m_pCall->AddParameter(Value0, "inputDecimal", XSD_DECIMAL);
	m_pCall->SetReturnType(XSD_DECIMAL);
	nStatus = m_pCall->Invoke();
	if (AXIS_SUCCESS == nStatus)
	{
		m_pCall->GetResult()->GetDecimal(&Ret);
	}
	m_pCall->UnInitialize();
	return Ret;
}


/////////////////////////////////////////////////////////////////
// This method wrap the service methodechoBoolean
//////////////////////////////////////////////////////////////////
Axis_Boolean InteropTestPortType::echoBoolean(Axis_Boolean Value0)
{
	int nStatus;
	Axis_Boolean Ret;
	if (AXIS_SUCCESS != m_pCall->Initialize()) return Ret;
	m_pCall->SetSOAPVersion(SOAP_VER_1_1);
	m_pCall->SetOperation("echoBoolean", "http://soapinterop.org/");
	m_pCall->AddParameter(Value0, "inputBoolean", XSD_BOOLEAN);
	m_pCall->SetReturnType(XSD_BOOLEAN);
	nStatus = m_pCall->Invoke();
	if (AXIS_SUCCESS == nStatus)
	{
		m_pCall->GetResult()->GetInt(&Ret);
	}
	m_pCall->UnInitialize();
	return Ret;
}

