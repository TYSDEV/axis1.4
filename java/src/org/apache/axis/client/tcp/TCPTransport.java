/*
 * The Apache Software License, Version 1.1
 *
 *
 * Copyright (c) 1999 The Apache Software Foundation.  All rights
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
 *    Apache Software Foundation (http://www.apache.org/)."
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

package org.apache.axis.client.tcp ;

import java.util.* ;
import org.apache.axis.* ;
import org.apache.axis.utils.Debug ;
import org.apache.axis.handlers.* ;
import org.apache.axis.registries.* ;
import org.apache.axis.client.Transport;
import org.apache.axis.client.AxisClient;
import org.apache.axis.client.ServiceClient;
import org.apache.axis.client.http.HTTPTransport; // UGLY!!!!!
import org.apache.axis.transport.tcp.TCPDispatchHandler;
import org.apache.axis.handlers.tcp.TCPActionHandler;

/**
 * Extends Client by implementing the setupMessageContext function to
 * set TCP-specific message context fields.  May not even be necessary
 * if we arrange things differently somehow.
 *
 * @author Rob Jellinghaus (robj@unrealities.com)
 * @author Doug Davis (dug@us.ibm.com)
 * @author Glen Daniels (gdaniels@allaire.com)
 */
public class TCPTransport extends Transport
{
    private Handler engine;
    
    static private boolean initedClient = false;
    
    private String host;
    private String port;
    
    public TCPTransport () {
    }
    
    public TCPTransport (String host, String port) {
        this.host = host;
        this.port = port;
    }
    
    /**
     * Find/load the registries and save them so we don't need to do this
     * each time we're called.
     */
    public void init(AxisEngine engine) {
        this.engine = engine;
        // Load the simple handler registry and init it
        Debug.Print( 1, "Enter: TCPTransport::init" );
        
        // add the TCPDispatchHandler
        HandlerRegistry hr = engine.getHandlerRegistry();
        hr.add("TCPSender", new TCPDispatchHandler());
        hr.add("TCPAction", new TCPActionHandler());
        
        SimpleChain c = new SimpleChain();
        c.addHandler( hr.find( "TCPAction" ) );
        hr.add( "TCP.input", c );
    }
    
    /**
     * TCP properties
     */
    static public String HOST = "tcp.host";
    static public String PORT = "tcp.port";
    
    /**
     * Initialize the given MessageContext with the correct handlers and registries.
     */
    public void initMessageContext (MessageContext mc, ServiceClient serv, AxisEngine engine, boolean doLocal)
    {
        HandlerRegistry sr = engine.getServiceRegistry();
        if ( sr == null || sr.find("TCP.input") == null )
            mc.setProperty( MessageContext.TRANS_INPUT, "TCPSender" );
        else
            mc.setProperty( MessageContext.TRANS_INPUT, "TCP.input" );
        mc.setProperty(MessageContext.TRANS_OUTPUT, "TCP.output" );
    }
        
    
    /**
     * Set up any transport-specific derived properties in the message context.
     * @param context the context to set up
     * @param message the client service instance
     * @param engine the engine containing the registries
     * @param doLocal if true, we are setting up for local testing
     */
    public void setupMessageContext (MessageContext mc, ServiceClient serv, AxisEngine engine, boolean doLocal)
    {
        // kind of ugly... fake up a "http://host:port/" url to send down the chain
        // ROBJ TODO: clean this up so we use TCP transport properties all the way down
        // use serviceclient properties if any, otherwise use ours
        if (host != null) serv.set(HOST, host);
        if (port != null) serv.set(PORT, port);
        String url = "http://"+serv.get(HOST)+":"+serv.get(PORT);
        serv.set(HTTPTransport.URL, url);
    }
}

