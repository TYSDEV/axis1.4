#if !defined(__ADDRESSING_HEADERS_OF_AXIS_INCLUDED__)
#define __ADDRESSING_HEADERS_OF_AXIS_INCLUDED__

#include <map>
#include "Action.hpp"
#include "ReplyTo.hpp"
#include "FaultTo.hpp"
#include "From.hpp"
#include "To.hpp"
#include "MessageID.hpp"
#include "ReferenceProperties.hpp"
#include "RelatesTo.hpp"
#include "RelationshipType.hpp"

using namespace std;

class AddressingHeaders 
{
private:
	Action * m_pAction;
    ReplyTo * m_pReplyTo;
    To * m_pTo;
    FaultTo * m_pFaultTo;
    From * m_pFrom;
    MessageId * m_pMessageId;
    list<RelatesTo*> m_relatesToList;  
    ReferenceProperties * m_pReferenceProperties;
    bool m_bSetMustUnderstand;
  
public:
   
    AddressingHeaders();
    AddressingHeaders(IMessageData *pIMsg);
    AddressingHeaders(IMessageData *pIMsg,bool bProcess,bool bRemove);
    AddressingHeaders(IMessageData *pIMsg,bool bProcess, bool bRemove, bool setMustUnderstand);
    ~AddressingHeaders();
    void init();    
    Action * getAction();
    void setAction(Action * pAction);
    To * getTo();
    void setTo(AxisChar * pachUri); 
    void setTo(To * pTo);
    void setMessageId(MessageId * pMessageId);
    void setReferenceProperties(ReferenceProperties * pRefProps);
    ReferenceProperties * getReferenceProperties();
    void toSoapMessage(IMessageData *pIMsg);
    MessageId * getMessageId();
    list<RelatesTo*> getRelatesToList();
    void setRelatesToList(list<RelatesTo*> relatesToList);
    void addRelatesTo(AxisChar * pachUri, RelationshipType * pType);
    From * getFrom();
    void setFrom(From * pFrom);
    ReplyTo * getReplyTo();
    void setReplyTo(ReplyTo * pReplyTo);
    EndpointReferenceType * getFaultTo();
    void setFaultTo(FaultTo * pFaultTo);                                                  
    bool isSetMustUnderstand();
    void setSetMustUnderstand(bool setMustUnderstand);
};
#endif
