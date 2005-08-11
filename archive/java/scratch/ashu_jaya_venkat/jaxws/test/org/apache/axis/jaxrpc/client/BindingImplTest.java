package org.apache.axis.jaxrpc.client;

import junit.framework.TestCase;
import org.apache.axis.jaxrpc.client.BindingImpl;

public class BindingImplTest extends TestCase
{
  org.apache.axis.jaxrpc.client.BindingImpl bindingimpl = null;
  
  public BindingImplTest(String name) {
    super(name);
  }
  
  public org.apache.axis.jaxrpc.client.BindingImpl createInstance() throws Exception {
    return new org.apache.axis.jaxrpc.client.BindingImpl();
  }
  
  protected void setUp() throws Exception {
    super.setUp();
    bindingimpl = createInstance();
  }
  
  protected void tearDown() throws Exception {
    bindingimpl = null;
    super.tearDown();
  }
  
  public void testSetGetHandlerChain() throws Exception {
    java.util.List[] tests = {new java.util.List(), null};
    
    for (int i = 0; i < tests.length; i++) {
      bindingimpl.setHandlerChain(tests[i]);
      assertEquals(tests[i], bindingimpl.getHandlerChain());
    }
  }
  
  public void testGetSecurityConfiguration() throws Exception {
  }
    
  public static void main(String[] args) {
    junit.textui.TestRunner.run(BindingImplTest.class);
  }
}
