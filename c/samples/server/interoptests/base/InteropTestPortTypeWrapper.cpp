///////////////////////////////////////////////////////////////////////
//This is the Service implementation CPP file genarated by theWSDL2Ws.
//		InteropTestPortTypeWrapper.cpp: implemtation for the InteropTestPortTypeWrapper.
//
//////////////////////////////////////////////////////////////////////

#include "InteropTestPortTypeWrapper.h"

extern int Axis_DeSerialize_SOAPStruct(SOAPStruct* param, IWrapperSoapDeSerializer *pDZ);
extern void* Axis_Create_SOAPStruct(bool bArray = false, int nSize=0);
extern void Axis_Delete_SOAPStruct(SOAPStruct* param, bool bArray = false, int nSize=0);
extern int Axis_Serialize_SOAPStruct(SOAPStruct* param, IWrapperSoapSerializer& pSZ, bool bArray = false);
extern int Axis_GetSize_SOAPStruct();

InteropTestPortTypeWrapper::InteropTestPortTypeWrapper()
{
	pWs = new InteropTestPortType();
}

InteropTestPortTypeWrapper::~InteropTestPortTypeWrapper()
{
	delete pWs;
}

//implementation of WrapperClassHandler interface
void InteropTestPortTypeWrapper::OnFault(IMessageData *pMsg)
{
}

int InteropTestPortTypeWrapper::Init()
{
	return AXIS_SUCCESS;
}

int InteropTestPortTypeWrapper::Fini()
{
	return AXIS_SUCCESS;
}


/////////////////////////////////////////////////////////////////
// This method invokes the right service method 
//////////////////////////////////////////////////////////////////
int InteropTestPortTypeWrapper::Invoke(IMessageData *mc)
{
	IWrapperSoapDeSerializer *pIWSDZ = NULL;
	mc->getSoapDeSerializer(&pIWSDZ);
	if (!pIWSDZ) return AXIS_FAIL;
	const AxisChar *method = pIWSDZ->GetMethodName();
	if (0 == strcmp(method, "echoString"))
		return echoString(mc);
	else if (0 == strcmp(method, "echoStringArray"))
		return echoStringArray(mc);
	else if (0 == strcmp(method, "echoInteger"))
		return echoInteger(mc);
	else if (0 == strcmp(method, "echoIntegerArray"))
		return echoIntegerArray(mc);
	else if (0 == strcmp(method, "echoFloat"))
		return echoFloat(mc);
	else if (0 == strcmp(method, "echoFloatArray"))
		return echoFloatArray(mc);
	else if (0 == strcmp(method, "echoStruct"))
		return echoStruct(mc);
	else if (0 == strcmp(method, "echoStructArray"))
		return echoStructArray(mc);
	else if (0 == strcmp(method, "echoVoid"))
		return echoVoid(mc);
	else if (0 == strcmp(method, "echoBase64"))
		return echoBase64(mc);
	else if (0 == strcmp(method, "echoDate"))
		return echoDate(mc);
	else if (0 == strcmp(method, "echoHexBinary"))
		return echoHexBinary(mc);
	else if (0 == strcmp(method, "echoDecimal"))
		return echoDecimal(mc);
	else if (0 == strcmp(method, "echoBoolean"))
		return echoBoolean(mc);
	else return AXIS_FAIL;
}


//Methods corresponding to the web service methods

/////////////////////////////////////////////////////////////////
// This method wrap the service method 
//////////////////////////////////////////////////////////////////
int InteropTestPortTypeWrapper::echoString(IMessageData* mc)
{
	IWrapperSoapSerializer *pIWSSZ = NULL;
	mc->getSoapSerializer(&pIWSSZ);
	if (!pIWSSZ) return AXIS_FAIL;
	IWrapperSoapDeSerializer *pIWSDZ = NULL;
	mc->getSoapDeSerializer(&pIWSDZ);
	if (!pIWSDZ) return AXIS_FAIL;
	pIWSSZ->createSoapMethod("echoStringResponse", pIWSSZ->getNewNamespacePrefix(), "http://soapinterop.org/");
	string v0 = pIWSDZ->GetString();
	if (AXIS_SUCCESS != pIWSDZ->GetStatus())
	{
		return AXIS_DESERIALIZATION_ERROR;
	}
	string ret = pWs->echoString(v0);
	return pIWSSZ->AddOutputParam("echoStringReturn", ret, XSD_STRING);
}


