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
 *
 *
 *   @author Damitha Kumarage (damitha@opensource.lk, damitha@jkcsworld.com)
 *
 */


#include "AxisDivByZeroException.h"

#include <axis/server/AxisWrapperAPI.h>

AxisDivByZeroException::AxisDivByZeroException()
{
/* This only serves the pupose of indicating that the 
 * service has thrown an excpetion 
 */ 
	m_iExceptionCode = AXISC_SERVICE_THROWN_EXCEPTION; 
	processException(m_iExceptionCode); 
}

AxisDivByZeroException::AxisDivByZeroException(DivByZeroStruct*pFault)
{
	m_iExceptionCode = AXISC_SERVICE_THROWN_EXCEPTION;
	processException(pFault);}

AxisDivByZeroException::AxisDivByZeroException(int iExceptionCode)
{

	m_iExceptionCode = iExceptionCode;
	processException (iExceptionCode);
}

AxisDivByZeroException::AxisDivByZeroException(exception* e)
{
	processException (e);
}

AxisDivByZeroException::AxisDivByZeroException(exception* e,int iExceptionCode)
{

	processException (e, iExceptionCode);
}

AxisDivByZeroException::~AxisDivByZeroException() throw () 
{
	m_sMessage ="";
}

void AxisDivByZeroException:: processException(exception* e, int iExceptionCode)
{
	m_sMessage = getMessage (e) + getMessage (iExceptionCode);
}

void AxisDivByZeroException::processException (DivByZeroStruct* pFault)
{
	/*User can do something like deserializing the struct into a string*/

        int iSize = strlen(pFault->varString) + 8;
        char* sMessage = new char[iSize];
        m_sMessage = new char[iSize];
        char* carrTempBuff =new char[4 * sizeof(char)];
        sprintf(carrTempBuff, "%d", pFault->varInt);
        strcpy(sMessage, carrTempBuff);
        strcat(sMessage, "\n");
        sprintf(carrTempBuff, "%f", pFault->varFloat);
        strcat(sMessage, carrTempBuff);
        strcat(sMessage, "\n");
        strcat(sMessage, pFault->varString);
        strcat(sMessage, "\n");
        m_sMessage = sMessage;
        delete(sMessage);
}

void AxisDivByZeroException::processException(exception* e)
{
	m_sMessage = getMessage (e);
}

void AxisDivByZeroException::processException(int iExceptionCode)
{
	m_sMessage = getMessage (iExceptionCode);
}

const string AxisDivByZeroException::getMessage (exception* objException)
{
	string sMessage = objException->what();
	return sMessage;
}

const string AxisDivByZeroException::getMessage (int iExceptionCode)
{
	string sMessage;
	switch(iExceptionCode)
	{
		case AXISC_SERVICE_THROWN_EXCEPTION:
		sMessage = "A service has thrown an exception. see detail";
		break;
		default:
		sMessage = "Unknown Exception has occured";
	}
return sMessage;
}

const char* AxisDivByZeroException::what() throw ()
{
	return m_sMessage.c_str ();
}

const int AxisDivByZeroException::getExceptionCode(){
	return m_iExceptionCode;
}

