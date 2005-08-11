package org.apache.axis.jaxrpc.client;

import junit.framework.TestCase;
import org.apache.axis.jaxrpc.client.DispatchImpl;

public class DispatchImplTest extends TestCase
{
  org.apache.axis.jaxrpc.client.DispatchImpl dispatchimpl = null;
  
  public DispatchImplTest(String name) {
    super(name);
  }
  
  public org.apache.axis.jaxrpc.client.DispatchImpl createInstance() throws Exception {
    return new org.apache.axis.jaxrpc.client.DispatchImpl();
  }
  
  protected void setUp() throws Exception {
    super.setUp();
    dispatchimpl = createInstance();
  }
  
  protected void tearDown() throws Exception {
    dispatchimpl = null;
    super.tearDown();
  }
  
  public void testInvoke() throws Exception {
  }
  
  public void testInvokeAsync() throws Exception {
  }
  
  public void testInvokeOneWay() throws Exception {
  }
    
  public static void main(String[] args) {
    junit.textui.TestRunner.run(DispatchImplTest.class);
  }
}
