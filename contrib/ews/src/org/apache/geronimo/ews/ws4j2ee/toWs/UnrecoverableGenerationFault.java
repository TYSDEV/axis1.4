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
 * <p>this error means that due to the error the programme going fail
 * and can't recoverd.</p>
 *
 * @author hemapani
 */
public class UnrecoverableGenerationFault extends RuntimeException {
    public UnrecoverableGenerationFault() {
        super();
    }

    /**
     * @param message
     * @param cause
     */
    public UnrecoverableGenerationFault(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param cause
     */
    public UnrecoverableGenerationFault(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     */
    public UnrecoverableGenerationFault(String message) {
        super(message);
    }
}
