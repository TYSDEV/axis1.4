/*
 *   Copyright 2003-2004 The Apache Software Foundation.
// (c) Copyright IBM Corp. 2004, 2005 All Rights Reserved
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

#include <axis/client/Call.hpp>
#include <axis/AxisException.hpp>

#include "../AxisObjectContainer.hpp"

#include <axis/Axis.h>
#include <axis/GDefine.h>
#include <axis/AxisUserAPI.h>
#include <axis/AxisUserAPIArrays.h>
#include <axis/SoapEnvVersions.h>
#include <axis/TypeMapping.h>
#include <axis/WSDDDefines.h>
#include <axis/client/Call.h>

AXIS_CPP_NAMESPACE_USE

extern "C" {

AXISC_STORAGE_CLASS_INFO 
AXISCHANDLE axiscCallCreate() 
{
    try
    {
        Call *c = new Call();
        AxisObjectContainer *h = new AxisObjectContainer(c);
        return (AXISCHANDLE)h;
    }
    catch ( AxisException& e  )
    {
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }

    return (AXISCHANDLE)NULL;
}

AXISC_STORAGE_CLASS_INFO 
void axiscCallDestroy(AXISCHANDLE call) 
{
    if ((AXISCHANDLE)NULL == call)
        return;

    AxisObjectContainer *h = (AxisObjectContainer *)call;
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        delete c;
        delete h;
    }
    catch ( AxisException& e  )
    {
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }
}

AXISC_STORAGE_CLASS_INFO 
void axiscCallSetSOAPVersion(AXISCHANDLE call, 
                             AXISC_SOAP_VERSION version) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        c->setSOAPVersion((SOAP_VERSION)version);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }
}

AXISC_STORAGE_CLASS_INFO 
int axiscCallSetTransportProperty(AXISCHANDLE call, 
                                  AXISC_TRANSPORT_INFORMATION_TYPE type, 
                                  const char * value) 
{   
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return c->setTransportProperty((AXIS_TRANSPORT_INFORMATION_TYPE)type, value);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }
    
    return -1;
}

AXISC_STORAGE_CLASS_INFO 
const char * axiscCallGetTransportProperty(AXISCHANDLE call, 
                                           const char *key, 
                                           AxiscBool response) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return c->getTransportProperty(key, (bool)(response !=0 ));
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }
    
    return (char *)NULL;
}

AXISC_STORAGE_CLASS_INFO 
int axiscCallSetHandlerProperty(AXISCHANDLE call, 
                                AxiscChar * name, 
                                void * value, 
                                int len) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return c->setHandlerProperty(name,value,len);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }
    
    return -1;
}

AXISC_STORAGE_CLASS_INFO 
int axiscCallSetProtocol(AXISCHANDLE call, 
                         AXISC_PROTOCOL_TYPE protocol) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return c->setProtocol((AXIS_PROTOCOL_TYPE)protocol);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }

    return -1;
}

AXISC_STORAGE_CLASS_INFO 
AXISC_PROTOCOL_TYPE axiscCallGetProtocol(AXISCHANDLE call) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return (AXISC_PROTOCOL_TYPE)(c->getProtocol());
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }
    
    return (AXISC_PROTOCOL_TYPE)-1;
}

AXISC_STORAGE_CLASS_INFO 
int axiscCallUnInitialize(AXISCHANDLE call) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return c->unInitialize();
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }
    
    return -1;
}

AXISC_STORAGE_CLASS_INFO 
int axiscCallInitialize(AXISCHANDLE call, 
                        AXISC_PROVIDERTYPE nStyle) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return c->initialize((PROVIDERTYPE)nStyle);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }
    
    return -1;
}

AXISC_STORAGE_CLASS_INFO 
int axiscCallInvoke(AXISCHANDLE call) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return c->invoke();
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }
    
    return -1;
}

AXISC_STORAGE_CLASS_INFO 
void axiscCallAddCmplxParameter(AXISCHANDLE call, 
                                void * pObject, 
                                void * pSZFunct, 
                                void * pDelFunct, 
                                const AxiscChar * pName, 
                                const AxiscChar * pNamespace) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        c->addCmplxParameter(pObject,pSZFunct,pDelFunct,pName,pNamespace);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }
}

AXISC_STORAGE_CLASS_INFO 
void axiscCallAddCmplxArrayParameter(AXISCHANDLE call, 
                                     Axisc_Array * pArray,
                                     void * pSZFunct, 
                                     void * pDelFunct, 
                                     void * pSizeFunct, 
                                     const AxiscChar * pName, 
                                     const AxiscChar * pNamespace) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        c->addCmplxArrayParameter((Axis_Array*)pArray,pSZFunct,pDelFunct,pSizeFunct,pName,pNamespace);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }
}

AXISC_STORAGE_CLASS_INFO 
void axiscCallAddBasicArrayParameter(AXISCHANDLE call, 
                                     Axisc_Array * pArray, 
                                     AXISC_XSDTYPE nType, 
                                     const AxiscChar * pName) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        c->addBasicArrayParameter((Axis_Array*)pArray, (XSDTYPE)nType, pName);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }
}

AXISC_STORAGE_CLASS_INFO 
void axiscCallAddParameter(AXISCHANDLE call, 
                           void * pValue, 
                           const char * pchName, 
                           AXISC_XSDTYPE nType) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        c->addParameter(pValue,pchName,(XSDTYPE)nType);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }
}

AXISC_STORAGE_CLASS_INFO 
void axiscCallSetOperation(AXISCHANDLE call, 
                           const char * pchOperation, 
                           const char * pchNamespace) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        c->setOperation(pchOperation,pchNamespace);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }
}

AXISC_STORAGE_CLASS_INFO 
int axiscCallSetEndpointURI(AXISCHANDLE call, 
                            const char * pchEndpointURI) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return c->setEndpointURI(pchEndpointURI);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }
    
    return -1;
}

AXISC_STORAGE_CLASS_INFO 
AXISCHANDLE axiscCallCreateHeaderBlock(AXISCHANDLE call, 
                                       AxiscChar * pachLocalName, 
                                       AxiscChar * pachUri, 
                                       AxiscChar * pachPrefix) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return (AXISCHANDLE)(c->createHeaderBlock(pachLocalName, pachUri, pachPrefix));
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }
    
    return (AXISCHANDLE)NULL;
}

AXISC_STORAGE_CLASS_INFO 
xsdc__int * axiscCallGetElementAsInt(AXISCHANDLE call, 
                                     const AxiscChar * pName, 
                                     const AxiscChar * pNamespace) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return c->getElementAsInt(pName,pNamespace);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }
    
    return (xsdc__int *)NULL;
}

/*
AXISC_STORAGE_CLASS_INFO 
int axiscCallGetFaultDetail(AXISCHANDLE call, 
                            char * * ppcDetail) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return c->getFaultDetail(ppcDetail);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }
   
    return -1;
}
*/

