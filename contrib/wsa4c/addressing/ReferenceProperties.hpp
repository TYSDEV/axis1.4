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


#if !defined(__REFERENCE_PROPERTIES_INCLUDED__)
#define __REFERENCE_PROPERTIES_INCLUDED__

#include "Constants.hpp"
#include <map>
#include <axis/server/BasicNode.hpp>
#include <axis/server/IHeaderBlock.hpp>
#include <axis/server/IMessageData.hpp>

AXIS_CPP_NAMESPACE_USE

class ReferenceProperties
{
private:
	int iNoOfChildren;
    map <AxisChar*, AxisChar*> m_refProps;
    AxisChar* m_pachPrefix;
    AxisChar* m_pachLocalName;
    
public:

	ReferenceProperties();
	ReferenceProperties(AxisChar * pachLocalName);
	~ReferenceProperties();
	void setLocalName(AxisChar * pachLocalName);
	AxisChar * getLocalName();
    void setPrefix(AxisChar * pachPrefix);
    AxisChar * getPrefix();
	int setProperty(AxisChar * pName, AxisChar * pValue);
	const AxisChar* getProperty (AxisChar* pachName);
    map<AxisChar*,AxisChar*> getProperties();
	IHeaderBlock * toSoapHeaderBlock(IMessageData *pIMsg); 
    IHeaderBlock * toSoapHeaderBlock(IMessageData *pIMsg, AxisChar* pName); 

};

#endif