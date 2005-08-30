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
package org.apache.geronimo.ews.jaxrpcmapping;

import org.apache.axis.enum.Scope;
import org.apache.axis.utils.CLArgsParser;
import org.apache.axis.utils.CLOption;
import org.apache.axis.utils.CLOptionDescriptor;
import org.apache.axis.utils.JavaUtils;
import org.apache.axis.utils.Messages;
import org.apache.axis.wsdl.gen.Parser;
import org.apache.axis.wsdl.gen.WSDL2;

import java.util.List;

/**
 * @author Ias (iasandcb@tmax.co.kr)
 */
public class WsdlToJ2ee extends WSDL2 {

    protected static final int MAPPING_OPT = 'm';

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

    protected boolean bPackageOpt = false;
    private J2eeEmitter emitter;

    /**
     * Define the understood options. Each CLOptionDescriptor contains:
     * - The "long" version of the option. Eg, "help" means that "--help" will
     * be recognised.
     * - The option flags, governing the option's argument(s).
     * - The "short" version of the option. Eg, 'h' means that "-h" will be
     * recognised.
     * - A description of the option for the usage message
     */
    protected static final CLOptionDescriptor[] options = new CLOptionDescriptor[]{
        new CLOptionDescriptor("server-side",
                CLOptionDescriptor.ARGUMENT_DISALLOWED,
                SERVER_OPT,
                Messages.getMessage("optionSkel00")),
        new CLOptionDescriptor("skeletonDeploy",
                CLOptionDescriptor.ARGUMENT_REQUIRED,
                SKELETON_DEPLOY_OPT,
                Messages.getMessage("optionSkeletonDeploy00")),
        new CLOptionDescriptor("NStoPkg",
                CLOptionDescriptor.DUPLICATES_ALLOWED + CLOptionDescriptor.ARGUMENTS_REQUIRED_2,
                NAMESPACE_OPT,
                Messages.getMessage("optionNStoPkg00")),
        new CLOptionDescriptor("fileNStoPkg",
                CLOptionDescriptor.ARGUMENT_REQUIRED,
                NAMESPACE_FILE_OPT,
                Messages.getMessage("optionFileNStoPkg00")),
        new CLOptionDescriptor("package",
                CLOptionDescriptor.ARGUMENT_REQUIRED,
                PACKAGE_OPT,
                Messages.getMessage("optionPackage00")),
        new CLOptionDescriptor("output",
                CLOptionDescriptor.ARGUMENT_REQUIRED,
                OUTPUT_OPT,
                Messages.getMessage("optionOutput00")),
        new CLOptionDescriptor("deployScope",
                CLOptionDescriptor.ARGUMENT_REQUIRED,
                SCOPE_OPT,
                Messages.getMessage("optionScope00")),
        new CLOptionDescriptor("testCase",
                CLOptionDescriptor.ARGUMENT_DISALLOWED,
                TEST_OPT,
                Messages.getMessage("optionTest00")),
        new CLOptionDescriptor("all",
                CLOptionDescriptor.ARGUMENT_DISALLOWED,
                ALL_OPT,
                Messages.getMessage("optionAll00")),
        new CLOptionDescriptor("typeMappingVersion",
                CLOptionDescriptor.ARGUMENT_REQUIRED,
                TYPEMAPPING_OPT,
                Messages.getMessage("optionTypeMapping00")),
        new CLOptionDescriptor("factory",
                CLOptionDescriptor.ARGUMENT_REQUIRED,
                FACTORY_CLASS_OPT,
                Messages.getMessage("optionFactory00")),
        new CLOptionDescriptor("helperGen",
                CLOptionDescriptor.ARGUMENT_DISALLOWED,
                HELPER_CLASS_OPT,
                Messages.getMessage("optionHelper00")),
        new CLOptionDescriptor("user",
                CLOptionDescriptor.ARGUMENT_REQUIRED,
                USERNAME_OPT,
                Messages.getMessage("optionUsername")),
        new CLOptionDescriptor("password",
                CLOptionDescriptor.ARGUMENT_REQUIRED,
                PASSWORD_OPT,
                Messages.getMessage("optionPassword")),
        new CLOptionDescriptor("mapping-file-path",
                CLOptionDescriptor.ARGUMENT_REQUIRED,
                MAPPING_OPT,
                "Choose a mapping file path for J2EE JAX-RPC mapping")
    };

