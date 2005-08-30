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

package org.apache.geronimo.ews.ws4j2ee.context.impl;

import org.apache.geronimo.ews.ws4j2ee.context.InputOutputFile;
import org.apache.geronimo.ews.ws4j2ee.context.MiscInfo;
import org.apache.geronimo.ews.ws4j2ee.context.SEIOperation;
import org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFHandler;
import org.apache.geronimo.ews.ws4j2ee.toWs.GenerationConstants;

import java.util.ArrayList;

/**
 * @author Srinath Perera(hemapani@opensource.lk)
 * @see org.apache.geronimo.ews.ws4j2ee.context.MiscInfo
 */
public class MiscInfoImpl implements MiscInfo {
    private ClassLoader classloader;
    private boolean implwithEJB = true;
    /* if Impl is avalible the class need not to be created agaien*/
    private boolean implAvalible = true;

    private String outputPath = ".";
    private String j2eelink;
    private String seiname;

    private ArrayList operations;
    private boolean verbose = false;
    private String wsConfFileLocation;

    private InputOutputFile wsdlFile;
    private InputOutputFile jaxrpcfile;
    private InputOutputFile wsconffile;

    private String targetJ2EEContainer = GenerationConstants.JBOSS_CONTAINER;
    private String implStyle = GenerationConstants.USE_LOCAL_AND_REMOTE;
    private boolean seiExists = false;
    private ArrayList classpathelements;
    private boolean compile = true;

    private WSCFHandler[] handlers;

    public MiscInfoImpl() {
        operations = new ArrayList();
        targetJ2EEContainer = GenerationConstants.JBOSS_CONTAINER;
        implStyle = GenerationConstants.USE_REMOTE;
    }

    public String getOutPutPath() {
        return outputPath;
    }

    public void setOutputPath(String string) {
        outputPath = string;
    }

    public void validate() {
    }

    /**
     * @return
     */
    /* (non-Javadoc)
     * @see org.apache.geronimo.ews.ws4j2ee.context.MiscInfo#getSEIOperations()
     */
    public ArrayList getSEIOperations() {
        return operations;
    }

    /* (non-Javadoc)
     * @see org.apache.geronimo.ews.ws4j2ee.context.MiscInfo#setSEIOperations(java.util.ArrayList)
     */
    public void setSEIOperations(SEIOperation operation) {
        this.operations.add(operation);
    }

    /**
     * @return
     */
    public boolean isVerbose() {
        return verbose;
    }

    /**
     * @return
     */
    public String getWsConfFileLocation() {
        return wsConfFileLocation;
    }

    /**
     * @param b
     */
    public void setVerbose(boolean b) {
        verbose = b;
    }

    /**
     * @param string
     */
    public void setWsConfFileLocation(String string) {
        wsConfFileLocation = string;
    }

    /**
     * @return
     */
    public String getJ2eeComponetLink() {
        return j2eelink;
    }

    /**
     * @param string
     */
    public void setJ2eeComponetLink(String string) {
        j2eelink = string;
    }

    /**
     * @return
     */
    public InputOutputFile getJaxrpcfile() {
        return jaxrpcfile;
    }

    /**
     * @return
     */
    public InputOutputFile getWsdlFile() {
        return wsdlFile;
    }

    /**
     * @param string
     */
    public void setJaxrpcfile(InputOutputFile string) {
        jaxrpcfile = string;
    }

    /**
     * @param string
     */
    public void setWsdlFile(InputOutputFile string) {
        wsdlFile = string;
    }

    /**
     * @return
     */
    public String getJaxrpcSEI() {
        return seiname;
    }

    /**
     * @param string
     */
    public void setJaxrpcSEI(String string) {
        seiname = string;
    }

    /**
     * @return
     */
    public String getImplStyle() {
        return implStyle;
    }

    /**
     * @return
     */
    public String getTargetJ2EEContainer() {
        return targetJ2EEContainer;
    }

    /**
     * @param string
     */
    public void setImplStyle(String string) {
        implStyle = string;
    }

    /**
     * @param string
     */
    public void setTargetJ2EEContainer(String string) {
        targetJ2EEContainer = string;
    }

    /**
     * @return
     */
    public boolean isImplwithEJB() {
        return implwithEJB;
    }

    /**
     * @param b
     */
    public void setImplwithEJB(boolean b) {
        implwithEJB = b;
    }

    /**
     * @return
     */
    public boolean isImplAvalible() {
        return implAvalible;
    }

    /**
     * @param b
     */
    public void setImplAvalible(boolean b) {
        this.implAvalible = b;
    }

    /**
     * @return
     */
    public InputOutputFile getWsconffile() {
        return wsconffile;
    }

    /**
     * @param string
     */
    public void setWsconffile(InputOutputFile string) {
        wsconffile = string;
    }

    /* (non-Javadoc)
     * @see org.apache.geronimo.ews.ws4j2ee.context.MiscInfo#getHandlers()
     */
    public WSCFHandler[] getHandlers() {
        return handlers;
    }

    /* (non-Javadoc)
     * @see org.apache.geronimo.ews.ws4j2ee.context.MiscInfo#setHandlers(org.apache.geronimo.ews.ws4j2ee.context.webservices.server.interfaces.WSCFHandler[])
     */
    public void setHandlers(WSCFHandler[] handlers) {
        this.handlers = handlers;
    }

    /* (non-Javadoc)
     * @see org.apache.geronimo.ews.ws4j2ee.context.MiscInfo#isSEIExists()
     */
    public boolean isSEIExists() {
        return seiExists;
    }

    /* (non-Javadoc)
     * @see org.apache.geronimo.ews.ws4j2ee.context.MiscInfo#setSEIExists()
     */
    public void setSEIExists(boolean seiExists) {
        this.seiExists = seiExists;
    }

    /* (non-Javadoc)
     * @see org.apache.geronimo.ews.ws4j2ee.context.MiscInfo#getClasspathElements()
     */
    public ArrayList getClasspathElements() {
        return classpathelements;
    }

    /* (non-Javadoc)
     * @see org.apache.geronimo.ews.ws4j2ee.context.MiscInfo#setClassPathElements()
     */
    public void setClassPathElements(ArrayList classpathelements) {
        this.classpathelements = classpathelements;
    }

    /**
     * @return
     */
    public ClassLoader getClassloader() {
        return classloader;
    }

    /**
     * @param loader
     */
    public void setClassloader(ClassLoader loader) {
        classloader = loader;
    }

    /**
     * @return
     */
    public boolean isCompile() {
        return compile;
    }

    /**
     * @param b
     */
    public void setCompile(boolean b) {
        compile = b;
    }

}
