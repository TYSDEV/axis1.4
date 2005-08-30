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
#include <axis/BasicNode.hpp>
#include <axis/IHeaderBlock.hpp>
#include <axis/IMessageData.hpp>

AXIS_CPP_NAMESPACE_USE

class ReferenceProperties
{   
private:
    struct ltstr
	{
		bool operator()(const char* s1, const char* s2) const
		{
		    return strcmp(s1, s2) < 0;
		}
	};	
	int iNoOfChildren;
    map <AxisChar*,AxisChar*,ltstr> m_refProps;
    AxisChar* m_pachUri;
    AxisChar* m_pachLocalName;  

public:    
	ReferenceProperties();
    ReferenceProperties(ReferenceProperties * pRefprops);
	ReferenceProperties(IHeaderBlock * pIHParent);
	ReferenceProperties(const AxisChar * pachLocalName);
	~ReferenceProperties();
	void setLocalName(const AxisChar * pachLocalName);
	AxisChar * getLocalName();
    void setPrefixUri(const AxisChar * pachUri);
    AxisChar * getPrefixUri();
	const AxisChar* getProperty (AxisChar* pachName);
    map<AxisChar*,AxisChar*,ltstr> getProperties();
	void addProperty(const AxisChar * pachLocalName,const AxisChar * pachValue);
	IHeaderBlock * toSoapHeaderBlock(IMessageData *pIMsg); 
    IHeaderBlock * toSoapHeaderBlock(IMessageData *pIMsg,const AxisChar* pName); 
    void toSoapHeaders(IMessageData *pIMsg);  
};

#endif