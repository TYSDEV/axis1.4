/*
 * The Apache Software License, Version 1.1
 *
 *
 * Copyright (c) 2001 The Apache Software Foundation.  All rights 
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

package org.apache.axis.registries ;

import java.io.* ;
import java.util.* ;
import org.apache.axis.* ;
import org.apache.axis.registries.* ;
import org.apache.axis.suppliers.*;

/** A <code>SupplierRegistry</code> contains Suppliers, which are used
 * by the find() method to obtain actual Handler references.  This
 * allows creational dynamics to be configured on a per-handler basis.
 *
 * @author Glen Daniels (gdaniels@allaire.com)
 */
public class SupplierRegistry implements HandlerRegistry {
    protected String     fileName;
    protected Hashtable  suppliers = null ;
    
    public SupplierRegistry(String fileName)
    {
        this.fileName = fileName;
    }
    
    /**
     * Init (ie. load settings...)
     */
    public void init() {
        load();
    }

    /**
     * Add a new Handler to the registry.
     */
    public void add(String key, Handler handler) {
        if ( suppliers == null ) suppliers = new Hashtable();
        suppliers.put( key, new SimpleSupplier(handler) );
        save();
    }
    
    public void add(String key, Supplier supplier) {
        if ( suppliers == null ) suppliers = new Hashtable();
        suppliers.put( key, supplier );
        save();
    }
    
    /**
     * Remove a Handler (locate by key) from the registry - returns old
     * value if it was there - or null if not.
     */
    public Handler remove(String key) {
        if ( suppliers == null ) return( null );
        Object old = suppliers.remove( key );
        save();
        
        // What should we do here?
        return null;
    }

    /**
     * Given a 'key' return the corresponding Handler
     */
    public Handler find(String key) {
        if ( suppliers == null ) return( null );
        Supplier supplier = (Supplier)suppliers.get(key);
        if (supplier == null) return null;
        return supplier.getHandler();
    }

    /**
     * Return the list (in an array) of keys for the Handlers
     */
    public String[] list(){
        int  loop =  0 ;

        if ( suppliers == null ) return( null );
        String[]  result = new String[suppliers.size()];
        Enumeration  keys = suppliers.keys();
        while ( keys.hasMoreElements() )
            result[loop++] = (String) keys.nextElement();
        return( result );
    }

    private void load() { 
        try {
            FileInputStream    fis = new FileInputStream( fileName );
            ObjectInputStream  ois = new ObjectInputStream( fis );
            suppliers = (Hashtable) ois.readObject();
            fis.close();
        }
        catch( Exception e ) {
            if ( !(e instanceof FileNotFoundException) )
                e.printStackTrace( System.err );
        }
    }

    private void save() {
        try {
            FileOutputStream    fos = new FileOutputStream( fileName );
            ObjectOutputStream  oos = new ObjectOutputStream( fos );
            oos.writeObject( suppliers );
            fos.close();
        }
        catch( Exception e ) {
            e.printStackTrace( System.err );
        }
    }
};
