package org.apache.axis.jaxrpc.client;

import junit.framework.TestCase;
import org.apache.axis.jaxrpc.client.JAXRPCResponseContext;

public class JAXRPCResponseContextTest extends TestCase
{
  org.apache.axis.jaxrpc.client.JAXRPCResponseContext jaxrpcresponsecontext = null;
  
  public JAXRPCResponseContextTest(String name) {
    super(name);
  }
  
  public org.apache.axis.jaxrpc.client.JAXRPCResponseContext createInstance() throws Exception {
    return new org.apache.axis.jaxrpc.client.JAXRPCResponseContext(null);
  }
  
  protected void setUp() throws Exception {
    super.setUp();
    jaxrpcresponsecontext = createInstance();
  }
  
  protected void tearDown() throws Exception {
    jaxrpcresponsecontext = null;
    super.tearDown();
  }
    
  public static void main(String[] args) {
    junit.textui.TestRunner.run(JAXRPCResponseContextTest.class);
  }
}
