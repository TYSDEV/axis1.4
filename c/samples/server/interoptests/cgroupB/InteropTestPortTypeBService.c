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
 * This file contains the two export functions of the Web service Dynamic Library 
 */

#include <malloc.h>
#include "InteropTestPortTypeBWrapper.h"

static BasicHandlerFunctions InteropTestPortTypeBWrapper_functions = {
	InteropTestPortTypeBWrapper_Invoke,
	InteropTestPortTypeBWrapper_OnFault,
	InteropTestPortTypeBWrapper_Init,
	InteropTestPortTypeBWrapper_Fini,
	InteropTestPortTypeBWrapper_GetType,
	InteropTestPortTypeBWrapper_GetBindingStyle
};
STORAGE_CLASS_INFO
int GetClassInstance(BasicHandler **inst){
	*inst = malloc(sizeof(BasicHandler));
	(*inst)->_object = 0;	/* instantiate and provide the context object if needed */
	(*inst)->_functions = &InteropTestPortTypeBWrapper_functions;
	return (*inst)->_functions->init((*inst)->_object);
}

STORAGE_CLASS_INFO
int DestroyInstance(BasicHandler *inst){
	if (inst){
		inst->_functions->fini(inst->_object);
		/* destroy the context object set to inst->_object if any here */
		free(inst);
		return AXIS_SUCCESS;
	}
	return AXIS_FAIL;
}