AXISC_STORAGE_CLASS_INFO 
xsdc__boolean * axiscCallGetElementAsBoolean(AXISCHANDLE call, 
                                             const AxiscChar * pName, 
                                             const AxiscChar * pNamespace) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return (xsdc__boolean* )(c->getElementAsBoolean(pName,pNamespace));
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }

    return (xsdc__boolean *)NULL;
}

AXISC_STORAGE_CLASS_INFO 
xsdc__unsignedInt * axiscCallGetElementAsUnsignedInt(AXISCHANDLE call, 
                                                     const AxiscChar * pName, 
                                                     const AxiscChar * pNamespace) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return c->getElementAsUnsignedInt(pName,pNamespace);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }

    return (xsdc__unsignedInt *)NULL;
}

AXISC_STORAGE_CLASS_INFO 
xsdc__short * axiscCallGetElementAsShort(AXISCHANDLE call, 
                                         const AxiscChar * pName, 
                                         const AxiscChar * pNamespace) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return c->getElementAsShort(pName,pNamespace);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }

    return (xsdc__short *)NULL;
}

AXISC_STORAGE_CLASS_INFO 
xsdc__unsignedShort * axiscCallGetElementAsUnsignedShort(AXISCHANDLE call, 
                                                         const AxiscChar * pName, 
                                                         const AxiscChar * pNamespace) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return c->getElementAsUnsignedShort(pName,pNamespace);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }

    return (xsdc__unsignedShort *)NULL;
}

