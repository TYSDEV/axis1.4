package test.encoding;

import junit.framework.TestCase;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * soapenc's PackageTests tests multiple floating point
 * deserialization classes (float and double, primitive and object).
 */
public class PackageTests extends TestCase
{
    public PackageTests(String name)
    {
        super(name);
    }

    public static Test suite() throws Exception
    {
        TestSuite suite = new TestSuite();

        suite.addTestSuite(TestDeser.class);
        suite.addTestSuite(TestDeser1999.class);
        suite.addTestSuite(TestDeser2000.class);
        suite.addTestSuite(TestDeser2001.class);
        suite.addTestSuite(TestSer.class);
        suite.addTestSuite(TestString.class);
        suite.addTestSuite(TestHrefs.class);

        return suite;
    }
}
