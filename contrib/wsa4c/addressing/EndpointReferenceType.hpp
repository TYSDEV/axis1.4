#if !defined(__ENDPOINT_TYPE_REFERENCE_TYPE_OF_AXIS_INCLUDED__)
#define __ENDPOINT_TYPE_REFERENCE_TYPE_OF_AXIS_INCLUDED__

#include "Adress.hpp"
#include "PortType.hpp"
#include "ReferenceProperties.hpp"
#include "PortType.hpp"
#include "ServiceNameType.hpp"
#include "AttributedQName.hpp"
#include "AttributedUri.hpp"
  
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
         AttributedQName * m_pServiceName;

         AxisChar * m_pachLocalName;
  
    
	 public:

         EndpointReferenceType(AxisChar* pachLocalName);
  	  
      /***
       * Constructor EndpointReferenceType
       * 
       * @param address 
       */
         EndpointReferenceType(AxisChar* pachLocalName,AxisChar * pachAddress);
  
         ~EndpointReferenceType();
      
      /***
       * Method getAddress
       * 
       * @return 
       */
         AttributedUri * getAddress();
 
      /***
       * Method setAddress
       * 
       * @param address 
       */
         void setAddress(AxisChar * pachAddress);
 
      /***
       * Method getPortType
       * 
       * @return 
       */
         AttributedQName * getPortType();
 
      /***
       * Method setPortType
       * 
       * @param portType 
       */
         void setPortType(AttributedQName * portType);
 
      /***
       * Method getProperties
       * 
       * @return 
       */
         ReferenceProperties * getProperties();
 
      /***
       * Method setProperties
       * 
       * @param properties 
       */
         void setProperties(ReferenceProperties * pRefprops);
 
      /***
       * Method getServiceName
       * 
       * @return 
       */
        AttributedQName * getServiceName();
 
      /***
       * Method setServiceName
       * 
       * @param serviceName 
       */
        void setServiceName(AttributedQName * pServiceName);

        
        IHeaderBlock * toSoapHeaderBlock(IMessageData *pIMsg);

         
 };

#endif
 
 
