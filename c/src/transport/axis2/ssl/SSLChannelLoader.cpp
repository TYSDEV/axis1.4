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
 */

/*
 * @author susantha Kumara (skumara@virtusa.com)
 * @author damitha Kumarage (damitha@hsenid.lk, damitha@opensource.lk)
 *
 */

#include "OpenSSLChannel.hpp"

extern "C" {
STORAGE_CLASS_INFO
int CreateInstance(SSLChannel **inst)
{
	*inst = new OpenSSLChannel();
	if (*inst)
	{
		return AXIS_SUCCESS;
	}
	return AXIS_FAIL;
}
STORAGE_CLASS_INFO 
int DestroyInstance(SSLChannel *inst)
{
	if (inst)
	{
		delete inst;
		return AXIS_SUCCESS;
	}
	return AXIS_FAIL;
}
}
