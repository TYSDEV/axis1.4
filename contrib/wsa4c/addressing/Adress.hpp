#if !defined(__ADDRESS_OF_AXIS_INCLUDED__)
#define __ADDRESS_OF_AXIS_INCLUDED__

#include "AttributedURI.hpp"
#include <string>

class Address:public AttributedUri {
public:
    /***
     * Constructor Adress
     * 
     * @param uri 
     */
    Address(AxisChar * pachUri);

    
      
};
#endif
