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
//#include<iostream.h>
#include<string.h>
#include<time.h>
#include<stdlib.h>
#include<malloc.h>
#include <conio.h>

const unsigned int COUNT_START = -12219292800000;  // 15 October 1582
const unsigned int ULONG_MAX = 4294967295;
const unsigned int MAX_RAND = 16384;
const char * pcHexaChars[16] = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};   

class UUIDGen{
public:
    UUIDGen();
    static char * nextUUID();
private:
    static int hexToint(char cHex);    
    int nextInt(int iNumber);
    int next(int iBits);
    int iClockSequence;      
    
};