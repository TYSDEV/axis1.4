/*
 * This is the Client Stub implementation file genarated by WSDL2Ws tool.
 * SimpleTestSoap.cpp: implemtation for the SimpleTestSoap.
 *
 */

#include "SimpleTestSoap.h"

#include <axis/server/AxisWrapperAPI.h>

bool CallBase::bInitialized;
CallFunctions CallBase::ms_VFtable;
extern int Axis_DeSerialize_SOAPStruct(SOAPStruct* param, IWrapperSoapDeSerializer *pDZ);
extern void* Axis_Create_SOAPStruct(SOAPStruct *Obj, bool bArray = false, int nSize=0);
extern void Axis_Delete_SOAPStruct(SOAPStruct* param, bool bArray = false, int nSize=0);
extern int Axis_Serialize_SOAPStruct(SOAPStruct* param, IWrapperSoapSerializer* pSZ, bool bArray = false);
extern int Axis_GetSize_SOAPStruct();

SimpleTestSoap::SimpleTestSoap(const char* pcEndpointUri):Stub(pcEndpointUri)
{
}

SimpleTestSoap::~SimpleTestSoap()
{}


/*Methods corresponding to the web service methods*/

/*
 * This method wrap the service methodechoVoid
 */
void SimpleTestSoap::echoVoid()
{
	if (AXIS_SUCCESS != m_pCall->initialize(CPP_RPC_PROVIDER, NORMAL_CHANNEL)) return ;
	m_pCall->setTransportProperty(SOAPACTION_HEADER , "null");
	m_pCall->setSOAPVersion(SOAP_VER_1_1);
	m_pCall->setOperation("echoVoid", "http://soapinterop.org/");
	applyUserPreferences();
	if (AXIS_SUCCESS == m_pCall->invoke())
	{
		if(AXIS_SUCCESS == m_pCall->checkMessage("echoVoidResponse", "http://soapinterop.org/"))
		{
			/*not successful*/
		}
	}
	m_pCall->unInitialize();
}


/*
 * This method wrap the service methodechoInteger
 */
int SimpleTestSoap::echoInteger(int Value0)
{
	int Ret;
	if (AXIS_SUCCESS != m_pCall->initialize(CPP_RPC_PROVIDER, NORMAL_CHANNEL)) return Ret;
	m_pCall->setTransportProperty(SOAPACTION_HEADER , "null");
	m_pCall->setSOAPVersion(SOAP_VER_1_1);
	m_pCall->setOperation("echoInteger", "http://soapinterop.org/");
	applyUserPreferences();
	m_pCall->addParameter((void*)&Value0, "inputInteger", XSD_INT);
	if (AXIS_SUCCESS == m_pCall->invoke())
	{
		if(AXIS_SUCCESS == m_pCall->checkMessage("echoIntegerResponse", "http://soapinterop.org/"))
		{
			Ret = m_pCall->getElementAsInt("return", 0);
		}
	}
	m_pCall->unInitialize();
	return Ret;
}


/*
 * This method wrap the service methodechoFloat
 */
float SimpleTestSoap::echoFloat(float Value0)
{
	float Ret;
	if (AXIS_SUCCESS != m_pCall->initialize(CPP_RPC_PROVIDER, NORMAL_CHANNEL)) return Ret;
	m_pCall->setTransportProperty(SOAPACTION_HEADER , "null");
	m_pCall->setSOAPVersion(SOAP_VER_1_1);
	m_pCall->setOperation("echoFloat", "http://soapinterop.org/");
	applyUserPreferences();
	m_pCall->addParameter((void*)&Value0, "inputFloat", XSD_FLOAT);
	if (AXIS_SUCCESS == m_pCall->invoke())
	{
		if(AXIS_SUCCESS == m_pCall->checkMessage("echoFloatResponse", "http://soapinterop.org/"))
		{
			Ret = m_pCall->getElementAsFloat("return", 0);
		}
	}
	m_pCall->unInitialize();
	return Ret;
}


/*
 * This method wrap the service methodechoString
 */