/////////////////////////////////////////////////////////////////
// This method wrap the service method 
//////////////////////////////////////////////////////////////////
int InteropTestPortTypeWrapper::echoStringArray(IMessageData* mc)
{
	IWrapperSoapSerializer *pIWSSZ = NULL;
	mc->getSoapSerializer(&pIWSSZ);
	if (!pIWSSZ) return AXIS_FAIL;
	IWrapperSoapDeSerializer *pIWSDZ = NULL;
	mc->getSoapDeSerializer(&pIWSDZ);
	if (!pIWSDZ) return AXIS_FAIL;
	pIWSSZ->createSoapMethod("echoStringArrayResponse", pIWSSZ->getNewNamespacePrefix(), "http://soapinterop.org/");
	ArrayOfstring v0;
	v0.m_Size = pIWSDZ->GetArraySize();
	if (v0.m_Size < 1) return AXIS_FAIL;
	v0.m_Array = new string[v0.m_Size];
	if (AXIS_SUCCESS != pIWSDZ->GetArray((Axis_Array*)(&v0), XSD_STRING)) return AXIS_FAIL;
	if (AXIS_SUCCESS != pIWSDZ->GetStatus())
	{
		delete [] v0.m_Array;
		return AXIS_DESERIALIZATION_ERROR;
	}
	ArrayOfstring ret = pWs->echoStringArray(v0);
	return pIWSSZ->AddOutputParam("echoStringArrayReturn", (Axis_Array*)(&ret),XSD_STRING);
}


/////////////////////////////////////////////////////////////////
// This method wrap the service method 
//////////////////////////////////////////////////////////////////
int InteropTestPortTypeWrapper::echoInteger(IMessageData* mc)
{
	IWrapperSoapSerializer *pIWSSZ = NULL;
	mc->getSoapSerializer(&pIWSSZ);
	if (!pIWSSZ) return AXIS_FAIL;
	IWrapperSoapDeSerializer *pIWSDZ = NULL;
	mc->getSoapDeSerializer(&pIWSDZ);
	if (!pIWSDZ) return AXIS_FAIL;
	pIWSSZ->createSoapMethod("echoIntegerResponse", pIWSSZ->getNewNamespacePrefix(), "http://soapinterop.org/");
	int v0 = pIWSDZ->GetInt();
	if (AXIS_SUCCESS != pIWSDZ->GetStatus())
	{
		return AXIS_DESERIALIZATION_ERROR;
	}

	int ret = pWs->echoInteger(v0);
	return pIWSSZ->AddOutputParam("echoIntegerReturn", ret, XSD_INT);
}


/////////////////////////////////////////////////////////////////
// This method wrap the service method 
//////////////////////////////////////////////////////////////////
int InteropTestPortTypeWrapper::echoIntegerArray(IMessageData* mc)
{
	IWrapperSoapSerializer *pIWSSZ = NULL;
	mc->getSoapSerializer(&pIWSSZ);
	if (!pIWSSZ) return AXIS_FAIL;
	IWrapperSoapDeSerializer *pIWSDZ = NULL;
	mc->getSoapDeSerializer(&pIWSDZ);
	if (!pIWSDZ) return AXIS_FAIL;
	pIWSSZ->createSoapMethod("echoIntegerArrayResponse", pIWSSZ->getNewNamespacePrefix(), "http://soapinterop.org/");
	ArrayOfint v0;
	v0.m_Size = pIWSDZ->GetArraySize();
	if (v0.m_Size < 1) return AXIS_FAIL;
	v0.m_Array = new int[v0.m_Size];
	if (AXIS_SUCCESS != pIWSDZ->GetArray((Axis_Array*)(&v0), XSD_INT)) return AXIS_FAIL;
	if (AXIS_SUCCESS != pIWSDZ->GetStatus())
	{
		delete [] v0.m_Array;
		return AXIS_DESERIALIZATION_ERROR;
	}
	ArrayOfint ret = pWs->echoIntegerArray(v0);
	return pIWSSZ->AddOutputParam("echoIntegerArrayReturn", (Axis_Array*)(&ret),XSD_INT);
}


