/* -*- C++ -*- */

/*
 * The Apache Software License, Version 1.1
 *
 *
 * Copyright (c) 2002 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "SOAP" and "Apache Software Foundation" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache",
 *    nor may "Apache" appear in their name, without prior written
 *    permission of the Apache Software Foundation.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 *
 *
 *
 * @author Susantha Kumara (skumara@virtusa.com)
 *
 */

// TypeMapping.cpp: implementation of the TypeMapping class.
//
//////////////////////////////////////////////////////////////////////

#include <axis/common/TypeMapping.h>
#include "AxisUtils.h"
#define __TRC(X) AxisUtils::Convert(X)

//////////////////////////////////////////////////////////////////////
// Construction/Destruction
//////////////////////////////////////////////////////////////////////
#ifndef AIX
map<const AxisXMLString, XSDTYPE> TypeMapping::m_sTypeMap;
#else
map<AxisXMLString, XSDTYPE> TypeMapping::m_sTypeMap;
#endif
volatile bool TypeMapping::m_bInit = false;

TypeMapping::TypeMapping()
{

}

TypeMapping::~TypeMapping()
{

}

void TypeMapping::Initialize()
{
	if (!m_bInit)
	{
		m_sTypeMap[__TRC("int")] = XSD_INT;
		m_sTypeMap[__TRC("boolean")] = XSD_BOOLEAN;
        m_sTypeMap[__TRC("unsignedInt")] = XSD_UNSIGNEDINT;
        m_sTypeMap[__TRC("short")] = XSD_SHORT;
        m_sTypeMap[__TRC("unsignedShort")] = XSD_UNSIGNEDSHORT;
        m_sTypeMap[__TRC("byte")] = XSD_BYTE;
        m_sTypeMap[__TRC("unsignedByte")] = XSD_UNSIGNEDBYTE;
        m_sTypeMap[__TRC("long")] = XSD_LONG;
        m_sTypeMap[__TRC("integer")] = XSD_INTEGER;
        m_sTypeMap[__TRC("unsignedLong")] = XSD_UNSIGNEDLONG;
        m_sTypeMap[__TRC("float")] = XSD_FLOAT;
        m_sTypeMap[__TRC("double")] = XSD_DOUBLE;
        m_sTypeMap[__TRC("decimal")] = XSD_DECIMAL;
		m_sTypeMap[__TRC("string")] = XSD_STRING;
		m_sTypeMap[__TRC("base64Binary")] = XSD_BASE64BINARY;
		m_sTypeMap[__TRC("hexBinary")] = XSD_HEXBINARY;
		m_sTypeMap[__TRC("anyURI")] = XSD_ANYURI;
		m_sTypeMap[__TRC("QName")] = XSD_QNAME;
        m_sTypeMap[__TRC("duration")] = XSD_DURATION;
        m_sTypeMap[__TRC("dateTime")] = XSD_DATETIME;
        m_sTypeMap[__TRC("date")] = XSD_DATE;
        m_sTypeMap[__TRC("time")] = XSD_TIME;
        m_sTypeMap[__TRC("gYearMonth")] = XSD_YEARMONTH;
        m_sTypeMap[__TRC("gYear")] = XSD_YEAR;
        m_sTypeMap[__TRC("gMonthDay")] = XSD_MONTHDAY;
        m_sTypeMap[__TRC("gDay")] = XSD_DAY;
        m_sTypeMap[__TRC("gMonth")] = XSD_MONTH;    
		m_bInit = true;
	}
}

XSDTYPE TypeMapping::Map(const AxisXMLCh *sType)
{
	if (m_sTypeMap.find(sType) != m_sTypeMap.end())
	{
		return m_sTypeMap[sType];
	}
	return XSD_UNKNOWN;
}
