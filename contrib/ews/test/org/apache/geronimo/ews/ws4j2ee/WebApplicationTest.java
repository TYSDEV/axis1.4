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

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.URL;
import java.net.URLClassLoader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.axis.client.AdminClient;
import org.apache.axis.client.Call;
import org.apache.axis.deployment.wsdd.WSDDDocument;
import org.apache.axis.transport.http.SimpleAxisServer;
import org.apache.axis.utils.ClassUtils;
import org.apache.geronimo.ews.AbstractTestCase;
import org.apache.geronimo.ews.ws4j2ee.toWs.Ws4J2ee;

/**
 * @author Srinath Perera(hemapani@opensource.lk)
 */
public class WebApplicationTest extends AbstractTestCase{
    public WebApplicationTest(String testName) {
        super(testName);
    }
    protected void setUp() throws Exception {
//        File wsddFile = new File(getTestFile("target/server-config.wsdd"));
//        if(!wsddFile.exists()){
//            wsddFile.createNewFile();
//            OutputStream out = new FileOutputStream(wsddFile);
//            
//            InputStream in = Thread.currentThread().getContextClassLoader()
//                .getResourceAsStream("org/apache/axis/server/server-config.wsdd");
//            getTestFile("target/server-config.wsdd");
//            byte[] buf = new byte[1024];
//            int read = in.read(buf);
//        
//            while(read > 0){
//                out.write(buf,0,read);
//                read = in.read(buf);
//            }
//            in.close();
//            out.close();
//        }
//
//        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//        dbf.setNamespaceAware(true);
//        DocumentBuilder db = dbf.newDocumentBuilder();
//        WSDDDocument wsdddoc = new WSDDDocument(db.parse(wsddFile));
//        SimpleAxisServer sas = new SimpleAxisServer();
//        sas.setServerSocket(new ServerSocket(5555));
//        sas.setMyConfig(wsdddoc.getDeployment());
//        sas.start();
//        
    }
    
    
    public void testEcho()throws Exception{
//        String[] args2 = new String[]{
//                getTestFile("target/generated/samples/echo.war"),"-o",
//                outDir+"withoutWSDL/echo-war/"};
//        Ws4J2ee.main(args2);
//
//        File jarFile = new File("target/generated/samples/withoutWSDL/echo-war/");
//        
//        URLClassLoader cl = new URLClassLoader(new URL[]{jarFile.toURL()});
//        InputStream deplydd = cl.getResourceAsStream("deploy.wsdd");
//        assertNotNull(deplydd);
//
//        ClassLoader parentClassLoder = ClassUtils.getDefaultClassLoader();
//        ClassUtils.setDefaultClassLoader(cl);
//        AdminClient adminClient = new AdminClient();
//
//        URL requestUrl = new URL("http://localhost:5555/axis/services/AdminService");
//        Call call = adminClient.getCall();
//        call.setTargetEndpointAddress(requestUrl);
//        String result = adminClient.process(null, deplydd);
//        System.out.println(result);
//
//        Class echoLoacaterClass = ClassUtils.forName("org.apache.ws.echosample.EchoServiceLocator");
//        Class structClass = ClassUtils.forName("org.apache.ws.echosample.EchoStruct");
//
//        Object echoLoacater = echoLoacaterClass.newInstance();
//        Method getportMethod = echoLoacaterClass.getMethod("getechoPort", new Class[]{URL.class});
//
//        URL serviceURL = new URL("http://localhost:5555/axis/services/echoPort");
//        Object echoPort = getportMethod.invoke(echoLoacater, new Object[]{serviceURL});
//        Class echoClass = echoPort.getClass();
//
//        Method echostuctMethod = echoClass.getMethod("echoStruct", new Class[]{structClass});
//        Object structval = structClass.newInstance();
//
//        Object structret = echostuctMethod.invoke(echoPort, new Object[]{null});
//        System.out.println("Sucssessful");
        
    }
    protected void tearDown() throws Exception {
        
    }

}
