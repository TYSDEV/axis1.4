/*
 * The Apache Software License, Version 1.1
 *
 *
 * Copyright (c) 2001 The Apache Software Foundation.  All rights
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
 *        Apache Software Foundation (http://www.apache.org/)."
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
package org.apache.axis.deployment.wsdd;

import org.apache.axis.deployment.DeploymentDocument;
import org.apache.axis.deployment.DeploymentException;
import org.apache.axis.deployment.DeploymentRegistry;
import org.apache.axis.deployment.DeployableItem;
import org.apache.axis.encoding.*;
import org.apache.axis.utils.XMLUtils;
import org.apache.axis.Constants;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import java.io.IOException;
import java.io.StringWriter;
import java.io.StringReader;

/**
 * represents a WSDD Document (this is the top level object in this object model)
 */
public class WSDDDocument
    implements DeploymentDocument
{

    /** XXX */
    private Document doc;

    /** XXX */
    private WSDDDeployment dep;

    /**
     *
     */
    public WSDDDocument()
    {
    }

    /**
     *
     * @param doc (Document) XXX
     */
    public WSDDDocument(Document doc) throws WSDDException
    {
        this.doc = doc;
        dep = new WSDDDeployment(doc.getDocumentElement());
    }

    /**
     *
     * @param e (Element) XXX
     */
    public WSDDDocument(Element e) throws WSDDException
    {
        doc = e.getOwnerDocument();
        dep = new WSDDDeployment(e);
    }

    /**
     *
     * @return XXX
     */
    public WSDDDeployment getDeployment()
    {
        if (dep == null)
            dep = new WSDDDeployment();
        return dep;
    }

    public Document getDOMDocument() throws DeploymentException {
        StringWriter writer = new StringWriter();
        SerializationContext context = new SerializationContext(writer, null);
        context.setPretty(true);
        try {
            dep.writeToContext(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            writer.close();
            return XMLUtils.newDocument(new InputSource(new StringReader(writer.getBuffer().toString())));
        } catch (IOException e) {
            return null;
        }
    }

    public void writeToContext(SerializationContext context)
        throws IOException
    {
        getDeployment().writeToContext(context);
    }

    /**
     *
     * @param document XXX
     */
    public void setDocument(Document document)
    {
        doc = document;

        dep = null;
    }

    /**
     *
     * @param registry XXX
     * @throws DeploymentException XXX
     */
    public void deploy(DeploymentRegistry registry)
        throws DeploymentException
    {
        getDeployment();

        WSDDGlobalConfiguration global = dep.getGlobalConfiguration();

        if (global != null) {
            registry.setGlobalConfiguration(global);
        }

        WSDDHandler[]     handlers   = dep.getHandlers();
        WSDDTransport[]   transports = dep.getTransports();
        WSDDService[]     services   = dep.getServices();
        WSDDTypeMapping[] mappings   = dep.getTypeMappings();

        for (int n = 0; n < handlers.length; n++) {
            registry.deployHandler(handlers[n]);
        }

        for (int n = 0; n < transports.length; n++) {
            registry.deployTransport(transports[n]);
        }

        for (int n = 0; n < services.length; n++) {
            registry.deployService(services[n]);
        }

        for (int n = 0; n < mappings.length; n++) {
            WSDDTypeMapping     mapping = mappings[n];
            deployMappingToRegistry(mapping, registry);
        }
    }

    public static void deployMappingToRegistry(WSDDTypeMapping mapping, 
                                               DeploymentRegistry registry) 
            throws DeploymentException {
        TypeMappingRegistry tmr     =
                registry.getTypeMappingRegistry(mapping.getEncodingStyle());
        
        if (tmr == null) {
            tmr = new TypeMappingRegistry();
            tmr.setParent(SOAPTypeMappingRegistry.getSingleton());

            registry.addTypeMappingRegistry(mapping.getEncodingStyle(),
                                            tmr);
        }

        Serializer          ser   = null;
        DeserializerFactory deser = null;

        try {
            ser   = (Serializer) mapping.getSerializer().newInstance();
            deser =
                (DeserializerFactory) mapping.getDeserializer()
                    .newInstance();

            if (ser != null) {
                tmr.addSerializer(mapping.getLanguageSpecificType(),
                                  mapping.getQName(), ser);
            }

            if (deser != null) {
                tmr.addDeserializerFactory(mapping.getQName(), mapping
                    .getLanguageSpecificType(), deser);
            }
        }
        catch (Exception e) {
            throw new DeploymentException(e.getMessage());
        }
    }

    public void importItem(DeployableItem item) throws DeploymentException {
    }
}
