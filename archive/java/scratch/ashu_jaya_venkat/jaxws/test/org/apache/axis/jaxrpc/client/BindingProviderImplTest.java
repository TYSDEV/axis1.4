package org.apache.axis.jaxrpc.client;

import junit.framework.TestCase;
import org.apache.axis.jaxrpc.client.BindingProviderImpl;

public class BindingProviderImplTest extends TestCase
{
  org.apache.axis.jaxrpc.client.BindingProviderImpl bindingproviderimpl = null;
  
  public BindingProviderImplTest(String name) {
    super(name);
  }
  
  public org.apache.axis.jaxrpc.client.BindingProviderImpl createInstance() throws Exception {
    return new org.apache.axis.jaxrpc.client.BindingProviderImpl();
  }
  
  protected void setUp() throws Exception {
    super.setUp();
    bindingproviderimpl = createInstance();
  }
  
  protected void tearDown() throws Exception {
    bindingproviderimpl = null;
    super.tearDown();
  }
  
  public void testGetRequestContext() throws Exception {
  }
  
  public void testGetResponseContext() throws Exception {
  }
  
  public void testSetGetBinding() throws Exception {
    BindingImpl[] tests = {new BindingImpl(), null};
    
    for (int i = 0; i < tests.length; i++) {
      bindingproviderimpl.setBinding(tests[i]);
      assertEquals(tests[i], bindingproviderimpl.getBinding());
    }
  }
  
  public void testGetHandlerChain() throws Exception {
  }
  
  public void testSetGetClientHome() throws Exception {
    String[] tests = {new String(), null};
    
    for (int i = 0; i < tests.length; i++) {
      bindingproviderimpl.setClientHome(tests[i]);
      assertEquals(tests[i], bindingproviderimpl.getClientHome());
    }
  }
    
  public static void main(String[] args) {
    junit.textui.TestRunner.run(BindingProviderImplTest.class);
  }
}
