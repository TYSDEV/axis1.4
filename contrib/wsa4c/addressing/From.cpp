#include "From.hpp"

From::From()
:EndpointReferenceType(Constants.FROM)
{
}
From::From(AxisChar * address)
:EndpointReferenceType(Constants.FROM,address)
{
}
  

      
