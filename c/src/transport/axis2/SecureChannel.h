#if !defined(_AXIS_SECURECHANNEL_H)
#define _AXIS_SECURECHANNEL_H
#include "ISecureChannel.hpp"
#include "SSLChannelFactory.hpp"
#include "../SSLChannel.hpp"
#include "Channel.h"
#include <iostream>
using namespace std;

class SecureChannel: public Channel, public ISecureChannel
{
public:
	SecureChannel();
	virtual ~SecureChannel();

	bool open() throw (AxisTransportException&);
	void close();

	const Channel& operator >> (std::string&) throw (AxisTransportException);
	const Channel& operator << (const char *) throw (AxisTransportException);

	bool setServerName( std::string);
	string getServerName();

	void setSecureProperties( const char *);
	const char * getSecureProperties();
        int setTransportProperty(AXIS_TRANSPORT_INFORMATION_TYPE
            type, const char* value);

private:
    bool openConnection();
    bool closeConnection();

    int openSecureSocket();
    int closeSecureSocket();

    bool writeSecureSocket(const char *);
    bool readSecureSocket( char *, int);

    //void setSecureError(int iError){};
    //char* getSecureError(){ return NULL;};

protected:
	string sServerName;
        //SecureChannel* m_pSecureChannel;
	SSLChannelFactory* m_pFactory;
        SSLChannel* m_pSSLChannel;
};

#endif

