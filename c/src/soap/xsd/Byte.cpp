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

#include "Byte.hpp"

AXIS_CPP_NAMESPACE_START

Byte::Byte():m_Byte(NULL)
{
}

Byte::~Byte()
{
}

AxisChar* Byte::serialize(const void* value) throw (AxisSoapException)
{
    return serialize((xsd__byte*) value);
}

void* Byte::deserializer(const AxisChar* valueAsChar) throw (AxisSoapException)
{
    return (void*) deserializeByte(valueAsChar);
}

AxisChar* Byte::serialize(const char* value) throw (AxisSoapException)
{
    xsd__short valueAsShort = static_cast<xsd__short>(*value);
    return Short::serialize(&valueAsShort);
}

char* Byte::deserializeByte(const AxisChar* valueAsChar) throw (AxisSoapException)
{
    xsd__short* returnValue = Short::deserializeShort(valueAsChar);
 
    if(m_Byte)
    {
        delete m_Byte;
        m_Byte = NULL;
    }
    m_Byte = new xsd__byte;
    *m_Byte = static_cast<xsd__byte> (*returnValue);
    delete returnValue; // Samissa - need to clean this memory
    return m_Byte;
}

MinInclusive* Byte::getMinInclusive()
{
    return new MinInclusive(static_cast<LONGLONG>(-128));
}

MinExclusive* Byte::getMinExclusive()
{
    return new MinExclusive(static_cast<LONGLONG>(-129));
}

MaxInclusive* Byte::getMaxInclusive()
{
    return new MaxInclusive(static_cast<LONGLONG>(127));
}

MaxExclusive* Byte::getMaxExclusive()
{
    return new MaxExclusive(static_cast<LONGLONG>(128));
}

AXIS_CPP_NAMESPACE_END
