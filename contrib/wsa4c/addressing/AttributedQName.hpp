#if !defined(__ATTRIBUTED_QNAME_INCLUDED__)
#define __ATTRIBUTED_QNAME_INCLUDED__
#include <axis/server/BasicNode.h>
#include <axis/server/IHeaderBlock.h>
#include <axis/server/IMessageData.h>

class AttributedQName
{
private:
	AxisChar * m_pachLocalName;
	AxisChar * m_pachUri;
	AxisChar * m_pachQname;
public:
	AttributedQName();
	~AttributedQName();
	AttributedQName(const AxisChar* pachLocalName, const AxisChar* pachUri);
	const AxisChar * toUri();
	AxisChar * getLocalName();
	void setLocalName(AxisChar * pachLocalName);
	AxisChar * getUri();
	AxisChar * getQname();
	void setUri(AxisChar * pachUri);
	virtual IHeaderBlock * toSoapHeaderBlock(IMessageData *pIMsg);
   
};

#endif
  
  