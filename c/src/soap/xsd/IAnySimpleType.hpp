/*
 *   Copyright 2003-2004 The Apache Software Foundation.
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
 
 /*
  * @file IAnySimpleType.hpp
  */
  
#if !defined(_IANYSIMPLETYPE_HPP____OF_AXIS_INCLUDED_)
#define _IANYSIMPLETYPE_HPP____OF_AXIS_INCLUDED_

#include <axis/GDefine.hpp>
#include <string>
#include "../AxisSoapException.h"

AXIS_CPP_NAMESPACE_START

const AxisChar XML_ENTITY_REFERENCE_CHARS[]    = "<>&\"\'";
/* Entity reference characters */
const AxisChar ENCODED_LESSER_STR[]            = "&lt;";    
/* Encoded string for less than character */
const AxisChar ENCODED_GREATER_STR[]        = "&gt;";    
/* Encoded string for greator than character */
const AxisChar ENCODED_AMPERSAND_STR[]        = "&amp;";    
/* Encoded string for ampersand character */
const AxisChar ENCODED_DBL_QUOTE_STR[]        = "&quot;";    
/* Encoded string for single quote character */
const AxisChar ENCODED_SGL_QUOTE_STR[]        = "&apos;";    
/* Encoded string for double quote character */


/**
 *   @class IAnySimpleType
 *   @brief Interface for all XSD built-in simple type.
 *
 *   @author Adrian Dick (adrian.dick@uk.ibm.com)
 *
 */
class IAnySimpleType {
public:

	virtual ~IAnySimpleType() {};
	/**
	 * Serialize value to it's on-the-wire string form.
	 * @param value The value to be serialized.
	 * @return Serialized form of value.
	 */
    virtual AxisChar* serialize(const void* value) throw (AxisSoapException) = 0;
	
	/**
	 * Deserialize value from it's on-the-wire string form.
	 * @param valueAsChar Serialized form of value.
	 * @return Deserialized value.
	 */
    virtual void* deserialize(const AxisChar* valueAsChar) throw (AxisSoapException) = 0;
    
protected:
    const AxisString& replaceReservedCharacters(AxisString& value);
    enum
    {
        GREATER_THAN_CHAR    =    L'>',    /* Greater than character */
        LESSER_THAN_CHAR    =    L'<',    /* Less than character */
        SINGLE_QUOTE_CHAR    =    L'\'',    /* Single quotation character */
        DOUBLE_QUOTE_CHAR    =    L'\"',    /* Double quotation character */
        AMPERSAND_CHAR        =    L'&'    /* Ampersand character */
    };
    
private:
    AxisString m_strReturnVal;
};

AXIS_CPP_NAMESPACE_END

#endif
