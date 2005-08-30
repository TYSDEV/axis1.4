/*
 * Copyright 2001-2004 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#if !defined(_WSSCONFIG_H_INCLUDED_)
#define _WSSCONFIG_H_INCLUDED_

WSS4C_NAMESPACE_BEGIN

class WSSConfig  
{
public:
	virtual ~WSSConfig();

public:
	static	WSSConfig* getNewInstance(void) ;
	static	WSSConfig* getDefaultWSConfig(void) ;

	enum {TIMESTAMP_IN_SECURITY_ELEMENT = 1, TIMESTAMP_IN_HEADER_ELEMENT = 2, };

	string	getWsseNS(void) ;
	void	setWsseNS(string wsseNamespace) ;
	string	getWsuNS(void) ;
	void	setWsuNS(string wsuNamespace) ;
	bool	isBSTAttributesQualified(void) ;
	void	setBSTAttributesQualified(bool qualify) ;
	bool	isBSTValuesPrefixed(void);
	void	setBSTValuesPrefixed(bool prefix);
	bool	isTargetIdQualified(void);
	void	setTargetIdQualified(bool qualify);
	int		getTimestampLocation(void);
	void	setTimestampLocation(int timestampElementLocation);
	bool	getProcessNonCompliantMessages(void);
	void	setProcessNonCompliantMessages(bool attemptProcess);

protected:
	WSSConfig();
	
protected:
	static	WSSConfig* defaultConfig ;
	string	wsse_ns ;
	string	wsu_ns ;
	bool	qualifyBSTAttributes ;
	bool	prefixBSTValues ;
	bool	targetIdQualified ;
	bool	processNonCompliantMessages ;
	int		timestampLocation ;
};

WSS4C_NAMESPACE_END

#endif // !defined(_WSSCONFIG_H_INCLUDED_)
