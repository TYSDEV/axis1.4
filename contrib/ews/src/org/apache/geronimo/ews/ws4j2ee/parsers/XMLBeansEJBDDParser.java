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

import java.io.InputStream;

import noNamespace.EjbJarDocument;

import org.apache.geronimo.ews.ws4j2ee.context.J2EEWebServiceContext;
import org.apache.geronimo.ews.ws4j2ee.context.impl.EJBDDContextImpl;
import org.apache.geronimo.ews.ws4j2ee.context.j2eeDD.EJBContext;
import org.apache.geronimo.ews.ws4j2ee.toWs.GenerationFault;
import org.apache.geronimo.ews.ws4j2ee.utils.EWSUtils;
import org.apache.geronimo.ews.ws4j2ee.utils.Utils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Parse the ejb-jar.xml file and get the name of the EJB
 * @author Srinath Perera(hemapani@opensource.lk)
 */
public class XMLBeansEJBDDParser {
	private J2EEWebServiceContext j2eewscontext;
	private String ejbName = null;
	private EJBContext context;

	public XMLBeansEJBDDParser(J2EEWebServiceContext j2eewscontext) {
		this.j2eewscontext = j2eewscontext;
	}

	public void parse(InputStream inputStream) throws GenerationFault {
			try {
                EjbJarDocument ejbjarDoc = EjbJarDocument.Factory.parse(inputStream);
                EjbJarDocument.EjbJar ejbjar =  ejbjarDoc.getEjbJar();
                ejbjar.selectPath("ejb-jar/enterprise-beans/session/home");
                
                
                
				Document doc = EWSUtils.createDocument(inputStream);
				Element root =  doc.getDocumentElement();
				NodeList beaneles = root.getElementsByTagName("enterprise-beans");
				if(beaneles.getLength()>0){
					Element beanele = (Element)beaneles.item(0);
					NodeList sessionList = beanele.getElementsByTagName("session");
					if(sessionList.getLength()>0){
						Element session = (Element)sessionList.item(0);
						
						String ejbName = Utils.getElementValue(session.getElementsByTagName("ejb-name"));
						String home = Utils.getElementValue(session.getElementsByTagName("home"));
						String remote = Utils.getElementValue(session.getElementsByTagName("remote"));
						String ejbClass = Utils.getElementValue(session.getElementsByTagName("ejb-class"));

						context = new EJBDDContextImpl(
							ejbName,
							ejbClass,
							home,remote,null,null);
					}else{
						throw new GenerationFault("session tag not found");
				}
				}else{
					throw new GenerationFault("enterprise-beans tag not found");
				}
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
