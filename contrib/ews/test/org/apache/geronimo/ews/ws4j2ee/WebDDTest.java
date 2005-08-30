/*
 * Copyright 2001-2004 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.geronimo.ews.ws4j2ee;

import java.io.FileInputStream;

import junit.framework.Assert;

import org.apache.geronimo.ews.AbstractTestCase;
import org.apache.geronimo.ews.ws4j2ee.context.J2EEWebServiceContext;
import org.apache.geronimo.ews.ws4j2ee.parsers.WebDDParser;

import testData.MockWs4J2eeContext;

/**
 * @author hemapani
 */
public class WebDDTest extends AbstractTestCase{
    /**
     * @param testName
     */
    public WebDDTest(String testName) {
        super(testName);
    }

	public void testWebDD() throws Exception{
	   J2EEWebServiceContext con = new MockWs4J2eeContext();
       con.getMiscInfo().setJ2eeComponetLink("AxisServlet");
	   WebDDParser pars = new WebDDParser(con);
	   pars.parse(new FileInputStream(getTestFile(sampleDir + "servlet/math/web.xml")));
	   Assert.assertEquals("org.apache.axis.transport.http.AxisServlet",pars.getServletClass()); 
	}
}
