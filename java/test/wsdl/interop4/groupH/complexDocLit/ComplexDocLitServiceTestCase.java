/**
 * ComplexDocLitServiceTestCase.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package test.wsdl.interop4.groupH.complexDocLit;

public class ComplexDocLitServiceTestCase extends junit.framework.TestCase {
    public ComplexDocLitServiceTestCase(java.lang.String name) {
        super(name);
    }
    public void test1ComplexDocLitPortEchoSOAPStructFault() throws Exception {
        test.wsdl.interop4.groupH.complexDocLit.ComplexDocLitPortType binding;
        try {
            binding = new test.wsdl.interop4.groupH.complexDocLit.ComplexDocLitServiceLocator().getComplexDocLitPort();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertTrue("binding is null", binding != null);

        // Test operation
        try {
            test.wsdl.interop4.groupH.complexDocLit.EchoSOAPStructFaultResponse value = null;
            value = binding.echoSOAPStructFault(new test.wsdl.interop4.groupH.complexDocLit.SOAPStruct());
        }
        catch (test.wsdl.interop4.groupH.complexDocLit.SOAPStructFault e1) {
            throw new junit.framework.AssertionFailedError("ComplexFault Exception caught: " + e1);
        }
            // TBD - validate results
    }

    public void test2ComplexDocLitPortEchoBaseStructFault() throws Exception {
        test.wsdl.interop4.groupH.complexDocLit.ComplexDocLitPortType binding;
        try {
            binding = new test.wsdl.interop4.groupH.complexDocLit.ComplexDocLitServiceLocator().getComplexDocLitPort();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertTrue("binding is null", binding != null);

        // Test operation
        try {
            test.wsdl.interop4.groupH.complexDocLit.EchoBaseStructFaultResponse value = null;
            value = binding.echoBaseStructFault(new test.wsdl.interop4.groupH.complexDocLit.BaseStruct());
        }
        catch (test.wsdl.interop4.groupH.complexDocLit.BaseStruct e1) {
            throw new junit.framework.AssertionFailedError("ComplexFault Exception caught: " + e1);
        }
            // TBD - validate results
    }

    public void test3ComplexDocLitPortEchoExtendedStructFault() throws Exception {
        test.wsdl.interop4.groupH.complexDocLit.ComplexDocLitPortType binding;
        try {
            binding = new test.wsdl.interop4.groupH.complexDocLit.ComplexDocLitServiceLocator().getComplexDocLitPort();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertTrue("binding is null", binding != null);

        // Test operation
        try {
            test.wsdl.interop4.groupH.complexDocLit.EchoExtendedStructFaultResponse value = null;
            value = binding.echoExtendedStructFault(new test.wsdl.interop4.groupH.complexDocLit.ExtendedStruct());
        }
        catch (test.wsdl.interop4.groupH.complexDocLit.ExtendedStruct e1) {
            throw new junit.framework.AssertionFailedError("ComplexFault Exception caught: " + e1);
        }
            // TBD - validate results
    }

    public void test4ComplexDocLitPortEchoMultipleFaults1() throws Exception {
        test.wsdl.interop4.groupH.complexDocLit.ComplexDocLitPortType binding;
        try {
            binding = new test.wsdl.interop4.groupH.complexDocLit.ComplexDocLitServiceLocator().getComplexDocLitPort();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertTrue("binding is null", binding != null);

        // Test operation
        try {
            test.wsdl.interop4.groupH.complexDocLit.EchoMultipleFaults1Response value = null;
            value = binding.echoMultipleFaults1(new test.wsdl.interop4.groupH.complexDocLit.EchoMultipleFaults1Request());
        }
        catch (test.wsdl.interop4.groupH.complexDocLit.BaseStruct e1) {
            throw new junit.framework.AssertionFailedError("ComplexFault2 Exception caught: " + e1);
        }
        catch (test.wsdl.interop4.groupH.complexDocLit.SOAPStructFault e2) {
            throw new junit.framework.AssertionFailedError("ComplexFault1 Exception caught: " + e2);
        }
            // TBD - validate results
    }

    public void test5ComplexDocLitPortEchoMultipleFaults2() throws Exception {
        test.wsdl.interop4.groupH.complexDocLit.ComplexDocLitPortType binding;
        try {
            binding = new test.wsdl.interop4.groupH.complexDocLit.ComplexDocLitServiceLocator().getComplexDocLitPort();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertTrue("binding is null", binding != null);

        // Test operation
        try {
            test.wsdl.interop4.groupH.complexDocLit.EchoMultipleFaults2Response value = null;
            value = binding.echoMultipleFaults2(new test.wsdl.interop4.groupH.complexDocLit.EchoMultipleFaults2Request());
        }
        catch (test.wsdl.interop4.groupH.complexDocLit.MoreExtendedStruct e1) {
            throw new junit.framework.AssertionFailedError("ComplexFault3 Exception caught: " + e1);
        }
        catch (test.wsdl.interop4.groupH.complexDocLit.ExtendedStruct e2) {
            throw new junit.framework.AssertionFailedError("ComplexFault2 Exception caught: " + e2);
        }
        catch (test.wsdl.interop4.groupH.complexDocLit.BaseStruct e3) {
            throw new junit.framework.AssertionFailedError("ComplexFault1 Exception caught: " + e3);
        }
            // TBD - validate results
    }

}
