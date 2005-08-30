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

/**
 * represent a operation in the SEI.
 *
 * @author Srinath Perera(hemapani@opensource.lk)
 */
public interface SEIOperation {
    public String getMethodName();

    /**
     * @return a Map in which key is the parameter name and the Type is the value.
     */
    public ArrayList getParameterNames();

    public String getParameterType(String name);

    /**
     * @return ArrayList of Strings
     */
    public ArrayList getFaults();

    public void setMethodName(String methodName);

    public void addParameter(String type, String name);

    public void addFault(String name);

    public String getReturnType();

    public void setReturnType(String returnType);
}
