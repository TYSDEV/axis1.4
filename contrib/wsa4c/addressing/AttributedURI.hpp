#if !defined(__ATTRIBUTEDURI_OF_AXIS_INCLUDED__)
#define __ATTRIBUTEDURI_OF_AXIS_INCLUDED__

#include "Constants.hpp"
#include <axis/server/IHeaderBlock.h>
#include <axis/server/IMessageData.h>

class AttributedUri
{
private:
    AxisChar * m_pachLocalName;
    AxisChar * m_pachUri;
    
public:
	/*
     * Constructor AttributedURI
     */
    AttributedUri(); 

	/*
     * Constructor AttributedURI
     * @param uri 
     */
    AttributedUri(AxisChar * pachLocalName, AxisChar * pachUri);

    /*Distructor
	 */
	~AttributedUri();
   
    void setUri(AxisChar * pUri);

    AxisChar * getUri();

    void setLocalName(AxisChar * pachLocalName);

    AxisChar * getLocalName();

    virtual IHeaderBlock * toSoapHeaderBlock(IMessageData *pIMsg);
     
    
};
#endif
