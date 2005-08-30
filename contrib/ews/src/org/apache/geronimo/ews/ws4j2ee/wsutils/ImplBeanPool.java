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

package org.apache.geronimo.ews.ws4j2ee.wsutils;

import org.apache.axis.utils.ClassUtils;

/**
 * <p>This class is the pool that pool the servlet based JSR109 implementations
 * These is a issue of the same thing happen differently when the code gerneration
 * Done using interfaces and DD + WSDL.</p>
 *
 * @author Srinath Perera(hemapani@opensource.lk)
 */
public class ImplBeanPool {
    private static ImplBeanPool instance;

    static {
        instance = new ImplBeanPool();
    }

    public static Object getImplBean(String classname) throws J2EEFault {
        return instance.getBean(classname);
    }

    private Object getBean(String classname) throws J2EEFault {
        try {
            Class implClass = ClassUtils.forName(classname);
            return implClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new J2EEFault(e);
        }
    }
}
