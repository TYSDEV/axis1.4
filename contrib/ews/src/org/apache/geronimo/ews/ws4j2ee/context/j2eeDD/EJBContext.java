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

package org.apache.geronimo.ews.ws4j2ee.context.j2eeDD;

/**
 * @author hemapani@opensource.lk
 */
public interface EJBContext {
    public String getEjbLocalHomeInterfce();

    public String getEjbLocalInterface();

    public String getEjbName();

    public String getEjbRemoteInterface();

    public String getImplBean();

    public String getEjbhomeInterface();

    public void setEjbhomeInterface(String string);

    public void setEjbLocalHomeInterfce(String string);

    public void setEjbLocalInterface(String string);

    public void setEjbName(String string);

    public void setEjbRemoteInterface(String string);

    public void setImplBean(String string);
}
