/*
 * The Apache Software License, Version 1.1
 *
 *
 * Copyright (c) 2001-2003 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by the
 *    Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "Axis" and "Apache Software Foundation" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache",
 *    nor may "Apache" appear in their name, without prior written
 *    permission of the Apache Software Foundation.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 */

package org.apache.axismora.wsdl2ws.java;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.axismora.Constants;
import org.apache.axismora.wsdl2ws.SourceWriter;
import org.apache.axismora.wsdl2ws.WrapperFault;
import org.apache.axismora.wsdl2ws.WrapperUtils;
import org.apache.axismora.wsdl2ws.info.WebServiceContext;

/**
 * @author Dimuthu Leelarathne.(muthulee@yahoo.com)
 * 
 * This writes the .wsdd file
 */
public class DeploymentWriter implements SourceWriter {
    private String servicename;
    private BufferedWriter writer;
    private WebServiceContext wscontext;
    private File file;

    public DeploymentWriter(WebServiceContext wscontext) throws WrapperFault {
        this.wscontext = wscontext;
        servicename =
            WrapperUtils.getClassNameFromFullyQualifiedName(
                this.wscontext.getSerInfo().getQualifiedServiceName());

        try {
            file = getJavaFilePath();
            this.writer = new BufferedWriter(new FileWriter(getJavaFilePath(), false));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void writeSource() throws WrapperFault {

        String classname =
            WrapperUtils.getClassNameFromFullyQualifiedName(
                wscontext.getSerInfo().getQualifiedServiceName());

        try {
            String servcieclass =
                wscontext.getSerInfo().getQualifiedServiceName()
                    + Constants.WRAPPER_NAME_APPENDER;
            writer.write(
                "<deployment xmlns=\"http://xml.apache.org/axis/wsdd/\" xmlns:java=\"http://xml.apache.org/axis/wsdd/providers/java\">\n");
            writer.write(
                "\t<service name==\"" + servicename + "\" provider=\"java:RPC\">\n");
            writer.write("\t\t<parameter name=\"allowedMethods\" value=\"*\"/>\n");
            writer.write(
                "\t\t<parameter name=\"className\" value=\" " + servcieclass + "\"/>\n");
            writer.write("\t</service>\n</deployment>\n");
            writer.flush();
            writer.close();
            System.out.println(file.getAbsolutePath() + "is created ........");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public File getJavaFilePath() {
        String dirpath;
        String targetOutputLocation =
            this.wscontext.getWrapInfo().getTargetOutputLocation();
        if (targetOutputLocation.endsWith("/"))
            targetOutputLocation =
                targetOutputLocation.substring(0, targetOutputLocation.length() - 1);
        if (targetOutputLocation.equals(""))
            dirpath = ".";
        else
            dirpath =
                targetOutputLocation
                    + "/"
                    + WrapperUtils
                        .getPackegeName4QualifiedName(
                            this.wscontext.getSerInfo().getQualifiedServiceName())
                        .replace('.', '/');
        new File(dirpath).mkdirs();
        return new File(dirpath + "/" + servicename + "Deployment.wsdd");
    }

}
