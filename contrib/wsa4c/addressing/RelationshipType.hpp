#if !defined(__RELATIONSHIP_TYPE_OF_AXIS_INCLUDED__)
#define __RELATIONSHIP_TYPE_OF_AXIS_INCLUDED__

#include "AttributedQName.hpp"
#include <axis/server/BasicNode.h>
#include <axis/server/IHeaderBlock.h>
#include <axis/server/IMessageData.h>

  
 
class RelationshipType:public AttributedQName{
      
public:
    RelationshipType();
	RelationshipType(const AxisChar* pachLocalName, const AxisChar* pachUri);
	IHeaderBlock * toSoapHeaderBlock(IMessageData *pIMsg);       
};
 

#endif
