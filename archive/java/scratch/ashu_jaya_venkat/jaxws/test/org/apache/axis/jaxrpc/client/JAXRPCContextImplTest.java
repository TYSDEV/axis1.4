package org.apache.axis.jaxrpc.client;

import junit.framework.TestCase;
import org.apache.axis.jaxrpc.client.JAXRPCContextImpl;

public class JAXRPCContextImplTest extends TestCase
{
  org.apache.axis.jaxrpc.client.JAXRPCContextImpl jaxrpccontextimpl = null;
  
  public JAXRPCContextImplTest(String name) {
    super(name);
  }
  
  public org.apache.axis.jaxrpc.client.JAXRPCContextImpl createInstance() throws Exception {
    return new org.apache.axis.jaxrpc.client.JAXRPCContextImpl();
  }
  
  protected void setUp() throws Exception {
    super.setUp();
    jaxrpccontextimpl = createInstance();
  }
  
  protected void tearDown() throws Exception {
    jaxrpccontextimpl = null;
    super.tearDown();
  }
  
  public void testSetProperty() throws Exception {
  }
  
  public void testRemoveProperty() throws Exception {
  }
  
  public void testGetProperty() throws Exception {
  }
  
  public void testGetPropertyNames() throws Exception {
  }
  
  public void testGetAxis2Engine() throws Exception {
  }
    
  public static void main(String[] args) {
    junit.textui.TestRunner.run(JAXRPCContextImplTest.class);
  }
}
