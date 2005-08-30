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

#include <wss4c/wssenc.hpp>
#include "WSEncryptionPart.hpp"

WSS4C_NAMESPACE_BEGIN

//////////////////////////////////////////////////////////////////////
// WSEncryptionPart Class
//////////////////////////////////////////////////////////////////////

string WSEncryptionPart::getName(void)			{ return name;		}
string WSEncryptionPart::getNamespace(void)		{ return nmspace;	}
string WSEncryptionPart::getEncModifier(void)	{ return encModifier;	}


//////////////////////////////////////////////////////////////////////
// Construction/Destruction
//////////////////////////////////////////////////////////////////////

WSEncryptionPart::WSEncryptionPart()
{	
	name = "";
	nmspace = "";
	encModifier = "";
}

WSEncryptionPart::WSEncryptionPart(string nm, string nmspace, string encMod){

	this->name = nm;
	this->nmspace = nmspace;
	this->encModifier = encMod;
}

WSEncryptionPart::~WSEncryptionPart()
{
}

WSS4C_NAMESPACE_END
