// SoapInputSource.cpp: implementation of the SoapInputSource class.
//
//////////////////////////////////////////////////////////////////////

#include <axis/soap/SoapInputSource.h>

//////////////////////////////////////////////////////////////////////
// Construction/Destruction
//////////////////////////////////////////////////////////////////////

SoapInputSource::SoapInputSource(AXIS_GET_BYTES pReadFunct, const void* pContext)
{
	m_pInputStream = new SoapBinInputStream(pReadFunct, pContext);
}

SoapInputSource::~SoapInputSource()
{

}

BinInputStream* SoapInputSource::makeStream() const
{
	return m_pInputStream;
}
