#include "EndpointReferenceType.hpp"

class ReplyTo:public EndpointReferenceType 
{
      /***
       * Constructor ReplyTo
       * 
       * @param address 
       */
public:
    ReplyTo();
    ReplyTo(AxisChar * address);   
  };
