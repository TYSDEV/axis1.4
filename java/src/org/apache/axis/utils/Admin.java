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

package org.apache.axis.utils ;

import java.io.* ;
import java.util.* ;
import org.apache.axis.* ;
import org.apache.axis.registries.* ;
import org.apache.axis.handlers.* ;
import org.apache.axis.handlers.soap.SOAPService;
import org.apache.axis.utils.* ;
import org.apache.axis.suppliers.*;
import org.apache.axis.encoding.*;

import org.w3c.dom.* ;

/**
 *
 * @author Doug Davis (dug@us.ibm.com)
 */
public class Admin {
  private static  DefaultHandlerRegistry  hr = null ;
  private static  DefaultServiceRegistry  sr = null ;
  private static  TypeMappingRegistry     tmr = null ;
  private boolean onServer = true ;
    

    /**
     * Hacky tester function.  Wipe the statics so we can invoke this class
     * multiple times within the same JVM.  For functional testing purposes.
     * See test.functional.TestTransportSample
     */
    public static void wipe () {
        hr = null;
        sr = null;
        tmr = null;
    }

  private void init() {
    if ( hr == null ) {
      if ( onServer )
        hr = new DefaultHandlerRegistry(Constants.SERVER_HANDLER_REGISTRY);
      else
        hr = new DefaultHandlerRegistry(Constants.CLIENT_HANDLER_REGISTRY);
      hr.setOnServer( onServer );
      hr.init();
    }
    if ( sr == null ) {
      if ( onServer )
        sr = new DefaultServiceRegistry(Constants.SERVER_SERVICE_REGISTRY);
      else
        sr = new DefaultServiceRegistry(Constants.CLIENT_SERVICE_REGISTRY);
      sr.setOnServer( onServer );
      sr.init();
    }
  }

  private void getOptions(Element root, Handler handler) {
    NodeList  list = root.getChildNodes();
    for ( int i = 0 ; list != null && i < list.getLength() ; i++ ) {
      Node    node  = list.item(i);
      if ( node.getNodeType() != Node.ELEMENT_NODE ) continue ;
      Element elem  = (Element) node ;
      if ( !"option".equals(elem.getTagName()) ) continue ;
      String  name  = elem.getAttribute( "name" );
      String  value = elem.getAttribute( "value" );

      if ( name != null && value != null )
        handler.addOption( name, value );
    }
  }
  
  private void registerTypeMappings(Element root, SOAPService service)
    throws Exception
  {
    NodeList list = root.getElementsByTagName("bean");
    Debug.Print(1, "Registering " + list.getLength() + " service-specific types.");
    for (int i = 0; list != null && i < list.getLength(); i++) {
      Element el = (Element)list.item(i);
      registerTypeMapping(el, service.getTypeMappingRegistry());
    }
  }

  public Document AdminService(MessageContext msgContext, Document xml)
                  throws AxisFault
  {
    Debug.Print( 1, "Enter: Admin:AdminService" );
    AxisEngine engine =  msgContext.getAxisEngine();
    hr = (DefaultHandlerRegistry)engine.getHandlerRegistry();
    sr = (DefaultServiceRegistry)engine.getServiceRegistry();
    tmr = msgContext.getTypeMappingRegistry();
    Document doc = process( msgContext, xml );
    Debug.Print( 1, "Exit: Admin:AdminService" );
    return( doc );
  }

  public Document process(MessageContext msgContext, Document doc) throws AxisFault {
    return( process( msgContext, doc.getDocumentElement() ) );
  }

