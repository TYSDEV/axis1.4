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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.geronimo.ews.ws4j2ee.toWs.GenerationFault;

/**
 * @author Srinath Perera (hemapani@opensource.lk)
 */
public class FileUtils {
    public static void copyFile(InputStream in, OutputStream out) throws GenerationFault{
        try{
           
            byte[] buf = new byte[1024];
            int val = in.read(buf);
            while(val > 0){
                out.write(buf,0,val);
                val = in.read(buf);
            }
            out.close();
            in.close();
        }catch(Exception e){
                throw GenerationFault.createGenerationFault(e);
        }
    }
    
    public static int configCount = 0; 
    private JarFile ajar;
    private static byte[] data = new byte[10 * 1024];
    private String dir;
    

    public static ArrayList uncompressWar(File outDir, File WarFile)throws IOException{
        ArrayList urls = new ArrayList();
        
        ZipFile zippedWar = new ZipFile(WarFile);
        Enumeration enties = zippedWar.entries();
        
        File classesDir = new File(outDir,"WEB-INF/classes");
        classesDir.mkdirs();
        urls.add(classesDir);
        
        while(enties.hasMoreElements()){
            ZipEntry entry = (ZipEntry)enties.nextElement();
            if(entry.isDirectory()){
                
            }else{
                if(entry.getName().startsWith("WEB-INF/classes")){
                    File out = new File(outDir,entry.getName());
                    out.getParentFile().mkdirs();
                    uncompressFile(zippedWar.getInputStream(entry),out);
                }
                if(entry.getName().startsWith("WEB-INF/lib")){
                    File outJar = new File(outDir,entry.getName());
                    uncompressFile(zippedWar.getInputStream(entry),outJar);
                    urls.add(outJar);
                }
            
            }
        }
        return urls;
    }
    
    public static void uncompressFile(InputStream is,File outFile) throws IOException{
        outFile.getParentFile().mkdirs();
        OutputStream os = new FileOutputStream(outFile);
        int val = 1;
        while (true) {
            val = is.read(data, 0, 1024);
            if (val < 0 || val == 0)
                break;
            os.write(data, 0, val);
            System.out.println(val);
        }
        is.close();
        os.close();
    }

}
