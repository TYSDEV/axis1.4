/*
 * Copyright 2003,2004 The Apache Software Foundation.
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
package org.apache.geronimo.ews.ws4j2ee.context.webservices.server.xmlbeans;

import com.sun.java.xml.ns.j2Ee.XsdStringType;

/**
 * @author Srinath Perera(hemapani@opensource.lk)
 */
public class XMLBeansUtils {
    public static String getStringValue(XsdStringType[] invalue) {
        if (invalue.length > 0) {
            return invalue[0].getStringValue();
        } else {
            return null;
        }
    }

    public static String getStringValue(com.sun.java.xml.ns.j2Ee.String[] invalue) {
        if (invalue.length > 0) {
            return invalue[0].getStringValue();
        } else {
            return null;
        }
    }

    public static String getStringValue(com.sun.java.xml.ns.j2Ee.String invalue) {
        if (invalue != null) {
            return invalue.getStringValue();
        } else {
            return null;
        }
    }

    public static String getStringValue(XsdStringType invalue) {
        if (invalue != null) {
            return invalue.getStringValue();
        } else {
            return null;
        }
    }

}
