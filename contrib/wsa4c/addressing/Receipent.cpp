#include "Receipent.hpp"

Receipent::Receipent(Adress * address)
:EndpointReferenceType(Constants.RECIPIENT,Constants.NS_PREFIX_ADDRESSING,NULL,address)
{
}
  
Receipent::Receipent(std::string uri)
:EndpointReferenceType(Constants.RECIPIENT,Constants.NS_PREFIX_ADDRESSING,NULL,uri)
{
}
  
//Receipent::Receipent(EndpointReferenceType * endpoint);
  
      
