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

package org.apache.geronimo.ews.ws4j2ee.toWs.handlers;

import java.util.HashMap;

import org.apache.geronimo.ews.ws4j2ee.context.J2EEWebServiceContext;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFHandler;
import org.apache.geronimo.ews.ws4j2ee.toWs.GenerationFault;
import org.apache.geronimo.ews.ws4j2ee.toWs.Generator;
import org.apache.geronimo.ews.ws4j2ee.toWs.Writer;

/**
 * <p>Genarate the signature of the handlers as given by the webservices.xml file.</p>
 * 
 * @author Srinath Perera(hemapani@opensource.lk)
 */
public class HandlerGenerator implements Generator {
	private J2EEWebServiceContext j2eewscontext;
	private Writer[] writers = new Writer[0];
	
	private static HashMap handlermap = new HashMap();
	static {
		handlermap.put("org.apache.ws.axis.security.CheckPoint4J2EEHandler",
			"org.apache.ws.axis.security.CheckPoint4J2EEHandler");
	};


	public HandlerGenerator(J2EEWebServiceContext j2eewscontext) throws GenerationFault {
		this.j2eewscontext = j2eewscontext;
			WSCFHandler[] handlers = j2eewscontext.getMiscInfo().getHandlers();
			if(handlers!= null){
				writers = new Writer[handlers.length];
				for (int i = 0; i < handlers.length; i++) {
					if(!handlermap.containsKey(handlers[i].getHandlerClass())){
						writers[i] = new HandlerWriter(j2eewscontext, handlers[i]);
					}
				}
			}
	}

	/**
	 * genarate the handlers
	 */
	public void generate() throws GenerationFault {
		for (int i = 0; i < writers.length; i++) {
			if(writers[i] != null){
				writers[i].write();			
			}
		}
	}
}
