#if !defined(__REFERENCE_PROPERTIES_INCLUDED__)
#define __REFERENCE_PROPERTIES_INCLUDED__

#include "Constants.hpp"
#include <map>
#include <axis/server/BasicNode.h>
#include <axis/server/IHeaderBlock.h>
#include <axis/server/IMessageData.h>



class ReferenceProperties
{
private:
	int iNoOfChildren;
    map <AxisChar*, AxisChar*> m_refProps;
    AxisChar* m_pachPrefix;
    AxisChar* m_pachLocalName;
    
public:

	ReferenceProperties();
	ReferenceProperties(AxisChar * pachLocalName);
	~ReferenceProperties();
	void setLocalName(AxisChar * pachLocalName);
	AxisChar * getLocalName();
    void setPrefix(AxisChar * pachPrefix);
    AxisChar * getPrefix();
	int setProperty(AxisChar * pName, AxisChar * pValue);
	const AxisChar* getProperty (AxisChar* pachName);
    map<AxisChar*,AxisChar*> getProperties();
	IHeaderBlock * toSoapHeaderBlock(IMessageData *pIMsg); 
    IHeaderBlock * toSoapHeaderBlock(IMessageData *pIMsg, AxisChar* pName); 

};

#endif