xsd__string SimpleTestSoap::echoString(xsd__string Value0)
{
	xsd__string Ret;
	if (AXIS_SUCCESS != m_pCall->initialize(CPP_RPC_PROVIDER, NORMAL_CHANNEL)) return Ret;
	m_pCall->setTransportProperty(SOAPACTION_HEADER , "null");
	m_pCall->setSOAPVersion(SOAP_VER_1_1);
	m_pCall->setOperation("echoString", "http://soapinterop.org/");
	applyUserPreferences();
	m_pCall->addParameter((void*)&Value0, "inputString", XSD_STRING);
	if (AXIS_SUCCESS == m_pCall->invoke())
	{
		if(AXIS_SUCCESS == m_pCall->checkMessage("echoStringResponse", "http://soapinterop.org/"))
		{
			Ret = m_pCall->getElementAsString("return", 0);
		}
	}
	m_pCall->unInitialize();
	return Ret;
}


/*
 * This method wrap the service methodechoBase64
 */
xsd__base64Binary SimpleTestSoap::echoBase64(xsd__base64Binary Value0)
{
	xsd__base64Binary Ret;
	if (AXIS_SUCCESS != m_pCall->initialize(CPP_RPC_PROVIDER, NORMAL_CHANNEL)) return Ret;
	m_pCall->setTransportProperty(SOAPACTION_HEADER , "null");
	m_pCall->setSOAPVersion(SOAP_VER_1_1);
	m_pCall->setOperation("echoBase64", "http://soapinterop.org/");
	applyUserPreferences();
	m_pCall->addParameter((void*)&Value0, "inputBase64", XSD_BASE64BINARY);
	if (AXIS_SUCCESS == m_pCall->invoke())
	{
		if(AXIS_SUCCESS == m_pCall->checkMessage("echoBase64Response", "http://soapinterop.org/"))
		{
			Ret = m_pCall->getElementAsBase64Binary("return", 0);
		}
	}
	m_pCall->unInitialize();
	return Ret;
}


/*
 * This method wrap the service methodechoDate
 */
xsd__dateTime SimpleTestSoap::echoDate(xsd__dateTime Value0)
{
	xsd__dateTime Ret;
	if (AXIS_SUCCESS != m_pCall->initialize(CPP_RPC_PROVIDER, NORMAL_CHANNEL)) return Ret;
	m_pCall->setTransportProperty(SOAPACTION_HEADER , "null");
	m_pCall->setSOAPVersion(SOAP_VER_1_1);
	m_pCall->setOperation("echoDate", "http://soapinterop.org/");
	applyUserPreferences();
	m_pCall->addParameter((void*)&Value0, "inputDate", XSD_DATETIME);
	if (AXIS_SUCCESS == m_pCall->invoke())
	{
		if(AXIS_SUCCESS == m_pCall->checkMessage("echoDateResponse", "http://soapinterop.org/"))
		{
			Ret = m_pCall->getElementAsDateTime("return", 0);
		}
	}
	m_pCall->unInitialize();
	return Ret;
}


/*
 * This method wrap the service methodechoStruct
 */
SOAPStruct* SimpleTestSoap::echoStruct(SOAPStruct* Value0)
{
	SOAPStruct* pReturn = NULL;
	if (AXIS_SUCCESS != m_pCall->initialize(CPP_RPC_PROVIDER, NORMAL_CHANNEL)) return pReturn;
	m_pCall->setTransportProperty(SOAPACTION_HEADER , "null");
	m_pCall->setSOAPVersion(SOAP_VER_1_1);
	m_pCall->setOperation("echoStruct", "http://soapinterop.org/");
	applyUserPreferences();
	m_pCall->addCmplxParameter(Value0, (void*)Axis_Serialize_SOAPStruct, (void*)Axis_Delete_SOAPStruct, "inputStruct", Axis_URI_SOAPStruct);
	if (AXIS_SUCCESS == m_pCall->invoke())
	{
		if(AXIS_SUCCESS == m_pCall->checkMessage("echoStructResponse", "http://soapinterop.org/"))
		{
			pReturn = (SOAPStruct*)m_pCall->getCmplxObject((void*) Axis_DeSerialize_SOAPStruct, (void*) Axis_Create_SOAPStruct, (void*) Axis_Delete_SOAPStruct,"return", 0);
		}
	}
	m_pCall->unInitialize();
	return pReturn;
}


