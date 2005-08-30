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
package org.apache.geronimo.ews.ws4j2ee.toWs.dd.jboss;

import org.apache.geronimo.ews.ws4j2ee.context.J2EEWebServiceContext;
import org.apache.geronimo.ews.ws4j2ee.toWs.AbstractWriter;
import org.apache.geronimo.ews.ws4j2ee.toWs.GenerationConstants;
import org.apache.geronimo.ews.ws4j2ee.toWs.GenerationFault;

/**
 * @author Srinath Perera(hemapani@opensource.lk)
 */
public class JBossDDWriter extends AbstractWriter {
    public JBossDDWriter(J2EEWebServiceContext j2eewscontext) throws GenerationFault {
        super(j2eewscontext, j2eewscontext.getMiscInfo().getOutPutPath() +
                "/META-INF/jboss.xml");
    }

    public void writeCode() throws GenerationFault {
        if (out == null)
            return;
        String ejbname = j2eewscontext.getWSDLContext().getTargetPortType().getName().toLowerCase();
        int index = ejbname.lastIndexOf(".");
        if (index > 0) {
            ejbname = ejbname.substring(index + 1);
        }
        out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        out.write("<jboss>\n");
        out.write("  <enterprise-beans>\n");
        out.write("	<session>\n");
        out.write("	  <ejb-name>" + ejbname + "</ejb-name>\n");
        String implStyle = j2eewscontext.getMiscInfo().getImplStyle();
        if (GenerationConstants.USE_LOCAL_AND_REMOTE.equals(implStyle)
                || GenerationConstants.USE_REMOTE.equals(implStyle)) {
            out.write("	  <jndi-name>" + "ejb/" + ejbname + "</jndi-name>\n");
        }
        if (GenerationConstants.USE_LOCAL_AND_REMOTE.equals(implStyle)
                || GenerationConstants.USE_LOCAL.equals(implStyle)) {
            out.write("	  <local-jndi-name>" + "ejb/" + ejbname + "Local" + "</local-jndi-name>\n");
        }
        out.write("	</session>\n");
        out.write("  </enterprise-beans>\n");
        out.write("</jboss>\n");
    }
}
