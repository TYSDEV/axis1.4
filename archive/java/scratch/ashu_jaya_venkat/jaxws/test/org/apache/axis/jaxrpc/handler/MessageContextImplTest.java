package org.apache.axis.jaxrpc.handler;

import junit.framework.TestCase;
import org.apache.axis.jaxrpc.handler.MessageContextImpl;

public class MessageContextImplTest extends TestCase
{
  org.apache.axis.jaxrpc.handler.MessageContextImpl messagecontextimpl = null;
  
  public MessageContextImplTest(String name) {
    super(name);
  }
  
  public org.apache.axis.jaxrpc.handler.MessageContextImpl createInstance() throws Exception {
    return new org.apache.axis.jaxrpc.handler.MessageContextImpl();
  }
  
  protected void setUp() throws Exception {
    super.setUp();
    messagecontextimpl = createInstance();
  }
  
  protected void tearDown() throws Exception {
    messagecontextimpl = null;
    super.tearDown();
  }
  
  public void testSetPropertyScope() throws Exception {
  }
  
  public void testGetPropertyScope() throws Exception {
  }
  
  public void testSetProperty() throws Exception {
  }
  
  public void testGetProperty() throws Exception {
  }
  
  public void testRemoveProperty() throws Exception {
  }
  
  public void testContainsProperty() throws Exception {
  }
  
  public void testGetPropertyNames() throws Exception {
  }
    
  public static void main(String[] args) {
    junit.textui.TestRunner.run(MessageContextImplTest.class);
  }
}
