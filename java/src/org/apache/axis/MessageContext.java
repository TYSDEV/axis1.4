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

package org.apache.axis ;

import java.util.* ;
import org.apache.axis.* ;
import org.apache.axis.utils.AxisClassLoader ;

/**
 * Some more general docs will go here.
 *
 * This class also contains constants for accessing some
 * well-known properties. Using a hierarchical namespace is
 * strongly suggested in order to lower the chance for
 * conflicts.
 *
 * (These constants should be viewed as an explicit list of well
 *  known and widely used context keys, there's nothing wrong
 *  with directly using the key strings. This is the reason for
 *  the hierarchical constant namespace.
 *
 *  Actually I think we might just list the keys in the docs and
 *  provide no such constants since they create yet another
 *  namespace, but we'd have no compile-time checks then. 
 *
 *  Whaddya think? - todo by Jacek)
 *
 *
 * @author Doug Davis (dug@us.ibm.com)
 * @author Jacek Kopecky (jacek@idoox.com)
 */
public class MessageContext {
    /**
     * Just a placeholder until we figure out how many messages we'll actually
     * be passing around.
     */
    private Message inMessage ;

    /**
     * Just a placeholder until we figure out how many messages we'll actually
     * be passing around.
     */
    private Message outMessage ;

    /**
     * That unique key/name that the next router/dispatch handler should use
     * to determine what to do next.
     */
    private String           targetService ;

    /**
     * The default classloader that this service should use
     */
    private AxisClassLoader  classLoader ;

    /**
     * 
     */
    private Hashtable bag ;

    public MessageContext() {}

    public MessageContext( Message inMsg ) {
        setRequestMessage( inMsg );
    }

    /**
     * Placeholder.
     */
    public Message getRequestMessage() { 
        return inMessage ; 
    };

    /**
     * Placeholder.
     */
    public void setRequestMessage(Message inMsg) { 
        inMessage = inMsg ; 
    };

    /**
     * Placeholder.
     */
    public Message getResponseMessage() { return outMessage ; }

    /**
     * Placeholder.
     */
    public void setResponseMessage(Message inMsg) { 
        outMessage = inMsg ;
    };

    public AxisClassLoader getClassLoader() {
      if ( classLoader == null ) 
        classLoader = AxisClassLoader.getClassLoader(null);
      return( classLoader );
    }

    public AxisClassLoader getClassLoader(String name) {
      if ( name == null ) return( getClassLoader() );
      return( AxisClassLoader.getClassLoader(name) );
    }

    public void setClassLoader(AxisClassLoader cl ) {
      classLoader = cl ;
    }

    public String getTargetService() {
      return( targetService );
    }

    public void setTargetService(String tServ) {
      targetService = tServ ;
    }

    /** Contains an instance of Handler, which is the
     *  ServiceContext and the entrypoint of this service.
     *
     *  (if it has been so configured - will our deployment
     *   tool do this by default?  - todo by Jacek)
     */
    public static String SVC_HANDLER         = "service.handler";

    public static String ENGINE_HANDLER      = "engine.handler";

    /** This String is the URL that the message came to
     */
    public static String TRANS_URL           = "transport.url";

    /** The transport specific input/output chains  */
    public static String TRANS_INPUT         = "transport.input";
    public static String TRANS_OUTPUT        = "transport.output";

    /** The protocol specific handler (ie. SOAP) */
    public static String PROTOCOL_HANDLER    = "procotol.handler";

    /** A String with the user's ID (if available)
     */
    public static String USERID              = "user.id";

    /** A String with the user's password (if available)
     */
    public static String PASSWORD            = "user.password";

    /** Just a util so we don't have to cast the result 
     */
    public String getStrProp(String propName) {
      return( (String) getProperty(propName) );
    }

    public Object getProperty(String propName) {
        if ( bag == null ) return( null );
        return( bag.get(propName) );
    }

    public void setProperty(String propName, Object propValue) {
        if ( bag == null ) bag = new Hashtable() ;
        bag.put( propName, propValue );
    }
    
    public void clearProperty(String propName)
    {
        if (bag != null) {
            bag.remove(propName);
        }
    }

};
