/*
 *This file is automatically generated by the Axis C++ Wrapper Class Generator
 *Web service wrapper class's implementation generated by Axis WCG
 *Parameters and wrapper methos to manipulate SOAPStructStruct
 */

#include <malloc.h>
#include "SOAPStructStruct.h"
#include <axis/server/AxisWrapperAPI.h>

extern int Axis_DeSerialize_SOAPStruct(SOAPStruct* param, IWrapperSoapDeSerializer *pDZ);
extern void* Axis_Create_SOAPStruct(SOAPStruct* pObj, bool bArray, int nSize);
extern void Axis_Delete_SOAPStruct(SOAPStruct* param, bool bArray, int nSize);
extern int Axis_Serialize_SOAPStruct(SOAPStruct* param, IWrapperSoapSerializer* pSZ, bool bArray);
extern int Axis_GetSize_SOAPStruct();

/**
 * This static method serialize a SOAPStructStruct type of object
 */
int Axis_Serialize_SOAPStructStruct(SOAPStructStruct* param, IWrapperSoapSerializer* pSZ, bool bArray)
{
	const AxisChar* sPrefix;
	if (bArray)
	{
		pSZ->_functions->SerializeStartElementOfType(pSZ->_object, Axis_TypeName_SOAPStructStruct, 0, 0);
	}
	else
	{
		sPrefix = pSZ->_functions->GetNamespacePrefix(pSZ->_object, Axis_URI_SOAPStructStruct);
		pSZ->_functions->SerializeStartElementOfType(pSZ->_object, Axis_TypeName_SOAPStructStruct, Axis_URI_SOAPStructStruct, sPrefix);
	}

	pSZ->_functions->SerializeAsElement(pSZ->_object, "varString", (void*)&(param->varString), XSD_STRING);
	pSZ->_functions->SerializeAsElement(pSZ->_object, "varInt", (void*)&(param->varInt), XSD_INT);
	pSZ->_functions->SerializeAsElement(pSZ->_object, "varFloat", (void*)&(param->varFloat), XSD_FLOAT);
	Axis_Serialize_SOAPStruct(param->varStruct, pSZ, false);

	pSZ->_functions->SerializeEndElementOfType(pSZ->_object, Axis_TypeName_SOAPStructStruct);
	return AXIS_SUCCESS;
}

/**
 * This static method deserialize a SOAPStructStruct type of object
 */
int Axis_DeSerialize_SOAPStructStruct(SOAPStructStruct* param, IWrapperSoapDeSerializer *pDZ)
{
	param->varString = pDZ->_functions->GetElementAsString(pDZ->_object,0,0);
	param->varInt = pDZ->_functions->GetElementAsInt(pDZ->_object,0,0);
	param->varFloat = pDZ->_functions->GetElementAsFloat(pDZ->_object,0,0);
	param->varStruct = (SOAPStruct*)pDZ->_functions->GetCmplxObject(pDZ->_object, (void*)Axis_DeSerialize_SOAPStruct
		, (void*)Axis_Create_SOAPStruct, (void*)Axis_Delete_SOAPStruct
		, Axis_TypeName_SOAPStruct, Axis_URI_SOAPStruct);
	return pDZ->_functions->GetStatus(pDZ->_object);
}
void* Axis_Create_SOAPStructStruct(SOAPStructStruct* pObj, bool bArray, int nSize)
{
	if (bArray && (nSize > 0))
	{
		pObj = malloc(sizeof(SOAPStructStruct)*nSize);
		memset(pObj, 0, sizeof(SOAPStructStruct)*nSize);
	}
	else
	{
		pObj = malloc(sizeof(SOAPStructStruct));
		memset(pObj, 0, sizeof(SOAPStructStruct));
	}
	return pObj;
}
/**
 * This static method to deallocate a SOAPStructStruct type of object
 */
void Axis_Delete_SOAPStructStruct(SOAPStructStruct* param, bool bArray, int nSize)
{
	int x;
	SOAPStructStruct* pTemp;
	if (bArray)
	{
		/*delete any pointer members or array members of this struct here*/
		pTemp = param;
		for (x=0; x<nSize; x++)
		{
			if(pTemp->varString) free(pTemp->varString);
			if (pTemp->varStruct) Axis_Delete_SOAPStruct(pTemp->varStruct, false, 0);
			pTemp++;
		}
		free(param);
	}
	else
	{
		/*delete any pointer members or array members of this struct here*/
		if(param->varString) free(param->varString);
		if (param->varStruct) Axis_Delete_SOAPStruct(param->varStruct, false, 0);
		free(param);
	}
}
/**
 * This static method gives the size of SOAPStructStruct type of object
 */
int Axis_GetSize_SOAPStructStruct()
{
	return sizeof(SOAPStructStruct);
}