/////////////////////////////////////////////////////////////////
// This method wrap the service method 
//////////////////////////////////////////////////////////////////
int InteropTestPortTypeWrapper::echoFloat(IMessageData* mc)
{
	IWrapperSoapSerializer *pIWSSZ = NULL;
	mc->getSoapSerializer(&pIWSSZ);
	if (!pIWSSZ) return AXIS_FAIL;
	IWrapperSoapDeSerializer *pIWSDZ = NULL;
	mc->getSoapDeSerializer(&pIWSDZ);
	if (!pIWSDZ) return AXIS_FAIL;
	pIWSSZ->createSoapMethod("echoFloatResponse", pIWSSZ->getNewNamespacePrefix(), "http://soapinterop.org/");
	float v0 = pIWSDZ->GetFloat();
	if (AXIS_SUCCESS != pIWSDZ->GetStatus())
	{
		return AXIS_DESERIALIZATION_ERROR;
	}
	float ret = pWs->echoFloat(v0);
	return pIWSSZ->AddOutputParam("echoFloatReturn", ret, XSD_FLOAT);
}


/////////////////////////////////////////////////////////////////
// This method wrap the service method 
//////////////////////////////////////////////////////////////////
int InteropTestPortTypeWrapper::echoFloatArray(IMessageData* mc)
{
	IWrapperSoapSerializer *pIWSSZ = NULL;
	mc->getSoapSerializer(&pIWSSZ);
	if (!pIWSSZ) return AXIS_FAIL;
	IWrapperSoapDeSerializer *pIWSDZ = NULL;
	mc->getSoapDeSerializer(&pIWSDZ);
	if (!pIWSDZ) return AXIS_FAIL;
	pIWSSZ->createSoapMethod("echoFloatArrayResponse", pIWSSZ->getNewNamespacePrefix(), "http://soapinterop.org/");
	ArrayOffloat v0;
	v0.m_Size = pIWSDZ->GetArraySize();
	if (v0.m_Size < 1) return AXIS_FAIL;
	v0.m_Array = new float[v0.m_Size];
	if (AXIS_SUCCESS != pIWSDZ->GetArray((Axis_Array*)(&v0), XSD_FLOAT)) return AXIS_FAIL;
	if (AXIS_SUCCESS != pIWSDZ->GetStatus())
	{
		delete v0.m_Array;
		return AXIS_DESERIALIZATION_ERROR;
	}
	ArrayOffloat ret = pWs->echoFloatArray(v0);
	return pIWSSZ->AddOutputParam("echoFloatArrayReturn", (Axis_Array*)(&ret),XSD_FLOAT);
}


/////////////////////////////////////////////////////////////////
// This method wrap the service method 
//////////////////////////////////////////////////////////////////
int InteropTestPortTypeWrapper::echoStruct(IMessageData* mc)
{
	IWrapperSoapSerializer *pIWSSZ = NULL;
	mc->getSoapSerializer(&pIWSSZ);
	if (!pIWSSZ) return AXIS_FAIL;
	IWrapperSoapDeSerializer *pIWSDZ = NULL;
	mc->getSoapDeSerializer(&pIWSDZ);
	if (!pIWSDZ) return AXIS_FAIL;
	pIWSSZ->createSoapMethod("echoStructResponse", pIWSSZ->getNewNamespacePrefix(), "http://soapinterop.org/");
	SOAPStruct *v0 = (SOAPStruct*)pIWSDZ->GetObject((void*)Axis_DeSerialize_SOAPStruct
		, (void*)Axis_Create_SOAPStruct, (void*)Axis_Delete_SOAPStruct
		, Axis_TypeName_SOAPStruct, Axis_URI_SOAPStruct);
	if (AXIS_SUCCESS != pIWSDZ->GetStatus())
	{
		delete v0;
		return AXIS_DESERIALIZATION_ERROR;
	}
	SOAPStruct *ret = pWs->echoStruct(v0);
	return pIWSSZ->AddOutputParam("echoStructReturn", ret, (void*)Axis_Serialize_SOAPStruct, (void*)Axis_Delete_SOAPStruct);
}


