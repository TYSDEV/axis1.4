/*
 * The Apache Software License, Version 1.1
 *
 *
 * Copyright (c) 2001 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "Axis" and "Apache Software Foundation" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache",
 *    nor may "Apache" appear in their name, without prior written
 *    permission of the Apache Software Foundation.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 */
package javax.xml.rpc;

import java.util.Iterator;

/**
 * Interface for Web Service stubs generated from WSDL. 
 */
public interface Stub {

    // Constants for the standard properties
    /**
     * User name for authentication.
     */
    public static final String USERNAME_PROPERTY = Call.USERNAME_PROPERTY;
    /**
     * Password for authentication.
     */
    public static final String PASSWORD_PROPERTY = Call.PASSWORD_PROPERTY;
    /**
     * Target service endpoint address.
     */
    public static final String ENDPOINT_ADDRESS_PROPERTY =
            "javax.xml.rpc.service.endpoint.address";
    /**
     * This boolean property is used by a service client to indicate
     * whether or not it wants to participate in a session with a service
     * endpoint.
     */
    public static final String SESSION_MAINTAIN_PROPERTY = Call.SESSION_MAINTAIN_PROPERTY;

    /**
     * Sets the value for a named property. JAX-RPC 1.0 specification 
     * specifies a standard set of properties that may be passed 
     * to the Stub._setProperty method. These properties include:
     * <UL>
     * <LI>javax.xml.rpc.security.auth.username: Username for the HTTP Basic Authentication
     * <LI>javax.xml.rpc.security.auth.password: Password for the HTTP Basic Authentication
     * <LI>javax.xml.rpc.service.endpoint.address: Target service endpoint address.
     * <LI>[TBD: Additional properties]
     * </UL>
     *
     * @param name - Name of the property
     * @param value - Value of the property
     */
    public void _setProperty(String name, Object value);

    /**
     * Gets the value of a named property.
     *
     * @param name
     *
     * @return the value of a named property.
     */
    public Object _getProperty(String name);

    /**
     * Return the names of configurable properties for this stub class.
     */
    public Iterator _getPropertyNames();

} // interface Stub


