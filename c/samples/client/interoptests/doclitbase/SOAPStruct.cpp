///////////////////////////////////////////////////////////////////////////////////
//This file is automatically generated by the Axis C++ Wrapper Class Generator
//Web service wrapper class's implementation generated by Axis WCG
//Parameters and wrapper methos to manipulate SOAPStruct
////////////////////////////////////////////////////////////////////////////////////

#include "SOAPStruct.h"
#include <axis/server/AxisWrapperAPI.h>

/////////////////////////////////////////////////////////////////////////////
// This static method serialize a SOAPStruct type of object
//////////////////////////////////////////////////////////////////////
int Axis_Serialize_SOAPStruct(SOAPStruct* param, IWrapperSoapSerializer* pSZ, bool bArray = false)
{
	pSZ->SerializeAsElement("varString", (void*)&(param->varString), XSD_STRING);
	pSZ->SerializeAsElement("varInt", (void*)&(param->varInt), XSD_INT);
	pSZ->SerializeAsElement("varFloat", (void*)&(param->varFloat), XSD_FLOAT);
	return AXIS_SUCCESS;
}

/////////////////////////////////////////////////////////////////////////////
// This static method deserialize a SOAPStruct type of object
//////////////////////////////////////////////////////////////////////
int Axis_DeSerialize_SOAPStruct(SOAPStruct* param, IWrapperSoapDeSerializer *pIWSDZ)
{
	param->varString = pIWSDZ->GetElementAsString("varString",0);
	param->varInt = pIWSDZ->GetElementAsInt("varInt",0);
	param->varFloat = pIWSDZ->GetElementAsFloat("varFloat",0);
	return AXIS_SUCCESS;
}
void* Axis_Create_SOAPStruct(void* pObj, bool bArray = false, int nSize=0)
{

	if (bArray && (nSize > 0))
	{
		if (pObj)
		{
			SOAPStruct* pNew = new SOAPStruct[nSize];
			memcpy(pNew, pObj, sizeof(SOAPStruct)*nSize/2);
			memset(pObj, 0, sizeof(SOAPStruct)*nSize/2);
			delete [] ((SOAPStruct*)pObj);
			return pNew;
		}
		else
		{
			return new SOAPStruct[nSize];
		}
	}
	else
		return new SOAPStruct;
}

SOAPStruct::~SOAPStruct()
{
	free(varString);
}

SOAPStruct::SOAPStruct()
{
	varString = 0;
}

/////////////////////////////////////////////////////////////////////////////
// This static method delete a SOAPStruct type of object
//////////////////////////////////////////////////////////////////////
void Axis_Delete_SOAPStruct(SOAPStruct* param, bool bArray = false, int nSize=0)
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
/////////////////////////////////////////////////////////////////////////////
// This static method gives the size of SOAPStruct type of object
//////////////////////////////////////////////////////////////////////
int Axis_GetSize_SOAPStruct()
{
	return sizeof(SOAPStruct);
}