/////////////////////////////////////////////////////////////////
// This method wrap the service method 
//////////////////////////////////////////////////////////////////
int InteropTestPortTypeWrapper::echoStructArray(IMessageData* mc)
{
	IWrapperSoapSerializer *pIWSSZ = NULL;
	mc->getSoapSerializer(&pIWSSZ);
	if (!pIWSSZ) return AXIS_FAIL;
	IWrapperSoapDeSerializer *pIWSDZ = NULL;
	mc->getSoapDeSerializer(&pIWSDZ);
	if (!pIWSDZ) return AXIS_FAIL;
	pIWSSZ->createSoapMethod("echoStructArrayResponse", pIWSSZ->getNewNamespacePrefix(), "http://soapinterop.org/");
	ArrayOfSOAPStruct v0 = (ArrayOfSOAPStruct&)pIWSDZ->GetArray((void*)Axis_DeSerialize_SOAPStruct
		, (void*)Axis_Create_SOAPStruct, (void*)Axis_Delete_SOAPStruct
		, (void*)Axis_GetSize_SOAPStruct, Axis_TypeName_SOAPStruct, Axis_URI_SOAPStruct);
	if (AXIS_SUCCESS != pIWSDZ->GetStatus())
	{
		delete [] v0.m_Array;
		return AXIS_DESERIALIZATION_ERROR;
	}
	ArrayOfSOAPStruct ret = pWs->echoStructArray(v0);
	return pIWSSZ->AddOutputParam("echoStructArrayReturn", (Axis_Array*)(&ret),(void*) Axis_Serialize_SOAPStruct, (void*) Axis_Delete_SOAPStruct, (void*) Axis_GetSize_SOAPStruct, Axis_TypeName_SOAPStruct, Axis_URI_SOAPStruct);
}


/////////////////////////////////////////////////////////////////
// This method wrap the service method 
//////////////////////////////////////////////////////////////////
int InteropTestPortTypeWrapper::echoVoid(IMessageData* mc)
{
	IWrapperSoapSerializer *pIWSSZ = NULL;
	mc->getSoapSerializer(&pIWSSZ);
	if (!pIWSSZ) return AXIS_FAIL;
	IWrapperSoapDeSerializer *pIWSDZ = NULL;
	mc->getSoapDeSerializer(&pIWSDZ);
	if (!pIWSDZ) return AXIS_FAIL;
	pIWSSZ->createSoapMethod("echoVoidResponse", pIWSSZ->getNewNamespacePrefix(), "http://soapinterop.org/");
	pWs->echoVoid();
	return AXIS_SUCCESS;
}


/////////////////////////////////////////////////////////////////
// This method wrap the service method 
//////////////////////////////////////////////////////////////////
int InteropTestPortTypeWrapper::echoBase64(IMessageData* mc)
{
	IWrapperSoapSerializer *pIWSSZ = NULL;
	mc->getSoapSerializer(&pIWSSZ);
	if (!pIWSSZ) return AXIS_FAIL;
	IWrapperSoapDeSerializer *pIWSDZ = NULL;
	mc->getSoapDeSerializer(&pIWSDZ);
	if (!pIWSDZ) return AXIS_FAIL;
	pIWSSZ->createSoapMethod("echoBase64Response", pIWSSZ->getNewNamespacePrefix(), "http://soapinterop.org/");
	Axis_Base64Binary v0 = pIWSDZ->GetBase64String();
	if (AXIS_SUCCESS != pIWSDZ->GetStatus())
	{
		return AXIS_DESERIALIZATION_ERROR;
	}
	Axis_Base64Binary ret = pWs->echoBase64(v0);
	return pIWSSZ->AddOutputParam("echoBase64Return", ret, XSD_BASE64BINARY);
}


