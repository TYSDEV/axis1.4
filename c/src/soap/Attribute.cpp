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
 * @author Roshan Weerasuriya (roshan@jkcs.slt.lk)
 *
 */

// Attribute.cpp: implementation of the Attribute class.
//
//////////////////////////////////////////////////////////////////////

#include "Attribute.h"
#include "../common/GDefine.h"
#include <iostream>

//////////////////////////////////////////////////////////////////////
// Construction/Destruction
//////////////////////////////////////////////////////////////////////

Attribute::Attribute()
{
	
}

Attribute::~Attribute()
{

}

void Attribute::setLocalName(const string &localname)
{
	m_localname= localname;
}

void Attribute::setPrefix(const string &prefix)
{
	m_prefix= prefix;
}

void Attribute::setUri(const string &uri)
{
	m_uri= uri;
}

void Attribute::setValue(const string &value)
{
	m_value= value;
}

Attribute::Attribute(const string &localname, const string &prefix, const string &uri, const string &value)
{
	m_localname= localname;
	m_prefix= prefix;
	m_uri= uri;
	m_value= value;
}

/*
 *
 */
int Attribute::serialize(string& sSerialized)
{	
	int intStatus= FAIL;

	if (isSerializable()) {
		sSerialized+= " ";

		if(!m_prefix.empty()) {
			sSerialized= sSerialized+ m_prefix+ ":";
		}

		sSerialized= sSerialized + m_localname +
			"=\""+ m_value+ "\"";

		intStatus= SUCCESS;
	}

	return intStatus;	
}

/*string& Attribute::serialize()
{	
	m_strAttrSerialized="";

	if (isSerializable()) {
		if(!m_prefix.empty()) {
			m_strAttrSerialized= m_prefix+ ":";
		}

		m_strAttrSerialized= m_strAttrSerialized + m_localname +
			"=\""+ m_value+ "\"";
	}

	return m_strAttrSerialized;	
}*/

bool Attribute::isSerializable()
{
	bool bStatus= true;

	if(m_localname.empty()) {
		bStatus= false;
	}

	return bStatus;
}
