/*
 * Copyright 2003,2004 The Apache Software Foundation.
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

import noNamespace.EjbJarDocument;

import org.apache.geronimo.ews.AbstractTestCase;
import org.apache.xmlbeans.XmlObject;

import com.sun.java.xml.ns.j2Ee.DisplayNameType;
import com.sun.java.xml.ns.j2Ee.WebservicesDocument;
import com.sun.java.xml.ns.j2Ee.WebservicesType;

import junit.framework.TestCase;

/**
 * @author Srinath Perera(hemapani@opensource.lk)
 */
public class XMLBeansTest extends AbstractTestCase{

    /**
     * @param testName
     */
    public XMLBeansTest(String testName) {
        super(testName);
        // TODO Auto-generated constructor stub
    }
    public void testXpaths()throws Exception{
        EjbJarDocument ejbjarDoc = EjbJarDocument.Factory.parse(
            new FileInputStream(getTestFile(testDir+"testData/math/ejb-jar.xml")));
        EjbJarDocument.EjbJar ejbjar =  ejbjarDoc.getEjbJar();
        System.out.println(ejbjar.getDisplayName().getStringValue());
        
        XmlObject[] vals = ejbjar.selectPath("ejb-jar/enterprise-beans/session");
        System.out.println(vals.length);
    }    

    public void testNamspacedD()throws Exception{
//        EjbJarDocument ejbjarDoc = EjbJarDocument.Factory.parse(
//            new FileInputStream(getTestFile("samples/ejb/echo/META-INF/ejb-jar.xml")));
//        EjbJarDocument.EjbJar ejbjar =  ejbjarDoc.getEjbJar();
//        System.out.println(ejbjar.getDisplayName().getStringValue());
//        
//        XmlObject[] vals = ejbjar.selectPath("ejb-jar/enterprise-beans/session");
//        System.out.println(vals.length);
//        
//        
//    
    }    
    
    public void testWSDD()throws Exception{
        WebservicesDocument wsDoc = WebservicesDocument.Factory.parse(
            new FileInputStream(getTestFile("samples/ejb/echo/META-INF/webservice.xml")));
        WebservicesType ws =  wsDoc.getWebservices();
        System.out.println(ws.getDisplayNameArray().length);
        
        XmlObject[] vals = wsDoc.selectPath("declare namespace j2ee=\'http://java.sun.com/xml/ns/j2ee\' j2ee:webservices/j2ee:display-name");
        System.out.println(((DisplayNameType)vals[0]).getStringValue());
    }

}
