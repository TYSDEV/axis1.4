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

#include "RecurseTypesWS.hpp" 
#include <stdlib.h> // For malloc(), calloc(), strdup() and free()
#include <iostream>
#include <fstream>

#define WSDL_DEFAULT_ENDPOINT "http://localhost:9080/RecurseTypes/services/sampleWS"

// If we re-direct cout it will be to this ofstream
static ofstream output_file;

// Prototype
bool parse_args_for_endpoint(int *argc, char *argv[], char **endpoint);
void shift_args(int i, int *argc, char *argv[]);
void setLogOptions(const char *output_filename);

extern void* Axis_Create_Type1(Type1 *Obj, bool bArray = false, int nSize=0);

int main(int argc, char* argv[])
{ 
  RecurseTypesWS *ws;

  char *endpoint = WSDL_DEFAULT_ENDPOINT;
  bool endpoint_set = false;
  int returnValue = 1; // Assume Failure

  endpoint_set = parse_args_for_endpoint(&argc, argv, &endpoint);

		bool bSuccess = false;
		int	iRetryIterationCount = 3;

		do
		{
  try {
    if(endpoint_set) {
      ws = new RecurseTypesWS(endpoint, APTHTTP1_1);
      free(endpoint);
      endpoint_set = false;
    } else
      ws = new RecurseTypesWS();

    Type1 *input,*output;
    int i;

    input = new Type1 ();
    input->att_kind = new char[strlen(Kind_CHEQUE) + 1];
	strcpy(input->att_kind, Kind_CHEQUE);
    input->kind = new char[strlen("Check In") + 1];
	strcpy(input->kind, "Check In");

	Type1_Array arrayIn;
	Type1 ** array = new Type1*[10];

    for ( i = 0; i < 10; i++ )
	{
		array[i]=new Type1();
        array[i]->kind = new char[strlen("Sample") + 1];
		strcpy(array[i]->kind, "Sample");
		array[i]->index = 0;
    }
	arrayIn.set(array,10);
	input->setfollowings(&arrayIn);
    output = ws->getInput(input);

    cout << "Result" << endl;
    if ( output == NULL )
      cout << "Invoke failed" << endl;
    else {
      cout << "\tAtt_kind = " << output->att_kind << endl;
      cout << "\tKind = " << output->kind << endl;
	  int outputSize = 0;
      Type1 ** outArray = output->followings->get(outputSize);;
      for ( i = 0; i < outputSize; i++ ) {
        cout << "\tKind [" << i << "] = " << outArray[i]->kind << endl;        
      }
	    // Clear up input array        
        for (int deleteIndex = 0 ; deleteIndex < 10 ; deleteIndex++ )
        {
            delete array[deleteIndex];
        }
		delete [] array;
		delete input;
		delete output;
      returnValue = 0; // Success

	  bSuccess = true;
    }
  } catch(AxisException &e) {
			bool bSilent = false;

			if( e.getExceptionCode() == CLIENT_TRANSPORT_OPEN_CONNECTION_FAILED)
			{
				if( iRetryIterationCount > 0)
				{
					bSilent = true;
				}
			}
			else
			{
				iRetryIterationCount = 0;
			}

            if( !bSilent)
			{
    cout << e.what() << endl;
			}
  } catch(...) {
    cout << "Unknown Exception occured." << endl;
  }
  try
  {
	  delete ws; 
  }
  catch(exception& exception)
  {
  	cout << "Exception on clean up of ws: " << exception.what()<<endl;
  }
  catch(...)
  {
  	cout << "Unknown exception on clean up of ws: " << endl;
  }
		iRetryIterationCount--;
		} while( iRetryIterationCount > 0 && !bSuccess);
    if(endpoint_set)
      free(endpoint);
  cout << "---------------------- TEST COMPLETE -----------------------------"<< endl;
  
  return returnValue;
}

/* Spin through args list and check for -e -p and -s options.
   Option values are expected to follow the option letter as the next
   argument.
 
   These options and values are removed from the arg list.
   If both -e and -s and or -p, then -e takes priority
*/
bool parse_args_for_endpoint(int *argc, char *argv[], char **endpoint) {

    // We need at least 2 extra arg after program name
    if(*argc < 3)
        return false;

    char *server = "localhost";
    int  port = 80;
    bool ep_set = false;
    bool server_set = false;
    bool port_set = false;

    for(int i=1; i<*argc; i++) {
        if(*argv[i] == '-') {
            switch(*(argv[i]+1)) {
            case 'e':
                *endpoint = strdup(argv[i+1]);
                ep_set = true;
                shift_args(i, argc, argv);
                i--;
                break;
            case 's':
                server = strdup(argv[i+1]);
                server_set = true;
                shift_args(i, argc, argv);
                i--;
                break;
            case 'p':
                port = atoi(argv[i+1]);
                if(port >80) port_set = true;
                shift_args(i, argc, argv);
                i--;
                break;
            case 'o':
                setLogOptions(argv[i+1]);
                shift_args(i, argc, argv);
                i--;
                break;
            default:
                break;
            }
        }
    }

    // use the supplied server and/or port to build the endpoint
    if(ep_set == false && (server_set || port_set)) {
        // Set p to the location of the first '/' after the http:// (7 chars)
        // e.g. from http://localhost:80/axis/base gets /axis/base
        char *ep_context = strpbrk(&(*endpoint)[7], "/");

        // http://:/ is 9 characters + terminating NULL character so add 10.
        // Allow space for port number upto 999999 6 chars
        *endpoint = (char *)calloc(1, 10 + strlen(ep_context) + strlen(server) + 6);
        sprintf(*endpoint, "http://%s:%d/%s", server, port, ep_context+1);
        if(server_set) free(server);
        ep_set = true;
    }

    return ep_set;
}

void shift_args(int i, int *argc, char *argv[]) {
    for(int j=i, k=i+2; j<*(argc)-2; j++, k++)
        argv[j]=argv[k];
    *argc-=2;
}
void setLogOptions(const char *output_filename) {
    output_file.open(output_filename, ios::out);
    if(output_file.is_open()){
        cout.rdbuf( output_file.rdbuf() );
    }
}

