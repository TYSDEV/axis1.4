/*This file is automatically generated by the Axis C++ Wrapper Class Generator
 *Service file containing the two export functions of the Web service Library*/
#ifdef WIN32
#define STORAGE_CLASS_INFO __declspec(dllexport)
#else
#define STORAGE_CLASS_INFO 
#endif

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
