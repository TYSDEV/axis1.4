/**
 * RefTestServiceTestCase.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis Wsdl2java emitter, and
 * then modified by Rich Scheuerle to add testcase info.
 */

package test.wsdl.refattr;

import org.apache.axis.utils.ClassUtils;

public class RefTestServiceTestCase extends junit.framework.TestCase {
    public RefTestServiceTestCase(String name) {
        super(name);
    }

    public void testRefTest() {
        test.wsdl.refattr.RefTest binding;
        try {
            binding = new RefTestServiceLocator().getRefTest();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre );
        }
        
        // The following declarations are used to verify that unmangled classes
        // are generated by Wsdl2Java (not a mangled class like Phone_Type).
        Info info = new Info();
        Phone phone = new Phone();
        Enum  enum  = Enum.fromValue("one");
        info.setEnum(enum);
        Forward forward  = new Forward();
        NoMangle noMangle  = new NoMangle();
        
        // The following code ensures that mangling occurred for the Mangle class
        Mangle_Type mangle_type = new Mangle_Type();
        Mangle_ElemType mangle_elem_type = new Mangle_ElemType();
        try {
            Class cls = ClassUtils.forName("test.wsdl.refattr.Mangle");
            assertTrue("Found unmangled class test.wsdl.refattr.Mangle", false);
        } catch (Exception e) {
            // Okay expect to get an exception
        }

        assertTrue("binding is null", binding != null);
        try {
            binding.test(info);
        } catch (java.rmi.RemoteException re) {
            throw new junit.framework.AssertionFailedError("Remote Exception caught: " + re );
        }
    }
}

