/*
 * Copyright 2003-2006 The Apache Software Foundation.

 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * This file was auto-generated by the Axis C++ Web Service Generator (WSDL2Ws)
 * This file contains definitions of the web service
 */

#include "MathOps.hpp"


MathOps::MathOps()
{
}

MathOps::~MathOps()
{
}

/* This function is called by the AxisEngine when something went wrong
 with the current web service request processing. Appropriate actions should
 be taken here.*/
void MathOps::onFault()
{
}

xsd__int MathOps::div(xsd__int Value0,xsd__int Value1)  
{
	if (Value1 == 0) 
     {
         DivByZeroStruct* pObjFault = new DivByZeroStruct();
         if(pObjFault)
         {
             pObjFault->varString = "Division by zero exception";
             pObjFault->varInt = 1;
             pObjFault->varFloat = (float) 10.52;
             throw pObjFault;
         }
     }
	  if(Value0 < 0 || Value1 < 0) {
		   OutOfBoundStruct* pObjFault= new OutOfBoundStruct();
		   if(pObjFault)
           {
             pObjFault->varString = "Out of bounds exception";
             pObjFault->varInt = 2;
			 SpecialDetailStruct * detail=new SpecialDetailStruct();
			 detail->varString="This bounds exception is a forced exception";
             pObjFault->specialDetail=detail;
             throw pObjFault;
           }           
        }
        if(Value0 == 1000) {
			SpecialDetailStruct * detail=new SpecialDetailStruct();
			detail->varString="You have entered 1000 for the first parameter. 1000 is reserved. Please do not use it";
            throw detail;
        }
		return Value0/Value1;
}

