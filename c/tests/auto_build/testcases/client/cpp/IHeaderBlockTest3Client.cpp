
/* Tests createNamespaceDecl(),createAttribute() and getAttributeValue() APIs in IHeaderBlock 
@ Author : James Jose
*/


#include "Calculator.hpp"
#include <axis/IHeaderBlock.hpp>
#include <axis/IAttribute.hpp>
#include <axis/AxisException.hpp>
#include <stdio.h>
#include <iostream>

int main(int argc, char* argv[])
{
	char endpoint[256];
	const char* url="http://localhost:80/axis/Calculator";
	const char* op = 0;
	int i1=0, i2=0;
	int iResult;
	url = argv[1];
	try
	{
		sprintf(endpoint, "%s", url);
		Calculator ws(endpoint);
		op = "add";
		i1 = 2;
		i2 = 3; 
		const AxisChar *prefix="np";
		const AxisChar *uri="http://ws.apache.org/";
		IHeaderBlock *phb = ws.createSOAPHeaderBlock("TestHeader","http://ws.apache.org/");
		INamespace *Insp1=phb->createNamespaceDecl(prefix,uri);			
		INamespace *Insp2=phb->createNamespaceDecl("np1","http://axis.apache.org/");
		const AxisChar *temp="apache";
		const AxisChar *localname="name";	
		IAttribute *Iattr1=phb->createAttribute(localname,prefix,temp);		
		IAttribute *Iattr2=phb->createAttribute(localname,"np1","","Axis");		
		IAttribute *Iattr3=phb->createAttribute(localname,"","","");		
		cout << "np:name=" << phb->getAttributeValue(localname,prefix)<<endl;
		cout << "np1:name=" << phb->getAttributeValue("name","np1")<<endl;
		cout <<"name=" << phb->getAttributeValue("name","")<<endl;
		if(phb->getAttributeValue(NULL,NULL)!=NULL)
			cout<<"NULL is not returned for NULL argumets in getAttributeValue"<<endl;
		if(phb->getAttributeValue("name","nm")!=NULL)
			cout<<"NULL is not returned for Non existing attribute"<<endl;
		if(phb->getAttributeValue("NAME","np1")!=NULL)
			cout<<"NULL is not returned for Non existing Attribute"<<endl;
		if (strcmp(op, "add") == 0)
		{
			iResult=ws.add(i1, i2);			
			cout << iResult << endl;
		}
	    

	}
	catch(AxisException& e)
	{
		cout << "Exception : " << e.what() << endl;
	}
	catch(exception& e)
	{
		cout << "Unknown exception has occured" << endl;
	}
	catch(...)
	{
		cout << "Unspecified exception has occured" << endl;
	}
	cout<< "---------------------- TEST COMPLETE -----------------------------"<< endl;	
	return 0;
}
