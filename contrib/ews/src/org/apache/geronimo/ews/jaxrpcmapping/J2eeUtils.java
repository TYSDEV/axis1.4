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

/**
 * @author Ias (iasandcb@tmax.co.kr)
 */
public class J2eeUtils {
    /**
     * @deprecated no more used by JaxRpcMapper
     */
    public static String convertArrayExpression(String arrayParameter) {
        int bracketOpen = arrayParameter.indexOf('[');
        if (bracketOpen < 0) {
            return arrayParameter;
        }
        String beforeBracket = arrayParameter.substring(0, bracketOpen);
        String afterBracket = arrayParameter.substring(bracketOpen);
        int countBracket = 0;
        for (int i = 0; i < afterBracket.length(); i++) {
            if (afterBracket.charAt(i) == '[') {
                countBracket++;
            }
        }
        String convertedBrackets = "";
        for (int i = 0; i < countBracket; i++) {
            convertedBrackets += "\\[\\]";
        }
        return beforeBracket + convertedBrackets;
    }

    public static String jni2javaName(String returnType) {
        if (returnType == null)
            return null;
        if (!returnType.startsWith("[")) {
            return returnType;
        } else {
            returnType = returnType.substring(1);
        }
        String end = "[]";
        while (returnType.startsWith("[")) {
            end = end + "[]";
            returnType = returnType.substring(1);
        }
        if (returnType.startsWith("B")) {
            returnType = "byte";
        } else if (returnType.startsWith("I")) {
            returnType = "int";
        } else if (returnType.startsWith("D")) {
            returnType = "double";
        } else if (returnType.startsWith("J")) {
            returnType = "long";
        } else if (returnType.startsWith("Z")) {
            returnType = "boolean";
        } else if (returnType.startsWith("F")) {
            returnType = "float";
        } else if (returnType.startsWith("S")) {
            returnType = "short";
        } else if (returnType.startsWith("L")) {
            int index = returnType.indexOf(";@");
            returnType.substring(1, index);
        }
        return returnType + end;
    }

}
