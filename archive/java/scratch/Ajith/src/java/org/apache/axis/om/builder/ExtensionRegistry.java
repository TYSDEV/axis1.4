package org.apache.axis.om.builder;

import java.util.ArrayList;

/*
 * Copyright 2004,2005 The Apache Software Foundation.
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
 *
 * 
 */
public class ExtensionRegistry {
    private ArrayList list;

    private static ExtensionRegistry instance = null;

    public static ExtensionRegistry getInstance(){
        if (instance==null)
            instance = new ExtensionRegistry();
        return instance;
    }

    private ExtensionRegistry() {
        this.list = new ArrayList();
    }

   public void registerExtension(BuilderExtension extension){
       this.list.add(extension);
   }

    public void clearExtensions(){
        this.list.clear();
    }
   public BuilderExtension[] getExtensions(){

       if (list.isEmpty()) return null;
       BuilderExtension[] returnArray = new BuilderExtension[list.size()];
       for (int i = 0; i < returnArray.length; i++) {
          returnArray[i]=(BuilderExtension)list.get(i);

       }

       return returnArray;
   }

    public boolean isEmpty(){
        return this.list.isEmpty();
    }
}
