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


#if !defined(__ATTRIBUTED_QNAME_INCLUDED__)
#define __ATTRIBUTED_QNAME_INCLUDED__
#include <axis/server/BasicNode.hpp>
#include <axis/server/IHeaderBlock.hpp>
#include <axis/server/IMessageData.hpp>

AXIS_CPP_NAMESPACE_USE

class AttributedQName
{
private:
	AxisChar * m_pachLocalName;
	AxisChar * m_pachUri;
	AxisChar * m_pachQname;
public:
	AttributedQName();
	~AttributedQName();
	AttributedQName(const AxisChar* pachLocalName, const AxisChar* pachUri);
	const AxisChar * toUri();
	AxisChar * getLocalName();
	void setLocalName(AxisChar * pachLocalName);
	AxisChar * getUri();
	AxisChar * getQname();
	void setUri(AxisChar * pachUri);
	virtual IHeaderBlock * toSoapHeaderBlock(IMessageData *pIMsg);
   
};

#endif
  
  