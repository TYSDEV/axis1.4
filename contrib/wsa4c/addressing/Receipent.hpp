#include "EndpointReferenceType.hpp"
#include "Adress.hpp"

class Receipent:public EndpointReferenceType {
      /***
       * Constructor Receipent
       * 
       * @param address 
       */
public:
    Receipent(Adress * address);
  
      /***
       * Constructor Receipent
       * 
       * @param uri 
       */
    Receipent(std::string uri);
  
      /***
       * Constructor Receipent
       * 
       * @param endpoint 
       */
    Receipent(EndpointReferenceType * endpoint);
  
      
    virtual HeaderBlock * toSoapHeaderBlock(SoapEnvelope * pEnv); 
          
  
      
  };
