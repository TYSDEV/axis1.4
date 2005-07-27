/*
 * Created on Jun 29, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.apache.axis2.management.mbeans;

/**
 * @author CEkanayake
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface Axis2ManagerMBean {

	public String[] getServices();

	public String[] getFaultyServices();

	public String[] getModules();

	public String[] getFaultyModules();

	public String[] getGloballyEngagedModules();

	public String[] getPhases();

	public String[] getOperations(String serviceName) throws Exception;

	public String engageModuleGlobally(String moduleName);

	public String engageModuleToService(String moduleName, String serviceName);

	public String engageModuleToOperation(String moduleName, String serviceName, String operationName);

}