/////////////////////////////////////////////////////////////////
// This method wrap the service method 
//////////////////////////////////////////////////////////////////
int InteropTestPortTypeWrapper::echoDate(IMessageData* mc)
{
	IWrapperSoapSerializer *pIWSSZ = NULL;
	mc->getSoapSerializer(&pIWSSZ);
	if (!pIWSSZ) return AXIS_FAIL;
	IWrapperSoapDeSerializer *pIWSDZ = NULL;
	mc->getSoapDeSerializer(&pIWSDZ);
	if (!pIWSDZ) return AXIS_FAIL;
	pIWSSZ->createSoapMethod("echoDateResponse", pIWSSZ->getNewNamespacePrefix(), "http://soapinterop.org/");
	Axis_DateTime v0 = pIWSDZ->GetDateTime();
	if (AXIS_SUCCESS != pIWSDZ->GetStatus())
	{
		return AXIS_DESERIALIZATION_ERROR;
	}
	Axis_DateTime ret = pWs->echoDate(v0);
	return pIWSSZ->AddOutputParam("echoDateReturn", ret, XSD_DATETIME);
}


/////////////////////////////////////////////////////////////////
// This method wrap the service method 
//////////////////////////////////////////////////////////////////
int InteropTestPortTypeWrapper::echoHexBinary(IMessageData* mc)
{
	IWrapperSoapSerializer *pIWSSZ = NULL;
	mc->getSoapSerializer(&pIWSSZ);
	if (!pIWSSZ) return AXIS_FAIL;
	IWrapperSoapDeSerializer *pIWSDZ = NULL;
	mc->getSoapDeSerializer(&pIWSDZ);
	if (!pIWSDZ) return AXIS_FAIL;
	pIWSSZ->createSoapMethod("echoHexBinaryResponse", pIWSSZ->getNewNamespacePrefix(), "http://soapinterop.org/");
	Axis_HexBinary v0 = pIWSDZ->GetHexString();
	if (AXIS_SUCCESS != pIWSDZ->GetStatus())
	{
		return AXIS_DESERIALIZATION_ERROR;
	}
	Axis_HexBinary ret = pWs->echoHexBinary(v0);
	return pIWSSZ->AddOutputParam("echoHexBinaryReturn", ret, XSD_HEXBINARY);
}


/////////////////////////////////////////////////////////////////
// This method wrap the service method 
//////////////////////////////////////////////////////////////////
int InteropTestPortTypeWrapper::echoDecimal(IMessageData* mc)
{
	IWrapperSoapSerializer *pIWSSZ = NULL;
	mc->getSoapSerializer(&pIWSSZ);
	if (!pIWSSZ) return AXIS_FAIL;
	IWrapperSoapDeSerializer *pIWSDZ = NULL;
	mc->getSoapDeSerializer(&pIWSDZ);
	if (!pIWSDZ) return AXIS_FAIL;
	pIWSSZ->createSoapMethod("echoDecimalResponse", pIWSSZ->getNewNamespacePrefix(), "http://soapinterop.org/");
	Axis_Decimal v0 = pIWSDZ->GetDecimal();
	if (AXIS_SUCCESS != pIWSDZ->GetStatus())
	{
		return AXIS_DESERIALIZATION_ERROR;
	}
	Axis_Decimal ret = pWs->echoDecimal(v0);
	return pIWSSZ->AddOutputParam("echoDecimalReturn", ret, XSD_DECIMAL);
}


/////////////////////////////////////////////////////////////////
// This method wrap the service method 
//////////////////////////////////////////////////////////////////
int InteropTestPortTypeWrapper::echoBoolean(IMessageData* mc)
{
	IWrapperSoapSerializer *pIWSSZ = NULL;
	mc->getSoapSerializer(&pIWSSZ);
	if (!pIWSSZ) return AXIS_FAIL;
	IWrapperSoapDeSerializer *pIWSDZ = NULL;
	mc->getSoapDeSerializer(&pIWSDZ);
	if (!pIWSDZ) return AXIS_FAIL;
	pIWSSZ->createSoapMethod("echoBooleanResponse", pIWSSZ->getNewNamespacePrefix(), "http://soapinterop.org/");
	Axis_Boolean v0 = pIWSDZ->GetInt();
	if (AXIS_SUCCESS != pIWSDZ->GetStatus())
	{
		return AXIS_DESERIALIZATION_ERROR;
	}
	Axis_Boolean ret = pWs->echoBoolean(v0);
	return pIWSSZ->AddOutputParam("echoBooleanReturn", ret, XSD_BOOLEAN);
}

