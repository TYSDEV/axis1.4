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
 
#ifndef __AXISWSDDEXCEPTION_H_OF_AXIS_INCLUDED_
#define __AXISWSDDEXCEPTION_H_OF_AXIS_INCLUDED_

#include <string>
#include <exception>
#include <axis/server/AxisException.h>
using namespace std;

class AxisWsddException :public AxisException
{

  public:
  AxisWsddException();
  AxisWsddException(int exceptionCode);
  AxisWsddException(exception* e);
  AxisWsddException(exception* e, int exceptionCode);
  virtual ~AxisWsddException() throw();
};

#endif

