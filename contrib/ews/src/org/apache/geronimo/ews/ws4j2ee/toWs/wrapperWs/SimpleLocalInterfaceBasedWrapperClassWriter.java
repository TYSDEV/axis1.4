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
package org.apache.geronimo.ews.ws4j2ee.toWs.wrapperWs;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.geronimo.ews.ws4j2ee.context.J2EEWebServiceContext;
import org.apache.geronimo.ews.ws4j2ee.context.SEIOperation;
import org.apache.geronimo.ews.ws4j2ee.toWs.GenerationFault;

/**
 * @author Srinath Perera(hemapani@opensource.lk)
 */
public class SimpleLocalInterfaceBasedWrapperClassWriter extends EJBBasedWrapperClassWriter{

	/**
	 * @param j2eewscontext
	 * @throws GenerationFault
	 */
	public SimpleLocalInterfaceBasedWrapperClassWriter(J2EEWebServiceContext j2eewscontext)
		throws GenerationFault {
		super(j2eewscontext);
		seiName = context.getEjbLocalInterface();
	}
    
	
	protected void writeMethods() throws GenerationFault {
		out.write("\tpublic void setMessageContext(org.apache.axis.MessageContext msgcontext){;\n");
		out.write("\t\tthis.msgcontext = msgcontext;\n");
		out.write("\t}\n");
		writeGetRemoteRef(classname);
		
		String parmlistStr = null;
		 ArrayList operations = j2eewscontext.getMiscInfo().getSEIOperations();
		 for(int i =0;i<operations.size();i++){
			parmlistStr = "";
			 SEIOperation op = (SEIOperation)operations.get(i);
			 String returnType = op.getReturnType();
			 if(returnType == null)
				returnType = "void";
			 out.write("\tpublic "+returnType+" "+op.getMethodName()+"(");
				
			 Iterator pas = op.getParameterNames().iterator();
			 boolean first = true;
			 while(pas.hasNext()){
				 String name = (String)pas.next();
				 String type = op.getParameterType(name);
				 if(first){ 
					 first = false;
					 out.write(type + " " +name);
					 parmlistStr = parmlistStr + name;
				 }else{
					 out.write(","+type + " " +name);
					 parmlistStr = parmlistStr + ","+name;
				 }
						
			 }
				
			 out.write(") throws java.rmi.RemoteException");
			 ArrayList faults = op.getFaults();
			 for(int j = 0;j<faults.size();j++){
				 out.write(","+(String)faults.get(i));
			 }
			 out.write("{\n");
			 out.write("\t\tif(ejb ==  null)\n");
			 out.write("\t\t\tejb = getRemoteRef();\n");

			 if(!"void".equals(returnType))
				 out.write("\t\treturn ejb."+op.getMethodName()+"("+parmlistStr+");\n");
			 else
				 out.write("\t\tejb."+op.getMethodName()+"("+parmlistStr+");\n");	
				
			 out.write("\t}\n");
		 }
		 //out.write("}\n");	

	}
	private void writeGetRemoteRef(String classname){
//	   out.write("\tpublic "+seiName+" getRemoteRef()throws org.apache.axis.AxisFault{\n");
//	   out.write("\t\ttry {\n");
//	   out.write("\t\t    javax.security.auth.callback.CallbackHandler handler\n");
//	   out.write("\t\t        = org.apache.geronimo.ews.ws4j2ee.wsutils.security.jaasmodules.\n");
//	   out.write("\t\t            AutenticationCallbackHandlerFactory.createCallbackHandler(msgcontext);\n");
//	   out.write("\t\t    if(handler != null){\n");
//	   out.write("\t\t        javax.security.auth.login.LoginContext lc\n"); 
//	   out.write("\t\t            = new javax.security.auth.login.LoginContext(\"TestClient\", handler);\n");
//	   out.write("\t\t        lc.login();\n");
//	   out.write("\t\t    }\n");
//	   out.write("\t\t}catch (javax.security.auth.login.LoginException e) {\n");
//	   out.write("\t\t     e.printStackTrace();\n");
//	   out.write("\t\t     throw org.apache.axis.AxisFault.makeFault(e);\n");
//	   out.write("\t\t}\n");
   	
	   out.write("\t\ttry{\n");
	   String ejbname = j2eewscontext.getWSDLContext().getTargetPortType().getName().toLowerCase();
		int index = ejbname.lastIndexOf(".");
		if(index>0){
		  ejbname = ejbname.substring(index+1);
		}
	   out.write("\t\t\tjavax.naming.Context initial = new javax.naming.InitialContext();\n");
	   out.write("\t\t\tObject objref = jndiContext.lookup(\"java:comp/env/ejb/\""+ejbname+");\n");
	   String ejbhome = j2eewscontext.getEJBDDContext().getEjbhomeInterface();
	   out.write("\t\t\t"+ejbhome+" home = ("+ejbhome+")objref;\n");
	   out.write("\t\t\treturn home.create();\n");
	   out.write("\t\t}catch (Exception e) {\n");
	   out.write("\t\t    throw org.apache.axis.AxisFault.makeFault(e);\n");
	   out.write("\t\t}\n");
	   out.write("\t}\n");
	}
}
