///////////////////////////////////////////////////////////////////////////////////
//This file is automatically generated by the Axis C++ Wrapper Class Generator
//Web service wrapper class's implementation generated by Axis WCG
//Parameters and wrapper methos to manipulate SOAPStruct
////////////////////////////////////////////////////////////////////////////////////

#include "SOAPStruct.h"
#include <axis/common/AxisWrapperAPI.h>

/////////////////////////////////////////////////////////////////////////////
// This static method serialize a SOAPStruct type of object
//////////////////////////////////////////////////////////////////////
int Axis_Serialize_SOAPStruct(SOAPStruct* param, IWrapperSoapSerializer& pSZ, bool bArray = false)
{
	if (bArray)
	{
		pSZ << "<" << Axis_TypeName_SOAPStruct << ">";
	}
	else
	{
		const AxisChar* sPrefix = pSZ.getNewNamespacePrefix();
		pSZ << "<" << Axis_TypeName_SOAPStruct << " xsi:type=\"" << sPrefix <<":"
			<< Axis_TypeName_SOAPStruct << "\" xmlns:" << sPrefix << "=\""
			<< Axis_URI_SOAPStruct << "\">";
	}

	pSZ << pSZ.SerializeBasicType("varString", param->varString, XSD_STRING);
	pSZ << pSZ.SerializeBasicType("varInt", param->varInt, XSD_INT);
	pSZ << pSZ.SerializeBasicType("varFloat", param->varFloat, XSD_FLOAT);

	pSZ << "</" << Axis_TypeName_SOAPStruct << ">";
	return AXIS_SUCCESS;
}

/////////////////////////////////////////////////////////////////////////////
// This static method deserialize a SOAPStruct type of object
//////////////////////////////////////////////////////////////////////
int Axis_DeSerialize_SOAPStruct(SOAPStruct* param, IWrapperSoapDeSerializer *pIWSDZ)
{
	param->varString = pIWSDZ->GetString();
	param->varInt = pIWSDZ->GetInt();
	param->varFloat = pIWSDZ->GetFloat();
	return AXIS_SUCCESS;
}
void* Axis_Create_SOAPStruct(bool bArray = false, int nSize=0)
{
	if (bArray && (nSize > 0))
		return new SOAPStruct[nSize];
	else
		return new SOAPStruct;
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
		//delete any pointer members or array members of this struct here
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

SOAPStruct::SOAPStruct()
{

}

SOAPStruct::~SOAPStruct()
{

}
