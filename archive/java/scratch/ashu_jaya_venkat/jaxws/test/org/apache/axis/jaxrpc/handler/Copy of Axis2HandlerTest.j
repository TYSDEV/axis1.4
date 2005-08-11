package org.apache.axis.jaxrpc.handler;

import junit.framework.TestCase;
// JUnitDoclet begin import
import org.apache.axis.jaxrpc.handler.Axis2Handler;
// JUnitDoclet end import

/**


* Please see www.junitdoclet.org, www.gnu.org
* and www.objectfab.de for informations about
* the tool, the licence and the authors.
*/


public class Axis2HandlerTest
// JUnitDoclet begin extends_implements
extends TestCase
// JUnitDoclet end extends_implements
{
  // JUnitDoclet begin class
  org.apache.axis.jaxrpc.handler.Axis2Handler axis2handler = null;
  // JUnitDoclet end class
  
  public Axis2HandlerTest(String name) {
    // JUnitDoclet begin method Axis2HandlerTest
    super(name);
    // JUnitDoclet end method Axis2HandlerTest
  }
  
  public org.apache.axis.jaxrpc.handler.Axis2Handler createInstance() throws Exception {
    // JUnitDoclet begin method testcase.createInstance
    return new org.apache.axis.jaxrpc.handler.Axis2Handler();
    // JUnitDoclet end method testcase.createInstance
  }
  
  protected void setUp() throws Exception {
    // JUnitDoclet begin method testcase.setUp
    super.setUp();
    axis2handler = createInstance();
    // JUnitDoclet end method testcase.setUp
  }
  
  protected void tearDown() throws Exception {
    // JUnitDoclet begin method testcase.tearDown
    axis2handler = null;
    super.tearDown();
    // JUnitDoclet end method testcase.tearDown
  }
  
  public void testSetGetJaxRpcHandler() throws Exception {
    // JUnitDoclet begin method setJaxRpcHandler getJaxRpcHandler
    javax.xml.rpc.handler.AbstractHandler[] tests = {new javax.xml.rpc.handler.AbstractHandler(), null};
    
    for (int i = 0; i < tests.length; i++) {
      axis2handler.setJaxRpcHandler(tests[i]);
      assertEquals(tests[i], axis2handler.getJaxRpcHandler());
    }
    // JUnitDoclet end method setJaxRpcHandler getJaxRpcHandler
  }
  
  public void testInvoke() throws Exception {
    // JUnitDoclet begin method invoke
    // JUnitDoclet end method invoke
  }
  
  
  
  /**
  * JUnitDoclet moves marker to this method, if there is not match
  * for them in the regenerated code and if the marker is not empty.
  * This way, no test gets lost when regenerating after renaming.
  * Method testVault is supposed to be empty.
  */
  public void testVault() throws Exception {
    // JUnitDoclet begin method testcase.testVault
    // JUnitDoclet end method testcase.testVault
  }
  
  public static void main(String[] args) {
    // JUnitDoclet begin method testcase.main
    junit.textui.TestRunner.run(Axis2HandlerTest.class);
    // JUnitDoclet end method testcase.main
  }
}
