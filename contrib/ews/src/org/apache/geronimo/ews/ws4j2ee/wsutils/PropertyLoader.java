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

package org.apache.geronimo.ews.ws4j2ee.wsutils;

import org.apache.geronimo.ews.ws4j2ee.toWs.GenerationConstants;
import org.apache.geronimo.ews.ws4j2ee.toWs.GenerationFault;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * Load a given property file
 *
 * @author Srinath Perera(hemapani@opensource.lk)
 */
public class PropertyLoader {
    protected static Log log =
            LogFactory.getLog(PropertyLoader.class.getName());
    public Properties loadProperties(String propertyFile) throws GenerationFault {
        try {
            Properties properties = new Properties();
            InputStream proIn = getClass().getClassLoader().getResourceAsStream(propertyFile);
            if (proIn == null) {
                proIn = GenerationConstants.class.getResourceAsStream("META-INF/" + propertyFile);
            }
            if (proIn == null) {
                proIn = new FileInputStream("conf/" + propertyFile);
            }
            if (proIn == null) {
                proIn = new FileInputStream(propertyFile);
            }
            if (proIn != null) {
                properties.load(proIn);
            } else {
                throw new GenerationFault("the " + propertyFile + "not found");
            }
            return properties;
        } catch (Exception e) {
            log.error(e);
            throw GenerationFault.createGenerationFault(e);
        }
    }
}
