#if !defined(__MESSAGEID_OF_AXIS_INCLUDED__)
#define __MESSAGEID_OF_AXIS_INCLUDED__

#include "AttributedURI.hpp"
#include <string>
using namespace std;

class MessageId:public AttributedUri {
public:
    /*
     * Constructor MessageId
     * 
     * @param uri 
     */
	MessageId(AxisChar * pachUri);	

  };

#endif
