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

package org.apache.axis.wsdl.wsdl2ws.c;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.apache.axis.wsdl.wsdl2ws.SourceWriter;
import org.apache.axis.wsdl.wsdl2ws.WrapperFault;
import org.apache.axis.wsdl.wsdl2ws.WrapperUtils;
import org.apache.axis.wsdl.wsdl2ws.CUtils;
import org.apache.axis.wsdl.wsdl2ws.info.WebServiceContext;

public class ClassLoader implements SourceWriter{
	private Writer writer;
	private WebServiceContext wscontext;
	private String classname;
	public ClassLoader(WebServiceContext wscontext) throws WrapperFault {
		this.wscontext = wscontext;
		this.classname = WrapperUtils.getClassNameFromFullyQualifiedName(wscontext.getSerInfo().getQualifiedServiceName());
	}

	public void writeSource() throws WrapperFault {
		String wrappername = classname + CUtils.WRAPPER_NAME_APPENDER;
	try{	
	  this.writer = new BufferedWriter(new FileWriter(getFilePath(), false));
	  writer.write("/*This file is automatically generated by the Axis C++ Wrapper Class Generator\n");
	  writer.write(" *Service file containing the two export functions of the Web service Library*/\n");
	  writer.write("#ifdef WIN32\n");
	  writer.write("#define STORAGE_CLASS_INFO __declspec(dllexport)\n");
	  writer.write("#else\n");
	  writer.write("#define STORAGE_CLASS_INFO \n");
	  writer.write("#endif\n\n");
	  writer.write("#include <malloc.h>\n");
	  writer.write("#include \""+wrappername+".h\"\n\n");
	  writer.write("static BasicHandlerFunctions "+wrappername+"_functions = {\n");
	  writer.write("\t"+wrappername+"_Invoke,\n");
	  writer.write("\t"+wrappername+"_OnFault,\n");
	  writer.write("\t"+wrappername+"_Init,\n");
	  writer.write("\t"+wrappername+"_Fini,\n");
	  writer.write("\t"+wrappername+"_GetType,\n");
	  writer.write("\t"+wrappername+"_GetBindingStyle\n};\n");	  
	  writer.write("STORAGE_CLASS_INFO\n");
	  writer.write("int GetClassInstance(BasicHandler **inst){\n");
	  writer.write("\t*inst = malloc(sizeof(BasicHandler));\n");
	  writer.write("\t(*inst)->_object = 0;	/* instantiate and provide the context object if needed */\n");
	  writer.write("\t(*inst)->_functions = &"+wrappername+"_functions;\n");
	  writer.write("\treturn (*inst)->_functions->init((*inst)->_object);\n");
	  writer.write("}\n\n");
	  writer.write("STORAGE_CLASS_INFO\n");
	  writer.write("int DestroyInstance(BasicHandler *inst){\n");
	  writer.write("\tif (inst){\n");
	  writer.write("\t\tinst->_functions->fini(inst->_object);\n");
	  writer.write("\t\t/* destroy the context object set to inst->_object if any here */\n");
	  writer.write("\t\tfree(inst);\n");
	  writer.write("\t\treturn AXIS_SUCCESS;\n");
	  writer.write("\t}\n");
	  writer.write("\treturn AXIS_FAIL;\n");
	  writer.write("}\n");
	  writer.flush();
	  writer.close();
	  System.out.println(getFilePath().getAbsolutePath() + " created.....");
	 }catch(IOException e){
		throw new WrapperFault(e);
	 }
	}

	protected File getFilePath() throws WrapperFault {
		String targetOutputLocation = this.wscontext.getWrapInfo().getTargetOutputLocation();
		if(targetOutputLocation.endsWith("/"))
			targetOutputLocation = targetOutputLocation.substring(0, targetOutputLocation.length() - 1);
		new File(targetOutputLocation).mkdirs();
		String fileName = targetOutputLocation + "/" + classname + CUtils.CLASS_LODER_APPENDER + ".c";
		return new File(fileName);
	}

}
