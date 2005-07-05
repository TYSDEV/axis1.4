// Copyright 2003-2004 The Apache Software Foundation.
// (c) Copyright IBM Corp. 2004, 2005 All Rights Reserved
// 
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
// 
//        http://www.apache.org/licenses/LICENSE-2.0
// 
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.


#include "XSD_gYearMonth.hpp"
#include <axis/AxisException.hpp>
#include <ctype.h>
#include <iostream>


int main(int argc, char* argv[])
{
    char endpoint[256];
    const char* url="http://localhost:80/axis/XSD_gYearMonth";

    if(argc>1)
        url = argv[1];

    try
    {
        sprintf(endpoint, "%s", url);
        XSD_gYearMonth* ws = new XSD_gYearMonth(endpoint);

        char returnString[50];
        
        time_t timeToTest = 1100246323;
        struct tm *temp = gmtime(&timeToTest);
        struct tm time;
        memcpy(&time, temp, sizeof(struct tm));

        // Test non-nillable element
        xsd__gYearMonth result = ws->asNonNillableElement(time);
        strftime(returnString, 50, "%b %Y", &result);
        cout << "non-nillable element=" << returnString << endl;

        // Test nillable element, with a value
        xsd__gYearMonth* nillableInput = new xsd__gYearMonth();
        *(nillableInput) = time;
        xsd__gYearMonth* nillableResult = ws->asNillableElement(nillableInput);
        if (nillableResult)
        {
            strftime(returnString, 50, "%b %Y", nillableResult);
            cout << "nillable element=" << returnString << endl;
            delete nillableResult;
        }
        else
        {
            cout << "nillable element=<nil>" << endl;
        }
        delete nillableInput;

        // Test nillable element, with nil
        nillableResult = ws->asNillableElement(NULL);
        if (nillableResult)
        {
            strftime(returnString, 50, "%b %Y", nillableResult);
            cout << "nil element=" << returnString << endl;
            delete nillableResult;
        }
        else
        {
            cout << "nil element=<nil>" << endl;
        }

        // Test required attribute
        RequiredAttributeElement requiredAttributeInput;
        requiredAttributeInput.setrequiredAttribute(time);
        RequiredAttributeElement* requiredAttributeResult = ws->asRequiredAttribute(&requiredAttributeInput);
        result = requiredAttributeResult->getrequiredAttribute();
        strftime(returnString, 50, "%b %Y", &result);
        cout << "required attribute=" << returnString << endl;
        delete requiredAttributeResult;

/* Optional Attributes currently unsupported by WSDL2Ws
 * Exact coding of this section may change depending on chosen implementation
        // Test optional attribute, with a value
        OptionalAttributeElement optionalAttributeInput;
        optionalAttributeInput.setoptionalAttribute(time);
        OptionalAttributeElement* optionalAttributeResult = ws->asOptionalAttribute(&optionalAttributeInput);
        if (optionalAttributeResult->getoptionalAttribute())
        {
            strftime(returnString, 50, "%b %Y", optionalAttributeResult->getoptionalAttribute());
            cout << "optional attribute, with data=" << returnString << endl;
        }
        else
        {
            cout << "optional attribute, with data=<not present>" << endl;
        }
        delete optionalAttributeResult;

        // Test optional attribute, not present
        optionalAttributeInput.setattribute();
        optionalAttributeResult = ws->asOptionalAttribute(&optionalAttributeInput);
        if (optionalAttributeResult->getoptionalAttribute())
        {
            strftime(returnString, 50, "%b %Y", optionalAttributeResult->getoptionalAttribute());
            cout << "optional attribute, not present=" << returnString << endl;
        }
        else
        {
            cout << "optional attribute, not present=<not present>" << endl;
        }
        delete optionalAttributeResult;
*/
        // Test array
        xsd__gYearMonth_Array arrayInput;
        arrayInput.m_Array = new xsd__gYearMonth*[2];
        xsd__gYearMonth * array = new xsd__gYearMonth[2];
        arrayInput.m_Size = 2;
        for (int inputIndex=0 ; inputIndex < 2 ; inputIndex++)
        {
            array[inputIndex] = time;
            arrayInput.m_Array[inputIndex] = &array[inputIndex];
        }
        xsd__gYearMonth_Array arrayResult = ws->asArray(arrayInput);
        cout << "array of " << arrayResult.m_Size << " elements" << endl;
        for (int index = 0; index < arrayResult.m_Size ; index++)
        {
            strftime(returnString, 50, "%b %Y", arrayResult.m_Array[index]);
            cout << "  element[" << index << "]=" << returnString << endl;
            delete arrayResult.m_Array[index];
        }
        delete [] array;
        delete [] arrayInput.m_Array;
        delete [] arrayResult.m_Array;

        // Test complex type
        SimpleComplexType complexTypeInput;
        complexTypeInput.setcomplexTypeElement(time);
        SimpleComplexType* complexTypeResult = ws->asComplexType(&complexTypeInput);
        result = complexTypeResult->getcomplexTypeElement();
        strftime(returnString, 50, "%b %Y", &result);
        cout << "within complex type=" << returnString << endl;
        delete complexTypeResult;

        // Tests now complete

        delete ws;
    }
    catch(AxisException& e)
    {
        cout << "Exception : " << e.what() << endl;
    }
    catch(exception& e)
    {
        cout << "Unknown exception has occured: " << e.what() << endl;
    }
    catch(...)
    {
        cout << "Unknown exception has occured" << endl;
    }

    cout<< "---------------------- TEST COMPLETE -----------------------------"<< endl;
   
    return 0;
}
