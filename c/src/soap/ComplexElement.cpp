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
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 *
 *
 *
 * @author Roshan Weerasuriya (roshanw@jkcsworld.com, roshan@opensource.lk)
 * @author Samisa Abeysinghe (sabeysinghe@virtusa.com)
 *
 */

/*
 * Revision 1.1  2004/05/25 samisa
 * Added copy constructure and clone for copy constructing derived classes
 */

/*
 * Revision 1.2  2004/07/01 roshan
 * Added code to addChild(BasicNode *pBasicNode) method to deal with NULL
 *  values.
 */

/* ComplexElement.cpp: implementation of the ComplexElement class. */


#include "ComplexElement.h"
#include "SoapSerializer.h"
#include <axis/server/GDefine.h>

ComplexElement::ComplexElement()
{
    m_pachPrefix = '\0';
    m_pachLocalName = '\0';
    m_pachURI = '\0';

    m_iNodeType= ELEMENT_NODE;
    iNoOfChildren = 0;
}

ComplexElement::ComplexElement(AxisChar *pachLocalName, AxisChar *pachPrefix,
                               AxisChar *pachUri)
{
    m_pachLocalName = (AxisChar*) malloc(strlen(pachLocalName)+1);
    strcpy(m_pachLocalName, pachLocalName);
    m_pachPrefix = (AxisChar*) malloc(strlen(pachPrefix)+1);
    strcpy(m_pachPrefix, pachPrefix);
    m_pachURI = (AxisChar*) malloc(strlen(pachUri)+1);
    strcpy(m_pachURI, pachUri);
}

ComplexElement::ComplexElement(const ComplexElement& rCopy)
:BasicNode(rCopy), m_pachPrefix(NULL), m_pachLocalName(NULL), m_pachURI(NULL)
{
    this->iNoOfChildren = rCopy.iNoOfChildren;
    
    list<BasicNode*>::const_iterator itCurrBasicNode= rCopy.m_children.begin();

    while(itCurrBasicNode != rCopy.m_children.end())
    {
        this->m_children.push_back((*itCurrBasicNode)->clone());	
        itCurrBasicNode++;        
    } 
    
    if(rCopy.m_pachPrefix)
    	this->m_pachPrefix = strdup(rCopy.m_pachPrefix);

    if(rCopy.m_pachLocalName)
    	this->m_pachLocalName = strdup(rCopy.m_pachLocalName);
	
    if(rCopy.m_pachURI)
    	this->m_pachURI = strdup(rCopy.m_pachURI);
}

BasicNode* ComplexElement::clone()
{
    return new ComplexElement(*this);
}


ComplexElement::~ComplexElement()
{
    free(m_pachPrefix);
    free(m_pachLocalName);
    free(m_pachURI);


    /* Clear the children */
    list<BasicNode*>::iterator itCurrChild= m_children.begin();
    while(itCurrChild != m_children.end())
    {        
        delete (*itCurrChild);
        itCurrChild++;        
    }
    m_children.clear();
}

int ComplexElement::setPrefix(const AxisChar* pachPrefix)
{
    m_pachPrefix = (AxisChar*) malloc(strlen(pachPrefix)+1);
    strcpy(m_pachPrefix, pachPrefix);
    return AXIS_SUCCESS;
}

int ComplexElement::setLocalName(const AxisChar* pachLocalName)
{
    m_pachLocalName = (AxisChar*) malloc(strlen(pachLocalName)+1);
    strcpy(m_pachLocalName, pachLocalName);
    return AXIS_SUCCESS;
}

int ComplexElement::addChild(BasicNode *pBasicNode)
{
    if (pBasicNode)
    {
        m_children.push_back(pBasicNode);
        iNoOfChildren++;
        return AXIS_SUCCESS;
    }
    else
    {
        return AXIS_FAIL;
    }
}

int ComplexElement::serialize(SoapSerializer& pSZ)
{
    int iStatus= AXIS_SUCCESS;
    do
    {
        if(isSerializable()) 
        {    
            pSZ.serialize("<", NULL);    
            if(strlen(m_pachPrefix) != 0)
            {                
                pSZ.serialize(m_pachPrefix, ":", NULL);
            }
            pSZ.serialize(m_pachLocalName, NULL);
            if((strlen(m_pachPrefix) != 0) && (strlen(m_pachURI) != 0))
            {
                pSZ.serialize(" xmlns:", m_pachPrefix, "=\"", m_pachURI, "\"",
                    NULL);
            }
            pSZ.serialize(">", NULL);
            iStatus= serializeChildren(pSZ);
            if(iStatus==AXIS_FAIL)
            {
                break;
            }
            pSZ.serialize("</", NULL);
            if(strlen(m_pachPrefix) != 0)
            {
                pSZ.serialize(m_pachPrefix, ":", NULL);
            }
            pSZ.serialize(m_pachLocalName, ">", NULL);
            iStatus= AXIS_SUCCESS;
        } 
        else
        {
            iStatus= AXIS_FAIL;
        }
    } while(0);    
    return iStatus;
}

