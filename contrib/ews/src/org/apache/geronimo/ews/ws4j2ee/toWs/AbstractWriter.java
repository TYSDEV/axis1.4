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

package org.apache.geronimo.ews.ws4j2ee.toWs;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.geronimo.ews.ws4j2ee.context.J2EEWebServiceContext;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <p>This is a conveniance class to wite the Writers</p>
 *
 * @author Srinath Perera(hemapani@opensource.lk)
 */

public abstract class AbstractWriter implements Writer {
    /**
     * <p>this parameter act as a mediator, It contains all the information
     * this will be passed to the each writer. This will make sure
     * even if the information that should passed around it will keep
     * method signatures intact.</p>
     */
    protected static Log log =
            LogFactory.getLog(AbstractWriter.class.getName());
    protected J2EEWebServiceContext j2eewscontext;
    /* this is used to write the file */
    protected PrintWriter out;
    private String fileName;
    private boolean verbose;

    public AbstractWriter(J2EEWebServiceContext j2eewscontext, String filename)
            throws GenerationFault {
        this.j2eewscontext = j2eewscontext;
        this.fileName = filename;
        verbose = j2eewscontext.getMiscInfo().isVerbose();
    }

    protected abstract void writeCode() throws GenerationFault;

    protected final void prepare() throws GenerationFault {
        try {
            File file = new File(this.fileName);
            if (verbose) {
                log.info("genarating ... " + file.getAbsolutePath());
            }
            if (!isOverWrite() && file.exists()) {
                out = null;
                if (verbose) {
                    log.info("the file already exists .. tool will not overwrite it ");
                }
            } else {
                File parent = file.getParentFile();
                if (parent != null)
                    parent.mkdirs();
                file.createNewFile();
                out = new PrintWriter(new FileWriter(file, false));
            }
        } catch (IOException e) {
            log.error(e);
            throw GenerationFault.createGenerationFault(e);
        }
    }

    protected final void cleanUp() throws GenerationFault {
        if (out != null)
            out.close();
    }

    protected boolean isOverWrite() {
        return true;
    }

    public final void write() throws GenerationFault {
        try {
            prepare();
            writeCode();
        } finally {
            cleanUp();
        }
    }
}
