package org.apache.axis2.management.mbeans;

import org.apache.axis2.engine.AxisConfiguration;
import org.apache.axis2.engine.AxisConfigurationImpl;
import org.apache.axis2.description.ServiceDescription;
import org.apache.axis2.description.ModuleDescription;
import org.apache.axis2.description.OperationDescription;
import org.apache.axis2.engine.Phase;
import org.apache.axis2.engine.AxisFault;

import javax.xml.namespace.QName;
import java.util.*;

public class Axis2Manager implements Axis2ManagerMBean{

	private AxisConfiguration axisConfig;

	public Axis2Manager(AxisConfiguration axisConfig){
		this.axisConfig = axisConfig;
	}

	public String[] getServices(){

		String[] services = null;

		// get the service list
		HashMap serviceMap = axisConfig.getServices();
		if(serviceMap!=null && !serviceMap.isEmpty()){
			Collection serviceCollection = serviceMap.values();

			// add name of each service to a string array
			services = new String[serviceCollection.size()];

			int i=0;
			for(Iterator iterator=serviceCollection.iterator(); iterator.hasNext();){
				ServiceDescription axisService = (ServiceDescription)iterator.next();
				String serviceName = axisService.getName().getLocalPart();

				services[i] = serviceName;
				i++;
			}

		}

		return services;
	}


	public String[] getFaultyServices(){

		String[] faultyServices = null;

		Hashtable faultyServicesTable = axisConfig.getFaulytServices();
		if(faultyServicesTable != null && faultyServicesTable.size() > 0){

			faultyServices = new String[faultyServicesTable.size()];
			Enumeration eFaultyServices = faultyServicesTable.keys();

			int i=0;
			while (eFaultyServices.hasMoreElements()) {
				String faultyServiceName = (String)eFaultyServices.nextElement();
				faultyServices[i] = faultyServiceName;
				i++;
			}
		}

		return faultyServices;
	}


	public String[] getModules(){

		String[] modules = null;
		ArrayList moduleList = new ArrayList();

		// get the module list
		HashMap moduleMap = ((AxisConfigurationImpl)axisConfig).getModules();
		if(moduleMap!=null && !moduleMap.isEmpty()){
			Collection moduleCollection = moduleMap.values();

			modules = new String[moduleCollection.size()];
			int i=0;
			for(Iterator iterator=moduleCollection.iterator(); iterator.hasNext();){
				ModuleDescription axisModule = (ModuleDescription)iterator.next();
				String moduleName = axisModule.getName().getLocalPart();

				modules[i] = moduleName;
				i++;
			}

		}

		return modules;
	}


	public String[] getFaultyModules(){

		String[] faultyModules = null;

		Hashtable faultyModulesTable = axisConfig.getFaulytModules();
		if(faultyModulesTable != null && faultyModulesTable.size() > 0){

			faultyModules = new String[faultyModulesTable.size()];
			Enumeration eFaultyModules = faultyModulesTable.keys();

			int i = 0;
			while (eFaultyModules.hasMoreElements()) {
				String faultyModuleName = (String) eFaultyModules.nextElement();
				faultyModules[i] = faultyModuleName;
				i++;
			}
		}

		return faultyModules;
	}


	public String[] getGloballyEngagedModules(){

		String[] modules = null;
		Collection engagedModuleCollection = ((AxisConfigurationImpl)axisConfig).getEngadgedModules();

		if(engagedModuleCollection != null && engagedModuleCollection.size() > 0) {

			modules = new String[engagedModuleCollection.size()];

			int i = 0;
			for(Iterator iterator=engagedModuleCollection.iterator(); iterator.hasNext();){
				QName axisModule = (QName) iterator.next();
                String moduleName = axisModule.getLocalPart();

				modules[i] = moduleName;
				i++;
			}
		}

		return modules;
	}


	public String[] getPhases(){

		ArrayList phaseList = axisConfig.getInPhasesUptoAndIncludingPostDispatch();
		String[] phases = new String[phaseList.size()];

		for(int i=0; i<phaseList.size(); i++){
			Phase phase = (Phase)phaseList.get(i);
			phases[i] = phase.getPhaseName();
		}

		return phases;
	}


	public String[] getOperations (String serviceName) throws Exception{

		String[] operations = null;

		HashMap operationsMap = axisConfig.getService(new QName(serviceName)).getOperations();

		if(operationsMap !=null && !operationsMap.isEmpty()){

			Collection operationsCollection = operationsMap.values();
			operations = new String[operationsCollection.size()];

			int i = 0;
			for(Iterator iterator=operationsCollection.iterator(); iterator.hasNext();){
				OperationDescription operationDesc = (OperationDescription)iterator.next();

				operations[i] = operationDesc.getName().getLocalPart();
				i++;
			}
		}

		return operations;
	}


	public String engageModuleGlobally(String moduleName){

		String info = "";

		try{
			axisConfig.engageModule(new QName(moduleName));
			info = "Module: " + moduleName + " globally enagaged successfully.";
		}
		catch(AxisFault axisFault){
			info = "Module: " + moduleName + " failed to engage globally.";
		}

		return info;
	}


	public String engageModuleToService(String moduleName, String serviceName){

		String info = "";

		if ( moduleName != null && serviceName != null) {

			try {

				axisConfig.getService(new QName(serviceName)).engageModule(axisConfig.getModule(new QName(moduleName)));
				info = "Module " + moduleName + " is successfully engaged to the service " + serviceName;

			} catch (AxisFault axisFault) {

				info = "Failed to engage the module " + moduleName + " to the service " + serviceName;
			}
        }

		return info;
	}


	public String engageModuleToOperation(String moduleName, String serviceName, String operationName){

		String info = "";

		if ( moduleName != null && serviceName != null && operationName != null) {
			try {
				OperationDescription operation = axisConfig.getService(new QName(serviceName)).getOperation(new QName(operationName));
				operation.engageModule(axisConfig.getModule(new QName(moduleName)));

				info = moduleName + " module engaged to the operation Successfully";

			} catch (AxisFault axisFault) {
				info = info = "Failed to engage the module " + moduleName + " to the operation " + operationName;
			}
        }

		return info;

	}



}