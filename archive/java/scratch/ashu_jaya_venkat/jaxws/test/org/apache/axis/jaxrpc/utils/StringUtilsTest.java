package org.apache.axis.jaxrpc.utils;

import junit.framework.TestCase;
import org.apache.axis.jaxrpc.utils.StringUtils;

public class StringUtilsTest extends TestCase
{
  org.apache.axis.jaxrpc.utils.StringUtils stringutils = null;
  
  public StringUtilsTest(String name) {
    super(name);
  }
  
  public org.apache.axis.jaxrpc.utils.StringUtils createInstance() throws Exception {
    return new org.apache.axis.jaxrpc.utils.StringUtils();
  }
  
  protected void setUp() throws Exception {
    super.setUp();
    stringutils = createInstance();
  }
  
  protected void tearDown() throws Exception {
    stringutils = null;
    super.tearDown();
  }
  
  public void testStartsWithIgnoreWhitespaces() throws Exception {
  }
  
  public void testSplit() throws Exception {
  }
  
  public void testIsEmpty() throws Exception {
  }
  
  public void testStrip() throws Exception {
  }
  
  public void testStripStart() throws Exception {
  }
  
  public void testStripEnd() throws Exception {
  }
  
  public void testEscapeNumericChar() throws Exception {
  }
  
  public void testUnescapeNumericChar() throws Exception {
  }
    
  public static void main(String[] args) {
    junit.textui.TestRunner.run(StringUtilsTest.class);
  }
}