  public Document process(MessageContext msgContext, Element root) throws AxisFault {
    Document doc = null ;
    try {
      init();
      AxisClassLoader   cl     = AxisClassLoader.getClassLoader();
      String            action = root.getTagName();

      if ( !action.equals("deploy") && !action.equals("undeploy") &&
           !action.equals("list") && !action.equals("quit") )
        throw new AxisFault( "Admin.error",
                             "Root element must be 'deploy', 'undeploy' " +
                             "or 'list'",
                             null, null );

        if (action.equals("quit")) {
            System.err.println("Admin service requested to quit, quitting.");
            if (msgContext != null) {
                // put a flag into message context so listener will exit after
                // sending response
                msgContext.setProperty(msgContext.QUIT_REQUESTED, "true");
            }
              doc = XMLUtils.newDocument();
              doc.appendChild( root = doc.createElement( "Admin" ) );
              root.appendChild( doc.createTextNode( "Quitting" ) );
            return doc;
        }
        
      if ( action.equals("list") ) {
        String[]   names ;
        Handler    h ;
        int        i, j ;
        HandlerRegistry registry = hr;

        doc = XMLUtils.newDocument();
        root = doc.createElement( "Admin" );
        doc.appendChild( root );

        Element    elem = null ;
        Hashtable  opts = null ;

        /* Process Handlers first */
        /**************************/
        for ( int loop = 0 ; loop < 2 ; loop++ ) {
          if ( loop == 1 )
            registry = sr;
          
          names = registry.list();

          for( i = 0 ; i < names.length ; i++ ) {
            h = registry.find(names[i]);
            if (h == null)
              throw new AxisFault("Server", "Couldn't find registered handler '" + names[i] + "'", null, null);
            elem = h.getDeploymentData(doc);

            if ( elem == null ) continue ;

            if ( loop == 1 ) {
              Element tmpElem = doc.createElement( "service" );
              NodeList list = elem.getChildNodes();
              for ( int ii = 0 ; ii < list.getLength() ; ii++ ) {
                // Not the cleanest way to do this, but this code will be
                // replaced anyhow...
                if (h instanceof SOAPService) {
                  tmpElem.setAttribute(SOAPService.OPTION_PIVOT, (String)h.getOption("pivot"));
                }
                
                
                tmpElem.appendChild( doc.importNode(list.item(ii),true) );
              }
              elem = tmpElem ;
            }

            if ( elem.getTagName().equals("chain") )
              elem.removeAttribute( "class" );
        
            elem.setAttribute( "name", names[i] );
            root.appendChild( doc.importNode(elem,true) );
          }
        }

        return( doc );
      }
  
      NodeList list = root.getChildNodes();
      for ( int loop = 0 ; loop < list.getLength() ; loop++ ) {
        Node     node    = list.item(loop);

        if ( node.getNodeType() != Node.ELEMENT_NODE ) continue ;

        Element  elem    = (Element) node ;
        String   type    = elem.getTagName();
        String   name    = elem.getAttribute( "name" );

        if ( name != null && name.equals("") ) name = null ;
  
        if ( action.equals( "undeploy" ) ) {
          if ( type.equals("service") ) {
            Debug.Print( 2, "Undeploying " + type + ": " + name );
            sr.remove( name );
          }
          else if ( type.equals("handler") || type.equals("chain") ) {
            Debug.Print( 2, "Undeploying " + type + ": " + name );
            hr.remove( name );
          }
          else
            throw new AxisFault( "Admin.error",
                                 "Unknown type; " + type,
                                 null, null );
          continue ;
        }
  
        Handler  h       = null ;
        String   hName ;
        Handler  tmpH ;
        String   flow    = elem.getAttribute( "flow" );
        String   input   = elem.getAttribute( "input" );
        String   pivot   = elem.getAttribute( "pivot" );
        String   output  = elem.getAttribute( "output" );

        if ( flow   != null && flow.equals("") )   flow = null ;
        if ( input  != null && input.equals("") )  input = null ;
        if ( output != null && output.equals("") ) output = null ;
        if ( pivot  != null && pivot.equals("") )  pivot = null ;
 
  
        if ( type.equals( "handler" ) ) {
          String   cls   = elem.getAttribute( "class" );
          if ( cls != null && cls.equals("") ) cls = null ;
          Debug.Print( 2, "Deploying handler: " + name );
          
          if (hr instanceof SupplierRegistry) {
            String lifeCycle = elem.getAttribute("lifecycle");
            Supplier supplier;

            if ("factory".equals(lifeCycle)) {
              supplier = new FactorySupplier(cl.loadClass(cls), new Hashtable());
            } else if ("static".equals(lifeCycle)) {
              h = (Handler) cl.loadClass(cls).newInstance();
              getOptions( elem, h );
              supplier = new SimpleSupplier( h );
            } else {
              // Default to static for now
              h = (Handler) cl.loadClass(cls).newInstance();
              getOptions( elem, h );
              supplier = new SimpleSupplier( h );
            }
            
            ((SupplierRegistry)hr).add(name, supplier);
          } else {
            h = hr.find( name );
            if ( h == null ) h = (Handler) cl.loadClass(cls).newInstance();
            getOptions( elem, h );
            hr.add( name, h );
          }
        }
        else if ( type.equals( "chain" ) ) {
          if ( flow != null && flow.length() > 0 ) {
            Debug.Print( 2, "Deploying chain: " + name );
            Chain    c       = (Chain) hr.find( name );

            if ( c == null ) c = new SimpleChain();
            else             c.clear();

            StringTokenizer st = new StringTokenizer( flow, " \t\n\r\f," );
            while ( st.hasMoreElements() ) {
              hName = st.nextToken();
              tmpH = hr.find( hName );
              if ( tmpH == null )
                throw new AxisFault( "Admin.error",
                                     "Unknown handler: " + hName,
                                     null, null );
              c.addHandler( tmpH );
            }
            getOptions( elem, c );
            hr.add( name, c );
          }
          else {
            Debug.Print( 2, "Deploying chain: " + name );
            StringTokenizer      st = null ;
            SimpleTargetedChain  cc = null ;
            Chain                c  = null ;

            cc = (SimpleTargetedChain) hr.find( name );

            if ( cc == null ) cc = new SimpleTargetedChain();
            else              cc.clear();
  
            st = new StringTokenizer( input, " \t\n\r\f," );
            while ( st.hasMoreElements() ) {
              if ( c == null )
                cc.setInputChain( c = new SimpleChain() );
              hName = st.nextToken();
              tmpH = hr.find( hName );
              if ( tmpH == null )
                throw new AxisFault( "Admin.error",
                                     "Unknown handler: " + hName,
                                     null, null );
              c.addHandler( tmpH );
            }
          
            cc.setPivotHandler( hr.find( pivot ) );
  
            st = new StringTokenizer( output, " \t\n\r\f," );
            c  = null ;
            while ( st.hasMoreElements() ) {
              if ( c == null )
                cc.setOutputChain( c = new SimpleChain() );
              hName = st.nextToken();
              tmpH = hr.find( hName );
              if ( tmpH == null )
                throw new AxisFault( "Admin.error",
                                     "Unknown handler: " + hName,
                                     null, null );
              c.addHandler( tmpH );
            }
            getOptions( elem, cc );
            hr.add( name, cc );
          }
        }
        else if ( type.equals( "service" ) ) {
          Debug.Print( 2, "Deploying service: " + name );
          StringTokenizer      st = null ;
          SOAPService     service = null ;
          Chain                c  = null ;

          if ( pivot == null && input == null && output == null )
            throw new AxisFault( "Admin.error",
                                 "Services must use targeted chains",
                                 null, null );

          service = (SOAPService) hr.find( name );

          if ( service == null ) service = new SOAPService();
          else              service.clear();
          
          if (tmr != null)
            service.getTypeMappingRegistry().setParent(tmr.getParent());

          if ( input != null && !"".equals(input) ) {
            st = new StringTokenizer( input, " \t\n\r\f," );
            c  = null ;
            while ( st.hasMoreElements() ) {
              if ( c == null )
                service.setInputChain( c = new SimpleChain() );
              hName = st.nextToken();
              tmpH = hr.find( hName );
              if ( tmpH == null )
                throw new AxisFault( "Admin.error",
                                     "Unknown handler: " + hName,
                                     null, null );
              c.addHandler( tmpH );
            }
          }
          
          if ( pivot != null && !"".equals(pivot) ) {
            service.setPivotHandler( hr.find( pivot ) );
            // Save pivot name so we can list it later.
            service.addOption("pivot", pivot);
          }
  
          if ( output != null && !"".equals(output) ) {
            st = new StringTokenizer( output, " \t\n\r\f," );
            c  = null ;
            while ( st.hasMoreElements() ) {
              if ( c == null )
                service.setOutputChain( c = new SimpleChain() );
              hName = st.nextToken();
              tmpH = hr.find( hName );
              if ( tmpH == null )
                throw new AxisFault( "Admin.error",
                                     "Unknown handler: " + hName,
                                     null, null );
              c.addHandler( tmpH );
            }
          }
          getOptions( elem, service );
          registerTypeMappings(elem, service);
  
          hr.add( name, service ); // ???
          sr.add( name, service );
        }

        // A streamlined means of deploying both a serializer and a deserializer
        // for a bean at the same time.
        else if ( type.equals( "bean" ) ) {
          Debug.Print( 2, "Deploying bean: " + name );
          registerTypeMapping(elem, tmr.getParent());
        } else
          throw new AxisFault( "Admin.error",
                               "Unknown type to " + action + ": " + type,
                               null, null );
      }
      doc = XMLUtils.newDocument();
      doc.appendChild( root = doc.createElement( "Admin" ) );
      root.appendChild( doc.createTextNode( "Done processing" ) );
    }
    catch( Exception e ) {
      e.printStackTrace();
      if ( !(e instanceof AxisFault) ) e = new AxisFault( e );
      throw (AxisFault) e ;
    }
    return( doc );
  }

