/*
 * The Apache Software License, Version 1.1
 *
 *
 * Copyright (c) 2002 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "Axis" and "Apache Software Foundation" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache",
 *    nor may "Apache" appear in their name, without prior written
 *    permission of the Apache Software Foundation.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 */
package org.apache.axis.utils.bytecode;

import org.apache.axis.utils.JavaUtils;
import org.apache.axis.utils.Messages;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * This is the class file reader for obtaining the parameter names
 * for declared methods in a class.  The class must have debugging
 * attributes for us to obtain this information. <p>
 *
 * This does not work for inherited methods.  To obtain parameter
 * names for inherited methods, you must use a paramReader for the
 * class that originally declared the method. <p>
 *
 * don't get tricky, it's the bare minimum.  Instances of this class
 * are not threadsafe -- don't share them. <p>
 *
 * @author Edwin Smith, Macromedia
 */
public class ParamReader
        extends ClassReader {
    private String methodName;
    private Map methods = new HashMap();
    private Class[] paramTypes;

    /**
     * process a class file, given it's class.  We'll use the defining
     * classloader to locate the bytecode.
     * @param c
     * @throws IOException
     */
    public ParamReader(Class c) throws IOException {
        this(getBytes(c));
    }

    /**
     * process the given class bytes directly.
     * @param b
     * @throws IOException
     */
    public ParamReader(byte[] b) throws IOException {
        super(b, findAttributeReaders(ParamReader.class));

        // check the magic number
        if (readInt() != 0xCAFEBABE) {
            // not a class file!
            throw new IOException(Messages.getMessage("badClassFile00"));
        }

        readShort(); // minor version
        readShort(); // major version

        readCpool(); // slurp in the constant pool

        readShort(); // access flags
        readShort(); // this class name
        readShort(); // super class name

        int count = readShort(); // ifaces count
        for (int i = 0; i < count; i++) {
            readShort(); // interface index
        }

        count = readShort(); // fields count
        for (int i = 0; i < count; i++) {
            readShort(); // access flags
            readShort(); // name index
            readShort(); // descriptor index
            skipAttributes(); // field attributes
        }

        count = readShort(); // methods count
        for (int i = 0; i < count; i++) {
            readShort(); // access flags
            int m = readShort(); // name index
            String name = resolveUtf8(m);
            int d = readShort(); // descriptor index
            this.methodName = name + resolveUtf8(d);
            readAttributes(); // method attributes
        }

    }

    /**
     * return the names of the declared parameters for the given method.
     * If we cannot determine the names, return null.  The returned array will
     * have one name per parameter.  The length of the array will be the same
     * as the length of the Class[] array returned by Method.getParameterTypes().
     * @param method
     * @return String[] array of names, one per parameter, or null
     */
    public String[] getParameterNames(Method method) {
        paramTypes = method.getParameterTypes();

        // look up the names for this method
        String[] localNames = (String[]) methods.get(getSignature(method, paramTypes));

        // we know all the local variable names, but we only need to return
        // the names of the parameters.

        if (localNames != null) {
            String[] paramNames = new String[paramTypes.length];
            int j = Modifier.isStatic(method.getModifiers()) ? 0 : 1;

            for (int i = 0; i < paramNames.length; i++) {
                paramNames[i] = localNames[j++];
                if (paramTypes[i] == double.class || paramTypes[i] == long.class) {
                    // skip a slot for 64bit params
                    j++;
                }
            }

            return paramNames;
        } else {
            return null;
        }
    }

    /**
     * this is invoked when a LocalVariableTable attribute is encountered.
     * @throws IOException
     */
    public void readLocalVariableTable() throws IOException {
        int len = readShort(); // table length
        String[] names = null;
        if (methods != null && methodName != null) {
            // need enough room in the table to handle double-wide parameters
            names = new String[2 * len];
            methods.put(methodName, names);
        }
        for (int j = 0; j < len; j++) {
            readShort(); // start pc
            readShort(); // length
            int n = readShort(); // name_index
            readShort(); // descriptor_index
            int index = readShort(); // index

            if (names != null && index < 2 * len) {
                names[index] = resolveUtf8(n);
            }
        }
    }

}
