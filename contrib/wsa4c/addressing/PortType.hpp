#if !defined(__PORTTYPE_OF_AXIS_INCLUDED__)
#define __PORTTYPE_OF_AXIS_INCLUDED__

#include "AttributedQName.hpp"
#include <axis/server/BasicNode.h>
#include <axis/server/IHeaderBlock.h>
#include <axis/server/IMessageData.h>

  
 /***
  * C++ content class for PortType complex type.
  * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at http://schemas.xmlsoap.org/ws/2003/03/addressing/ line 67)
  * <p>
  * <pre>
  * &lt;complexType name="PortType">
  *   &lt;simpleContent>
  *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>QName">
  *       &lt;attribute name="PortName" type="{http://www.w3.org/2001/XMLSchema}NCName" />
  *     &lt;/extension>
  *   &lt;/simpleContent>
  * &lt;/complexType>
  * </pre>
  * 
  */
class PortType:public AttributedQName{
      
public:
    PortType();
	PortType(const AxisChar* pLocalName, const AxisChar* pUri);
	IHeaderBlock * toSoapHeaderBlock(IMessageData *pIMsg);   

};
 

#endif
