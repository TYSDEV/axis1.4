///////////////////////////////////////////////////////////////////////
//This is the Service implementation CPP file genarated by theWSDL2Ws.
//		InteropTestPortTypeBWrapper.cpp: implemtation for the InteropTestPortTypeBWrapper.
//
//////////////////////////////////////////////////////////////////////

#include "InteropTestPortTypeBWrapper.h"

extern int Axis_DeSerialize_SOAPStruct(SOAPStruct* param, IWrapperSoapDeSerializer *pDZ);
extern void* Axis_Create_SOAPStruct(bool bArray = false, int nSize=0);
extern void Axis_Delete_SOAPStruct(SOAPStruct* param, bool bArray = false, int nSize=0);
extern int Axis_Serialize_SOAPStruct(SOAPStruct* param, IWrapperSoapSerializer& pSZ, bool bArray = false);
extern int Axis_GetSize_SOAPStruct();

extern int Axis_DeSerialize_SOAPArrayStruct(SOAPArrayStruct* param, IWrapperSoapDeSerializer *pDZ);
extern void* Axis_Create_SOAPArrayStruct(bool bArray = false, int nSize=0);
extern void Axis_Delete_SOAPArrayStruct(SOAPArrayStruct* param, bool bArray = false, int nSize=0);
extern int Axis_Serialize_SOAPArrayStruct(SOAPArrayStruct* param, IWrapperSoapSerializer& pSZ, bool bArray = false);
extern int Axis_GetSize_SOAPArrayStruct();

extern int Axis_DeSerialize_SOAPStructStruct(SOAPStructStruct* param, IWrapperSoapDeSerializer *pDZ);
extern void* Axis_Create_SOAPStructStruct(bool bArray = false, int nSize=0);
extern void Axis_Delete_SOAPStructStruct(SOAPStructStruct* param, bool bArray = false, int nSize=0);
extern int Axis_Serialize_SOAPStructStruct(SOAPStructStruct* param, IWrapperSoapSerializer& pSZ, bool bArray = false);
extern int Axis_GetSize_SOAPStructStruct();

InteropTestPortTypeBWrapper::InteropTestPortTypeBWrapper()
{
	pWs = new InteropTestPortTypeB();
}

InteropTestPortTypeBWrapper::~InteropTestPortTypeBWrapper()
{
	delete pWs;
}

//implementation of WrapperClassHandler interface
void InteropTestPortTypeBWrapper::OnFault(IMessageData *pMsg)
{
}

int InteropTestPortTypeBWrapper::Init()
{
	return AXIS_SUCCESS;
}

int InteropTestPortTypeBWrapper::Fini()
{
	return AXIS_SUCCESS;
}


/////////////////////////////////////////////////////////////////
// This method invokes the right service method 
//////////////////////////////////////////////////////////////////
int InteropTestPortTypeBWrapper::Invoke(IMessageData *mc)
{
	IWrapperSoapDeSerializer *pIWSDZ = NULL;
	mc->getSoapDeSerializer(&pIWSDZ);
	if (!pIWSDZ) return AXIS_FAIL;
	const AxisChar *method = pIWSDZ->GetMethodName();
	if (0 == strcmp(method, "echoStructAsSimpleTypes"))
		return echoStructAsSimpleTypes(mc);
	else if (0 == strcmp(method, "echoSimpleTypesAsStruct"))
		return echoSimpleTypesAsStruct(mc);
	else if (0 == strcmp(method, "echo2DStringArray"))
		return echo2DStringArray(mc);
	else if (0 == strcmp(method, "echoNestedStruct"))
		return echoNestedStruct(mc);
	else if (0 == strcmp(method, "echoNestedArray"))
		return echoNestedArray(mc);
	else return AXIS_FAIL;
}


//Methods corresponding to the web service methods

