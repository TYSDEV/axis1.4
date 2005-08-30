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
package org.apache.geronimo.ews.ws4j2ee.toWs.wrapperWs;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.geronimo.ews.ws4j2ee.context.J2EEWebServiceContext;
import org.apache.geronimo.ews.ws4j2ee.toWs.GenerationFault;
import org.apache.geronimo.ews.ws4j2ee.toWs.Generator;
import org.apache.geronimo.ews.ws4j2ee.toWs.Writer;

/**
 * <p>This genarated theWrapper WS required in the
 * Axis.</p>
 *
 * @author Srinath Perera(hemapani@opensource.lk)
 */
public class WrapperWsGenerator implements Generator {
    private J2EEWebServiceContext j2eewscontext;
    private Writer writer;
    protected static Log log =
            LogFactory.getLog(WrapperWsGenerator.class.getName());

    public WrapperWsGenerator(J2EEWebServiceContext j2eewscontext) throws GenerationFault {
        this.j2eewscontext = j2eewscontext;
        writer = WrapperClassGeneratorFactory.createInstance(j2eewscontext);
    }

    public void generate() throws GenerationFault {
        writer.write();
    }
}