  private void registerTypeMapping(Element root, TypeMappingRegistry map)
    throws Exception
  {
    NodeList  list = root.getChildNodes();
    for ( int i = 0 ; list != null && i < list.getLength() ; i++ ) {
      Node    node  = list.item(i);
      if ( node.getNodeType() != Node.ELEMENT_NODE ) continue ;
      Element elem  = (Element) node ;

      // Retrieve classname attribute

      String classname = elem.getAttribute("classname");
      if ((classname == null) || classname.equals(""))
        throw new AxisFault("Server.Admin.error",
                            "No classname attribute in bean mapping",
                            null, null);
    
      // Resolve class name

      Class cls;
      try {
        cls = Class.forName(classname);
      } catch (Exception e) {
        throw new AxisFault( "Admin.error", e.toString(), null, null);
      }

      // Resolve qname based on prefix and localpart

      String namespaceURI = elem.getNamespaceURI();
      String localName    = elem.getLocalName();
      QName qn = new QName(namespaceURI, localName);

      // register both serializers and deserializers for this bean

      map.addSerializer(cls, qn, new BeanSerializer(cls));
      map.addDeserializerFactory(qn, cls, BeanSerializer.getFactory(cls));
    }
  }
  
  public static void main(String args[]) throws Exception {
    int  i = 0 ;

    if ( args.length < 2 || !(args[0].equals("client") ||
                             args[0].equals("server")) ) {
      System.err.println( "Usage: Admin client|server <xml-file>\n" );

      System.err.println( "Where <xml-file> looks like:" );
      System.err.println( "<deploy>" );
      /*
      System.err.println( "  <transport name=a input=\"a,b,c\" sender=\"s\"");
      System.err.println( "                    output=\"d,e\"/>" );
      */
      System.err.println( "  <handler name=a class=className/>" );
      System.err.println( "  <chain name=a flow=\"a,b,c\" />" );
      System.err.println( "  <chain name=a input=\"a,b,c\" pivot=\"d\"" );
      System.err.println( "                  output=\"e,f,g\" />" );
      System.err.println( "  <service name=a handler=b />" );
      System.err.println( "</deploy>" );
      System.err.println( "<undeploy>" );
      System.err.println( "  <handler name=a/>" );
      System.err.println( "  <chain name=a/>" );
      System.err.println( "  <service name=a/>" );
      System.err.println( "</undeploy>\n" );
      System.err.println( "<list/>\n" );


      // throw an Exception which will go uncaught!  this way, a test suite
      // can invoke main() and detect the exception
      throw new IllegalArgumentException();
      // System.exit( 1 );
    }

    Admin admin = new Admin();

    if ( args[0].equals("client") ) admin.onServer = false ;

    try {
      for ( i = 1 ; i < args.length ; i++ ) {
        System.out.println( "Processing '" + args[i] + "'" );
        admin.process(null, XMLUtils.newDocument( new FileInputStream( args[i] ) ));
      }
    }
    catch( AxisFault e ) {
      e.dump();
      //System.exit(1);
        throw e;
    }
    catch( Exception e ) {
      System.err.println( "Error processing '" + args[i] + "'" );
      e.printStackTrace( System.err );
      //System.exit( 1 );
        throw e;
    }
  }
}
