#if !defined(__SERVICE_NAME_OF_AXIS_INCLUDED__)
#define __SERVICE_NAME_OF_AXIS_INCLUDED__

#include "AttributedQName.hpp"
#include <axis/server/BasicNode.h>
#include <axis/server/IHeaderBlock.h>
#include <axis/server/IMessageData.h>

  
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
 
