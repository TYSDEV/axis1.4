/*
 * Copyright 2001-2004 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


#include <wss4c/wssenc.hpp>
#include "WssString.hpp"

const XMLCh* WssString::toXmlChArray()
{
	if (pxData) // if non-null release this buffer first.
		XMLString::release(&pxData);

	pxData = XMLString::transcode(data.c_str());
	return pxData;
}

const char* WssString::toCharArray() const
{
	return data.c_str();
}

string WssString::toStdString() const
{ 
	return data;
}

bool WssString::isEmpty()
{
	if (data.length() == 0)
		return true;
	else
		return false;
}

WssString& WssString::operator= (const XMLCh* xcstr)
{
	setData(xcstr);
	return *this;
}

WssString& WssString::operator= (const char* cstr)
{
	data = cstr;
	return *this;
}

WssString& WssString::operator= (const string& str)
{
	data = str;
	return *this;
}

WssString& WssString::operator= (const WssString& s)
{
	data = s.toStdString();
	return *this;
}

void WssString::setData(const XMLCh* str)
{
	if ( str ) // Non null string
	{
		char* chstr = XMLString::transcode(str);
		if (chstr)
			data = chstr;
		XMLString::release(&chstr);
	}
	else
		data = "";
}

/************************************************************************/
/* Construction and Destruction											*/
/************************************************************************/
WssString::WssString()
{
	data = "";
	pxData = 0;
}

WssString::WssString(const XMLCh* xcstr)
{
	setData(xcstr);
}

WssString::WssString(const char* str)
{
	pxData = 0;

	if (str) // Non null string
		data = str;
	else
		data = "";
}

WssString::WssString(const string& str)
{
	data = str;
	pxData = 0;
}

WssString::~WssString()
{
	if (pxData)
		XMLString::release(&pxData);
}
