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

package org.apache.geronimo.ews.ws4j2ee.toWs;

/**
 * <p>This interface has constants that are specific to the generators.</p>
 */
public class GenerationConstants {

    //generators
    public static final int EJB_GENERATOR = 0;
    public static final int AXIS_WEBSERVICE_WRAPPER_GENERATOR = 1;
    public static final int WSDL_GENERATOR = 2;
    public static final int CLIENT_STUB_GENERATOR = 3;
    public static final int SEI_AND_TYPES_GENERATOR = 4;
    public static final int HANDLER_GENERATOR = 13;
    public static final int JAXRPCMAPPER_GENERATOR = 14;
    public static final int WEBSERVICEDD_GENERATOR = 15;
    public static final int J2EE_CONTAINER_DD_GENERATOR = 16;
    public static final int BUILD_FILE_GENERATOR = 17;
    public static final int WEB_CONTAINER_DD_GENERATOR = 18;

    //writers
    public static final int EJB_REMOTE_INTERFACE_WRITER = 5;
    public static final int EJB_HOME_INTERFACE_WRITER = 6;
    public static final int EJB_LOCAL_INTERFACE_WRITER = 13;
    public static final int EJB_LOCAL_HOME_INTERFACE_WRITER = 14;

    public static final int EJB_IMPLEMENTATION_BEAN_WRITER = 7;
    public static final int EJB_DD_WRITER = 8;
    public static final int AXIS_WEBSERVICE_WRAPPER_WRITER = 9;
    public static final int AXIS_WEBSERVICE_WRAPPER_DEPLOYMENT_FILE_WRITER = 10;
    public static final int WSDL_FILE_WRITER = 11;
    public static final int SEI_FILE_WRITER = 12;

    public static final String OPTION_WS4J2EE_PROPERTY_FILE = "ws4j2ee.ConfigFile";

    public static final String J2EE_CONTAINER_DD = "j2ee-container-dd";
    public static final String WS4J2EE_PROPERTY_FILE = "ws4j2ee.properties";
    public static final String WS4J2EE_PROVIDER = "j2ee";
    public static final String J2EE_VERSION = "j2ee-version";
    public static final String J2EE_VERSION_1_3 = "1.3";
    public static final String J2EE_VERSION_1_4 = "1.4";
    public static final String AXIS_HOST = "axis-host";
    public static final String AXIS_PORT = "axis-port";
    public static final String AXIS_WEBAPPS_LIB = "axis-webapps-lib";
    public static final String EJB_DEPLOY_DIR = "ejb-deploy";
	
	
//	private static Ws4J2eeProperties properties = new Ws4J2eeProperties();
//	public static String getProperty(String key){
//		return properties.getProperty(key);
//	}

    /**
     * j2ee Container DDs
     */
    public static final String JBOSS_DD = "jboss.xml";
    public static final String JONAS_DD = "jonas-ejb-jar.xml";
    public static final String GERONIMO_DD = "openejb-jar.xml";
    public static final String GERONIMO_WEB_DD = "geronimo-jetty.xml";

    /**
     * j2ee Containers
     */
    public static final String JBOSS_CONTAINER = "jboss";
    public static final String JONAS_CONTAINER = "jonas";
    public static final String GERONIMO_CONTAINER = "geronimo";

    /**
     * implementation styles
     */
    public static final String USE_REMOTE = "use-remote";
    public static final String USE_LOCAL = "use-local";
    public static final String USE_INTERNALS = "use-internals";
    public static final String USE_LOCAL_AND_REMOTE = "use-local-remote";

    public static final String CONFIG_STORE = "target/configStore";
    public static final String MAVEN_LOCAL_REPOSITARY = "maven.repo.local";
}
