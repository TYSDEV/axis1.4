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

package org.apache.axis.transport;

import org.apache.axis.context.MessageContext;
import org.apache.axis.engine.AxisFault;
import org.apache.axis.transport.http.HTTPTransportReciver;

public class TransportReciverLocator {
    public static final String TRANSPORT_TCP = "tcp";
    public static final String TRANSPORT_HTTP = "http";

    public static TransportReciver locate(MessageContext msgContext)
        throws AxisFault {
        String type =
            (String) msgContext.getProperty(MessageContext.TRANSPORT_TYPE);
        if (TransportReciverLocator.TRANSPORT_HTTP.equals(type)) {
            return new HTTPTransportReciver();
        }else{
            throw new AxisFault("No tranport found");        
        }
    }
}
