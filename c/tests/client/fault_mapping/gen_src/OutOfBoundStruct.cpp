/*
 * This file was auto-generated by the Axis C++ Web Service Generator (WSDL2Ws)
 * This file contains functions to manipulate complex type OutOfBoundStruct
 */

#include <malloc.h>
#include "OutOfBoundStruct.h"
#include <axis/server/AxisWrapperAPI.h>

extern int Axis_DeSerialize_SpecialDetailStruct(SpecialDetailStruct* param, IWrapperSoapDeSerializer* pDZ);
extern void* Axis_Create_SpecialDetailStruct(SpecialDetailStruct* pObj, bool bArray = false, int nSize=0);
extern void Axis_Delete_SpecialDetailStruct(SpecialDetailStruct* param, bool bArray = false, int nSize=0);
extern int Axis_Serialize_SpecialDetailStruct(SpecialDetailStruct* param, IWrapperSoapSerializer* pSZ, bool bArray = false);
extern int Axis_GetSize_SpecialDetailStruct();

/*
 * This static method serialize a OutOfBoundStruct type of object
 */
int Axis_Serialize_OutOfBoundStruct(OutOfBoundStruct* param, IWrapperSoapSerializer* pSZ, bool bArray = false)
{
	if (bArray)
	{
		pSZ->serialize("<", Axis_TypeName_OutOfBoundStruct, ">", NULL);
	}
	else
	{
		const AxisChar* sPrefix = pSZ->getNamespacePrefix(Axis_URI_OutOfBoundStruct);
		pSZ->serialize("<", Axis_TypeName_OutOfBoundStruct, " xsi:type=\"", sPrefix, ":",
			Axis_TypeName_OutOfBoundStruct, "\" xmlns:", sPrefix, "=\"",
			Axis_URI_OutOfBoundStruct, "\">", NULL);
	}

	pSZ->serializeAsElement("varString", (void*)&(param->varString), XSD_STRING);
	pSZ->serializeAsElement("varInt", (void*)&(param->varInt), XSD_INT);
	Axis_Serialize_SpecialDetailStruct(param->specialDetail, pSZ);

	pSZ->serialize("</", Axis_TypeName_OutOfBoundStruct, ">", NULL);
	return AXIS_SUCCESS;
}

/*
 * This static method deserialize a OutOfBoundStruct type of object
 */
int Axis_DeSerialize_OutOfBoundStruct(OutOfBoundStruct* param, IWrapperSoapDeSerializer* pIWSDZ)
{
	param->varString = pIWSDZ->getElementAsString("varString",0);
	param->varInt = pIWSDZ->getElementAsInt("varInt",0);
	param->specialDetail = (SpecialDetailStruct*)pIWSDZ->getCmplxObject((void*)Axis_DeSerialize_SpecialDetailStruct
		, (void*)Axis_Create_SpecialDetailStruct, (void*)Axis_Delete_SpecialDetailStruct
		, "SpecialDetailStruct", Axis_URI_SpecialDetailStruct);
	return pIWSDZ->getStatus();
}
void* Axis_Create_OutOfBoundStruct(OutOfBoundStruct* pObj, bool bArray = false, int nSize=0)
{
	if (bArray && (nSize > 0))
	{
		if (pObj)
		{
			OutOfBoundStruct* pNew = new OutOfBoundStruct[nSize];
			memcpy(pNew, pObj, sizeof(OutOfBoundStruct)*nSize/2);
			memset(pObj, 0, sizeof(OutOfBoundStruct)*nSize/2);
			delete [] pObj;
			return pNew;
		}
		else
		{
			return new OutOfBoundStruct[nSize];
		}
	}
	else
		return new OutOfBoundStruct;
}

/*
 * This static method delete a OutOfBoundStruct type of object
 */
void Axis_Delete_OutOfBoundStruct(OutOfBoundStruct* param, bool bArray = false, int nSize=0)
{
	if (bArray)
	{
		delete [] param;
	}
	else
	{
		delete param;
	}
}
/*
 * This static method gives the size of OutOfBoundStruct type of object
 */
int Axis_GetSize_OutOfBoundStruct()
{
	return sizeof(OutOfBoundStruct);
}

OutOfBoundStruct::OutOfBoundStruct()
{
	/*do not allocate memory to any pointer members here
	 because deserializer will allocate memory anyway. */
	specialDetail=0;
}

OutOfBoundStruct::~OutOfBoundStruct()
{
	/*delete any pointer and array members here*/
	delete specialDetail;
}
