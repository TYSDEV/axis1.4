/*
 * The Apache Software License, Version 1.1
 *
 *
 * Copyright (c) 2001-2003 The Apache Software Foundation.  All rights
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

package org.apache.axis.wsdl.jaxme;
import java.util.HashMap;

import javax.xml.namespace.QName;

import org.apache.ws.jaxme.xs.parser.XSContext;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.NamespaceSupport;


/**
 * This class is extend from the JAXP org.xml.sax.helpers.AttributesImpl.
 * It gives the namespace support for the parsing
 * @author Srinath Perera(hemapani@opensource.lk)
 */
public class AxisAttributeImpl extends AttributesImpl {
	private NamespaceSupport nssupport;
	private HashMap map = new HashMap();
	private XSContext context;

	public AxisAttributeImpl(XSContext context){
		this.context = context;
		this.nssupport= context.getNamespaceSupport() ;
	}
	
    public void addAttribute(
        String uri,
        String localName,
        String qName,
        String type,
        String value) {
        super.addAttribute(uri, localName, qName, type, value);
        
        if(localName == null){
			localName = qName;
        }
        
		int prefixIndex = localName.indexOf((int)':');
		if(prefixIndex < 0){
		}else{
			localName =  localName.substring(prefixIndex+1);
		} 
		
		QName qvalue = null;
        
		prefixIndex = value.indexOf((int)':');
			
			
		if(prefixIndex < 0){
			//set the default namespace
			qvalue = new QName(nssupport.getURI(""),value);
		}else{
			String prefix =  value.substring(0,prefixIndex);
			value =  value.substring(prefixIndex+1);
			qvalue = new QName(nssupport.getURI(prefix),value);
		}

		this.map.put(localName,qvalue);
    }
    
    public QName getValueAsQName(String name){
    	return (QName)this.map.get(name);
    }
}
