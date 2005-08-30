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

package org.apache.geronimo.ews.ws4j2ee.toWs;

/**
 * <p>for each single classs to be written there should be an associated
 * writer instance. The writer instances of a single module, (e.g. writer
 * instances of the EJB say <code>RemoteInterface Writer,HomeInterfaceWriter,
 * ImplementationBeanWriter</code>) are bundeled by a single Genarator instance.
 * The writer instaces should extend from the AbstractWriter unless it
 * is required to have it otherwise.</p>
 */
public interface Writer {
    /**
     * The method writeCode() of the Writer should write the file Associated
     * with this writer.
     */
    public abstract void write() throws GenerationFault;
}
