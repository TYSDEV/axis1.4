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


#if !defined(__ENDPOINT_REFERENCE_TYPE_OF_AXIS_INCLUDED__)
#define __ENDPOINT_REFERENCE_TYPE_OF_AXIS_INCLUDED__

#include "Adress.hpp"
#include "PortType.hpp"
#include "ReferenceProperties.hpp"
#include "PortType.hpp"
#include "ServiceNameType.hpp"
#include "AttributedQName.hpp"
#include "AttributedUri.hpp"
#include <axis/IHeaderBlock.hpp>
  
  /***
   * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at http://schemas.xmlsoap.org/ws/2003/03/addressing/ line 46)
   * <p>
   * <pre>
   * &lt;complexType name="EndpointReferenceType">
   *   &lt;complexContent>
   *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
   *       &lt;sequence>
   *         &lt;element name="Address" type="{http://schemas.xmlsoap.org/ws/2003/03/addressing}AttributedURI"/>
   *         &lt;element name="ReferenceProperties" type="{http://schemas.xmlsoap.org/ws/2003/03/addressing}ReferencePropertiesType" minOccurs="0"/>
   *         &lt;element name="PortType" type="{http://schemas.xmlsoap.org/ws/2003/03/addressing}AttributedQName" minOccurs="0"/>
   *         &lt;element name="ServiceName" type="{http://schemas.xmlsoap.org/ws/2003/03/addressing}ServiceNameType" minOccurs="0"/>
   *         &lt;any/>
   *       &lt;/sequence>
   *     &lt;/restriction>
   *   &lt;/complexContent>
   * &lt;/complexType>
   * </pre>
   * 
   */
class EndpointReferenceType
{    
private:
    /***
     * Field address
     */
	AttributedUri * m_pAddress;
    /***
     * Field properties
     */
    ReferenceProperties * m_pRefprops;  
    /***
     * Field portType
     */
    AttributedQName * m_pPortType; 
    /***
     * Field serviceName
     */
    ServiceNameType * m_pServiceName;
    /***
     * Field localname
     */
    AxisChar * m_pachLocalName;

    bool m_bMustUnderstand;
  
public:
    
    EndpointReferenceType(const AxisChar* pachLocalName); 	
    EndpointReferenceType(const AxisChar* pachLocalName,const AxisChar * pachAddress);
    EndpointReferenceType(EndpointReferenceType * pEndpoint);
    EndpointReferenceType(IHeaderBlock * pIHeaderBlock);  
    ~EndpointReferenceType();      
    void init();
    AttributedUri * getAddress();
    void setAddress(const AxisChar * pachAddress); 
    AttributedQName * getPortType();
    void setPortType(AttributedQName * portType);
    ReferenceProperties * getProperties(); 
    void setProperties(ReferenceProperties * pRefprops);
    ServiceNameType * getServiceName(); 
    void setServiceName(ServiceNameType * pServiceName);
    void setMustUnderstand(bool bMustUnderstand);
    bool isMustUnderstand();
    virtual IHeaderBlock * toSoapHeaderBlock(IMessageData *pIMsg);      
    void setLocalName(const AxisChar * pachLocalName);
 };

#endif
 
 
