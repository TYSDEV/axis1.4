#if !defined(_SOAPSTRINGLITERALS_H____OF_AXIS_INCLUDED_)
#define _SOAPSTRINGLITERALS_H____OF_AXIS_INCLUDED_

#include <axis/server/GDefine.h>

typedef struct {
    const AxisChar* pLiteral;
    int nSize;
} StringLiteral;

/* Caution : Be extremely carefull when you make any changes
 *           to structures below. The results of any careless
 *           change may be mostly crashing the software.
 */
enum {
    SL_SPACE=0,
    SL_LT,
    SL_GT,
    SL_DBLQUOTE,
    SL_SGLQUOTE,
    SL_COLON,
    SL_EQUAL,
    SL_LT_FSLASH,
    SL_FSLASH_GT,
    SL_SPACEXSITYPEEQXSD,
    SL_XSINILEQLTRUE,
    SL_ENCODED_LESSER_STR,
    SL_ENCODED_GREATOR_STR,
    SL_ENCODED_AMPERSAND_STR,
    SL_ENCODED_DBL_QUOTE_STR,
    SL_ENCODED_SGL_QUOTE_STR,
    SL_XSD_INT,
    SL_XSD_BOOLEAN,
    SL_XSD_UNSIGNEDINT,
    SL_XSD_SHORT,
    SL_XSD_UNSIGNEDSHORT,
    SL_XSD_BYTE,
    SL_XSD_UNSIGNEDBYTE,
    SL_XSD_LONG,
    SL_XSD_INTEGER,
    SL_XSD_UNSIGNEDLONG,
    SL_XSD_FLOAT,
    SL_XSD_DOUBLE,
    SL_XSD_DECIMAL,
    SL_XSD_STRING,
    SL_XSD_HEXBINARY,
    SL_XSD_BASE64BINARY,
    SL_XSD_DURATION,
    SL_XSD_DATETIME,
    SL_XSD_TIME,
    SL_XSD_DATE,
    SL_XSD_YEARMONTH,
    SL_XSD_YEAR,
    SL_XSD_MONTHDAY,
    SL_XSD_DAY,
    SL_XSD_MONTH,
    SL_XSD_ANYURI,
    SL_XSD_QNAME,
    SL_LAST
} STRINGLITERALTABLEINDEX;

const AxisChar gsc_BigStringLiteral[] = 
  " </>&\"' xsi:type=\"xsd:&lt;&gt;&amp;&quot;&apos; xsi:nil=\"true\"";
/* 012345 678901234567 890123456789012345678901234567890123456 78901 2345678901234567890 */
/* 0          1          2         3         4         5          6          7         8 */ 

const AxisChar* gsc_strlit_int = "int";
const AxisChar* gsc_strlit_boolean = "boolean";
const AxisChar* gsc_strlit_unsignedInt = "unsignedInt";
const AxisChar* gsc_strlit_short = "short";
const AxisChar* gsc_strlit_unsignedShort = "unsignedShort";
const AxisChar* gsc_strlit_byte = "byte";
const AxisChar* gsc_strlit_unsignedByte = "unsignedByte";
const AxisChar* gsc_strlit_long = "long";
const AxisChar* gsc_strlit_integer = "integer";
const AxisChar* gsc_strlit_unsignedLong = "unsignedLong";
const AxisChar* gsc_strlit_float = "float";
const AxisChar* gsc_strlit_double = "double";
const AxisChar* gsc_strlit_decimal = "decimal";
const AxisChar* gsc_strlit_string = "string";
const AxisChar* gsc_strlit_hexBinary = "hexBinary";
const AxisChar* gsc_strlit_base64Binary = "base64Binary";
const AxisChar* gsc_strlit_duration = "duration";
const AxisChar* gsc_strlit_dateTime = "dateTime";
const AxisChar* gsc_strlit_time = "time";
const AxisChar* gsc_strlit_date = "date";
const AxisChar* gsc_strlit_gYearMonth = "gYearMonth";
const AxisChar* gsc_strlit_gYear = "gYear";
const AxisChar* gsc_strlit_gMonthDay = "gMonthDay";
const AxisChar* gsc_strlit_gDay = "gDay";
const AxisChar* gsc_strlit_gMonth = "gMonth";
const AxisChar* gsc_strlit_anyURI = "anyURI";
const AxisChar* gsc_strlit_QName = "QName";

