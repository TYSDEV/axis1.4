/*
 * This file was auto-generated by the Axis C++ Web Service Generator (WSDL2Ws)
 * This file contains Client Stub implementation for remote web service.
 */

#include "InteropTestPortType.h"

extern int Axis_DeSerialize_SOAPStruct(SOAPStruct* param, IWrapperSoapDeSerializer* pDZ);
extern void* Axis_Create_SOAPStruct(SOAPStruct *Obj, bool bArray, int nSize);
extern void Axis_Delete_SOAPStruct(SOAPStruct* param, bool bArray, int nSize);
extern int Axis_Serialize_SOAPStruct(SOAPStruct* param, IWrapperSoapSerializer* pSZ, bool bArray);
extern int Axis_GetSize_SOAPStruct();

void* get_InteropTestPortType_stub(const char* pchEndPointUri)
{	if(pchEndPointUri)
	{
		return getStubObject(APTHTTP1_1, pchEndPointUri);
	}
	else
	{
		return getStubObject(APTHTTP1_1, "http://localhost/axis/cbase");
	}
}
void destroy_InteropTestPortType_stub(void* p){
	destroyStubObject(p);
}
int get_InteropTestPortType_Status(void* pStub){
	Call* pCall = (Call*)pStub;
	if ( pStub == NULL ) return AXIS_SUCCESS; else return pCall->_functions->getStatus(pCall->_object);
	}

/*Methods corresponding to the web service methods*/

/*
 * This method wrap the service methodechoString
 */
xsd__string echoString(void* pStub, xsd__string Value0)
{
	Call* pCall = (Call*)pStub;
	xsd__string Ret;
	/* Following will establish the connections with the server too */
	if (AXIS_SUCCESS != pCall->_functions->initialize(pCall->_object,C_RPC_PROVIDER, NORMAL_CHANNEL)) return Ret;
	pCall->_functions->setTransportProperty(pCall->_object,SOAPACTION_HEADER , "cbase#echoString");
	pCall->_functions->setSOAPVersion(pCall->_object, SOAP_VER_1_1);
	pCall->_functions->setOperation(pCall->_object, "echoString", "http://soapinterop.org/");
	pCall->_functions->addParameter(pCall->_object, (void*)&Value0, "inputString", XSD_STRING);
	if (AXIS_SUCCESS == pCall->_functions->invoke(pCall->_object))
	{
		if(AXIS_SUCCESS == pCall->_functions->checkMessage(pCall->_object, "echoStringResponse", ""))
		{
			Ret = pCall->_functions->getElementAsString(pCall->_object, "return", 0);
		}
	}
	pCall->_functions->unInitialize(pCall->_object);
	return Ret;
}


/*
 * This method wrap the service methodechoStringArray
 */
xsd__string_Array echoStringArray(void* pStub, xsd__string_Array Value0)
{
	Call* pCall = (Call*)pStub;
	xsd__string_Array RetArray = {NULL, 0};
	Axis_Array array;
	/* Following will establish the connections with the server too */
	if (AXIS_SUCCESS != pCall->_functions->initialize(pCall->_object,C_RPC_PROVIDER, NORMAL_CHANNEL)) return RetArray;
	pCall->_functions->setTransportProperty(pCall->_object,SOAPACTION_HEADER , "cbase#echoStringArray");
	pCall->_functions->setSOAPVersion(pCall->_object, SOAP_VER_1_1);
	pCall->_functions->setOperation(pCall->_object, "echoStringArray", "http://soapinterop.org/");
	pCall->_functions->addBasicArrayParameter(pCall->_object, (Axis_Array*)(&Value0), XSD_STRING, "inputStringArray");
	if (AXIS_SUCCESS == pCall->_functions->invoke(pCall->_object))
	{
		if(AXIS_SUCCESS == pCall->_functions->checkMessage(pCall->_object, "echoStringArrayResponse", ""))
		{
			array = pCall->_functions->getBasicArray(pCall->_object, XSD_STRING, "return", 0);
			memcpy(&RetArray, &array, sizeof(Axis_Array));
		}
	}
	pCall->_functions->unInitialize(pCall->_object);
	return RetArray;
}


