/*
 * This is the Service Class genarated by the tool WSDL2Ws
 * InteropTestPortTypeB.h: interface for the InteropTestPortTypeBclass.
 *
 */
#if !defined(__INTEROPTESTPORTTYPEB_SERVERSKELETON_H__OF_AXIS_INCLUDED_)
#define __INTEROPTESTPORTTYPEB_SERVERSKELETON_H__OF_AXIS_INCLUDED_

#include <axis/server/AxisUserAPI.h>

#include "SOAPArrayStruct.h"
#include "SOAPStruct.h"
#include "SOAPStructStruct.h"

class InteropTestPortTypeB 
{
	public:
		InteropTestPortTypeB();
	public:
		virtual ~InteropTestPortTypeB();
	public: 
		void echoStructAsSimpleTypes(SOAPStruct* Value0, AXIS_OUT_PARAM float *OutValue0, AXIS_OUT_PARAM int *OutValue1, AXIS_OUT_PARAM xsd__string *OutValue2);
		SOAPStruct* echoSimpleTypesAsStruct(float Value0,int Value1,xsd__string Value2);
		SOAPStructStruct* echoNestedStruct(SOAPStructStruct* Value0);
		SOAPArrayStruct* echoNestedArray(SOAPArrayStruct* Value0);
};

#endif /* !defined(__INTEROPTESTPORTTYPEB_SERVERSKELETON_H__OF_AXIS_INCLUDED_)*/