AXISC_STORAGE_CLASS_INFO 
xsdc__byte * axiscCallGetElementAsByte(AXISCHANDLE call, 
                                       const AxiscChar * pName, 
                                       const AxiscChar * pNamespace) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return c->getElementAsByte(pName,pNamespace);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }

    return (xsdc__byte *)NULL;
}

AXISC_STORAGE_CLASS_INFO 
xsdc__unsignedByte * axiscCallGetElementAsUnsignedByte(AXISCHANDLE call, 
                                                       const AxiscChar * pName, 
                                                       const AxiscChar * pNamespace) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return c->getElementAsUnsignedByte(pName,pNamespace);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }
    
    return (xsdc__unsignedByte *)NULL;
}

AXISC_STORAGE_CLASS_INFO 
xsdc__long * axiscCallGetElementAsLong(AXISCHANDLE call, 
                                       const AxiscChar * pName, 
                                       const AxiscChar * pNamespace) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return (xsd__long*)(c->getElementAsLong(pName,pNamespace));
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }

    return (xsdc__long *)NULL;
}

AXISC_STORAGE_CLASS_INFO 
xsdc__integer * axiscCallGetElementAsInteger(AXISCHANDLE call, 
                                             const AxiscChar * pName, 
                                             const AxiscChar * pNamespace) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return c->getElementAsInteger(pName,pNamespace);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }

    return (xsdc__integer *)NULL;
}

AXISC_STORAGE_CLASS_INFO 
xsdc__unsignedLong * axiscCallGetElementAsUnsignedLong(AXISCHANDLE call, 
                                                       const AxiscChar * pName, 
                                                       const AxiscChar * pNamespace) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return c->getElementAsUnsignedLong(pName,pNamespace);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }

    return (xsdc__unsignedLong *)NULL;
}

AXISC_STORAGE_CLASS_INFO 
xsdc__float * axiscCallGetElementAsFloat(AXISCHANDLE call, 
                                         const AxiscChar * pName, 
                                         const AxiscChar * pNamespace) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return c->getElementAsFloat(pName,pNamespace);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }

    return (xsdc__float *)NULL;
}

AXISC_STORAGE_CLASS_INFO 
xsdc__double * axiscCallGetElementAsDouble(AXISCHANDLE call, 
                                           const AxiscChar * pName, 
                                           const AxiscChar * pNamespace) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return c->getElementAsDouble(pName,pNamespace);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }

    return (xsdc__double *)NULL;
}

AXISC_STORAGE_CLASS_INFO 
xsdc__decimal * axiscCallGetElementAsDecimal(AXISCHANDLE call, 
                                             const AxiscChar * pName, 
                                             const AxiscChar * pNamespace) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return c->getElementAsDecimal(pName,pNamespace);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }

    return (xsdc__decimal *)NULL;
}

AXISC_STORAGE_CLASS_INFO 
xsdc__string axiscCallGetElementAsString(AXISCHANDLE call, 
                                         const AxiscChar * pName, 
                                         const AxiscChar * pNamespace)
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return c->getElementAsString(pName,pNamespace);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }

    return (xsdc__string)NULL;
}

