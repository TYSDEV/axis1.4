// SoapBinInputStream.cpp: implementation of the SoapBinInputStream class.
//
//////////////////////////////////////////////////////////////////////

#include "axis/soap/SoapBinInputStream.h"

//////////////////////////////////////////////////////////////////////
// Construction/Destruction
//////////////////////////////////////////////////////////////////////

SoapBinInputStream::SoapBinInputStream(AXIS_GET_BYTES pReadFunct, const void* pContext)
{
	m_pContext = pContext;
	m_pReadFunct = pReadFunct;
	m_nByteCount = 0;
}

SoapBinInputStream::~SoapBinInputStream()
{

}

/**
 * I dont really understand the use of this function
 */
unsigned int SoapBinInputStream::curPos() const
{
	return m_nByteCount;
}

unsigned int SoapBinInputStream::readBytes(XMLByte* const toFill, const unsigned int maxToRead)
{
	int nRead = 0;
	m_pReadFunct((char*)toFill, maxToRead, &nRead, m_pContext);
    
	return nRead;
}