static StringLiteral gsc_StringLiteralsTable[SL_LAST] = {
/*SL_SPACE                  */{&gsc_BigStringLiteral[0], 1},
/*SL_LT                     */{&gsc_BigStringLiteral[1], 1},
/*SL_GT                     */{&gsc_BigStringLiteral[3], 1},
/*SL_DBLQUOTE               */{&gsc_BigStringLiteral[5], 1},
/*SL_SGLQUOTE               */{&gsc_BigStringLiteral[6], 1},
/*SL_COLON                  */{&gsc_BigStringLiteral[11], 1},
/*SL_EQUAL                  */{&gsc_BigStringLiteral[16], 1},
/*SL_LT_FSLASH              */{&gsc_BigStringLiteral[1], 2},
/*SL_FSLASH_GT              */{&gsc_BigStringLiteral[2], 2},
/*SL_SPACEXSITYPEEQXSD      */{&gsc_BigStringLiteral[7], 15},
/*SL_XSINILEQLTRUE          */{&gsc_BigStringLiteral[47], 15},
/*SL_ENCODED_LESSER_STR     */{&gsc_BigStringLiteral[22], 4},
/*SL_ENCODED_GREATOR_STR    */{&gsc_BigStringLiteral[26], 4},
/*SL_ENCODED_AMPERSAND_STR  */{&gsc_BigStringLiteral[30], 5},
/*SL_ENCODED_DBL_QUOTE_STR  */{&gsc_BigStringLiteral[35], 6},
/*SL_ENCODED_SGL_QUOTE_STR  */{&gsc_BigStringLiteral[41], 6},
/*SL_XSD_INT                */{gsc_strlit_int, 3},
/*SL_XSD_BOOLEAN            */{gsc_strlit_boolean, 7},
/*SL_XSD_UNSIGNEDINT        */{gsc_strlit_unsignedInt, 11},
/*SL_XSD_SHORT              */{gsc_strlit_short, 5},
/*SL_XSD_UNSIGNEDSHORT      */{gsc_strlit_unsignedShort, 13},
/*SL_XSD_BYTE               */{gsc_strlit_byte, 4},
/*SL_XSD_UNSIGNEDBYTE       */{gsc_strlit_unsignedByte, 12},
/*SL_XSD_LONG               */{gsc_strlit_long, 4},
/*SL_XSD_INTEGER            */{gsc_strlit_integer, 7},
/*SL_XSD_UNSIGNEDLONG       */{gsc_strlit_unsignedLong, 12},
/*SL_XSD_FLOAT              */{gsc_strlit_float, 5},
/*SL_XSD_DOUBLE             */{gsc_strlit_double, 6},
/*SL_XSD_DECIMAL            */{gsc_strlit_decimal, 7},
/*SL_XSD_STRING             */{gsc_strlit_string, 6},
/*SL_XSD_HEXBINARY          */{gsc_strlit_hexBinary, 9},
/*SL_XSD_BASE64BINARY       */{gsc_strlit_base64Binary, 12},
/*SL_XSD_DURATION           */{gsc_strlit_duration, 8},
/*SL_XSD_DATETIME           */{gsc_strlit_dateTime, 8},
/*SL_XSD_TIME               */{gsc_strlit_time, 4},
/*SL_XSD_DATE               */{gsc_strlit_date, 4},
/*SL_XSD_YEARMONTH          */{gsc_strlit_gYearMonth, 10},
/*SL_XSD_YEAR               */{gsc_strlit_gYear, 5},
/*SL_XSD_MONTHDAY           */{gsc_strlit_gMonthDay, 9},
/*SL_XSD_DAY                */{gsc_strlit_gDay, 4},
/*SL_XSD_MONTH              */{gsc_strlit_gMonth, 6},
/*SL_XSD_ANYURI             */{gsc_strlit_anyURI, 6},
/*SL_XSD_QNAME              */{gsc_strlit_QName, 5}
};

#endif


