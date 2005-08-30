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

#if !defined(AFX_WSSSTRING_H__3E3A2460_EA33_4DD8_B20C_24476752BABF__INCLUDED_)
#define AFX_WSSSTRING_H__3E3A2460_EA33_4DD8_B20C_24476752BABF__INCLUDED_

class WssString  
{
public:
	/** Default constructor. Initializes an empty string */
	WssString();
	/** Constructor. Constructs a WssString from a std::string */
	WssString(const string&);
	/** Construcotr. Constructs a WssString from a basic character array */
	WssString(const char*);
	/** Constructor. Constructs a WssString from a XMLCh array */
	WssString(const XMLCh*);
	/** Destructor */
	virtual ~WssString();

	/**
	 *	Returns a char array representaion of the string.
	 */
	const char* toCharArray(void) const;
	/**
	 *	Returns a std::string representation of the string.
	 */
	string toStdString(void) const;
	/**
	 *	Returns a xercesc::XMLCh array representing the string.
	 */
	const XMLCh* toXmlChArray(void);
	/** Returns whether the current object refers to an empty string */
	bool isEmpty(void);

	/**
	 *	Overloaded assignment operators.
	 */
	WssString& operator= (const char*);
	WssString& operator= (const XMLCh*);
	WssString& operator= (const string&);
	WssString& operator= (const WssString&);

private:
	void setData(const XMLCh*);

protected:
	string data;
	XMLCh* pxData;
};

#endif // !defined(AFX_WSSSTRING_H__3E3A2460_EA33_4DD8_B20C_24476752BABF__INCLUDED_)
