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


#include "AttributedQName.hpp"
#include "Constants.hpp"

AttributedQName::AttributedQName()
{
	m_pachLocalName = "\0";
	m_pachUri = "\0";
	m_pachQname = "\0";

}
AttributedQName::AttributedQName(const AxisChar* pachLocalName, const AxisChar* pachUri)
{
	m_pachLocalName = (AxisChar*) malloc(strlen(pachLocalName)+1);
    strcpy(m_pachLocalName, pachLocalName);
	m_pachUri = (AxisChar*) malloc(strlen(pachUri)+1);
	strcpy(m_pachUri,pachUri);
	m_pachQname = "\0";
}

AttributedQName::~AttributedQName()
{	
	free(m_pachLocalName);
	free(m_pachUri);
	free(m_pachQname);
}

AxisChar * AttributedQName::getLocalName()
{
	return m_pachLocalName;
}

void AttributedQName::setLocalName(AxisChar * pachLocalName)
{
	free(m_pachLocalName);
	m_pachLocalName = (AxisChar*) malloc(strlen(pachLocalName)+1);
    strcpy(m_pachLocalName, pachLocalName);
}

AxisChar * AttributedQName::getUri()
{
	return m_pachUri;
}

void AttributedQName::setUri(AxisChar * pachUri)
{
	free(m_pachUri);
	m_pachUri = (AxisChar*) malloc(strlen(pachUri)+1);
	strcpy(m_pachUri,pachUri);

}

AxisChar * AttributedQName::getQname()
{  
    if(strlen(m_pachQname)!=0)
		 free(m_pachQname);

	m_pachQname = (AxisChar*) malloc(strlen(m_pachLocalName)+strlen(m_pachUri)+5);
	
    if(strlen(m_pachUri)!=0){
		strcpy(m_pachQname,m_pachUri);
        if(strlen(m_pachLocalName)!=0){
            strcat(m_pachQname,":");
		    strcat(m_pachQname,m_pachLocalName);		    
        }
    }
    if(strlen(m_pachQname)==0)
        return "";
	
	return m_pachQname;	
}

IHeaderBlock * AttributedQName::toSoapHeaderBlock(IMessageData *pIMsg)
{
    return NULL;
}

	
