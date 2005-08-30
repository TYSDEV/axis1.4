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
	m_pachLocalName = NULL;
	m_pachUri = NULL;
	m_pachQname = NULL;

}

AttributedQName::AttributedQName(const AxisChar* pachLocalName, const AxisChar* pachUri)
{
    if(pachLocalName != NULL && pachUri != NULL)
    {
	    m_pachLocalName = new AxisChar(strlen(pachLocalName)+1);
        strcpy(m_pachLocalName, pachLocalName);
	    m_pachUri = new AxisChar(strlen(pachUri)+1);
	    strcpy(m_pachUri,pachUri);
    }
	m_pachQname = NULL;
}

AttributedQName::AttributedQName(const AxisChar* pachQname)
{
    if(pachQname != NULL)
    {
        AxisChar *pachTemQname = new AxisChar(strlen(pachQname)+1);
        strcpy(pachTemQname,pachQname);
        AxisChar * p = pachTemQname;
        m_pachUri = pachTemQname;
        while(*p!='\0')
        { 
            if(*p==':')
            {
                m_pachUri = p+1;
                *p = '\0';
                m_pachLocalName =pachTemQname;
                break;
            }
            p++;
        }
        if(m_pachQname!=NULL)
            delete(m_pachQname);
        m_pachQname = new AxisChar(strlen(pachQname)+1);
        strcpy(m_pachQname,pachQname);      
    }
}

AttributedQName::~AttributedQName()
{	
    if(m_pachLocalName != NULL)
    {
	    delete(m_pachLocalName);
        m_pachLocalName = NULL;
    }
    if(m_pachUri != NULL)
    {
	    delete(m_pachUri);
        m_pachUri = NULL;
    }
    if(m_pachQname != NULL)
    {
	    delete(m_pachQname);
        m_pachQname = NULL;
    }
}

AxisChar * AttributedQName::getLocalName()
{
	return m_pachLocalName;
}

void AttributedQName::setLocalName(const AxisChar * pachLocalName)
{
	delete(m_pachLocalName);
	m_pachLocalName = new AxisChar(strlen(pachLocalName)+1);
    strcpy(m_pachLocalName, pachLocalName);
}

AxisChar * AttributedQName::getUri()
{
	return m_pachUri;
}

void AttributedQName::setUri(const AxisChar * pachUri)
{
    if(m_pachUri!=NULL)
	    delete(m_pachUri);
	m_pachUri = new AxisChar(strlen(pachUri)+1);
	strcpy(m_pachUri,pachUri);

}

AxisChar * AttributedQName::getQname()
{  
    if(m_pachQname!=NULL)
        delete(m_pachQname);

    if(m_pachUri != NULL)
    {	
        m_pachQname = (AxisChar*) malloc(strlen(m_pachLocalName)+strlen(m_pachUri)+5);	
        if(m_pachLocalName!=0)
        {
            strcpy(m_pachQname,m_pachLocalName);
		    strcat(m_pachQname,":");
            strcat(m_pachQname,m_pachUri);
        }
        else
        {
            strcpy(m_pachQname,m_pachUri);
        }
    }
    	
	return m_pachQname;	
}

IHeaderBlock * AttributedQName::toSoapHeaderBlock(IMessageData *pIMsg)
{
    return NULL;
}

	