    public WsdlToJ2ee() {
        emitter = (J2eeEmitter) parser;
        addOptions(options);
    } // ctor

    protected Parser createParser() {
        return new J2eeEmitter();
    } // createParser

    protected void parseOption(CLOption option) {
        switch (option.getId()) {
            case FACTORY_CLASS_OPT:
                emitter.setFactory(option.getArgument());
                break;
            case HELPER_CLASS_OPT:
                emitter.setHelperWanted(true);
                break;
            case SKELETON_DEPLOY_OPT:
                emitter.setSkeletonWanted(JavaUtils.isTrueExplicitly(option.getArgument(0)));
                // --skeletonDeploy assumes --server-side, so fall thru

            case SERVER_OPT:
                emitter.setServerSide(true);
                break;
            case NAMESPACE_OPT:
                String namespace = option.getArgument(0);
                String packageName = option.getArgument(1);
                emitter.getNamespaceMap().put(namespace, packageName);
                break;
            case NAMESPACE_FILE_OPT:
                emitter.setNStoPkg(option.getArgument());
                break;
            case PACKAGE_OPT:
                bPackageOpt = true;
                emitter.setPackageName(option.getArgument());
                break;
            case OUTPUT_OPT:
                emitter.setOutputDir(option.getArgument());
                break;
            case SCOPE_OPT:
                String arg = option.getArgument();
                
                // Provide 'null' default, prevents logging internal error.
                // we have something different to report here.
                Scope scope = Scope.getScope(arg, null);
                if (scope != null) {
                    emitter.setScope(scope);
                } else {
                    System.err.println(Messages.getMessage("badScope00", arg));
                }
                break;
            case TEST_OPT:
                emitter.setTestCaseWanted(true);
                break;
            case ALL_OPT:
                emitter.setAllWanted(true);
                break;
            case TYPEMAPPING_OPT:
                String tmValue = option.getArgument();
                if (tmValue.equals("1.1")) {
                    emitter.setTypeMappingVersion("1.1");
                } else if (tmValue.equals("1.2")) {
                    emitter.setTypeMappingVersion("1.2");
                } else {
                    System.out.println(Messages.getMessage("badTypeMappingOption00"));
                }
                break;
            case USERNAME_OPT:
                emitter.setUsername(option.getArgument());
                break;
            case PASSWORD_OPT:
                emitter.setPassword(option.getArgument());
                break;
            case MAPPING_OPT:
                emitter.setMappingFilePath(option.getArgument());
                break;
            default:
                super.parseOption(option);
        }
    } // parseOption

    /**
     * validateOptions
     * This method is invoked after the options are set to validate
     * the option settings.
     */
    protected void validateOptions() {
        super.validateOptions();

        // validate argument combinations
        if (emitter.isSkeletonWanted() && !emitter.isServerSide()) {
            System.out.println(Messages.getMessage("badSkeleton00"));
            printUsage();
        }
        if (!emitter.getNamespaceMap().isEmpty() && bPackageOpt) {
            System.out.println(Messages.getMessage("badpackage00"));
            printUsage();
        }
    } // validateOptions

    /**
     * run
     * checkes the command-line arguments and runs the tool.
     *
     * @param args String[] command-line arguments.
     */
    protected void run(String[] args) {
        // Parse the arguments
        CLArgsParser argsParser = new CLArgsParser(args, options);

        // Print parser errors, if any
        if (null != argsParser.getErrorString()) {
            System.err.println(Messages.getMessage("error01", argsParser.getErrorString()));
            printUsage();
        }

        // Get a list of parsed options
        List clOptions = argsParser.getArguments();
        int size = clOptions.size();

        // Parse the options and configure the emitter as appropriate.
        for (int i = 0; i < size; i++) {
            parseOption((CLOption) clOptions.get(i));
        }

        // validate argument combinations
        // 
        validateOptions();
        try {
            parser.run(wsdlURI);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }    // run

    /**
     * Main
     */
    public static void main(String args[]) {
        WsdlToJ2ee j2ee = new WsdlToJ2ee();
        j2ee.run(args);
    } // main
} 