/*
 * This method wrap the service methodechoInteger
 */
int echoInteger(void* pStub, int Value0)
{
	Call* pCall = (Call*)pStub;
	int Ret;
	/* Following will establish the connections with the server too */
	if (AXIS_SUCCESS != pCall->_functions->initialize(pCall->_object,C_RPC_PROVIDER, NORMAL_CHANNEL)) return Ret;
	pCall->_functions->setTransportProperty(pCall->_object,SOAPACTION_HEADER , "cbase#echoInteger");
	pCall->_functions->setSOAPVersion(pCall->_object, SOAP_VER_1_1);
	pCall->_functions->setOperation(pCall->_object, "echoInteger", "http://soapinterop.org/");
	pCall->_functions->addParameter(pCall->_object, (void*)&Value0, "inputInteger", XSD_INT);
	if (AXIS_SUCCESS == pCall->_functions->invoke(pCall->_object))
	{
		if(AXIS_SUCCESS == pCall->_functions->checkMessage(pCall->_object, "echoIntegerResponse", ""))
		{
			Ret = pCall->_functions->getElementAsInt(pCall->_object, "return", 0);
		}
	}
	pCall->_functions->unInitialize(pCall->_object);
	return Ret;
}


/*
 * This method wrap the service methodechoIntegerArray
 */
xsd__int_Array echoIntegerArray(void* pStub, xsd__int_Array Value0)
{
	Call* pCall = (Call*)pStub;
	xsd__int_Array RetArray = {NULL, 0};
	Axis_Array array;
	/* Following will establish the connections with the server too */
	if (AXIS_SUCCESS != pCall->_functions->initialize(pCall->_object,C_RPC_PROVIDER, NORMAL_CHANNEL)) return RetArray;
	pCall->_functions->setTransportProperty(pCall->_object,SOAPACTION_HEADER , "cbase#echoIntegerArray");
	pCall->_functions->setSOAPVersion(pCall->_object, SOAP_VER_1_1);
	pCall->_functions->setOperation(pCall->_object, "echoIntegerArray", "http://soapinterop.org/");
	pCall->_functions->addBasicArrayParameter(pCall->_object, (Axis_Array*)(&Value0), XSD_INT, "inputIntegerArray");
	if (AXIS_SUCCESS == pCall->_functions->invoke(pCall->_object))
	{
		if(AXIS_SUCCESS == pCall->_functions->checkMessage(pCall->_object, "echoIntegerArrayResponse", ""))
		{
			array = pCall->_functions->getBasicArray(pCall->_object, XSD_INT, "return", 0);
			memcpy(&RetArray, &array, sizeof(Axis_Array));
		}
	}
	pCall->_functions->unInitialize(pCall->_object);
	return RetArray;
}


/*
 * This method wrap the service methodechoFloat
 */
float echoFloat(void* pStub, float Value0)
{
	Call* pCall = (Call*)pStub;
	float Ret;
	/* Following will establish the connections with the server too */
	if (AXIS_SUCCESS != pCall->_functions->initialize(pCall->_object,C_RPC_PROVIDER, NORMAL_CHANNEL)) return Ret;
	pCall->_functions->setTransportProperty(pCall->_object,SOAPACTION_HEADER , "cbase#echoFloat");
	pCall->_functions->setSOAPVersion(pCall->_object, SOAP_VER_1_1);
	pCall->_functions->setOperation(pCall->_object, "echoFloat", "http://soapinterop.org/");
	pCall->_functions->addParameter(pCall->_object, (void*)&Value0, "inputFloat", XSD_FLOAT);
	if (AXIS_SUCCESS == pCall->_functions->invoke(pCall->_object))
	{
		if(AXIS_SUCCESS == pCall->_functions->checkMessage(pCall->_object, "echoFloatResponse", ""))
		{
			Ret = pCall->_functions->getElementAsFloat(pCall->_object, "return", 0);
		}
	}
	pCall->_functions->unInitialize(pCall->_object);
	return Ret;
}


/*
 * This method wrap the service methodechoFloatArray
 */
