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
package org.apache.geronimo.ews.ws4j2ee.utils;

import java.io.File;
import java.util.HashMap;

import org.apache.geronimo.ews.ws4j2ee.toWs.GenerationConstants;

/**
 * @author Srinath Perera (hemapani@opensource.lk)
 */
public class TemporaryRepository {
    private static File root = new File(GenerationConstants.CONFIG_STORE);
    static{
        root.delete();
        root.mkdirs();
    }
    private static HashMap map = new HashMap();
    private static int COUNT = 0;
    
    public TemporaryRepository(File baseDir){
        TemporaryRepository.root = baseDir;
        root.mkdirs();
    }
    
    public static File getEntry(){
        File dir = new File(root,String.valueOf(COUNT));
        COUNT++;
        dir.mkdir();
        map.put(dir.getAbsoluteFile(),dir);
        return dir;
    }
}
