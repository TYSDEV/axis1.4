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
 * This file contains Client Stub Class for remote web service 
 */

#if !defined(__INTEROPTESTPORTTYPEB_H__INCLUDED_)
#define __INTEROPTESTPORTTYPEB_H__INCLUDED_

#include <axis/client/Stub.h>
#include <axis/server/AxisWrapperAPI.h>
#include "SOAPArrayStruct.h"
#include "SOAPStruct.h"
#include "SOAPStructStruct.h"

extern void* get_InteropTestPortTypeB_stub(const char* pchEndPointUri);
extern void destroy_InteropTestPortTypeB_stub(void* p);
extern int get_InteropTestPortTypeB_Status(void* pStub);
extern void echoStructAsSimpleTypes(void* pStub, SOAPStruct* Value0, AXIS_OUT_PARAM xsd__string *OutValue0, AXIS_OUT_PARAM int *OutValue1, AXIS_OUT_PARAM float *OutValue2);
extern SOAPStruct* echoSimpleTypesAsStruct(void* pStub, xsd__string Value0, int Value1, float Value2);
extern SOAPStructStruct* echoNestedStruct(void* pStub, SOAPStructStruct* Value0);
extern SOAPArrayStruct* echoNestedArray(void* pStub, SOAPArrayStruct* Value0);


#endif /* !defined(__INTEROPTESTPORTTYPEB_H__INCLUDED_) */
