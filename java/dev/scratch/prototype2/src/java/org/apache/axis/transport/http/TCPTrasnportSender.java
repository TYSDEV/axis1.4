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

package org.apache.axis.transport.http;

import org.apache.axis.engine.AxisFault;
import org.apache.axis.engine.TransportSender;
import org.apache.axis.engine.context.MessageContext;
import org.apache.axis.handlers.AbstractHandler;
import org.apache.axis.om.OMEnvelope;

import java.io.OutputStream;

/**
 * @author Srinath Perera(hemapani@opensource.lk)
 */
public class TCPTrasnportSender extends AbstractHandler implements TransportSender{

    public void invoke(MessageContext msgContext) throws AxisFault {
        OutputStream out = (OutputStream)msgContext.getProperty(MessageContext.TRANSPORT_DATA);
        OMEnvelope envelope = msgContext.getEnvelope();
        //TODO write OM  
    }

    public void revoke(MessageContext msgContext) {

    }

}
