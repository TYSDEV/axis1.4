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

import org.apache.axis.utils.CLArgsParser;
import org.apache.axis.utils.CLOption;
import org.apache.axis.utils.CLOptionDescriptor;

import java.util.List;

/**
 * @author hemapani
 */
public class Ws4J2eeCLOptionParser {
    // Define our short one-letter option identifiers.
    protected static final int SERVER_OPT = 's';
    protected static final int SKELETON_DEPLOY_OPT = 'S';
    protected static final int NAMESPACE_OPT = 'N';
    protected static final int NAMESPACE_FILE_OPT = 'f';
    protected static final int OUTPUT_OPT = 'o';
    protected static final int SCOPE_OPT = 'd';
    protected static final int TEST_OPT = 't';
    protected static final int PACKAGE_OPT = 'p';
    protected static final int ALL_OPT = 'a';
    protected static final int TYPEMAPPING_OPT = 'T';
    protected static final int FACTORY_CLASS_OPT = 'F';
    protected static final int HELPER_CLASS_OPT = 'H';
    protected static final int USERNAME_OPT = 'U';
    protected static final int PASSWORD_OPT = 'P';
    protected static final int IMPL_STYLE_OPT = 'E';
    protected static final int CONTAINER_OPT = 'J';

    private String wscffile;
    private String outputDirectory = ".";
    private boolean isServerSide = false;
    private String userName;
    private String password;
    private String implStyle = GenerationConstants.USE_LOCAL_AND_REMOTE;
    private String contanier = GenerationConstants.JBOSS_CONTAINER;

    protected static final CLOptionDescriptor[] options = new CLOptionDescriptor[]{
        new CLOptionDescriptor("server-side",
                CLOptionDescriptor.ARGUMENT_OPTIONAL,
                SERVER_OPT,
                "Genarate Server side codes"),
        new CLOptionDescriptor("output",
                CLOptionDescriptor.ARGUMENT_REQUIRED,
                OUTPUT_OPT,
                "output Directory "),
        new CLOptionDescriptor("user",
                CLOptionDescriptor.ARGUMENT_REQUIRED,
                USERNAME_OPT,
                "user name"),
        new CLOptionDescriptor("password",
                CLOptionDescriptor.ARGUMENT_REQUIRED,
                PASSWORD_OPT,
                "password"),
        new CLOptionDescriptor("implStyle",
                CLOptionDescriptor.ARGUMENT_REQUIRED,
                IMPL_STYLE_OPT,
                "impelemtation Style"),
        new CLOptionDescriptor("container",
                CLOptionDescriptor.ARGUMENT_REQUIRED,
                CONTAINER_OPT,
                "the J2EE contianer")
    };

    public Ws4J2eeCLOptionParser(String[] args) {
        CLArgsParser argsParser = new CLArgsParser(args, options);

        // Print parser errors, if any
        if (null != argsParser.getErrorString()) {
            System.err.println(argsParser.getErrorString());
            //printUsage();
        }

        // Get a list of parsed options
        List clOptions = argsParser.getArguments();
        int size = clOptions.size();
        try {
            // Parse the options and configure the emitter as appropriate.
            for (int i = 0; i < size; i++) {
                parseOption((CLOption) clOptions.get(i));
            }

            // validate argument combinations
            //
            //validateOptions();

            //parser.run(wsdlURI);

            // everything is good
            //System.exit(0);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }

    protected void parseOption(CLOption option) {
        switch (option.getId()) {
            case SERVER_OPT:
                isServerSide = true;
                break;
            case OUTPUT_OPT:
                outputDirectory = option.getArgument();
                break;
            case USERNAME_OPT:
                userName = option.getArgument();
                break;
            case PASSWORD_OPT:
                password = option.getArgument();
                break;
            case CLOption.TEXT_ARGUMENT:
                if (wscffile != null) {
                    throw new UnrecoverableGenerationFault("Only one arguement allowed ");
                    //printUsage();
                }
                wscffile = option.getArgument();
                break;
            case IMPL_STYLE_OPT:
                this.implStyle = option.getArgument();
                break;
            case CONTAINER_OPT:
                this.contanier = option.getArgument();
                break;
            default:
                throw new UnrecoverableGenerationFault("unknown option");
        }
    } // parseOption

    /**
     * @return
     */
    public String getWscffile() {
        return wscffile;
    }

    /**
     * @param string
     */
    public void setWscffile(String string) {
        wscffile = string;
    }

    /**
     * @return
     */
    public boolean isServerSide() {
        return isServerSide;
    }

    /**
     * @return
     */
    public String getOutputDirectory() {
        return outputDirectory;
    }

    /**
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param b
     */
    public void setServerSide(boolean b) {
        isServerSide = b;
    }

    /**
     * @param string
     */
    public void setOutputDirectory(String string) {
        outputDirectory = string;
    }

    /**
     * @param string
     */
    public void setPassword(String string) {
        password = string;
    }

    /**
     * @param string
     */
    public void setUserName(String string) {
        userName = string;
    }

    /**
     * @return
     */
    public String getImplStyle() {
        return implStyle;
    }

    /**
     * @param string
     */
    public void setImplStyle(String string) {
        implStyle = string;
    }

    /**
     * @return
     */
    public String getContanier() {
        return contanier;
    }

    /**
     * @param string
     */
    public void setContanier(String string) {
        contanier = string;
    }

}
