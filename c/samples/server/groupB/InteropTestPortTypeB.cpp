// Copyright 2003-2004 The Apache Software Foundation.
// 
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
// 
//        http://www.apache.org/licenses/LICENSE-2.0
// 
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/*
 * This file was auto-generated by the Axis C++ Web Service Generator (WSDL2Ws)
 * This file contains definitions of the web service
 */

#include "InteropTestPortTypeB.h"


InteropTestPortTypeB::InteropTestPortTypeB()
{
}

InteropTestPortTypeB::~InteropTestPortTypeB()
{
}

/* This function is called by the AxisEngine when something went wrong
 with the current web service request processing. Appropriate actions should
 be taken here.*/
void InteropTestPortTypeB::onFault()
{
}
/* This function is called by the AxisEngine when this web service
 library is first loaded. So here we can initialize any global/static
 data structures of this web service or open database connections */
void InteropTestPortTypeB::init()
{
}
/* This function is called by the AxisEngine when this web service
 library is unloaded. So we can deallocate any global/static data structures
 and close database connections etc here. */
void InteropTestPortTypeB::fini()
{
}
void InteropTestPortTypeB::echoStructAsSimpleTypes(SOAPStruct* Value0, AXIS_OUT_PARAM xsd__string *OutValue0, AXIS_OUT_PARAM int *OutValue1, AXIS_OUT_PARAM float *OutValue2)  
{	
	*OutValue0 = Value0->varString;
	*OutValue1 = Value0->varInt;
	*OutValue2 = Value0->varFloat;
}

SOAPStruct* InteropTestPortTypeB::echoSimpleTypesAsStruct(xsd__string Value0, int Value1, float Value2)  
{
	SOAPStruct* pRet = new SOAPStruct();
	pRet->varString = Value0;
	pRet->varInt = Value1;
	pRet->varFloat = Value2;
	return pRet;
}
SOAPStructStruct* InteropTestPortTypeB::echoNestedStruct(SOAPStructStruct* Value0)  
{
	return Value0;
}
SOAPArrayStruct* InteropTestPortTypeB::echoNestedArray(SOAPArrayStruct* Value0)  
{
	return Value0;
}
