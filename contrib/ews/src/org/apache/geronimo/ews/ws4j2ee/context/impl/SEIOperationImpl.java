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

import org.apache.geronimo.ews.ws4j2ee.context.SEIOperation;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Srinath Perera(hemapani@opensource.lk)
 * @see SEIOperation
 */
public class SEIOperationImpl implements SEIOperation {
    private String methodName;
    private String returnType;
    private HashMap parameters = new HashMap();
    private ArrayList parmNames = new ArrayList();
    private ArrayList faults = new ArrayList();

    public String getMethodName() {
        return methodName;
    }

    public ArrayList getParameterNames() {
        return parmNames;
    }

    public ArrayList getFaults() {
        return faults;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void addParameter(String type, String name) {
        parmNames.add(name);
        parameters.put(name, type);
    }

    public void addFault(String name) {
        faults.add(name);
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    /* (non-Javadoc)
     * @see org.apache.geronimo.ews.ws4j2ee.context.SEIOperation#getParameterTypes()
     */
    public String getParameterType(String name) {
        return (String) parameters.get(name);
    }

}
