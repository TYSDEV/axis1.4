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

package org.apache.geronimo.ews.ws4j2ee.utils.packager;

import java.io.File;
import java.util.ArrayList;

/**
 * @author Srinath Perera(hemapani@opensource.lk)
 */
public class FileSerachAgent {
    private ArrayList acceptedFiles;
    private ArrayList rejectedFiles;
    private boolean selectAll;
    private String startingDir = ".";
    private boolean recursive = true;

    //this says what to do with the files that are not in 
    //acccpeted or rejected lists
    private boolean defaultForUnspsecified = false;

    public FileSerachAgent(String startingDir) {
        this.startingDir = startingDir;
        acceptedFiles = new ArrayList();
        rejectedFiles = new ArrayList();
    }

    /**
     * add file to be accepted
     * 
     * @param extension e.g. ".class"
     */
    public void addAcceptedFile(String extension) {
        acceptedFiles.add(extension);
    }

    public void addRejectedFile(String extension) {
        rejectedFiles.add(extension);
    }

    /**
     * @return 
     */
    public boolean isSelectAll() {
        return selectAll;
    }

    /**
     * @param b 
     */
    public void setSelectAll(boolean b) {
        selectAll = b;
    }

    public ArrayList serach() {
        ArrayList result = new ArrayList();
        getSourceFiles(result, new File(startingDir));
        return result;
    }

    /**
     * fill the supplied vector with the selected files
     * 
     * @param list     
     * @param location 
     */
    private void getSourceFiles(ArrayList list, File location) {

        String[] dirs = location.list();
        if (dirs == null)
            return;

        for (int i = 0; i < dirs.length; i++) {
            String filename = location.getAbsolutePath() + "/" + dirs[i];
            File file = new File(filename);
            if (file.isFile()) {
                if (!isAcceptedFile(filename))
                    continue;
                list.add(filename);
            } else {
                if (recursive)
                    getSourceFiles(list, new File(filename));
            }

        }
    }

    private boolean isAcceptedFile(String filename) {
        if (!(new File(filename)).isFile())
            return true;
        if (selectAll)
            return true;
        for (int i = 0; i < rejectedFiles.size(); i++) {
            if (filename.endsWith((String) rejectedFiles.get(i)))
                return false;
        }
        for (int i = 0; i < acceptedFiles.size(); i++) {
            if (filename.endsWith((String) acceptedFiles.get(i)))
                return true;
        }
        return defaultForUnspsecified;
    }

    /**
     * @return 
     */
    public boolean isDefaultForUnspsecified() {
        return defaultForUnspsecified;
    }

    /**
     * @param b 
     */
    public void setDefaultForUnspsecified(boolean b) {
        defaultForUnspsecified = b;
    }

    /**
     * @return 
     */
    public boolean isRecursive() {
        return recursive;
    }

    /**
     * @param b 
     */
    public void setRecursive(boolean b) {
        recursive = b;
    }

}