int ComplexElement::serialize(SoapSerializer& pSZ,
                              list<AxisChar*>& lstTmpNameSpaceStack)
{
    int iStatus= AXIS_SUCCESS;
    do
    {
        if(isSerializable()) 
        {    
            bool blnIsNewNamespace = false;

            pSZ.serialize("<", NULL);    
            if( (m_pachPrefix != NULL) && (strlen(m_pachPrefix) != 0))
            {                
                pSZ.serialize(m_pachPrefix, ":", NULL);
            }
            else if ( (m_pachURI != NULL) && (strlen(m_pachURI) != 0))
            {
                const AxisChar* pachTmp = pSZ.getNamespacePrefix(m_pachURI,
                    blnIsNewNamespace);
                m_pachPrefix = (AxisChar*) malloc(strlen(pachTmp)+1);
                strcpy(m_pachPrefix , pachTmp);

                if (blnIsNewNamespace)
                {
                    lstTmpNameSpaceStack.push_back(m_pachURI);
                }
                pSZ.serialize(m_pachPrefix, ":", NULL);
            }

            pSZ.serialize(m_pachLocalName, NULL);

            if(((m_pachPrefix != NULL) && (strlen(m_pachPrefix) != 0)) && 
                    ( (m_pachURI != NULL) && (strlen(m_pachURI) != 0)) && 
                    (blnIsNewNamespace))
            {
                pSZ.serialize(" xmlns:", m_pachPrefix, "=\"", m_pachURI, "\"",
                    NULL);
            }
            pSZ.serialize(">", NULL);

            iStatus= serializeChildren(pSZ, lstTmpNameSpaceStack);
            if(iStatus==AXIS_FAIL)
            {
                break;
            }
            pSZ.serialize("</", NULL);
            if((m_pachPrefix!=NULL) && (strlen(m_pachPrefix) != 0))
            {
                pSZ.serialize(m_pachPrefix, ":", NULL);
            }
            pSZ.serialize(m_pachLocalName, ">", NULL);
            iStatus= AXIS_SUCCESS;
        } 
        else
        {
            iStatus= AXIS_FAIL;
        }
    } while(0);    
    return iStatus;
}

bool ComplexElement::isSerializable()
{
    bool bStatus= true;

    do
    {
        if(strlen(m_pachLocalName) == 0)
        {
            bStatus= false;
            break;
        }
    }while(0);

    return bStatus;
}

int ComplexElement::setURI(const AxisChar* pachURI)
{
    m_pachURI = (AxisChar*) malloc(strlen(pachURI)+1);
    strcpy(m_pachURI, pachURI);
    return AXIS_SUCCESS;
}

int ComplexElement::serializeChildren(SoapSerializer& pSZ)
{
    list<BasicNode*>::iterator itCurrBasicNode= m_children.begin();

    while(itCurrBasicNode != m_children.end())
    {
        
        (*itCurrBasicNode)->serialize(pSZ);
        
        itCurrBasicNode++;        
    }    

    return AXIS_SUCCESS;
}

int ComplexElement::serializeChildren(SoapSerializer& pSZ,
                                      list<AxisChar*>& lstTmpNameSpaceStack)
{
    list<BasicNode*>::iterator itCurrBasicNode= m_children.begin();

    while(itCurrBasicNode != m_children.end())
    {
        if ((*itCurrBasicNode)->getNodeType() == ELEMENT_NODE)
        {
            (*itCurrBasicNode)->serialize(pSZ, lstTmpNameSpaceStack);    
        }
        else
        {
            /* for CHARACTER_NODE */
            (*itCurrBasicNode)->serialize(pSZ);
        }
        itCurrBasicNode++;        
    }    

    return AXIS_SUCCESS;
}

NODE_TYPE ComplexElement::getNodeType() const
{
    return m_iNodeType;
}

const BasicNode* ComplexElement::getFirstChild() const
{
    list<BasicNode*>::const_iterator itCurrChild= m_children.begin();

    if (itCurrChild != m_children.end())
    {        
        return (*itCurrChild);
    }    

    return NULL;
}

const BasicNode* ComplexElement::getLastChild() const
{
    list<BasicNode*>::const_reverse_iterator ritCurrChild= m_children.rbegin();

    if (ritCurrChild != m_children.rend())
    {        
        return (*ritCurrChild);
    }    

    return NULL;
}

const BasicNode* ComplexElement::getChild(int iChildPosition) const
{
    if (iChildPosition > iNoOfChildren)
    {
        return NULL;
    }
    else
    {
        list<BasicNode*>::const_iterator itCurrChild= m_children.begin();
        /* The following is done since the previous line already takes the 
         * iterator one step forward
         */
        iChildPosition--;

        /* Takes the iterator to the relavent positon */
        for (int i=0; i<iChildPosition; i++)
        {
            itCurrChild++;
        }

        if (itCurrChild != m_children.end())
        {
            return *itCurrChild;
        }
        else
        {
            return NULL;
        }
    }
}

int ComplexElement::getNoOfChildren()
{
    return iNoOfChildren;
}

const AxisChar* ComplexElement::getLocalName() {
    return m_pachLocalName; 
}
