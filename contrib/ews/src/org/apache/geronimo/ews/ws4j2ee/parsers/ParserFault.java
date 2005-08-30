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

/**
 * <p>This denotes the Exception occured at the code genaration.
 * There is a isssue of wrapping the Exception such that JDK1.3 compatibility.
 * This code write same way as it was done at the RemoteException.</p>
 */
public class ParserFault extends Exception {
    /**
     * Nested Exception to hold wrapped exception.
     * <p>This field predates the general-purpose exception chaining facility.
     * The {@link Throwable#getCause()} method is now the preferred means of
     * obtaining this information.</p>
     *
     * @serial
     */
    public Throwable detail;

    public ParserFault(Exception e) {
        initCause(null);  // Disallow subsequent initCause
        detail = e;
    }

    public ParserFault(String message) {
        super(message);
    }

    /**
     * Constructs a <code>Exception</code> with the specified
     * detail message and nested exception.
     *
     * @param s  the detail message
     * @param ex the nested exception
     */
    public ParserFault(String s, Throwable ex) {
        super(s);
        initCause(null);  // Disallow subsequent initCause
        detail = ex;
    }

    /**
     * Returns the detail message, including the message from the nested
     * exception if there is one.
     *
     * @return	the detail message, including nested exception message if any
     */
    public String getMessage() {
        if (detail == null) {
            return super.getMessage();
        } else {
            return super.getMessage() + "; nested exception is: \n\t" +
                    detail.toString();
        }
    }

    /**
     * Returns the wrapped exception (the <i>cause</i>).
     *
     * @return the wrapped exception, which may be <tt>null</tt>.
     */
    public Throwable getCause() {
        return detail;
    }
}