xsd__float_Array echoFloatArray(void* pStub, xsd__float_Array Value0)
{
	Call* pCall = (Call*)pStub;
	xsd__float_Array RetArray = {NULL, 0};
	Axis_Array array;
	/* Following will establish the connections with the server too */
	if (AXIS_SUCCESS != pCall->_functions->initialize(pCall->_object,C_RPC_PROVIDER, NORMAL_CHANNEL)) return RetArray;
	pCall->_functions->setTransportProperty(pCall->_object,SOAPACTION_HEADER , "cbase#echoFloatArray");
	pCall->_functions->setSOAPVersion(pCall->_object, SOAP_VER_1_1);
	pCall->_functions->setOperation(pCall->_object, "echoFloatArray", "http://soapinterop.org/");
	pCall->_functions->addBasicArrayParameter(pCall->_object, (Axis_Array*)(&Value0), XSD_FLOAT, "inputFloatArray");
	if (AXIS_SUCCESS == pCall->_functions->invoke(pCall->_object))
	{
		if(AXIS_SUCCESS == pCall->_functions->checkMessage(pCall->_object, "echoFloatArrayResponse", ""))
		{
			array = pCall->_functions->getBasicArray(pCall->_object, XSD_FLOAT, "return", 0);
			memcpy(&RetArray, &array, sizeof(Axis_Array));
		}
	}
	pCall->_functions->unInitialize(pCall->_object);
	return RetArray;
}


/*
 * This method wrap the service methodechoStruct
 */
SOAPStruct* echoStruct(void* pStub, SOAPStruct* Value0)
{
	Call* pCall = (Call*)pStub;
	SOAPStruct* pReturn = NULL;
	/* Following will establish the connections with the server too */
	if (AXIS_SUCCESS != pCall->_functions->initialize(pCall->_object,C_RPC_PROVIDER, NORMAL_CHANNEL)) return pReturn;
	pCall->_functions->setTransportProperty(pCall->_object,SOAPACTION_HEADER , "cbase#echoStruct");
	pCall->_functions->setSOAPVersion(pCall->_object, SOAP_VER_1_1);
	pCall->_functions->setOperation(pCall->_object, "echoStruct", "http://soapinterop.org/");
	pCall->_functions->addCmplxParameter(pCall->_object, Value0, (void*)Axis_Serialize_SOAPStruct, (void*)Axis_Delete_SOAPStruct, "inputStruct", 0);
	if (AXIS_SUCCESS == pCall->_functions->invoke(pCall->_object))
	{
		if(AXIS_SUCCESS == pCall->_functions->checkMessage(pCall->_object, "echoStructResponse", ""))
		{
			pReturn = (SOAPStruct*)pCall->_functions->getCmplxObject(pCall->_object, (void*) Axis_DeSerialize_SOAPStruct, (void*) Axis_Create_SOAPStruct, (void*) Axis_Delete_SOAPStruct,"return", 0);
		}
	}
	pCall->_functions->unInitialize(pCall->_object);
	return pReturn;
}


/*
 * This method wrap the service methodechoStructArray
 */
SOAPStruct_Array echoStructArray(void* pStub, SOAPStruct_Array Value0)
{
	Call* pCall = (Call*)pStub;
	SOAPStruct_Array RetArray = {NULL, 0};
	Axis_Array array;
	/* Following will establish the connections with the server too */
	if (AXIS_SUCCESS != pCall->_functions->initialize(pCall->_object,C_RPC_PROVIDER, NORMAL_CHANNEL)) return RetArray;
	pCall->_functions->setTransportProperty(pCall->_object,SOAPACTION_HEADER , "cbase#echoStructArray");
	pCall->_functions->setSOAPVersion(pCall->_object, SOAP_VER_1_1);
	pCall->_functions->setOperation(pCall->_object, "echoStructArray", "http://soapinterop.org/");
	pCall->_functions->addCmplxArrayParameter(pCall->_object, (Axis_Array*)(&Value0), (void*)Axis_Serialize_SOAPStruct, (void*)Axis_Delete_SOAPStruct, (void*) Axis_GetSize_SOAPStruct, Axis_TypeName_SOAPStruct, Axis_URI_SOAPStruct);
	if (AXIS_SUCCESS == pCall->_functions->invoke(pCall->_object))
	{
		if(AXIS_SUCCESS == pCall->_functions->checkMessage(pCall->_object, "echoStructArrayResponse", ""))
		{
			array = pCall->_functions->getCmplxArray(pCall->_object, (void*) Axis_DeSerialize_SOAPStruct, (void*) Axis_Create_SOAPStruct, (void*) Axis_Delete_SOAPStruct, (void*) Axis_GetSize_SOAPStruct, "return", 0);
			memcpy(&RetArray, &array, sizeof(Axis_Array));
		}
	}
	pCall->_functions->unInitialize(pCall->_object);
	return RetArray;
}


