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
package org.apache.ws.axis.security;

import org.apache.ws.axis.security.WSDoAllConstants;

/**
 * Defines the Constants used or WS-J2EE security Mapping implementation.
 *
 * @author Rajith Priyanga (rpriyanga@yahoo.com)
 * @date May 28, 2004
 */
public class WSS4J2EEConstants extends WSDoAllConstants {

    /**
     * This property contains the security information required to authenticate
     * the user to the J2EE server, plus some more information. Represents
     * a SecurityContext4J2EE object.
     */
    public static final String SEC_CONTEXT_4J2EE = "SEC_CONTEXT_4J2EE";

    public static final String AUTH_AT_AXIS = "AuthenticationAtAxis";
}
