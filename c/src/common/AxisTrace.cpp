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
 * @author Damitha Kumarage (damitha@opensource.lk, damitha@jkcsworld.com)
 *
 */

#include <axis/common/AxisTrace.h>
#include <time.h>
#include <stdio.h>
#include <axis/common/AxisUtils.h>
#include <axis/common/GDefine.h>
#include <axis/common/AxisConfig.h>
#include <fcntl.h>
#include <unistd.h>


extern AxisConfig* g_pConfig;


using namespace std;

AxisTrace::AxisTrace()
{
}

AxisTrace::~AxisTrace()
{
    fclose(fileTrace);
}

/**
    open log file for writing at server side.
*/
int AxisTrace::openFile()
{
    char* sFileName =  g_pConfig->GetAxisLogPath();
    if((fileTrace = fopen(sFileName, "w")) == NULL)
        return AXIS_FAIL;
    fclose(fileTrace);
    int length = strlen(sFileName) + 12;
    char* setPerm = (char*) malloc(length);
    if(setPerm)
    {
        strcpy(setPerm, "chmod 777 ");
        strcat(setPerm, sFileName);
    }

#ifdef __GNUC__
    system(setPerm);
#endif
    if((fileTrace = fopen(sFileName, "ab")) == NULL)
        return AXIS_FAIL;
                
    return AXIS_SUCCESS;
}

/**
    open log file for writing at client side.
*/
int AxisTrace::openFileByClient()
{
    char* sFileName =  "./ClientAxisLog";
    
    if((fileTrace = fopen(sFileName, "ab")) == NULL)
        return AXIS_FAIL;

    return AXIS_SUCCESS;
}

/** 
*   Logs messages according to their severity level.
*   @param sLog log message
*   @param level severity level of the message
*   @param arg2 file name
*   @param arg3 line number
*/
int AxisTrace::logaxis(const char* sLog, int level, char* arg2, int arg3)
{
    time_t ltime;
    time(&ltime);

    fputs("Severity Level : ", fileTrace);

    switch(level)
    {
        case 1: strLevel = "CRITICAL"; break;
        case 2: strLevel = "WARN"; break;
        case 3: strLevel = "INFO"; break;
        case 4: strLevel = "TRIVIAL"; break;
    }
        
    fputs(strLevel, fileTrace);
    fputs("\n", fileTrace);
 
    fputs("time : ", fileTrace);
    fputs(ctime(&ltime), fileTrace);
    fputs("file : ", fileTrace);
    fputs(arg2, fileTrace);
    fputs("\n", fileTrace);
    fputs("line : ", fileTrace);
    sprintf(strLine,"%d", arg3);
    fputs(strLine, fileTrace);
    fputs("\n", fileTrace);        
    fputs(sLog, fileTrace);        
    fputs("\n", fileTrace);
    fputs("-------------------------------------------------", fileTrace);
    fputs("\n", fileTrace);

    fflush(fileTrace);
            
    return AXIS_SUCCESS;
   
}

/**
*   Logs messages according to their severity level.
*   @param sLog1 log message1
*   @param sLog2 log message2
*   @param level severity level of the message
*   @param arg2 file name
*   @param arg3 line number
*/
int AxisTrace::logaxis(const char* sLog1, const char* sLog2, int level, char* arg3, int arg4)
{
    time_t ltime;
    time(&ltime);

    fputs("Severity Level : ", fileTrace);
    switch(level)
    {
        case 1: strLevel = "CRITICAL"; break;
        case 2: strLevel = "WARN"; break;
        case 3: strLevel = "INFO"; break;
        case 4: strLevel = "TRIVIAL"; break;
    }
    fputs(strLevel, fileTrace);
    fputs("\n", fileTrace);

    fputs("time : ", fileTrace);
    fputs(ctime(&ltime), fileTrace);
    fputs("file : ", fileTrace);
    fputs(arg3, fileTrace);
    fputs("\n", fileTrace);
    fputs("line : ", fileTrace);
    sprintf(strLine,"%d", arg4);
    fputs(strLine, fileTrace);
    fputs("\n", fileTrace);
    fputs(sLog1, fileTrace);
    fputs(" ", fileTrace);
    fputs(sLog2, fileTrace);
    fputs("\n", fileTrace);
    fputs("-------------------------------------------------", fileTrace);
    fputs("\n", fileTrace);

    fflush(fileTrace);
    
    return AXIS_SUCCESS;
    
}

int AxisTrace::trace(const char *pchLog)
{
	printf("DEBUG LINE :\n%s\n", pchLog);

	return AXIS_SUCCESS;
}

/*
int main(int argc, char* argv[])
{
  tracer.trace("test");
  return 0;
}
*/