/*
 * This method wrap the service methodechoIntegerArray
 */
xsd__int_Array SimpleTestSoap::echoIntegerArray(xsd__int_Array Value0)
{
	xsd__int_Array RetArray = {NULL, 0};
	if (AXIS_SUCCESS != m_pCall->initialize(CPP_RPC_PROVIDER, NORMAL_CHANNEL)) return RetArray;
	m_pCall->setTransportProperty(SOAPACTION_HEADER , "null");
	m_pCall->setSOAPVersion(SOAP_VER_1_1);
	m_pCall->setOperation("echoIntegerArray", "http://soapinterop.org/");
	applyUserPreferences();
	m_pCall->addBasicArrayParameter((Axis_Array*)(&Value0), XSD_INT, "inputIntegerArray");
	if (AXIS_SUCCESS == m_pCall->invoke())
	{
		if(AXIS_SUCCESS == m_pCall->checkMessage("echoIntegerArrayResponse", "http://soapinterop.org/"))
		{
			RetArray = (xsd__int_Array&)m_pCall->getBasicArray(XSD_INT, "return", 0);
		}
	}
	m_pCall->unInitialize();
	return RetArray;
}


/*
 * This method wrap the service methodechoFloatArray
 */
xsd__float_Array SimpleTestSoap::echoFloatArray(xsd__float_Array Value0)
{
	xsd__float_Array RetArray = {NULL, 0};
	if (AXIS_SUCCESS != m_pCall->initialize(CPP_RPC_PROVIDER, NORMAL_CHANNEL)) return RetArray;
	m_pCall->setTransportProperty(SOAPACTION_HEADER , "null");
	m_pCall->setSOAPVersion(SOAP_VER_1_1);
	m_pCall->setOperation("echoFloatArray", "http://soapinterop.org/");
	applyUserPreferences();
	m_pCall->addBasicArrayParameter((Axis_Array*)(&Value0), XSD_FLOAT, "inputFloatArray");
	if (AXIS_SUCCESS == m_pCall->invoke())
	{
		if(AXIS_SUCCESS == m_pCall->checkMessage("echoFloatArrayResponse", "http://soapinterop.org/"))
		{
			RetArray = (xsd__float_Array&)m_pCall->getBasicArray(XSD_FLOAT, "return", 0);
		}
	}
	m_pCall->unInitialize();
	return RetArray;
}


/*
 * This method wrap the service methodechoStringArray
 */
xsd__string_Array SimpleTestSoap::echoStringArray(xsd__string_Array Value0)
{
	xsd__string_Array RetArray = {NULL, 0};
	if (AXIS_SUCCESS != m_pCall->initialize(CPP_RPC_PROVIDER, NORMAL_CHANNEL)) return RetArray;
	m_pCall->setTransportProperty(SOAPACTION_HEADER , "null");
	m_pCall->setSOAPVersion(SOAP_VER_1_1);
	m_pCall->setOperation("echoStringArray", "http://soapinterop.org/");
	applyUserPreferences();
	m_pCall->addBasicArrayParameter((Axis_Array*)(&Value0), XSD_STRING, "inputStringArray");
	if (AXIS_SUCCESS == m_pCall->invoke())
	{
		if(AXIS_SUCCESS == m_pCall->checkMessage("echoStringArrayResponse", "http://soapinterop.org/"))
		{
			RetArray = (xsd__string_Array&)m_pCall->getBasicArray(XSD_STRING, "return", 0);
		}
	}
	m_pCall->unInitialize();
	return RetArray;
}


/*
 * This method wrap the service methodechoStructArray
 */