AXISC_STORAGE_CLASS_INFO 
xsdc__anyURI axiscCallGetElementAsAnyURI(AXISCHANDLE call, 
                                         const AxiscChar * pName, 
                                         const AxiscChar * pNamespace) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return c->getElementAsAnyURI(pName,pNamespace);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }
    
    return (xsdc__anyURI)NULL;
}

AXISC_STORAGE_CLASS_INFO 
xsdc__QName axiscCallGetElementAsQName(AXISCHANDLE call, 
                                       const AxiscChar * pName, 
                                       const AxiscChar * pNamespace) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return c->getElementAsQName(pName,pNamespace);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }

    return (xsdc__QName)NULL;
}

AXISC_STORAGE_CLASS_INFO 
xsdc__hexBinary * axiscCallGetElementAsHexBinary(AXISCHANDLE call, 
                                                 const AxiscChar * pName, 
                                                 const AxiscChar * pNamespace) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        //TODO: Unimplemented
        xsdc__hexBinary * hb = new xsdc__hexBinary();
        memset(hb,0,sizeof(hb));
        return hb;
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }

    return (xsdc__hexBinary *)NULL;
}

AXISC_STORAGE_CLASS_INFO 
xsdc__base64Binary * axiscCallGetElementAsBase64Binary(AXISCHANDLE call, 
                                                       const AxiscChar * pName, 
                                                       const AxiscChar * pNamespace) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        //TODO: Unimplemented
        xsdc__base64Binary * bb = new xsdc__base64Binary();
        memset(bb,0,sizeof(bb));
        return bb;
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }

    return (xsdc__base64Binary *)NULL;
}

AXISC_STORAGE_CLASS_INFO 
xsdc__dateTime * axiscCallGetElementAsDateTime(AXISCHANDLE call, 
                                               const AxiscChar * pName, 
                                               const AxiscChar * pNamespace) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return c->getElementAsDateTime(pName,pNamespace);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }

    return (xsdc__dateTime *)NULL;
}

AXISC_STORAGE_CLASS_INFO 
xsdc__date * axiscCallGetElementAsDate(AXISCHANDLE call, 
                                       const AxiscChar * pName, 
                                       const AxiscChar * pNamespace) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return c->getElementAsDate(pName,pNamespace);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }

    return (xsdc__date *)NULL;
}

AXISC_STORAGE_CLASS_INFO 
xsdc__time * axiscCallGetElementAsTime(AXISCHANDLE call, 
                                       const AxiscChar * pName, 
                                       const AxiscChar * pNamespace) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return c->getElementAsTime(pName,pNamespace);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }

    return (xsdc__time *)NULL;
}

AXISC_STORAGE_CLASS_INFO 
xsdc__duration * axiscCallGetElementAsDuration(AXISCHANDLE call, 
                                               const AxiscChar * pName, 
                                               const AxiscChar * pNamespace) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return c->getElementAsDuration(pName,pNamespace);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }

    return (xsdc__duration *)NULL;
}

AXISC_STORAGE_CLASS_INFO 
xsdc__int * axiscCallGetAttributeAsInt(AXISCHANDLE call, 
                                       const AxiscChar * pName,
                                       const AxiscChar * pNamespace) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return c->getAttributeAsInt(pName,pNamespace);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }

    return (xsdc__int *)NULL;
}

AXISC_STORAGE_CLASS_INFO 
xsdc__boolean * axiscCallGetAttributeAsBoolean(AXISCHANDLE call, 
                                               const AxiscChar * pName, const AxiscChar * pNamespace) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return (xsdc__boolean* )(c->getAttributeAsBoolean(pName,pNamespace));
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }

    return (xsdc__boolean *)NULL;
}

AXISC_STORAGE_CLASS_INFO 
xsdc__unsignedInt * axiscCallGetAttributeAsUnsignedInt(AXISCHANDLE call, 
                                                       const AxiscChar * pName, 
                                                       const AxiscChar * pNamespace) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return c->getAttributeAsUnsignedInt(pName,pNamespace);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }

    return (xsdc__unsignedInt *)NULL;
}

