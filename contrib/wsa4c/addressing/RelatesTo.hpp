#if !defined(__RELATESTO_OF_AXIS_INCLUDED__)
#define __RELATESTO_OF_AXIS_INCLUDED__

#include "AttributedURI.hpp"
#include "RelationshipType.hpp"
#include <string>

class RelatesTo:AttributedUri{
public:
    /***
     * Constructor Adress
     * 
     * @param uri 
     */

    RelatesTo();
    ~RelatesTo();
    RelatesTo(AxisChar* pachUri);
    void setRelationshipType(RelationshipType * pRelationshipType);
    RelationshipType * getRelationshipType();
	IHeaderBlock * toSoapHeaderBlock(IMessageData *pIMsg);   
   
private:
    RelationshipType * m_pRelationshipType;
};
#endif