SOAPStruct_Array SimpleTestSoap::echoStructArray(SOAPStruct_Array Value0)
{
	SOAPStruct_Array RetArray = {NULL, 0};
	if (AXIS_SUCCESS != m_pCall->initialize(CPP_RPC_PROVIDER, NORMAL_CHANNEL)) return RetArray;
	m_pCall->setTransportProperty(SOAPACTION_HEADER , "null");
	m_pCall->setSOAPVersion(SOAP_VER_1_1);
	m_pCall->setOperation("echoStructArray", "http://soapinterop.org/");
	applyUserPreferences();
	m_pCall->addCmplxArrayParameter((Axis_Array*)(&Value0), (void*)Axis_Serialize_SOAPStruct, (void*)Axis_Delete_SOAPStruct, (void*) Axis_GetSize_SOAPStruct, "inputStructArray", Axis_URI_SOAPStruct);
	if (AXIS_SUCCESS == m_pCall->invoke())
	{
		if(AXIS_SUCCESS == m_pCall->checkMessage("echoStructArrayResponse", "http://soapinterop.org/"))
		{
			RetArray = (SOAPStruct_Array&)m_pCall->getCmplxArray((void*) Axis_DeSerialize_SOAPStruct, (void*) Axis_Create_SOAPStruct, (void*) Axis_Delete_SOAPStruct, (void*) Axis_GetSize_SOAPStruct, "return", Axis_URI_SOAPStruct);
		}
	}
	m_pCall->unInitialize();
	return RetArray;
}


/*
 * This method wrap the service methodechoDecimal
 */
xsd__decimal SimpleTestSoap::echoDecimal(xsd__decimal Value0)
{
	xsd__decimal Ret;
	if (AXIS_SUCCESS != m_pCall->initialize(CPP_RPC_PROVIDER, NORMAL_CHANNEL)) return Ret;
	m_pCall->setTransportProperty(SOAPACTION_HEADER , "null");
	m_pCall->setSOAPVersion(SOAP_VER_1_1);
	m_pCall->setOperation("echoDecimal", "http://soapinterop.org/");
	applyUserPreferences();
	m_pCall->addParameter((void*)&Value0, "inputDecimal", XSD_DECIMAL);
	if (AXIS_SUCCESS == m_pCall->invoke())
	{
		if(AXIS_SUCCESS == m_pCall->checkMessage("echoDecimalResponse", "http://soapinterop.org/"))
		{
			Ret = m_pCall->getElementAsDecimal("return", 0);
		}
	}
	m_pCall->unInitialize();
	return Ret;
}


/*
 * This method wrap the service methodechoBoolean
 */
xsd__boolean SimpleTestSoap::echoBoolean(xsd__boolean Value0)
{
	xsd__boolean Ret;
	if (AXIS_SUCCESS != m_pCall->initialize(CPP_RPC_PROVIDER, NORMAL_CHANNEL)) return Ret;
	m_pCall->setTransportProperty(SOAPACTION_HEADER , "null");
	m_pCall->setSOAPVersion(SOAP_VER_1_1);
	m_pCall->setOperation("echoBoolean", "http://soapinterop.org/");
	applyUserPreferences();
	m_pCall->addParameter((void*)&Value0, "inputBoolean", XSD_BOOLEAN);
	if (AXIS_SUCCESS == m_pCall->invoke())
	{
		if(AXIS_SUCCESS == m_pCall->checkMessage("echoBooleanResponse", "http://soapinterop.org/"))
		{
			Ret = m_pCall->getElementAsBoolean("return", 0);
		}
	}
	m_pCall->unInitialize();
	return Ret;
}


/*
 * This method wrap the service methodechoHexBinary
 */
xsd__hexBinary SimpleTestSoap::echoHexBinary(xsd__hexBinary Value0)
{
	xsd__hexBinary Ret;
	if (AXIS_SUCCESS != m_pCall->initialize(CPP_RPC_PROVIDER, NORMAL_CHANNEL)) return Ret;
	m_pCall->setTransportProperty(SOAPACTION_HEADER , "null");
	m_pCall->setSOAPVersion(SOAP_VER_1_1);
	m_pCall->setOperation("echoHexBinary", "http://soapinterop.org/");
	applyUserPreferences();
	m_pCall->addParameter((void*)&Value0, "inputHexBinary", XSD_HEXBINARY);
	if (AXIS_SUCCESS == m_pCall->invoke())
	{
		if(AXIS_SUCCESS == m_pCall->checkMessage("echoHexBinaryResponse", "http://soapinterop.org/"))
		{
			Ret = m_pCall->getElementAsHexBinary("return", 0);
		}
	}
	m_pCall->unInitialize();
	return Ret;
}

	int SimpleTestSoap::getStatus(){if ( m_pCall==NULL ) return AXIS_SUCCESS; else return m_pCall->getStatus();}
