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
 * @author Roshan Weerasuriya (roshan@jkcs.slt.lk, roshan@opensource.lk)
 *
 */
// AxisUtils.h: interface for the AxisUtils class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_AXISUTILS_H__B5175A8C_0210_417D_BA43_6AAAF7E03551__INCLUDED_)
#define AFX_AXISUTILS_H__B5175A8C_0210_417D_BA43_6AAAF7E03551__INCLUDED_

#include "GDefine.h"

#include <string>
using namespace std;

#define CONVERT_BUFFER_SIZE 1024

class AxisUtils  
{
	friend class TypeMapping;
	friend class URIMapping;
public:
	/**
	 * Converts the given character pointer value to lowercase.
	 * @param pchWord The character pointer to be converted.
	 * @return The converted lowercase character value
	 */
	static char* toLowerCase(const char* pchWord);
	/**
	 * Converts the given string to lowercase.
	 * @param pchWord The string to be converted.
	 * @return The converted lowercase string value
	 */
	static string toLowerCase(const string sWord);
	/**
	 * Converts the given character pointer value to uppercase.
	 * @param pchWord The character pointer to be converted.
	 * @return The converted uppercase character value
	 */
	static char* toUpperCase(const char* pchWord);
	/**
	 * Converts the given string to uppercase.
	 * @param pchWord The string to be converted.
	 * @return The converted uppercase string value
	 */
	static string toUpperCase(const string sWord);
	/**
	 * Searchs for the specified character in the given character array and returns whether it is found or not.
	 * @param pchStringToSearch The character array to be searched.
	 * @param cCharacter The character to search.
	 */
	static bool isCharacterAvailable(const char *pchStringToSearch, const char cCharacter);
	/**
	 * Searchs for the specified character in the given string and returns whether it is found or not.
	 * @param sString The string array to be searched.
	 * @param cCharacter The character to search.
	 */
	static bool isCharacterAvailable(const string &sString, const char cCharacter);
	/**
	 * Clears the content of passed character array
	 * @param arrCh The character array which is to be cleared.
	 * @param iSize The size of the array which is to be cleared.
	 */
	static int clearArray(char* arrCh, int iSize);
	static void Initialize();
	static const AxisXMLCh* ToAxisXMLCh(const AxisChar* pch);
	AxisUtils();
	virtual ~AxisUtils();
	// any usefull static const AxisXMLCh strings. 
    
private:
	static const AxisXMLCh* Convert(const AxisChar* pch);
	static AxisXMLCh m_Buffer[CONVERT_BUFFER_SIZE];   
    
};

#endif // !defined(AFX_AXISUTILS_H__B5175A8C_0210_417D_BA43_6AAAF7E03551__INCLUDED_)