/*
 * This method wrap the service methodechoVoid
 */
void echoVoid(void* pStub)
{
	Call* pCall = (Call*)pStub;
	/* Following will establish the connections with the server too */
	if (AXIS_SUCCESS != pCall->_functions->initialize(pCall->_object,C_RPC_PROVIDER, NORMAL_CHANNEL)) return ;
	pCall->_functions->setTransportProperty(pCall->_object,SOAPACTION_HEADER , "cbase#echoVoid");
	pCall->_functions->setSOAPVersion(pCall->_object, SOAP_VER_1_1);
	pCall->_functions->setOperation(pCall->_object, "echoVoid", "http://soapinterop.org/");
	if (AXIS_SUCCESS == pCall->_functions->invoke(pCall->_object))
	{
		if(AXIS_SUCCESS == pCall->_functions->checkMessage(pCall->_object, "echoVoidResponse", ""))
		{
			/*not successful*/
		}
	}
	pCall->_functions->unInitialize(pCall->_object);
}


/*
 * This method wrap the service methodechoBase64
 */
xsd__base64Binary echoBase64(void* pStub, xsd__base64Binary Value0)
{
	Call* pCall = (Call*)pStub;
	xsd__base64Binary Ret;
	/* Following will establish the connections with the server too */
	if (AXIS_SUCCESS != pCall->_functions->initialize(pCall->_object,C_RPC_PROVIDER, NORMAL_CHANNEL)) return Ret;
	pCall->_functions->setTransportProperty(pCall->_object,SOAPACTION_HEADER , "cbase#echoBase64");
	pCall->_functions->setSOAPVersion(pCall->_object, SOAP_VER_1_1);
	pCall->_functions->setOperation(pCall->_object, "echoBase64", "http://soapinterop.org/");
	pCall->_functions->addParameter(pCall->_object, (void*)&Value0, "inputBase64", XSD_BASE64BINARY);
	if (AXIS_SUCCESS == pCall->_functions->invoke(pCall->_object))
	{
		if(AXIS_SUCCESS == pCall->_functions->checkMessage(pCall->_object, "echoBase64Response", ""))
		{
			Ret = pCall->_functions->getElementAsBase64Binary(pCall->_object, "return", 0);
		}
	}
	pCall->_functions->unInitialize(pCall->_object);
	return Ret;
}


/*
 * This method wrap the service methodechoDate
 */
xsd__dateTime echoDate(void* pStub, xsd__dateTime Value0)
{
	Call* pCall = (Call*)pStub;
	xsd__dateTime Ret;
	/* Following will establish the connections with the server too */
	if (AXIS_SUCCESS != pCall->_functions->initialize(pCall->_object,C_RPC_PROVIDER, NORMAL_CHANNEL)) return Ret;
	pCall->_functions->setTransportProperty(pCall->_object,SOAPACTION_HEADER , "cbase#echoDate");
	pCall->_functions->setSOAPVersion(pCall->_object, SOAP_VER_1_1);
	pCall->_functions->setOperation(pCall->_object, "echoDate", "http://soapinterop.org/");
	pCall->_functions->addParameter(pCall->_object, (void*)&Value0, "inputDate", XSD_DATETIME);
	if (AXIS_SUCCESS == pCall->_functions->invoke(pCall->_object))
	{
		if(AXIS_SUCCESS == pCall->_functions->checkMessage(pCall->_object, "echoDateResponse", ""))
		{
			Ret = pCall->_functions->getElementAsDateTime(pCall->_object, "return", 0);
		}
	}
	pCall->_functions->unInitialize(pCall->_object);
	return Ret;
}


