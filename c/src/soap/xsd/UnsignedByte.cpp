// Copyright 2003-2004 The Apache Software Foundation.
// (c) Copyright IBM Corp. 2004, 2005 All Rights Reserved
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

#include "UnsignedByte.hpp"

AXIS_CPP_NAMESPACE_START

UnsignedByte::UnsignedByte():m_UnsignedByte(NULL)
{
}

UnsignedByte::~UnsignedByte()
{
}

AxisChar* UnsignedByte::serialize(const void* value) throw (AxisSoapException)
{
    return serialize((xsd__unsignedByte*) value);
}

void* UnsignedByte::deserialize(const AxisChar* valueAsChar) throw (AxisSoapException)
{
    return (void*) deserializeUnsignedByte(valueAsChar);
}

AxisChar* UnsignedByte::serialize(const xsd__unsignedByte* value) throw (AxisSoapException)
{
    xsd__unsignedShort valueAsShort = static_cast<xsd__unsignedShort>(*value);
    return UnsignedShort::serialize(&valueAsShort);
}

xsd__unsignedByte* UnsignedByte::deserializeUnsignedByte(const AxisChar* valueAsChar) throw (AxisSoapException)
{
    xsd__unsignedShort* returnValue = UnsignedShort::deserializeUnsignedShort(valueAsChar);
 
    if(m_UnsignedByte)
    {
        delete m_UnsignedByte;
        m_UnsignedByte = NULL;
    }
    m_UnsignedByte = new xsd__unsignedByte;
    *m_UnsignedByte = static_cast<xsd__unsignedByte> (*returnValue);
    return m_UnsignedByte;
}

MaxInclusive* UnsignedByte::getMaxInclusive()
{
   AxisChar* end;    
   LONGLONG* maxInclusive = new LONGLONG;
   *maxInclusive = strtol ("255", &end, 10);
    
    MaxInclusive* retVal = new MaxInclusive(*maxInclusive);
    delete maxInclusive;
    return retVal;
}

AXIS_CPP_NAMESPACE_END