AXISC_STORAGE_CLASS_INFO 
xsdc__short * axiscCallGetAttributeAsShort(AXISCHANDLE call, 
                                           const AxiscChar * pName, 
                                           const AxiscChar * pNamespace) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return c->getAttributeAsShort(pName,pNamespace);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }

    return (xsdc__short *)NULL;
}

AXISC_STORAGE_CLASS_INFO 
xsdc__unsignedShort * axiscCallGetAttributeAsUnsignedShort(AXISCHANDLE call, 
                                                           const AxiscChar * pName, 
                                                           const AxiscChar * pNamespace) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return c->getAttributeAsUnsignedShort(pName,pNamespace);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }

    return (xsdc__unsignedShort *)NULL;
}

AXISC_STORAGE_CLASS_INFO 
xsdc__byte * axiscCallGetAttributeAsByte(AXISCHANDLE call, 
                                         const AxiscChar * pName, 
                                         const AxiscChar * pNamespace) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return c->getAttributeAsByte(pName,pNamespace);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }

    return (xsdc__byte *)NULL;
}

AXISC_STORAGE_CLASS_INFO 
xsdc__unsignedByte * axiscCallGetAttributeAsUnsignedByte(AXISCHANDLE call, 
                                                         const AxiscChar * pName, 
                                                         const AxiscChar * pNamespace) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return c->getAttributeAsUnsignedByte(pName,pNamespace);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }

    return (xsdc__unsignedByte *)NULL;
}

AXISC_STORAGE_CLASS_INFO 
xsdc__long * axiscCallGetAttributeAsLong(AXISCHANDLE call, 
                                         const AxiscChar * pName, 
                                         const AxiscChar * pNamespace) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return c->getAttributeAsLong(pName,pNamespace);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }

    return (xsdc__long *)NULL;
}

AXISC_STORAGE_CLASS_INFO 
xsdc__integer * axiscCallGetAttributeAsInteger(AXISCHANDLE call, 
                                               const AxiscChar * pName, 
                                               const AxiscChar * pNamespace) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return c->getAttributeAsInteger(pName,pNamespace);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }

    return (xsdc__integer *)NULL;
}

AXISC_STORAGE_CLASS_INFO 
xsdc__unsignedLong * axiscCallGetAttributeAsUnsignedLong(AXISCHANDLE call, 
                                                         const AxiscChar * pName, 
                                                         const AxiscChar * pNamespace) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return c->getAttributeAsUnsignedLong(pName,pNamespace);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }

    return (xsdc__unsignedLong *)NULL;
}

AXISC_STORAGE_CLASS_INFO 
xsdc__float * axiscCallGetAttributeAsFloat(AXISCHANDLE call, 
                                           const AxiscChar * pName, 
                                           const AxiscChar * pNamespace) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return c->getAttributeAsFloat(pName,pNamespace);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }

    return (xsdc__float *)NULL;
}

AXISC_STORAGE_CLASS_INFO 
xsdc__double * axiscCallGetAttributeAsDouble(AXISCHANDLE call, 
                                             const AxiscChar * pName, 
                                             const AxiscChar * pNamespace) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return c->getAttributeAsDouble(pName,pNamespace);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }

    return (xsdc__double *)NULL;
}

AXISC_STORAGE_CLASS_INFO 
xsdc__decimal * axiscCallGetAttributeAsDecimal(AXISCHANDLE call, 
                                               const AxiscChar * pName, 
                                               const AxiscChar * pNamespace) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return c->getAttributeAsDecimal(pName,pNamespace);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }

    return (xsdc__decimal *)NULL;
}

AXISC_STORAGE_CLASS_INFO 
xsdc__string axiscCallGetAttributeAsString(AXISCHANDLE call, 
                                           const AxiscChar * pName, 
                                           const AxiscChar * pNamespace) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return c->getAttributeAsString(pName,pNamespace);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }

    return (xsdc__string)NULL;
}

