/*
 * This file was auto-generated by the Axis C++ Web Service Generator (WSDL2Ws)
 * This file contains implementations of the base Exception class of the web service.
 */

#include "AxisServiceException.hpp"

#include <axis/AxisWrapperAPI.hpp>

AxisServiceException::AxisServiceException()
{
/* This only serves the purpose of indicating that the 
 * service has thrown an excpetion 
 */ 
	m_iExceptionCode = AXISC_SERVICE_THROWN_EXCEPTION; 
	processException(m_iExceptionCode); 
}

AxisServiceException::AxisServiceException(ISoapFault* pFault)
{
	m_iExceptionCode = AXISC_SERVICE_THROWN_EXCEPTION;
	m_pISoapFault = pFault;
	processException(pFault);}

AxisServiceException::AxisServiceException(int iExceptionCode)
{

	m_iExceptionCode = iExceptionCode;
	processException (iExceptionCode);
}

AxisServiceException::AxisServiceException(exception* e)
{
	processException (e);
}

AxisServiceException::AxisServiceException(exception* e,int iExceptionCode)
{

	processException (e, iExceptionCode);
}

AxisServiceException::AxisServiceException(string sMessage)
{
	 m_sMessage =sMessage;
}

AxisServiceException::~AxisServiceException() throw () 
{
	m_sMessage ="";
}

void AxisServiceException:: processException(exception* e, int iExceptionCode)
{
	m_sMessage = getMessage (e) + getMessage (iExceptionCode);
}

void AxisServiceException::processException (ISoapFault* pFault)
{
	/*User can do something like deserializing the struct into a string*/
}

void AxisServiceException::processException(exception* e)
{
	m_sMessage = getMessage (e);
}

void AxisServiceException::processException(int iExceptionCode)
{
	m_sMessage = getMessage (iExceptionCode);
}

const string AxisServiceException::getMessage (exception* objException)
{
	string sMessage = objException->what();
	return sMessage;
}

const string AxisServiceException::getMessage (int iExceptionCode)
{
	string sMessage;
	switch(iExceptionCode)
	{
		case AXISC_SERVICE_THROWN_EXCEPTION:
		sMessage = "The base service has thrown an exception. see details";
		break;
		default:
		sMessage = "Unknown Exception has occured in the base service";
	}
return sMessage;
}

const char* AxisServiceException::what() throw ()
{
	return m_sMessage.c_str ();
}

const int AxisServiceException::getExceptionCode(){
	return m_iExceptionCode;
}

const ISoapFault* AxisServiceException::getFault(){
	return m_pISoapFault;
}

