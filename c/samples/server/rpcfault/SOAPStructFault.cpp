/*
 *This file is automatically generated by the Axis C++ Wrapper Class Generator
 *Web service wrapper class's implementation generated by Axis WCG
 *Parameters and wrapper methos to manipulate SOAPStructFault
 */

#include <malloc.h>
#include "SOAPStructFault.h"
#include <axis/server/AxisWrapperAPI.h>

/*
 * This static method serialize a SOAPStructFault type of object
 */
int Axis_Serialize_SOAPStructFault(SOAPStructFault* param, IWrapperSoapSerializer* pSZ, bool bArray = false)
{
	if (bArray)
	{
		pSZ->serialize("<", Axis_TypeName_SOAPStructFault, ">", NULL);
	}
	else
	{
		const AxisChar* sPrefix = pSZ->getNamespacePrefix(Axis_URI_SOAPStructFault);
		pSZ->serialize("<", Axis_TypeName_SOAPStructFault, " xsi:type=\"", sPrefix, ":",
			Axis_TypeName_SOAPStructFault, "\" xmlns:", sPrefix, "=\"",
			Axis_URI_SOAPStructFault, "\">", NULL);
	}

	pSZ->serializeAsElement("varString", (void*)&(param->varString), XSD_STRING);
	pSZ->serializeAsElement("varInt", (void*)&(param->varInt), XSD_INT);
	pSZ->serializeAsElement("varFloat", (void*)&(param->varFloat), XSD_FLOAT);

	pSZ->serialize("</", Axis_TypeName_SOAPStructFault, ">", NULL);
	return AXIS_SUCCESS;
}

/*
 * This static method deserialize a SOAPStructFault type of object
 */
int Axis_DeSerialize_SOAPStructFault(SOAPStructFault* param, IWrapperSoapDeSerializer *pIWSDZ)
{
	param->varString = pIWSDZ->getElementAsString("varString",0);
	param->varInt = pIWSDZ->getElementAsInt("varInt",0);
	param->varFloat = pIWSDZ->getElementAsFloat("varFloat",0);
	if(AXIS_FAIL == pIWSDZ->getStatus())
        {
            param->varString = "Division by zero exception";
            param->varInt = 1;
            param->varFloat = 10.52;
            return AXIS_SUCCESS;
        }
	return pIWSDZ->getStatus();
}

void* Axis_Create_SOAPStructFault(SOAPStructFault* pObj, bool bArray = false, int nSize=0)
{
	if (bArray && (nSize > 0))
	{
		if (pObj)
		{
			SOAPStructFault* pNew = new SOAPStructFault[nSize];
			memcpy(pNew, pObj, sizeof(SOAPStructFault)*nSize/2);
			memset(pObj, 0, sizeof(SOAPStructFault)*nSize/2);
			delete [] pObj;
			return pNew;
		}
		else
		{
			return new SOAPStructFault[nSize];
		}
	}
	else
		return new SOAPStructFault;
}

/*
 * This static method delete a SOAPStructFault type of object
 */
void Axis_Delete_SOAPStructFault(SOAPStructFault* param, bool bArray = false, int nSize=0)
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
 * This static method gives the size of SOAPStructFault type of object
 */
int Axis_GetSize_SOAPStructFault()
{
	return sizeof(SOAPStructFault);
}

SOAPStructFault::SOAPStructFault()
{
	/*do not allocate memory to any pointer members here
	 because deserializer will allocate memory anyway. */
}

SOAPStructFault::~SOAPStructFault()
{
	/*delete any pointer and array members here*/
}