/////////////////////////////////////////////////////////////////
// This method wrap the service method 
//////////////////////////////////////////////////////////////////
int InteropTestPortTypeBWrapper::echoStructAsSimpleTypes(IMessageData* mc)
{
	IWrapperSoapSerializer *pIWSSZ = NULL;
	mc->getSoapSerializer(&pIWSSZ);
	if (!pIWSSZ) return AXIS_FAIL;
	IWrapperSoapDeSerializer *pIWSDZ = NULL;
	mc->getSoapDeSerializer(&pIWSDZ);
	if (!pIWSDZ) return AXIS_FAIL;
	pIWSSZ->createSoapMethod("echoStructAsSimpleTypesResponse", pIWSSZ->getNewNamespacePrefix(), "http://soapinterop.org/");
	SOAPStruct *v0 = (SOAPStruct*)pIWSDZ->GetObject((void*)Axis_DeSerialize_SOAPStruct
		, (void*)Axis_Create_SOAPStruct, (void*)Axis_Delete_SOAPStruct
		, Axis_TypeName_SOAPStruct, Axis_URI_SOAPStruct);
	string outValue0;
	int outValue1;
	float outValue2;
	if (AXIS_SUCCESS != pIWSDZ->GetStatus())
	{
		delete v0;
		return AXIS_DESERIALIZATION_ERROR;
	}
	pWs->echoStructAsSimpleTypes(v0, &outValue0, &outValue1, &outValue2);
	if (AXIS_SUCCESS != pIWSSZ->AddOutputParam("String", outValue0, XSD_STRING)) return AXIS_FAIL;
	if (AXIS_SUCCESS != pIWSSZ->AddOutputParam("Int", outValue1, XSD_INT)) return AXIS_FAIL;
	if (AXIS_SUCCESS != pIWSSZ->AddOutputParam("Float", outValue2, XSD_FLOAT)) return AXIS_FAIL;
	return AXIS_SUCCESS;
}


/////////////////////////////////////////////////////////////////
// This method wrap the service method 
//////////////////////////////////////////////////////////////////
int InteropTestPortTypeBWrapper::echoSimpleTypesAsStruct(IMessageData* mc)
{
	IWrapperSoapSerializer *pIWSSZ = NULL;
	mc->getSoapSerializer(&pIWSSZ);
	if (!pIWSSZ) return AXIS_FAIL;
	IWrapperSoapDeSerializer *pIWSDZ = NULL;
	mc->getSoapDeSerializer(&pIWSDZ);
	if (!pIWSDZ) return AXIS_FAIL;
	pIWSSZ->createSoapMethod("echoSimpleTypesAsStructResponse", pIWSSZ->getNewNamespacePrefix(), "http://soapinterop.org/");
	float v0 = pIWSDZ->GetFloat();
	int v1 = pIWSDZ->GetInt();
	string v2 = pIWSDZ->GetString();
	if (AXIS_SUCCESS != pIWSDZ->GetStatus())
	{
		return AXIS_DESERIALIZATION_ERROR;
	}
	SOAPStruct *ret = pWs->echoSimpleTypesAsStruct(v0,v1,v2);
	return pIWSSZ->AddOutputParam("echoSimpleTypesAsStructReturn", ret, (void*)Axis_Serialize_SOAPStruct, (void*)Axis_Delete_SOAPStruct);
}