AXISC_STORAGE_CLASS_INFO 
xsdc__anyURI axiscCallGetAttributeAsAnyURI(AXISCHANDLE call, 
                                           const AxiscChar * pName, 
                                           const AxiscChar * pNamespace) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return c->getAttributeAsAnyURI(pName,pNamespace);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }

    return (xsdc__anyURI)NULL;
}

AXISC_STORAGE_CLASS_INFO 
xsdc__QName axiscCallGetAttributeAsQName(AXISCHANDLE call, 
                                         const AxiscChar * pName, 
                                         const AxiscChar * pNamespace) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return c->getAttributeAsQName(pName,pNamespace);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }

    return (xsdc__QName)NULL;
}

AXISC_STORAGE_CLASS_INFO 
xsdc__hexBinary * axiscCallGetAttributeAsHexBinary(AXISCHANDLE call, 
                                                   const AxiscChar * pName, 
                                                   const AxiscChar * pNamespace) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        //TODO: Unimplemented
        xsdc__hexBinary * hb = new xsdc__hexBinary();
        memset(hb,0,sizeof(hb));
        return hb;
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }

    return (xsdc__hexBinary *)NULL;
}

AXISC_STORAGE_CLASS_INFO 
xsdc__base64Binary * axiscCallGetAttributeAsBase64Binary(AXISCHANDLE call, 
                                                         const AxiscChar * pName, 
                                                         const AxiscChar * pNamespace) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        //TODO: Unimplemented
        xsdc__base64Binary *bb = new xsdc__base64Binary();
        memset(bb,0,sizeof(bb));
        return bb;
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }

    return (xsdc__base64Binary *)NULL;
}

AXISC_STORAGE_CLASS_INFO 
xsdc__dateTime * axiscCallGetAttributeAsDateTime(AXISCHANDLE call, 
                                                 const AxiscChar * pName, 
                                                 const AxiscChar * pNamespace) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return c->getAttributeAsDateTime(pName,pNamespace);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }

    return (xsdc__dateTime *)NULL;
}

AXISC_STORAGE_CLASS_INFO 
xsdc__date * axiscCallGetAttributeAsDate(AXISCHANDLE call, 
                                         const AxiscChar * pName, 
                                         const AxiscChar * pNamespace) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return c->getAttributeAsDate(pName,pNamespace);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }

    return (xsdc__date *)NULL;
}

AXISC_STORAGE_CLASS_INFO 
xsdc__time * axiscCallGetAttributeAsTime(AXISCHANDLE call, 
                                         const AxiscChar * pName, 
                                         const AxiscChar * pNamespace) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return c->getAttributeAsTime(pName,pNamespace);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }
    return (xsdc__time *)NULL;
}

AXISC_STORAGE_CLASS_INFO 
xsdc__duration * axiscCallGetAttributeAsDuration(AXISCHANDLE call, 
                                                 const AxiscChar * pName, 
                                                 const AxiscChar * pNamespace) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return c->getAttributeAsDuration(pName,pNamespace);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }
    
    return (xsdc__duration *)NULL;
}

AXISC_STORAGE_CLASS_INFO 
void * axiscCallGetCmplxObject(AXISCHANDLE call, 
                               void * pDZFunct, 
                               void * pCreFunct, 
                               void * pDelFunct, 
                               const AxiscChar * pName, 
                               const AxiscChar * pNamespace) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return c->getCmplxObject(pDZFunct,pCreFunct,pDelFunct,pName,pNamespace);    
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }
    
    return NULL;
}

