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

#include "UUIDGen.hpp"
#include <iostream.h>
#include "MacAddress.hpp"

UUIDGen::UUIDGen()
{
    iClockSequence = nextInt(MAX_RAND);
}

int UUIDGen::hexToint(char cHex)
{
    switch(cHex)
    {
    case '0':
        return 0;
    case '1':
        return 1;
    case '2':
        return 2;
    case '3':
        return 3;
    case '4':
        return 4;
    case '5':
        return 5;
    case '6':
        return 6;
    case '7':
        return 7;
    case '8':
        return 8;
    case '9':
        return 9;
    case 'A':
        return 10;
    case 'B':
        return 11;
    case 'C':
        return 12;
    case 'D':
        return 13;
    case 'E':
        return 14;
    case 'F':
        return 15;
    }
}  
char * UUIDGen::nextUUID()
{
   unsigned long ulCount;

   // the number of milliseconds since 1 January 1970
   time_t timeValue;
   time(&timeValue);
   unsigned long ulCurrentTime = timeValue;    

   // the number of milliseconds since 15 October 1582
   unsigned long ulCountMillis = ulCurrentTime + COUNT_START;

   // the result
   ulCount = ulCountMillis * 10000;
   
   char * pcHexString =(char*) malloc(32);
   strcpy(pcHexString,"");
   for (int i=30; i>=0; i--) 
   {
       if(i<16)
       {
	       strcat(pcHexString,pcHexaChars[((ulCount >> i*4) & 0xF)]);
		   //cout<<"0123456789ABCDEF"[((ulCount >> i*4) & 0xF)];
       }
       else
       {
           //left padding with zero
           strcat(pcHexString,"0");
           //cout<<"0";
       }
    }
			 
    // the time_low field
	char * pcTimeLow = (char*)malloc(9);
	char * p = pcHexString;
    for (unsigned int j = 0; j <strlen(pcHexString)-8; j++)
        p++;
	strcpy(pcTimeLow,p);	
	*p='\0';
	
	// the time_mid field
	char * pcTimeMid = (char*)malloc(5);
	p= pcHexString;
    for (j = 0; j <strlen(pcHexString)-4; j++)
        p++;
	strcpy(pcTimeMid,p);
	*p='\0';

    // the time hi field multiplexed with the version
	char * pcTimeHiAndVersion =(char*)malloc(5);
    p = pcHexString;
    for (j=0; j <strlen(pcHexString)-4; j++)
        p++;
    strcpy(pcTimeHiAndVersion,p);
    *pcTimeHiAndVersion = '1';
    *p='\0';        
		
    // the clock_seq_low field
    char * pcClockSeqLow = (char*) malloc(3);
    // the clock_seq_hi_and_reserved field
    char * pcClockSeqHiAndReserved = (char*)malloc(3);
    strcpy(pcClockSeqLow,"");
    strcpy(pcClockSeqHiAndReserved,"");
    for (i=3; i>=0; i--) {   
        if(i>1)
			strcat(pcClockSeqLow,pcHexaChars[((ulCount >> i*4) & 0xF)]);
        else
            strcat(pcClockSeqHiAndReserved,pcHexaChars[((ulCount >> i*4) & 0xF)]);
    }
   
    int * piClockSeqHi = new int(hexToint(*pcClockSeqLow));
    *piClockSeqHi = *piClockSeqHi & 0x3F;
    *piClockSeqHi = *piClockSeqHi | 0xB0;
            
    pcClockSeqHiAndReserved = (char*) malloc(3);
    strcpy(pcClockSeqHiAndReserved,"");
    for (i=1; i>=0; i--) 
    {   
		strcat(pcClockSeqHiAndReserved,pcHexaChars[((*piClockSeqHi >> i*4) & 0xF)]);
    }
        
    char * pcMacAddress = (char*)malloc(13);
    MacAddress *pMacAddress = new MacAddress;
    pMacAddress->findMacAddress(pcMacAddress);
    
    char * pcUUIDString = (char*)malloc(37);
    strcpy(pcUUIDString,pcTimeLow);
    strcat(pcUUIDString,"-");
    strcat(pcUUIDString,pcTimeMid);
    strcat(pcUUIDString,"-");
    strcat(pcUUIDString,pcTimeHiAndVersion);
    strcat(pcUUIDString,"-");
    strcat(pcUUIDString,pcClockSeqHiAndReserved);
    strcat(pcUUIDString,pcClockSeqLow);
    strcat(pcUUIDString,"-");
    strcat(pcUUIDString,strdup(pcMacAddress));

    free(pcHexString);
    free(pcTimeLow);
    free(pcTimeMid);
    free(pcTimeHiAndVersion);
    free(pcClockSeqHiAndReserved);
    free(pcClockSeqLow);
    free(pcMacAddress);
    delete(pMacAddress);
    delete(piClockSeqHi);
    

    return pcUUIDString;

} 

int UUIDGen::nextInt(int iNumber)
{
    if ((iNumber & -iNumber) == iNumber)  // i.e., n is a power of 2
         return (int)((iNumber * (long)next(31)) >> 31);

     int bits, val;
     do {
         bits = next(31);
         val = bits % iNumber;
     } while((bits - val + (iNumber-1) < 0) & val>0);
     return val;
}

int UUIDGen::next(int iBits)
{
     time_t timeValue;
	 time(&timeValue);      
     unsigned int seed = timeValue;
     seed = (seed * 0x5DEECE66DL + 0xBL) & ((1L << 48) - 1);
     return (int)(seed >> (48 - iBits));
}
 