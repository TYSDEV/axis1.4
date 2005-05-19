/*
 * Copyright 2004,2005 The Apache Software Foundation.
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


import org.apache.axis.addressing.EndpointReference;
import org.apache.axis.engine.AxisFault;

/**
 * Class TransportReceiver
 */
public abstract class TransportReceiver{
    public abstract void start()throws AxisFault;
    public abstract void stop()throws AxisFault;
    public abstract EndpointReference replyToEPR(String serviceName)throws AxisFault ;
}
