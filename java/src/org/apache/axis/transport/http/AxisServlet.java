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

package org.apache.axis.transport.http ;

import org.apache.axis.AxisEngine;
import org.apache.axis.AxisFault;
import org.apache.axis.Constants;
import org.apache.axis.Message;
import org.apache.axis.MessageContext;
import org.apache.axis.configuration.FileProvider;
import org.apache.axis.message.SOAPEnvelope;
import org.apache.axis.message.SOAPFaultElement;
import org.apache.axis.registries.HandlerRegistry;
import org.apache.axis.security.servlet.ServletSecurityProvider;
import org.apache.axis.server.AxisServer;
import org.apache.axis.utils.Admin;
import org.apache.axis.utils.XMLUtils;
import org.w3c.dom.Document;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 *
 * @author Doug Davis (dug@us.ibm.com)
 */
public class AxisServlet extends HttpServlet {
    // These have default values.
    private String transportName = "http";
    private AxisEngine engine = null;
    private ServletSecurityProvider securityProvider = null;

    private static final String AXIS_ENGINE = "AxisEngine" ;

    public void init() {
        String param = getInitParameter("transport.name");
        ServletContext context = getServletConfig().getServletContext();

        if (param == null)
            param = context.getInitParameter("transport.name");
        if (param != null)
            transportName = param;

        param = getInitParameter("use-servlet-security");
        if ((param != null) && (param.equalsIgnoreCase("true"))) {
            securityProvider = new ServletSecurityProvider();
        }
    }

