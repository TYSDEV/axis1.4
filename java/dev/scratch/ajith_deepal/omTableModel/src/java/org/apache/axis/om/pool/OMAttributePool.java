/**
 * Copyright 2001-2004 The Apache Software Foundation.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @author Axis team
 * Date: Sep 28, 2004
 * Time: 10:22:35 PM
 */
package org.apache.axis.om.pool;

import org.apache.axis.om.impl.OMAttributeImpl;

public class OMAttributePool extends AbstractPool{
    private static OMAttributePool ourInstance;

    public synchronized static OMAttributePool getInstance() {
        if (ourInstance == null) {
            ourInstance = new OMAttributePool();
        }
        return ourInstance;
    }

    private OMAttributePool() {
    }

     /**
      *
      * @return an instance of the OMAttribute
      */
    public PooledObject getPooledObject() {
        return new OMAttributeImpl();
    }
}

