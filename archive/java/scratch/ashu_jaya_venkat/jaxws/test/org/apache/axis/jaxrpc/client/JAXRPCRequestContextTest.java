package org.apache.axis.jaxrpc.client;

import junit.framework.TestCase;
import org.apache.axis.jaxrpc.client.JAXRPCRequestContext;

public class JAXRPCRequestContextTest extends TestCase
{
  org.apache.axis.jaxrpc.client.JAXRPCRequestContext jaxrpcrequestcontext = null;
  
  public JAXRPCRequestContextTest(String name) {
    super(name);
  }
  
  public org.apache.axis.jaxrpc.client.JAXRPCRequestContext createInstance() throws Exception {
    return new org.apache.axis.jaxrpc.client.JAXRPCRequestContext(null);
  }
  
  protected void setUp() throws Exception {
    super.setUp();
    jaxrpcrequestcontext = createInstance();
  }
  
  protected void tearDown() throws Exception {
    jaxrpcrequestcontext = null;
    super.tearDown();
  }
    
  public static void main(String[] args) {
    junit.textui.TestRunner.run(JAXRPCRequestContextTest.class);
  }
}
