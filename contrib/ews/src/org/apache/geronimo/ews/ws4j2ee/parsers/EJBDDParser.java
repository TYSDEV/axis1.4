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

package org.apache.geronimo.ews.ws4j2ee.parsers;

import org.apache.geronimo.ews.ws4j2ee.context.J2EEWebServiceContext;
import org.apache.geronimo.ews.ws4j2ee.context.impl.EJBDDContextImpl;
import org.apache.geronimo.ews.ws4j2ee.context.j2eeDD.EJBContext;
import org.apache.geronimo.ews.ws4j2ee.parsers.ejbdd.EjbJar;
import org.apache.geronimo.ews.ws4j2ee.parsers.ejbdd.EjbJarType.EnterpriseBeansType.Session;
import org.apache.geronimo.ews.ws4j2ee.toWs.GenerationFault;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.util.List;

/**
 * Parse the ejb-jar.xml file and get the name of the EJB
 *
 * @author Srinath Perera(hemapani@opensource.lk)
 */
public class EJBDDParser {
    private J2EEWebServiceContext j2eewscontext;
    private String ejbName = null;
    private EJBContext context;

    public EJBDDParser(J2EEWebServiceContext j2eewscontext) {
        this.j2eewscontext = j2eewscontext;
    }

    public void parse(InputStream inputStream) throws GenerationFault {
        try {
            JAXBContext jc = JAXBContext.newInstance("org.apache.geronimo.ews.ws4j2ee.parsers.ejbdd");
            // create an Unmarshaller
            Unmarshaller u = jc.createUnmarshaller();
            u.setValidating(false);
            EjbJar ejbJar =
                    (EjbJar) u.unmarshal(inputStream);
            List sessions = ejbJar.getEnterpriseBeans().getSessionOrEntity();
            if (!(sessions.size() > 0 && sessions.get(0) instanceof Session))
                return;
            Session session = (Session) sessions.get(0);
            ejbName = session.getEjbName().getValue();
            context = new EJBDDContextImpl(ejbName, session.getEjbClass().getValue(), session.getHome().getValue(), null, null, null);
        } catch (Exception e) {
            throw GenerationFault.createGenerationFault(e);
        }
    }

    /**
     * @return
     */
    public EJBContext getContext() {
        return context;
    }

    /**
     * @param context
     */
    public void setContext(EJBContext context) {
        this.context = context;
    }

}