    public AxisServer getEngine() {
        if (getServletContext().getAttribute("AxisEngine") == null) {
            // Set the base path for the AxisServer to our WEB-INF directory
            // (so the config files can't get snooped by a browser)
            FileProvider provider =
                    new FileProvider(getServletContext().getRealPath("/WEB-INF"),
                                     "server-config.xml");

            getServletContext().setAttribute("AxisEngine", new AxisServer(provider));
        }
        return (AxisServer)getServletContext().getAttribute("AxisEngine");
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {
        if (engine == null)
            engine = getEngine();

        ServletContext context = getServletConfig().getServletContext();
        MessageContext msgContext = new MessageContext(engine);
        HandlerRegistry hr = engine.getHandlerRegistry();

        String realpath = context.getRealPath(req.getServletPath());
        String configPath = context.getRealPath("/WEB-INF");
        if (realpath != null) {
            msgContext.setProperty(Constants.MC_REALPATH, realpath);
            msgContext.setProperty(Constants.MC_CONFIGPATH, configPath);

            /* Set the Transport */
            /*********************/
            msgContext.setTransportName(transportName);

            /* Save some HTTP specific info in the bag in case a handler needs it */
            /**********************************************************************/
            msgContext.setProperty(HTTPConstants.MC_HTTP_SERVLET, this );
            msgContext.setProperty(HTTPConstants.MC_HTTP_SERVLETREQUEST, req );
            msgContext.setProperty(HTTPConstants.MC_HTTP_SERVLETRESPONSE, res );
            msgContext.setProperty(Constants.MC_REMOTE_ADDR, req.getRemoteAddr());

            try {
                String url = req.getScheme() + "://" +
                        req.getServerName() + ":" +
                        req.getServerPort() + req.getRequestURI();

                msgContext.setProperty(MessageContext.TRANS_URL, url);

                boolean wsdlRequested = false;
                boolean listRequested = false;

                String queryString = req.getQueryString();
                if (queryString != null) {
                    if (queryString.equalsIgnoreCase("wsdl")) {
                        wsdlRequested = true;
                    } else if (queryString.equalsIgnoreCase("list")) {
                        listRequested = true;
                    }
                }

                if (wsdlRequested) {
                    engine.generateWSDL(msgContext);
                    Document doc = (Document) msgContext.getProperty("WSDL");
                    if (doc != null) {
                        res.setContentType("text/xml");
                        XMLUtils.DocumentToWriter(doc, res.getWriter());
                        res.getWriter().close();
                    }
                } else if (listRequested) {
                    Document doc = Admin.listConfig(engine);
                    if (doc != null) {
                        res.setContentType("text/xml");
                        XMLUtils.DocumentToWriter(doc, res.getWriter());
                        res.getWriter().close();
                    }
                } else if (req.getParameterNames().hasMoreElements()) {
                    Enumeration enum = req.getParameterNames();
                    PrintWriter writer = res.getWriter();
                    String method = null;
                    String args = "";
                    while (enum.hasMoreElements()) {
                        String param = (String) enum.nextElement();
                        if (param.equalsIgnoreCase("method")) {
                            method = req.getParameter(param);
                        } else {
                            args += "<" + param + ">" +
                                    req.getParameter(param) +
                                    "</" + param + ">";
                        }
                    }
                    if (method == null) {
                        writer.println("<p>No method!</p>");
                        writer.close();
                        return;
                    }
                    String body = "<" + method + ">" + args +
                                  "</" + method + ">";
                    String msgtxt = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
                                 "<SOAP-ENV:Body>" + body + "</SOAP-ENV:Body>" +
                                 "</SOAP-ENV:Envelope>";
                    ByteArrayInputStream istream = new ByteArrayInputStream(
                        msgtxt.getBytes());
                    Message msg = new Message(istream, false);
                    msgContext.setRequestMessage(msg);
//                    if (msg != null) {
//                        writer.println(msg.getAsString());
//                        return;
//                    }
                    engine.invoke(msgContext);
                    Message respMsg = msgContext.getResponseMessage();
                    if (respMsg != null) {
                        writer.println("<p>Got response message:</p>");
                        writer.println(respMsg.getAsString());
                        writer.close();
                    } else {
                        writer.println("<p>No response message!</p>");
                        writer.close();
                    }
                    return;
                } else {
                    res.setContentType("text/html");
                    res.getWriter().println("<h1>" + req.getRequestURI() +
                            "</h1>");
                    res.getWriter().println(configPath);
                    res.getWriter().println(
                            "<p>Hi there, this is an Axis service!</p>");
                    res.getWriter().println(
                            "<i>Perhaps there'll be a form for invoking the service here...</i>");
                    res.getWriter().close();
                    return;
                }
            } catch (AxisFault fault) {
                res.getWriter().println("<pre>Fault - " + fault + " </pre>");
            } catch (Exception e) {
                  res.getWriter().println("<pre>Exception - " + e + "<br>");
                  e.printStackTrace(res.getWriter());
                  res.getWriter().println("</pre>");
            }
        }

        res.setContentType("text/html");
        res.getWriter().println( "<html><h1>Axis HTTP Servlet</h1>" );
        res.getWriter().println( "Hi, you've reached the Axis HTTP servlet." +
           "Normally you would be hitting this URL with a SOAP client " +
           "rather than a browser.");

        res.getWriter().println("<p>In case you're interested, my Axis " +
            "transport name appears to be '<b>" + transportName + "</b>'");
        res.getWriter().println("</html>");
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {
        if (engine == null)
            engine = getEngine();

        ServletConfig  config  = getServletConfig();
        ServletContext context = config.getServletContext();
        HttpSession    session = req.getSession();

        if (engine == null)
            engine = (AxisEngine)context.getAttribute(AXIS_ENGINE);

        if (engine == null) {
            // !!! should return a SOAP fault...
            throw new ServletException("Couldn't find AxisEngine!");
        }

        /* Place the Request message in the MessagContext object - notice */
        /* that we just leave it as a 'ServletRequest' object and let the  */
        /* Message processing routine convert it - we don't do it since we */
        /* don't know how it's going to be used - perhaps it might not     */
        /* even need to be parsed.                                         */
        /*******************************************************************/
        MessageContext    msgContext = new MessageContext(engine);
        InputStream       inp        = req.getInputStream();
        Message           msg        = new Message( inp );

        /* Set the request(incoming) message field in the context */
        /**********************************************************/
        msgContext.setRequestMessage( msg );

        /* Set the Transport */
        /*********************/
        msgContext.setTransportName(transportName);

        /* Save some HTTP specific info in the bag in case a handler needs it */
        /**********************************************************************/
        msgContext.setProperty(HTTPConstants.MC_HTTP_SERVLET, this );
        msgContext.setProperty(HTTPConstants.MC_HTTP_SERVLETREQUEST, req );
        msgContext.setProperty(HTTPConstants.MC_HTTP_SERVLETRESPONSE, res );
        msgContext.setProperty(Constants.MC_REMOTE_ADDR, req.getRemoteAddr());

        if (securityProvider != null)
            msgContext.setProperty("securityProvider", securityProvider);

        /* Save the SOAPAction header in the MessageContext bag - this will */
        /* be used to tell the Axis Engine which service is being invoked.  */
        /* This will save us the trouble of having to parse the Request     */
        /* message - although we will need to double-check later on that    */
        /* the SOAPAction header does in fact match the URI in the body.    */
        /* (is this last stmt true??? (I don't think so - Glen))            */
        /* if SOAPAction is "" then use the URL                             */
        /* if SOAPAction is null then we'll we be forced to scan the body   */
        /*   for it.                                                        */
        /********************************************************************/
        String  tmp ;
        tmp = (String) req.getHeader( HTTPConstants.HEADER_SOAP_ACTION );

        try {
            /** Technically, if we don't find this header, we should probably fault.
            * It's required in the SOAP HTTP binding.
            */
            if ( tmp == null ) {
                throw new AxisFault( "Client.NoSOAPAction",
                    "No SOAPAction header!",
                    null, null );
            }

            if ( "".equals(tmp) )
                tmp = req.getContextPath(); // Is this right?

            if ( tmp != null )
                msgContext.setProperty( HTTPConstants.MC_HTTP_SOAPACTION, tmp );

            // Create a Session wrapper for the HTTP session.
            // These can/should be pooled at some point.  (Sam is Watching! :-)
            msgContext.setSession(new AxisHttpSession(req.getSession()));

            /* Save the real path */
            /**********************/
            String realpath = context.getRealPath(req.getServletPath());
            if (realpath != null)
                msgContext.setProperty(Constants.MC_REALPATH, realpath);
            String configPath = context.getRealPath("/WEB-INF");
            msgContext.setProperty(Constants.MC_CONFIGPATH, configPath);

            /* Invoke the Axis engine... */
            /*****************************/
            engine.invoke( msgContext );
        }
        catch( Exception e ) {
            if ( e instanceof AxisFault ) {
                AxisFault  af = (AxisFault) e ;
                if ( "Server.Unauthorized".equals( af.getFaultCode() ) )
                    res.setStatus( HttpServletResponse.SC_UNAUTHORIZED );
                else
                    res.setStatus( HttpServletResponse.SC_INTERNAL_SERVER_ERROR );
                // It's been suggested that a lack of SOAPAction should produce some
                // other error code (in the 400s)...
            }
            else
                res.setStatus( HttpServletResponse.SC_INTERNAL_SERVER_ERROR );
            if ( !(e instanceof AxisFault) )
                e = new AxisFault( e );
            msg = msgContext.getResponseMessage();
            if (msg == null) {
                msg = new Message((AxisFault)e);
                msgContext.setResponseMessage(msg);
            } else {
                try {
                    SOAPEnvelope env = msg.getAsSOAPEnvelope();
                    env.clearBody();
                    env.addBodyElement(new SOAPFaultElement((AxisFault)e));
                } catch (AxisFault af) {
                    // Should never reach here!
                }
            }
        }

        /* Send it back along the wire...  */
        /***********************************/
        msg = msgContext.getResponseMessage();
        res.setContentType( "text/xml; charset=utf-8" );
        String response;
        if (msg == null) {
            response="No data";
        } else {
            response = (String)msg.getAsString();
        }
        res.setContentLength( response.getBytes().length );
        res.getWriter().print( response );
    }
}