/*
 * This method wrap the service methodechoHexBinary
 */
xsd__hexBinary echoHexBinary(void* pStub, xsd__hexBinary Value0)
{
	Call* pCall = (Call*)pStub;
	xsd__hexBinary Ret;
	/* Following will establish the connections with the server too */
	if (AXIS_SUCCESS != pCall->_functions->initialize(pCall->_object,C_RPC_PROVIDER, NORMAL_CHANNEL)) return Ret;
	pCall->_functions->setTransportProperty(pCall->_object,SOAPACTION_HEADER , "cbase#echoHexBinary");
	pCall->_functions->setSOAPVersion(pCall->_object, SOAP_VER_1_1);
	pCall->_functions->setOperation(pCall->_object, "echoHexBinary", "http://soapinterop.org/");
	pCall->_functions->addParameter(pCall->_object, (void*)&Value0, "inputHexBinary", XSD_HEXBINARY);
	if (AXIS_SUCCESS == pCall->_functions->invoke(pCall->_object))
	{
		if(AXIS_SUCCESS == pCall->_functions->checkMessage(pCall->_object, "echoHexBinaryResponse", ""))
		{
			Ret = pCall->_functions->getElementAsHexBinary(pCall->_object, "return", 0);
		}
	}
	pCall->_functions->unInitialize(pCall->_object);
	return Ret;
}


/*
 * This method wrap the service methodechoDecimal
 */
xsd__decimal echoDecimal(void* pStub, xsd__decimal Value0)
{
	Call* pCall = (Call*)pStub;
	xsd__decimal Ret;
	/* Following will establish the connections with the server too */
	if (AXIS_SUCCESS != pCall->_functions->initialize(pCall->_object,C_RPC_PROVIDER, NORMAL_CHANNEL)) return Ret;
	pCall->_functions->setTransportProperty(pCall->_object,SOAPACTION_HEADER , "cbase#echoDecimal");
	pCall->_functions->setSOAPVersion(pCall->_object, SOAP_VER_1_1);
	pCall->_functions->setOperation(pCall->_object, "echoDecimal", "http://soapinterop.org/");
	pCall->_functions->addParameter(pCall->_object, (void*)&Value0, "inputDecimal", XSD_DECIMAL);
	if (AXIS_SUCCESS == pCall->_functions->invoke(pCall->_object))
	{
		if(AXIS_SUCCESS == pCall->_functions->checkMessage(pCall->_object, "echoDecimalResponse", ""))
		{
			Ret = pCall->_functions->getElementAsDecimal(pCall->_object, "return", 0);
		}
	}
	pCall->_functions->unInitialize(pCall->_object);
	return Ret;
}


/*
 * This method wrap the service methodechoBoolean
 */
xsd__boolean echoBoolean(void* pStub, xsd__boolean Value0)
{
	Call* pCall = (Call*)pStub;
	xsd__boolean Ret;
	/* Following will establish the connections with the server too */
	if (AXIS_SUCCESS != pCall->_functions->initialize(pCall->_object,C_RPC_PROVIDER, NORMAL_CHANNEL)) return Ret;
	pCall->_functions->setTransportProperty(pCall->_object,SOAPACTION_HEADER , "cbase#echoBoolean");
	pCall->_functions->setSOAPVersion(pCall->_object, SOAP_VER_1_1);
	pCall->_functions->setOperation(pCall->_object, "echoBoolean", "http://soapinterop.org/");
	pCall->_functions->addParameter(pCall->_object, (void*)&Value0, "inputBoolean", XSD_BOOLEAN);
	if (AXIS_SUCCESS == pCall->_functions->invoke(pCall->_object))
	{
		if(AXIS_SUCCESS == pCall->_functions->checkMessage(pCall->_object, "echoBooleanResponse", ""))
		{
			Ret = pCall->_functions->getElementAsBoolean(pCall->_object, "return", 0);
		}
	}
	pCall->_functions->unInitialize(pCall->_object);
	return Ret;
}

