/*
 *   Copyright 2003-2004 The Apache Software Foundation.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

 
/**
 * @author Srinath Perera(hemapani@openource.lk)
 * @author Susantha Kumara(susantha@opensource.lk, skumara@virtusa.com)
 */


package org.apache.axis.wsdl.wsdl2ws.cpp.literal;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.axis.wsdl.wsdl2ws.WrapperFault;
import org.apache.axis.wsdl.wsdl2ws.BasicFileWriter;

public abstract class CPPClassWriter extends BasicFileWriter{
	public CPPClassWriter(String classname)throws WrapperFault{
		super(classname);
	}
	public void writeSource()throws WrapperFault{
	   try{
	  this.writer = new BufferedWriter(new FileWriter(getFilePath(), false));
	   writeClassComment();
	   writePreprocssorStatements();
	   writeGlobalCodes();

	  // this.writer.write("public class "+servicename+getExtendsPart()+"{\n");

	   writeAttributes();
	   writeConstructors();
	   writeDistructors();
	   writeMethods();
	   //this.writer.write("}\n");
	   //cleanup
	   writer.flush();
	   writer.close();
	   System.out.println(getFilePath().getAbsolutePath() + " created.....");

	   } catch (IOException e) {
			e.printStackTrace();
			throw new WrapperFault(e);
		}

	}
	protected void writeGlobalCodes()throws WrapperFault{}
}



