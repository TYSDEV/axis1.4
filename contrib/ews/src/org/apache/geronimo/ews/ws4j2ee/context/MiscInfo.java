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

package org.apache.geronimo.ews.ws4j2ee.context;

import java.util.ArrayList;
import java.util.Vector;

import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFHandler;

/**
 * <p>This class interface the importent information about the webservice
 * under consideration. E.g. the webservice DD may have one or more
 * WebserviceDescriptions, WSDLportType's WSDLBindings ect. This type of object
 * provide acsess to the instance's under the consideration. It is recomended to
 * acscess these instances over MiscInfo snd It will make sure everybody is
 * acsessing same port type ect.
 * 
 * @author Srinath Perera(hemapani@opensource.lk)
 */
public interface MiscInfo {
	/**
	 * the root diretory for the output  
	 * @param outputpath
	 */
    public void setOutputPath(String outputpath);
    public String getOutPutPath();
    
    
	/**
	 * @return ArrayList of SEIOperations
	 */
	public ArrayList getSEIOperations();
	public void setSEIOperations(SEIOperation operation);
	
	public void setSEIExists(boolean seiExists);
	public boolean isSEIExists();

	/**
	 * Names of the classes corresponding to SEI, Implementation bean and the 
	 * web service and the sessionless EJB. 
	 * @return
	 */    

	public String getJaxrpcSEI();
	public void setJaxrpcSEI(String string);

	public String getJ2eeComponetLink();
	public void setJ2eeComponetLink(String string);



	/**
	 * Validate the Content of the Misc Info
	 */
    public void validate();

    public boolean isVerbose();
	public void setVerbose(boolean b);
	
    
	/**
	 * The jaxrpc-file specifies a location of the WSDL description of
	 * a set of Web services. The location is relative to the root of the module
	 * and must be specified by the developer.
	 */
    public InputOutputFile getJaxrpcfile();
	public void setJaxrpcfile(InputOutputFile string);
	/**
	 * The wsdl-file specifies a location of the WSDL description of
	 * a set of Web services. The location is relative to the root of the module
	 * and must be specified by the developer.
	 */
    public InputOutputFile getWsdlFile();
    public void setWsdlFile(InputOutputFile string);

	/**
	 * <p>One of the following values and specify how the ejb based web service 
	 * Obtain the referance to the sessionless web service</p> 
	 * public static final String USE_REMOTE ="use-remote";
	 * public static final String USE_LOCAL =  "use-local";
	 * public static final String USE_INTERNALS = "use-internals";
	 * public static final String USE_LOCAL_AND_REMOTE = "use-local-remote";
	 */
	public String getImplStyle();
	public void setImplStyle(String string);

	/**
	 * <p>One of the following values and specify what is the j2ee container the 
	 * implementation is based on.</p>
	 * public static final String JBOSS_CONTAINER = "jboss";
	 * public static final String JONAS_CONTAINER = "jonas";
	 * public static final String GERONIMO_CONTAINER = "geronimo";
	 */ 
	public String getTargetJ2EEContainer();
	public void setTargetJ2EEContainer(String string);

	/**
	 * Say the implementation of the webservice is based on the sessionless web service 
	 * or a java class. 
	 * @return
	 */
	public boolean isImplwithEJB();
	public void setImplwithEJB(boolean b);

	/**
	 * Say wheather the Implementation bean is avalible when gererating without wsdl.
	 * For other cases it does not make sense.   
	 * @return
	 */
	public boolean isImplAvalible();
	public void setImplAvalible(boolean b);

	/**
	 * The webservices.xml specifies a location of the WSDL description of
	 * a set of Web services. The location is relative to the root of the module
	 * and must be specified by the developer.
	 */
	public InputOutputFile getWsconffile(); 
	public void setWsconffile(InputOutputFile string);

	/**
	 * Handlers. A developer may optionally specify handlers associated with the 
	 * service-ref using the handler element.
	 */
	public WSCFHandler[] getHandlers();
	public void setHandlers(WSCFHandler[] handlers);
	
    /**
     * the class path elemnts added via jar,war,ear files this is a Vector of
     * java.io.File
     */
    public ArrayList getClasspathElements();
    public void setClassPathElements(ArrayList classpathelements);
    
	public ClassLoader getClassloader();
	public void setClassloader(ClassLoader loader);
    
    public boolean isCompile();
    public void setCompile(boolean compile);
    
}
