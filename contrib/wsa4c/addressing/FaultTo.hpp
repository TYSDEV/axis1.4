#include "EndpointReferenceType.hpp"

class FaultTo:public EndpointReferenceType 
{
      /***
       * Constructor FaultTo
       * 
       * @param address 
       */
public:
    FaultTo();
    FaultTo(AxisChar * address);   
  };