AXISC_STORAGE_CLASS_INFO 
Axisc_Array* axiscCallGetCmplxArray(AXISCHANDLE call, 
                                    Axisc_Array* pArray, 
                                    void * pDZFunct, 
                                    void * pCreFunct, 
                                    void * pDelFunct, 
                                    void * pSizeFunct, 
                                    const AxiscChar * pName, 
                                    const AxiscChar * pNamespace) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
    	Axis_Array ObjArray;
    	Axis_Array *pObjArray = NULL;
    	
    	if (pArray)
    	{
    		ObjArray.set(pArray->m_Array, pArray->m_Size, (XSDTYPE)pArray->m_Type);
    		pObjArray = &ObjArray;
        }
        
    	c->getCmplxArray(&ObjArray, pDZFunct, pCreFunct,pDelFunct,pSizeFunct, pName, pNamespace); 
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }
    
    return (Axisc_Array *)NULL;
}

AXISC_STORAGE_CLASS_INFO 
Axisc_Array* axiscCallGetBasicArray(AXISCHANDLE call, 
                                    AXISC_XSDTYPE nType, 
                                    const AxiscChar * pName, 
                                    const AxiscChar * pNamespace) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        //TODO: Unimplemented
        Axisc_Array* aa = (Axisc_Array *)NULL;
        return aa;
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }
    
    return (Axisc_Array *)NULL;
}

AXISC_STORAGE_CLASS_INFO 
int axiscCallCheckMessage(AXISCHANDLE call, 
                          const AxiscChar * pName, 
                          const AxiscChar * pNamespace) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return c->checkMessage(pName,pNamespace);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }
    
    return -1;
}

AXISC_STORAGE_CLASS_INFO 
void * axiscCallCheckFault(AXISCHANDLE call, 
                           const AxiscChar * pName, 
                           const AxiscChar * pNamespace) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return c->checkFault(pName,pNamespace);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }
    
    return NULL;
}

AXISC_STORAGE_CLASS_INFO 
int axiscCallGetStatus(AXISCHANDLE call) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return c->getStatus();
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }
    
    return -1;
}


AXISC_STORAGE_CLASS_INFO 
void axiscCallSetProxy(AXISCHANDLE call, 
                       const char * pcProxyHost, 
                       unsigned int uiProxyPort) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        c->setProxy(pcProxyHost,uiProxyPort);    
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }
}

AXISC_STORAGE_CLASS_INFO 
AxiscAnyType * axiscCallGetAnyObject(AXISCHANDLE call) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return (AxiscAnyType*)(c->getAnyObject());
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }
    
    return (AxiscAnyType *)NULL;
}

AXISC_STORAGE_CLASS_INFO 
int axiscCallAddAnyObject(AXISCHANDLE call, 
                          AxiscAnyType * pAnyObject) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return c->addAnyObject((AnyType*)pAnyObject);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }
    
    return -1;
}

AXISC_STORAGE_CLASS_INFO 
const AxiscChar * axiscCallGetNamespacePrefix(AXISCHANDLE call, 
                                              const AxiscChar * pNamespace) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        return c->getNamespacePrefix(pNamespace);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }
    
    return (AxiscChar *)NULL;
}

AXISC_STORAGE_CLASS_INFO 
void axiscCallSetSOAPMethodAttribute(AXISCHANDLE call, 
                                 const AxiscChar * pLocalname, 
                                 const AxiscChar * pPrefix, 
                                 const AxiscChar * pUri, 
                                 const AxiscChar * pValue) 
{
    AxisObjectContainer *h = (AxisObjectContainer *)call;
    h->_exception.resetException();
    Call *c = (Call*)h->_objHandle;
    
    try
    {
        c->setSOAPMethodAttribute(pLocalname,pPrefix,pUri,pValue);
    }
    catch ( AxisException& e  )
    {
        h->_exception.setExceptionFromException(e);
        axiscAxisInvokeExceptionHandler(e.getExceptionCode(), e.what());
    }
    catch ( ... )
    {
        h->_exception.setExceptionCode(-1);  
        h->_exception.setMessage("Unrecognized exception thrown.");  
        axiscAxisInvokeExceptionHandler(-1, "Unrecognized exception thrown.");
    }
}

}
