/*
 *This file is automatically generated by the Axis C++ Wrapper Class Generator
 *Web service wrapper class's implementation generated by Axis WCG
 *Parameters and wrapper methos to manipulate SOAPArrayStruct
 */

#include <malloc.h>
#include "SOAPArrayStruct.h"
#include <axis/server/AxisWrapperAPI.h>

/**
 * This static method serialize a SOAPArrayStruct type of object
 */
int Axis_Serialize_SOAPArrayStruct(SOAPArrayStruct* param, IWrapperSoapSerializer* pSZ, bool bArray)
{
	const AxisChar* sPrefix;
	if (bArray)
	{
		pSZ->_functions->SerializeStartElementOfType(pSZ->_object, Axis_TypeName_SOAPArrayStruct, 0, 0);
	}
	else
	{
		sPrefix = pSZ->_functions->GetNamespacePrefix(pSZ->_object, Axis_URI_SOAPArrayStruct);
		pSZ->_functions->SerializeStartElementOfType(pSZ->_object, Axis_TypeName_SOAPArrayStruct, Axis_URI_SOAPArrayStruct, sPrefix);
	}

	pSZ->_functions->SerializeAsElement(pSZ->_object, "varString", (void*)&(param->varString), XSD_STRING);
	pSZ->_functions->SerializeAsElement(pSZ->_object, "varInt", (void*)&(param->varInt), XSD_INT);
	pSZ->_functions->SerializeAsElement(pSZ->_object, "varFloat", (void*)&(param->varFloat), XSD_FLOAT);
	pSZ->_functions->SerializeBasicArray(pSZ->_object, (Axis_Array*)(&param->varArray),XSD_STRING, "varArray");

	pSZ->_functions->SerializeEndElementOfType(pSZ->_object, Axis_TypeName_SOAPArrayStruct);
	return AXIS_SUCCESS;
}

/**
 * This static method deserialize a SOAPArrayStruct type of object
 */
int Axis_DeSerialize_SOAPArrayStruct(SOAPArrayStruct* param, IWrapperSoapDeSerializer *pDZ)
{
	Axis_Array array;
	param->varString = pDZ->_functions->GetElementAsString(pDZ->_object,0,0);
	param->varInt = pDZ->_functions->GetElementAsInt(pDZ->_object,0,0);
	param->varFloat = pDZ->_functions->GetElementAsFloat(pDZ->_object,0,0);
	array = pDZ->_functions->GetBasicArray(pDZ->_object, XSD_STRING,0,0);
	memcpy(&(param->varArray), &array, sizeof(Axis_Array));
	return pDZ->_functions->GetStatus(pDZ->_object);
}
void* Axis_Create_SOAPArrayStruct(SOAPArrayStruct* pObj, bool bArray, int nSize)
{
	if (bArray && (nSize > 0))
	{
		pObj = malloc(sizeof(SOAPArrayStruct)*nSize);
		memset(pObj, 0, sizeof(SOAPArrayStruct)*nSize);
	}
	else
	{
		pObj = malloc(sizeof(SOAPArrayStruct));
		memset(pObj, 0, sizeof(SOAPArrayStruct));
	}
	return pObj;
}
/**
 * This static method to deallocate a SOAPArrayStruct type of object
 */
void Axis_Delete_SOAPArrayStruct(SOAPArrayStruct* param, bool bArray, int nSize)
{
	int x;
	SOAPArrayStruct* pTemp;
	if (bArray)
	{
		/*delete any pointer members or array members of this struct here*/
		pTemp = param;
		for (x=0; x<nSize; x++)
		{
			if(pTemp->varString) free(pTemp->varString);
			if (pTemp->varArray.m_Array) free(pTemp->varArray.m_Array);
			pTemp++;
		}
		free(param);
	}
	else
	{
		/*delete any pointer members or array members of this struct here*/
		if(param->varString) free(param->varString);
		if (param->varArray.m_Array) free(param->varArray.m_Array);
		free(param);
	}
}
/**
 * This static method gives the size of SOAPArrayStruct type of object
 */
int Axis_GetSize_SOAPArrayStruct()
{
	return sizeof(SOAPArrayStruct);
}
