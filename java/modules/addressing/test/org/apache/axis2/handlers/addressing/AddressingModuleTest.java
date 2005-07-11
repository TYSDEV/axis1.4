package org.apache.axis2.handlers.addressing;

import junit.framework.TestCase;
import org.apache.axis2.context.ConfigurationContextFactory;
import org.apache.axis2.description.ModuleDescription;
import org.apache.axis2.engine.AxisConfiguration;
import org.apache.axis2.engine.AxisFault;

import javax.xml.namespace.QName;
import java.io.File;

/**
 * Copyright 2001-2004 The Apache Software Foundation.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * <p/>
 */
public class AddressingModuleTest extends TestCase {

    /**
     * @param testName
     */
    public AddressingModuleTest(String testName) {
        super(testName);
    }

    public void testExtractAddressingInformationFromHeaders() throws AxisFault {
        ConfigurationContextFactory builder = new ConfigurationContextFactory();
        AxisConfiguration er = builder.buildConfigurationContext("target").getAxisConfiguration();
        File file = new File("target/addressing.mar");
        assertTrue(file.exists());
        ModuleDescription moduleDesc = er.getModule(new QName("addressing"));
        assertNotNull(moduleDesc);
    }

}
