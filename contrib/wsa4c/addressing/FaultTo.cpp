#include "FaultTo.hpp"

FaultTo::FaultTo()
:EndpointReferenceType(Constants.FAULT_TO)
{
}
FaultTo::FaultTo(AxisChar * address)
:EndpointReferenceType(Constants.FAULT_TO,address)
{
}
  

      
