#include "ReplyTo.hpp"

ReplyTo::ReplyTo()
:EndpointReferenceType(Constants.REPLY_TO)
{
}
ReplyTo::ReplyTo(AxisChar * address)
:EndpointReferenceType(Constants.REPLY_TO,address)
{
}
  

      
