package org.apache.axis2.management.manager;

import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.engine.AxisFault;
import org.apache.axis2.management.mbeans.Axis2Manager;

import java.io.PrintWriter;
import java.io.FileOutputStream;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.management.*;
import javax.management.remote.*;
import java.util.*;
import java.net.*;

public class JMXManager{

	private ConfigurationContext configContext;

	JMXConnectorServer cs;

	public JMXManager(ConfigurationContext configContext){
		this.configContext = configContext;
	}


	public void startMBeanServer() throws AxisFault{


		 try{
			initMBeans();
		 }catch(Exception e){
			throw new AxisFault(e.getMessage());
		 }

	}


	public void initMBeans() throws Exception{

			Registry reg=null;
			String domain = "Axis2";


			try {
				if( reg==null )
	       			reg=LocateRegistry.createRegistry(9995);
	       	 } catch(Exception e) {
	          		throw new AxisFault(e.getMessage());
	       	 }


			// try to find existing MBeanServers. If there are no MBeanServers create a new one for Axis2
			MBeanServer mbs = null;
			if (MBeanServerFactory.findMBeanServer(null).size() > 0) {
				mbs = (MBeanServer) MBeanServerFactory.findMBeanServer(null).get(0);
			} else {
				mbs = MBeanServerFactory.createMBeanServer();
			}


			String axis2ManagerNameStr = domain+":type=management.Manager,index=1";
			ObjectName axis2ManagerName = ObjectName.getInstance(axis2ManagerNameStr);

			Axis2Manager axis2Manager = new Axis2Manager(configContext.getAxisConfiguration());
			mbs.registerMBean(axis2Manager, axis2ManagerName);


			JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:9995/axis2");
			cs = JMXConnectorServerFactory.newJMXConnectorServer(url, null, mbs);
			cs.start();

	}

}