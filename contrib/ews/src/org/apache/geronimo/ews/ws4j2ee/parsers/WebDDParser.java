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

import org.apache.geronimo.ews.ws4j2ee.context.J2EEWebServiceContext;
import org.apache.geronimo.ews.ws4j2ee.context.impl.WebDDContextImpl;
import org.apache.geronimo.ews.ws4j2ee.context.j2eeDD.WebContext;
import org.apache.geronimo.ews.ws4j2ee.toWs.GenerationFault;
import org.apache.geronimo.ews.ws4j2ee.toWs.UnrecoverableGenerationFault;
import org.apache.geronimo.ews.ws4j2ee.utils.EWSUtils;
import org.apache.geronimo.ews.ws4j2ee.utils.Utils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * &lt;web-app&gt; .....
 *  &lt;servlet&gt;
 *   &lt;servlet-name&gt;AxisServlet&lt;/servlet-name&gt;
 *   &lt;display-name&gt;Apache-Axis Servlet&lt;/display-name&gt;
 *   &lt;servlet-class&gt;
 *       org.apache.axis.transport.http.AxisServlet
 *   &lt;/servlet-class&gt;
 * &lt;/servlet&gt;
 * 
 * ...
 * &lt;/web-app&gt;
 * Parse the web,xl file and get the servlet class corresponds to the given servlet
 * @author hemapani
 */
public class WebDDParser {
	private J2EEWebServiceContext j2eewscontext;
	private WebContext context;
	private String servletClass = null;
	private String servletName = null;

	public WebDDParser(J2EEWebServiceContext j2eewscontext) {
		this.j2eewscontext = j2eewscontext;
	}

	public void parse(InputStream inputStream) throws GenerationFault {
			Document doc = EWSUtils.createDocument(inputStream);
			Element root =  doc.getDocumentElement();
			NodeList sevlele = root.getElementsByTagName("servlet");
            int count = 0;
            while(count < sevlele.getLength()){
				Element serv = (Element)sevlele.item(count);
                servletName = Utils.getElementValue(serv.getElementsByTagName("servlet-name"));
                if(servletName.equals(j2eewscontext.getMiscInfo().getJ2eeComponetLink())){
                    servletClass = Utils.getElementValue(serv.getElementsByTagName("servlet-class"));
                    context = new WebDDContextImpl(servletClass,servletName);
                    return;
                }    
                count++;
			}
			throw new UnrecoverableGenerationFault("J2EE link not found");
	}
	
	
	/**
	 * @return
	 */
	public String getServletClass() {
		return servletClass;
	}

	/**
	 * @param string
	 */
	public void setServletClass(String string) {
		servletClass = string;
	}

    /**
     * @return
     */
    public WebContext getContext() {
        return context;
    }

    /**
     * @param context
     */
    public void setContext(WebContext context) {
        this.context = context;
    }

}