/////////////////////////////////////////////////////////////////
// This method wrap the service method 
//////////////////////////////////////////////////////////////////
int InteropTestPortTypeBWrapper::echo2DStringArray(IMessageData* mc)
{
	IWrapperSoapSerializer *pIWSSZ = NULL;
	mc->getSoapSerializer(&pIWSSZ);
	if (!pIWSSZ) return AXIS_FAIL;
	IWrapperSoapDeSerializer *pIWSDZ = NULL;
	mc->getSoapDeSerializer(&pIWSDZ);
	if (!pIWSDZ) return AXIS_FAIL;
	pIWSSZ->createSoapMethod("echo2DStringArrayResponse", pIWSSZ->getNewNamespacePrefix(), "http://soapinterop.org/");
	ArrayOfString2D v0;
	v0.m_Size = pIWSDZ->GetArraySize();
	if (v0.m_Size < 1) return AXIS_FAIL;
	v0.m_Array = new string[v0.m_Size];
	if (AXIS_SUCCESS != pIWSDZ->GetArray((Axis_Array*)(&v0), XSD_STRING)) return AXIS_FAIL;
	if (AXIS_SUCCESS != pIWSDZ->GetStatus())
	{
		delete [] v0.m_Array;
		return AXIS_DESERIALIZATION_ERROR;
	}
	ArrayOfString2D ret = pWs->echo2DStringArray(v0);
	return pIWSSZ->AddOutputParam("echo2DStringArrayReturn", (Axis_Array*)(&ret),XSD_STRING);
}


/////////////////////////////////////////////////////////////////
// This method wrap the service method 
//////////////////////////////////////////////////////////////////
int InteropTestPortTypeBWrapper::echoNestedStruct(IMessageData* mc)
{
	IWrapperSoapSerializer *pIWSSZ = NULL;
	mc->getSoapSerializer(&pIWSSZ);
	if (!pIWSSZ) return AXIS_FAIL;
	IWrapperSoapDeSerializer *pIWSDZ = NULL;
	mc->getSoapDeSerializer(&pIWSDZ);
	if (!pIWSDZ) return AXIS_FAIL;
	pIWSSZ->createSoapMethod("echoNestedStructResponse", pIWSSZ->getNewNamespacePrefix(), "http://soapinterop.org/");
	SOAPStructStruct *v0 = (SOAPStructStruct*)pIWSDZ->GetObject((void*)Axis_DeSerialize_SOAPStructStruct
		, (void*)Axis_Create_SOAPStructStruct, (void*)Axis_Delete_SOAPStructStruct
		, Axis_TypeName_SOAPStructStruct, Axis_URI_SOAPStructStruct);
	if (AXIS_SUCCESS != pIWSDZ->GetStatus())
	{
		delete v0;
		return AXIS_DESERIALIZATION_ERROR;
	}
	SOAPStructStruct *ret = pWs->echoNestedStruct(v0);
	return pIWSSZ->AddOutputParam("echoNestedStructReturn", ret, (void*)Axis_Serialize_SOAPStructStruct, (void*)Axis_Delete_SOAPStructStruct);
}


/////////////////////////////////////////////////////////////////
// This method wrap the service method 
//////////////////////////////////////////////////////////////////
int InteropTestPortTypeBWrapper::echoNestedArray(IMessageData* mc)
{
	IWrapperSoapSerializer *pIWSSZ = NULL;
	mc->getSoapSerializer(&pIWSSZ);
	if (!pIWSSZ) return AXIS_FAIL;
	IWrapperSoapDeSerializer *pIWSDZ = NULL;
	mc->getSoapDeSerializer(&pIWSDZ);
	if (!pIWSDZ) return AXIS_FAIL;
	pIWSSZ->createSoapMethod("echoNestedArrayResponse", pIWSSZ->getNewNamespacePrefix(), "http://soapinterop.org/");
	SOAPArrayStruct *v0 = (SOAPArrayStruct*)pIWSDZ->GetObject((void*)Axis_DeSerialize_SOAPArrayStruct
		, (void*)Axis_Create_SOAPArrayStruct, (void*)Axis_Delete_SOAPArrayStruct
		, Axis_TypeName_SOAPArrayStruct, Axis_URI_SOAPArrayStruct);
	if (AXIS_SUCCESS != pIWSDZ->GetStatus())
	{
		delete v0;
		return AXIS_DESERIALIZATION_ERROR;
	}
	SOAPArrayStruct *ret = pWs->echoNestedArray(v0);
	return pIWSSZ->AddOutputParam("echoNestedArrayReturn", ret, (void*)Axis_Serialize_SOAPArrayStruct, (void*)Axis_Delete_SOAPArrayStruct);
}

