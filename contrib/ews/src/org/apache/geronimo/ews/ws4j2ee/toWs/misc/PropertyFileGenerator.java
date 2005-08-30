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

package org.apache.geronimo.ews.ws4j2ee.toWs.misc;

import org.apache.geronimo.ews.ws4j2ee.context.J2EEWebServiceContext;
import org.apache.geronimo.ews.ws4j2ee.toWs.GenerationFault;
import org.apache.geronimo.ews.ws4j2ee.toWs.Generator;

import java.io.FileOutputStream;
import java.util.Properties;

/**
 * @author hemapani@opensource.lk
 */
public class PropertyFileGenerator implements Generator {
    private J2EEWebServiceContext context;

    public PropertyFileGenerator(J2EEWebServiceContext context) {
        this.context = context;
    }

    public void generate() throws GenerationFault {
        try {
            Properties p = new Properties();
            String wsclass = context.getWSDLContext().gettargetBinding().getName() + "Impl";
            p.setProperty("impl", wsclass);
            FileOutputStream out = new FileOutputStream(context.getMiscInfo().getOutPutPath() + "/ws.properties");
            p.store(out, "ws poperties");
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw GenerationFault.createGenerationFault(e);
        }
    }

}
