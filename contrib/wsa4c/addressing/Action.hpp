#if !defined(__ACTION_OF_AXIS_INCLUDED__)
#define __ACTION_OF_AXIS_INCLUDED__

#include "AttributedURI.hpp"
#include <string>
#include <axis/server/IMessageData.h>

class Action:public AttributedUri {
public:
    /*
     * Constructor Action
     * 
     * @param uri 
     */
     Action(AxisChar * pachUri);

     
      
  };

#endif
