/*
 * The Apache Software License, Version 1.1
 *
 *
 * Copyright (c) 2003 The Apache Software Foundation.  All rights
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
 
/**
 * @author Srinath Perera(hemapani@openource.lk)
 * @author Susantha Kumara(susantha@opensource.lk, skumara@virtusa.com)
 */

package org.apache.axis.wsdl.wsdl2ws.c;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.axis.wsdl.wsdl2ws.WrapperFault;

public abstract class HeaderFileWriter extends BasicFileWriter {
	public HeaderFileWriter(String classname)throws WrapperFault{
		super(classname);
	}
	public void writeSource()throws WrapperFault{
	   try{
	  this.writer = new BufferedWriter(new FileWriter(getFilePath(), false));
	   writeClassComment();
	   // if this headerfile not defined define it 
	   this.writer.write("#if !defined(__"+classname.toUpperCase()+"_H__INCLUDED_)\n");
	   this.writer.write("#define __"+classname.toUpperCase()+"_H__INCLUDED_\n\n");
	   //includes
	   writePreprocssorStatements();
		//class
	   this.writer.write("class "+classname+getExtendsPart()+"\n{\n");
	   writeAttributes();
	   writeConstructors();
	   writeDistructors();
	   writeMethods();
	   this.writer.write("};\n\n");
	   this.writer.write("#endif // !defined(__"+classname.toUpperCase()+"_H__INCLUDED_)\n");
	   //cleanup
	   writer.flush();
	   writer.close();
	   System.out.println(getFilePath().getAbsolutePath() + " created.....");

	   } catch (IOException e) {
			e.printStackTrace();
			throw new WrapperFault(e);
		}

	}
	protected String getExtendsPart(){return " ";}
	protected abstract File getFilePath()throws WrapperFault;

}
