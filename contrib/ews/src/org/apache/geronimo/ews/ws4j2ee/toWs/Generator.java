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
 * <p>Instance of the Genarator represents an entity that genarates a
 * Module. for e.g. EJB classes or JAX-RPC wrapper class and WSDD file genarator.</p>
 */
public interface Generator {
    /**
     * <p>at the call of the genarate method the Genarator will genarate what
     * it is suppose to be genarate. Usally the Genarator is a assembly of
     * One or more writers.</p>
     */
    public abstract void generate() throws GenerationFault;
}
