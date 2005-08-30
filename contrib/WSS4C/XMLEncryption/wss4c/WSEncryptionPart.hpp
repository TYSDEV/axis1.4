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

#if !defined(_WSENCRYPTIONPART_H_INCLUDED_)
#define _WSENCRYPTIONPART_H_INCLUDED_

WSS4C_NAMESPACE_BEGIN

class WSEncryptionPart  
{
public:
	WSEncryptionPart();
	WSEncryptionPart(string nm, string nmspace, string encMod);
	virtual ~WSEncryptionPart();
		
	string getName(void);
	string getNamespace(void);
	string getEncModifier(void);

private:
	string name;
	string nmspace;
	string encModifier;
};

WSS4C_NAMESPACE_END

#endif
