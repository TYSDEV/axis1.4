package org.apache.axis2.management.manager;

import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.engine.AxisFault;
import org.apache.axis2.management.mbeans.Axis2Manager;

public class JMXAdmin{

	/**
	 * This method is used to initailize and register MBeans.
	 *
	 * @param configContext
	 * @throws AxisFault
     */
	public static void initMBeans(ConfigurationContext configContext) throws AxisFault{

		try{

			// Create MBeans
			String axis2ManagerName = "Axis2:type=management.Manager,index=1";
			Axis2Manager axis2Manager = new Axis2Manager(configContext.getAxisConfiguration());

			// Register MBeans using JMXManager
			JMXManagerR jmxManager = JMXManagerR.getJMXManagerR();
			jmxManager.registerMBean(axis2Manager, axis2ManagerName);


		}catch(Exception e){
			throw AxisFault.makeFault(e);
		}

	}

}