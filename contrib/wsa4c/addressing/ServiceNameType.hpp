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


#if !defined(__SERVICE_NAME_OF_AXIS_INCLUDED__)
#define __SERVICE_NAME_OF_AXIS_INCLUDED__

#include "AttributedQName.hpp"
#include <axis/server/BasicNode.hpp>
#include <axis/server/IHeaderBlock.hpp>
#include <axis/server/IMessageData.hpp>

AXIS_CPP_NAMESPACE_USE
  
 /***
  * C++ content class for ServiceNameType complex type.
  * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at http://schemas.xmlsoap.org/ws/2003/03/addressing/ line 67)
  * <p>
  * <pre>
  * &lt;complexType name="ServiceNameType">
  *   &lt;simpleContent>
  *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>QName">
  *       &lt;attribute name="PortName" type="{http://www.w3.org/2001/XMLSchema}NCName" />
  *     &lt;/extension>
  *   &lt;/simpleContent>
  * &lt;/complexType>
  * </pre>
  * 
  */
class ServiceNameType:public AttributedQName{
      
public:
    ServiceNameType();
	~ServiceNameType();
	AxisChar * getPortName();
	void setPortName(AxisChar * pachPortName);
	ServiceNameType(const AxisChar* pachLocalName, const AxisChar* pachUri,const AxisChar * portName);
	IHeaderBlock * toSoapHeaderBlock(IMessageData *pIMsg);
private:
	AxisChar * m_pachPortName;
        
};

#endif
 
